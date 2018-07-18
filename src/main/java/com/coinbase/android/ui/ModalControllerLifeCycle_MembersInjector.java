package com.coinbase.android.ui;

import android.view.ViewGroup;
import com.coinbase.android.ScreenProtector;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.functions.Func0;

public final class ModalControllerLifeCycle_MembersInjector implements MembersInjector<ModalControllerLifeCycle> {
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<ScreenProtector> mScreenProtectorProvider;

    public ModalControllerLifeCycle_MembersInjector(Provider<ScreenProtector> mScreenProtectorProvider, Provider<Func0<ViewGroup>> mModalViewProvider) {
        this.mScreenProtectorProvider = mScreenProtectorProvider;
        this.mModalViewProvider = mModalViewProvider;
    }

    public static MembersInjector<ModalControllerLifeCycle> create(Provider<ScreenProtector> mScreenProtectorProvider, Provider<Func0<ViewGroup>> mModalViewProvider) {
        return new ModalControllerLifeCycle_MembersInjector(mScreenProtectorProvider, mModalViewProvider);
    }

    public void injectMembers(ModalControllerLifeCycle instance) {
        injectMScreenProtector(instance, (ScreenProtector) this.mScreenProtectorProvider.get());
        injectMModalView(instance, (Func0) this.mModalViewProvider.get());
    }

    public static void injectMScreenProtector(ModalControllerLifeCycle instance, ScreenProtector mScreenProtector) {
        instance.mScreenProtector = mScreenProtector;
    }

    public static void injectMModalView(ModalControllerLifeCycle instance, Func0<ViewGroup> mModalView) {
        instance.mModalView = mModalView;
    }
}
