package com.mixpanel.android.surveys;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

@TargetApi(14)
public class FadeOnPressButton extends Button {
    private boolean mIsFaded;

    public FadeOnPressButton(Context context) {
        super(context);
    }

    public FadeOnPressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FadeOnPressButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void drawableStateChanged() {
        boolean isPressed = false;
        for (int i : getDrawableState()) {
            if (i == 16842919) {
                if (!this.mIsFaded) {
                    setAlphaBySDK(0.5f);
                }
                isPressed = true;
                if (this.mIsFaded && !isPressed) {
                    setAlphaBySDK(1.0f);
                    this.mIsFaded = true;
                }
                super.drawableStateChanged();
            }
        }
        setAlphaBySDK(1.0f);
        this.mIsFaded = true;
        super.drawableStateChanged();
    }

    private void setAlphaBySDK(float alpha) {
        setAlpha(alpha);
    }
}
