package com.coinbase.android.signin;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface EmailVerifyControllerSubcomponent {
    void inject(EmailVerifyController emailVerifyController);
}
