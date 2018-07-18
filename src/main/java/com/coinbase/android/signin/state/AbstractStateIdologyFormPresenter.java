package com.coinbase.android.signin.state;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.idology.ProgressConnector;
import com.coinbase.android.settings.idology.IdologyArgsBuilder;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.idology.Data;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public abstract class AbstractStateIdologyFormPresenter {
    Bundle mArgs;
    private final IdologyFormValidConnector mIdologyFormValidConnector;
    private final IdologyVerificationConnector mIdologyVerificationConnector;
    private final Logger mLogger = LoggerFactory.getLogger(AbstractStateIdologyFormPresenter.class);
    private final Scheduler mMainScheduler;
    private final ProgressConnector mProgressConnector;
    private final StateIdologyFormScreen mScreen;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    public abstract void processVerificationResult(Data data);

    public AbstractStateIdologyFormPresenter(StateIdologyFormScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyVerificationConnector idologyVerificationConnector, ProgressConnector progressConnector, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mIdologyFormValidConnector = idologyFormValidConnector;
        this.mIdologyVerificationConnector = idologyVerificationConnector;
        this.mProgressConnector = progressConnector;
        this.mMainScheduler = mainScheduler;
    }

    void onViewCreated(Bundle args) {
        this.mArgs = args;
        if (this.mArgs != null) {
            Data idologyData = getFetchedIdologyData();
            if (idologyData == null) {
                idologyData = getBuildingIdologyData();
                if (idologyData == null) {
                    return;
                }
            }
            this.mScreen.setIdologyData(idologyData);
        }
    }

    void onShow() {
        this.mSubscription.clear();
        this.mSubscription.add(this.mIdologyFormValidConnector.get().onBackpressureLatest().filter(AbstractStateIdologyFormPresenter$$Lambda$1.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(AbstractStateIdologyFormPresenter$$Lambda$2.lambdaFactory$(this), AbstractStateIdologyFormPresenter$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mIdologyVerificationConnector.get().onBackpressureLatest().subscribe(AbstractStateIdologyFormPresenter$$Lambda$4.lambdaFactory$(this), AbstractStateIdologyFormPresenter$$Lambda$5.lambdaFactory$(this)));
        this.mSubscription.add(this.mProgressConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(AbstractStateIdologyFormPresenter$$Lambda$6.lambdaFactory$(this), AbstractStateIdologyFormPresenter$$Lambda$7.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onShow$0(Boolean isValid) {
        return Boolean.valueOf(isValid != null);
    }

    static /* synthetic */ void lambda$onShow$1(AbstractStateIdologyFormPresenter this_, Boolean isValid) {
        this_.setContinueButtonEnabled(isValid.booleanValue());
        this_.mScreen.hideProgress();
    }

    static /* synthetic */ void lambda$onShow$5(AbstractStateIdologyFormPresenter this_, Boolean b) {
        if (b.booleanValue()) {
            this_.mScreen.showProgress();
        } else {
            this_.mScreen.hideProgress();
        }
    }

    void onHide() {
        this.mSubscription.clear();
    }

    void onContinueButtonClicked() {
        setContinueButtonEnabled(false);
        this.mScreen.submitForm(getBuildingIdologyData());
    }

    protected void setContinueButtonEnabled(boolean isEnabled) {
        this.mScreen.setContinueButtonEnabled(isEnabled);
    }

    private Data getBuildingIdologyData() {
        String idologyJson = this.mArgs.getString(IdologyArgsBuilder.BUILDING_IDOLOGY_DATA);
        if (idologyJson == null) {
            return null;
        }
        return (Data) new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().fromJson(idologyJson, Data.class);
    }

    private Data getFetchedIdologyData() {
        String idologyJson = this.mArgs.getString(IdologyArgsBuilder.IDOLOGY_DATA);
        if (idologyJson == null) {
            return null;
        }
        return (Data) new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().fromJson(idologyJson, Data.class);
    }
}
