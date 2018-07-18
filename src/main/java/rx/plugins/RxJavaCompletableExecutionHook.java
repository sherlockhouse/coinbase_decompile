package rx.plugins;

import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.Completable.Operator;

public abstract class RxJavaCompletableExecutionHook {
    @Deprecated
    public OnSubscribe onCreate(OnSubscribe f) {
        return f;
    }

    @Deprecated
    public OnSubscribe onSubscribeStart(Completable completableInstance, OnSubscribe onSubscribe) {
        return onSubscribe;
    }

    @Deprecated
    public Throwable onSubscribeError(Throwable e) {
        return e;
    }

    @Deprecated
    public Operator onLift(Operator lift) {
        return lift;
    }
}
