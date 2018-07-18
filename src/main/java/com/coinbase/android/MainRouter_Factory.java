package com.coinbase.android;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class MainRouter_Factory implements Factory<MainRouter> {
    private final Provider<MainActivity> activityProvider;

    public MainRouter_Factory(Provider<MainActivity> activityProvider) {
        this.activityProvider = activityProvider;
    }

    public MainRouter get() {
        return provideInstance(this.activityProvider);
    }

    public static MainRouter provideInstance(Provider<MainActivity> activityProvider) {
        return new MainRouter((MainActivity) activityProvider.get());
    }

    public static MainRouter_Factory create(Provider<MainActivity> activityProvider) {
        return new MainRouter_Factory(activityProvider);
    }

    public static MainRouter newMainRouter(MainActivity activity) {
        return new MainRouter(activity);
    }
}
