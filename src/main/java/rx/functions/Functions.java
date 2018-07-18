package rx.functions;

public final class Functions {
    public static <T0, T1, R> FuncN<R> fromFunc(final Func2<? super T0, ? super T1, ? extends R> f) {
        return new FuncN<R>() {
            public R call(Object... args) {
                if (args.length == 2) {
                    return f.call(args[0], args[1]);
                }
                throw new IllegalArgumentException("Func2 expecting 2 arguments.");
            }
        };
    }

    public static <T0, T1, T2, R> FuncN<R> fromFunc(final Func3<? super T0, ? super T1, ? super T2, ? extends R> f) {
        return new FuncN<R>() {
            public R call(Object... args) {
                if (args.length == 3) {
                    return f.call(args[0], args[1], args[2]);
                }
                throw new IllegalArgumentException("Func3 expecting 3 arguments.");
            }
        };
    }

    public static <T0, T1, T2, T3, R> FuncN<R> fromFunc(final Func4<? super T0, ? super T1, ? super T2, ? super T3, ? extends R> f) {
        return new FuncN<R>() {
            public R call(Object... args) {
                if (args.length == 4) {
                    return f.call(args[0], args[1], args[2], args[3]);
                }
                throw new IllegalArgumentException("Func4 expecting 4 arguments.");
            }
        };
    }
}
