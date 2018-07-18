package com.coinbase.android.wbl;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ExistingUserDialogPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final ExistingUserDialogPresenterModule module;

    public ExistingUserDialogPresenterModule_ProvidesControllerFactory(ExistingUserDialogPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(ExistingUserDialogPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static ExistingUserDialogPresenterModule_ProvidesControllerFactory create(ExistingUserDialogPresenterModule module) {
        return new ExistingUserDialogPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(ExistingUserDialogPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
