package rx.internal.util;

import rx.functions.Func1;

public final class UtilityFunctions {

    enum AlwaysTrue implements Func1<Object, Boolean> {
        INSTANCE;

        public Boolean call(Object o) {
            return Boolean.valueOf(true);
        }
    }

    enum Identity implements Func1<Object, Object> {
        INSTANCE;

        public Object call(Object o) {
            return o;
        }
    }

    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }

    public static <T> Func1<T, T> identity() {
        return Identity.INSTANCE;
    }
}
