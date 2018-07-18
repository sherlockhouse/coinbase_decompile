package com.coinbase.android.accounts;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class AccountRefreshListLayout$$Lambda$1 implements OnRefreshListener {
    private final AccountRefreshListLayout arg$1;

    private AccountRefreshListLayout$$Lambda$1(AccountRefreshListLayout accountRefreshListLayout) {
        this.arg$1 = accountRefreshListLayout;
    }

    public static OnRefreshListener lambdaFactory$(AccountRefreshListLayout accountRefreshListLayout) {
        return new AccountRefreshListLayout$$Lambda$1(accountRefreshListLayout);
    }

    public void onRefresh() {
        this.arg$1.mPresenter.refreshAccountList();
    }
}
