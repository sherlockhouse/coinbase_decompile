package rx.functions;

public final class Actions {
    private static final EmptyAction EMPTY_ACTION = new EmptyAction();

    static final class EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> implements Action0, Action1<T0> {
        EmptyAction() {
        }

        public void call() {
        }

        public void call(T0 t0) {
        }
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> empty() {
        return EMPTY_ACTION;
    }
}
