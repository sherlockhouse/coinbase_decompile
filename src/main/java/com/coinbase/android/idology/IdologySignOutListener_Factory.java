package com.coinbase.android.idology;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class IdologySignOutListener_Factory implements Factory<IdologySignOutListener> {
    private final Provider<IdologyUtils> idologyUtilsProvider;

    public IdologySignOutListener_Factory(Provider<IdologyUtils> idologyUtilsProvider) {
        this.idologyUtilsProvider = idologyUtilsProvider;
    }

    public IdologySignOutListener get() {
        return provideInstance(this.idologyUtilsProvider);
    }

    public static IdologySignOutListener provideInstance(Provider<IdologyUtils> idologyUtilsProvider) {
        return new IdologySignOutListener((IdologyUtils) idologyUtilsProvider.get());
    }

    public static IdologySignOutListener_Factory create(Provider<IdologyUtils> idologyUtilsProvider) {
        return new IdologySignOutListener_Factory(idologyUtilsProvider);
    }

    public static IdologySignOutListener newIdologySignOutListener(IdologyUtils idologyUtils) {
        return new IdologySignOutListener(idologyUtils);
    }
}
