package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.Scheduler$Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.SubscriptionList;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public final class EventLoopsScheduler extends Scheduler implements SchedulerLifecycle {
    static final int MAX_THREADS;
    static final FixedSchedulerPool NONE = new FixedSchedulerPool(null, 0);
    static final PoolWorker SHUTDOWN_WORKER = new PoolWorker(RxThreadFactory.NONE);
    final AtomicReference<FixedSchedulerPool> pool = new AtomicReference(NONE);
    final ThreadFactory threadFactory;

    static final class EventLoopWorker extends Scheduler$Worker {
        private final SubscriptionList both = new SubscriptionList(this.serial, this.timed);
        private final PoolWorker poolWorker;
        private final SubscriptionList serial = new SubscriptionList();
        private final CompositeSubscription timed = new CompositeSubscription();

        EventLoopWorker(PoolWorker poolWorker) {
            this.poolWorker = poolWorker;
        }

        public void unsubscribe() {
            this.both.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.both.isUnsubscribed();
        }

        public Subscription schedule(final Action0 action) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            return this.poolWorker.scheduleActual(new Action0() {
                public void call() {
                    if (!EventLoopWorker.this.isUnsubscribed()) {
                        action.call();
                    }
                }
            }, 0, null, this.serial);
        }

        public Subscription schedule(final Action0 action, long delayTime, TimeUnit unit) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            return this.poolWorker.scheduleActual(new Action0() {
                public void call() {
                    if (!EventLoopWorker.this.isUnsubscribed()) {
                        action.call();
                    }
                }
            }, delayTime, unit, this.timed);
        }
    }

    static final class FixedSchedulerPool {
        final int cores;
        final PoolWorker[] eventLoops;
        long n;

        FixedSchedulerPool(ThreadFactory threadFactory, int maxThreads) {
            this.cores = maxThreads;
            this.eventLoops = new PoolWorker[maxThreads];
            for (int i = 0; i < maxThreads; i++) {
                this.eventLoops[i] = new PoolWorker(threadFactory);
            }
        }

        public PoolWorker getEventLoop() {
            int c = this.cores;
            if (c == 0) {
                return EventLoopsScheduler.SHUTDOWN_WORKER;
            }
            PoolWorker[] poolWorkerArr = this.eventLoops;
            long j = this.n;
            this.n = 1 + j;
            return poolWorkerArr[(int) (j % ((long) c))];
        }

        public void shutdown() {
            for (PoolWorker w : this.eventLoops) {
                w.unsubscribe();
            }
        }
    }

    static final class PoolWorker extends NewThreadWorker {
        PoolWorker(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        int max;
        int maxThreads = Integer.getInteger("rx.scheduler.max-computation-threads", 0).intValue();
        int cpuCount = Runtime.getRuntime().availableProcessors();
        if (maxThreads <= 0 || maxThreads > cpuCount) {
            max = cpuCount;
        } else {
            max = maxThreads;
        }
        MAX_THREADS = max;
        SHUTDOWN_WORKER.unsubscribe();
    }

    public EventLoopsScheduler(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        start();
    }

    public Scheduler$Worker createWorker() {
        return new EventLoopWorker(((FixedSchedulerPool) this.pool.get()).getEventLoop());
    }

    public void start() {
        FixedSchedulerPool update = new FixedSchedulerPool(this.threadFactory, MAX_THREADS);
        if (!this.pool.compareAndSet(NONE, update)) {
            update.shutdown();
        }
    }

    public void shutdown() {
        FixedSchedulerPool curr;
        do {
            curr = (FixedSchedulerPool) this.pool.get();
            if (curr == NONE) {
                return;
            }
        } while (!this.pool.compareAndSet(curr, NONE));
        curr.shutdown();
    }

    public Subscription scheduleDirect(Action0 action) {
        return ((FixedSchedulerPool) this.pool.get()).getEventLoop().scheduleActual(action, -1, TimeUnit.NANOSECONDS);
    }
}
