package rx;

import java.util.concurrent.TimeUnit;
import rx.functions.Action0;
import rx.internal.schedulers.SchedulePeriodicHelper;

public abstract class Scheduler$Worker implements Subscription {
    public abstract Subscription schedule(Action0 action0);

    public abstract Subscription schedule(Action0 action0, long j, TimeUnit timeUnit);

    public Subscription schedulePeriodically(Action0 action, long initialDelay, long period, TimeUnit unit) {
        return SchedulePeriodicHelper.schedulePeriodically(this, action, initialDelay, period, unit, null);
    }

    public long now() {
        return System.currentTimeMillis();
    }
}
