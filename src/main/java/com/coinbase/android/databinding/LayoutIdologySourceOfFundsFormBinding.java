package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.TextInputLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.coinbase.android.R;

public class LayoutIdologySourceOfFundsFormBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final EditText etCoinbaseUses;
    public final EditText etEmployer;
    public final EditText etJobTitle;
    public final EditText etSourceOfFunds;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RelativeLayout rlContainer;
    public final TextInputLayout tlCoinbaseUses;
    public final TextInputLayout tlEmployer;
    public final TextInputLayout tlJobTitle;
    public final TextInputLayout tlSourceOfFunds;

    static {
        sViewsWithIds.put(R.id.rlContainer, 1);
        sViewsWithIds.put(R.id.tlCoinbaseUses, 2);
        sViewsWithIds.put(R.id.etCoinbaseUses, 3);
        sViewsWithIds.put(R.id.tlSourceOfFunds, 4);
        sViewsWithIds.put(R.id.etSourceOfFunds, 5);
        sViewsWithIds.put(R.id.tlJobTitle, 6);
        sViewsWithIds.put(R.id.etJobTitle, 7);
        sViewsWithIds.put(R.id.tlEmployer, 8);
        sViewsWithIds.put(R.id.etEmployer, 9);
    }

    public LayoutIdologySourceOfFundsFormBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.etCoinbaseUses = (EditText) bindings[3];
        this.etEmployer = (EditText) bindings[9];
        this.etJobTitle = (EditText) bindings[7];
        this.etSourceOfFunds = (EditText) bindings[5];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlContainer = (RelativeLayout) bindings[1];
        this.tlCoinbaseUses = (TextInputLayout) bindings[2];
        this.tlEmployer = (TextInputLayout) bindings[8];
        this.tlJobTitle = (TextInputLayout) bindings[6];
        this.tlSourceOfFunds = (TextInputLayout) bindings[4];
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

    public static LayoutIdologySourceOfFundsFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologySourceOfFundsFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutIdologySourceOfFundsFormBinding) DataBindingUtil.inflate(inflater, R.layout.layout_idology_source_of_funds_form, root, attachToRoot, bindingComponent);
    }

    public static LayoutIdologySourceOfFundsFormBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologySourceOfFundsFormBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_idology_source_of_funds_form, null, false), bindingComponent);
    }

    public static LayoutIdologySourceOfFundsFormBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologySourceOfFundsFormBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_idology_source_of_funds_form_0".equals(view.getTag())) {
            return new LayoutIdologySourceOfFundsFormBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
