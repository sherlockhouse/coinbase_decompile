package com.coinbase.android.ui;

import android.app.Application;
import com.coinbase.android.ApplicationScope;

public class AnimationModule {
    @ApplicationScope
    AnimationUtilsWrapper providesAnimationUtilsWrapper(Application app) {
        return new AnimationUtilsWrapper(app);
    }
}
