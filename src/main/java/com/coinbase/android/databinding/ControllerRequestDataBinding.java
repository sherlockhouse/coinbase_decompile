package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerRequestDataBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AppBarLayout abLayout;
    public final Button btSendRequest;
    public final RelativeLayout clContainer;
    public final Toolbar cvCoinbaseToolbar;
    public final EditText etAddlMessage;
    private long mDirtyFlags = -1;
    public final ProgressBar progressBar;
    public final RecyclerView rvRequestDataOptions;
    public final TextView tvAddlMessage;
    public final TextView tvLegalHeader;

    static {
        sViewsWithIds.put(R.id.abLayout, 1);
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 2);
        sViewsWithIds.put(R.id.tvLegalHeader, 3);
        sViewsWithIds.put(R.id.rvRequestDataOptions, 4);
        sViewsWithIds.put(R.id.tvAddlMessage, 5);
        sViewsWithIds.put(R.id.etAddlMessage, 6);
        sViewsWithIds.put(R.id.progressBar, 7);
        sViewsWithIds.put(R.id.btSendRequest, 8);
    }

    public ControllerRequestDataBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.abLayout = (AppBarLayout) bindings[1];
        this.btSendRequest = (Button) bindings[8];
        this.clContainer = (RelativeLayout) bindings[0];
        this.clContainer.setTag(null);
        this.cvCoinbaseToolbar = (Toolbar) bindings[2];
        this.etAddlMessage = (EditText) bindings[6];
        this.progressBar = (ProgressBar) bindings[7];
        this.rvRequestDataOptions = (RecyclerView) bindings[4];
        this.tvAddlMessage = (TextView) bindings[5];
        this.tvLegalHeader = (TextView) bindings[3];
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

    public static ControllerRequestDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerRequestDataBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerRequestDataBinding) DataBindingUtil.inflate(inflater, R.layout.controller_request_data, root, attachToRoot, bindingComponent);
    }

    public static ControllerRequestDataBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerRequestDataBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_request_data, null, false), bindingComponent);
    }

    public static ControllerRequestDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerRequestDataBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_request_data_0".equals(view.getTag())) {
            return new ControllerRequestDataBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
