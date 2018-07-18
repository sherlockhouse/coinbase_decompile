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

public class LeftOffsetItemDecorator extends ItemDecoration {
    private static final int[] ATTRS = new int[]{16843284};
    private static final String TAG = LeftOffsetItemDecorator.class.getSimpleName();
    private final Rect mBounds = new Rect();
    protected Drawable mDivider;
    private int mLeftOffset;

    public LeftOffsetItemDecorator(Context context, int leftOffset) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.mDivider = a.getDrawable(0);
        if (this.mDivider == null) {
            Log.w(TAG, "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        a.recycle();
        this.mLeftOffset = leftOffset;
    }

    public void setDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        this.mDivider = drawable;
    }

    public void onDraw(Canvas canvas, RecyclerView parent, State state) {
        if (parent.getLayoutManager() != null && this.mDivider != null) {
            canvas.save();
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                drawItemDecorator(canvas, parent, i);
            }
            canvas.restore();
        }
    }

    protected void drawItemDecorator(Canvas canvas, RecyclerView parent, int position) {
        int right;
        View child = parent.getChildAt(position);
        int adapterPosition = parent.getChildAdapterPosition(child);
        if (parent.getClipToPadding()) {
            right = parent.getWidth() - parent.getPaddingRight();
        } else {
            right = parent.getWidth();
        }
        int left = adapterPosition == parent.getAdapter().getItemCount() + -1 ? 0 : this.mLeftOffset;
        parent.getDecoratedBoundsWithMargins(child, this.mBounds);
        int bottom = this.mBounds.bottom + Math.round(child.getTranslationY());
        this.mDivider.setBounds(left, bottom - this.mDivider.getIntrinsicHeight(), right, bottom);
        this.mDivider.draw(canvas);
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        outRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
    }
}
