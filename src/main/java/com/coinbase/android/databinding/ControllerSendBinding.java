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
import com.coinbase.android.ui.AutoResizeTextView;
import com.coinbase.android.ui.NumericKeyboardLayout;
import com.coinbase.android.wbl.AvailableBalanceAppBarLayout;

public class ControllerSendBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AvailableBalanceAppBarLayout apbLayout;
    public final Button btnContinue;
    public final ImageView ivSwap;
    public final RelativeLayout llAmount;
    public final LinearLayout llSendMaxContainer;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final NumericKeyboardLayout numericKeyboardLayout;
    public final ProgressBar progressBar;
    public final RelativeLayout rlSendContent;
    public final AutoResizeTextView tvAmount;
    public final TextView tvSendAll;
    public final TextView tvSendFourth;
    public final TextView tvSendHalf;

    static {
        sViewsWithIds.put(R.id.apbLayout, 1);
        sViewsWithIds.put(R.id.rlSendContent, 2);
        sViewsWithIds.put(R.id.llAmount, 3);
        sViewsWithIds.put(R.id.tvAmount, 4);
        sViewsWithIds.put(R.id.ivSwap, 5);
        sViewsWithIds.put(R.id.llSendMaxContainer, 6);
        sViewsWithIds.put(R.id.tvSendAll, 7);
        sViewsWithIds.put(R.id.tvSendHalf, 8);
        sViewsWithIds.put(R.id.tvSendFourth, 9);
        sViewsWithIds.put(R.id.numericKeyboardLayout, 10);
        sViewsWithIds.put(R.id.btnContinue, 11);
        sViewsWithIds.put(R.id.progressBar, 12);
    }

    public ControllerSendBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.apbLayout = (AvailableBalanceAppBarLayout) bindings[1];
        this.btnContinue = (Button) bindings[11];
        this.ivSwap = (ImageView) bindings[5];
        this.llAmount = (RelativeLayout) bindings[3];
        this.llSendMaxContainer = (LinearLayout) bindings[6];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.numericKeyboardLayout = (NumericKeyboardLayout) bindings[10];
        this.progressBar = (ProgressBar) bindings[12];
        this.rlSendContent = (RelativeLayout) bindings[2];
        this.tvAmount = (AutoResizeTextView) bindings[4];
        this.tvSendAll = (TextView) bindings[7];
        this.tvSendFourth = (TextView) bindings[9];
        this.tvSendHalf = (TextView) bindings[8];
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

    public static ControllerSendBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerSendBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerSendBinding) DataBindingUtil.inflate(inflater, R.layout.controller_send, root, attachToRoot, bindingComponent);
    }

    public static ControllerSendBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerSendBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_send, null, false), bindingComponent);
    }

    public static ControllerSendBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerSendBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_send_0".equals(view.getTag())) {
            return new ControllerSendBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
