package rx.observers;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.internal.operators.NotificationLite;

public class SerializedObserver<T> implements Observer<T> {
    private final Observer<? super T> actual;
    private boolean emitting;
    private FastList queue;
    private volatile boolean terminated;

    static final class FastList {
        Object[] array;
        int size;

        FastList() {
        }

        public void add(Object o) {
            int s = this.size;
            Object[] a = this.array;
            if (a == null) {
                a = new Object[16];
                this.array = a;
            } else if (s == a.length) {
                Object[] array2 = new Object[((s >> 2) + s)];
                System.arraycopy(a, 0, array2, 0, s);
                a = array2;
                this.array = a;
            }
            a[s] = o;
            this.size = s + 1;
        }
    }

    public SerializedObserver(Observer<? super T> s) {
        this.actual = s;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onNext(T t) {
        if (!this.terminated) {
            synchronized (this) {
                if (this.terminated) {
                } else if (this.emitting) {
                    FastList list = this.queue;
                    if (list == null) {
                        list = new FastList();
                        this.queue = list;
                    }
                    list.add(NotificationLite.next(t));
                } else {
                    this.emitting = true;
                }
            }
        }
    }

    public void onError(Throwable e) {
        Exceptions.throwIfFatal(e);
        if (!this.terminated) {
            synchronized (this) {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                if (this.emitting) {
                    FastList list = this.queue;
                    if (list == null) {
                        list = new FastList();
                        this.queue = list;
                    }
                    list.add(NotificationLite.error(e));
                    return;
                }
                this.emitting = true;
                this.actual.onError(e);
            }
        }
    }

    public void onCompleted() {
        if (!this.terminated) {
            synchronized (this) {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                if (this.emitting) {
                    FastList list = this.queue;
                    if (list == null) {
                        list = new FastList();
                        this.queue = list;
                    }
                    list.add(NotificationLite.completed());
                    return;
                }
                this.emitting = true;
                this.actual.onCompleted();
            }
        }
    }
}
