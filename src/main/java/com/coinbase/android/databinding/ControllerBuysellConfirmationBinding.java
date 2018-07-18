package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.CenteredAppBarLayout;
import com.coinbase.android.ui.MaterialProgressBar;

public class ControllerBuysellConfirmationBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final CenteredAppBarLayout apbLayout;
    public final Button btnContinue;
    public final MaterialProgressBar btnProgress;
    public final ImageView ivMastercardProcessingIcon;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final ProgressBar progressBar;
    public final RelativeLayout rlProgressBarOverlay;
    public final RelativeLayout rlWorldPay;
    public final RecyclerView rvDetails;
    public final LinearLayout slView;
    public final TextView tvCryptoAmount;
    public final TextView tvWarningMessage;
    public final TextView tvWorldPayProcessingHeader;
    public final TextView tvWorldPayProcessingText;

    static {
        sViewsWithIds.put(R.id.apbLayout, 1);
        sViewsWithIds.put(R.id.slView, 2);
        sViewsWithIds.put(R.id.tvCryptoAmount, 3);
        sViewsWithIds.put(R.id.rvDetails, 4);
        sViewsWithIds.put(R.id.tvWarningMessage, 5);
        sViewsWithIds.put(R.id.rlWorldPay, 6);
        sViewsWithIds.put(R.id.ivMastercardProcessingIcon, 7);
        sViewsWithIds.put(R.id.tvWorldPayProcessingHeader, 8);
        sViewsWithIds.put(R.id.tvWorldPayProcessingText, 9);
        sViewsWithIds.put(R.id.btnContinue, 10);
        sViewsWithIds.put(R.id.btnProgress, 11);
        sViewsWithIds.put(R.id.rlProgressBarOverlay, 12);
        sViewsWithIds.put(R.id.progressBar, 13);
    }

    public ControllerBuysellConfirmationBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds);
        this.apbLayout = (CenteredAppBarLayout) bindings[1];
        this.btnContinue = (Button) bindings[10];
        this.btnProgress = (MaterialProgressBar) bindings[11];
        this.ivMastercardProcessingIcon = (ImageView) bindings[7];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressBar = (ProgressBar) bindings[13];
        this.rlProgressBarOverlay = (RelativeLayout) bindings[12];
        this.rlWorldPay = (RelativeLayout) bindings[6];
        this.rvDetails = (RecyclerView) bindings[4];
        this.slView = (LinearLayout) bindings[2];
        this.tvCryptoAmount = (TextView) bindings[3];
        this.tvWarningMessage = (TextView) bindings[5];
        this.tvWorldPayProcessingHeader = (TextView) bindings[8];
        this.tvWorldPayProcessingText = (TextView) bindings[9];
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

    public static ControllerBuysellConfirmationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerBuysellConfirmationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerBuysellConfirmationBinding) DataBindingUtil.inflate(inflater, R.layout.controller_buysell_confirmation, root, attachToRoot, bindingComponent);
    }

    public static ControllerBuysellConfirmationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerBuysellConfirmationBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_buysell_confirmation, null, false), bindingComponent);
    }

    public static ControllerBuysellConfirmationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerBuysellConfirmationBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_buysell_confirmation_0".equals(view.getTag())) {
            return new ControllerBuysellConfirmationBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
