package com.coinbase.android.ui;

import dagger.internal.Factory;

public final class ControllerLifeCycleFactory_Factory implements Factory<ControllerLifeCycleFactory> {
    private static final ControllerLifeCycleFactory_Factory INSTANCE = new ControllerLifeCycleFactory_Factory();

    public ControllerLifeCycleFactory get() {
        return provideInstance();
    }

    public static ControllerLifeCycleFactory provideInstance() {
        return new ControllerLifeCycleFactory();
    }

    public static ControllerLifeCycleFactory_Factory create() {
        return INSTANCE;
    }

    public static ControllerLifeCycleFactory newControllerLifeCycleFactory() {
        return new ControllerLifeCycleFactory();
    }
}
