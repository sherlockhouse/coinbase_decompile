package com.coinbase.android.accounts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.deposits.fiat.DepositFiatController;
import com.coinbase.android.deposits.fiat.DepositFiatPresenter;
import com.coinbase.android.deposits.fiat.WithdrawFiatController;
import com.coinbase.android.deposits.fiat.WithdrawFiatPresenter;
import com.coinbase.android.settings.tiers.InvestmentTiersSettingsController;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.transfers.SendController;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.v2.models.account.Data;
import com.google.gson.Gson;
import javax.inject.Inject;

@ControllerScope
public class AccountTransactionsRouter {
    private final AppCompatActivity mActivity;
    private final ActionBarController mController;
    private final SplitTesting mSplitTesting;

    @Inject
    public AccountTransactionsRouter(AppCompatActivity activity, ActionBarController controller, SplitTesting splitTesting) {
        this.mActivity = activity;
        this.mController = controller;
        this.mSplitTesting = splitTesting;
    }

    public void routeShowSend(Data selectedAccount) {
        Bundle bundle = new Bundle();
        bundle.putString("selected_account", new Gson().toJson((Object) selectedAccount));
        bundle.putInt(ActionBarController.OVERIDDEN_UP_INDICATOR, R.drawable.modal_close_gray);
        this.mController.pushModalController(new SendController(this.mController.appendModalArgsWithRoot(bundle)));
    }

    public void routeToInvestmentTiers() {
        this.mController.pushModalController(new InvestmentTiersSettingsController(this.mController.appendModalArgs(new Bundle())));
    }

    void routeToWithdrawModal(Data account) {
        Bundle args = new Bundle();
        args.putString(WithdrawFiatPresenter.WITHDRAW_FIAT_ACCOUNT, new Gson().toJson((Object) account));
        args.putInt(ActionBarController.OVERIDDEN_UP_INDICATOR, R.drawable.modal_close_gray);
        this.mController.pushModalController(new WithdrawFiatController(this.mController.appendModalArgs(args)));
    }

    void routeToDepositModal(Data account) {
        Bundle args = new Bundle();
        args.putString(DepositFiatPresenter.DEPOSIT_FIAT_ACCOUNT, new Gson().toJson((Object) account));
        args.putInt(ActionBarController.OVERIDDEN_UP_INDICATOR, R.drawable.modal_close_gray);
        this.mController.pushModalController(new DepositFiatController(this.mController.appendModalArgs(args)));
    }
}
