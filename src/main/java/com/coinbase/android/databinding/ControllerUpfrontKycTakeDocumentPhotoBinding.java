package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.identityverification.CustomSurfaceView;

public class ControllerUpfrontKycTakeDocumentPhotoBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Toolbar cvCoinbaseToolbar;
    public final CustomSurfaceView cvJumioCameraImage;
    public final FrameLayout flBlankSpaceContainer;
    public final LinearLayout flFacematchContainer;
    public final FrameLayout flHeaderContainer;
    public final ImageButton ibtnTakePhoto;
    public final View jumioBottomOverlay;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlContainer;
    public final RelativeLayout rlJumioButtonsContainer;
    public final RecyclerView rvHints;
    public final TextView tvFlashLight;
    public final TextView tvTakeDocumentHeader;
    public final View vBlankSpaceLeftMargin;
    public final View vBlankSpaceRightMargin;
    public final View vJumioBlankSpace;
    public final View vJumioScanGuides;

    static {
        sViewsWithIds.put(R.id.cvJumioCameraImage, 1);
        sViewsWithIds.put(R.id.flHeaderContainer, 2);
        sViewsWithIds.put(R.id.tvTakeDocumentHeader, 3);
        sViewsWithIds.put(R.id.vBlankSpaceLeftMargin, 4);
        sViewsWithIds.put(R.id.flBlankSpaceContainer, 5);
        sViewsWithIds.put(R.id.vJumioBlankSpace, 6);
        sViewsWithIds.put(R.id.vJumioScanGuides, 7);
        sViewsWithIds.put(R.id.vBlankSpaceRightMargin, 8);
        sViewsWithIds.put(R.id.flFacematchContainer, 9);
        sViewsWithIds.put(R.id.jumio_bottom_overlay, 10);
        sViewsWithIds.put(R.id.rlJumioButtonsContainer, 11);
        sViewsWithIds.put(R.id.rvHints, 12);
        sViewsWithIds.put(R.id.ibtnTakePhoto, 13);
        sViewsWithIds.put(R.id.tvFlashLight, 14);
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 15);
    }

    public ControllerUpfrontKycTakeDocumentPhotoBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds);
        this.cvCoinbaseToolbar = (Toolbar) bindings[15];
        this.cvJumioCameraImage = (CustomSurfaceView) bindings[1];
        this.flBlankSpaceContainer = (FrameLayout) bindings[5];
        this.flFacematchContainer = (LinearLayout) bindings[9];
        this.flHeaderContainer = (FrameLayout) bindings[2];
        this.ibtnTakePhoto = (ImageButton) bindings[13];
        this.jumioBottomOverlay = (View) bindings[10];
        this.rlContainer = (RelativeLayout) bindings[0];
        this.rlContainer.setTag(null);
        this.rlJumioButtonsContainer = (RelativeLayout) bindings[11];
        this.rvHints = (RecyclerView) bindings[12];
        this.tvFlashLight = (TextView) bindings[14];
        this.tvTakeDocumentHeader = (TextView) bindings[3];
        this.vBlankSpaceLeftMargin = (View) bindings[4];
        this.vBlankSpaceRightMargin = (View) bindings[8];
        this.vJumioBlankSpace = (View) bindings[6];
        this.vJumioScanGuides = (View) bindings[7];
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

    public static ControllerUpfrontKycTakeDocumentPhotoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycTakeDocumentPhotoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerUpfrontKycTakeDocumentPhotoBinding) DataBindingUtil.inflate(inflater, R.layout.controller_upfront_kyc_take_document_photo, root, attachToRoot, bindingComponent);
    }

    public static ControllerUpfrontKycTakeDocumentPhotoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycTakeDocumentPhotoBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_upfront_kyc_take_document_photo, null, false), bindingComponent);
    }

    public static ControllerUpfrontKycTakeDocumentPhotoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycTakeDocumentPhotoBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_upfront_kyc_take_document_photo_0".equals(view.getTag())) {
            return new ControllerUpfrontKycTakeDocumentPhotoBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
