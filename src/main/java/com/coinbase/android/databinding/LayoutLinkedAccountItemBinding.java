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

public class LayoutLinkedAccountItemBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btAdd;
    public final ImageView ivArrow2;
    public final ImageView ivArrow3;
    public final ImageView ivFiatBackground;
    public final ImageView ivLinkedAccountIcon;
    public final LinearLayout llParent;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlFiatAccount;
    public final RelativeLayout rlFiatIcon;
    public final RelativeLayout rlLinkedAccount;
    public final RelativeLayout rlLoading;
    public final RelativeLayout rlMissingLinkedAccount;
    public final TextView tvAddAccount;
    public final TextView tvFiatBalance;
    public final TextView tvFiatName;
    public final TextView tvFiatSymbol;
    public final TextView tvLinkedAccountLimit;
    public final TextView tvLinkedAccountName;
    public final TextView tvLinkedAccountNumber;
    public final TextView tvLoading;

    static {
        sViewsWithIds.put(R.id.rlLoading, 1);
        sViewsWithIds.put(R.id.tvLoading, 2);
        sViewsWithIds.put(R.id.rlMissingLinkedAccount, 3);
        sViewsWithIds.put(R.id.tvAddAccount, 4);
        sViewsWithIds.put(R.id.btAdd, 5);
        sViewsWithIds.put(R.id.rlLinkedAccount, 6);
        sViewsWithIds.put(R.id.ivLinkedAccountIcon, 7);
        sViewsWithIds.put(R.id.tvLinkedAccountName, 8);
        sViewsWithIds.put(R.id.tvLinkedAccountNumber, 9);
        sViewsWithIds.put(R.id.tvLinkedAccountLimit, 10);
        sViewsWithIds.put(R.id.ivArrow2, 11);
        sViewsWithIds.put(R.id.rlFiatAccount, 12);
        sViewsWithIds.put(R.id.rlFiatIcon, 13);
        sViewsWithIds.put(R.id.ivFiatBackground, 14);
        sViewsWithIds.put(R.id.tvFiatSymbol, 15);
        sViewsWithIds.put(R.id.tvFiatName, 16);
        sViewsWithIds.put(R.id.tvFiatBalance, 17);
        sViewsWithIds.put(R.id.ivArrow3, 18);
    }

    public LayoutLinkedAccountItemBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds);
        this.btAdd = (Button) bindings[5];
        this.ivArrow2 = (ImageView) bindings[11];
        this.ivArrow3 = (ImageView) bindings[18];
        this.ivFiatBackground = (ImageView) bindings[14];
        this.ivLinkedAccountIcon = (ImageView) bindings[7];
        this.llParent = (LinearLayout) bindings[0];
        this.llParent.setTag(null);
        this.rlFiatAccount = (RelativeLayout) bindings[12];
        this.rlFiatIcon = (RelativeLayout) bindings[13];
        this.rlLinkedAccount = (RelativeLayout) bindings[6];
        this.rlLoading = (RelativeLayout) bindings[1];
        this.rlMissingLinkedAccount = (RelativeLayout) bindings[3];
        this.tvAddAccount = (TextView) bindings[4];
        this.tvFiatBalance = (TextView) bindings[17];
        this.tvFiatName = (TextView) bindings[16];
        this.tvFiatSymbol = (TextView) bindings[15];
        this.tvLinkedAccountLimit = (TextView) bindings[10];
        this.tvLinkedAccountName = (TextView) bindings[8];
        this.tvLinkedAccountNumber = (TextView) bindings[9];
        this.tvLoading = (TextView) bindings[2];
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

    public static LayoutLinkedAccountItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutLinkedAccountItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutLinkedAccountItemBinding) DataBindingUtil.inflate(inflater, R.layout.layout_linked_account_item, root, attachToRoot, bindingComponent);
    }

    public static LayoutLinkedAccountItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutLinkedAccountItemBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_linked_account_item, null, false), bindingComponent);
    }

    public static LayoutLinkedAccountItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutLinkedAccountItemBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_linked_account_item_0".equals(view.getTag())) {
            return new LayoutLinkedAccountItemBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
