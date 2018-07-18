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
import com.coinbase.v2.models.user.OnboardingItem;
import com.coinbase.v2.models.user.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class GdprPrivacyPolicyPresenter {
    private final Context mContext;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final OnboardingRouter mOnboardingRouter;
    private final OnboardingUpdatedConnector mOnboardingUpdatedConnector;
    private final GdprPrivacyPolicyScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public GdprPrivacyPolicyPresenter(Application application, GdprPrivacyPolicyScreen gdprPrivacyPolicyScreen, LoginManager loginManager, MixpanelTracking mixpanelTracking, OnboardingRouter onboardingRouter, OnboardingUpdatedConnector onboardingUpdatedConnector, @MainScheduler Scheduler mainScheduler, SnackBarWrapper snackBarWrapper) {
        this.mContext = application;
        this.mScreen = gdprPrivacyPolicyScreen;
        this.mLoginManager = loginManager;
        this.mMixpanelTracking = mixpanelTracking;
        this.mOnboardingRouter = onboardingRouter;
        this.mOnboardingUpdatedConnector = onboardingUpdatedConnector;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMainScheduler = mainScheduler;
    }

    void onHide() {
        this.mSubscription.clear();
        this.mScreen.hideProgressBar();
    }

    void onAcknowledgeClicked() {
        this.mSubscription.clear();
        this.mScreen.showProgressBar();
        Map properties = new HashMap();
        properties.put(GdprProperty.LOGGED_IN, Boolean.valueOf(this.mLoginManager.isSignedIn()));
        this.mMixpanelTracking.trackEvent(GdprEvent.PRIVACY_TAPPED_ACKNOWLEDGE, properties);
        if (this.mLoginManager.isSignedIn()) {
            HashMap<String, Object> params = new HashMap();
            params.put(ApiConstants.ACCEPTED_PRIVACY_NOTICE, Boolean.valueOf(true));
            this.mSubscription.add(this.mLoginManager.getClient().updateUserRx(params).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(GdprPrivacyPolicyPresenter$$Lambda$1.lambdaFactory$(this), GdprPrivacyPolicyPresenter$$Lambda$2.lambdaFactory$(this)));
            return;
        }
        Map<String, Object> results = new HashMap((Map) this.mOnboardingUpdatedConnector.getResults().getValue());
        results.put(ApiConstants.ACCEPTED_PRIVACY_NOTICE, Boolean.valueOf(true));
        this.mOnboardingUpdatedConnector.getResults().onNext(results);
        List<OnboardingItem> remainingOnboardingItems = new ArrayList((Collection) this.mOnboardingUpdatedConnector.getOnboardingItems().getValue());
        remainingOnboardingItems.remove(0);
        this.mOnboardingRouter.routeToNext(remainingOnboardingItems);
    }

    static /* synthetic */ void lambda$onAcknowledgeClicked$0(GdprPrivacyPolicyPresenter this_, Pair responseRetrofitPair) {
        this_.mScreen.hideProgressBar();
        Response<User> response = responseRetrofitPair.first;
        if (response.isSuccessful()) {
            this_.mOnboardingRouter.routeToNext(((User) response.body()).getData().getOnboardingItems());
        } else {
            this_.mSnackBarWrapper.showError(response);
        }
    }

    static /* synthetic */ void lambda$onAcknowledgeClicked$1(GdprPrivacyPolicyPresenter this_, Throwable throwable) {
        this_.mScreen.hideProgressBar();
        this_.mSnackBarWrapper.show(Utils.getMessage(this_.mContext, throwable));
    }

    public void onCreateView(Bundle args) {
        Map properties = new HashMap();
        properties.put(GdprProperty.LOGGED_IN, Boolean.valueOf(this.mLoginManager.isSignedIn()));
        this.mMixpanelTracking.trackEvent(GdprEvent.PRIVACY_VIEWED, properties);
        if (args.getBoolean(OnboardingScreen.CENTER_MODAL_WITH_OVERLAY, false)) {
            this.mScreen.setBackgroundTranslucent(true);
            this.mScreen.setModalCentered(true);
            return;
        }
        this.mScreen.setBackgroundTranslucent(false);
        this.mScreen.setModalCentered(false);
    }
}
