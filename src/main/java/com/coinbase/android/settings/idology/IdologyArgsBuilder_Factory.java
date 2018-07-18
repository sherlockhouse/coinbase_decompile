package com.coinbase.android.settings.idology;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class IdologyArgsBuilder_Factory implements Factory<IdologyArgsBuilder> {
    private final Provider<ActionBarController> controllerProvider;

    public IdologyArgsBuilder_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public IdologyArgsBuilder get() {
        return provideInstance(this.controllerProvider);
    }

    public static IdologyArgsBuilder provideInstance(Provider<ActionBarController> controllerProvider) {
        return new IdologyArgsBuilder((ActionBarController) controllerProvider.get());
    }

    public static IdologyArgsBuilder_Factory create(Provider<ActionBarController> controllerProvider) {
        return new IdologyArgsBuilder_Factory(controllerProvider);
    }

    public static IdologyArgsBuilder newIdologyArgsBuilder(ActionBarController controller) {
        return new IdologyArgsBuilder(controller);
    }
}
