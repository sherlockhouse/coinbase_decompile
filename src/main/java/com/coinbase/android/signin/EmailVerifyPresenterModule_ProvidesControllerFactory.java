package com.coinbase.android.signin;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class EmailVerifyPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final EmailVerifyPresenterModule module;

    public EmailVerifyPresenterModule_ProvidesControllerFactory(EmailVerifyPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(EmailVerifyPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static EmailVerifyPresenterModule_ProvidesControllerFactory create(EmailVerifyPresenterModule module) {
        return new EmailVerifyPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(EmailVerifyPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
