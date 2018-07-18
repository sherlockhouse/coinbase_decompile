package com.coinbase.android.ui;

import android.support.v7.widget.Toolbar;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.utils.Utils;
import javax.inject.Inject;

@ActivityScope
public class ThemeUpdater {
    private final int mAnimationDuration;
    private final StatusBarUpdater mStatusBarUpdater;
    private final Toolbar mToolbar;

    @Inject
    public ThemeUpdater(StatusBarUpdater statusBarUpdater, Toolbar toolbar, int animationDuration) {
        this.mStatusBarUpdater = statusBarUpdater;
        this.mToolbar = toolbar;
        this.mAnimationDuration = animationDuration;
    }

    public void setThemeColorAndAnimateStatusBar(int color, int statusBarColor) {
        setActionBarColor(color);
        this.mStatusBarUpdater.startStatusBarAnimation(statusBarColor, this.mAnimationDuration);
    }

    public void setThemeColor(int color, int statusBarColor) {
        setActionBarColor(color);
        this.mStatusBarUpdater.setStatusBarColor(Utils.updateColor(statusBarColor, 0.8f));
    }

    public void setActionBarColor(int color) {
        this.mToolbar.setBackgroundColor(color);
    }
}
