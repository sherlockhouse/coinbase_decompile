package com.coinbase.android.idology;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class IdologyAddressFormLayout_MembersInjector implements MembersInjector<IdologyAddressFormLayout> {
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<IdologyAddressFormPresenter> mPresenterProvider;

    public IdologyAddressFormLayout_MembersInjector(Provider<IdologyAddressFormPresenter> mPresenterProvider, Provider<LoginManager> mLoginManagerProvider) {
        this.mPresenterProvider = mPresenterProvider;
        this.mLoginManagerProvider = mLoginManagerProvider;
    }

    public static MembersInjector<IdologyAddressFormLayout> create(Provider<IdologyAddressFormPresenter> mPresenterProvider, Provider<LoginManager> mLoginManagerProvider) {
        return new IdologyAddressFormLayout_MembersInjector(mPresenterProvider, mLoginManagerProvider);
    }

    public void injectMembers(IdologyAddressFormLayout instance) {
        injectMPresenter(instance, (IdologyAddressFormPresenter) this.mPresenterProvider.get());
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
    }

    public static void injectMPresenter(IdologyAddressFormLayout instance, IdologyAddressFormPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMLoginManager(IdologyAddressFormLayout instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }
}
