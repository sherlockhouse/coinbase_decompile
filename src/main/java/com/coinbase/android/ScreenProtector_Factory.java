package com.coinbase.android;

import android.view.Window;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ScreenProtector_Factory implements Factory<ScreenProtector> {
    private final Provider<Window> windowProvider;

    public ScreenProtector_Factory(Provider<Window> windowProvider) {
        this.windowProvider = windowProvider;
    }

    public ScreenProtector get() {
        return provideInstance(this.windowProvider);
    }

    public static ScreenProtector provideInstance(Provider<Window> windowProvider) {
        return new ScreenProtector((Window) windowProvider.get());
    }

    public static ScreenProtector_Factory create(Provider<Window> windowProvider) {
        return new ScreenProtector_Factory(windowProvider);
    }

    public static ScreenProtector newScreenProtector(Window window) {
        return new ScreenProtector(window);
    }
}
