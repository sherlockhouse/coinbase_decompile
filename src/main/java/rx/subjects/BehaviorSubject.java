package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import rx.Observable.OnSubscribe;
import rx.exceptions.Exceptions;
import rx.internal.operators.NotificationLite;
import rx.subjects.SubjectSubscriptionManager.SubjectObserver;

public final class BehaviorSubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private final SubjectSubscriptionManager<T> state;

    public static <T> BehaviorSubject<T> create() {
        return create(null, false);
    }

    public static <T> BehaviorSubject<T> create(T defaultValue) {
        return create(defaultValue, true);
    }

    private static <T> BehaviorSubject<T> create(T defaultValue, boolean hasDefault) {
        SubjectSubscriptionManager<T> state = new SubjectSubscriptionManager();
        if (hasDefault) {
            state.setLatest(NotificationLite.next(defaultValue));
        }
        state.onAdded = new 1(state);
        state.onTerminated = state.onAdded;
        return new BehaviorSubject(state, state);
    }

    protected BehaviorSubject(OnSubscribe<T> onSubscribe, SubjectSubscriptionManager<T> state) {
        super(onSubscribe);
        this.state = state;
    }

    public void onCompleted() {
        if (this.state.getLatest() == null || this.state.active) {
            Object n = NotificationLite.completed();
            for (SubjectObserver<T> bo : this.state.terminate(n)) {
                bo.emitNext(n);
            }
        }
    }

    public void onError(Throwable e) {
        if (this.state.getLatest() == null || this.state.active) {
            Object n = NotificationLite.error(e);
            List<Throwable> errors = null;
            for (SubjectObserver<T> bo : this.state.terminate(n)) {
                try {
                    bo.emitNext(n);
                } catch (Throwable e2) {
                    if (errors == null) {
                        errors = new ArrayList();
                    }
                    errors.add(e2);
                }
            }
            Exceptions.throwIfAny(errors);
        }
    }

    public void onNext(T v) {
        if (this.state.getLatest() == null || this.state.active) {
            Object n = NotificationLite.next(v);
            for (SubjectObserver<T> bo : this.state.next(n)) {
                bo.emitNext(n);
            }
        }
    }

    public T getValue() {
        Object o = this.state.getLatest();
        if (NotificationLite.isNext(o)) {
            return NotificationLite.getValue(o);
        }
        return null;
    }
}
