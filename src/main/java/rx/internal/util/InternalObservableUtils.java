package rx.internal.util;

import java.util.List;
import rx.Notification;
import rx.Observable;
import rx.Observable$Operator;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.OperatorAny;

public enum InternalObservableUtils {
    ;
    
    public static final PlusOneFunc2 COUNTER = null;
    static final NotificationErrorExtractor ERROR_EXTRACTOR = null;
    public static final Action1<Throwable> ERROR_NOT_IMPLEMENTED = null;
    public static final Observable$Operator<Boolean, Object> IS_EMPTY = null;
    public static final PlusOneLongFunc2 LONG_COUNTER = null;
    public static final ObjectEqualsFunc2 OBJECT_EQUALS = null;
    static final ReturnsVoidFunc1 RETURNS_VOID = null;
    public static final ToArrayFunc1 TO_ARRAY = null;

    static final class ErrorNotImplementedAction implements Action1<Throwable> {
        ErrorNotImplementedAction() {
        }

        public void call(Throwable t) {
            throw new OnErrorNotImplementedException(t);
        }
    }

    static final class NotificationErrorExtractor implements Func1<Notification<?>, Throwable> {
        NotificationErrorExtractor() {
        }

        public Throwable call(Notification<?> t) {
            return t.getThrowable();
        }
    }

    static final class ObjectEqualsFunc2 implements Func2<Object, Object, Boolean> {
        ObjectEqualsFunc2() {
        }

        public Boolean call(Object first, Object second) {
            boolean z = first == second || (first != null && first.equals(second));
            return Boolean.valueOf(z);
        }
    }

    static final class PlusOneFunc2 implements Func2<Integer, Object, Integer> {
        PlusOneFunc2() {
        }

        public Integer call(Integer count, Object o) {
            return Integer.valueOf(count.intValue() + 1);
        }
    }

    static final class PlusOneLongFunc2 implements Func2<Long, Object, Long> {
        PlusOneLongFunc2() {
        }

        public Long call(Long count, Object o) {
            return Long.valueOf(count.longValue() + 1);
        }
    }

    static final class ReturnsVoidFunc1 implements Func1<Object, Void> {
        ReturnsVoidFunc1() {
        }

        public Void call(Object t) {
            return null;
        }
    }

    static final class ToArrayFunc1 implements Func1<List<? extends Observable<?>>, Observable<?>[]> {
        ToArrayFunc1() {
        }

        public Observable<?>[] call(List<? extends Observable<?>> o) {
            return (Observable[]) o.toArray(new Observable[o.size()]);
        }
    }

    static {
        LONG_COUNTER = new PlusOneLongFunc2();
        OBJECT_EQUALS = new ObjectEqualsFunc2();
        TO_ARRAY = new ToArrayFunc1();
        RETURNS_VOID = new ReturnsVoidFunc1();
        COUNTER = new PlusOneFunc2();
        ERROR_EXTRACTOR = new NotificationErrorExtractor();
        ERROR_NOT_IMPLEMENTED = new ErrorNotImplementedAction();
        IS_EMPTY = new OperatorAny(UtilityFunctions.alwaysTrue(), true);
    }
}
