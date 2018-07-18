package com.coinbase.android.deposits;

import com.coinbase.android.FragmentScope;
import com.coinbase.android.R;
import com.coinbase.android.deposits.SepaDepositPresenter.CopyAddress;

public class SepaDepositViewModule {
    @CopyAddress
    @FragmentScope
    int providesCopyAddressRes() {
        return R.string.copy_address;
    }
}
