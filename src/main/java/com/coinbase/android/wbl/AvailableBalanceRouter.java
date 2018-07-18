package com.coinbase.android.wbl;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.buysell.SellController;
import com.coinbase.android.deposits.fiat.WithdrawFiatController;
import com.coinbase.android.transfers.SendController;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;

@ControllerScope
public class AvailableBalanceRouter {
    private final ActionBarController mController;
    private final MixpanelTracking mMixpanelTracking;

    @Inject
    public AvailableBalanceRouter(ActionBarController actionBarController, MixpanelTracking mixpanelTracking) {
        this.mController = actionBarController;
        this.mMixpanelTracking = mixpanelTracking;
    }

    void routeShowAvailableBalance(String currency) {
        this.mController.pushModalController(new AvailableBalanceController(this.mController.appendModalArgs(new Bundle())));
        trackAvailableBalanceClicked(currency);
    }

    private void trackAvailableBalanceClicked(String currency) {
        String event = "";
        if (this.mController instanceof SendController) {
            event = MixpanelTracking.EVENT_SEND_TAPPED_AVAILABLE_BALANCE_DETAIL;
        } else if (this.mController instanceof SellController) {
            event = MixpanelTracking.EVENT_SELL_TAPPED_AVAILABLE_BALANCE_DETAIL;
        } else if (this.mController instanceof WithdrawFiatController) {
            event = MixpanelTracking.EVENT_WITHDRAW_TAPPED_AVAILABLE_BALANCE_DETAIL;
        }
        this.mMixpanelTracking.trackEvent(event, "currency", currency);
    }
}
