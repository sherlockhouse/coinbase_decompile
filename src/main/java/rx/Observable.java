package rx;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorFailedException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.FuncN;
import rx.functions.Functions;
import rx.internal.operators.EmptyObservableHolder;
import rx.internal.operators.NeverObservableHolder;
import rx.internal.operators.OnSubscribeCombineLatest;
import rx.internal.operators.OnSubscribeConcatMap;
import rx.internal.operators.OnSubscribeDoOnEach;
import rx.internal.operators.OnSubscribeFilter;
import rx.internal.operators.OnSubscribeFromIterable;
import rx.internal.operators.OnSubscribeLift;
import rx.internal.operators.OnSubscribeMap;
import rx.internal.operators.OnSubscribeSingle;
import rx.internal.operators.OnSubscribeTimerOnce;
import rx.internal.operators.OnSubscribeTimerPeriodically;
import rx.internal.operators.OperatorAsObservable;
import rx.internal.operators.OperatorDelay;
import rx.internal.operators.OperatorDistinctUntilChanged;
import rx.internal.operators.OperatorDoOnSubscribe;
import rx.internal.operators.OperatorMerge;
import rx.internal.operators.OperatorObserveOn;
import rx.internal.operators.OperatorOnBackpressureLatest;
import rx.internal.operators.OperatorOnErrorResumeNextViaFunction;
import rx.internal.operators.OperatorSingle;
import rx.internal.operators.OperatorSubscribeOn;
import rx.internal.operators.OperatorTake;
import rx.internal.operators.OperatorTakeWhile;
import rx.internal.util.ActionObserver;
import rx.internal.util.ActionSubscriber;
import rx.internal.util.InternalObservableUtils;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.UtilityFunctions;
import rx.observers.SafeSubscriber;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

public class Observable<T> {
    final OnSubscribe<T> onSubscribe;

    protected Observable(OnSubscribe<T> f) {
        this.onSubscribe = f;
    }

    public static <T> Observable<T> create(OnSubscribe<T> f) {
        return new Observable(RxJavaHooks.onCreate(f));
    }

    public final <R> Observable<R> lift(Operator<? extends R, ? super T> operator) {
        return create(new OnSubscribeLift(this.onSubscribe, operator));
    }

    public Single<T> toSingle() {
        return new Single(OnSubscribeSingle.create(this));
    }

    public static <T1, T2, R> Observable<R> combineLatest(Observable<? extends T1> o1, Observable<? extends T2> o2, Func2<? super T1, ? super T2, ? extends R> combineFunction) {
        return combineLatest(Arrays.asList(new Observable[]{o1, o2}), Functions.fromFunc(combineFunction));
    }

    public static <T1, T2, T3, R> Observable<R> combineLatest(Observable<? extends T1> o1, Observable<? extends T2> o2, Observable<? extends T3> o3, Func3<? super T1, ? super T2, ? super T3, ? extends R> combineFunction) {
        return combineLatest(Arrays.asList(new Observable[]{o1, o2, o3}), Functions.fromFunc(combineFunction));
    }

    public static <T1, T2, T3, T4, R> Observable<R> combineLatest(Observable<? extends T1> o1, Observable<? extends T2> o2, Observable<? extends T3> o3, Observable<? extends T4> o4, Func4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> combineFunction) {
        return combineLatest(Arrays.asList(new Observable[]{o1, o2, o3, o4}), Functions.fromFunc(combineFunction));
    }

    public static <T, R> Observable<R> combineLatest(List<? extends Observable<? extends T>> sources, FuncN<? extends R> combineFunction) {
        return create(new OnSubscribeCombineLatest(sources, combineFunction));
    }

    public static <T> Observable<T> concat(Iterable<? extends Observable<? extends T>> sequences) {
        return concat(from(sequences));
    }

    public static <T> Observable<T> concat(Observable<? extends Observable<? extends T>> observables) {
        return observables.concatMap(UtilityFunctions.identity());
    }

    public static <T> Observable<T> empty() {
        return EmptyObservableHolder.instance();
    }

    public static <T> Observable<T> from(Iterable<? extends T> iterable) {
        return create(new OnSubscribeFromIterable(iterable));
    }

    public static Observable<Long> interval(long initialDelay, long period, TimeUnit unit) {
        return interval(initialDelay, period, unit, Schedulers.computation());
    }

    public static Observable<Long> interval(long initialDelay, long period, TimeUnit unit, Scheduler scheduler) {
        return create(new OnSubscribeTimerPeriodically(initialDelay, period, unit, scheduler));
    }

    public static <T> Observable<T> just(T value) {
        return ScalarSynchronousObservable.create(value);
    }

    public static <T> Observable<T> merge(Observable<? extends Observable<? extends T>> source) {
        if (source.getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable) source).scalarFlatMap(UtilityFunctions.identity());
        }
        return source.lift(OperatorMerge.instance(false));
    }

    public static <T> Observable<T> never() {
        return NeverObservableHolder.instance();
    }

    public static Observable<Long> timer(long delay, TimeUnit unit) {
        return timer(delay, unit, Schedulers.computation());
    }

    public static Observable<Long> timer(long delay, TimeUnit unit, Scheduler scheduler) {
        return create(new OnSubscribeTimerOnce(delay, unit, scheduler));
    }

    public final Observable<T> asObservable() {
        return lift(OperatorAsObservable.instance());
    }

    public final <R> Observable<R> concatMap(Func1<? super T, ? extends Observable<? extends R>> func) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).scalarFlatMap(func);
        }
        return create(new OnSubscribeConcatMap(this, func, 2, 0));
    }

    public final Observable<T> delay(long delay, TimeUnit unit) {
        return delay(delay, unit, Schedulers.computation());
    }

    public final Observable<T> delay(long delay, TimeUnit unit, Scheduler scheduler) {
        return lift(new OperatorDelay(delay, unit, scheduler));
    }

    public final Observable<T> distinctUntilChanged() {
        return lift(OperatorDistinctUntilChanged.instance());
    }

    public final Observable<T> doOnCompleted(Action0 onCompleted) {
        return create(new OnSubscribeDoOnEach(this, new ActionObserver(Actions.empty(), Actions.empty(), onCompleted)));
    }

    public final Observable<T> doOnError(Action1<? super Throwable> onError) {
        return create(new OnSubscribeDoOnEach(this, new ActionObserver(Actions.empty(), onError, Actions.empty())));
    }

    public final Observable<T> doOnSubscribe(Action0 subscribe) {
        return lift(new OperatorDoOnSubscribe(subscribe));
    }

    public final Observable<T> filter(Func1<? super T, Boolean> predicate) {
        return create(new OnSubscribeFilter(this, predicate));
    }

    public final Observable<T> first() {
        return take(1).single();
    }

    public final <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> func) {
        if (getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable) this).scalarFlatMap(func);
        }
        return merge(map(func));
    }

    public final <R> Observable<R> map(Func1<? super T, ? extends R> func) {
        return create(new OnSubscribeMap(this, func));
    }

    public final Observable<T> observeOn(Scheduler scheduler) {
        return observeOn(scheduler, RxRingBuffer.SIZE);
    }

    public final Observable<T> observeOn(Scheduler scheduler, int bufferSize) {
        return observeOn(scheduler, false, bufferSize);
    }

    public final Observable<T> observeOn(Scheduler scheduler, boolean delayError, int bufferSize) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).scalarScheduleOn(scheduler);
        }
        return lift(new OperatorObserveOn(scheduler, delayError, bufferSize));
    }

    public final Observable<T> onBackpressureLatest() {
        return lift(OperatorOnBackpressureLatest.instance());
    }

    public final Observable<T> onErrorReturn(Func1<? super Throwable, ? extends T> resumeFunction) {
        return lift(OperatorOnErrorResumeNextViaFunction.withSingle(resumeFunction));
    }

    public final Observable<T> single() {
        return lift(OperatorSingle.instance());
    }

    public final Subscription subscribe() {
        return subscribe(new ActionSubscriber(Actions.empty(), InternalObservableUtils.ERROR_NOT_IMPLEMENTED, Actions.empty()));
    }

    public final Subscription subscribe(Action1<? super T> onNext) {
        if (onNext != null) {
            return subscribe(new ActionSubscriber(onNext, InternalObservableUtils.ERROR_NOT_IMPLEMENTED, Actions.empty()));
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public final Subscription subscribe(Action1<? super T> onNext, Action1<Throwable> onError) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        } else if (onError != null) {
            return subscribe(new ActionSubscriber(onNext, onError, Actions.empty()));
        } else {
            throw new IllegalArgumentException("onError can not be null");
        }
    }

    public final Subscription subscribe(Action1<? super T> onNext, Action1<Throwable> onError, Action0 onCompleted) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        } else if (onError == null) {
            throw new IllegalArgumentException("onError can not be null");
        } else if (onCompleted != null) {
            return subscribe(new ActionSubscriber(onNext, onError, onCompleted));
        } else {
            throw new IllegalArgumentException("onComplete can not be null");
        }
    }

    public final Subscription unsafeSubscribe(Subscriber<? super T> subscriber) {
        try {
            subscriber.onStart();
            RxJavaHooks.onObservableStart(this, this.onSubscribe).call(subscriber);
            return RxJavaHooks.onObservableReturn(subscriber);
        } catch (Throwable e2) {
            Exceptions.throwIfFatal(e2);
            RxJavaHooks.onObservableError(new OnErrorFailedException("Error occurred attempting to subscribe [" + e.getMessage() + "] and then again while trying to pass to onError.", e2));
        }
    }

    public final Subscription subscribe(Subscriber<? super T> subscriber) {
        return subscribe((Subscriber) subscriber, this);
    }

    static <T> Subscription subscribe(Subscriber<? super T> subscriber, Observable<T> observable) {
        if (subscriber == null) {
            throw new IllegalArgumentException("subscriber can not be null");
        } else if (observable.onSubscribe == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        } else {
            subscriber.onStart();
            if (!(subscriber instanceof SafeSubscriber)) {
                subscriber = new SafeSubscriber(subscriber);
            }
            try {
                RxJavaHooks.onObservableStart(observable, observable.onSubscribe).call(subscriber);
                return RxJavaHooks.onObservableReturn(subscriber);
            } catch (Throwable e2) {
                Exceptions.throwIfFatal(e2);
                RxJavaHooks.onObservableError(new OnErrorFailedException("Error occurred attempting to subscribe [" + e.getMessage() + "] and then again while trying to pass to onError.", e2));
            }
        }
    }

    public final Observable<T> subscribeOn(Scheduler scheduler) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).scalarScheduleOn(scheduler);
        }
        return create(new OperatorSubscribeOn(this, scheduler));
    }

    public final Observable<T> take(int count) {
        return lift(new OperatorTake(count));
    }

    public final Observable<T> takeWhile(Func1<? super T, Boolean> predicate) {
        return lift(new OperatorTakeWhile(predicate));
    }
}
