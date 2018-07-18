package com.coinbase.android.wbl;

import android.os.Bundle;
import android.text.TextUtils;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;

@ControllerScope
public class WithdrawalBasedLimitsDialogPresenter {
    static final String DISMISS_TEXT = "dismiss_text";
    static final String LEARN_MORE = "learn_more";
    static final String MESSAGE = "message";
    static final String TITLE = "title";
    static final String TRACKING_CONTEXT = "tracking_context";
    private final MixpanelTracking mMixpanelTracking;
    private final WithdrawalBasedLimitsDialogScreen mScreen;
    private LimitExceededTrackingContext mTrackingContext;

    @Inject
    public WithdrawalBasedLimitsDialogPresenter(WithdrawalBasedLimitsDialogScreen screen, MixpanelTracking mixpanelTracking) {
        this.mScreen = screen;
        this.mMixpanelTracking = mixpanelTracking;
    }

    boolean onCreate(Bundle args) {
        if (args == null || TextUtils.isEmpty(args.getString("message", null)) || TextUtils.isEmpty(args.getString(TITLE, null)) || TextUtils.isEmpty(args.getString(LEARN_MORE, null)) || TextUtils.isEmpty(args.getString(DISMISS_TEXT, null))) {
            this.mScreen.dismiss();
            return false;
        }
        this.mTrackingContext = LimitExceededTrackingContext.from(args.getInt(TRACKING_CONTEXT));
        this.mScreen.setTextFields(args.getString(TITLE), args.getString("message"), args.getString(DISMISS_TEXT), args.getString(LEARN_MORE));
        return true;
    }

    void onClickLearnMore() {
        this.mScreen.routeToAvailableBalance();
        trackLearnMore();
    }

    void onClickDismiss() {
        this.mScreen.dismiss();
        trackDismiss();
    }

    private void trackLearnMore() {
        switch (this.mTrackingContext) {
            case SEND:
                this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SEND_EXCEEDED_TAPPED_LEARN_MORE, new String[0]);
                return;
            case WITHDRAW:
                this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_WITHDRAW_EXCEEDED_TAPPED_LEARN_MORE, new String[0]);
                return;
            case SELL:
                this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SELL_EXCEEDED_TAPPED_LEARN_MORE, new String[0]);
                return;
            default:
                return;
        }
    }

    private void trackDismiss() {
        switch (this.mTrackingContext) {
            case SEND:
                this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SEND_EXCEEDED_TAPPED_DISMISS, new String[0]);
                return;
            case WITHDRAW:
                this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_WITHDRAW_EXCEEDED_TAPPED_DISMISS, new String[0]);
                return;
            case SELL:
                this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SELL_EXCEEDED_TAPPED_DISMISS, new String[0]);
                return;
            default:
                return;
        }
    }
}
