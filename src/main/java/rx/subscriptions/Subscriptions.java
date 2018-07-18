package rx.subscriptions;

import rx.Subscription;
import rx.functions.Action0;

public final class Subscriptions {
    private static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();

    static final class Unsubscribed implements Subscription {
        Unsubscribed() {
        }

        public void unsubscribe() {
        }

        public boolean isUnsubscribed() {
            return true;
        }
    }

    public static Subscription unsubscribed() {
        return UNSUBSCRIBED;
    }

    public static Subscription create(Action0 unsubscribe) {
        return BooleanSubscription.create(unsubscribe);
    }
}
