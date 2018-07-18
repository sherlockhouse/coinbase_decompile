package com.coinbase.android.signin;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DeviceVerifyRouter_Factory implements Factory<DeviceVerifyRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public DeviceVerifyRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public DeviceVerifyRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static DeviceVerifyRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new DeviceVerifyRouter((ActionBarController) controllerProvider.get());
    }

    public static DeviceVerifyRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new DeviceVerifyRouter_Factory(controllerProvider);
    }

    public static DeviceVerifyRouter newDeviceVerifyRouter(ActionBarController controller) {
        return new DeviceVerifyRouter(controller);
    }
}
