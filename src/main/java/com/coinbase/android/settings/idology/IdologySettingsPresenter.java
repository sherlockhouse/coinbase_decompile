package com.coinbase.android.settings.idology;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyRetryConnector;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.idology.Data;
import com.google.gson.GsonBuilder;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class IdologySettingsPresenter {
    private Bundle mArgs;
    private final IdologyFailureRouter mIdologyFailureRouter;
    private final IdologyFormValidConnector mIdologyFormValidConnector;
    private final IdologyRetryConnector mIdologyRetryConnector;
    private final IdologySuccessRouter mIdologySuccessRouter;
    private final IdologyVerificationConnector mIdologyVerificationConnector;
    private final Logger mLogger = LoggerFactory.getLogger(IdologySettingsPresenter.class);
    private final Scheduler mMainScheduler;
    private final IdologyRouter mRouter;
    private final IdologySettingsScreen mScreen;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public IdologySettingsPresenter(IdologySettingsScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyRouter idologyRouter, IdologySuccessRouter idologySuccessRouter, IdologyFailureRouter idologyFailureRouter, IdologyRetryConnector idologyRetryConnector, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mIdologyFormValidConnector = idologyFormValidConnector;
        this.mIdologyVerificationConnector = idologyVerificationConnector;
        this.mMainScheduler = mainScheduler;
        this.mRouter = idologyRouter;
        this.mIdologySuccessRouter = idologySuccessRouter;
        this.mIdologyFailureRouter = idologyFailureRouter;
        this.mIdologyRetryConnector = idologyRetryConnector;
    }

    void onViewCreated(Bundle args) {
        this.mArgs = args;
        if (this.mArgs != null) {
            String idologyJson = args.getString(IdologyArgsBuilder.IDOLOGY_DATA);
            if (idologyJson != null) {
                Data idologyData = (Data) new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().fromJson(idologyJson, Data.class);
                if (idologyData != null) {
                    this.mScreen.setIdologyData(idologyData);
                }
            }
        }
    }

    void onShow() {
        this.mSubscription.clear();
        this.mSubscription.add(this.mIdologyFormValidConnector.get().onBackpressureLatest().filter(IdologySettingsPresenter$$Lambda$1.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(IdologySettingsPresenter$$Lambda$2.lambdaFactory$(this), IdologySettingsPresenter$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mIdologyRetryConnector.get().onBackpressureLatest().filter(IdologySettingsPresenter$$Lambda$4.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(IdologySettingsPresenter$$Lambda$5.lambdaFactory$(this), IdologySettingsPresenter$$Lambda$6.lambdaFactory$(this)));
        this.mSubscription.add(this.mIdologyVerificationConnector.get().onBackpressureLatest().subscribe(IdologySettingsPresenter$$Lambda$7.lambdaFactory$(this), IdologySettingsPresenter$$Lambda$8.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onShow$0(Boolean isValid) {
        return Boolean.valueOf(isValid != null);
    }

    static /* synthetic */ Boolean lambda$onShow$3(Boolean isValid) {
        return Boolean.valueOf(isValid != null);
    }

    void onHide() {
        this.mSubscription.clear();
    }

    void onContinueClicked() {
        setContinueMenuEnabled(false);
        this.mScreen.submitForm();
    }

    private void setContinueMenuEnabled(boolean isEnabled) {
        this.mScreen.setContinueMenuEnabled(isEnabled);
    }

    private void processVerificationResult(Data idology) {
        if (idology != null && idology.getStatus() != null) {
            saveArgs(idology);
            switch (idology.getStatus()) {
                case HAS_QUESTIONS:
                    this.mRouter.routeToQuestionsPage(idology);
                    return;
                case SUCCESS:
                    this.mSubscription.add(this.mIdologySuccessRouter.routeToSuccessPage());
                    return;
                default:
                    this.mIdologyFailureRouter.routeToFailure(idology);
                    return;
            }
        }
    }

    private void saveArgs(Data idology) {
        if (this.mArgs != null && idology != null) {
            String idologyStr = new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().toJson((Object) idology);
            if (idologyStr != null) {
                this.mArgs.putString(IdologyArgsBuilder.IDOLOGY_DATA, idologyStr);
            }
        }
    }
}
