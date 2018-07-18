package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.EmptySupportRecyclerView;

public class FragmentPhoneNumberBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Toolbar cvCoinbaseToolbar;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final ProgressBar progress;
    public final RelativeLayout rlEmptyView;
    public final EmptySupportRecyclerView rvPhoneNumbers;
    public final TextView tvEmpty;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.rlEmptyView, 2);
        sViewsWithIds.put(R.id.tvEmpty, 3);
        sViewsWithIds.put(R.id.rvPhoneNumbers, 4);
        sViewsWithIds.put(R.id.progress, 5);
    }

    public FragmentPhoneNumberBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (ProgressBar) bindings[5];
        this.rlEmptyView = (RelativeLayout) bindings[2];
        this.rvPhoneNumbers = (EmptySupportRecyclerView) bindings[4];
        this.tvEmpty = (TextView) bindings[3];
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

    public static FragmentPhoneNumberBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPhoneNumberBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentPhoneNumberBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_phone_number, root, attachToRoot, bindingComponent);
    }

    public static FragmentPhoneNumberBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPhoneNumberBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_phone_number, null, false), bindingComponent);
    }

    public static FragmentPhoneNumberBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPhoneNumberBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_phone_number_0".equals(view.getTag())) {
            return new FragmentPhoneNumberBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
