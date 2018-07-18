package rx.plugins;

import rx.Completable;
import rx.Completable.OnSubscribe;
import rx.Completable.Operator;
import rx.Observable;
import rx.Observable$OnSubscribe;
import rx.Observable$Operator;
import rx.Scheduler;
import rx.Single;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.SingleFromObservable;
import rx.internal.operators.SingleToObservable;

public final class RxJavaHooks {
    static volatile Func1<OnSubscribe, OnSubscribe> onCompletableCreate;
    static volatile Func1<Operator, Operator> onCompletableLift;
    static volatile Func2<Completable, OnSubscribe, OnSubscribe> onCompletableStart;
    static volatile Func1<Throwable, Throwable> onCompletableSubscribeError;
    static volatile Func1<Scheduler, Scheduler> onComputationScheduler;
    static volatile Action1<Throwable> onError;
    static volatile Func1<Scheduler, Scheduler> onIOScheduler;
    static volatile Func1<Observable$OnSubscribe, Observable$OnSubscribe> onObservableCreate;
    static volatile Func1<Observable$Operator, Observable$Operator> onObservableLift;
    static volatile Func1<Subscription, Subscription> onObservableReturn;
    static volatile Func2<Observable, Observable$OnSubscribe, Observable$OnSubscribe> onObservableStart;
    static volatile Func1<Throwable, Throwable> onObservableSubscribeError;
    static volatile Func1<Action0, Action0> onScheduleAction;
    static volatile Func1<Single.OnSubscribe, Single.OnSubscribe> onSingleCreate;
    static volatile Func1<Observable$Operator, Observable$Operator> onSingleLift;
    static volatile Func1<Subscription, Subscription> onSingleReturn;
    static volatile Func2<Single, Single.OnSubscribe, Single.OnSubscribe> onSingleStart;
    static volatile Func1<Throwable, Throwable> onSingleSubscribeError;

    static {
        init();
    }

    static void init() {
        onError = new Action1<Throwable>() {
            public void call(Throwable e) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            }
        };
        onObservableStart = new Func2<Observable, Observable$OnSubscribe, Observable$OnSubscribe>() {
            public Observable$OnSubscribe call(Observable t1, Observable$OnSubscribe t2) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeStart(t1, t2);
            }
        };
        onObservableReturn = new Func1<Subscription, Subscription>() {
            public Subscription call(Subscription f) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeReturn(f);
            }
        };
        onSingleStart = new Func2<Single, Single.OnSubscribe, Single.OnSubscribe>() {
            public Single.OnSubscribe call(Single t1, Single.OnSubscribe t2) {
                RxJavaSingleExecutionHook hook = RxJavaPlugins.getInstance().getSingleExecutionHook();
                return hook == RxJavaSingleExecutionHookDefault.getInstance() ? t2 : new SingleFromObservable(hook.onSubscribeStart(t1, new SingleToObservable(t2)));
            }
        };
        onSingleReturn = new Func1<Subscription, Subscription>() {
            public Subscription call(Subscription f) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeReturn(f);
            }
        };
        onCompletableStart = new Func2<Completable, OnSubscribe, OnSubscribe>() {
            public OnSubscribe call(Completable t1, OnSubscribe t2) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeStart(t1, t2);
            }
        };
        onScheduleAction = new Func1<Action0, Action0>() {
            public Action0 call(Action0 a) {
                return RxJavaPlugins.getInstance().getSchedulersHook().onSchedule(a);
            }
        };
        onObservableSubscribeError = new Func1<Throwable, Throwable>() {
            public Throwable call(Throwable t) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeError(t);
            }
        };
        onObservableLift = new Func1<Observable$Operator, Observable$Operator>() {
            public Observable$Operator call(Observable$Operator t) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onLift(t);
            }
        };
        onSingleSubscribeError = new Func1<Throwable, Throwable>() {
            public Throwable call(Throwable t) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeError(t);
            }
        };
        onSingleLift = new Func1<Observable$Operator, Observable$Operator>() {
            public Observable$Operator call(Observable$Operator t) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onLift(t);
            }
        };
        onCompletableSubscribeError = new Func1<Throwable, Throwable>() {
            public Throwable call(Throwable t) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeError(t);
            }
        };
        onCompletableLift = new Func1<Operator, Operator>() {
            public Operator call(Operator t) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onLift(t);
            }
        };
        initCreate();
    }

    static void initCreate() {
        onObservableCreate = new Func1<Observable$OnSubscribe, Observable$OnSubscribe>() {
            public Observable$OnSubscribe call(Observable$OnSubscribe f) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onCreate(f);
            }
        };
        onSingleCreate = new Func1<Single.OnSubscribe, Single.OnSubscribe>() {
            public Single.OnSubscribe call(Single.OnSubscribe f) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onCreate(f);
            }
        };
        onCompletableCreate = new Func1<OnSubscribe, OnSubscribe>() {
            public OnSubscribe call(OnSubscribe f) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onCreate(f);
            }
        };
    }

    public static void onError(Throwable ex) {
        Action1<Throwable> f = onError;
        if (f != null) {
            try {
                f.call(ex);
                return;
            } catch (Throwable pluginException) {
                System.err.println("The onError handler threw an Exception. It shouldn't. => " + pluginException.getMessage());
                pluginException.printStackTrace();
                signalUncaught(pluginException);
            }
        }
        signalUncaught(ex);
    }

    static void signalUncaught(Throwable ex) {
        Thread current = Thread.currentThread();
        current.getUncaughtExceptionHandler().uncaughtException(current, ex);
    }

    public static <T> Observable$OnSubscribe<T> onCreate(Observable$OnSubscribe<T> onSubscribe) {
        Func1<Observable$OnSubscribe, Observable$OnSubscribe> f = onObservableCreate;
        if (f != null) {
            return (Observable$OnSubscribe) f.call(onSubscribe);
        }
        return onSubscribe;
    }

    public static <T> Single.OnSubscribe<T> onCreate(Single.OnSubscribe<T> onSubscribe) {
        Func1<Single.OnSubscribe, Single.OnSubscribe> f = onSingleCreate;
        if (f != null) {
            return (Single.OnSubscribe) f.call(onSubscribe);
        }
        return onSubscribe;
    }

    public static OnSubscribe onCreate(OnSubscribe onSubscribe) {
        Func1<OnSubscribe, OnSubscribe> f = onCompletableCreate;
        if (f != null) {
            return (OnSubscribe) f.call(onSubscribe);
        }
        return onSubscribe;
    }

    public static Scheduler onComputationScheduler(Scheduler scheduler) {
        Func1<Scheduler, Scheduler> f = onComputationScheduler;
        if (f != null) {
            return (Scheduler) f.call(scheduler);
        }
        return scheduler;
    }

    public static Scheduler onIOScheduler(Scheduler scheduler) {
        Func1<Scheduler, Scheduler> f = onIOScheduler;
        if (f != null) {
            return (Scheduler) f.call(scheduler);
        }
        return scheduler;
    }

    public static Action0 onScheduledAction(Action0 action) {
        Func1<Action0, Action0> f = onScheduleAction;
        if (f != null) {
            return (Action0) f.call(action);
        }
        return action;
    }

    public static <T> Observable$OnSubscribe<T> onObservableStart(Observable<T> instance, Observable$OnSubscribe<T> onSubscribe) {
        Func2<Observable, Observable$OnSubscribe, Observable$OnSubscribe> f = onObservableStart;
        if (f != null) {
            return (Observable$OnSubscribe) f.call(instance, onSubscribe);
        }
        return onSubscribe;
    }

    public static Subscription onObservableReturn(Subscription subscription) {
        Func1<Subscription, Subscription> f = onObservableReturn;
        if (f != null) {
            return (Subscription) f.call(subscription);
        }
        return subscription;
    }

    public static Throwable onObservableError(Throwable error) {
        Func1<Throwable, Throwable> f = onObservableSubscribeError;
        if (f != null) {
            return (Throwable) f.call(error);
        }
        return error;
    }

    public static <T, R> Observable$Operator<R, T> onObservableLift(Observable$Operator<R, T> operator) {
        Func1<Observable$Operator, Observable$Operator> f = onObservableLift;
        if (f != null) {
            return (Observable$Operator) f.call(operator);
        }
        return operator;
    }

    public static <T, R> Observable$Operator<R, T> onSingleLift(Observable$Operator<R, T> operator) {
        Func1<Observable$Operator, Observable$Operator> f = onSingleLift;
        if (f != null) {
            return (Observable$Operator) f.call(operator);
        }
        return operator;
    }

    public static <T> OnSubscribe onCompletableStart(Completable instance, OnSubscribe onSubscribe) {
        Func2<Completable, OnSubscribe, OnSubscribe> f = onCompletableStart;
        if (f != null) {
            return (OnSubscribe) f.call(instance, onSubscribe);
        }
        return onSubscribe;
    }

    public static Throwable onCompletableError(Throwable error) {
        Func1<Throwable, Throwable> f = onCompletableSubscribeError;
        if (f != null) {
            return (Throwable) f.call(error);
        }
        return error;
    }
}
