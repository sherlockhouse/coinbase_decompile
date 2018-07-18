package rx.android.schedulers;

import android.os.Handler;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Scheduler$Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.schedulers.ScheduledAction;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public class HandlerThreadScheduler extends Scheduler {
    private final Handler handler;

    private static class InnerHandlerThreadScheduler extends Scheduler$Worker {
        private final CompositeSubscription compositeSubscription = new CompositeSubscription();
        private final Handler handler;

        public InnerHandlerThreadScheduler(Handler handler) {
            this.handler = handler;
        }

        public void unsubscribe() {
            this.compositeSubscription.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.compositeSubscription.isUnsubscribed();
        }

        public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
            final ScheduledAction scheduledAction = new ScheduledAction(action);
            scheduledAction.add(Subscriptions.create(new Action0() {
                public void call() {
                    InnerHandlerThreadScheduler.this.handler.removeCallbacks(scheduledAction);
                }
            }));
            scheduledAction.addParent(this.compositeSubscription);
            this.compositeSubscription.add(scheduledAction);
            this.handler.postDelayed(scheduledAction, unit.toMillis(delayTime));
            return scheduledAction;
        }

        public Subscription schedule(Action0 action) {
            return schedule(action, 0, TimeUnit.MILLISECONDS);
        }
    }

    public HandlerThreadScheduler(Handler handler) {
        this.handler = handler;
    }

    public Scheduler$Worker createWorker() {
        return new InnerHandlerThreadScheduler(this.handler);
    }
}
