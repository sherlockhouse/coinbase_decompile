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
import android.widget.ScrollView;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.MaterialProgressBar;

public class LayoutIdologyAddressFormBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final EditText etAddressLine1;
    public final EditText etAddressLine2;
    public final EditText etAutoComplete;
    public final EditText etCity;
    public final EditText etState;
    public final EditText etZip;
    public final LinearLayout flAutoCompleteContainer;
    public final LinearLayout llStateZip;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final MaterialProgressBar progress;
    public final RelativeLayout rlContainer;
    public final ScrollView svManualEntry;
    public final TextInputLayout tlAddressLine1;
    public final TextInputLayout tlAddressLine2;
    public final TextInputLayout tlCity;
    public final TextInputLayout tlState;
    public final TextInputLayout tlZip;
    public final TextView tvEnterManually;

    static {
        sViewsWithIds.put(R.id.progress, 1);
        sViewsWithIds.put(R.id.flAutoCompleteContainer, 2);
        sViewsWithIds.put(R.id.etAutoComplete, 3);
        sViewsWithIds.put(R.id.tvEnterManually, 4);
        sViewsWithIds.put(R.id.svManualEntry, 5);
        sViewsWithIds.put(R.id.rlContainer, 6);
        sViewsWithIds.put(R.id.tlAddressLine1, 7);
        sViewsWithIds.put(R.id.etAddressLine1, 8);
        sViewsWithIds.put(R.id.tlAddressLine2, 9);
        sViewsWithIds.put(R.id.etAddressLine2, 10);
        sViewsWithIds.put(R.id.tlCity, 11);
        sViewsWithIds.put(R.id.etCity, 12);
        sViewsWithIds.put(R.id.llStateZip, 13);
        sViewsWithIds.put(R.id.tlState, 14);
        sViewsWithIds.put(R.id.etState, 15);
        sViewsWithIds.put(R.id.tlZip, 16);
        sViewsWithIds.put(R.id.etZip, 17);
    }

    public LayoutIdologyAddressFormBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds);
        this.etAddressLine1 = (EditText) bindings[8];
        this.etAddressLine2 = (EditText) bindings[10];
        this.etAutoComplete = (EditText) bindings[3];
        this.etCity = (EditText) bindings[12];
        this.etState = (EditText) bindings[15];
        this.etZip = (EditText) bindings[17];
        this.flAutoCompleteContainer = (LinearLayout) bindings[2];
        this.llStateZip = (LinearLayout) bindings[13];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (MaterialProgressBar) bindings[1];
        this.rlContainer = (RelativeLayout) bindings[6];
        this.svManualEntry = (ScrollView) bindings[5];
        this.tlAddressLine1 = (TextInputLayout) bindings[7];
        this.tlAddressLine2 = (TextInputLayout) bindings[9];
        this.tlCity = (TextInputLayout) bindings[11];
        this.tlState = (TextInputLayout) bindings[14];
        this.tlZip = (TextInputLayout) bindings[16];
        this.tvEnterManually = (TextView) bindings[4];
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

    public static LayoutIdologyAddressFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyAddressFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutIdologyAddressFormBinding) DataBindingUtil.inflate(inflater, R.layout.layout_idology_address_form, root, attachToRoot, bindingComponent);
    }

    public static LayoutIdologyAddressFormBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyAddressFormBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_idology_address_form, null, false), bindingComponent);
    }

    public static LayoutIdologyAddressFormBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyAddressFormBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_idology_address_form_0".equals(view.getTag())) {
            return new LayoutIdologyAddressFormBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
