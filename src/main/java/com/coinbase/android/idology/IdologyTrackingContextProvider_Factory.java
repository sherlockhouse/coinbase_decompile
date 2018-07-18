package com.coinbase.android.idology;

import android.app.Application;
import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class IdologyTrackingContextProvider_Factory implements Factory<IdologyTrackingContextProvider> {
    private final Provider<Application> appProvider;
    private final Provider<ActionBarController> controllerProvider;

    public IdologyTrackingContextProvider_Factory(Provider<ActionBarController> controllerProvider, Provider<Application> appProvider) {
        this.controllerProvider = controllerProvider;
        this.appProvider = appProvider;
    }

    public IdologyTrackingContextProvider get() {
        return provideInstance(this.controllerProvider, this.appProvider);
    }

    public static IdologyTrackingContextProvider provideInstance(Provider<ActionBarController> controllerProvider, Provider<Application> appProvider) {
        return new IdologyTrackingContextProvider((ActionBarController) controllerProvider.get(), (Application) appProvider.get());
    }

    public static IdologyTrackingContextProvider_Factory create(Provider<ActionBarController> controllerProvider, Provider<Application> appProvider) {
        return new IdologyTrackingContextProvider_Factory(controllerProvider, appProvider);
    }

    public static IdologyTrackingContextProvider newIdologyTrackingContextProvider(ActionBarController controller, Application app) {
        return new IdologyTrackingContextProvider(controller, app);
    }
}
