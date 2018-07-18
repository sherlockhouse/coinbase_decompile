package rx.internal.util;

import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;

public final class ActionObserver<T> implements Observer<T> {
    final Action0 onCompleted;
    final Action1<? super Throwable> onError;
    final Action1<? super T> onNext;

    public ActionObserver(Action1<? super T> onNext, Action1<? super Throwable> onError, Action0 onCompleted) {
        this.onNext = onNext;
        this.onError = onError;
        this.onCompleted = onCompleted;
    }

    public void onNext(T t) {
        this.onNext.call(t);
    }

    public void onError(Throwable e) {
        this.onError.call(e);
    }

    public void onCompleted() {
        this.onCompleted.call();
    }
}
