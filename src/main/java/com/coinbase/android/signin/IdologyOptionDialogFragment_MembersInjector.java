package com.coinbase.android.signin;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class IdologyOptionDialogFragment_MembersInjector implements MembersInjector<IdologyOptionDialogFragment> {
    private final Provider<IdologyOptionSelectedConnector> mIdologyOptionSelectedConnectorProvider;

    public IdologyOptionDialogFragment_MembersInjector(Provider<IdologyOptionSelectedConnector> mIdologyOptionSelectedConnectorProvider) {
        this.mIdologyOptionSelectedConnectorProvider = mIdologyOptionSelectedConnectorProvider;
    }

    public static MembersInjector<IdologyOptionDialogFragment> create(Provider<IdologyOptionSelectedConnector> mIdologyOptionSelectedConnectorProvider) {
        return new IdologyOptionDialogFragment_MembersInjector(mIdologyOptionSelectedConnectorProvider);
    }

    public void injectMembers(IdologyOptionDialogFragment instance) {
        injectMIdologyOptionSelectedConnector(instance, (IdologyOptionSelectedConnector) this.mIdologyOptionSelectedConnectorProvider.get());
    }

    public static void injectMIdologyOptionSelectedConnector(IdologyOptionDialogFragment instance, IdologyOptionSelectedConnector mIdologyOptionSelectedConnector) {
        instance.mIdologyOptionSelectedConnector = mIdologyOptionSelectedConnector;
    }
}
