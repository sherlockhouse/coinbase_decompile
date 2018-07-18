package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerAcceptTermsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView imageView8;
    public final LinearLayout linearLayout5;
    public final View linearLayout6;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RelativeLayout progress;
    public final TextView textView9;
    public final TextView tvSignupTncAgree;
    public final TextView tvSignupTncCancel;
    public final TextView tvSignupTncDescription;

    static {
        sViewsWithIds.put(R.id.progress, 1);
        sViewsWithIds.put(R.id.imageView8, 2);
        sViewsWithIds.put(R.id.textView9, 3);
        sViewsWithIds.put(R.id.tvSignupTncDescription, 4);
        sViewsWithIds.put(R.id.linearLayout6, 5);
        sViewsWithIds.put(R.id.linearLayout5, 6);
        sViewsWithIds.put(R.id.tvSignupTncCancel, 7);
        sViewsWithIds.put(R.id.tvSignupTncAgree, 8);
    }

    public ControllerAcceptTermsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.imageView8 = (ImageView) bindings[2];
        this.linearLayout5 = (LinearLayout) bindings[6];
        this.linearLayout6 = (View) bindings[5];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (RelativeLayout) bindings[1];
        this.textView9 = (TextView) bindings[3];
        this.tvSignupTncAgree = (TextView) bindings[8];
        this.tvSignupTncCancel = (TextView) bindings[7];
        this.tvSignupTncDescription = (TextView) bindings[4];
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

    public static ControllerAcceptTermsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerAcceptTermsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerAcceptTermsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_accept_terms, root, attachToRoot, bindingComponent);
    }

    public static ControllerAcceptTermsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerAcceptTermsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_accept_terms, null, false), bindingComponent);
    }

    public static ControllerAcceptTermsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerAcceptTermsBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_accept_terms_0".equals(view.getTag())) {
            return new ControllerAcceptTermsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
