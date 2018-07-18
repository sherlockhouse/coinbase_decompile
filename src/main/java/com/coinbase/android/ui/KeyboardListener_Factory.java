package com.coinbase.android.ui;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class KeyboardListener_Factory implements Factory<KeyboardListener> {
    private final Provider<RootViewWrapper> rootViewWrapperProvider;

    public KeyboardListener_Factory(Provider<RootViewWrapper> rootViewWrapperProvider) {
        this.rootViewWrapperProvider = rootViewWrapperProvider;
    }

    public KeyboardListener get() {
        return provideInstance(this.rootViewWrapperProvider);
    }

    public static KeyboardListener provideInstance(Provider<RootViewWrapper> rootViewWrapperProvider) {
        return new KeyboardListener((RootViewWrapper) rootViewWrapperProvider.get());
    }

    public static KeyboardListener_Factory create(Provider<RootViewWrapper> rootViewWrapperProvider) {
        return new KeyboardListener_Factory(rootViewWrapperProvider);
    }

    public static KeyboardListener newKeyboardListener(RootViewWrapper rootViewWrapper) {
        return new KeyboardListener(rootViewWrapper);
    }
}
