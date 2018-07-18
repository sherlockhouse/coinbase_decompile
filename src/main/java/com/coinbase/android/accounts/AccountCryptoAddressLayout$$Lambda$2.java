package com.coinbase.android.accounts;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.android.accounts.AccountCryptoAddressButtonConnector.AccountCryptoAddressButtonEvent;

final /* synthetic */ class AccountCryptoAddressLayout$$Lambda$2 implements OnClickListener {
    private final AccountCryptoAddressLayout arg$1;

    private AccountCryptoAddressLayout$$Lambda$2(AccountCryptoAddressLayout accountCryptoAddressLayout) {
        this.arg$1 = accountCryptoAddressLayout;
    }

    public static OnClickListener lambdaFactory$(AccountCryptoAddressLayout accountCryptoAddressLayout) {
        return new AccountCryptoAddressLayout$$Lambda$2(accountCryptoAddressLayout);
    }

    public void onClick(View view) {
        this.arg$1.mAccountCryptoAddressButtonConnector.get().onNext(AccountCryptoAddressButtonEvent.COPY);
    }
}
