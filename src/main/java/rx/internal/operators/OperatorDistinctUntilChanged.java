package rx.internal.operators;

import rx.Observable$Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.util.UtilityFunctions;

public final class OperatorDistinctUntilChanged<T, U> implements Observable$Operator<T, T>, Func2<U, U, Boolean> {
    final Func2<? super U, ? super U, Boolean> comparator = this;
    final Func1<? super T, ? extends U> keySelector;

    static final class Holder {
        static final OperatorDistinctUntilChanged<?, ?> INSTANCE = new OperatorDistinctUntilChanged(UtilityFunctions.identity());
    }

    public static <T> OperatorDistinctUntilChanged<T, T> instance() {
        return Holder.INSTANCE;
    }

    public OperatorDistinctUntilChanged(Func1<? super T, ? extends U> keySelector) {
        this.keySelector = keySelector;
    }

    public Boolean call(U t1, U t2) {
        boolean z = t1 == t2 || (t1 != null && t1.equals(t2));
        return Boolean.valueOf(z);
    }

    public Subscriber<? super T> call(final Subscriber<? super T> child) {
        return new Subscriber<T>(child) {
            boolean hasPrevious;
            U previousKey;

            public void onNext(T t) {
                try {
                    U key = OperatorDistinctUntilChanged.this.keySelector.call(t);
                    U currentKey = this.previousKey;
                    this.previousKey = key;
                    if (this.hasPrevious) {
                        try {
                            if (((Boolean) OperatorDistinctUntilChanged.this.comparator.call(currentKey, key)).booleanValue()) {
                                request(1);
                                return;
                            } else {
                                child.onNext(t);
                                return;
                            }
                        } catch (Throwable e) {
                            Exceptions.throwOrReport(e, child, key);
                            return;
                        }
                    }
                    this.hasPrevious = true;
                    child.onNext(t);
                } catch (Throwable e2) {
                    Exceptions.throwOrReport(e2, child, t);
                }
            }

            public void onError(Throwable e) {
                child.onError(e);
            }

            public void onCompleted() {
                child.onCompleted();
            }
        };
    }
}
