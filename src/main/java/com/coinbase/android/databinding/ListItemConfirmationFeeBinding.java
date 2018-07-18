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

public class ListItemConfirmationFeeBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivFeeHelp;
    public final LinearLayout llCoinbaseFee;
    private long mDirtyFlags = -1;
    public final TextView tvFeeHeader;
    public final TextView tvFeeValue;

    static {
        sViewsWithIds.put(R.id.tvFeeHeader, 1);
        sViewsWithIds.put(R.id.ivFeeHelp, 2);
        sViewsWithIds.put(R.id.tvFeeValue, 3);
    }

    public ListItemConfirmationFeeBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.ivFeeHelp = (ImageView) bindings[2];
        this.llCoinbaseFee = (LinearLayout) bindings[0];
        this.llCoinbaseFee.setTag(null);
        this.tvFeeHeader = (TextView) bindings[1];
        this.tvFeeValue = (TextView) bindings[3];
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

    public static ListItemConfirmationFeeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConfirmationFeeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemConfirmationFeeBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_confirmation_fee, root, attachToRoot, bindingComponent);
    }

    public static ListItemConfirmationFeeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConfirmationFeeBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_confirmation_fee, null, false), bindingComponent);
    }

    public static ListItemConfirmationFeeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConfirmationFeeBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_confirmation_fee_0".equals(view.getTag())) {
            return new ListItemConfirmationFeeBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
