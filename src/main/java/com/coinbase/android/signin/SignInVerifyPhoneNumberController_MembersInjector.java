package com.coinbase.android.signin;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.coinbase.android.ui.ActionBarController_MembersInjector;
import com.coinbase.android.ui.AnimationUtilsWrapper;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.ControllerLifeCycleFactory;
import com.coinbase.android.ui.ControllerTransitionContainer;
import com.coinbase.android.ui.OptionalViewHider;
import com.coinbase.android.ui.StatusBarUpdater;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class SignInVerifyPhoneNumberController_MembersInjector implements MembersInjector<SignInVerifyPhoneNumberController> {
    private final Provider<AnimationUtilsWrapper> mAnimationUtilsWrapperProvider;
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<MixpanelTracking> mMixpanelTrackingProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<OptionalViewHider> mOptionalViewHiderProvider;
    private final Provider<SignInVerifyPhoneNumberPresenter> mPresenterProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;

    public SignInVerifyPhoneNumberController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<SignInVerifyPhoneNumberPresenter> mPresenterProvider, Provider<AnimationUtilsWrapper> mAnimationUtilsWrapperProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider, Provider<OptionalViewHider> mOptionalViewHiderProvider) {
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mPresenterProvider = mPresenterProvider;
        this.mAnimationUtilsWrapperProvider = mAnimationUtilsWrapperProvider;
        this.mMixpanelTrackingProvider = mMixpanelTrackingProvider;
        this.mOptionalViewHiderProvider = mOptionalViewHiderProvider;
    }

    public static MembersInjector<SignInVerifyPhoneNumberController> create(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<SignInVerifyPhoneNumberPresenter> mPresenterProvider, Provider<AnimationUtilsWrapper> mAnimationUtilsWrapperProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider, Provider<OptionalViewHider> mOptionalViewHiderProvider) {
        return new SignInVerifyPhoneNumberController_MembersInjector(mAppCompatActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, mPresenterProvider, mAnimationUtilsWrapperProvider, mMixpanelTrackingProvider, mOptionalViewHiderProvider);
    }

    public void injectMembers(SignInVerifyPhoneNumberController instance) {
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        injectMPresenter(instance, (SignInVerifyPhoneNumberPresenter) this.mPresenterProvider.get());
        injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        injectMAnimationUtilsWrapper(instance, (AnimationUtilsWrapper) this.mAnimationUtilsWrapperProvider.get());
        injectMMixpanelTracking(instance, (MixpanelTracking) this.mMixpanelTrackingProvider.get());
        injectMOptionalViewHider(instance, (OptionalViewHider) this.mOptionalViewHiderProvider.get());
    }

    public static void injectMPresenter(SignInVerifyPhoneNumberController instance, SignInVerifyPhoneNumberPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMBackNavigationConnector(SignInVerifyPhoneNumberController instance, BackNavigationConnector mBackNavigationConnector) {
        instance.mBackNavigationConnector = mBackNavigationConnector;
    }

    public static void injectMAnimationUtilsWrapper(SignInVerifyPhoneNumberController instance, AnimationUtilsWrapper mAnimationUtilsWrapper) {
        instance.mAnimationUtilsWrapper = mAnimationUtilsWrapper;
    }

    public static void injectMMixpanelTracking(SignInVerifyPhoneNumberController instance, MixpanelTracking mMixpanelTracking) {
        instance.mMixpanelTracking = mMixpanelTracking;
    }

    public static void injectMOptionalViewHider(SignInVerifyPhoneNumberController instance, OptionalViewHider mOptionalViewHider) {
        instance.mOptionalViewHider = mOptionalViewHider;
    }
}
