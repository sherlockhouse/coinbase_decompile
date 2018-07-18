package com.coinbase.android.signin;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SignInVerifyPhoneNumberPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final SignInVerifyPhoneNumberPresenterModule module;

    public SignInVerifyPhoneNumberPresenterModule_ProvidesActionBarControllerFactory(SignInVerifyPhoneNumberPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(SignInVerifyPhoneNumberPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static SignInVerifyPhoneNumberPresenterModule_ProvidesActionBarControllerFactory create(SignInVerifyPhoneNumberPresenterModule module) {
        return new SignInVerifyPhoneNumberPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(SignInVerifyPhoneNumberPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
