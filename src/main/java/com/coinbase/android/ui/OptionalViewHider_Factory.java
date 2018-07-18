package com.coinbase.android.ui;

import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class OptionalViewHider_Factory implements Factory<OptionalViewHider> {
    private final Provider<KeyboardListener> keyboardListenerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;

    public OptionalViewHider_Factory(Provider<KeyboardListener> keyboardListenerProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.keyboardListenerProvider = keyboardListenerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public OptionalViewHider get() {
        return provideInstance(this.keyboardListenerProvider, this.mainSchedulerProvider);
    }

    public static OptionalViewHider provideInstance(Provider<KeyboardListener> keyboardListenerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new OptionalViewHider((KeyboardListener) keyboardListenerProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static OptionalViewHider_Factory create(Provider<KeyboardListener> keyboardListenerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new OptionalViewHider_Factory(keyboardListenerProvider, mainSchedulerProvider);
    }

    public static OptionalViewHider newOptionalViewHider(KeyboardListener keyboardListener, Scheduler mainScheduler) {
        return new OptionalViewHider(keyboardListener, mainScheduler);
    }
}
