package com.coinbase.android.ui;

import android.support.v7.widget.Toolbar;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ThemeUpdater_Factory implements Factory<ThemeUpdater> {
    private final Provider<Integer> animationDurationProvider;
    private final Provider<StatusBarUpdater> statusBarUpdaterProvider;
    private final Provider<Toolbar> toolbarProvider;

    public ThemeUpdater_Factory(Provider<StatusBarUpdater> statusBarUpdaterProvider, Provider<Toolbar> toolbarProvider, Provider<Integer> animationDurationProvider) {
        this.statusBarUpdaterProvider = statusBarUpdaterProvider;
        this.toolbarProvider = toolbarProvider;
        this.animationDurationProvider = animationDurationProvider;
    }

    public ThemeUpdater get() {
        return provideInstance(this.statusBarUpdaterProvider, this.toolbarProvider, this.animationDurationProvider);
    }

    public static ThemeUpdater provideInstance(Provider<StatusBarUpdater> statusBarUpdaterProvider, Provider<Toolbar> toolbarProvider, Provider<Integer> animationDurationProvider) {
        return new ThemeUpdater((StatusBarUpdater) statusBarUpdaterProvider.get(), (Toolbar) toolbarProvider.get(), ((Integer) animationDurationProvider.get()).intValue());
    }

    public static ThemeUpdater_Factory create(Provider<StatusBarUpdater> statusBarUpdaterProvider, Provider<Toolbar> toolbarProvider, Provider<Integer> animationDurationProvider) {
        return new ThemeUpdater_Factory(statusBarUpdaterProvider, toolbarProvider, animationDurationProvider);
    }

    public static ThemeUpdater newThemeUpdater(StatusBarUpdater statusBarUpdater, Toolbar toolbar, int animationDuration) {
        return new ThemeUpdater(statusBarUpdater, toolbar, animationDuration);
    }
}
