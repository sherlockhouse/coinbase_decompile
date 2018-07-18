package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.idology.IdologyQuestionsLayout;

public class ControllerStateIdologyQuestionsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnIdologyContinue;
    public final IdologyQuestionsLayout idologyQuestionsLayout;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RelativeLayout progress;
    public final RelativeLayout rlQuestionsContainer;
    public final View vBottomView;
    public final View vButtonDivider;

    static {
        sViewsWithIds.put(R.id.progress, 1);
        sViewsWithIds.put(R.id.rlQuestionsContainer, 2);
        sViewsWithIds.put(R.id.idologyQuestionsLayout, 3);
        sViewsWithIds.put(R.id.vButtonDivider, 4);
        sViewsWithIds.put(R.id.btnIdologyContinue, 5);
        sViewsWithIds.put(R.id.vBottomView, 6);
    }

    public ControllerStateIdologyQuestionsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.btnIdologyContinue = (TextView) bindings[5];
        this.idologyQuestionsLayout = (IdologyQuestionsLayout) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (RelativeLayout) bindings[1];
        this.rlQuestionsContainer = (RelativeLayout) bindings[2];
        this.vBottomView = (View) bindings[6];
        this.vButtonDivider = (View) bindings[4];
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

    public static ControllerStateIdologyQuestionsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateIdologyQuestionsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerStateIdologyQuestionsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_idology_questions, root, attachToRoot, bindingComponent);
    }

    public static ControllerStateIdologyQuestionsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateIdologyQuestionsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_state_idology_questions, null, false), bindingComponent);
    }

    public static ControllerStateIdologyQuestionsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateIdologyQuestionsBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_state_idology_questions_0".equals(view.getTag())) {
            return new ControllerStateIdologyQuestionsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
