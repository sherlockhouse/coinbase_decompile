package com.coinbase.android.billing;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AddBillingAddressFragment_MembersInjector implements MembersInjector<AddBillingAddressFragment> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<AddBillingAddressPresenter> mPresenterProvider;

    public AddBillingAddressFragment_MembersInjector(Provider<LoginManager> loginManagerProvider, Provider<AddBillingAddressPresenter> mPresenterProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<AddBillingAddressFragment> create(Provider<LoginManager> loginManagerProvider, Provider<AddBillingAddressPresenter> mPresenterProvider) {
        return new AddBillingAddressFragment_MembersInjector(loginManagerProvider, mPresenterProvider);
    }

    public void injectMembers(AddBillingAddressFragment instance) {
        injectLoginManager(instance, (LoginManager) this.loginManagerProvider.get());
        injectMPresenter(instance, (AddBillingAddressPresenter) this.mPresenterProvider.get());
    }

    public static void injectLoginManager(AddBillingAddressFragment instance, LoginManager loginManager) {
        instance.loginManager = loginManager;
    }

    public static void injectMPresenter(AddBillingAddressFragment instance, AddBillingAddressPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
