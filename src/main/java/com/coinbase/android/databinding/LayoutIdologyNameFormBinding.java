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
import com.coinbase.android.ui.DatePickerSpinnerLayout;

public class LayoutIdologyNameFormBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final DatePickerSpinnerLayout dobDatePicker;
    public final EditText etDob;
    public final EditText etFirstName;
    public final EditText etLastName;
    public final EditText etSSN;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RelativeLayout rlContainer;
    public final TextInputLayout tlDob;
    public final TextInputLayout tlFirstName;
    public final TextInputLayout tlLastName;
    public final TextInputLayout tlSSN;

    static {
        sViewsWithIds.put(R.id.rlContainer, 1);
        sViewsWithIds.put(R.id.tlFirstName, 2);
        sViewsWithIds.put(R.id.etFirstName, 3);
        sViewsWithIds.put(R.id.tlLastName, 4);
        sViewsWithIds.put(R.id.etLastName, 5);
        sViewsWithIds.put(R.id.tlDob, 6);
        sViewsWithIds.put(R.id.etDob, 7);
        sViewsWithIds.put(R.id.tlSSN, 8);
        sViewsWithIds.put(R.id.etSSN, 9);
        sViewsWithIds.put(R.id.dobDatePicker, 10);
    }

    public LayoutIdologyNameFormBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.dobDatePicker = (DatePickerSpinnerLayout) bindings[10];
        this.etDob = (EditText) bindings[7];
        this.etFirstName = (EditText) bindings[3];
        this.etLastName = (EditText) bindings[5];
        this.etSSN = (EditText) bindings[9];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlContainer = (RelativeLayout) bindings[1];
        this.tlDob = (TextInputLayout) bindings[6];
        this.tlFirstName = (TextInputLayout) bindings[2];
        this.tlLastName = (TextInputLayout) bindings[4];
        this.tlSSN = (TextInputLayout) bindings[8];
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

    public static LayoutIdologyNameFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyNameFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutIdologyNameFormBinding) DataBindingUtil.inflate(inflater, R.layout.layout_idology_name_form, root, attachToRoot, bindingComponent);
    }

    public static LayoutIdologyNameFormBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyNameFormBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_idology_name_form, null, false), bindingComponent);
    }

    public static LayoutIdologyNameFormBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyNameFormBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_idology_name_form_0".equals(view.getTag())) {
            return new LayoutIdologyNameFormBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
