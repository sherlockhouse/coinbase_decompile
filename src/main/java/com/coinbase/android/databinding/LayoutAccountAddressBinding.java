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
import android.widget.TextView;
import com.coinbase.android.R;

public class LayoutAccountAddressBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btShowAddress;
    public final Button btnCopy;
    public final Button btnHelp;
    public final ImageView ivAccountIcon;
    public final ImageView ivQrCode;
    public final LinearLayout llAccountAddress;
    public final LinearLayout llAddressCopied;
    public final LinearLayout llQrCode;
    public final LinearLayout llWarningContainer;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvAddressCopied;
    public final TextView tvTitle;
    public final TextView tvWarningDetails;
    public final TextView tvWarningTitle;

    static {
        sViewsWithIds.put(R.id.llAccountAddress, 1);
        sViewsWithIds.put(R.id.tvTitle, 2);
        sViewsWithIds.put(R.id.llQrCode, 3);
        sViewsWithIds.put(R.id.ivQrCode, 4);
        sViewsWithIds.put(R.id.btnHelp, 5);
        sViewsWithIds.put(R.id.btnCopy, 6);
        sViewsWithIds.put(R.id.llWarningContainer, 7);
        sViewsWithIds.put(R.id.ivAccountIcon, 8);
        sViewsWithIds.put(R.id.tvWarningTitle, 9);
        sViewsWithIds.put(R.id.tvWarningDetails, 10);
        sViewsWithIds.put(R.id.btShowAddress, 11);
        sViewsWithIds.put(R.id.llAddressCopied, 12);
        sViewsWithIds.put(R.id.tvAddressCopied, 13);
    }

    public LayoutAccountAddressBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds);
        this.btShowAddress = (Button) bindings[11];
        this.btnCopy = (Button) bindings[6];
        this.btnHelp = (Button) bindings[5];
        this.ivAccountIcon = (ImageView) bindings[8];
        this.ivQrCode = (ImageView) bindings[4];
        this.llAccountAddress = (LinearLayout) bindings[1];
        this.llAddressCopied = (LinearLayout) bindings[12];
        this.llQrCode = (LinearLayout) bindings[3];
        this.llWarningContainer = (LinearLayout) bindings[7];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvAddressCopied = (TextView) bindings[13];
        this.tvTitle = (TextView) bindings[2];
        this.tvWarningDetails = (TextView) bindings[10];
        this.tvWarningTitle = (TextView) bindings[9];
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

    public static LayoutAccountAddressBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutAccountAddressBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutAccountAddressBinding) DataBindingUtil.inflate(inflater, R.layout.layout_account_address, root, attachToRoot, bindingComponent);
    }

    public static LayoutAccountAddressBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutAccountAddressBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_account_address, null, false), bindingComponent);
    }

    public static LayoutAccountAddressBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutAccountAddressBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_account_address_0".equals(view.getTag())) {
            return new LayoutAccountAddressBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
