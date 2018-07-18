package rx.internal.schedulers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler$Worker;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.util.PlatformDependent;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.SubscriptionList;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public class NewThreadWorker extends Scheduler$Worker implements Subscription {
    private static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> EXECUTORS = new ConcurrentHashMap();
    private static final AtomicReference<ScheduledExecutorService> PURGE = new AtomicReference();
    public static final int PURGE_FREQUENCY = Integer.getInteger("rx.scheduler.jdk6.purge-frequency-millis", 1000).intValue();
    private static final Object SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED = new Object();
    private static final boolean SHOULD_TRY_ENABLE_CANCEL_POLICY;
    private static volatile Object cachedSetRemoveOnCancelPolicyMethod;
    private final ScheduledExecutorService executor;
    volatile boolean isUnsubscribed;

    static {
        boolean purgeForce = Boolean.getBoolean("rx.scheduler.jdk6.purge-force");
        int androidApiVersion = PlatformDependent.getAndroidApiVersion();
        boolean z = !purgeForce && (androidApiVersion == 0 || androidApiVersion >= 21);
        SHOULD_TRY_ENABLE_CANCEL_POLICY = z;
    }

    public static void registerExecutor(ScheduledThreadPoolExecutor service) {
        while (((ScheduledExecutorService) PURGE.get()) == null) {
            ScheduledExecutorService exec = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge-"));
            if (PURGE.compareAndSet(null, exec)) {
                exec.scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        NewThreadWorker.purgeExecutors();
                    }
                }, (long) PURGE_FREQUENCY, (long) PURGE_FREQUENCY, TimeUnit.MILLISECONDS);
                break;
            }
            exec.shutdownNow();
        }
        EXECUTORS.putIfAbsent(service, service);
    }

    public static void deregisterExecutor(ScheduledExecutorService service) {
        EXECUTORS.remove(service);
    }

    static void purgeExecutors() {
        try {
            Iterator<ScheduledThreadPoolExecutor> it = EXECUTORS.keySet().iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor exec = (ScheduledThreadPoolExecutor) it.next();
                if (exec.isShutdown()) {
                    it.remove();
                } else {
                    exec.purge();
                }
            }
        } catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            RxJavaHooks.onError(t);
        }
    }

    public static boolean tryEnableCancelPolicy(ScheduledExecutorService executor) {
        if (SHOULD_TRY_ENABLE_CANCEL_POLICY) {
            Method methodToCall;
            if (executor instanceof ScheduledThreadPoolExecutor) {
                Object localSetRemoveOnCancelPolicyMethod = cachedSetRemoveOnCancelPolicyMethod;
                if (localSetRemoveOnCancelPolicyMethod == SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED) {
                    return false;
                }
                if (localSetRemoveOnCancelPolicyMethod == null) {
                    Method method = findSetRemoveOnCancelPolicyMethod(executor);
                    cachedSetRemoveOnCancelPolicyMethod = method != null ? method : SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED;
                    methodToCall = method;
                } else {
                    methodToCall = (Method) localSetRemoveOnCancelPolicyMethod;
                }
            } else {
                methodToCall = findSetRemoveOnCancelPolicyMethod(executor);
            }
            if (methodToCall != null) {
                try {
                    methodToCall.invoke(executor, new Object[]{Boolean.valueOf(true)});
                    return true;
                } catch (InvocationTargetException e) {
                    RxJavaHooks.onError(e);
                } catch (IllegalAccessException e2) {
                    RxJavaHooks.onError(e2);
                } catch (IllegalArgumentException e3) {
                    RxJavaHooks.onError(e3);
                }
            }
        }
        return false;
    }

    static Method findSetRemoveOnCancelPolicyMethod(ScheduledExecutorService executor) {
        for (Method method : executor.getClass().getMethods()) {
            if (method.getName().equals("setRemoveOnCancelPolicy")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == Boolean.TYPE) {
                    return method;
                }
            }
        }
        return null;
    }

    public NewThreadWorker(ThreadFactory threadFactory) {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1, threadFactory);
        if (!tryEnableCancelPolicy(exec) && (exec instanceof ScheduledThreadPoolExecutor)) {
            registerExecutor((ScheduledThreadPoolExecutor) exec);
        }
        this.executor = exec;
    }

    public Subscription schedule(Action0 action) {
        return schedule(action, 0, null);
    }

    public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
        if (this.isUnsubscribed) {
            return Subscriptions.unsubscribed();
        }
        return scheduleActual(action, delayTime, unit);
    }

    public ScheduledAction scheduleActual(Action0 action, long delayTime, TimeUnit unit) {
        Future f;
        ScheduledAction run = new ScheduledAction(RxJavaHooks.onScheduledAction(action));
        if (delayTime <= 0) {
            f = this.executor.submit(run);
        } else {
            f = this.executor.schedule(run, delayTime, unit);
        }
        run.add(f);
        return run;
    }

    public ScheduledAction scheduleActual(Action0 action, long delayTime, TimeUnit unit, CompositeSubscription parent) {
        Future f;
        ScheduledAction run = new ScheduledAction(RxJavaHooks.onScheduledAction(action), parent);
        parent.add(run);
        if (delayTime <= 0) {
            f = this.executor.submit(run);
        } else {
            f = this.executor.schedule(run, delayTime, unit);
        }
        run.add(f);
        return run;
    }

    public ScheduledAction scheduleActual(Action0 action, long delayTime, TimeUnit unit, SubscriptionList parent) {
        Future f;
        ScheduledAction run = new ScheduledAction(RxJavaHooks.onScheduledAction(action), parent);
        parent.add(run);
        if (delayTime <= 0) {
            f = this.executor.submit(run);
        } else {
            f = this.executor.schedule(run, delayTime, unit);
        }
        run.add(f);
        return run;
    }

    public void unsubscribe() {
        this.isUnsubscribed = true;
        this.executor.shutdownNow();
        deregisterExecutor(this.executor);
    }

    public boolean isUnsubscribed() {
        return this.isUnsubscribed;
    }
}
