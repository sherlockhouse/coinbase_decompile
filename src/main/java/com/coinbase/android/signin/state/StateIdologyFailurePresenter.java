package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyTrackingContextProvider;
import com.coinbase.android.settings.idology.IdologyRouter;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;

@ControllerScope
public class StateIdologyFailurePresenter {
    private final IdologyTrackingContextProvider mIdologyTrackingContext;
    private final MixpanelTracking mMixpanelTracking;
    private final IdologyRouter mRouter;

    @Inject
    public StateIdologyFailurePresenter(MixpanelTracking mixpanelTracking, IdologyTrackingContextProvider idologyTrackingContext, IdologyRouter idologyRouter) {
        this.mMixpanelTracking = mixpanelTracking;
        this.mIdologyTrackingContext = idologyTrackingContext;
        this.mRouter = idologyRouter;
    }

    public void onShow() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_FAILURE, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext.getContext());
    }

    public void retryIdology() {
        this.mRouter.routeToStateRetryPage();
    }

    boolean onBackPressed() {
        this.mRouter.routeToStateRetryPage();
        return true;
    }
}
