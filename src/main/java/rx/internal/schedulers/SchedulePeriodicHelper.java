package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.Scheduler$Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.subscriptions.SequentialSubscription;

public final class SchedulePeriodicHelper {
    public static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    public interface NowNanoSupplier {
        long nowNanos();
    }

    public static Subscription schedulePeriodically(Scheduler$Worker worker, Action0 action, long initialDelay, long period, TimeUnit unit, NowNanoSupplier nowNanoSupplier) {
        final long periodInNanos = unit.toNanos(period);
        final long firstNowNanos = nowNanoSupplier != null ? nowNanoSupplier.nowNanos() : TimeUnit.MILLISECONDS.toNanos(worker.now());
        final long firstStartInNanos = firstNowNanos + unit.toNanos(initialDelay);
        SequentialSubscription first = new SequentialSubscription();
        final SequentialSubscription mas = new SequentialSubscription(first);
        final Action0 action0 = action;
        final NowNanoSupplier nowNanoSupplier2 = nowNanoSupplier;
        final Scheduler$Worker scheduler$Worker = worker;
        first.replace(worker.schedule(new Action0() {
            long count;
            long lastNowNanos = firstNowNanos;
            long startInNanos = firstStartInNanos;

            public void call() {
                action0.call();
                if (!mas.isUnsubscribed()) {
                    long nowNanos;
                    long nextTick;
                    if (nowNanoSupplier2 != null) {
                        nowNanos = nowNanoSupplier2.nowNanos();
                    } else {
                        nowNanos = TimeUnit.MILLISECONDS.toNanos(scheduler$Worker.now());
                    }
                    long j;
                    long j2;
                    if (SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS + nowNanos < this.lastNowNanos || nowNanos >= (this.lastNowNanos + periodInNanos) + SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS) {
                        nextTick = nowNanos + periodInNanos;
                        j = periodInNanos;
                        j2 = this.count + 1;
                        this.count = j2;
                        this.startInNanos = nextTick - (j * j2);
                    } else {
                        j = this.startInNanos;
                        j2 = this.count + 1;
                        this.count = j2;
                        nextTick = j + (j2 * periodInNanos);
                    }
                    this.lastNowNanos = nowNanos;
                    mas.replace(scheduler$Worker.schedule(this, nextTick - nowNanos, TimeUnit.NANOSECONDS));
                }
            }
        }, initialDelay, unit));
        return mas;
    }
}
