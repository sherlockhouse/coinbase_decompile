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

public class ListItemIdologyQuestionBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivArrow;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlAnswerPrompt;
    public final LinearLayout rlIdologyQuestion;
    public final TextView tvAnswer;
    public final TextView tvAnswerHint;
    public final TextView tvQuestion;

    static {
        sViewsWithIds.put(R.id.tvQuestion, 1);
        sViewsWithIds.put(R.id.rlAnswerPrompt, 2);
        sViewsWithIds.put(R.id.tvAnswer, 3);
        sViewsWithIds.put(R.id.tvAnswerHint, 4);
        sViewsWithIds.put(R.id.ivArrow, 5);
    }

    public ListItemIdologyQuestionBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.ivArrow = (ImageView) bindings[5];
        this.rlAnswerPrompt = (RelativeLayout) bindings[2];
        this.rlIdologyQuestion = (LinearLayout) bindings[0];
        this.rlIdologyQuestion.setTag(null);
        this.tvAnswer = (TextView) bindings[3];
        this.tvAnswerHint = (TextView) bindings[4];
        this.tvQuestion = (TextView) bindings[1];
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

    public static ListItemIdologyQuestionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemIdologyQuestionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemIdologyQuestionBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_idology_question, root, attachToRoot, bindingComponent);
    }

    public static ListItemIdologyQuestionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemIdologyQuestionBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_idology_question, null, false), bindingComponent);
    }

    public static ListItemIdologyQuestionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemIdologyQuestionBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_idology_question_0".equals(view.getTag())) {
            return new ListItemIdologyQuestionBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
