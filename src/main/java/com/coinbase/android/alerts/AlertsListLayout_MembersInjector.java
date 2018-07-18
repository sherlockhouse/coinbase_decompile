package com.coinbase.android.alerts;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class AlertsListLayout_MembersInjector implements MembersInjector<AlertsListLayout> {
    private final Provider<AlertsListPresenter> mPresenterProvider;

    public AlertsListLayout_MembersInjector(Provider<AlertsListPresenter> mPresenterProvider) {
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<AlertsListLayout> create(Provider<AlertsListPresenter> mPresenterProvider) {
        return new AlertsListLayout_MembersInjector(mPresenterProvider);
    }

    public void injectMembers(AlertsListLayout instance) {
        injectMPresenter(instance, (AlertsListPresenter) this.mPresenterProvider.get());
    }

    public static void injectMPresenter(AlertsListLayout instance, AlertsListPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
