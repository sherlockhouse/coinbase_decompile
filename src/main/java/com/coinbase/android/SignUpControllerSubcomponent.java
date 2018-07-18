package com.coinbase.android;

import com.coinbase.android.signin.SignUpController;

@ControllerScope
public interface SignUpControllerSubcomponent {
    void inject(SignUpController signUpController);
}
