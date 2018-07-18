package rx.internal.operators;

import rx.Observable$Operator;
import rx.Single.OnSubscribe;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.producers.SingleProducer;
import rx.plugins.RxJavaHooks;

public final class SingleLiftObservableOperator<T, R> implements OnSubscribe<R> {
    final Observable$Operator<? extends R, ? super T> lift;
    final OnSubscribe<T> source;

    static final class WrapSubscriberIntoSingle<T> extends SingleSubscriber<T> {
        final Subscriber<? super T> actual;

        WrapSubscriberIntoSingle(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        public void onSuccess(T value) {
            this.actual.setProducer(new SingleProducer(this.actual, value));
        }

        public void onError(Throwable error) {
            this.actual.onError(error);
        }
    }

    public void call(SingleSubscriber<? super R> t) {
        Subscriber<R> outputAsSubscriber = new WrapSingleIntoSubscriber(t);
        t.add(outputAsSubscriber);
        try {
            Subscriber<? super T> inputAsSubscriber = (Subscriber) RxJavaHooks.onSingleLift(this.lift).call(outputAsSubscriber);
            SingleSubscriber<? super T> input = wrap(inputAsSubscriber);
            inputAsSubscriber.onStart();
            this.source.call(input);
        } catch (Throwable ex) {
            Exceptions.throwOrReport(ex, (SingleSubscriber) t);
        }
    }

    public static <T> SingleSubscriber<T> wrap(Subscriber<T> subscriber) {
        WrapSubscriberIntoSingle<T> parent = new WrapSubscriberIntoSingle(subscriber);
        subscriber.add(parent);
        return parent;
    }
}
