package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Scheduler$Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.Subscriptions;

public final class ImmediateScheduler extends Scheduler {
    public static final ImmediateScheduler INSTANCE = new ImmediateScheduler();

    final class InnerImmediateScheduler extends Scheduler$Worker implements Subscription {
        final BooleanSubscription innerSubscription = new BooleanSubscription();

        InnerImmediateScheduler() {
        }

        public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
            return schedule(new SleepingAction(action, this, ImmediateScheduler.this.now() + unit.toMillis(delayTime)));
        }

        public Subscription schedule(Action0 action) {
            action.call();
            return Subscriptions.unsubscribed();
        }

        public void unsubscribe() {
            this.innerSubscription.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.innerSubscription.isUnsubscribed();
        }
    }

    private ImmediateScheduler() {
    }

    public Scheduler$Worker createWorker() {
        return new InnerImmediateScheduler();
    }
}
