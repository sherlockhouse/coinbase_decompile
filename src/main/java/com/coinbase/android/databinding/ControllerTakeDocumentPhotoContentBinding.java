package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.identityverification.CustomSurfaceView;

public class ControllerTakeDocumentPhotoContentBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final CustomSurfaceView cvJumioCameraImage;
    public final ImageButton ibtnFlashlight;
    public final ImageButton ibtnJumioBack;
    public final ImageButton ibtnTakePhoto;
    public final ImageButton ibtnTakePhotoLandscape;
    public final View jumioBottomOverlay;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final RelativeLayout rlJumioButtonsContainer;
    public final RelativeLayout rlJumioButtonsContainerLandscape;
    public final RelativeLayout rlJumioScanTitleBar;
    public final TextView tvJumioTitle;
    public final View vJumioBlankSpace;
    public final View vJumioScanGuides;
    public final View vJumioTopOverlay;

    static {
        sViewsWithIds.put(R.id.cvJumioCameraImage, 1);
        sViewsWithIds.put(R.id.vJumioTopOverlay, 2);
        sViewsWithIds.put(R.id.vJumioBlankSpace, 3);
        sViewsWithIds.put(R.id.jumio_bottom_overlay, 4);
        sViewsWithIds.put(R.id.rlJumioButtonsContainer, 5);
        sViewsWithIds.put(R.id.ibtnTakePhoto, 6);
        sViewsWithIds.put(R.id.vJumioScanGuides, 7);
        sViewsWithIds.put(R.id.rlJumioScanTitleBar, 8);
        sViewsWithIds.put(R.id.ibtnJumioBack, 9);
        sViewsWithIds.put(R.id.tvJumioTitle, 10);
        sViewsWithIds.put(R.id.ibtnFlashlight, 11);
        sViewsWithIds.put(R.id.rlJumioButtonsContainerLandscape, 12);
        sViewsWithIds.put(R.id.ibtnTakePhotoLandscape, 13);
    }

    public ControllerTakeDocumentPhotoContentBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds);
        this.cvJumioCameraImage = (CustomSurfaceView) bindings[1];
        this.ibtnFlashlight = (ImageButton) bindings[11];
        this.ibtnJumioBack = (ImageButton) bindings[9];
        this.ibtnTakePhoto = (ImageButton) bindings[6];
        this.ibtnTakePhotoLandscape = (ImageButton) bindings[13];
        this.jumioBottomOverlay = (View) bindings[4];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlJumioButtonsContainer = (RelativeLayout) bindings[5];
        this.rlJumioButtonsContainerLandscape = (RelativeLayout) bindings[12];
        this.rlJumioScanTitleBar = (RelativeLayout) bindings[8];
        this.tvJumioTitle = (TextView) bindings[10];
        this.vJumioBlankSpace = (View) bindings[3];
        this.vJumioScanGuides = (View) bindings[7];
        this.vJumioTopOverlay = (View) bindings[2];
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

    public static ControllerTakeDocumentPhotoContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerTakeDocumentPhotoContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerTakeDocumentPhotoContentBinding) DataBindingUtil.inflate(inflater, R.layout.controller_take_document_photo_content, root, attachToRoot, bindingComponent);
    }

    public static ControllerTakeDocumentPhotoContentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerTakeDocumentPhotoContentBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_take_document_photo_content, null, false), bindingComponent);
    }

    public static ControllerTakeDocumentPhotoContentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerTakeDocumentPhotoContentBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_take_document_photo_content_0".equals(view.getTag())) {
            return new ControllerTakeDocumentPhotoContentBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
