package com.coinbase.android.gdpr;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class GdprMarketingEmailPresenter {
    private final Context mContext;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final OnboardingRouter mOnboardingRouter;
    private final OnboardingUpdatedConnector mOnboardingUpdatedConnector;
    private final GdprMarketingEmailScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public GdprMarketingEmailPresenter(Application application, GdprMarketingEmailScreen gdprMarketingEmailScreen, LoginManager loginManager, MixpanelTracking mixpanelTracking, OnboardingRouter onboardingRouter, OnboardingUpdatedConnector onboardingUpdatedConnector, @MainScheduler Scheduler mainScheduler, SnackBarWrapper snackBarWrapper) {
        this.mContext = application;
        this.mScreen = gdprMarketingEmailScreen;
        this.mLoginManager = loginManager;
        this.mMixpanelTracking = mixpanelTracking;
        this.mOnboardingRouter = onboardingRouter;
        this.mOnboardingUpdatedConnector = onboardingUpdatedConnector;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMainScheduler = mainScheduler;
    }

    void onCreateView(Bundle args) {
        this.mMixpanelTracking.trackEvent(GdprEvent.MARKETING_VIEWED);
        if (args.getBoolean(OnboardingScreen.CENTER_MODAL_WITH_OVERLAY, false)) {
            this.mScreen.setBackgroundTranslucent(true);
            this.mScreen.setModalCentered(true);
            return;
        }
        this.mScreen.setBackgroundTranslucent(false);
        this.mScreen.setModalCentered(false);
    }

    void onHide() {
        this.mSubscription.clear();
        this.mScreen.hideProgressBar();
    }

    void onYesClicked() {
        this.mMixpanelTracking.trackEvent(GdprEvent.MARKETING_TAPPED_YES);
        updateEmailPreferences(true);
    }

    void onNoClicked() {
        this.mMixpanelTracking.trackEvent(GdprEvent.MARKETING_TAPPED_NO);
        updateEmailPreferences(false);
    }

    private void updateEmailPreferences(boolean shouldEmail) {
        this.mSubscription.clear();
        this.mScreen.showProgressBar();
        if (this.mLoginManager.isSignedIn()) {
            HashMap<String, Object> body = new HashMap();
            body.put("type", ApiConstants.MARKETING);
            body.put(ApiConstants.SHOULD_EMAIL, Boolean.valueOf(shouldEmail));
            this.mSubscription.add(this.mLoginManager.getClient().updateEmailPreferencesRx(body).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(GdprMarketingEmailPresenter$$Lambda$1.lambdaFactory$(this), GdprMarketingEmailPresenter$$Lambda$2.lambdaFactory$(this)));
            return;
        }
        HashMap<String, Object> marketingEmailType = new HashMap();
        marketingEmailType.put(ApiConstants.SHOULD_SEND_MARKETING, Boolean.valueOf(shouldEmail));
        Map<String, Object> results = new HashMap((Map) this.mOnboardingUpdatedConnector.getResults().getValue());
        results.put(ApiConstants.EMAIL_PREFERENCE_PARAM, marketingEmailType);
        this.mOnboardingUpdatedConnector.getResults().onNext(results);
        this.mOnboardingRouter.routeToNext(null);
    }

    static /* synthetic */ void lambda$updateEmailPreferences$0(GdprMarketingEmailPresenter this_, Pair responseRetrofitPair) {
        this_.mScreen.hideProgressBar();
        Response<Void> response = responseRetrofitPair.first;
        if (response.isSuccessful()) {
            this_.mOnboardingRouter.routeToNext(null);
        } else {
            this_.mSnackBarWrapper.showError(response);
        }
    }

    static /* synthetic */ void lambda$updateEmailPreferences$1(GdprMarketingEmailPresenter this_, Throwable throwable) {
        this_.mScreen.hideProgressBar();
        this_.mSnackBarWrapper.show(Utils.getMessage(this_.mContext, throwable));
    }
}
