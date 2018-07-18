package com.coinbase.android.ui;

import android.view.ViewGroup;
import android.view.Window;
import com.coinbase.android.ScreenProtector;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class PageControllerLifeCycle_MembersInjector implements MembersInjector<PageControllerLifeCycle> {
    private final Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<ScreenProtector> mScreenProtectorProvider;
    private final Provider<Window> mWindowProvider;

    public PageControllerLifeCycle_MembersInjector(Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider, Provider<ScreenProtector> mScreenProtectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<Window> mWindowProvider) {
        this.mBottomNavigationConnectorProvider = mBottomNavigationConnectorProvider;
        this.mScreenProtectorProvider = mScreenProtectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mWindowProvider = mWindowProvider;
    }

    public static MembersInjector<PageControllerLifeCycle> create(Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider, Provider<ScreenProtector> mScreenProtectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<Window> mWindowProvider) {
        return new PageControllerLifeCycle_MembersInjector(mBottomNavigationConnectorProvider, mScreenProtectorProvider, mMainSchedulerProvider, mModalViewProvider, mWindowProvider);
    }

    public void injectMembers(PageControllerLifeCycle instance) {
        injectMBottomNavigationConnector(instance, (BottomNavigationConnector) this.mBottomNavigationConnectorProvider.get());
        injectMScreenProtector(instance, (ScreenProtector) this.mScreenProtectorProvider.get());
        injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        injectMWindow(instance, (Window) this.mWindowProvider.get());
    }

    public static void injectMBottomNavigationConnector(PageControllerLifeCycle instance, BottomNavigationConnector mBottomNavigationConnector) {
        instance.mBottomNavigationConnector = mBottomNavigationConnector;
    }

    public static void injectMScreenProtector(PageControllerLifeCycle instance, ScreenProtector mScreenProtector) {
        instance.mScreenProtector = mScreenProtector;
    }

    public static void injectMMainScheduler(PageControllerLifeCycle instance, Scheduler mMainScheduler) {
        instance.mMainScheduler = mMainScheduler;
    }

    public static void injectMModalView(PageControllerLifeCycle instance, Func0<ViewGroup> mModalView) {
        instance.mModalView = mModalView;
    }

    public static void injectMWindow(PageControllerLifeCycle instance, Window mWindow) {
        instance.mWindow = mWindow;
    }
}
