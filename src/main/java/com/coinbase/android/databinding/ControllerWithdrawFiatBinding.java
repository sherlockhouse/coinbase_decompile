package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.coinbase.android.R;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountItemLayout;
import com.coinbase.android.ui.AutoResizeTextView;
import com.coinbase.android.ui.NumericKeyboardLayout;
import com.coinbase.android.wbl.AvailableBalanceAppBarLayout;

public class ControllerWithdrawFiatBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(9);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AvailableBalanceAppBarLayout apbLayout;
    public final Button btPreview;
    public final LinkedAccountItemLayout linkedAccountItemLayout;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final NumericKeyboardLayout numericKeyboardLayout;
    public final ProgressBar progressBar;
    public final OverlayBuysellDepositBinding quickstartContent;
    public final RelativeLayout rlWithdrawContent;
    public final AutoResizeTextView tvAmount;

    static {
        sIncludes.setIncludes(0, new String[]{"overlay_buysell_deposit"}, new int[]{1}, new int[]{R.layout.overlay_buysell_deposit});
        sViewsWithIds.put(R.id.apbLayout, 2);
        sViewsWithIds.put(R.id.rlWithdrawContent, 3);
        sViewsWithIds.put(R.id.tvAmount, 4);
        sViewsWithIds.put(R.id.linkedAccountItemLayout, 5);
        sViewsWithIds.put(R.id.numericKeyboardLayout, 6);
        sViewsWithIds.put(R.id.btPreview, 7);
        sViewsWithIds.put(R.id.progressBar, 8);
    }

    public ControllerWithdrawFiatBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.apbLayout = (AvailableBalanceAppBarLayout) bindings[2];
        this.btPreview = (Button) bindings[7];
        this.linkedAccountItemLayout = (LinkedAccountItemLayout) bindings[5];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.numericKeyboardLayout = (NumericKeyboardLayout) bindings[6];
        this.progressBar = (ProgressBar) bindings[8];
        this.quickstartContent = (OverlayBuysellDepositBinding) bindings[1];
        setContainedBinding(this.quickstartContent);
        this.rlWithdrawContent = (RelativeLayout) bindings[3];
        this.tvAmount = (AutoResizeTextView) bindings[4];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.quickstartContent.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
        }
    }

    public boolean setVariable(int variableId, Object variable) {
        return false;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeQuickstartContent((OverlayBuysellDepositBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeQuickstartContent(OverlayBuysellDepositBinding QuickstartContent, int fieldId) {
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            default:
                return false;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ViewDataBinding.executeBindingsOn(this.quickstartContent);
    }

    public static ControllerWithdrawFiatBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerWithdrawFiatBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerWithdrawFiatBinding) DataBindingUtil.inflate(inflater, R.layout.controller_withdraw_fiat, root, attachToRoot, bindingComponent);
    }

    public static ControllerWithdrawFiatBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerWithdrawFiatBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_withdraw_fiat, null, false), bindingComponent);
    }

    public static ControllerWithdrawFiatBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerWithdrawFiatBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_withdraw_fiat_0".equals(view.getTag())) {
            return new ControllerWithdrawFiatBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
