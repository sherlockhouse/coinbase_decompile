package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemConnectedAccountBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivPaymentMethodIcon;
    public final ImageView ivWarning;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlConnectedAccount;
    public final TextView tvPaymentMethodName;
    public final TextView tvPaymentMethodNumber;

    static {
        sViewsWithIds.put(R.id.ivPaymentMethodIcon, 1);
        sViewsWithIds.put(R.id.tvPaymentMethodName, 2);
        sViewsWithIds.put(R.id.tvPaymentMethodNumber, 3);
        sViewsWithIds.put(R.id.ivWarning, 4);
    }

    public ListItemConnectedAccountBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.ivPaymentMethodIcon = (ImageView) bindings[1];
        this.ivWarning = (ImageView) bindings[4];
        this.rlConnectedAccount = (RelativeLayout) bindings[0];
        this.rlConnectedAccount.setTag(null);
        this.tvPaymentMethodName = (TextView) bindings[2];
        this.tvPaymentMethodNumber = (TextView) bindings[3];
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

    public static ListItemConnectedAccountBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConnectedAccountBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemConnectedAccountBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_connected_account, root, attachToRoot, bindingComponent);
    }

    public static ListItemConnectedAccountBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConnectedAccountBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_connected_account, null, false), bindingComponent);
    }

    public static ListItemConnectedAccountBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConnectedAccountBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_connected_account_0".equals(view.getTag())) {
            return new ListItemConnectedAccountBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
