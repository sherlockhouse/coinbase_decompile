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
import android.widget.TextView;
import com.coinbase.android.R;

public class PlaidLoginAllDoneBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnPlaidAllDoneSubmit;
    public final LinearLayout llPlaidAllDone;
    private long mDirtyFlags = -1;
    public final TextView tvPlaidAllDoneDetails;

    static {
        sViewsWithIds.put(R.id.tvPlaidAllDoneDetails, 1);
        sViewsWithIds.put(R.id.btnPlaidAllDoneSubmit, 2);
    }

    public PlaidLoginAllDoneBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.btnPlaidAllDoneSubmit = (Button) bindings[2];
        this.llPlaidAllDone = (LinearLayout) bindings[0];
        this.llPlaidAllDone.setTag(null);
        this.tvPlaidAllDoneDetails = (TextView) bindings[1];
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

    public static PlaidLoginAllDoneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static PlaidLoginAllDoneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (PlaidLoginAllDoneBinding) DataBindingUtil.inflate(inflater, R.layout.plaid_login_all_done, root, attachToRoot, bindingComponent);
    }

    public static PlaidLoginAllDoneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static PlaidLoginAllDoneBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.plaid_login_all_done, null, false), bindingComponent);
    }

    public static PlaidLoginAllDoneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static PlaidLoginAllDoneBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/plaid_login_all_done_0".equals(view.getTag())) {
            return new PlaidLoginAllDoneBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
