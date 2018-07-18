package com.coinbase.android.paymentmethods.card;

import dagger.internal.Factory;

public final class WorldPayValidator_Factory implements Factory<WorldPayValidator> {
    private static final WorldPayValidator_Factory INSTANCE = new WorldPayValidator_Factory();

    public WorldPayValidator get() {
        return provideInstance();
    }

    public static WorldPayValidator provideInstance() {
        return new WorldPayValidator();
    }

    public static WorldPayValidator_Factory create() {
        return INSTANCE;
    }

    public static WorldPayValidator newWorldPayValidator() {
        return new WorldPayValidator();
    }
}
