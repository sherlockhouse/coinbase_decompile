package rx.internal.operators;

import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;

public final class OnSubscribeFilter<T> implements Observable$OnSubscribe<T> {
    final Func1<? super T, Boolean> predicate;
    final Observable<T> source;

    static final class FilterSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        boolean done;
        final Func1<? super T, Boolean> predicate;

        public FilterSubscriber(Subscriber<? super T> actual, Func1<? super T, Boolean> predicate) {
            this.actual = actual;
            this.predicate = predicate;
            request(0);
        }

        public void onNext(T t) {
            try {
                if (((Boolean) this.predicate.call(t)).booleanValue()) {
                    this.actual.onNext(t);
                } else {
                    request(1);
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(ex, t));
            }
        }

        public void onError(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
                return;
            }
            this.done = true;
            this.actual.onError(e);
        }

        public void onCompleted() {
            if (!this.done) {
                this.actual.onCompleted();
            }
        }

        public void setProducer(Producer p) {
            super.setProducer(p);
            this.actual.setProducer(p);
        }
    }

    public OnSubscribeFilter(Observable<T> source, Func1<? super T, Boolean> predicate) {
        this.source = source;
        this.predicate = predicate;
    }

    public void call(Subscriber<? super T> child) {
        FilterSubscriber<T> parent = new FilterSubscriber(child, this.predicate);
        child.add(parent);
        this.source.unsafeSubscribe(parent);
    }
}
