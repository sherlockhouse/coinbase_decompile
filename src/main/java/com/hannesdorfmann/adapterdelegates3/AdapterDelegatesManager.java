package com.hannesdorfmann.adapterdelegates3;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.List;

public class AdapterDelegatesManager<T> {
    private static final List<Object> PAYLOADS_EMPTY_LIST = Collections.emptyList();
    protected SparseArrayCompat<AdapterDelegate<T>> delegates = new SparseArrayCompat();
    protected AdapterDelegate<T> fallbackDelegate;

    public AdapterDelegatesManager<T> addDelegate(AdapterDelegate<T> delegate) {
        int viewType = this.delegates.size();
        while (this.delegates.get(viewType) != null) {
            viewType++;
            if (viewType == 2147483646) {
                throw new IllegalArgumentException("Oops, we are very close to Integer.MAX_VALUE. It seems that there are no more free and unused view type integers left to add another AdapterDelegate.");
            }
        }
        return addDelegate(viewType, false, delegate);
    }

    public AdapterDelegatesManager<T> addDelegate(int viewType, boolean allowReplacingDelegate, AdapterDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("AdapterDelegate is null!");
        } else if (viewType == 2147483646) {
            throw new IllegalArgumentException("The view type = 2147483646 is reserved for fallback adapter delegate (see setFallbackDelegate() ). Please use another view type.");
        } else if (allowReplacingDelegate || this.delegates.get(viewType) == null) {
            this.delegates.put(viewType, delegate);
            return this;
        } else {
            throw new IllegalArgumentException("An AdapterDelegate is already registered for the viewType = " + viewType + ". Already registered AdapterDelegate is " + this.delegates.get(viewType));
        }
    }

    public int getItemViewType(T items, int position) {
        if (items == null) {
            throw new NullPointerException("Items datasource is null!");
        }
        int delegatesCount = this.delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            if (((AdapterDelegate) this.delegates.valueAt(i)).isForViewType(items, position)) {
                return this.delegates.keyAt(i);
            }
        }
        if (this.fallbackDelegate != null) {
            return 2147483646;
        }
        throw new NullPointerException("No AdapterDelegate added that matches position=" + position + " in data source");
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate<T> delegate = getDelegateForViewType(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }
        ViewHolder vh = delegate.onCreateViewHolder(parent);
        if (vh != null) {
            return vh;
        }
        throw new NullPointerException("ViewHolder returned from AdapterDelegate " + delegate + " for ViewType =" + viewType + " is null!");
    }

    public void onBindViewHolder(T items, int position, ViewHolder viewHolder, List payloads) {
        AdapterDelegate<T> delegate = getDelegateForViewType(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for item at position = " + position + " for viewType = " + viewHolder.getItemViewType());
        }
        if (payloads == null) {
            payloads = PAYLOADS_EMPTY_LIST;
        }
        delegate.onBindViewHolder(items, position, viewHolder, payloads);
    }

    public void onBindViewHolder(T items, int position, ViewHolder viewHolder) {
        onBindViewHolder(items, position, viewHolder, PAYLOADS_EMPTY_LIST);
    }

    public AdapterDelegate<T> getDelegateForViewType(int viewType) {
        AdapterDelegate<T> delegate = (AdapterDelegate) this.delegates.get(viewType);
        if (delegate != null) {
            return delegate;
        }
        if (this.fallbackDelegate == null) {
            return null;
        }
        return this.fallbackDelegate;
    }
}
