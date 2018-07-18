package rx.internal.util;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public final class ActionSubscriber<T> extends Subscriber<T> {
    final Action0 onCompleted;
    final Action1<Throwable> onError;
    final Action1<? super T> onNext;

    public ActionSubscriber(Action1<? super T> onNext, Action1<Throwable> onError, Action0 onCompleted) {
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
