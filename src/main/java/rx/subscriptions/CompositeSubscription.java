package rx.subscriptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rx.Subscription;
import rx.exceptions.Exceptions;

public final class CompositeSubscription implements Subscription {
    private Set<Subscription> subscriptions;
    private volatile boolean unsubscribed;

    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    public void add(Subscription s) {
        if (!s.isUnsubscribed()) {
            if (!this.unsubscribed) {
                synchronized (this) {
                    if (!this.unsubscribed) {
                        if (this.subscriptions == null) {
                            this.subscriptions = new HashSet(4);
                        }
                        this.subscriptions.add(s);
                        return;
                    }
                }
            }
            s.unsubscribe();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void remove(Subscription s) {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (this.unsubscribed || this.subscriptions == null) {
                } else {
                    boolean unsubscribe = this.subscriptions.remove(s);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clear() {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (this.unsubscribed || this.subscriptions == null) {
                } else {
                    Collection<Subscription> unsubscribe = this.subscriptions;
                    this.subscriptions = null;
                    unsubscribeFromAll(unsubscribe);
                }
            }
        }
    }

    public void unsubscribe() {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (this.unsubscribed) {
                    return;
                }
                this.unsubscribed = true;
                Collection<Subscription> unsubscribe = this.subscriptions;
                this.subscriptions = null;
                unsubscribeFromAll(unsubscribe);
            }
        }
    }

    private static void unsubscribeFromAll(Collection<Subscription> subscriptions) {
        if (subscriptions != null) {
            List<Throwable> es = null;
            for (Subscription s : subscriptions) {
                try {
                    s.unsubscribe();
                } catch (Throwable e) {
                    if (es == null) {
                        es = new ArrayList();
                    }
                    es.add(e);
                }
            }
            Exceptions.throwIfAny(es);
        }
    }
}
