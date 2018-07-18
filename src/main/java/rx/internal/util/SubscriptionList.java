package rx.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import rx.Subscription;
import rx.exceptions.Exceptions;

public final class SubscriptionList implements Subscription {
    private List<Subscription> subscriptions;
    private volatile boolean unsubscribed;

    public SubscriptionList(Subscription... subscriptions) {
        this.subscriptions = new LinkedList(Arrays.asList(subscriptions));
    }

    public SubscriptionList(Subscription s) {
        this.subscriptions = new LinkedList();
        this.subscriptions.add(s);
    }

    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    public void add(Subscription s) {
        if (!s.isUnsubscribed()) {
            if (!this.unsubscribed) {
                synchronized (this) {
                    if (!this.unsubscribed) {
                        List<Subscription> subs = this.subscriptions;
                        if (subs == null) {
                            subs = new LinkedList();
                            this.subscriptions = subs;
                        }
                        subs.add(s);
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
                List<Subscription> subs = this.subscriptions;
                if (this.unsubscribed || subs == null) {
                } else {
                    boolean unsubscribe = subs.remove(s);
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
                List<Subscription> list = this.subscriptions;
                this.subscriptions = null;
                unsubscribeFromAll(list);
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
