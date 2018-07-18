package rx.internal.operators;

import rx.Observable$OnSubscribe;
import rx.Single.OnSubscribe;
import rx.Subscriber;

public final class SingleToObservable<T> implements Observable$OnSubscribe<T> {
    final OnSubscribe<T> source;

    public SingleToObservable(OnSubscribe<T> source) {
        this.source = source;
    }

    public void call(Subscriber<? super T> t) {
        WrapSubscriberIntoSingle<T> parent = new WrapSubscriberIntoSingle(t);
        t.add(parent);
        this.source.call(parent);
    }
}
