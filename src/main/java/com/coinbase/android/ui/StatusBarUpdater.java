package com.coinbase.android.ui;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Build.VERSION;
import android.view.Window;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.utils.Utils;
import javax.inject.Inject;

@ActivityScope
public class StatusBarUpdater {
    static final float STATUS_BAR_COLOR_FACTOR = 0.8f;
    private final Window mWindow;

    @Inject
    public StatusBarUpdater(Window window) {
        this.mWindow = window;
    }

    public void startStatusBarAnimation(int endColor, int duration) {
        if (VERSION.SDK_INT >= 21) {
            int startColor = this.mWindow.getStatusBarColor();
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(startColor), Integer.valueOf(Utils.updateColor(endColor, STATUS_BAR_COLOR_FACTOR))});
            colorAnimation.setDuration((long) duration);
            colorAnimation.addUpdateListener(StatusBarUpdater$$Lambda$1.lambdaFactory$(this));
            colorAnimation.start();
        }
    }

    public void setStatusBarColor(int color) {
        if (VERSION.SDK_INT >= 21) {
            this.mWindow.addFlags(Integer.MIN_VALUE);
            this.mWindow.clearFlags(67108864);
            this.mWindow.setStatusBarColor(color);
        }
    }

    public void setStatusBarColorFromBackgroundColor(int backgroundColor) {
        setStatusBarColor(Utils.updateColor(backgroundColor, STATUS_BAR_COLOR_FACTOR));
    }
}
