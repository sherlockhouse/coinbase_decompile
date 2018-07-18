package com.coinbase.android.signin;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DeviceVerifyPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final DeviceVerifyPresenterModule module;

    public DeviceVerifyPresenterModule_ProvidesControllerFactory(DeviceVerifyPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(DeviceVerifyPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static DeviceVerifyPresenterModule_ProvidesControllerFactory create(DeviceVerifyPresenterModule module) {
        return new DeviceVerifyPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(DeviceVerifyPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
