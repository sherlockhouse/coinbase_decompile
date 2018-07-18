package com.coinbase.android.idology;

import com.coinbase.android.ui.BackgroundDimmer;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class IdologyNameFormLayout_MembersInjector implements MembersInjector<IdologyNameFormLayout> {
    private final Provider<BackgroundDimmer> mBackgroundDimmerProvider;
    private final Provider<IdologyNameFormPresenter> mPresenterProvider;

    public IdologyNameFormLayout_MembersInjector(Provider<IdologyNameFormPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider) {
        this.mPresenterProvider = mPresenterProvider;
        this.mBackgroundDimmerProvider = mBackgroundDimmerProvider;
    }

    public static MembersInjector<IdologyNameFormLayout> create(Provider<IdologyNameFormPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider) {
        return new IdologyNameFormLayout_MembersInjector(mPresenterProvider, mBackgroundDimmerProvider);
    }

    public void injectMembers(IdologyNameFormLayout instance) {
        injectMPresenter(instance, (IdologyNameFormPresenter) this.mPresenterProvider.get());
        injectMBackgroundDimmer(instance, (BackgroundDimmer) this.mBackgroundDimmerProvider.get());
    }

    public static void injectMPresenter(IdologyNameFormLayout instance, IdologyNameFormPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMBackgroundDimmer(IdologyNameFormLayout instance, BackgroundDimmer mBackgroundDimmer) {
        instance.mBackgroundDimmer = mBackgroundDimmer;
    }
}
