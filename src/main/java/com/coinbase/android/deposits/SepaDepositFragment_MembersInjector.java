package com.coinbase.android.deposits;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class SepaDepositFragment_MembersInjector implements MembersInjector<SepaDepositFragment> {
    private final Provider<SepaDepositPresenter> mPresenterProvider;

    public SepaDepositFragment_MembersInjector(Provider<SepaDepositPresenter> mPresenterProvider) {
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<SepaDepositFragment> create(Provider<SepaDepositPresenter> mPresenterProvider) {
        return new SepaDepositFragment_MembersInjector(mPresenterProvider);
    }

    public void injectMembers(SepaDepositFragment instance) {
        injectMPresenter(instance, (SepaDepositPresenter) this.mPresenterProvider.get());
    }

    public static void injectMPresenter(SepaDepositFragment instance, SepaDepositPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
