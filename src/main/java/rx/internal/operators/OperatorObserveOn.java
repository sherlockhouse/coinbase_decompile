package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable$Operator;
import rx.Producer;
import rx.Scheduler;
import rx.Scheduler$Worker;
import rx.Subscriber;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.internal.schedulers.ImmediateScheduler;
import rx.internal.schedulers.TrampolineScheduler;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaHooks;

public final class OperatorObserveOn<T> implements Observable$Operator<T, T> {
    private final int bufferSize;
    private final boolean delayError;
    private final Scheduler scheduler;

    static final class ObserveOnSubscriber<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super T> child;
        final AtomicLong counter = new AtomicLong();
        final boolean delayError;
        long emitted;
        Throwable error;
        volatile boolean finished;
        final int limit;
        final Queue<Object> queue;
        final Scheduler$Worker recursiveScheduler;
        final AtomicLong requested = new AtomicLong();

        public ObserveOnSubscriber(Scheduler scheduler, Subscriber<? super T> child, boolean delayError, int bufferSize) {
            this.child = child;
            this.recursiveScheduler = scheduler.createWorker();
            this.delayError = delayError;
            int calculatedSize = bufferSize > 0 ? bufferSize : RxRingBuffer.SIZE;
            this.limit = calculatedSize - (calculatedSize >> 2);
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.queue = new SpscArrayQueue(calculatedSize);
            } else {
                this.queue = new SpscAtomicArrayQueue(calculatedSize);
            }
            request((long) calculatedSize);
        }

        void init() {
            Subscriber<? super T> localChild = this.child;
            localChild.setProducer(new Producer() {
                public void request(long n) {
                    if (n > 0) {
                        BackpressureUtils.getAndAddRequest(ObserveOnSubscriber.this.requested, n);
                        ObserveOnSubscriber.this.schedule();
                    }
                }
            });
            localChild.add(this.recursiveScheduler);
            localChild.add(this);
        }

        public void onNext(T t) {
            if (!isUnsubscribed() && !this.finished) {
                if (this.queue.offer(NotificationLite.next(t))) {
                    schedule();
                } else {
                    onError(new MissingBackpressureException());
                }
            }
        }

        public void onCompleted() {
            if (!isUnsubscribed() && !this.finished) {
                this.finished = true;
                schedule();
            }
        }

        public void onError(Throwable e) {
            if (isUnsubscribed() || this.finished) {
                RxJavaHooks.onError(e);
                return;
            }
            this.error = e;
            this.finished = true;
            schedule();
        }

        protected void schedule() {
            if (this.counter.getAndIncrement() == 0) {
                this.recursiveScheduler.schedule(this);
            }
        }

        public void call() {
            long missed = 1;
            long currentEmission = this.emitted;
            Queue<Object> q = this.queue;
            Subscriber<? super T> localChild = this.child;
            do {
                long requestAmount = this.requested.get();
                while (requestAmount != currentEmission) {
                    boolean done = this.finished;
                    Object v = q.poll();
                    boolean empty = v == null;
                    if (!checkTerminated(done, empty, localChild, q)) {
                        if (empty) {
                            break;
                        }
                        localChild.onNext(NotificationLite.getValue(v));
                        currentEmission++;
                        if (currentEmission == ((long) this.limit)) {
                            requestAmount = BackpressureUtils.produced(this.requested, currentEmission);
                            request(currentEmission);
                            currentEmission = 0;
                        }
                    } else {
                        return;
                    }
                }
                if (requestAmount != currentEmission || !checkTerminated(this.finished, q.isEmpty(), localChild, q)) {
                    this.emitted = currentEmission;
                    missed = this.counter.addAndGet(-missed);
                } else {
                    return;
                }
            } while (missed != 0);
        }

        boolean checkTerminated(boolean done, boolean isEmpty, Subscriber<? super T> a, Queue<Object> q) {
            if (a.isUnsubscribed()) {
                q.clear();
                return true;
            }
            if (done) {
                Throwable e;
                if (!this.delayError) {
                    e = this.error;
                    if (e != null) {
                        q.clear();
                        try {
                            a.onError(e);
                            return true;
                        } finally {
                            this.recursiveScheduler.unsubscribe();
                        }
                    } else if (isEmpty) {
                        try {
                            a.onCompleted();
                            return true;
                        } finally {
                            this.recursiveScheduler.unsubscribe();
                        }
                    }
                } else if (isEmpty) {
                    e = this.error;
                    if (e != null) {
                        try {
                            a.onError(e);
                        } catch (Throwable th) {
                            this.recursiveScheduler.unsubscribe();
                        }
                    } else {
                        a.onCompleted();
                    }
                    this.recursiveScheduler.unsubscribe();
                }
            }
            return false;
        }
    }

    public OperatorObserveOn(Scheduler scheduler, boolean delayError, int bufferSize) {
        this.scheduler = scheduler;
        this.delayError = delayError;
        if (bufferSize <= 0) {
            bufferSize = RxRingBuffer.SIZE;
        }
        this.bufferSize = bufferSize;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        if ((this.scheduler instanceof ImmediateScheduler) || (this.scheduler instanceof TrampolineScheduler)) {
            return child;
        }
        Subscriber parent = new ObserveOnSubscriber(this.scheduler, child, this.delayError, this.bufferSize);
        parent.init();
        return parent;
    }
}
