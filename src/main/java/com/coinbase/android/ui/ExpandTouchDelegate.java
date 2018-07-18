package com.coinbase.android.ui;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

public class ExpandTouchDelegate {
    public static int ALL = (((LEFT | RIGHT) | TOP) | BOTTOM);
    public static int BOTTOM = 4096;
    private static int HIT_PADDING = 50;
    public static int LEFT = 1;
    public static int RIGHT = 16;
    public static int TOP = 256;
    private final View mButton;

    public ExpandTouchDelegate(View button) {
        this.mButton = button;
    }

    public void expand(int flags) {
        int left;
        int right;
        int top;
        int bottom;
        if ((LEFT & flags) == LEFT) {
            left = HIT_PADDING;
        } else {
            left = 0;
        }
        if ((RIGHT & flags) == RIGHT) {
            right = HIT_PADDING;
        } else {
            right = 0;
        }
        if ((TOP & flags) == TOP) {
            top = HIT_PADDING;
        } else {
            top = 0;
        }
        if ((BOTTOM & flags) == BOTTOM) {
            bottom = HIT_PADDING;
        } else {
            bottom = 0;
        }
        final View parent = (View) this.mButton.getParent();
        parent.post(new Runnable() {
            public void run() {
                Rect rect = new Rect();
                ExpandTouchDelegate.this.mButton.getHitRect(rect);
                rect.left -= left;
                rect.right += right;
                rect.top -= top;
                rect.bottom += bottom;
                parent.setTouchDelegate(new TouchDelegate(rect, ExpandTouchDelegate.this.mButton));
            }
        });
    }
}
