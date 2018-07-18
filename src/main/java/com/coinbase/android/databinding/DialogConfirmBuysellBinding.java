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

public class DialogConfirmBuysellBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivFirstIcon;
    public final ImageView ivSecondIcon;
    public final LinearLayout llPayoutContainer;
    public final LinearLayout llVerifyCardContainer;
    public final LinearLayout llWarningContainer;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvAmount;
    public final TextView tvPaymentMethod;
    public final TextView tvPayoutDate;
    public final TextView tvTitle;
    public final TextView tvWarning;
    public final View vDivider;

    static {
        sViewsWithIds.put(R.id.tvTitle, 1);
        sViewsWithIds.put(R.id.ivFirstIcon, 2);
        sViewsWithIds.put(R.id.tvAmount, 3);
        sViewsWithIds.put(R.id.ivSecondIcon, 4);
        sViewsWithIds.put(R.id.tvPaymentMethod, 5);
        sViewsWithIds.put(R.id.vDivider, 6);
        sViewsWithIds.put(R.id.llPayoutContainer, 7);
        sViewsWithIds.put(R.id.tvPayoutDate, 8);
        sViewsWithIds.put(R.id.llWarningContainer, 9);
        sViewsWithIds.put(R.id.tvWarning, 10);
        sViewsWithIds.put(R.id.llVerifyCardContainer, 11);
    }

    public DialogConfirmBuysellBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.ivFirstIcon = (ImageView) bindings[2];
        this.ivSecondIcon = (ImageView) bindings[4];
        this.llPayoutContainer = (LinearLayout) bindings[7];
        this.llVerifyCardContainer = (LinearLayout) bindings[11];
        this.llWarningContainer = (LinearLayout) bindings[9];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvAmount = (TextView) bindings[3];
        this.tvPaymentMethod = (TextView) bindings[5];
        this.tvPayoutDate = (TextView) bindings[8];
        this.tvTitle = (TextView) bindings[1];
        this.tvWarning = (TextView) bindings[10];
        this.vDivider = (View) bindings[6];
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

    public static DialogConfirmBuysellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static DialogConfirmBuysellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (DialogConfirmBuysellBinding) DataBindingUtil.inflate(inflater, R.layout.dialog_confirm_buysell, root, attachToRoot, bindingComponent);
    }

    public static DialogConfirmBuysellBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static DialogConfirmBuysellBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.dialog_confirm_buysell, null, false), bindingComponent);
    }

    public static DialogConfirmBuysellBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DialogConfirmBuysellBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/dialog_confirm_buysell_0".equals(view.getTag())) {
            return new DialogConfirmBuysellBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
