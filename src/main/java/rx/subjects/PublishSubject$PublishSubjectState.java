package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable$OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.exceptions.Exceptions;

final class PublishSubject$PublishSubjectState<T> extends AtomicReference<PublishSubject$PublishSubjectProducer<T>[]> implements Observable$OnSubscribe<T>, Observer<T> {
    static final PublishSubject$PublishSubjectProducer[] EMPTY = new PublishSubject$PublishSubjectProducer[0];
    static final PublishSubject$PublishSubjectProducer[] TERMINATED = new PublishSubject$PublishSubjectProducer[0];
    Throwable error;

    public PublishSubject$PublishSubjectState() {
        lazySet(EMPTY);
    }

    public void call(Subscriber<? super T> t) {
        PublishSubject$PublishSubjectProducer<T> pp = new PublishSubject$PublishSubjectProducer(this, t);
        t.add(pp);
        t.setProducer(pp);
        if (!add(pp)) {
            Throwable ex = this.error;
            if (ex != null) {
                t.onError(ex);
            } else {
                t.onCompleted();
            }
        } else if (pp.isUnsubscribed()) {
            remove(pp);
        }
    }

    boolean add(PublishSubject$PublishSubjectProducer<T> inner) {
        PublishSubject$PublishSubjectProducer[] curr;
        PublishSubject$PublishSubjectProducer<T>[] next;
        do {
            curr = (PublishSubject$PublishSubjectProducer[]) get();
            if (curr == TERMINATED) {
                return false;
            }
            int n = curr.length;
            next = new PublishSubject$PublishSubjectProducer[(n + 1)];
            System.arraycopy(curr, 0, next, 0, n);
            next[n] = inner;
        } while (!compareAndSet(curr, next));
        return true;
    }

    void remove(PublishSubject$PublishSubjectProducer<T> inner) {
        PublishSubject$PublishSubjectProducer[] curr;
        PublishSubject$PublishSubjectProducer<T>[] next;
        do {
            curr = (PublishSubject$PublishSubjectProducer[]) get();
            if (curr != TERMINATED && curr != EMPTY) {
                int n = curr.length;
                int j = -1;
                for (int i = 0; i < n; i++) {
                    if (curr[i] == inner) {
                        j = i;
                        break;
                    }
                }
                if (j < 0) {
                    return;
                }
                if (n == 1) {
                    next = EMPTY;
                } else {
                    next = new PublishSubject$PublishSubjectProducer[(n - 1)];
                    System.arraycopy(curr, 0, next, 0, j);
                    System.arraycopy(curr, j + 1, next, j, (n - j) - 1);
                }
            } else {
                return;
            }
        } while (!compareAndSet(curr, next));
    }

    public void onNext(T t) {
        for (PublishSubject$PublishSubjectProducer<T> pp : (PublishSubject$PublishSubjectProducer[]) get()) {
            pp.onNext(t);
        }
    }

    public void onError(Throwable e) {
        this.error = e;
        List<Throwable> errors = null;
        for (PublishSubject$PublishSubjectProducer<T> pp : (PublishSubject$PublishSubjectProducer[]) getAndSet(TERMINATED)) {
            try {
                pp.onError(e);
            } catch (Throwable ex) {
                if (errors == null) {
                    errors = new ArrayList(1);
                }
                errors.add(ex);
            }
        }
        Exceptions.throwIfAny(errors);
    }

    public void onCompleted() {
        for (PublishSubject$PublishSubjectProducer<T> pp : (PublishSubject$PublishSubjectProducer[]) getAndSet(TERMINATED)) {
            pp.onCompleted();
        }
    }
}
