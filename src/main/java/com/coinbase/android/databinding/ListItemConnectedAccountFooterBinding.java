package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemConnectedAccountFooterBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btAdd;
    public final ImageView ivAddIcon;
    public final ImageView ivArrow;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlAddEmptyFooter;
    public final RelativeLayout rlAddFooter;
    public final LinearLayout rlFooter;
    public final TextView tvAddAccount;
    public final TextView tvAddAccount2;

    static {
        sViewsWithIds.put(R.id.rlAddFooter, 1);
        sViewsWithIds.put(R.id.ivAddIcon, 2);
        sViewsWithIds.put(R.id.tvAddAccount, 3);
        sViewsWithIds.put(R.id.ivArrow, 4);
        sViewsWithIds.put(R.id.rlAddEmptyFooter, 5);
        sViewsWithIds.put(R.id.tvAddAccount2, 6);
        sViewsWithIds.put(R.id.btAdd, 7);
    }

    public ListItemConnectedAccountFooterBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.btAdd = (Button) bindings[7];
        this.ivAddIcon = (ImageView) bindings[2];
        this.ivArrow = (ImageView) bindings[4];
        this.rlAddEmptyFooter = (RelativeLayout) bindings[5];
        this.rlAddFooter = (RelativeLayout) bindings[1];
        this.rlFooter = (LinearLayout) bindings[0];
        this.rlFooter.setTag(null);
        this.tvAddAccount = (TextView) bindings[3];
        this.tvAddAccount2 = (TextView) bindings[6];
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

    public static ListItemConnectedAccountFooterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConnectedAccountFooterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemConnectedAccountFooterBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_connected_account_footer, root, attachToRoot, bindingComponent);
    }

    public static ListItemConnectedAccountFooterBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConnectedAccountFooterBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_connected_account_footer, null, false), bindingComponent);
    }

    public static ListItemConnectedAccountFooterBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConnectedAccountFooterBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_connected_account_footer_0".equals(view.getTag())) {
            return new ListItemConnectedAccountFooterBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
