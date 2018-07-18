package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.observers.SerializedSubscriber;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;

public final class OnSubscribeConcatMap<T, R> implements Observable$OnSubscribe<R> {
    final int delayErrorMode;
    final Func1<? super T, ? extends Observable<? extends R>> mapper;
    final int prefetch;
    final Observable<? extends T> source;

    static final class ConcatMapInnerScalarProducer<T, R> implements Producer {
        boolean once;
        final ConcatMapSubscriber<T, R> parent;
        final R value;

        public ConcatMapInnerScalarProducer(R value, ConcatMapSubscriber<T, R> parent) {
            this.value = value;
            this.parent = parent;
        }

        public void request(long n) {
            if (!this.once && n > 0) {
                this.once = true;
                ConcatMapSubscriber<T, R> p = this.parent;
                p.innerNext(this.value);
                p.innerCompleted(1);
            }
        }
    }

    static final class ConcatMapInnerSubscriber<T, R> extends Subscriber<R> {
        final ConcatMapSubscriber<T, R> parent;
        long produced;

        public ConcatMapInnerSubscriber(ConcatMapSubscriber<T, R> parent) {
            this.parent = parent;
        }

        public void setProducer(Producer p) {
            this.parent.arbiter.setProducer(p);
        }

        public void onNext(R t) {
            this.produced++;
            this.parent.innerNext(t);
        }

        public void onError(Throwable e) {
            this.parent.innerError(e, this.produced);
        }

        public void onCompleted() {
            this.parent.innerCompleted(this.produced);
        }
    }

    static final class ConcatMapSubscriber<T, R> extends Subscriber<T> {
        volatile boolean active;
        final Subscriber<? super R> actual;
        final ProducerArbiter arbiter = new ProducerArbiter();
        final int delayErrorMode;
        volatile boolean done;
        final AtomicReference<Throwable> error = new AtomicReference();
        final SerialSubscription inner;
        final Func1<? super T, ? extends Observable<? extends R>> mapper;
        final Queue<Object> queue;
        final AtomicInteger wip = new AtomicInteger();

        public ConcatMapSubscriber(Subscriber<? super R> actual, Func1<? super T, ? extends Observable<? extends R>> mapper, int prefetch, int delayErrorMode) {
            Queue<Object> q;
            this.actual = actual;
            this.mapper = mapper;
            this.delayErrorMode = delayErrorMode;
            if (UnsafeAccess.isUnsafeAvailable()) {
                q = new SpscArrayQueue(prefetch);
            } else {
                q = new SpscAtomicArrayQueue(prefetch);
            }
            this.queue = q;
            this.inner = new SerialSubscription();
            request((long) prefetch);
        }

        public void onNext(T t) {
            if (this.queue.offer(NotificationLite.next(t))) {
                drain();
                return;
            }
            unsubscribe();
            onError(new MissingBackpressureException());
        }

        public void onError(Throwable mainError) {
            if (ExceptionsUtils.addThrowable(this.error, mainError)) {
                this.done = true;
                if (this.delayErrorMode == 0) {
                    Throwable ex = ExceptionsUtils.terminate(this.error);
                    if (!ExceptionsUtils.isTerminated(ex)) {
                        this.actual.onError(ex);
                    }
                    this.inner.unsubscribe();
                    return;
                }
                drain();
                return;
            }
            pluginError(mainError);
        }

        public void onCompleted() {
            this.done = true;
            drain();
        }

        void requestMore(long n) {
            if (n > 0) {
                this.arbiter.request(n);
            } else if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
        }

        void innerNext(R value) {
            this.actual.onNext(value);
        }

        void innerError(Throwable innerError, long produced) {
            if (!ExceptionsUtils.addThrowable(this.error, innerError)) {
                pluginError(innerError);
            } else if (this.delayErrorMode == 0) {
                Throwable ex = ExceptionsUtils.terminate(this.error);
                if (!ExceptionsUtils.isTerminated(ex)) {
                    this.actual.onError(ex);
                }
                unsubscribe();
            } else {
                if (produced != 0) {
                    this.arbiter.produced(produced);
                }
                this.active = false;
                drain();
            }
        }

        void innerCompleted(long produced) {
            if (produced != 0) {
                this.arbiter.produced(produced);
            }
            this.active = false;
            drain();
        }

        void pluginError(Throwable e) {
            RxJavaHooks.onError(e);
        }

        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                int delayErrorMode = this.delayErrorMode;
                while (!this.actual.isUnsubscribed()) {
                    if (!this.active) {
                        Throwable ex;
                        if (delayErrorMode != 1 || this.error.get() == null) {
                            boolean mainDone = this.done;
                            Object v = this.queue.poll();
                            boolean empty = v == null;
                            if (mainDone && empty) {
                                ex = ExceptionsUtils.terminate(this.error);
                                if (ex == null) {
                                    this.actual.onCompleted();
                                    return;
                                } else if (!ExceptionsUtils.isTerminated(ex)) {
                                    this.actual.onError(ex);
                                    return;
                                } else {
                                    return;
                                }
                            } else if (!empty) {
                                try {
                                    Observable<? extends R> source = (Observable) this.mapper.call(NotificationLite.getValue(v));
                                    if (source == null) {
                                        drainError(new NullPointerException("The source returned by the mapper was null"));
                                        return;
                                    } else if (source != Observable.empty()) {
                                        if (source instanceof ScalarSynchronousObservable) {
                                            ScalarSynchronousObservable<? extends R> scalarSource = (ScalarSynchronousObservable) source;
                                            this.active = true;
                                            this.arbiter.setProducer(new ConcatMapInnerScalarProducer(scalarSource.get(), this));
                                        } else {
                                            ConcatMapInnerSubscriber<T, R> innerSubscriber = new ConcatMapInnerSubscriber(this);
                                            this.inner.set(innerSubscriber);
                                            if (!innerSubscriber.isUnsubscribed()) {
                                                this.active = true;
                                                source.unsafeSubscribe(innerSubscriber);
                                            } else {
                                                return;
                                            }
                                        }
                                        request(1);
                                    } else {
                                        request(1);
                                    }
                                } catch (Throwable mapperError) {
                                    Exceptions.throwIfFatal(mapperError);
                                    drainError(mapperError);
                                    return;
                                }
                            }
                        }
                        ex = ExceptionsUtils.terminate(this.error);
                        if (!ExceptionsUtils.isTerminated(ex)) {
                            this.actual.onError(ex);
                            return;
                        }
                        return;
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        void drainError(Throwable mapperError) {
            unsubscribe();
            if (ExceptionsUtils.addThrowable(this.error, mapperError)) {
                Throwable ex = ExceptionsUtils.terminate(this.error);
                if (!ExceptionsUtils.isTerminated(ex)) {
                    this.actual.onError(ex);
                    return;
                }
                return;
            }
            pluginError(mapperError);
        }
    }

    public OnSubscribeConcatMap(Observable<? extends T> source, Func1<? super T, ? extends Observable<? extends R>> mapper, int prefetch, int delayErrorMode) {
        this.source = source;
        this.mapper = mapper;
        this.prefetch = prefetch;
        this.delayErrorMode = delayErrorMode;
    }

    public void call(Subscriber<? super R> child) {
        Subscriber<? super R> s;
        if (this.delayErrorMode == 0) {
            s = new SerializedSubscriber(child);
        } else {
            s = child;
        }
        final ConcatMapSubscriber<T, R> parent = new ConcatMapSubscriber(s, this.mapper, this.prefetch, this.delayErrorMode);
        child.add(parent);
        child.add(parent.inner);
        child.setProducer(new Producer() {
            public void request(long n) {
                parent.requestMore(n);
            }
        });
        if (!child.isUnsubscribed()) {
            this.source.unsafeSubscribe(parent);
        }
    }
}
