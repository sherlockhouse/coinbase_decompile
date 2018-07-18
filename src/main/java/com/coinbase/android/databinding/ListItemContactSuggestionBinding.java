package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemContactSuggestionBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivGravatar;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvEmail;
    public final TextView tvName;

    static {
        sViewsWithIds.put(R.id.ivGravatar, 1);
        sViewsWithIds.put(R.id.tvName, 2);
        sViewsWithIds.put(R.id.tvEmail, 3);
    }

    public ListItemContactSuggestionBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.ivGravatar = (ImageView) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvEmail = (TextView) bindings[3];
        this.tvName = (TextView) bindings[2];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, Object variable) {
        return false;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    public static ListItemContactSuggestionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemContactSuggestionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemContactSuggestionBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_contact_suggestion, root, attachToRoot, bindingComponent);
    }

    public static ListItemContactSuggestionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemContactSuggestionBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_contact_suggestion, null, false), bindingComponent);
    }

    public static ListItemContactSuggestionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemContactSuggestionBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_contact_suggestion_0".equals(view.getTag())) {
            return new ListItemContactSuggestionBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
