package rx;

import rx.functions.Action1;
import rx.plugins.RxJavaHooks;

public class Single<T> {
    final OnSubscribe<T> onSubscribe;

    public interface OnSubscribe<T> extends Action1<SingleSubscriber<? super T>> {
    }

    protected Single(OnSubscribe<T> f) {
        this.onSubscribe = RxJavaHooks.onCreate((OnSubscribe) f);
    }
}
