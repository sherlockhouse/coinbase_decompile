package rx.internal.util;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Producer;
import rx.Scheduler;
import rx.Scheduler$Worker;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.producers.SingleProducer;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.observers.Subscribers;
import rx.plugins.RxJavaHooks;

public final class ScalarSynchronousObservable<T> extends Observable<T> {
    static final boolean STRONG_MODE = Boolean.valueOf(System.getProperty("rx.just.strong-mode", "false")).booleanValue();
    final T t;

    static final class JustOnSubscribe<T> implements Observable$OnSubscribe<T> {
        final T value;

        JustOnSubscribe(T value) {
            this.value = value;
        }

        public void call(Subscriber<? super T> s) {
            s.setProducer(ScalarSynchronousObservable.createProducer(s, this.value));
        }
    }

    static final class ScalarAsyncOnSubscribe<T> implements Observable$OnSubscribe<T> {
        final Func1<Action0, Subscription> onSchedule;
        final T value;

        ScalarAsyncOnSubscribe(T value, Func1<Action0, Subscription> onSchedule) {
            this.value = value;
            this.onSchedule = onSchedule;
        }

        public void call(Subscriber<? super T> s) {
            s.setProducer(new ScalarAsyncProducer(s, this.value, this.onSchedule));
        }
    }

    static final class ScalarAsyncProducer<T> extends AtomicBoolean implements Producer, Action0 {
        final Subscriber<? super T> actual;
        final Func1<Action0, Subscription> onSchedule;
        final T value;

        public ScalarAsyncProducer(Subscriber<? super T> actual, T value, Func1<Action0, Subscription> onSchedule) {
            this.actual = actual;
            this.value = value;
            this.onSchedule = onSchedule;
        }

        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            } else if (n != 0 && compareAndSet(false, true)) {
                this.actual.add((Subscription) this.onSchedule.call(this));
            }
        }

        public void call() {
            Subscriber<? super T> a = this.actual;
            if (!a.isUnsubscribed()) {
                T v = this.value;
                try {
                    a.onNext(v);
                    if (!a.isUnsubscribed()) {
                        a.onCompleted();
                    }
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, a, v);
                }
            }
        }

        public String toString() {
            return "ScalarAsyncProducer[" + this.value + ", " + get() + "]";
        }
    }

    static final class WeakSingleProducer<T> implements Producer {
        final Subscriber<? super T> actual;
        boolean once;
        final T value;

        public WeakSingleProducer(Subscriber<? super T> actual, T value) {
            this.actual = actual;
            this.value = value;
        }

        public void request(long n) {
            if (!this.once) {
                if (n < 0) {
                    throw new IllegalStateException("n >= required but it was " + n);
                } else if (n != 0) {
                    this.once = true;
                    Subscriber<? super T> a = this.actual;
                    if (!a.isUnsubscribed()) {
                        T v = this.value;
                        try {
                            a.onNext(v);
                            if (!a.isUnsubscribed()) {
                                a.onCompleted();
                            }
                        } catch (Throwable e) {
                            Exceptions.throwOrReport(e, a, v);
                        }
                    }
                }
            }
        }
    }

    static <T> Producer createProducer(Subscriber<? super T> s, T v) {
        if (STRONG_MODE) {
            return new SingleProducer(s, v);
        }
        return new WeakSingleProducer(s, v);
    }

    public static <T> ScalarSynchronousObservable<T> create(T t) {
        return new ScalarSynchronousObservable(t);
    }

    protected ScalarSynchronousObservable(T t) {
        super(RxJavaHooks.onCreate(new JustOnSubscribe(t)));
        this.t = t;
    }

    public T get() {
        return this.t;
    }

    public Observable<T> scalarScheduleOn(final Scheduler scheduler) {
        Func1<Action0, Subscription> onSchedule;
        if (scheduler instanceof EventLoopsScheduler) {
            final EventLoopsScheduler els = (EventLoopsScheduler) scheduler;
            onSchedule = new Func1<Action0, Subscription>() {
                public Subscription call(Action0 a) {
                    return els.scheduleDirect(a);
                }
            };
        } else {
            onSchedule = new Func1<Action0, Subscription>() {
                public Subscription call(final Action0 a) {
                    final Scheduler$Worker w = scheduler.createWorker();
                    w.schedule(new Action0() {
                        public void call() {
                            try {
                                a.call();
                            } finally {
                                w.unsubscribe();
                            }
                        }
                    });
                    return w;
                }
            };
        }
        return create(new ScalarAsyncOnSubscribe(this.t, onSchedule));
    }

    public <R> Observable<R> scalarFlatMap(final Func1<? super T, ? extends Observable<? extends R>> func) {
        return create(new Observable$OnSubscribe<R>() {
            public void call(Subscriber<? super R> child) {
                Observable<? extends R> o = (Observable) func.call(ScalarSynchronousObservable.this.t);
                if (o instanceof ScalarSynchronousObservable) {
                    child.setProducer(ScalarSynchronousObservable.createProducer(child, ((ScalarSynchronousObservable) o).t));
                } else {
                    o.unsafeSubscribe(Subscribers.wrap(child));
                }
            }
        });
    }
}
