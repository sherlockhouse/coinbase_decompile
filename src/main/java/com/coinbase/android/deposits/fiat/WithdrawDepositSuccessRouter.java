package com.coinbase.android.deposits.fiat;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.accounts.AccountTransactionsController;
import com.coinbase.android.accounts.AccountTransactionsPresenter;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import com.coinbase.v2.models.account.Data;
import com.google.gson.Gson;
import javax.inject.Inject;

@ControllerScope
public class WithdrawDepositSuccessRouter {
    private final BottomNavigationConnector mBottomNavigationConnector;
    private final ActionBarController mController;

    @Inject
    public WithdrawDepositSuccessRouter(ActionBarController actionBarController, BottomNavigationConnector bottomNavigationConnector) {
        this.mController = actionBarController;
        this.mBottomNavigationConnector = bottomNavigationConnector;
    }

    void routeBackToAccount(Data account) {
        Bundle args = new Bundle();
        args.putString(AccountTransactionsPresenter.ACCOUNT_DATA, new Gson().toJson((Object) account));
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(((PageDestination) this.mBottomNavigationConnector.get().getValue()).getBottomNavigationItem()).setBottomNavigationItem(Type.ACCOUNTS).setPushPageController(new AccountTransactionsController(this.mController.appendPageArgs(args))).build());
    }
}
