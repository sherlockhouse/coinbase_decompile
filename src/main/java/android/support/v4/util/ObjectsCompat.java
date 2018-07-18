package android.support.v4.util;

import android.os.Build.VERSION;
import java.util.Objects;

public class ObjectsCompat {
    private static final ImplBase IMPL;

    private static class ImplBase {
        private ImplBase() {
        }

        public boolean equals(Object a, Object b) {
            return a == b || (a != null && a.equals(b));
        }
    }

    private static class ImplApi19 extends ImplBase {
        private ImplApi19() {
            super();
        }

        public boolean equals(Object a, Object b) {
            return Objects.equals(a, b);
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            IMPL = new ImplApi19();
        } else {
            IMPL = new ImplBase();
        }
    }

    public static boolean equals(Object a, Object b) {
        return IMPL.equals(a, b);
    }
}
