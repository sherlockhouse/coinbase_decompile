package rx.exceptions;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import rx.plugins.RxJavaPlugins;

public final class OnErrorThrowable extends RuntimeException {

    public static class OnNextValue extends RuntimeException {
        private final Object value;

        static final class Primitives {
            static final Set<Class<?>> INSTANCE = create();

            private static Set<Class<?>> create() {
                Set<Class<?>> set = new HashSet();
                set.add(Boolean.class);
                set.add(Character.class);
                set.add(Byte.class);
                set.add(Short.class);
                set.add(Integer.class);
                set.add(Long.class);
                set.add(Float.class);
                set.add(Double.class);
                return set;
            }
        }

        public OnNextValue(Object value) {
            Object obj;
            super("OnError while emitting onNext value: " + renderValue(value));
            if (value instanceof Serializable) {
                obj = value;
            } else {
                try {
                    obj = String.valueOf(value);
                } catch (Throwable ex) {
                    obj = ex.getMessage();
                }
            }
            this.value = obj;
        }

        public Object getValue() {
            return this.value;
        }

        static String renderValue(Object value) {
            if (value == null) {
                return "null";
            }
            if (Primitives.INSTANCE.contains(value.getClass())) {
                return value.toString();
            }
            if (value instanceof String) {
                return (String) value;
            }
            if (value instanceof Enum) {
                return ((Enum) value).name();
            }
            String pluggedRendering = RxJavaPlugins.getInstance().getErrorHandler().handleOnNextValueRendering(value);
            if (pluggedRendering != null) {
                return pluggedRendering;
            }
            return value.getClass().getName() + ".class";
        }
    }

    public static Throwable addValueAsLastCause(Throwable e, Object value) {
        if (e == null) {
            e = new NullPointerException();
        }
        Throwable lastCause = Exceptions.getFinalCause(e);
        if (!((lastCause instanceof OnNextValue) && ((OnNextValue) lastCause).getValue() == value)) {
            Exceptions.addCause(e, new OnNextValue(value));
        }
        return e;
    }
}
