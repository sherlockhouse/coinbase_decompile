package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable$OnSubscribe;
import rx.Single.OnSubscribe;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;

public final class SingleFromObservable<T> implements OnSubscribe<T> {
    final Observable$OnSubscribe<T> source;

    static final class WrapSingleIntoSubscriber<T> extends Subscriber<T> {
        final SingleSubscriber<? super T> actual;
        int state;
        T value;

        WrapSingleIntoSubscriber(SingleSubscriber<? super T> actual) {
            this.actual = actual;
        }

        public void onNext(T t) {
            int s = this.state;
            if (s == 0) {
                this.state = 1;
                this.value = t;
            } else if (s == 1) {
                this.state = 2;
                this.actual.onError(new IndexOutOfBoundsException("The upstream produced more than one value"));
            }
        }

        public void onError(Throwable e) {
            if (this.state == 2) {
                RxJavaHooks.onError(e);
                return;
            }
            this.value = null;
            this.actual.onError(e);
        }

        public void onCompleted() {
            int s = this.state;
            if (s == 0) {
                this.actual.onError(new NoSuchElementException());
            } else if (s == 1) {
                this.state = 2;
                T v = this.value;
                this.value = null;
                this.actual.onSuccess(v);
            }
        }
    }

    public SingleFromObservable(Observable$OnSubscribe<T> source) {
        this.source = source;
    }

    public void call(SingleSubscriber<? super T> t) {
        WrapSingleIntoSubscriber<T> parent = new WrapSingleIntoSubscriber(t);
        t.add(parent);
        this.source.call(parent);
    }
}
