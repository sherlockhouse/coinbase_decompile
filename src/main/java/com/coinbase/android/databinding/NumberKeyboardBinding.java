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

public class NumberKeyboardBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivAmountKeyboardBack;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvAmountKeyboard0;
    public final TextView tvAmountKeyboard1;
    public final TextView tvAmountKeyboard2;
    public final TextView tvAmountKeyboard3;
    public final TextView tvAmountKeyboard4;
    public final TextView tvAmountKeyboard5;
    public final TextView tvAmountKeyboard6;
    public final TextView tvAmountKeyboard7;
    public final TextView tvAmountKeyboard8;
    public final TextView tvAmountKeyboard9;
    public final TextView tvAmountKeyboardDot;

    static {
        sViewsWithIds.put(R.id.tvAmountKeyboard1, 1);
        sViewsWithIds.put(R.id.tvAmountKeyboard2, 2);
        sViewsWithIds.put(R.id.tvAmountKeyboard3, 3);
        sViewsWithIds.put(R.id.tvAmountKeyboard4, 4);
        sViewsWithIds.put(R.id.tvAmountKeyboard5, 5);
        sViewsWithIds.put(R.id.tvAmountKeyboard6, 6);
        sViewsWithIds.put(R.id.tvAmountKeyboard7, 7);
        sViewsWithIds.put(R.id.tvAmountKeyboard8, 8);
        sViewsWithIds.put(R.id.tvAmountKeyboard9, 9);
        sViewsWithIds.put(R.id.tvAmountKeyboardDot, 10);
        sViewsWithIds.put(R.id.tvAmountKeyboard0, 11);
        sViewsWithIds.put(R.id.ivAmountKeyboardBack, 12);
    }

    public NumberKeyboardBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.ivAmountKeyboardBack = (ImageView) bindings[12];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvAmountKeyboard0 = (TextView) bindings[11];
        this.tvAmountKeyboard1 = (TextView) bindings[1];
        this.tvAmountKeyboard2 = (TextView) bindings[2];
        this.tvAmountKeyboard3 = (TextView) bindings[3];
        this.tvAmountKeyboard4 = (TextView) bindings[4];
        this.tvAmountKeyboard5 = (TextView) bindings[5];
        this.tvAmountKeyboard6 = (TextView) bindings[6];
        this.tvAmountKeyboard7 = (TextView) bindings[7];
        this.tvAmountKeyboard8 = (TextView) bindings[8];
        this.tvAmountKeyboard9 = (TextView) bindings[9];
        this.tvAmountKeyboardDot = (TextView) bindings[10];
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

    public static NumberKeyboardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static NumberKeyboardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (NumberKeyboardBinding) DataBindingUtil.inflate(inflater, R.layout.number_keyboard, root, attachToRoot, bindingComponent);
    }

    public static NumberKeyboardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static NumberKeyboardBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.number_keyboard, null, false), bindingComponent);
    }

    public static NumberKeyboardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static NumberKeyboardBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/number_keyboard_0".equals(view.getTag())) {
            return new NumberKeyboardBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
