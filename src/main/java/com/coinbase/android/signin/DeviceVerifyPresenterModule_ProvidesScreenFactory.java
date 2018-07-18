package com.coinbase.android.signin;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DeviceVerifyPresenterModule_ProvidesScreenFactory implements Factory<DeviceVerifyScreen> {
    private final DeviceVerifyPresenterModule module;

    public DeviceVerifyPresenterModule_ProvidesScreenFactory(DeviceVerifyPresenterModule module) {
        this.module = module;
    }

    public DeviceVerifyScreen get() {
        return provideInstance(this.module);
    }

    public static DeviceVerifyScreen provideInstance(DeviceVerifyPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static DeviceVerifyPresenterModule_ProvidesScreenFactory create(DeviceVerifyPresenterModule module) {
        return new DeviceVerifyPresenterModule_ProvidesScreenFactory(module);
    }

    public static DeviceVerifyScreen proxyProvidesScreen(DeviceVerifyPresenterModule instance) {
        return (DeviceVerifyScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
