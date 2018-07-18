package rx.observers;

import java.util.Arrays;
import rx.Observer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.exceptions.UnsubscribeFailedException;
import rx.plugins.RxJavaHooks;
import rx.plugins.RxJavaPlugins;

public class SafeSubscriber<T> extends Subscriber<T> {
    private final Subscriber<? super T> actual;
    boolean done;

    public SafeSubscriber(Subscriber<? super T> actual) {
        super(actual);
        this.actual = actual;
    }

    public void onCompleted() {
        UnsubscribeFailedException unsubscribeFailedException;
        if (!this.done) {
            this.done = true;
            try {
                this.actual.onCompleted();
                try {
                    unsubscribe();
                } catch (Throwable e) {
                    RxJavaHooks.onError(e);
                    unsubscribeFailedException = new UnsubscribeFailedException(e.getMessage(), e);
                }
            } catch (Throwable th) {
                try {
                    unsubscribe();
                } catch (Throwable e2) {
                    RxJavaHooks.onError(e2);
                    unsubscribeFailedException = new UnsubscribeFailedException(e2.getMessage(), e2);
                }
            }
        }
    }

    public void onError(Throwable e) {
        Exceptions.throwIfFatal(e);
        if (!this.done) {
            this.done = true;
            _onError(e);
        }
    }

    public void onNext(T t) {
        try {
            if (!this.done) {
                this.actual.onNext(t);
            }
        } catch (Throwable e) {
            Exceptions.throwOrReport(e, (Observer) this);
        }
    }

    protected void _onError(Throwable e) {
        OnErrorFailedException onErrorFailedException;
        RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        try {
            this.actual.onError(e);
            try {
                unsubscribe();
            } catch (Throwable unsubscribeException) {
                RxJavaHooks.onError(unsubscribeException);
                onErrorFailedException = new OnErrorFailedException(unsubscribeException);
            }
        } catch (OnErrorNotImplementedException e2) {
            unsubscribe();
            throw e2;
        } catch (Throwable unsubscribeException2) {
            RxJavaHooks.onError(unsubscribeException2);
            onErrorFailedException = new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(new Throwable[]{e, e2, unsubscribeException2})));
        }
    }
}
