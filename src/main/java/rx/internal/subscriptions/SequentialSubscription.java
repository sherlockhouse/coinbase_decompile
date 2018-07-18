package rx.internal.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;

public final class SequentialSubscription extends AtomicReference<Subscription> implements Subscription {
    public SequentialSubscription(Subscription initial) {
        lazySet(initial);
    }

    public boolean update(Subscription next) {
        Subscription current;
        do {
            current = (Subscription) get();
            if (current == Unsubscribed.INSTANCE) {
                if (next != null) {
                    next.unsubscribe();
                }
                return false;
            }
        } while (!compareAndSet(current, next));
        if (current != null) {
            current.unsubscribe();
        }
        return true;
    }

    public boolean replace(Subscription next) {
        Subscription current;
        do {
            current = (Subscription) get();
            if (current == Unsubscribed.INSTANCE) {
                if (next != null) {
                    next.unsubscribe();
                }
                return false;
            }
        } while (!compareAndSet(current, next));
        return true;
    }

    public void unsubscribe() {
        if (((Subscription) get()) != Unsubscribed.INSTANCE) {
            Subscription current = (Subscription) getAndSet(Unsubscribed.INSTANCE);
            if (current != null && current != Unsubscribed.INSTANCE) {
                current.unsubscribe();
            }
        }
    }

    public boolean isUnsubscribed() {
        return get() == Unsubscribed.INSTANCE;
    }
}
