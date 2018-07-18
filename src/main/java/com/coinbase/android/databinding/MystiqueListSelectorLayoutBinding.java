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
import android.widget.ListView;
import android.widget.ProgressBar;
import com.coinbase.android.R;

public class MystiqueListSelectorLayoutBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnAction;
    public final Button btnClose;
    public final ListView lvItems;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final ProgressBar progressBar;

    static {
        sViewsWithIds.put(R.id.lvItems, 1);
        sViewsWithIds.put(R.id.progressBar, 2);
        sViewsWithIds.put(R.id.btnClose, 3);
        sViewsWithIds.put(R.id.btnAction, 4);
    }

    public MystiqueListSelectorLayoutBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.btnAction = (Button) bindings[4];
        this.btnClose = (Button) bindings[3];
        this.lvItems = (ListView) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressBar = (ProgressBar) bindings[2];
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

    public static MystiqueListSelectorLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static MystiqueListSelectorLayoutBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (MystiqueListSelectorLayoutBinding) DataBindingUtil.inflate(inflater, R.layout.mystique_list_selector_layout, root, attachToRoot, bindingComponent);
    }

    public static MystiqueListSelectorLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static MystiqueListSelectorLayoutBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.mystique_list_selector_layout, null, false), bindingComponent);
    }

    public static MystiqueListSelectorLayoutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static MystiqueListSelectorLayoutBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/mystique_list_selector_layout_0".equals(view.getTag())) {
            return new MystiqueListSelectorLayoutBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
