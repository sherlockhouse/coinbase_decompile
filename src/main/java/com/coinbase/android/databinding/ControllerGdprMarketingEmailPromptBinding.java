package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerGdprMarketingEmailPromptBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnNo;
    public final TextView btnYes;
    public final LinearLayout llWhiteBoxContainer;
    private long mDirtyFlags = -1;
    public final ProgressBar progressBar;
    public final RelativeLayout rlContainer;
    public final TextView tvTitle;

    static {
        sViewsWithIds.put(R.id.llWhiteBoxContainer, 1);
        sViewsWithIds.put(R.id.tvTitle, 2);
        sViewsWithIds.put(R.id.btnYes, 3);
        sViewsWithIds.put(R.id.btnNo, 4);
        sViewsWithIds.put(R.id.progressBar, 5);
    }

    public ControllerGdprMarketingEmailPromptBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.btnNo = (TextView) bindings[4];
        this.btnYes = (TextView) bindings[3];
        this.llWhiteBoxContainer = (LinearLayout) bindings[1];
        this.progressBar = (ProgressBar) bindings[5];
        this.rlContainer = (RelativeLayout) bindings[0];
        this.rlContainer.setTag(null);
        this.tvTitle = (TextView) bindings[2];
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

    public static ControllerGdprMarketingEmailPromptBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprMarketingEmailPromptBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerGdprMarketingEmailPromptBinding) DataBindingUtil.inflate(inflater, R.layout.controller_gdpr_marketing_email_prompt, root, attachToRoot, bindingComponent);
    }

    public static ControllerGdprMarketingEmailPromptBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprMarketingEmailPromptBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_gdpr_marketing_email_prompt, null, false), bindingComponent);
    }

    public static ControllerGdprMarketingEmailPromptBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprMarketingEmailPromptBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_gdpr_marketing_email_prompt_0".equals(view.getTag())) {
            return new ControllerGdprMarketingEmailPromptBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
