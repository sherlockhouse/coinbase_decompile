package com.coinbase.android.signin;

import android.content.Context;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ComponentProvider;
import com.coinbase.api.LoginManager;
import com.coinbase.v1.exception.CoinbaseException;
import java.io.IOException;
import javax.inject.Inject;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

@ActivityScope
public class OAuthTask {
    String authCode;
    @Inject
    LoginManager loginManager;
    @Inject
    @BackgroundScheduler
    Scheduler mBackgroundScheduler;

    protected OAuthTask(Context c, String authCode) {
        this.authCode = authCode;
        ((ComponentProvider) c.getApplicationContext()).applicationComponent().tasksSubcomponent().inject(this);
    }

    public Observable<Boolean> call() {
        return Observable.create(OAuthTask$$Lambda$1.lambdaFactory$(this)).subscribeOn(this.mBackgroundScheduler);
    }

    static /* synthetic */ void lambda$call$0(OAuthTask this_, Subscriber subscriber) {
        try {
            this_.loginManager.signin(this_.loginManager.getTokens(this_.authCode));
            subscriber.onNext(Boolean.valueOf(true));
            subscriber.onCompleted();
        } catch (CoinbaseException e) {
            if (this_.isDeviceVerify(e)) {
                subscriber.onNext(Boolean.valueOf(false));
                subscriber.onCompleted();
                return;
            }
            subscriber.onError(e);
        } catch (IOException e2) {
            subscriber.onError(e2);
        }
    }

    private boolean isDeviceVerify(CoinbaseException e) {
        String message = e.getMessage();
        if (message == null || !message.equalsIgnoreCase("Unconfirmed Device")) {
            return false;
        }
        return true;
    }
}
