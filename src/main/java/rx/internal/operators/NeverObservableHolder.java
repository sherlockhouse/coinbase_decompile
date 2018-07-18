package rx.internal.operators;

import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Subscriber;

public enum NeverObservableHolder implements Observable$OnSubscribe<Object> {
    INSTANCE;
    
    static final Observable<Object> NEVER = null;

    static {
        NEVER = Observable.create(INSTANCE);
    }

    public static <T> Observable<T> instance() {
        return NEVER;
    }

    public void call(Subscriber<? super Object> subscriber) {
    }
}
