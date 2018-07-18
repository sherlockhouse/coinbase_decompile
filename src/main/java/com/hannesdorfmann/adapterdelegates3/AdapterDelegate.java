package com.hannesdorfmann.adapterdelegates3;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import java.util.List;

public abstract class AdapterDelegate<T> {
    protected abstract boolean isForViewType(T t, int i);

    protected abstract void onBindViewHolder(T t, int i, ViewHolder viewHolder, List<Object> list);

    protected abstract ViewHolder onCreateViewHolder(ViewGroup viewGroup);

    protected void onViewRecycled(ViewHolder viewHolder) {
    }

    protected boolean onFailedToRecycleView(ViewHolder holder) {
        return false;
    }

    protected void onViewAttachedToWindow(ViewHolder holder) {
    }

    protected void onViewDetachedFromWindow(ViewHolder holder) {
    }
}
