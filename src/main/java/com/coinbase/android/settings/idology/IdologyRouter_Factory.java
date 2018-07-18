package com.coinbase.android.settings.idology;

import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BackNavigationConnector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class IdologyRouter_Factory implements Factory<IdologyRouter> {
    private final Provider<BackNavigationConnector> backNavigationConnectorProvider;
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<IdologyArgsBuilder> idologyArgsBuilderProvider;

    public IdologyRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider) {
        this.controllerProvider = controllerProvider;
        this.backNavigationConnectorProvider = backNavigationConnectorProvider;
        this.idologyArgsBuilderProvider = idologyArgsBuilderProvider;
    }

    public IdologyRouter get() {
        return provideInstance(this.controllerProvider, this.backNavigationConnectorProvider, this.idologyArgsBuilderProvider);
    }

    public static IdologyRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider) {
        return new IdologyRouter((ActionBarController) controllerProvider.get(), (BackNavigationConnector) backNavigationConnectorProvider.get(), (IdologyArgsBuilder) idologyArgsBuilderProvider.get());
    }

    public static IdologyRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider, Provider<IdologyArgsBuilder> idologyArgsBuilderProvider) {
        return new IdologyRouter_Factory(controllerProvider, backNavigationConnectorProvider, idologyArgsBuilderProvider);
    }

    public static IdologyRouter newIdologyRouter(ActionBarController controller, BackNavigationConnector backNavigationConnector, IdologyArgsBuilder idologyArgsBuilder) {
        return new IdologyRouter(controller, backNavigationConnector, idologyArgsBuilder);
    }
}
