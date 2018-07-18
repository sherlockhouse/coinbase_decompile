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
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemAccountBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivAccountListItemIcon;
    public final ImageView ivArrow;
    public final LinearLayout llBalance;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlAccount;
    public final RelativeLayout rlAccountListItemIcon;
    public final TextView tvAccountListItemBalance;
    public final TextView tvAccountListItemName;
    public final TextView tvAccountListNativeBalance;
    public final TextView tvFiatSymbol;

    static {
        sViewsWithIds.put(R.id.rlAccountListItemIcon, 1);
        sViewsWithIds.put(R.id.ivAccountListItemIcon, 2);
        sViewsWithIds.put(R.id.tvFiatSymbol, 3);
        sViewsWithIds.put(R.id.tvAccountListItemName, 4);
        sViewsWithIds.put(R.id.llBalance, 5);
        sViewsWithIds.put(R.id.tvAccountListNativeBalance, 6);
        sViewsWithIds.put(R.id.tvAccountListItemBalance, 7);
        sViewsWithIds.put(R.id.ivArrow, 8);
    }

    public ListItemAccountBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.ivAccountListItemIcon = (ImageView) bindings[2];
        this.ivArrow = (ImageView) bindings[8];
        this.llBalance = (LinearLayout) bindings[5];
        this.rlAccount = (RelativeLayout) bindings[0];
        this.rlAccount.setTag(null);
        this.rlAccountListItemIcon = (RelativeLayout) bindings[1];
        this.tvAccountListItemBalance = (TextView) bindings[7];
        this.tvAccountListItemName = (TextView) bindings[4];
        this.tvAccountListNativeBalance = (TextView) bindings[6];
        this.tvFiatSymbol = (TextView) bindings[3];
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

    public static ListItemAccountBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemAccountBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemAccountBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_account, root, attachToRoot, bindingComponent);
    }

    public static ListItemAccountBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemAccountBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_account, null, false), bindingComponent);
    }

    public static ListItemAccountBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemAccountBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_account_0".equals(view.getTag())) {
            return new ListItemAccountBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
