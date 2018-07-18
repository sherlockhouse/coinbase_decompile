package com.coinbase.android.ui;

import android.view.Window;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StatusBarUpdater_Factory implements Factory<StatusBarUpdater> {
    private final Provider<Window> windowProvider;

    public StatusBarUpdater_Factory(Provider<Window> windowProvider) {
        this.windowProvider = windowProvider;
    }

    public StatusBarUpdater get() {
        return provideInstance(this.windowProvider);
    }

    public static StatusBarUpdater provideInstance(Provider<Window> windowProvider) {
        return new StatusBarUpdater((Window) windowProvider.get());
    }

    public static StatusBarUpdater_Factory create(Provider<Window> windowProvider) {
        return new StatusBarUpdater_Factory(windowProvider);
    }

    public static StatusBarUpdater newStatusBarUpdater(Window window) {
        return new StatusBarUpdater(window);
    }
}
