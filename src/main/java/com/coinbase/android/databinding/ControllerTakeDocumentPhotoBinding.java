package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.coinbase.android.R;

public class ControllerTakeDocumentPhotoBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(2);
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final ControllerTakeDocumentPhotoContentBinding takeDocumentPhotoContent;

    static {
        sIncludes.setIncludes(0, new String[]{"controller_take_document_photo_content"}, new int[]{1}, new int[]{R.layout.controller_take_document_photo_content});
    }

    public ControllerTakeDocumentPhotoBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.takeDocumentPhotoContent = (ControllerTakeDocumentPhotoContentBinding) bindings[1];
        setContainedBinding(this.takeDocumentPhotoContent);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.takeDocumentPhotoContent.invalidateAll();
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
                return onChangeTakeDocumentPhotoContent((ControllerTakeDocumentPhotoContentBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeTakeDocumentPhotoContent(ControllerTakeDocumentPhotoContentBinding TakeDocumentPhotoContent, int fieldId) {
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
        ViewDataBinding.executeBindingsOn(this.takeDocumentPhotoContent);
    }

    public static ControllerTakeDocumentPhotoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerTakeDocumentPhotoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerTakeDocumentPhotoBinding) DataBindingUtil.inflate(inflater, R.layout.controller_take_document_photo, root, attachToRoot, bindingComponent);
    }

    public static ControllerTakeDocumentPhotoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerTakeDocumentPhotoBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_take_document_photo, null, false), bindingComponent);
    }

    public static ControllerTakeDocumentPhotoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerTakeDocumentPhotoBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_take_document_photo_0".equals(view.getTag())) {
            return new ControllerTakeDocumentPhotoBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
