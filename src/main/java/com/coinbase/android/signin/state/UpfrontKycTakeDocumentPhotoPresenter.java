package com.coinbase.android.signin.state;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.Surface;
import android.view.ViewGroup;
import android.view.Window;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.identityverification.IdentityVerificationBitmapConnector;
import com.coinbase.android.identityverification.IdentityVerificationBitmapContainer;
import com.coinbase.android.identityverification.IdentityVerificationBitmapContainer.Builder;
import com.coinbase.android.identityverification.IdentityVerificationConstants;
import com.coinbase.android.identityverification.PhotoTakenConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.ModelGsonAdapterFactory;
import com.coinbase.api.internal.models.jumio.JumioProfiles.Type;
import com.coinbase.api.internal.models.jumio.supportedDocuments.Data;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedIdType;
import com.google.gson.GsonBuilder;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerScope
public class UpfrontKycTakeDocumentPhotoPresenter {
    private static final float DRIVERS_LICENSE_RATIO = 0.59f;
    private static final float DRIVER_LICENSE_WIDTH_RATIO = 0.95f;
    private static final float PASSPORT_RATIO = 1.29f;
    private static final float PASSPORT_WIDTH_RATIO = 0.5f;
    Camera mCamera;
    int mCameraOrientation;
    private final Context mContext;
    int mDocTypeIndex;
    private final List<Integer> mHints = new LinkedList();
    private final AdapterDelegatesManager<List<Integer>> mHintsAdapterDelegate = new AdapterDelegatesManager();
    private final IdentityVerificationBitmapConnector mIdentityVerificationBitmapConnector;
    boolean mIsFlashlight;
    Data mJumioDoc;
    SupportedIdType mJumioType;
    private final Logger mLogger = LoggerFactory.getLogger(UpfrontKycTakeDocumentPhotoPresenter.class);
    private final PhotoTakenConnector mPhotoTakenConnector;
    boolean mPreviewRunning;
    private final UpfrontKycTakeDocumentPhotoRouter mRouter;
    private final UpfrontKycTakeDocumentPhotoScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    boolean mTakingBack = false;
    boolean mTakingFaceMatch = false;
    private final Window mWindow;

    @Inject
    public UpfrontKycTakeDocumentPhotoPresenter(UpfrontKycTakeDocumentPhotoScreen screen, UpfrontKycTakeDocumentPhotoRouter router, IdentityVerificationBitmapConnector identityVerificationBitmapConnector, PhotoTakenConnector photoTakenConnector, SnackBarWrapper snackBarWrapper, Window window, Application app) {
        this.mScreen = screen;
        this.mRouter = router;
        this.mIdentityVerificationBitmapConnector = identityVerificationBitmapConnector;
        this.mPhotoTakenConnector = photoTakenConnector;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mWindow = window;
        this.mContext = app;
        this.mHintsAdapterDelegate.addDelegate(new HintsAdapterDelegate());
    }

    public void onCreate(Bundle args) {
        this.mJumioDoc = (Data) new GsonBuilder().registerTypeAdapterFactory(ModelGsonAdapterFactory.create()).create().fromJson(args.getString(IdentityVerificationConstants.JUMIO_DOCUMENT_KEY), Data.class);
        if (this.mJumioDoc == null) {
            this.mScreen.popBackstack();
            return;
        }
        this.mDocTypeIndex = args.getInt(IdentityVerificationConstants.JUMIO_DOCUMENT_INDEX_KEY);
        this.mJumioType = (SupportedIdType) this.mJumioDoc.getSupportedIdTypes().get(this.mDocTypeIndex);
        this.mTakingBack = args.getBoolean(IdentityVerificationConstants.EXTRA_TAKING_BACK);
        this.mTakingFaceMatch = args.getBoolean(IdentityVerificationConstants.EXTRA_TAKING_FACE_MATCH);
        populateHints();
    }

    public void onCreateView() {
        if (this.mContext.getPackageManager().hasSystemFeature("android.hardware.camera.flash")) {
            this.mIsFlashlight = true;
        }
        if (this.mTakingFaceMatch) {
            this.mScreen.setJumioTitle(R.string.jumio_scan_selfie);
        } else if (this.mJumioType.getType() == Type.PASSPORT) {
            this.mScreen.setJumioTitle(R.string.upfront_kyc_identity_take_photo_passport_header);
        } else if (this.mTakingBack) {
            this.mScreen.setJumioTitle(R.string.upfront_kyc_identity_take_photo_back_header);
        } else {
            this.mScreen.setJumioTitle(R.string.upfront_kyc_identity_take_photo_id_header);
        }
    }

    public void onShow() {
        if (this.mTakingFaceMatch) {
            this.mScreen.showFaceMatchGuide();
            return;
        }
        this.mScreen.hideFaceMatchGuide();
        switch (this.mJumioType.getType()) {
            case PASSPORT:
                this.mScreen.setGuidelinesRatio(PASSPORT_RATIO, PASSPORT_WIDTH_RATIO);
                return;
            default:
                this.mScreen.setGuidelinesRatio(DRIVERS_LICENSE_RATIO, DRIVER_LICENSE_WIDTH_RATIO);
                return;
        }
    }

    public void onPictureTaken(byte[] data, Camera camera, Surface surface) {
        Bitmap bitmap = getPhotoBitmap(data, camera);
        if (this.mTakingFaceMatch) {
            Matrix matrix = new Matrix();
            matrix.postRotate(180.0f);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        if (surface != null) {
            try {
                this.mCamera.stopPreview();
                this.mPreviewRunning = false;
            } catch (Exception e) {
            }
            Builder identityVerificationContainerBuilder = IdentityVerificationBitmapContainer.builder();
            IdentityVerificationBitmapContainer lastValue = (IdentityVerificationBitmapContainer) this.mIdentityVerificationBitmapConnector.get().getValue();
            if (lastValue != null) {
                identityVerificationContainerBuilder.setBackBitmap(lastValue.getBackBitmap());
                identityVerificationContainerBuilder.setFaceMatchBitmap(lastValue.getFaceMatchBitmap());
                identityVerificationContainerBuilder.setFrontBitmap(lastValue.getFrontBitmap());
            }
            if (this.mTakingBack) {
                identityVerificationContainerBuilder.setBackBitmap(bitmap);
            } else if (this.mTakingFaceMatch) {
                identityVerificationContainerBuilder.setFaceMatchBitmap(bitmap);
            } else {
                identityVerificationContainerBuilder.setFrontBitmap(bitmap);
            }
            this.mIdentityVerificationBitmapConnector.get().onNext(identityVerificationContainerBuilder.build());
            this.mPhotoTakenConnector.get().onNext(new MutableBoolean(true));
            advanceToNextScreen();
        }
    }

    private void populateHints() {
        this.mHints.clear();
        if (this.mTakingBack) {
            this.mHints.addAll(Arrays.asList(new Integer[]{Integer.valueOf(R.string.upfront_kyc_identity_take_photo_hint_glare), Integer.valueOf(R.string.upfront_kyc_identity_take_photo_hint_hold), Integer.valueOf(R.string.upfront_kyc_identity_take_photo_hint_frame)}));
        } else if (this.mTakingFaceMatch) {
            this.mHints.addAll(Arrays.asList(new Integer[]{Integer.valueOf(R.string.upfront_kyc_identity_take_photo_facematch_hint_hats), Integer.valueOf(R.string.upfront_kyc_identity_take_photo_facematch_hint_frame)}));
        } else {
            this.mHints.addAll(Arrays.asList(new Integer[]{Integer.valueOf(R.string.upfront_kyc_identity_take_photo_hint_glare), Integer.valueOf(R.string.upfront_kyc_identity_take_photo_hint_hold), Integer.valueOf(R.string.upfront_kyc_identity_take_photo_hint_frame)}));
        }
        this.mScreen.notifyHintsChanged();
    }

    private void advanceToNextScreen() {
        if (((IdentityVerificationBitmapContainer) this.mIdentityVerificationBitmapConnector.get().getValue()) == null) {
            this.mRouter.routeToVerifyPhoto(createScanDocumentExtras(false, false));
        } else if (this.mTakingBack) {
            this.mRouter.routeToVerifyPhoto(createScanDocumentExtras(true, false));
        } else if (this.mTakingFaceMatch) {
            this.mRouter.routeToVerifyPhoto(createScanDocumentExtras(false, true));
        } else {
            this.mRouter.routeToVerifyPhoto(createScanDocumentExtras(false, false));
        }
    }

    private Bundle createScanDocumentExtras(boolean isTakingBack, boolean isTakingFaceMatch) {
        Bundle extras = new Bundle();
        extras.putBoolean(IdentityVerificationConstants.EXTRA_TAKING_BACK, isTakingBack);
        extras.putBoolean(IdentityVerificationConstants.EXTRA_TAKING_FACE_MATCH, isTakingFaceMatch);
        return extras;
    }

    private Bitmap getPhotoBitmap(byte[] data, Camera camera) {
        Options options = new Options();
        options.inDither = false;
        options.inTempStorage = new byte[32768];
        options.inPreferredConfig = Config.RGB_565;
        Bitmap bMap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        int orientation = this.mCameraOrientation;
        if (orientation == 0) {
            return Bitmap.createScaledBitmap(bMap, bMap.getWidth(), bMap.getHeight(), true);
        }
        try {
            Matrix matrix = new Matrix();
            matrix.postRotate((float) orientation);
            return Bitmap.createBitmap(bMap, 0, 0, bMap.getWidth(), bMap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            return bMap;
        } catch (Exception e2) {
            this.mSnackBarWrapper.show((int) R.string.error_occurred_try_again);
            return null;
        }
    }

    public void tryCameraRefresh(Surface surface) {
        if (surface != null) {
            try {
                this.mCamera.stopPreview();
                this.mPreviewRunning = false;
            } catch (Exception e) {
                this.mLogger.error("Error refreshing the camera.");
            }
            try {
                this.mScreen.tryPreviewDisplay(this.mCamera);
                this.mCamera.startPreview();
                this.mPreviewRunning = true;
            } catch (Exception e2) {
                this.mLogger.error("Error starting camera preview.");
            }
        }
    }

    public void tryCameraOpen() {
        try {
            if (this.mTakingFaceMatch) {
                this.mCamera = Camera.open(1);
            } else {
                this.mCamera = Camera.open();
            }
            if (this.mCamera == null) {
                Utils.showMessage(this.mContext, (int) R.string.camera_error, 1);
                return;
            }
            this.mScreen.configureCamera(this.mCamera, setDisplayOrientation(this.mCamera));
        } catch (RuntimeException e) {
        }
    }

    public void onParametersReady(Parameters param) {
        param.setFocusMode("continuous-picture");
        try {
            this.mCamera.setParameters(param);
            this.mScreen.tryPreviewDisplay(this.mCamera);
            this.mCamera.startPreview();
            this.mPreviewRunning = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onToggleClicked() {
        if (this.mCamera != null) {
            Parameters cp = this.mCamera.getParameters();
            if (cp != null && cp.getFlashMode() != null) {
                if (cp.getFlashMode().equals("torch")) {
                    cp.setFlashMode("off");
                    this.mScreen.showFlashlightOff();
                } else {
                    cp.setFlashMode("torch");
                    this.mScreen.showFlashlightOn();
                }
                this.mCamera.setParameters(cp);
            }
        }
    }

    void tryTakePicture(PictureCallback callback) {
        try {
            this.mCamera.takePicture(null, null, callback);
        } catch (Exception e) {
        }
    }

    void onSurfaceDestroyed() {
        if (this.mCamera != null) {
            this.mCamera.stopPreview();
            this.mPreviewRunning = false;
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    int getHintsItemCount() {
        return this.mHints.size();
    }

    int getHintsItemViewType(int position) {
        return this.mHintsAdapterDelegate.getItemViewType(this.mHints, position);
    }

    ViewHolder onCreateHintsViewHolder(ViewGroup parent, int viewType) {
        return this.mHintsAdapterDelegate.onCreateViewHolder(parent, viewType);
    }

    void onBindHintsViewHolder(ViewHolder holder, int position) {
        this.mHintsAdapterDelegate.onBindViewHolder(this.mHints, position, holder);
    }

    private boolean setDisplayOrientation(Camera camera) {
        int result;
        CameraInfo info = new CameraInfo();
        Camera.getCameraInfo(0, info);
        int degrees = 0;
        switch (this.mWindow.getWindowManager().getDefaultDisplay().getRotation()) {
            case 0:
                degrees = 0;
                break;
            case 1:
                degrees = 90;
                break;
            case 2:
                degrees = 180;
                break;
            case 3:
                degrees = 270;
                break;
        }
        if (info.facing == 1) {
            result = (360 - ((info.orientation + degrees) % 360)) % 360;
        } else {
            result = ((info.orientation - degrees) + 360) % 360;
        }
        this.mCameraOrientation = result;
        camera.setDisplayOrientation(result);
        if ((result / 90) % 2 == 1) {
            return true;
        }
        return false;
    }
}
