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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class OverlayBuysellDepositBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnSubscribe;
    public final ImageView ivOverlayIcon;
    public final LinearLayout llOverlay;
    public final LinearLayout llUnavailable;
    public final ListView lvOverlayQuickstart;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlQuickStart;
    public final TextView tvOverlayDetails;
    public final TextView tvUnavailableNotice;

    static {
        sViewsWithIds.put(R.id.llOverlay, 1);
        sViewsWithIds.put(R.id.ivOverlayIcon, 2);
        sViewsWithIds.put(R.id.tvOverlayDetails, 3);
        sViewsWithIds.put(R.id.lvOverlayQuickstart, 4);
        sViewsWithIds.put(R.id.llUnavailable, 5);
        sViewsWithIds.put(R.id.tvUnavailableNotice, 6);
        sViewsWithIds.put(R.id.btnSubscribe, 7);
    }

    public OverlayBuysellDepositBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.btnSubscribe = (Button) bindings[7];
        this.ivOverlayIcon = (ImageView) bindings[2];
        this.llOverlay = (LinearLayout) bindings[1];
        this.llUnavailable = (LinearLayout) bindings[5];
        this.lvOverlayQuickstart = (ListView) bindings[4];
        this.rlQuickStart = (RelativeLayout) bindings[0];
        this.rlQuickStart.setTag(null);
        this.tvOverlayDetails = (TextView) bindings[3];
        this.tvUnavailableNotice = (TextView) bindings[6];
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

    public static OverlayBuysellDepositBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static OverlayBuysellDepositBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (OverlayBuysellDepositBinding) DataBindingUtil.inflate(inflater, R.layout.overlay_buysell_deposit, root, attachToRoot, bindingComponent);
    }

    public static OverlayBuysellDepositBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static OverlayBuysellDepositBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.overlay_buysell_deposit, null, false), bindingComponent);
    }

    public static OverlayBuysellDepositBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static OverlayBuysellDepositBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/overlay_buysell_deposit_0".equals(view.getTag())) {
            return new OverlayBuysellDepositBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
