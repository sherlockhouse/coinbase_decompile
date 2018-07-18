package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable$Operator;
import rx.Subscriber;
import rx.internal.producers.SingleProducer;
import rx.plugins.RxJavaHooks;

public final class OperatorSingle<T> implements Observable$Operator<T, T> {
    private final T defaultValue;
    private final boolean hasDefaultValue;

    static final class Holder {
        static final OperatorSingle<?> INSTANCE = new OperatorSingle();
    }

    static final class ParentSubscriber<T> extends Subscriber<T> {
        private final Subscriber<? super T> child;
        private final T defaultValue;
        private final boolean hasDefaultValue;
        private boolean hasTooManyElements;
        private boolean isNonEmpty;
        private T value;

        ParentSubscriber(Subscriber<? super T> child, boolean hasDefaultValue, T defaultValue) {
            this.child = child;
            this.hasDefaultValue = hasDefaultValue;
            this.defaultValue = defaultValue;
            request(2);
        }

        public void onNext(T value) {
            if (!this.hasTooManyElements) {
                if (this.isNonEmpty) {
                    this.hasTooManyElements = true;
                    this.child.onError(new IllegalArgumentException("Sequence contains too many elements"));
                    unsubscribe();
                    return;
                }
                this.value = value;
                this.isNonEmpty = true;
            }
        }

        public void onCompleted() {
            if (!this.hasTooManyElements) {
                if (this.isNonEmpty) {
                    this.child.setProducer(new SingleProducer(this.child, this.value));
                } else if (this.hasDefaultValue) {
                    this.child.setProducer(new SingleProducer(this.child, this.defaultValue));
                } else {
                    this.child.onError(new NoSuchElementException("Sequence contains no elements"));
                }
            }
        }

        public void onError(Throwable e) {
            if (this.hasTooManyElements) {
                RxJavaHooks.onError(e);
            } else {
                this.child.onError(e);
            }
        }
    }

    public static <T> OperatorSingle<T> instance() {
        return Holder.INSTANCE;
    }

    OperatorSingle() {
        this(false, null);
    }

    private OperatorSingle(boolean hasDefaultValue, T defaultValue) {
        this.hasDefaultValue = hasDefaultValue;
        this.defaultValue = defaultValue;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        ParentSubscriber<T> parent = new ParentSubscriber(child, this.hasDefaultValue, this.defaultValue);
        child.add(parent);
        return parent;
    }
}
