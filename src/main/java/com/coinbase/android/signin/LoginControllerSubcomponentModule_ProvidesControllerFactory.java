package com.coinbase.android.signin;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class LoginControllerSubcomponentModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final LoginControllerSubcomponentModule module;

    public LoginControllerSubcomponentModule_ProvidesControllerFactory(LoginControllerSubcomponentModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(LoginControllerSubcomponentModule module) {
        return proxyProvidesController(module);
    }

    public static LoginControllerSubcomponentModule_ProvidesControllerFactory create(LoginControllerSubcomponentModule module) {
        return new LoginControllerSubcomponentModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(LoginControllerSubcomponentModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
