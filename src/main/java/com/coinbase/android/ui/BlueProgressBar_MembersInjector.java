package com.coinbase.android.ui;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class BlueProgressBar_MembersInjector implements MembersInjector<BlueProgressBar> {
    private final Provider<AnimationUtilsWrapper> mAnimationUtilsWrapperProvider;

    public BlueProgressBar_MembersInjector(Provider<AnimationUtilsWrapper> mAnimationUtilsWrapperProvider) {
        this.mAnimationUtilsWrapperProvider = mAnimationUtilsWrapperProvider;
    }

    public static MembersInjector<BlueProgressBar> create(Provider<AnimationUtilsWrapper> mAnimationUtilsWrapperProvider) {
        return new BlueProgressBar_MembersInjector(mAnimationUtilsWrapperProvider);
    }

    public void injectMembers(BlueProgressBar instance) {
        injectMAnimationUtilsWrapper(instance, (AnimationUtilsWrapper) this.mAnimationUtilsWrapperProvider.get());
    }

    public static void injectMAnimationUtilsWrapper(BlueProgressBar instance, AnimationUtilsWrapper mAnimationUtilsWrapper) {
        instance.mAnimationUtilsWrapper = mAnimationUtilsWrapper;
    }
}
