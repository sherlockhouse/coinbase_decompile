package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class GdprSettingsRouter_Factory implements Factory<GdprSettingsRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public GdprSettingsRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public GdprSettingsRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static GdprSettingsRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new GdprSettingsRouter((ActionBarController) controllerProvider.get());
    }

    public static GdprSettingsRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new GdprSettingsRouter_Factory(controllerProvider);
    }

    public static GdprSettingsRouter newGdprSettingsRouter(ActionBarController controller) {
        return new GdprSettingsRouter(controller);
    }
}
