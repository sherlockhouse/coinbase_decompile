package com.coinbase.android.accounts;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountCryptoAddressLayout$$Lambda$1 implements OnClickListener {
    private final AccountCryptoAddressLayout arg$1;

    private AccountCryptoAddressLayout$$Lambda$1(AccountCryptoAddressLayout accountCryptoAddressLayout) {
        this.arg$1 = accountCryptoAddressLayout;
    }

    public static OnClickListener lambdaFactory$(AccountCryptoAddressLayout accountCryptoAddressLayout) {
        return new AccountCryptoAddressLayout$$Lambda$1(accountCryptoAddressLayout);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onShowAddressClicked();
    }
}
