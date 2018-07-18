package rx.internal.operators;

import java.io.Serializable;
import rx.Observer;

public final class NotificationLite {
    private static final Object ON_COMPLETED_SENTINEL = new Serializable() {
        public String toString() {
            return "Notification=>Completed";
        }
    };
    private static final Object ON_NEXT_NULL_SENTINEL = new Serializable() {
        public String toString() {
            return "Notification=>NULL";
        }
    };

    static final class OnErrorSentinel implements Serializable {
        final Throwable e;

        public OnErrorSentinel(Throwable e) {
            this.e = e;
        }

        public String toString() {
            return "Notification=>Error:" + this.e;
        }
    }

    public static <T> Object next(T t) {
        if (t == null) {
            return ON_NEXT_NULL_SENTINEL;
        }
        return t;
    }

    public static Object completed() {
        return ON_COMPLETED_SENTINEL;
    }

    public static Object error(Throwable e) {
        return new OnErrorSentinel(e);
    }

    public static <T> boolean accept(Observer<? super T> o, Object n) {
        if (n == ON_COMPLETED_SENTINEL) {
            o.onCompleted();
            return true;
        } else if (n == ON_NEXT_NULL_SENTINEL) {
            o.onNext(null);
            return false;
        } else if (n == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        } else if (n.getClass() == OnErrorSentinel.class) {
            o.onError(((OnErrorSentinel) n).e);
            return true;
        } else {
            o.onNext(n);
            return false;
        }
    }

    public static boolean isCompleted(Object n) {
        return n == ON_COMPLETED_SENTINEL;
    }

    public static boolean isError(Object n) {
        return n instanceof OnErrorSentinel;
    }

    public static boolean isNext(Object n) {
        return (n == null || isError(n) || isCompleted(n)) ? false : true;
    }

    public static <T> T getValue(Object n) {
        return n == ON_NEXT_NULL_SENTINEL ? null : n;
    }
}
