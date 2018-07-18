package com.coinbase.android.accounts;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.v2.models.account.Data;

final /* synthetic */ class AccountListAdapter$$Lambda$1 implements OnClickListener {
    private final AccountListAdapter arg$1;
    private final Data arg$2;

    private AccountListAdapter$$Lambda$1(AccountListAdapter accountListAdapter, Data data) {
        this.arg$1 = accountListAdapter;
        this.arg$2 = data;
    }

    public static OnClickListener lambdaFactory$(AccountListAdapter accountListAdapter, Data data) {
        return new AccountListAdapter$$Lambda$1(accountListAdapter, data);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onAccountItemClicked(this.arg$2);
    }
}
