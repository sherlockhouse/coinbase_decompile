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

public class DropdownItemContactSuggestionBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivGravatar;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvName;

    static {
        sViewsWithIds.put(R.id.ivGravatar, 1);
        sViewsWithIds.put(R.id.tvName, 2);
    }

    public DropdownItemContactSuggestionBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.ivGravatar = (ImageView) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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

    public static DropdownItemContactSuggestionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static DropdownItemContactSuggestionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (DropdownItemContactSuggestionBinding) DataBindingUtil.inflate(inflater, R.layout.dropdown_item_contact_suggestion, root, attachToRoot, bindingComponent);
    }

    public static DropdownItemContactSuggestionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static DropdownItemContactSuggestionBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.dropdown_item_contact_suggestion, null, false), bindingComponent);
    }

    public static DropdownItemContactSuggestionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DropdownItemContactSuggestionBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/dropdown_item_contact_suggestion_0".equals(view.getTag())) {
            return new DropdownItemContactSuggestionBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
