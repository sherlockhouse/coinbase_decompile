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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountItemLayout;
import com.coinbase.android.ui.AutoResizeTextView;
import com.coinbase.android.ui.NumericKeyboardLayout;
import com.coinbase.android.wbl.AvailableBalanceAppBarLayout;

public class ControllerSellBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(17);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AvailableBalanceAppBarLayout apbLayout;
    public final Button btPreview;
    public final ImageView ivSwap;
    public final LinearLayout llSellButtons;
    public final LinearLayout llSwap;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final NumericKeyboardLayout numericKeyboardLayout;
    public final ProgressBar progressBar;
    public final OverlayBuysellDepositBinding quickstartOverlay;
    public final RelativeLayout rlProgressBarOverlay;
    public final RelativeLayout rlSellContent;
    public final LinkedAccountItemLayout sellLinkedAccountItemLayout;
    public final AutoResizeTextView tvAmount;
    public final TextView tvSecondaryCurrencyCode;
    public final TextView tvSellFourth;
    public final TextView tvSellHalf;
    public final TextView tvSellMax;

    static {
        sIncludes.setIncludes(0, new String[]{"overlay_buysell_deposit"}, new int[]{1}, new int[]{R.layout.overlay_buysell_deposit});
        sViewsWithIds.put(R.id.apbLayout, 2);
        sViewsWithIds.put(R.id.rlSellContent, 3);
        sViewsWithIds.put(R.id.tvAmount, 4);
        sViewsWithIds.put(R.id.llSwap, 5);
        sViewsWithIds.put(R.id.ivSwap, 6);
        sViewsWithIds.put(R.id.tvSecondaryCurrencyCode, 7);
        sViewsWithIds.put(R.id.llSellButtons, 8);
        sViewsWithIds.put(R.id.tvSellMax, 9);
        sViewsWithIds.put(R.id.tvSellHalf, 10);
        sViewsWithIds.put(R.id.tvSellFourth, 11);
        sViewsWithIds.put(R.id.sellLinkedAccountItemLayout, 12);
        sViewsWithIds.put(R.id.numericKeyboardLayout, 13);
        sViewsWithIds.put(R.id.btPreview, 14);
        sViewsWithIds.put(R.id.rlProgressBarOverlay, 15);
        sViewsWithIds.put(R.id.progressBar, 16);
    }

    public ControllerSellBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds);
        this.apbLayout = (AvailableBalanceAppBarLayout) bindings[2];
        this.btPreview = (Button) bindings[14];
        this.ivSwap = (ImageView) bindings[6];
        this.llSellButtons = (LinearLayout) bindings[8];
        this.llSwap = (LinearLayout) bindings[5];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.numericKeyboardLayout = (NumericKeyboardLayout) bindings[13];
        this.progressBar = (ProgressBar) bindings[16];
        this.quickstartOverlay = (OverlayBuysellDepositBinding) bindings[1];
        setContainedBinding(this.quickstartOverlay);
        this.rlProgressBarOverlay = (RelativeLayout) bindings[15];
        this.rlSellContent = (RelativeLayout) bindings[3];
        this.sellLinkedAccountItemLayout = (LinkedAccountItemLayout) bindings[12];
        this.tvAmount = (AutoResizeTextView) bindings[4];
        this.tvSecondaryCurrencyCode = (TextView) bindings[7];
        this.tvSellFourth = (TextView) bindings[11];
        this.tvSellHalf = (TextView) bindings[10];
        this.tvSellMax = (TextView) bindings[9];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.quickstartOverlay.invalidateAll();
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
                return onChangeQuickstartOverlay((OverlayBuysellDepositBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeQuickstartOverlay(OverlayBuysellDepositBinding QuickstartOverlay, int fieldId) {
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
        ViewDataBinding.executeBindingsOn(this.quickstartOverlay);
    }

    public static ControllerSellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerSellBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerSellBinding) DataBindingUtil.inflate(inflater, R.layout.controller_sell, root, attachToRoot, bindingComponent);
    }

    public static ControllerSellBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerSellBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_sell, null, false), bindingComponent);
    }

    public static ControllerSellBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerSellBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_sell_0".equals(view.getTag())) {
            return new ControllerSellBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
