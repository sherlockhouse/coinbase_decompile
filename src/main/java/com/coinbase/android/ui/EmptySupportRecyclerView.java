package com.coinbase.android.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.util.AttributeSet;
import android.view.View;

public class EmptySupportRecyclerView extends RecyclerView {
    private AdapterDataObserver mDataObserver = new AdapterDataObserver() {
        public void onChanged() {
            super.onChanged();
            EmptySupportRecyclerView.this.updateEmptyView();
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            EmptySupportRecyclerView.this.updateEmptyView();
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            EmptySupportRecyclerView.this.updateEmptyView();
        }
    };
    private View mEmptyView;

    public EmptySupportRecyclerView(Context context) {
        super(context);
    }

    public EmptySupportRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptySupportRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.mDataObserver);
        }
    }

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }

    private void updateEmptyView() {
        int i = 8;
        if (this.mEmptyView != null && getAdapter() != null) {
            int i2;
            boolean showEmptyView = getAdapter().getItemCount() == 0;
            View view = this.mEmptyView;
            if (showEmptyView) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
            if (!showEmptyView) {
                i = 0;
            }
            setVisibility(i);
        }
    }
}
