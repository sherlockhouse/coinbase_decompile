package com.coinbase.android.identityverification;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.coinbase.android.ui.ActionBarController_MembersInjector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.ControllerLifeCycleFactory;
import com.coinbase.android.ui.ControllerTransitionContainer;
import com.coinbase.android.ui.StatusBarUpdater;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class TakeDocumentPhotoController_MembersInjector implements MembersInjector<TakeDocumentPhotoController> {
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<IdentityVerificationBitmapConnector> mIdentityVerificationBitmapConnectorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<TakeDocumentPhotoPresenter> mPresenterProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;

    public TakeDocumentPhotoController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<IdentityVerificationBitmapConnector> mIdentityVerificationBitmapConnectorProvider, Provider<TakeDocumentPhotoPresenter> mPresenterProvider) {
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mIdentityVerificationBitmapConnectorProvider = mIdentityVerificationBitmapConnectorProvider;
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<TakeDocumentPhotoController> create(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<IdentityVerificationBitmapConnector> mIdentityVerificationBitmapConnectorProvider, Provider<TakeDocumentPhotoPresenter> mPresenterProvider) {
        return new TakeDocumentPhotoController_MembersInjector(mAppCompatActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, mIdentityVerificationBitmapConnectorProvider, mPresenterProvider);
    }

    public void injectMembers(TakeDocumentPhotoController instance) {
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        injectMIdentityVerificationBitmapConnector(instance, (IdentityVerificationBitmapConnector) this.mIdentityVerificationBitmapConnectorProvider.get());
        injectMPresenter(instance, (TakeDocumentPhotoPresenter) this.mPresenterProvider.get());
        injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
    }

    public static void injectMIdentityVerificationBitmapConnector(TakeDocumentPhotoController instance, IdentityVerificationBitmapConnector mIdentityVerificationBitmapConnector) {
        instance.mIdentityVerificationBitmapConnector = mIdentityVerificationBitmapConnector;
    }

    public static void injectMPresenter(TakeDocumentPhotoController instance, TakeDocumentPhotoPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMBackNavigationConnector(TakeDocumentPhotoController instance, BackNavigationConnector mBackNavigationConnector) {
        instance.mBackNavigationConnector = mBackNavigationConnector;
    }
}
