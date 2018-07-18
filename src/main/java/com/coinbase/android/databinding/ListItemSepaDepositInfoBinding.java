package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemSepaDepositInfoBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageButton ibtnCopy;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final TextView tvSepaContent;
    public final TextView tvSepaDetail;
    public final TextView tvSepaTitle;

    static {
        sViewsWithIds.put(R.id.tvSepaTitle, 1);
        sViewsWithIds.put(R.id.tvSepaContent, 2);
        sViewsWithIds.put(R.id.tvSepaDetail, 3);
        sViewsWithIds.put(R.id.ibtnCopy, 4);
    }

    public ListItemSepaDepositInfoBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.ibtnCopy = (ImageButton) bindings[4];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvSepaContent = (TextView) bindings[2];
        this.tvSepaDetail = (TextView) bindings[3];
        this.tvSepaTitle = (TextView) bindings[1];
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

    public static ListItemSepaDepositInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemSepaDepositInfoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemSepaDepositInfoBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_sepa_deposit_info, root, attachToRoot, bindingComponent);
    }

    public static ListItemSepaDepositInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemSepaDepositInfoBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_sepa_deposit_info, null, false), bindingComponent);
    }

    public static ListItemSepaDepositInfoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemSepaDepositInfoBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_sepa_deposit_info_0".equals(view.getTag())) {
            return new ListItemSepaDepositInfoBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
