package com.coinbase.android.ui;

import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class MainControllerPresenter_Factory implements Factory<MainControllerPresenter> {
    private final Provider<BackNavigationConnector> backNavigationConnectorProvider;
    private final Provider<BottomNavigationConnector> bottomNavigationConnectorProvider;
    private final Provider<KeyboardListener> keyboardListenerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MainScreen> mainScreenProvider;

    public MainControllerPresenter_Factory(Provider<MainScreen> mainScreenProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<KeyboardListener> keyboardListenerProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.mainScreenProvider = mainScreenProvider;
        this.bottomNavigationConnectorProvider = bottomNavigationConnectorProvider;
        this.backNavigationConnectorProvider = backNavigationConnectorProvider;
        this.keyboardListenerProvider = keyboardListenerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public MainControllerPresenter get() {
        return provideInstance(this.mainScreenProvider, this.bottomNavigationConnectorProvider, this.backNavigationConnectorProvider, this.keyboardListenerProvider, this.mainSchedulerProvider);
    }

    public static MainControllerPresenter provideInstance(Provider<MainScreen> mainScreenProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<KeyboardListener> keyboardListenerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new MainControllerPresenter((MainScreen) mainScreenProvider.get(), (BottomNavigationConnector) bottomNavigationConnectorProvider.get(), (BackNavigationConnector) backNavigationConnectorProvider.get(), (KeyboardListener) keyboardListenerProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static MainControllerPresenter_Factory create(Provider<MainScreen> mainScreenProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<KeyboardListener> keyboardListenerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new MainControllerPresenter_Factory(mainScreenProvider, bottomNavigationConnectorProvider, backNavigationConnectorProvider, keyboardListenerProvider, mainSchedulerProvider);
    }

    public static MainControllerPresenter newMainControllerPresenter(MainScreen mainScreen, BottomNavigationConnector bottomNavigationConnector, BackNavigationConnector backNavigationConnector, KeyboardListener keyboardListener, Scheduler mainScheduler) {
        return new MainControllerPresenter(mainScreen, bottomNavigationConnector, backNavigationConnector, keyboardListener, mainScheduler);
    }
}
