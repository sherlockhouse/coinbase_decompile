package com.coinbase.android.signin;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface TwoFactorControllerSubcomponent {
    void inject(TwoFactorController twoFactorController);
}
