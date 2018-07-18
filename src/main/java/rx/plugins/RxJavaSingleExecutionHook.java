package rx.plugins;

import rx.Observable;
import rx.Observable.Operator;
import rx.Single;
import rx.Single.OnSubscribe;
import rx.Subscription;

public abstract class RxJavaSingleExecutionHook {
    @Deprecated
    public <T> OnSubscribe<T> onCreate(OnSubscribe<T> f) {
        return f;
    }

    @Deprecated
    public <T> Observable.OnSubscribe<T> onSubscribeStart(Single<? extends T> single, Observable.OnSubscribe<T> onSubscribe) {
        return onSubscribe;
    }

    @Deprecated
    public <T> Subscription onSubscribeReturn(Subscription subscription) {
        return subscription;
    }

    @Deprecated
    public <T> Throwable onSubscribeError(Throwable e) {
        return e;
    }

    @Deprecated
    public <T, R> Operator<? extends R, ? super T> onLift(Operator<? extends R, ? super T> lift) {
        return lift;
    }
}
