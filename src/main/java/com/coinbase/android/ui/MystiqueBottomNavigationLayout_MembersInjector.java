package com.coinbase.android.ui;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class MystiqueBottomNavigationLayout_MembersInjector implements MembersInjector<MystiqueBottomNavigationLayout> {
    private final Provider<MystiqueBottomNavigationPresenter> mPresenterProvider;

    public MystiqueBottomNavigationLayout_MembersInjector(Provider<MystiqueBottomNavigationPresenter> mPresenterProvider) {
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<MystiqueBottomNavigationLayout> create(Provider<MystiqueBottomNavigationPresenter> mPresenterProvider) {
        return new MystiqueBottomNavigationLayout_MembersInjector(mPresenterProvider);
    }

    public void injectMembers(MystiqueBottomNavigationLayout instance) {
        injectMPresenter(instance, (MystiqueBottomNavigationPresenter) this.mPresenterProvider.get());
    }

    public static void injectMPresenter(MystiqueBottomNavigationLayout instance, MystiqueBottomNavigationPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
