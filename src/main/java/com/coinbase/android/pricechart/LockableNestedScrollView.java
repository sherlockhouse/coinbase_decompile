package com.coinbase.android.pricechart;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class LockableNestedScrollView extends NestedScrollView {
    private boolean mScrollable = true;

    public LockableNestedScrollView(Context context) {
        super(context);
    }

    public LockableNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LockableNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollingEnabled(boolean enabled) {
        this.mScrollable = enabled;
    }

    public boolean isScrollable() {
        return this.mScrollable;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                if (this.mScrollable) {
                    return super.onTouchEvent(ev);
                }
                return false;
            default:
                return super.onTouchEvent(ev);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.mScrollable) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }
}
