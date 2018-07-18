package com.coinbase.android.settings.gdpr;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Scheduler;
import rx.functions.Func0;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class GdprPrivacyRequestPresenter {
    static final String GDPR_PRIVACY_REQUEST_CLASS = "GDPR_PRIVACY_REQUEST_CLASS";
    private final SettingsPreferenceItemClickedConnector mClickedConnector;
    private final Context mContext;
    private PrivacyRequestViewModel mDataViewModel;
    private final Map<Class<?>, Func0<PrivacyRequestViewModel>> mGdprRequestViewModelMap;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final List<GdprPrivacyRequestSettingsPreferenceItem> mOptionsList = new ArrayList();
    private final GdprSettingsRouter mRouter;
    private final GdprPrivacyRequestScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubmitRequestSubscription = new CompositeSubscription();
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public GdprPrivacyRequestPresenter(Application application, GdprPrivacyRequestScreen screen, LoginManager loginManager, SettingsPreferenceItemClickedConnector clickedConnector, Map<Class<?>, Func0<PrivacyRequestViewModel>> requestViewModelMap, MixpanelTracking mixpanelTracking, GdprSettingsRouter router, SnackBarWrapper snackBarWrapper, @MainScheduler Scheduler mainScheduler) {
        this.mContext = application;
        this.mScreen = screen;
        this.mLoginManager = loginManager;
        this.mClickedConnector = clickedConnector;
        this.mGdprRequestViewModelMap = requestViewModelMap;
        this.mMixpanelTracking = mixpanelTracking;
        this.mRouter = router;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mMainScheduler = mainScheduler;
    }

    void onCreateView(Bundle args) {
        Func0<PrivacyRequestViewModel> gdpPrivacyRequestViewModelFunc0 = (Func0) this.mGdprRequestViewModelMap.get(args.get(GDPR_PRIVACY_REQUEST_CLASS));
        if (gdpPrivacyRequestViewModelFunc0 != null) {
            this.mDataViewModel = (PrivacyRequestViewModel) gdpPrivacyRequestViewModelFunc0.call();
            if (this.mDataViewModel == null) {
                this.mSnackBarWrapper.showGenericError();
                return;
            }
            this.mOptionsList.clear();
            this.mOptionsList.addAll(this.mDataViewModel.getOptions());
            this.mScreen.updateLegalHeader(this.mDataViewModel.getLegalHeader());
            if (this.mDataViewModel.isTerminalPage()) {
                this.mScreen.showAddlMessageAndButton();
            } else {
                this.mScreen.hideAddlMessageAndButton();
            }
        }
    }

    void onHide() {
        this.mSubscription.clear();
        this.mSubmitRequestSubscription.clear();
    }

    void onShow() {
        this.mMixpanelTracking.trackEvent(this.mDataViewModel.getAnalyticsEvent());
        this.mSubscription.add(this.mClickedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(GdprPrivacyRequestPresenter$$Lambda$1.lambdaFactory$(this)));
        updateToolbarTitle();
    }

    List<GdprPrivacyRequestSettingsPreferenceItem> getOptionsList() {
        return this.mOptionsList;
    }

    void onSendRequestClicked(String openFieldText) {
        this.mSubmitRequestSubscription.clear();
        this.mScreen.showProgressBar();
        List<String> fieldList = new ArrayList();
        for (GdprPrivacyRequestSettingsPreferenceItem item : this.mOptionsList) {
            if (item.isSwitchOn()) {
                fieldList.add(item.getSettingType().toString());
            }
        }
        HashMap<String, Object> body = new HashMap();
        body.put(ApiConstants.REQUEST_ACTION, this.mDataViewModel.getActionType().getAction());
        body.put(ApiConstants.FIELDS, fieldList);
        body.put(ApiConstants.OPEN_FIELD, openFieldText);
        this.mMixpanelTracking.trackEvent(GdprSettingsEvent.REQUEST_TAPPED_SEND_REQUEST);
        this.mSubmitRequestSubscription.add(this.mLoginManager.getClient().sendGdprRequestsRx(body).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(GdprPrivacyRequestPresenter$$Lambda$2.lambdaFactory$(this), GdprPrivacyRequestPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$onSendRequestClicked$1(GdprPrivacyRequestPresenter this_, Pair responseRetrofitPair) {
        this_.mScreen.hideProgressBar();
        Response<Void> response = responseRetrofitPair.first;
        if (response.isSuccessful()) {
            this_.mRouter.routeToSuccessPage();
        } else {
            this_.mSnackBarWrapper.showError(response);
        }
    }

    static /* synthetic */ void lambda$onSendRequestClicked$2(GdprPrivacyRequestPresenter this_, Throwable throwable) {
        this_.mScreen.hideProgressBar();
        this_.mSnackBarWrapper.show(Utils.getMessage(this_.mContext, throwable));
    }

    private void updateToolbarTitle() {
        if (this.mDataViewModel != null) {
            this.mScreen.updateToolbarTitle(this.mDataViewModel.getTitle());
        }
    }
}
