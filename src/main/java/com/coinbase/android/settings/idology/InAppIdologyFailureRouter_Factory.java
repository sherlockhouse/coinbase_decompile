package com.coinbase.android.settings.idology;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class InAppIdologyFailureRouter_Factory implements Factory<InAppIdologyFailureRouter> {
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<IdologyArgsBuilder> idologyArgsBuilderProvider;

    public InAppIdologyFailureRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider) {
        this.controllerProvider = controllerProvider;
        this.idologyArgsBuilderProvider = idologyArgsBuilderProvider;
    }

    public InAppIdologyFailureRouter get() {
        return provideInstance(this.controllerProvider, this.idologyArgsBuilderProvider);
    }

    public static InAppIdologyFailureRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider) {
        return new InAppIdologyFailureRouter((ActionBarController) controllerProvider.get(), (IdologyArgsBuilder) idologyArgsBuilderProvider.get());
    }

    public static InAppIdologyFailureRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider) {
        return new InAppIdologyFailureRouter_Factory(controllerProvider, idologyArgsBuilderProvider);
    }

    public static InAppIdologyFailureRouter newInAppIdologyFailureRouter(ActionBarController controller, IdologyArgsBuilder idologyArgsBuilder) {
        return new InAppIdologyFailureRouter(controller, idologyArgsBuilder);
    }
}
