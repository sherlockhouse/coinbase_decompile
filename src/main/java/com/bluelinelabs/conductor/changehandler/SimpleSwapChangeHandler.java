package com.bluelinelabs.conductor.changehandler;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeHandler.ControllerChangeCompletedListener;

public class SimpleSwapChangeHandler extends ControllerChangeHandler implements OnAttachStateChangeListener {
    private boolean canceled;
    private ControllerChangeCompletedListener changeListener;
    private ViewGroup container;
    private boolean removesFromViewOnPush;

    public SimpleSwapChangeHandler() {
        this(true);
    }

    public SimpleSwapChangeHandler(boolean removesFromViewOnPush) {
        this.removesFromViewOnPush = removesFromViewOnPush;
    }

    public void saveToBundle(Bundle bundle) {
        super.saveToBundle(bundle);
        bundle.putBoolean("SimpleSwapChangeHandler.removesFromViewOnPush", this.removesFromViewOnPush);
    }

    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        this.removesFromViewOnPush = bundle.getBoolean("SimpleSwapChangeHandler.removesFromViewOnPush");
    }

    public void onAbortPush(ControllerChangeHandler newHandler, Controller newTop) {
        super.onAbortPush(newHandler, newTop);
        this.canceled = true;
    }

    public void completeImmediately() {
        if (this.changeListener != null) {
            this.changeListener.onChangeCompleted();
            this.changeListener = null;
            this.container.removeOnAttachStateChangeListener(this);
            this.container = null;
        }
    }

    public void performChange(ViewGroup container, View from, View to, boolean isPush, ControllerChangeCompletedListener changeListener) {
        if (!this.canceled) {
            if (from != null && (!isPush || this.removesFromViewOnPush)) {
                container.removeView(from);
            }
            if (to != null && to.getParent() == null) {
                container.addView(to);
            }
        }
        if (container.getWindowToken() != null) {
            changeListener.onChangeCompleted();
            return;
        }
        this.changeListener = changeListener;
        this.container = container;
        container.addOnAttachStateChangeListener(this);
    }

    public boolean removesFromViewOnPush() {
        return this.removesFromViewOnPush;
    }

    public void onViewAttachedToWindow(View v) {
        v.removeOnAttachStateChangeListener(this);
        if (this.changeListener != null) {
            this.changeListener.onChangeCompleted();
            this.changeListener = null;
            this.container = null;
        }
    }

    public void onViewDetachedFromWindow(View v) {
    }

    public ControllerChangeHandler copy() {
        return new SimpleSwapChangeHandler(removesFromViewOnPush());
    }

    public boolean isReusable() {
        return true;
    }
}
