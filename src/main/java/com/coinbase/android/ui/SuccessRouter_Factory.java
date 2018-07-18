package com.coinbase.android.ui;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class SuccessRouter_Factory implements Factory<SuccessRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public SuccessRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public SuccessRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static SuccessRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new SuccessRouter((ActionBarController) controllerProvider.get());
    }

    public static SuccessRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new SuccessRouter_Factory(controllerProvider);
    }

    public static SuccessRouter newSuccessRouter(ActionBarController controller) {
        return new SuccessRouter(controller);
    }
}
