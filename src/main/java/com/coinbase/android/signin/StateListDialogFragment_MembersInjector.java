package com.coinbase.android.signin;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class StateListDialogFragment_MembersInjector implements MembersInjector<StateListDialogFragment> {
    private final Provider<StateListSelectorConnector> mConnectorProvider;

    public StateListDialogFragment_MembersInjector(Provider<StateListSelectorConnector> mConnectorProvider) {
        this.mConnectorProvider = mConnectorProvider;
    }

    public static MembersInjector<StateListDialogFragment> create(Provider<StateListSelectorConnector> mConnectorProvider) {
        return new StateListDialogFragment_MembersInjector(mConnectorProvider);
    }

    public void injectMembers(StateListDialogFragment instance) {
        injectMConnector(instance, (StateListSelectorConnector) this.mConnectorProvider.get());
    }

    public static void injectMConnector(StateListDialogFragment instance, StateListSelectorConnector mConnector) {
        instance.mConnector = mConnector;
    }
}
