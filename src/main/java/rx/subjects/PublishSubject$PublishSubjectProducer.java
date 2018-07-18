package rx.subjects;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.BackpressureUtils;

final class PublishSubject$PublishSubjectProducer<T> extends AtomicLong implements Observer<T>, Producer, Subscription {
    final Subscriber<? super T> actual;
    final PublishSubject$PublishSubjectState<T> parent;
    long produced;

    public PublishSubject$PublishSubjectProducer(PublishSubject$PublishSubjectState<T> parent, Subscriber<? super T> actual) {
        this.parent = parent;
        this.actual = actual;
    }

    public void request(long n) {
        if (BackpressureUtils.validate(n)) {
            long r;
            do {
                r = get();
                if (r == Long.MIN_VALUE) {
                    return;
                }
            } while (!compareAndSet(r, BackpressureUtils.addCap(r, n)));
        }
    }

    public boolean isUnsubscribed() {
        return get() == Long.MIN_VALUE;
    }

    public void unsubscribe() {
        if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
            this.parent.remove(this);
        }
    }

    public void onNext(T t) {
        long r = get();
        if (r != Long.MIN_VALUE) {
            long p = this.produced;
            if (r != p) {
                this.produced = 1 + p;
                this.actual.onNext(t);
                return;
            }
            unsubscribe();
            this.actual.onError(new MissingBackpressureException("PublishSubject: could not emit value due to lack of requests"));
        }
    }

    public void onError(Throwable e) {
        if (get() != Long.MIN_VALUE) {
            this.actual.onError(e);
        }
    }

    public void onCompleted() {
        if (get() != Long.MIN_VALUE) {
            this.actual.onCompleted();
        }
    }
}
