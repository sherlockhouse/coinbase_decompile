package rx.internal.operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.FuncN;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.plugins.RxJavaHooks;

public final class OnSubscribeCombineLatest<T, R> implements Observable$OnSubscribe<R> {
    final int bufferSize;
    final FuncN<? extends R> combiner;
    final boolean delayError;
    final Observable<? extends T>[] sources;
    final Iterable<? extends Observable<? extends T>> sourcesIterable;

    static final class CombinerSubscriber<T, R> extends Subscriber<T> {
        boolean done;
        final int index;
        final LatestCoordinator<T, R> parent;

        public CombinerSubscriber(LatestCoordinator<T, R> parent, int index) {
            this.parent = parent;
            this.index = index;
            request((long) parent.bufferSize);
        }

        public void onNext(T t) {
            if (!this.done) {
                this.parent.combine(NotificationLite.next(t), this.index);
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaHooks.onError(t);
                return;
            }
            this.parent.onError(t);
            this.done = true;
            this.parent.combine(null, this.index);
        }

        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                this.parent.combine(null, this.index);
            }
        }

        public void requestMore(long n) {
            request(n);
        }
    }

    static final class LatestCoordinator<T, R> extends AtomicInteger implements Producer, Subscription {
        static final Object MISSING = new Object();
        int active;
        final Subscriber<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final FuncN<? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final AtomicReference<Throwable> error = new AtomicReference();
        final Object[] latest;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested = new AtomicLong();
        final CombinerSubscriber<T, R>[] subscribers;

        public LatestCoordinator(Subscriber<? super R> actual, FuncN<? extends R> combiner, int count, int bufferSize, boolean delayError) {
            this.actual = actual;
            this.combiner = combiner;
            this.bufferSize = bufferSize;
            this.delayError = delayError;
            this.latest = new Object[count];
            Arrays.fill(this.latest, MISSING);
            this.subscribers = new CombinerSubscriber[count];
            this.queue = new SpscLinkedArrayQueue(bufferSize);
        }

        public void subscribe(Observable<? extends T>[] sources) {
            int i;
            Subscriber<T>[] as = this.subscribers;
            int len = as.length;
            for (i = 0; i < len; i++) {
                as[i] = new CombinerSubscriber(this, i);
            }
            lazySet(0);
            this.actual.add(this);
            this.actual.setProducer(this);
            for (i = 0; i < len && !this.cancelled; i++) {
                sources[i].subscribe(as[i]);
            }
        }

        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= required but it was " + n);
            } else if (n != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                drain();
            }
        }

        public void unsubscribe() {
            if (!this.cancelled) {
                this.cancelled = true;
                if (getAndIncrement() == 0) {
                    cancel(this.queue);
                }
            }
        }

        public boolean isUnsubscribed() {
            return this.cancelled;
        }

        void cancel(Queue<?> q) {
            q.clear();
            for (CombinerSubscriber<T, R> s : this.subscribers) {
                s.unsubscribe();
            }
        }

        void combine(Object value, int index) {
            boolean allSourcesFinished;
            boolean empty = false;
            CombinerSubscriber<T, R> combinerSubscriber = this.subscribers[index];
            synchronized (this) {
                int sourceCount = this.latest.length;
                Object o = this.latest[index];
                int activeCount = this.active;
                if (o == MISSING) {
                    activeCount++;
                    this.active = activeCount;
                }
                int completedCount = this.complete;
                if (value == null) {
                    completedCount++;
                    this.complete = completedCount;
                } else {
                    this.latest[index] = NotificationLite.getValue(value);
                }
                if (activeCount == sourceCount) {
                    allSourcesFinished = true;
                } else {
                    allSourcesFinished = false;
                }
                if (completedCount == sourceCount || (value == null && o == MISSING)) {
                    empty = true;
                }
                if (empty) {
                    this.done = true;
                } else if (value != null && allSourcesFinished) {
                    this.queue.offer(combinerSubscriber, this.latest.clone());
                } else if (value == null) {
                    if (this.error.get() != null && (o == MISSING || !this.delayError)) {
                        this.done = true;
                    }
                }
            }
            if (allSourcesFinished || value == null) {
                drain();
            } else {
                combinerSubscriber.requestMore(1);
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Queue<Object> q = this.queue;
                Subscriber<? super R> a = this.actual;
                boolean delayError = this.delayError;
                AtomicLong localRequested = this.requested;
                int missed = 1;
                while (!checkTerminated(this.done, q.isEmpty(), a, q, delayError)) {
                    long requestAmount = localRequested.get();
                    long emitted = 0;
                    while (emitted != requestAmount) {
                        boolean d = this.done;
                        CombinerSubscriber<T, R> cs = (CombinerSubscriber) q.peek();
                        boolean empty = cs == null;
                        if (!checkTerminated(d, empty, a, q, delayError)) {
                            if (empty) {
                                break;
                            }
                            q.poll();
                            Object[] array = (Object[]) q.poll();
                            if (array == null) {
                                this.cancelled = true;
                                cancel(q);
                                a.onError(new IllegalStateException("Broken queue?! Sender received but not the array."));
                                return;
                            }
                            try {
                                a.onNext(this.combiner.call(array));
                                cs.requestMore(1);
                                emitted++;
                            } catch (Throwable ex) {
                                this.cancelled = true;
                                cancel(q);
                                a.onError(ex);
                                return;
                            }
                        }
                        return;
                    }
                    if (!(emitted == 0 || requestAmount == Long.MAX_VALUE)) {
                        BackpressureUtils.produced(localRequested, emitted);
                    }
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            }
        }

        boolean checkTerminated(boolean mainDone, boolean queueEmpty, Subscriber<?> childSubscriber, Queue<?> q, boolean delayError) {
            if (this.cancelled) {
                cancel(q);
                return true;
            }
            if (mainDone) {
                Throwable e;
                if (!delayError) {
                    e = (Throwable) this.error.get();
                    if (e != null) {
                        cancel(q);
                        childSubscriber.onError(e);
                        return true;
                    } else if (queueEmpty) {
                        childSubscriber.onCompleted();
                        return true;
                    }
                } else if (queueEmpty) {
                    e = (Throwable) this.error.get();
                    if (e != null) {
                        childSubscriber.onError(e);
                        return true;
                    }
                    childSubscriber.onCompleted();
                    return true;
                }
            }
            return false;
        }

        void onError(Throwable e) {
            AtomicReference<Throwable> localError = this.error;
            Throwable curr;
            Throwable next;
            do {
                curr = (Throwable) localError.get();
                if (curr == null) {
                    next = e;
                } else if (curr instanceof CompositeException) {
                    List<Throwable> es = new ArrayList(((CompositeException) curr).getExceptions());
                    es.add(e);
                    next = new CompositeException(es);
                } else {
                    next = new CompositeException(Arrays.asList(new Throwable[]{curr, e}));
                }
            } while (!localError.compareAndSet(curr, next));
        }
    }

    public OnSubscribeCombineLatest(Iterable<? extends Observable<? extends T>> sourcesIterable, FuncN<? extends R> combiner) {
        this(null, sourcesIterable, combiner, RxRingBuffer.SIZE, false);
    }

    public OnSubscribeCombineLatest(Observable<? extends T>[] sources, Iterable<? extends Observable<? extends T>> sourcesIterable, FuncN<? extends R> combiner, int bufferSize, boolean delayError) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
        this.combiner = combiner;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }

    public void call(Subscriber<? super R> s) {
        Observable<? extends T>[] sources = this.sources;
        int count = 0;
        if (sources != null) {
            count = sources.length;
        } else if (this.sourcesIterable instanceof List) {
            List list = this.sourcesIterable;
            sources = (Observable[]) ((Observable[]) list.toArray(new Observable[list.size()]));
            count = sources.length;
        } else {
            sources = new Observable[8];
            for (Observable<? extends T> p : this.sourcesIterable) {
                if (count == sources.length) {
                    Observable<? extends T>[] b = new Observable[((count >> 2) + count)];
                    System.arraycopy(sources, 0, b, 0, count);
                    sources = b;
                }
                int count2 = count + 1;
                sources[count] = p;
                count = count2;
            }
        }
        if (count == 0) {
            s.onCompleted();
            return;
        }
        new LatestCoordinator(s, this.combiner, count, this.bufferSize, this.delayError).subscribe(sources);
    }
}
