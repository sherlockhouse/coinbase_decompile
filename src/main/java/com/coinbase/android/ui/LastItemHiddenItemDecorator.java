package com.coinbase.android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.util.Log;
import android.view.View;

public class LastItemHiddenItemDecorator extends ItemDecoration {
    private static final int[] ATTRS = new int[]{16843284};
    private static final String TAG = LastItemHiddenItemDecorator.class.getSimpleName();
    private final Rect mBounds = new Rect();
    private Drawable mDivider;

    public LastItemHiddenItemDecorator(Context context, int leftOffset) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.mDivider = a.getDrawable(0);
        if (this.mDivider == null) {
            Log.w(TAG, "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        a.recycle();
    }

    public void setDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        this.mDivider = drawable;
    }

    public void onDraw(Canvas canvas, RecyclerView parent, State state) {
        if (parent.getLayoutManager() != null && this.mDivider != null && parent.getAdapter() != null && parent.getAdapter().getItemCount() != 0) {
            int right;
            canvas.save();
            int lastPosition = parent.getAdapter().getItemCount();
            if (parent.getClipToPadding()) {
                right = parent.getWidth() - parent.getPaddingRight();
            } else {
                right = parent.getWidth();
            }
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                if (parent.getChildAdapterPosition(child) != lastPosition - 1) {
                    parent.getDecoratedBoundsWithMargins(child, this.mBounds);
                    int bottom = this.mBounds.bottom + Math.round(child.getTranslationY());
                    this.mDivider.setBounds(0, bottom - this.mDivider.getIntrinsicHeight(), right, bottom);
                    this.mDivider.draw(canvas);
                }
            }
            canvas.restore();
        }
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        outRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
    }
}
