package com.coinbase.android.ui;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final /* synthetic */ class StatusBarUpdater$$Lambda$1 implements AnimatorUpdateListener {
    private final StatusBarUpdater arg$1;

    private StatusBarUpdater$$Lambda$1(StatusBarUpdater statusBarUpdater) {
        this.arg$1 = statusBarUpdater;
    }

    public static AnimatorUpdateListener lambdaFactory$(StatusBarUpdater statusBarUpdater) {
        return new StatusBarUpdater$$Lambda$1(statusBarUpdater);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.arg$1.setStatusBarColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }
}
