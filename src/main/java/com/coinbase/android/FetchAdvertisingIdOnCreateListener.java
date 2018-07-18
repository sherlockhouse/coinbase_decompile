package com.coinbase.android;

import android.app.Application;
import android.content.Context;
import com.coinbase.api.internal.CoinbaseInternal;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

@ApplicationScope
public class FetchAdvertisingIdOnCreateListener implements ApplicationOnCreateListener {
    private final Scheduler mBackgroundScheduler;
    private final CoinbaseInternal mCoinbaseInternal;
    private final Context mContext;
    private final Logger mLogger = LoggerFactory.getLogger(FetchAdvertisingIdOnCreateListener.class);

    @Inject
    FetchAdvertisingIdOnCreateListener(Application app, @BackgroundScheduler Scheduler backgroundScheduler, CoinbaseInternal coinbaseInternal) {
        this.mContext = app;
        this.mBackgroundScheduler = backgroundScheduler;
        this.mCoinbaseInternal = coinbaseInternal;
    }

    public void onCreate() {
        Observable.create(FetchAdvertisingIdOnCreateListener$$Lambda$1.lambdaFactory$(this)).first().subscribeOn(this.mBackgroundScheduler).subscribe(FetchAdvertisingIdOnCreateListener$$Lambda$2.lambdaFactory$(this), FetchAdvertisingIdOnCreateListener$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$onCreate$0(FetchAdvertisingIdOnCreateListener this_, Subscriber subscriber) {
        Exception e;
        try {
            Info idInfo = AdvertisingIdClient.getAdvertisingIdInfo(this_.mContext);
            if (idInfo == null) {
                String errorMessage = "Id info returned null";
                this_.mLogger.error(errorMessage);
                subscriber.onError(new Throwable(errorMessage));
                return;
            }
            try {
                subscriber.onNext(idInfo.getId());
                subscriber.onCompleted();
            } catch (NullPointerException e2) {
                subscriber.onError(e2);
            }
        } catch (GooglePlayServicesNotAvailableException e3) {
            e = e3;
            this_.mLogger.error("Unable to get advertising id", e);
            subscriber.onError(e);
        } catch (GooglePlayServicesRepairableException e4) {
            e = e4;
            this_.mLogger.error("Unable to get advertising id", e);
            subscriber.onError(e);
        } catch (IOException e5) {
            e = e5;
            this_.mLogger.error("Unable to get advertising id", e);
            subscriber.onError(e);
        }
    }
}
