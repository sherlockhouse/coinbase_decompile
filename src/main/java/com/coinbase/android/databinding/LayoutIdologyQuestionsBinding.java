package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class LayoutIdologyQuestionsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final RelativeLayout rlTimer;
    public final RecyclerView rvQuestions;
    public final TextView tvTimerText;
    public final TextView tvTimerValue;

    static {
        sViewsWithIds.put(R.id.rvQuestions, 1);
        sViewsWithIds.put(R.id.rlTimer, 2);
        sViewsWithIds.put(R.id.tvTimerText, 3);
        sViewsWithIds.put(R.id.tvTimerValue, 4);
    }

    public LayoutIdologyQuestionsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlTimer = (RelativeLayout) bindings[2];
        this.rvQuestions = (RecyclerView) bindings[1];
        this.tvTimerText = (TextView) bindings[3];
        this.tvTimerValue = (TextView) bindings[4];
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

    public static LayoutIdologyQuestionsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyQuestionsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutIdologyQuestionsBinding) DataBindingUtil.inflate(inflater, R.layout.layout_idology_questions, root, attachToRoot, bindingComponent);
    }

    public static LayoutIdologyQuestionsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyQuestionsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_idology_questions, null, false), bindingComponent);
    }

    public static LayoutIdologyQuestionsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutIdologyQuestionsBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_idology_questions_0".equals(view.getTag())) {
            return new LayoutIdologyQuestionsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
