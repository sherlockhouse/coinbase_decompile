package com.coinbase.android.settings.idology;

import android.app.Application;
import android.content.Context;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.idology.IdologyTrackingContextProvider;
import com.coinbase.android.settings.AccountSettingsController;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;

@ControllerScope
public class IdologySuccessPresenter {
    private final Context mContext;
    private final IdologyTrackingContextProvider mIdologyTrackingContext;
    private final MixpanelTracking mMixpanelTracking;
    private final IdologySuccessScreen mScreen;
    private final SuccessRouter mSuccessRouter;

    @Inject
    public IdologySuccessPresenter(Application app, IdologySuccessScreen screen, MixpanelTracking mixpanelTracking, IdologyTrackingContextProvider idologyTrackingContext, SuccessRouter successRouter) {
        this.mContext = app;
        this.mScreen = screen;
        this.mMixpanelTracking = mixpanelTracking;
        this.mIdologyTrackingContext = idologyTrackingContext;
        this.mSuccessRouter = successRouter;
    }

    void onShow() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_SUCCESS, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext.getContext());
        if (this.mSuccessRouter.hasRootTag(AccountSettingsController.class)) {
            this.mScreen.setSuccessButtonText(this.mContext.getString(R.string.idology_linked_account_success_cta));
        } else {
            this.mScreen.setSuccessButtonText(this.mContext.getString(R.string.continue_button));
        }
    }

    void gotoSettings() {
        if (this.mSuccessRouter.shouldRouteSuccess()) {
            this.mSuccessRouter.routeSuccess();
        }
    }
}
