package com.coinbase.android.phone;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class PhoneNumberPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final PhoneNumberPresenterModule module;

    public PhoneNumberPresenterModule_ProvidesActionBarControllerFactory(PhoneNumberPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(PhoneNumberPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static PhoneNumberPresenterModule_ProvidesActionBarControllerFactory create(PhoneNumberPresenterModule module) {
        return new PhoneNumberPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(PhoneNumberPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
