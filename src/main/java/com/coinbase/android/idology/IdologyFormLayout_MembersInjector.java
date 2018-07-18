package com.coinbase.android.idology;

import com.coinbase.android.ui.BackgroundDimmer;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class IdologyFormLayout_MembersInjector implements MembersInjector<IdologyFormLayout> {
    private final Provider<BackgroundDimmer> mBackgroundDimmerProvider;
    private final Provider<IdologyFormPresenter> mPresenterProvider;

    public IdologyFormLayout_MembersInjector(Provider<IdologyFormPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider) {
        this.mPresenterProvider = mPresenterProvider;
        this.mBackgroundDimmerProvider = mBackgroundDimmerProvider;
    }

    public static MembersInjector<IdologyFormLayout> create(Provider<IdologyFormPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider) {
        return new IdologyFormLayout_MembersInjector(mPresenterProvider, mBackgroundDimmerProvider);
    }

    public void injectMembers(IdologyFormLayout instance) {
        injectMPresenter(instance, (IdologyFormPresenter) this.mPresenterProvider.get());
        injectMBackgroundDimmer(instance, (BackgroundDimmer) this.mBackgroundDimmerProvider.get());
    }

    public static void injectMPresenter(IdologyFormLayout instance, IdologyFormPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMBackgroundDimmer(IdologyFormLayout instance, BackgroundDimmer mBackgroundDimmer) {
        instance.mBackgroundDimmer = mBackgroundDimmer;
    }
}
