package com.coinbase.android.accounts;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountCryptoAddressLayout$$Lambda$3 implements OnClickListener {
    private final AccountCryptoAddressLayout arg$1;

    private AccountCryptoAddressLayout$$Lambda$3(AccountCryptoAddressLayout accountCryptoAddressLayout) {
        this.arg$1 = accountCryptoAddressLayout;
    }

    public static OnClickListener lambdaFactory$(AccountCryptoAddressLayout accountCryptoAddressLayout) {
        return new AccountCryptoAddressLayout$$Lambda$3(accountCryptoAddressLayout);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onHelpButtonClicked();
    }
}
