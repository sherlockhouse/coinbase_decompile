package com.coinbase.android.ui;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class CurrencyTabLayout_MembersInjector implements MembersInjector<CurrencyTabLayout> {
    private final Provider<CurrencyTabPresenter> mPresenterProvider;

    public CurrencyTabLayout_MembersInjector(Provider<CurrencyTabPresenter> mPresenterProvider) {
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<CurrencyTabLayout> create(Provider<CurrencyTabPresenter> mPresenterProvider) {
        return new CurrencyTabLayout_MembersInjector(mPresenterProvider);
    }

    public void injectMembers(CurrencyTabLayout instance) {
        injectMPresenter(instance, (CurrencyTabPresenter) this.mPresenterProvider.get());
    }

    public static void injectMPresenter(CurrencyTabLayout instance, CurrencyTabPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
