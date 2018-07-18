package com.coinbase.android.confirmation;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import com.coinbase.android.ui.LeftOffsetItemDecorator;

public class ConfirmationItemDecorator extends LeftOffsetItemDecorator {
    public ConfirmationItemDecorator(Context context, int leftOffset) {
        super(context, leftOffset);
    }

    public void onDraw(Canvas canvas, RecyclerView parent, State state) {
        if (parent.getLayoutManager() != null && this.mDivider != null) {
            canvas.save();
            ConfirmationDetailListAdapter adapter = (ConfirmationDetailListAdapter) parent.getAdapter();
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                switch (ItemType.values()[adapter.getItemViewType(parent.getChildAdapterPosition(parent.getChildAt(i)))]) {
                    case FEE:
                        break;
                    default:
                        drawItemDecorator(canvas, parent, i);
                        break;
                }
            }
            canvas.restore();
        }
    }
}
