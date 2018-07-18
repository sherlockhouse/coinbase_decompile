package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.SwitchCompat;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemSettingsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final SwitchCompat cvSettingsSwitch;
    public final ImageView ivArrow;
    public final LinearLayout llTextContainer;
    private long mDirtyFlags = -1;
    public final ConstraintLayout rlSetting;
    public final TextView tvDisplayDescription;
    public final TextView tvDisplayName;
    public final TextView tvDisplayValue;

    static {
        sViewsWithIds.put(R.id.llTextContainer, 1);
        sViewsWithIds.put(R.id.tvDisplayName, 2);
        sViewsWithIds.put(R.id.tvDisplayDescription, 3);
        sViewsWithIds.put(R.id.tvDisplayValue, 4);
        sViewsWithIds.put(R.id.cvSettingsSwitch, 5);
        sViewsWithIds.put(R.id.ivArrow, 6);
    }

    public ListItemSettingsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.cvSettingsSwitch = (SwitchCompat) bindings[5];
        this.ivArrow = (ImageView) bindings[6];
        this.llTextContainer = (LinearLayout) bindings[1];
        this.rlSetting = (ConstraintLayout) bindings[0];
        this.rlSetting.setTag(null);
        this.tvDisplayDescription = (TextView) bindings[3];
        this.tvDisplayName = (TextView) bindings[2];
        this.tvDisplayValue = (TextView) bindings[4];
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

    public static ListItemSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemSettingsBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_settings, root, attachToRoot, bindingComponent);
    }

    public static ListItemSettingsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemSettingsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_settings, null, false), bindingComponent);
    }

    public static ListItemSettingsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemSettingsBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_settings_0".equals(view.getTag())) {
            return new ListItemSettingsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
