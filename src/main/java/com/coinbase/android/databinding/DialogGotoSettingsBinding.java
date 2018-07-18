package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class DialogGotoSettingsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnGoToSettings;
    public final TextView btnGoToSettingsClose;
    public final LinearLayout llContent;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvDescription;
    public final TextView tvHeader;

    static {
        sViewsWithIds.put(R.id.llContent, 1);
        sViewsWithIds.put(R.id.tvHeader, 2);
        sViewsWithIds.put(R.id.tvDescription, 3);
        sViewsWithIds.put(R.id.btnGoToSettings, 4);
        sViewsWithIds.put(R.id.btnGoToSettingsClose, 5);
    }

    public DialogGotoSettingsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.btnGoToSettings = (Button) bindings[4];
        this.btnGoToSettingsClose = (TextView) bindings[5];
        this.llContent = (LinearLayout) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvDescription = (TextView) bindings[3];
        this.tvHeader = (TextView) bindings[2];
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

    public static DialogGotoSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static DialogGotoSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (DialogGotoSettingsBinding) DataBindingUtil.inflate(inflater, R.layout.dialog_goto_settings, root, attachToRoot, bindingComponent);
    }

    public static DialogGotoSettingsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static DialogGotoSettingsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.dialog_goto_settings, null, false), bindingComponent);
    }

    public static DialogGotoSettingsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DialogGotoSettingsBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/dialog_goto_settings_0".equals(view.getTag())) {
            return new DialogGotoSettingsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
