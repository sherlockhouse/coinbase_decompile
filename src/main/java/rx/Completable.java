package rx;

import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.Subscriptions;

public class Completable {
    static final Completable COMPLETE = new Completable(new OnSubscribe() {
        public void call(CompletableSubscriber s) {
            s.onSubscribe(Subscriptions.unsubscribed());
            s.onCompleted();
        }
    }, false);
    static final Completable NEVER = new Completable(new OnSubscribe() {
        public void call(CompletableSubscriber s) {
            s.onSubscribe(Subscriptions.unsubscribed());
        }
    }, false);
    private final OnSubscribe onSubscribe;

    public interface OnSubscribe extends Action1<CompletableSubscriber> {
    }

    public interface Operator extends Func1<CompletableSubscriber, CompletableSubscriber> {
    }

    public static Completable create(OnSubscribe onSubscribe) {
        requireNonNull(onSubscribe);
        try {
            return new Completable(onSubscribe);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Throwable ex2) {
            RxJavaHooks.onError(ex2);
            NullPointerException toNpe = toNpe(ex2);
        }
    }

    static <T> T requireNonNull(T o) {
        if (o != null) {
            return o;
        }
        throw new NullPointerException();
    }

    static NullPointerException toNpe(Throwable ex) {
        NullPointerException npe = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        npe.initCause(ex);
        return npe;
    }

    protected Completable(OnSubscribe onSubscribe) {
        this.onSubscribe = RxJavaHooks.onCreate(onSubscribe);
    }

    protected Completable(OnSubscribe onSubscribe, boolean useHook) {
        if (useHook) {
            onSubscribe = RxJavaHooks.onCreate(onSubscribe);
        }
        this.onSubscribe = onSubscribe;
    }

    public final void unsafeSubscribe(CompletableSubscriber s) {
        requireNonNull(s);
        try {
            RxJavaHooks.onCompletableStart(this, this.onSubscribe).call(s);
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Throwable ex2) {
            Exceptions.throwIfFatal(ex2);
            Throwable ex22 = RxJavaHooks.onCompletableError(ex22);
            RxJavaHooks.onError(ex22);
            NullPointerException toNpe = toNpe(ex22);
        }
    }

    public final Completable subscribeOn(final Scheduler scheduler) {
        requireNonNull(scheduler);
        return create(new OnSubscribe() {
            public void call(final CompletableSubscriber s) {
                final Scheduler$Worker w = scheduler.createWorker();
                w.schedule(new Action0() {
                    public void call() {
                        try {
                            Completable.this.unsafeSubscribe(s);
                        } finally {
                            w.unsubscribe();
                        }
                    }
                });
            }
        });
    }
}
