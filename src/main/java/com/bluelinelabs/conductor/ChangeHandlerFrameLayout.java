package com.bluelinelabs.conductor;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.bluelinelabs.conductor.ControllerChangeHandler.ControllerChangeListener;

public class ChangeHandlerFrameLayout extends FrameLayout implements ControllerChangeListener {
    private int inProgressTransactionCount;

    public ChangeHandlerFrameLayout(Context context) {
        super(context);
    }

    public ChangeHandlerFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChangeHandlerFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public ChangeHandlerFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.inProgressTransactionCount > 0 || super.onInterceptTouchEvent(ev);
    }

    public void onChangeStarted(Controller to, Controller from, boolean isPush, ViewGroup container, ControllerChangeHandler handler) {
        this.inProgressTransactionCount++;
    }

    public void onChangeCompleted(Controller to, Controller from, boolean isPush, ViewGroup container, ControllerChangeHandler handler) {
        this.inProgressTransactionCount--;
    }
}
