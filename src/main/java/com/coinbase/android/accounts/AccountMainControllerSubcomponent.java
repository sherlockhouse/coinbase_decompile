package com.coinbase.android.accounts;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface AccountMainControllerSubcomponent {
    void inject(AccountMainController accountMainController);
}
