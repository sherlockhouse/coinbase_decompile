package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable$Operator;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;

public final class OperatorOnBackpressureLatest<T> implements Observable$Operator<T, T> {

    static final class Holder {
        static final OperatorOnBackpressureLatest<Object> INSTANCE = new OperatorOnBackpressureLatest();
    }

    static final class LatestEmitter<T> extends AtomicLong implements Observer<T>, Producer, Subscription {
        static final Object EMPTY = new Object();
        final Subscriber<? super T> child;
        volatile boolean done;
        boolean emitting;
        boolean missed;
        LatestSubscriber<? super T> parent;
        Throwable terminal;
        final AtomicReference<Object> value = new AtomicReference(EMPTY);

        public LatestEmitter(Subscriber<? super T> child) {
            this.child = child;
            lazySet(-4611686018427387904L);
        }

        public void request(long n) {
            if (n >= 0) {
                long r;
                long u;
                do {
                    r = get();
                    if (r != Long.MIN_VALUE) {
                        if (r == -4611686018427387904L) {
                            u = n;
                        } else {
                            u = r + n;
                            if (u < 0) {
                                u = Long.MAX_VALUE;
                            }
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(r, u));
                if (r == -4611686018427387904L) {
                    this.parent.requestMore(Long.MAX_VALUE);
                }
                emit();
            }
        }

        long produced(long n) {
            long u;
            long r;
            do {
                r = get();
                if (r < 0) {
                    return r;
                }
                u = r - n;
            } while (!compareAndSet(r, u));
            return u;
        }

        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        public void unsubscribe() {
            if (get() >= 0) {
                getAndSet(Long.MIN_VALUE);
            }
        }

        public void onNext(T t) {
            this.value.lazySet(t);
            emit();
        }

        public void onError(Throwable e) {
            this.terminal = e;
            this.done = true;
            emit();
        }

        public void onCompleted() {
            this.done = true;
            emit();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                this.missed = false;
            }
        }
    }

    static final class LatestSubscriber<T> extends Subscriber<T> {
        private final LatestEmitter<T> producer;

        LatestSubscriber(LatestEmitter<T> producer) {
            this.producer = producer;
        }

        public void onStart() {
            request(0);
        }

        public void onNext(T t) {
            this.producer.onNext(t);
        }

        public void onError(Throwable e) {
            this.producer.onError(e);
        }

        public void onCompleted() {
            this.producer.onCompleted();
        }

        void requestMore(long n) {
            request(n);
        }
    }

    public static <T> OperatorOnBackpressureLatest<T> instance() {
        return Holder.INSTANCE;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        LatestEmitter<T> producer = new LatestEmitter(child);
        LatestSubscriber<T> parent = new LatestSubscriber(producer);
        producer.parent = parent;
        child.add(parent);
        child.add(producer);
        child.setProducer(producer);
        return parent;
    }
}
