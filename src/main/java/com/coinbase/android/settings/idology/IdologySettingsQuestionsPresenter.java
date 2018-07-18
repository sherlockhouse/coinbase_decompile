package com.coinbase.android.settings.idology;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyAnswerListValidConnector;
import com.coinbase.android.idology.IdologyFailureRouter;
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
public class IdologySettingsQuestionsPresenter {
    private Bundle mArgs;
    private final IdologyAnswerListValidConnector mIdologyAnswerListValidConnector;
    private final IdologyFailureRouter mIdologyFailureRouter;
    private final IdologyRetryConnector mIdologyRetryConnector;
    private final IdologySuccessRouter mIdologySuccessRouter;
    private final IdologyVerificationConnector mIdologyVerificationConnector;
    private final Logger mLogger = LoggerFactory.getLogger(IdologySettingsQuestionsPresenter.class);
    private final Scheduler mMainScheduler;
    private final IdologyRouter mRouter;
    private final IdologySettingsQuestionsScreen mScreen;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public IdologySettingsQuestionsPresenter(IdologySettingsQuestionsScreen screen, IdologyAnswerListValidConnector idologyAnswerListValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyRetryConnector idologyRetryConnector, IdologySuccessRouter idologySuccessRouter, IdologyFailureRouter idologyFailureRouter, IdologyRouter idologyRouter, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mIdologyAnswerListValidConnector = idologyAnswerListValidConnector;
        this.mIdologyVerificationConnector = idologyVerificationConnector;
        this.mMainScheduler = mainScheduler;
        this.mRouter = idologyRouter;
        this.mIdologySuccessRouter = idologySuccessRouter;
        this.mIdologyFailureRouter = idologyFailureRouter;
        this.mIdologyRetryConnector = idologyRetryConnector;
    }

    public void onShow() {
        this.mSubscription.add(this.mIdologyAnswerListValidConnector.get().onBackpressureLatest().filter(IdologySettingsQuestionsPresenter$$Lambda$1.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(IdologySettingsQuestionsPresenter$$Lambda$2.lambdaFactory$(this), IdologySettingsQuestionsPresenter$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mIdologyRetryConnector.get().onBackpressureLatest().filter(IdologySettingsQuestionsPresenter$$Lambda$4.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(IdologySettingsQuestionsPresenter$$Lambda$5.lambdaFactory$(this), IdologySettingsQuestionsPresenter$$Lambda$6.lambdaFactory$(this)));
        this.mSubscription.add(this.mIdologyVerificationConnector.get().onBackpressureLatest().subscribe(IdologySettingsQuestionsPresenter$$Lambda$7.lambdaFactory$(this), IdologySettingsQuestionsPresenter$$Lambda$8.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onShow$0(Boolean isValid) {
        return Boolean.valueOf(isValid != null);
    }

    static /* synthetic */ Boolean lambda$onShow$3(Boolean isValid) {
        return Boolean.valueOf(isValid != null);
    }

    public void onHide() {
        this.mSubscription.clear();
    }

    public void onViewCreated(Bundle args) {
        this.mArgs = args;
        Data idologyData = getIdologyData(args);
        if (idologyData != null) {
            this.mScreen.setQuestions(idologyData.getQuestions());
            setContinueMenuEnabled(Boolean.valueOf(false));
        }
    }

    public void onContinueClicked(Bundle args) {
        setContinueMenuEnabled(Boolean.valueOf(false));
        Data idologyData = getIdologyData(args);
        if (idologyData != null) {
            this.mScreen.showProgress();
            this.mScreen.submitAnswers(idologyData.getId());
        }
    }

    private Data getIdologyData(Bundle args) {
        if (args == null) {
            return null;
        }
        return (Data) new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().fromJson(args.getString(IdologyArgsBuilder.IDOLOGY_DATA), Data.class);
    }

    private void setContinueMenuEnabled(Boolean isValid) {
        this.mScreen.setContinueMenuEnabled(isValid.booleanValue());
    }

    private void processVerificationResult(Data idology) {
        if (idology != null && idology.getStatus() != null) {
            switch (idology.getStatus()) {
                case SUCCESS:
                    this.mSubscription.add(this.mIdologySuccessRouter.routeToSuccessPage());
                    return;
                default:
                    this.mIdologyFailureRouter.routeToFailure(idology);
                    return;
            }
        }
    }
}
