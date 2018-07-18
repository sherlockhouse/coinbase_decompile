package com.coinbase.android.signin;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SignInPhoneNumberPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final SignInPhoneNumberPresenterModule module;

    public SignInPhoneNumberPresenterModule_ProvidesActionBarControllerFactory(SignInPhoneNumberPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(SignInPhoneNumberPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static SignInPhoneNumberPresenterModule_ProvidesActionBarControllerFactory create(SignInPhoneNumberPresenterModule module) {
        return new SignInPhoneNumberPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(SignInPhoneNumberPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
