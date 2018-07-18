package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable$OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;

public final class OnSubscribeFromIterable<T> implements Observable$OnSubscribe<T> {
    final Iterable<? extends T> is;

    static final class IterableProducer<T> extends AtomicLong implements Producer {
        private final Iterator<? extends T> it;
        private final Subscriber<? super T> o;

        IterableProducer(Subscriber<? super T> o, Iterator<? extends T> it) {
            this.o = o;
            this.it = it;
        }

        public void request(long n) {
            if (get() != Long.MAX_VALUE) {
                if (n == Long.MAX_VALUE && compareAndSet(0, Long.MAX_VALUE)) {
                    fastPath();
                } else if (n > 0 && BackpressureUtils.getAndAddRequest(this, n) == 0) {
                    slowPath(n);
                }
            }
        }

        void slowPath(long n) {
            Observer o = this.o;
            Iterator<? extends T> it = this.it;
            long r = n;
            long e = 0;
            while (true) {
                if (e == r) {
                    r = get();
                    if (e == r) {
                        r = BackpressureUtils.produced(this, e);
                        if (r != 0) {
                            e = 0;
                        } else {
                            return;
                        }
                    }
                    continue;
                } else if (!o.isUnsubscribed()) {
                    try {
                        o.onNext(it.next());
                        if (!o.isUnsubscribed()) {
                            try {
                                if (!it.hasNext()) {
                                    break;
                                }
                                e++;
                            } catch (Throwable ex) {
                                Exceptions.throwOrReport(ex, o);
                                return;
                            }
                        }
                        return;
                    } catch (Throwable ex2) {
                        Exceptions.throwOrReport(ex2, o);
                        return;
                    }
                } else {
                    return;
                }
            }
            if (!o.isUnsubscribed()) {
                o.onCompleted();
            }
        }

        void fastPath() {
            Observer o = this.o;
            Iterator<? extends T> it = this.it;
            while (!o.isUnsubscribed()) {
                try {
                    o.onNext(it.next());
                    if (!o.isUnsubscribed()) {
                        try {
                            if (!it.hasNext()) {
                                if (!o.isUnsubscribed()) {
                                    o.onCompleted();
                                    return;
                                }
                                return;
                            }
                        } catch (Throwable ex) {
                            Exceptions.throwOrReport(ex, o);
                            return;
                        }
                    }
                    return;
                } catch (Throwable ex2) {
                    Exceptions.throwOrReport(ex2, o);
                    return;
                }
            }
        }
    }

    public OnSubscribeFromIterable(Iterable<? extends T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("iterable must not be null");
        }
        this.is = iterable;
    }

    public void call(Subscriber<? super T> o) {
        try {
            Iterator<? extends T> it = this.is.iterator();
            boolean b = it.hasNext();
            if (!o.isUnsubscribed()) {
                if (b) {
                    o.setProducer(new IterableProducer(o, it));
                } else {
                    o.onCompleted();
                }
            }
        } catch (Throwable ex) {
            Exceptions.throwOrReport(ex, (Observer) o);
        }
    }
}
