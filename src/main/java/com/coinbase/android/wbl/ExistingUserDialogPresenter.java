package com.coinbase.android.wbl;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.tiers.Level;
import com.coinbase.api.internal.models.tiers.Tiers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class ExistingUserDialogPresenter {
    public static final String ACCOUNT_LEVELS = "account_levels";
    private final Gson mGson = new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create();
    private final MixpanelTracking mMixpanelTracking;
    private final ExistingUserDialogRouter mRouter;
    private final ExistingUserDialogScreen mScreen;

    @Inject
    public ExistingUserDialogPresenter(ExistingUserDialogScreen screen, ExistingUserDialogRouter router, MixpanelTracking mixpanelTracking) {
        this.mScreen = screen;
        this.mRouter = router;
        this.mMixpanelTracking = mixpanelTracking;
    }

    void onCreate(Bundle args) {
        if (args == null || !args.containsKey(ACCOUNT_LEVELS)) {
            this.mScreen.dismiss(false);
            return;
        }
        Tiers data = (Tiers) this.mGson.fromJson(args.getString(ACCOUNT_LEVELS), Tiers.class);
        if (data.getData() == null || data.getData().getVerificationLevels() == null) {
            this.mScreen.dismiss(false);
            return;
        }
        List<Level> tiers = data.getData().getVerificationLevels().getLevels();
        if (tiers == null || tiers.isEmpty()) {
            this.mScreen.dismiss(false);
            return;
        }
        checkLevelsAndSetScreen(tiers);
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_WBL_EXISTING_USER_ACCOUNT_SETUP_VIEWED, new String[0]);
    }

    void onClickAccountLevels() {
        this.mScreen.dismiss(true);
        this.mRouter.routeToAccountLevels();
        trackAccountLevels();
    }

    void onClickLearnMore() {
        this.mScreen.dismiss(true);
        this.mRouter.routeToLearnMore();
        trackLearnMore();
    }

    void onDismiss() {
        this.mScreen.dismiss(false);
    }

    private void trackLearnMore() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_WBL_EXISTING_USER_ACCOUNT_SETUP_TAPPED_LEARN_MORE, new String[0]);
    }

    private void trackAccountLevels() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_WBL_EXISTING_USER_ACCOUNT_SETUP_TAPPED_COMPLETE_YOUR_PROFILE, new String[0]);
    }

    private void checkLevelsAndSetScreen(List<Level> tiers) {
        int i = 0;
        while (i < tiers.size()) {
            Level tier = (Level) tiers.get(i);
            if (tier.getStatus() != null && tier.getStatus().getComplete() != null && tier.getStatus().getComplete().booleanValue()) {
                i++;
            } else if (i == 0) {
                this.mScreen.showTier0AccountLevels();
                return;
            } else {
                this.mScreen.showIncompleteAccountLevels();
                return;
            }
        }
        this.mScreen.showCompleteAccountLevels();
    }
}
