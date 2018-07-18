package com.coinbase.android.identityverification;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CustomSurfaceView extends SurfaceView {
    public int desiredHeight;
    public int desiredWidth;

    public CustomSurfaceView(Context context) {
        super(context);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.desiredWidth != 0) {
            setMeasuredDimension(this.desiredWidth, this.desiredHeight);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
