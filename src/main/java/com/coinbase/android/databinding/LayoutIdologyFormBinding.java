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
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.DatePickerSpinnerLayout;

public class LayoutIdologyFormBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final DatePickerSpinnerLayout dobDatePicker;
    public final EditText etAddressLine1;
    public final EditText etAddressLine2;
    public final EditText etCity;
    public final EditText etCoinbaseUses;
    public final EditText etDob;
    public final EditText etEmployer;
    public final EditText etFirstName;
    public final EditText etJobTitle;
    public final EditText etLastName;
    public final EditText etSSN;
    public final EditText etSourceOfFunds;
    public final EditText etState;
    public final EditText etZip;
    public final LinearLayout llStateZip;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RelativeLayout rlContainer;
    public final TextInputLayout tlAddressLine1;
    public final TextInputLayout tlAddressLine2;
    public final TextInputLayout tlCity;
    public final TextInputLayout tlCoinbaseUses;
    public final TextInputLayout tlDob;
    public final TextInputLayout tlEmployer;
    public final TextInputLayout tlFirstName;
    public final TextInputLayout tlJobTitle;
    public final TextInputLayout tlLastName;
    public final TextInputLayout tlSSN;
    public final TextInputLayout tlSourceOfFunds;
    public final TextInputLayout tlState;
    public final TextInputLayout tlZip;
    public final TextView tvHeader;

    static {
        sViewsWithIds.put(R.id.tvHeader, 1);
        sViewsWithIds.put(R.id.rlContainer, 2);
        sViewsWithIds.put(R.id.tlFirstName, 3);
        sViewsWithIds.put(R.id.etFirstName, 4);
        sViewsWithIds.put(R.id.tlLastName, 5);
        sViewsWithIds.put(R.id.etLastName, 6);
        sViewsWithIds.put(R.id.tlDob, 7);
        sViewsWithIds.put(R.id.etDob, 8);
        sViewsWithIds.put(R.id.tlAddressLine1, 9);
        sViewsWithIds.put(R.id.etAddressLine1, 10);
        sViewsWithIds.put(R.id.tlAddressLine2, 11);
        sViewsWithIds.put(R.id.etAddressLine2, 12);
        sViewsWithIds.put(R.id.tlCity, 13);
        sViewsWithIds.put(R.id.etCity, 14);
        sViewsWithIds.put(R.id.llStateZip, 15);
        sViewsWithIds.put(R.id.tlState, 16);
        sViewsWithIds.put(R.id.etState, 17);
        sViewsWithIds.put(R.id.tlZip, 18);
        sViewsWithIds.put(R.id.etZip, 19);
        sViewsWithIds.put(R.id.tlSSN, 20);
        sViewsWithIds.put(R.id.etSSN, 21);
        sViewsWithIds.put(R.id.tlCoinbaseUses, 22);
        sViewsWithIds.put(R.id.etCoinbaseUses, 23);
        sViewsWithIds.put(R.id.tlSourceOfFunds, 24);
        sViewsWithIds.put(R.id.etSourceOfFunds, 25);
        sViewsWithIds.put(R.id.tlJobTitle, 26);
        sViewsWithIds.put(R.id.etJobTitle, 27);
        sViewsWithIds.put(R.id.tlEmployer, 28);
        sViewsWithIds.put(R.id.etEmployer, 29);
        sViewsWithIds.put(R.id.dobDatePicker, 30);
    }

    public LayoutIdologyFormBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 31, sIncludes, sViewsWithIds);
        this.dobDatePicker = (DatePickerSpinnerLayout) bindings[30];
        this.etAddressLine1 = (EditText) bindings[10];
        this.etAddressLine2 = (EditText) bindings[12];
        this.etCity = (EditText) bindings[14];
        this.etCoinbaseUses = (EditText) bindings[23];
        this.etDob = (EditText) bindings[8];
        this.etEmployer = (EditText) bindings[29];
        this.etFirstName = (EditText) bindings[4];
        this.etJobTitle = (EditText) bindings[27];
        this.etLastName = (EditText) bindings[6];
        this.etSSN = (EditText) bindings[21];
        this.etSourceOfFunds = (EditText) bindings[25];
        this.etState = (EditText) bindings[17];
        this.etZip = (EditText) bindings[19];
        this.llStateZip = (LinearLayout) bindings[15];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlContainer = (RelativeLayout) bindings[2];
        this.tlAddressLine1 = (TextInputLayout) bindings[9];
        this.tlAddressLine2 = (TextInputLayout) bindings[11];
        this.tlCity = (TextInputLayout) bindings[13];
        this.tlCoinbaseUses = (TextInputLayout) bindings[22];
        this.tlDob = (TextInputLayout) bindings[7];
        this.tlEmployer = (TextInputLayout) bindings[28];
        this.tlFirstName = (TextInputLayout) bindings[3];
        this.tlJobTitle = (TextInputLayout) bindings[26];
        this.tlLastName = (TextInputLayout) bindings[5];
        this.tlSSN = (TextInputLayout) bindings[20];
        this.tlSourceOfFunds = (TextInputLayout) bindings[24];
        this.tlState = (TextInputLayout) bindings[16];
        this.tlZip = (TextInputLayout) bindings[18];
        this.tvHeader = (TextView) bindings[1];
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

    public static LayoutIdologyFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutIdologyFormBinding) DataBindingUtil.inflate(inflater, R.layout.layout_idology_form, root, attachToRoot, bindingComponent);
    }

    public static LayoutIdologyFormBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyFormBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_idology_form, null, false), bindingComponent);
    }

    public static LayoutIdologyFormBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyFormBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_idology_form_0".equals(view.getTag())) {
            return new LayoutIdologyFormBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
