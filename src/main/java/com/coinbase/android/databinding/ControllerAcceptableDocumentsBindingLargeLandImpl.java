package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.coinbase.android.R;

public class ControllerAcceptableDocumentsBindingLargeLandImpl extends ControllerAcceptableDocumentsBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(5);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final FrameLayout mboundView1;

    static {
        sIncludes.setIncludes(1, new String[]{"controller_acceptable_documents_content"}, new int[]{2}, new int[]{R.layout.controller_acceptable_documents_content});
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 3);
        sViewsWithIds.put(R.id.progress, 4);
    }

    public ControllerAcceptableDocumentsBindingLargeLandImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ControllerAcceptableDocumentsBindingLargeLandImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ControllerAcceptableDocumentsContentBinding) bindings[2], (Toolbar) bindings[3], (ProgressBar) bindings[4]);
        this.mDirtyFlags = -1;
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (FrameLayout) bindings[1];
        this.mboundView1.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.acceptableDocumentsContent.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
        }
    }

    public boolean setVariable(int variableId, Object variable) {
        return false;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeAcceptableDocumentsContent((ControllerAcceptableDocumentsContentBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeAcceptableDocumentsContent(ControllerAcceptableDocumentsContentBinding AcceptableDocumentsContent, int fieldId) {
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            default:
                return false;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ViewDataBinding.executeBindingsOn(this.acceptableDocumentsContent);
    }
}
