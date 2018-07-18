package com.coinbase.android.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.coinbase.android.ComponentProvider;

public class MaterialProgressBar extends ImageView {
    private MaterialProgressDrawable mDrawable;

    public MaterialProgressBar(Context context) {
        super(context);
        setupDrawable(context);
    }

    public MaterialProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawable(context);
    }

    public MaterialProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupDrawable(context);
    }

    private void setupDrawable(Context context) {
        AnimationUtilsWrapper animationUtilsWrapper = ((ComponentProvider) context.getApplicationContext()).applicationComponent().animationUtilsWrapper();
        this.mDrawable = new MaterialProgressDrawable(context, this);
        this.mDrawable.setColorSchemeColors(-1);
        this.mDrawable.setBackgroundColor(0);
        this.mDrawable.setAlpha(255);
        setImageDrawable(this.mDrawable);
        animationUtilsWrapper.startMaterialProgressDrawableAnimation(this.mDrawable);
    }

    public void setVisibility(int visibility) {
        if (visibility == 4 || visibility == 8) {
            clearAnimation();
        } else {
            setupDrawable(getContext());
        }
        super.setVisibility(visibility);
    }
}
