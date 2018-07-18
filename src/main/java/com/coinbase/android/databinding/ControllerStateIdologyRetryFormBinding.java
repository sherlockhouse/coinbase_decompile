package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.idology.IdologyFormLayout;

public class ControllerStateIdologyRetryFormBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnIdologyContinue;
    public final IdologyFormLayout idologyFormLayout;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final RelativeLayout progress;
    public final RelativeLayout rlFormContainer;
    public final TextView tvErrorExplanation;
    public final View vBottomDivider;
    public final View vMiddleDivider;
    public final View vTopDivider;
    public final View vTopDivider2;

    static {
        sViewsWithIds.put(R.id.progress, 1);
        sViewsWithIds.put(R.id.rlFormContainer, 2);
        sViewsWithIds.put(R.id.vTopDivider, 3);
        sViewsWithIds.put(R.id.tvErrorExplanation, 4);
        sViewsWithIds.put(R.id.vTopDivider2, 5);
        sViewsWithIds.put(R.id.idologyFormLayout, 6);
        sViewsWithIds.put(R.id.vMiddleDivider, 7);
        sViewsWithIds.put(R.id.btnIdologyContinue, 8);
        sViewsWithIds.put(R.id.vBottomDivider, 9);
    }

    public ControllerStateIdologyRetryFormBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.btnIdologyContinue = (TextView) bindings[8];
        this.idologyFormLayout = (IdologyFormLayout) bindings[6];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (RelativeLayout) bindings[1];
        this.rlFormContainer = (RelativeLayout) bindings[2];
        this.tvErrorExplanation = (TextView) bindings[4];
        this.vBottomDivider = (View) bindings[9];
        this.vMiddleDivider = (View) bindings[7];
        this.vTopDivider = (View) bindings[3];
        this.vTopDivider2 = (View) bindings[5];
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

    public static ControllerStateIdologyRetryFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateIdologyRetryFormBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerStateIdologyRetryFormBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_idology_retry_form, root, attachToRoot, bindingComponent);
    }

    public static ControllerStateIdologyRetryFormBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateIdologyRetryFormBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_state_idology_retry_form, null, false), bindingComponent);
    }

    public static ControllerStateIdologyRetryFormBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateIdologyRetryFormBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_state_idology_retry_form_0".equals(view.getTag())) {
            return new ControllerStateIdologyRetryFormBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
