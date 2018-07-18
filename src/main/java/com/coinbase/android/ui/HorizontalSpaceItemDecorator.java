package com.coinbase.android.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class HorizontalSpaceItemDecorator extends ItemDecoration {
    private final boolean mHideLastItemSpace;
    private final int mSpaceWidth;

    public HorizontalSpaceItemDecorator(int spaceWidth, boolean hideLastItemSpace) {
        this.mSpaceWidth = spaceWidth;
        this.mHideLastItemSpace = hideLastItemSpace;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        if (!this.mHideLastItemSpace || parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.right = this.mSpaceWidth;
        }
    }
}
