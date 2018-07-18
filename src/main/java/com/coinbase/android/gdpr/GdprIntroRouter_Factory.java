package com.coinbase.android.gdpr;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class GdprIntroRouter_Factory implements Factory<GdprIntroRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public GdprIntroRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public GdprIntroRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static GdprIntroRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new GdprIntroRouter((ActionBarController) controllerProvider.get());
    }

    public static GdprIntroRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new GdprIntroRouter_Factory(controllerProvider);
    }

    public static GdprIntroRouter newGdprIntroRouter(ActionBarController controller) {
        return new GdprIntroRouter(controller);
    }
}
