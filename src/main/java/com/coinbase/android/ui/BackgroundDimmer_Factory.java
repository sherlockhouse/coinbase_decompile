package com.coinbase.android.ui;

import android.view.Window;
import android.widget.RelativeLayout;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BackgroundDimmer_Factory implements Factory<BackgroundDimmer> {
    private final Provider<BackNavigationConnector> backNavigationConnectorProvider;
    private final Provider<RelativeLayout> blockingOverlayViewProvider;
    private final Provider<StatusBarUpdater> statusBarUpdaterProvider;
    private final Provider<Window> windowProvider;

    public BackgroundDimmer_Factory(Provider<RelativeLayout> blockingOverlayViewProvider, Provider<Window> windowProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<StatusBarUpdater> statusBarUpdaterProvider) {
        this.blockingOverlayViewProvider = blockingOverlayViewProvider;
        this.windowProvider = windowProvider;
        this.backNavigationConnectorProvider = backNavigationConnectorProvider;
        this.statusBarUpdaterProvider = statusBarUpdaterProvider;
    }

    public BackgroundDimmer get() {
        return provideInstance(this.blockingOverlayViewProvider, this.windowProvider, this.backNavigationConnectorProvider, this.statusBarUpdaterProvider);
    }

    public static BackgroundDimmer provideInstance(Provider<RelativeLayout> blockingOverlayViewProvider, Provider<Window> windowProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<StatusBarUpdater> statusBarUpdaterProvider) {
        return new BackgroundDimmer((RelativeLayout) blockingOverlayViewProvider.get(), (Window) windowProvider.get(), (BackNavigationConnector) backNavigationConnectorProvider.get(), (StatusBarUpdater) statusBarUpdaterProvider.get());
    }

    public static BackgroundDimmer_Factory create(Provider<RelativeLayout> blockingOverlayViewProvider, Provider<Window> windowProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<StatusBarUpdater> statusBarUpdaterProvider) {
        return new BackgroundDimmer_Factory(blockingOverlayViewProvider, windowProvider, backNavigationConnectorProvider, statusBarUpdaterProvider);
    }

    public static BackgroundDimmer newBackgroundDimmer(RelativeLayout blockingOverlayView, Window window, BackNavigationConnector backNavigationConnector, StatusBarUpdater statusBarUpdater) {
        return new BackgroundDimmer(blockingOverlayView, window, backNavigationConnector, statusBarUpdater);
    }
}
