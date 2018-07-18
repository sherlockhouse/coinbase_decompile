package com.coinbase.android.settings.gdpr;

import android.app.Application;
import android.content.Context;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.settings.SettingsPreferenceItem;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.settings.gdpr.EmailSettingsPreferenceItem.MarketingEmailSettingsItem;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.emailPreferences.EmailPreferences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class EmailSettingsPresenter {
    private final Scheduler mBackgroundScheduler;
    private final SettingsPreferenceItemClickedConnector mClickedConnector;
    private final Context mContext;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final List<SettingsPreferenceItem> mPreferenceList = new ArrayList();
    private Map<String, Boolean> mPreferencesToggles = new HashMap();
    private final GdprSettingsRouter mRouter;
    private final EmailSettingsScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public EmailSettingsPresenter(Application application, EmailSettingsScreen screen, GdprSettingsRouter router, MixpanelTracking mixpanelTracking, SettingsPreferenceItemClickedConnector clickedConnector, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler, SnackBarWrapper snackBarWrapper, LoginManager loginManager) {
        this.mContext = application;
        this.mScreen = screen;
        this.mRouter = router;
        this.mMixpanelTracking = mixpanelTracking;
        this.mClickedConnector = clickedConnector;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mLoginManager = loginManager;
    }

    void onShow() {
        initList();
        this.mMixpanelTracking.trackEvent(GdprSettingsEvent.EMAIL_SETTINGS_VIEWED);
        this.mScreen.showLoading();
        this.mSubscription.add(this.mLoginManager.getClient().getEmailPreferencesRx().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(EmailSettingsPresenter$$Lambda$1.lambdaFactory$(this), EmailSettingsPresenter$$Lambda$2.lambdaFactory$(this)));
        this.mSubscription.add(this.mClickedConnector.get().onBackpressureLatest().observeOn(this.mBackgroundScheduler).flatMap(EmailSettingsPresenter$$Lambda$3.lambdaFactory$(this)).observeOn(this.mMainScheduler).subscribe(EmailSettingsPresenter$$Lambda$4.lambdaFactory$(this), EmailSettingsPresenter$$Lambda$5.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$onShow$0(EmailSettingsPresenter this_, Pair responseRetrofitPair) {
        Response<EmailPreferences> response = responseRetrofitPair.first;
        if (response.isSuccessful()) {
            this_.mScreen.hideLoading();
            this_.mPreferencesToggles = ((EmailPreferences) response.body()).getData();
            this_.updatePreferencesList();
            return;
        }
        this_.mScreen.hideLoading();
        this_.mSnackBarWrapper.show(this_.mContext.getString(R.string.error_occurred_try_again));
    }

    static /* synthetic */ void lambda$onShow$1(EmailSettingsPresenter this_, Throwable throwable) {
        this_.mScreen.hideLoading();
        this_.mSnackBarWrapper.show(this_.mContext.getString(R.string.error_occurred_try_again));
    }

    static /* synthetic */ Observable lambda$onShow$2(EmailSettingsPresenter this_, SettingsPreferenceItem settingsPreferenceItem) {
        HashMap<String, Object> params = new HashMap();
        params.put("type", ApiConstants.MARKETING);
        params.put(ApiConstants.SHOULD_EMAIL, Boolean.valueOf(!settingsPreferenceItem.isSwitchOn()));
        return this_.mLoginManager.getClient().updateEmailPreferencesRx(params);
    }

    static /* synthetic */ void lambda$onShow$3(EmailSettingsPresenter this_, Pair responseRetrofitPair) {
        if (responseRetrofitPair.first.isSuccessful()) {
            this_.mPreferencesToggles.put(ApiConstants.MARKETING, Boolean.valueOf(!((Boolean) this_.mPreferencesToggles.get(ApiConstants.MARKETING)).booleanValue()));
            this_.updatePreferencesList();
            return;
        }
        this_.mSnackBarWrapper.show(this_.mContext.getString(R.string.error_occurred_try_again));
    }

    void onHide() {
        this.mSubscription.clear();
    }

    void updatePreferencesList() {
        this.mScreen.updateList();
    }

    boolean isToggled(String itemName) {
        if (this.mPreferencesToggles == null || !this.mPreferencesToggles.containsKey(itemName)) {
            return false;
        }
        return ((Boolean) this.mPreferencesToggles.get(itemName)).booleanValue();
    }

    List<SettingsPreferenceItem> getPreferenceList() {
        return this.mPreferenceList;
    }

    private void initList() {
        this.mPreferenceList.clear();
        this.mPreferenceList.add(new MarketingEmailSettingsItem(this.mContext, this.mClickedConnector, this));
        updatePreferencesList();
    }
}
