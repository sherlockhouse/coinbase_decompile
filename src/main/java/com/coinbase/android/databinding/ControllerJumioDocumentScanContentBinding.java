package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerJumioDocumentScanContentBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnContinue;
    public final TextView btnRetake;
    public final ImageView ivJumioImagePreview;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final TextView tvJumioScanPrompt;
    public final TextView tvScanTheFrontBack;

    static {
        sViewsWithIds.put(R.id.tvScanTheFrontBack, 1);
        sViewsWithIds.put(R.id.tvJumioScanPrompt, 2);
        sViewsWithIds.put(R.id.ivJumioImagePreview, 3);
        sViewsWithIds.put(R.id.btnContinue, 4);
        sViewsWithIds.put(R.id.btnRetake, 5);
    }

    public ControllerJumioDocumentScanContentBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.btnContinue = (Button) bindings[4];
        this.btnRetake = (TextView) bindings[5];
        this.ivJumioImagePreview = (ImageView) bindings[3];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvJumioScanPrompt = (TextView) bindings[2];
        this.tvScanTheFrontBack = (TextView) bindings[1];
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

    public static ControllerJumioDocumentScanContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerJumioDocumentScanContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerJumioDocumentScanContentBinding) DataBindingUtil.inflate(inflater, R.layout.controller_jumio_document_scan_content, root, attachToRoot, bindingComponent);
    }

    public static ControllerJumioDocumentScanContentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerJumioDocumentScanContentBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_jumio_document_scan_content, null, false), bindingComponent);
    }

    public static ControllerJumioDocumentScanContentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerJumioDocumentScanContentBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_jumio_document_scan_content_0".equals(view.getTag())) {
            return new ControllerJumioDocumentScanContentBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
