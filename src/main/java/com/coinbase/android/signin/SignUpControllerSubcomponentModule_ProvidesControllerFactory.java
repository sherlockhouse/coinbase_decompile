package com.coinbase.android.signin;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SignUpControllerSubcomponentModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final SignUpControllerSubcomponentModule module;

    public SignUpControllerSubcomponentModule_ProvidesControllerFactory(SignUpControllerSubcomponentModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(SignUpControllerSubcomponentModule module) {
        return proxyProvidesController(module);
    }

    public static SignUpControllerSubcomponentModule_ProvidesControllerFactory create(SignUpControllerSubcomponentModule module) {
        return new SignUpControllerSubcomponentModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(SignUpControllerSubcomponentModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
