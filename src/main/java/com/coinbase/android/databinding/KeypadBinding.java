package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.coinbase.android.R;

public class KeypadBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final LinearLayout llAmountKeyboard0;
    public final LinearLayout llAmountKeyboard1;
    public final LinearLayout llAmountKeyboard2;
    public final LinearLayout llAmountKeyboard3;
    public final LinearLayout llAmountKeyboard4;
    public final LinearLayout llAmountKeyboard5;
    public final LinearLayout llAmountKeyboard6;
    public final LinearLayout llAmountKeyboard7;
    public final LinearLayout llAmountKeyboard8;
    public final LinearLayout llAmountKeyboard9;
    public final LinearLayout llAmountKeyboardBack;
    public final LinearLayout llAmountKeyboardCancel;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.llAmountKeyboard1, 1);
        sViewsWithIds.put(R.id.llAmountKeyboard2, 2);
        sViewsWithIds.put(R.id.llAmountKeyboard3, 3);
        sViewsWithIds.put(R.id.llAmountKeyboard4, 4);
        sViewsWithIds.put(R.id.llAmountKeyboard5, 5);
        sViewsWithIds.put(R.id.llAmountKeyboard6, 6);
        sViewsWithIds.put(R.id.llAmountKeyboard7, 7);
        sViewsWithIds.put(R.id.llAmountKeyboard8, 8);
        sViewsWithIds.put(R.id.llAmountKeyboard9, 9);
        sViewsWithIds.put(R.id.llAmountKeyboardCancel, 10);
        sViewsWithIds.put(R.id.llAmountKeyboard0, 11);
        sViewsWithIds.put(R.id.llAmountKeyboardBack, 12);
    }

    public KeypadBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.llAmountKeyboard0 = (LinearLayout) bindings[11];
        this.llAmountKeyboard1 = (LinearLayout) bindings[1];
        this.llAmountKeyboard2 = (LinearLayout) bindings[2];
        this.llAmountKeyboard3 = (LinearLayout) bindings[3];
        this.llAmountKeyboard4 = (LinearLayout) bindings[4];
        this.llAmountKeyboard5 = (LinearLayout) bindings[5];
        this.llAmountKeyboard6 = (LinearLayout) bindings[6];
        this.llAmountKeyboard7 = (LinearLayout) bindings[7];
        this.llAmountKeyboard8 = (LinearLayout) bindings[8];
        this.llAmountKeyboard9 = (LinearLayout) bindings[9];
        this.llAmountKeyboardBack = (LinearLayout) bindings[12];
        this.llAmountKeyboardCancel = (LinearLayout) bindings[10];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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

    public static KeypadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static KeypadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (KeypadBinding) DataBindingUtil.inflate(inflater, R.layout.keypad, root, attachToRoot, bindingComponent);
    }

    public static KeypadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static KeypadBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.keypad, null, false), bindingComponent);
    }

    public static KeypadBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static KeypadBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/keypad_0".equals(view.getTag())) {
            return new KeypadBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
