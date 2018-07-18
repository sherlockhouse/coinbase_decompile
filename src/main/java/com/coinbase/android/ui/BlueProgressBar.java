package com.coinbase.android.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import javax.inject.Inject;

public class BlueProgressBar extends ProgressBar {
    @Inject
    AnimationUtilsWrapper mAnimationUtilsWrapper;

    public BlueProgressBar(Context context) {
        super(context);
        init(context);
    }

    public BlueProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BlueProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
        if (!this.mAnimationUtilsWrapper.shouldAnimate()) {
            setIndeterminateDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.white)));
        } else if (VERSION.SDK_INT >= 21) {
            setIndeterminateTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.blue_progress_bar)));
        }
    }
}
