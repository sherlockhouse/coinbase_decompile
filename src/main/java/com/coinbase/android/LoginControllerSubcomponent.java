package com.coinbase.android;

import com.coinbase.android.signin.LoginController;

@ControllerScope
public interface LoginControllerSubcomponent {
    void inject(LoginController loginController);
}
