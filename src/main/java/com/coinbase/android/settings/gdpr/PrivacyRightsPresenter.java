package com.coinbase.android.settings.gdpr;

import android.app.Application;
import android.content.Context;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.settings.SettingsPreferenceItem;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Scheduler;
import rx.functions.Func0;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class PrivacyRightsPresenter {
    private final SettingsPreferenceItemClickedConnector mClickedConnector;
    private final Context mContext;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final List<SettingsPreferenceItem> mPreferenceList = new ArrayList();
    private final GdprSettingsRouter mRouter;
    private final PrivacyRightsScreen mScreen;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public PrivacyRightsPresenter(Application application, PrivacyRightsScreen screen, GdprSettingsRouter router, MixpanelTracking mixpanelTracking, SettingsPreferenceItemClickedConnector clickedConnector, List<Func0<SettingsPreferenceItem>> preferenceList, @MainScheduler Scheduler mainScheduler) {
        this.mContext = application;
        this.mScreen = screen;
        this.mRouter = router;
        this.mMixpanelTracking = mixpanelTracking;
        this.mClickedConnector = clickedConnector;
        this.mMainScheduler = mainScheduler;
        initList(preferenceList);
    }

    void onShow() {
        this.mMixpanelTracking.trackEvent(GdprSettingsEvent.PRIVACY_OPTIONS_VIEWED);
        this.mSubscription.add(this.mClickedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(PrivacyRightsPresenter$$Lambda$1.lambdaFactory$(this)));
    }

    void onHide() {
        this.mSubscription.clear();
    }

    List<SettingsPreferenceItem> getPreferenceList() {
        return this.mPreferenceList;
    }

    private void initList(List<Func0<SettingsPreferenceItem>> preferenceList) {
        this.mPreferenceList.clear();
        if (preferenceList != null) {
            for (Func0<SettingsPreferenceItem> item : preferenceList) {
                this.mPreferenceList.add(item.call());
            }
        }
    }
}
