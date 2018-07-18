package com.coinbase.android.identityverification;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.Surface;
import android.view.Window;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.identityverification.IdentityVerificationBitmapContainer.Builder;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.ModelGsonAdapterFactory;
import com.coinbase.api.internal.models.jumio.JumioProfiles.Type;
import com.coinbase.api.internal.models.jumio.supportedDocuments.Data;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedIdType;
import com.google.gson.GsonBuilder;
import javax.inject.Inject;
import org.apache.commons.lang3.mutable.MutableBoolean;

@ControllerScope
public class TakeDocumentPhotoPresenter {
    Camera mCamera;
    int mCameraOrientation;
    private final Context mContext;
    int mDocTypeIndex;
    private final IdentityVerificationBitmapConnector mIdentityVerificationBitmapConnector;
    boolean mIsFlashlight;
    Data mJumioDoc;
    SupportedIdType mJumioType;
    private final PhotoTakenConnector mPhotoTakenConnector;
    boolean mPreviewRunning;
    private final TakeDocumentPhotoScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    boolean mTakingBack = false;
    boolean mTakingFaceMatch = false;
    private final Window mWindow;

    @Inject
    public TakeDocumentPhotoPresenter(TakeDocumentPhotoScreen screen, IdentityVerificationBitmapConnector identityVerificationBitmapConnector, PhotoTakenConnector photoTakenConnector, SnackBarWrapper snackBarWrapper, Window window, Application app) {
        this.mScreen = screen;
        this.mIdentityVerificationBitmapConnector = identityVerificationBitmapConnector;
        this.mPhotoTakenConnector = photoTakenConnector;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mWindow = window;
        this.mContext = app;
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
    }

    public void onCreateView() {
        if (this.mContext.getPackageManager().hasSystemFeature("android.hardware.camera.flash")) {
            this.mIsFlashlight = true;
        }
        if (this.mTakingFaceMatch) {
            this.mScreen.setJumioTitle(R.string.jumio_scan_selfie);
        } else if (this.mJumioType.getType() == Type.PASSPORT) {
            this.mScreen.setJumioTitle(R.string.jumio_scan_the_photo_page);
        } else if (this.mTakingBack) {
            this.mScreen.setJumioTitle(R.string.jumio_scan_the_back);
        } else {
            this.mScreen.setJumioTitle(R.string.jumio_scan_the_front);
        }
        if (this.mTakingFaceMatch) {
            this.mScreen.showFaceMatchGuide();
        } else {
            this.mScreen.hideFaceMatchGuide();
        }
    }

    public void onPictureTaken(byte[] data, Camera camera, Surface surface) {
        Bitmap bitmap = getPhotoBitmap(data, camera);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (this.mTakingFaceMatch) {
            Matrix matrix = new Matrix();
            matrix.postRotate(180.0f);
            matrix.postScale((0.5f * ((float) width)) / ((float) width), (0.5f * ((float) height)) / ((float) height));
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } else {
            Size size = camera.getParameters().getPictureSize();
            int viewfinderCropX = (int) (this.mScreen.getViewfinderCropX() * ((double) width));
            int viewfinderCropY = (int) (this.mScreen.getViewfinderCropY() * ((double) height));
            bitmap = Bitmap.createBitmap(bitmap, viewfinderCropX, viewfinderCropY, (int) (this.mScreen.getViewfinderCropWidth() * ((double) width)), (int) (this.mScreen.getViewfinderCropHeight() * ((double) height)));
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
            this.mScreen.popBackstack();
        }
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
            }
            try {
                this.mScreen.tryPreviewDisplay(this.mCamera);
                this.mCamera.startPreview();
                this.mPreviewRunning = true;
            } catch (Exception e2) {
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
                this.mScreen.popBackstack();
                return;
            }
            this.mScreen.configureCamera(this.mCamera, setDisplayOrientation(this.mCamera));
        } catch (RuntimeException e) {
        }
    }

    public void onParametersReady(Parameters param) {
        boolean doGingerbreadAutoFocus = false;
        if (VERSION.SDK_INT >= 14) {
            param.setFocusMode("continuous-picture");
        } else {
            doGingerbreadAutoFocus = true;
        }
        try {
            this.mCamera.setParameters(param);
            this.mScreen.tryPreviewDisplay(this.mCamera);
            this.mCamera.startPreview();
            this.mPreviewRunning = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (doGingerbreadAutoFocus) {
            doGingerbreadAutoFocus(this.mCamera);
        }
    }

    public void onBackPressed() {
        this.mScreen.popBackstack();
    }

    private void doGingerbreadAutoFocus(final Camera camera) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (camera != null && TakeDocumentPhotoPresenter.this.mPreviewRunning) {
                    camera.autoFocus(new AutoFocusCallback() {
                        public void onAutoFocus(boolean success, Camera camera) {
                            TakeDocumentPhotoPresenter.this.doGingerbreadAutoFocus(camera);
                        }
                    });
                }
            }
        }, 3000);
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
