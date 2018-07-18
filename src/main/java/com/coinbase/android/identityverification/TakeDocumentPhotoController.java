package com.coinbase.android.identityverification;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout.LayoutParams;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.Log;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerTakeDocumentPhotoBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BackNavigationConnector;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerScope
public class TakeDocumentPhotoController extends ActionBarController implements Callback, TakeDocumentPhotoScreen {
    private static final double VIEWFINDER_ASPECT_RATIO = 1.586d;
    @Inject
    BackNavigationConnector mBackNavigationConnector;
    private ControllerTakeDocumentPhotoBinding mBinding;
    Drawable mFlashIcon;
    @Inject
    protected IdentityVerificationBitmapConnector mIdentityVerificationBitmapConnector;
    PictureCallback mJpegCallback;
    private final Logger mLogger = LoggerFactory.getLogger(TakeDocumentPhotoController.class);
    @Inject
    TakeDocumentPhotoPresenter mPresenter;
    SurfaceHolder mSurfaceHolder;
    double mViewfinderCropHeight;
    double mViewfinderCropWidth;
    double mViewfinderCropX;
    double mViewfinderCropY;

    public TakeDocumentPhotoController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerTakeDocumentPhotoBinding) DataBindingUtil.inflate(inflater, R.layout.controller_take_document_photo, container, false);
        onAttachToolbar(null);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addTakeDocumentPhotoControllerSubcomponent(new TakeDocumentPhotoPresenterModule(this)).inject(this);
        this.mPresenter.onCreate(getArgs());
        setFullscreen(true);
        this.mSurfaceHolder = this.mBinding.takeDocumentPhotoContent.cvJumioCameraImage.getHolder();
        this.mSurfaceHolder.addCallback(this);
        this.mSurfaceHolder.setType(3);
        this.mPresenter.onCreateView();
        this.mBinding.takeDocumentPhotoContent.ibtnFlashlight.getDrawable().setAlpha(155);
        this.mBinding.takeDocumentPhotoContent.ibtnFlashlight.setOnClickListener(TakeDocumentPhotoController$$Lambda$1.lambdaFactory$(this));
        this.mFlashIcon = getResources().getDrawable(R.drawable.qr_flash_icon);
        this.mJpegCallback = TakeDocumentPhotoController$$Lambda$2.lambdaFactory$(this);
        int navigationBarHeight = (int) getActivity().getResources().getDimension(R.dimen.margin_default);
        int statusBarHeight = getSystemHeightResource("status_bar_height");
        navigationBarHeight = getSystemHeightResource("navigation_bar_height");
        LayoutParams titleBarParams = (LayoutParams) this.mBinding.takeDocumentPhotoContent.rlJumioScanTitleBar.getLayoutParams();
        titleBarParams.topMargin = statusBarHeight;
        this.mBinding.takeDocumentPhotoContent.rlJumioScanTitleBar.setLayoutParams(titleBarParams);
        LayoutParams buttonsContainerParams = (LayoutParams) this.mBinding.takeDocumentPhotoContent.rlJumioButtonsContainer.getLayoutParams();
        buttonsContainerParams.bottomMargin = navigationBarHeight;
        this.mBinding.takeDocumentPhotoContent.rlJumioButtonsContainer.setLayoutParams(buttonsContainerParams);
        int displayWidth = getActivity().getResources().getDisplayMetrics().widthPixels;
        if (displayWidth > getActivity().getResources().getDisplayMetrics().heightPixels) {
            LayoutParams layoutParams = (LayoutParams) this.mBinding.takeDocumentPhotoContent.rlJumioButtonsContainerLandscape.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, navigationBarHeight);
            this.mBinding.takeDocumentPhotoContent.rlJumioButtonsContainerLandscape.setLayoutParams(layoutParams);
            this.mBinding.takeDocumentPhotoContent.rlJumioButtonsContainerLandscape.setVisibility(0);
            this.mBinding.takeDocumentPhotoContent.rlJumioButtonsContainer.setVisibility(4);
        } else {
            this.mBinding.takeDocumentPhotoContent.rlJumioButtonsContainerLandscape.setVisibility(8);
            this.mBinding.takeDocumentPhotoContent.rlJumioButtonsContainer.setVisibility(0);
        }
        this.mBinding.takeDocumentPhotoContent.vJumioBlankSpace.getLayoutParams().height = (int) (((double) ((float) displayWidth)) / VIEWFINDER_ASPECT_RATIO);
        this.mBinding.takeDocumentPhotoContent.vJumioBlankSpace.requestLayout();
        this.mBinding.takeDocumentPhotoContent.ibtnJumioBack.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                TakeDocumentPhotoController.this.mPresenter.onBackPressed();
            }
        });
        this.mBinding.takeDocumentPhotoContent.ibtnTakePhoto.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                    TakeDocumentPhotoController.this.captureImage(v);
                } catch (IOException e) {
                    TakeDocumentPhotoController.this.mLogger.error("Exception capturing image", e);
                }
            }
        });
        this.mBinding.takeDocumentPhotoContent.ibtnTakePhotoLandscape.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                    TakeDocumentPhotoController.this.captureImage(v);
                } catch (IOException e) {
                    TakeDocumentPhotoController.this.mLogger.error("Exception capturing image", e);
                }
            }
        });
        return this.mBinding.getRoot();
    }

    protected void onAttach(View view) {
        super.onAttach(view);
        setFullscreen(true);
    }

    protected void onDetach(View view) {
        super.onDetach(view);
        setFullscreen(false);
    }

    public void popBackstack() {
        setFullscreen(false);
        this.mBackNavigationConnector.get().onNext(null);
    }

    public void setJumioTitle(int title) {
        this.mBinding.takeDocumentPhotoContent.tvJumioTitle.setText(title);
    }

    public void showFaceMatchGuide() {
        this.mBinding.takeDocumentPhotoContent.vJumioScanGuides.setVisibility(4);
        this.mBinding.takeDocumentPhotoContent.vJumioTopOverlay.setVisibility(8);
        this.mBinding.takeDocumentPhotoContent.jumioBottomOverlay.setVisibility(4);
    }

    public void hideFaceMatchGuide() {
        this.mBinding.takeDocumentPhotoContent.vJumioScanGuides.setVisibility(0);
        this.mBinding.takeDocumentPhotoContent.vJumioTopOverlay.setVisibility(0);
        this.mBinding.takeDocumentPhotoContent.jumioBottomOverlay.setVisibility(0);
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.verify_identity_title));
    }

    private int getSystemHeightResource(String identifier) {
        int resourceId = getResources().getIdentifier(identifier, "dimen", "android");
        if (resourceId > 0) {
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public void captureImage(View v) throws IOException {
        this.mPresenter.tryTakePicture(this.mJpegCallback);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        this.mPresenter.tryCameraRefresh(this.mSurfaceHolder.getSurface());
    }

    public void surfaceCreated(SurfaceHolder holder) {
        this.mPresenter.tryCameraOpen();
    }

    public void configureCamera(Camera camera, boolean isRotated) {
        int targetWidth;
        int newWidth;
        int newHeight;
        Parameters param = camera.getParameters();
        Set<Double> acceptableAspectRatios = new HashSet();
        for (Size size : param.getSupportedPictureSizes()) {
            acceptableAspectRatios.add(Double.valueOf(((double) size.width) / ((double) size.height)));
        }
        List<Size> sizes = param.getSupportedPreviewSizes();
        Size bestSize = null;
        Size largestSize = null;
        int windowWidth = getView().getWidth();
        int windowHeight = getView().getHeight();
        if (isRotated) {
            targetWidth = windowHeight;
        } else {
            targetWidth = windowWidth;
        }
        for (Size size2 : sizes) {
            if (size2.width <= 2000 && size2.height <= 2000) {
                Double aspectRatio = Double.valueOf(((double) size2.width) / ((double) size2.height));
                if (acceptableAspectRatios.contains(aspectRatio)) {
                    if (size2.width > targetWidth && (bestSize == null || bestSize.width > size2.width)) {
                        bestSize = size2;
                    }
                    if (largestSize == null || largestSize.width < size2.width) {
                        largestSize = size2;
                    }
                } else {
                    Log.e("Coinbase", "Size rejected because " + aspectRatio);
                }
            }
        }
        if (bestSize == null) {
            bestSize = largestSize;
        }
        param.setPreviewSize(bestSize.width, bestSize.height);
        Size bestPictureSize = null;
        double targetAspectRatio = ((double) bestSize.width) / ((double) bestSize.height);
        for (Size size22 : param.getSupportedPictureSizes()) {
            double aspectRatio2 = ((double) size22.width) / ((double) size22.height);
            if (1.0E-5d + aspectRatio2 > targetAspectRatio && aspectRatio2 - 1.0E-5d < targetAspectRatio) {
                if (bestPictureSize == null || bestPictureSize.width < size22.width) {
                    bestPictureSize = size22;
                }
            }
        }
        if (bestPictureSize != null) {
            param.setPictureSize(bestPictureSize.width, bestPictureSize.height);
        }
        int actualWidth = isRotated ? bestSize.height : bestSize.width;
        int actualHeight = isRotated ? bestSize.width : bestSize.height;
        float aspectRatio3 = ((float) actualWidth) / ((float) actualHeight);
        int viewfinderHeightInWindow = (int) (((double) ((float) windowWidth)) / VIEWFINDER_ASPECT_RATIO);
        float scale;
        float viewfinderHeightInPreviewSpace;
        int top;
        int bottom;
        if (aspectRatio3 > ((float) windowWidth) / ((float) windowHeight)) {
            newWidth = (int) (((float) windowHeight) * aspectRatio3);
            newHeight = windowHeight;
            scale = ((float) actualHeight) / ((float) windowHeight);
            float windowWidthInPreviewSpace = ((float) windowWidth) * scale;
            viewfinderHeightInPreviewSpace = ((float) viewfinderHeightInWindow) * scale;
            int left = (int) ((((float) actualWidth) - windowWidthInPreviewSpace) / 2.0f);
            top = (int) ((((float) actualHeight) - viewfinderHeightInPreviewSpace) / 2.0f);
            int right = left + ((int) windowWidthInPreviewSpace);
            bottom = top + ((int) viewfinderHeightInPreviewSpace);
            this.mViewfinderCropX = ((double) left) / ((double) actualWidth);
            this.mViewfinderCropWidth = ((double) (right - left)) / ((double) actualWidth);
            this.mViewfinderCropY = ((double) top) / ((double) actualHeight);
            this.mViewfinderCropHeight = ((double) (bottom - top)) / ((double) actualHeight);
        } else {
            newWidth = windowWidth;
            newHeight = (int) (((float) windowWidth) * (1.0f / aspectRatio3));
            scale = ((float) actualWidth) / ((float) windowWidth);
            float windowHeightInPreviewSpace = ((float) windowHeight) * scale;
            viewfinderHeightInPreviewSpace = ((float) viewfinderHeightInWindow) * scale;
            top = ((int) ((((float) actualHeight) - windowHeightInPreviewSpace) / 2.0f)) + ((int) ((windowHeightInPreviewSpace - viewfinderHeightInPreviewSpace) / 2.0f));
            bottom = top + ((int) viewfinderHeightInPreviewSpace);
            this.mViewfinderCropX = ((double) null) / ((double) actualWidth);
            this.mViewfinderCropWidth = ((double) (actualWidth - 0)) / ((double) actualWidth);
            this.mViewfinderCropY = ((double) top) / ((double) actualHeight);
            this.mViewfinderCropHeight = ((double) (bottom - top)) / ((double) actualHeight);
        }
        LayoutParams layoutParams = new LayoutParams(newWidth, newHeight);
        layoutParams.addRule(13);
        this.mBinding.takeDocumentPhotoContent.cvJumioCameraImage.desiredHeight = newHeight;
        this.mBinding.takeDocumentPhotoContent.cvJumioCameraImage.desiredWidth = newWidth;
        this.mBinding.takeDocumentPhotoContent.cvJumioCameraImage.setLayoutParams(layoutParams);
        this.mPresenter.onParametersReady(param);
    }

    public void tryPreviewDisplay(Camera camera) throws Exception {
        camera.setPreviewDisplay(this.mSurfaceHolder);
    }

    public void showFlashlightOff() {
        this.mBinding.takeDocumentPhotoContent.ibtnFlashlight.getDrawable().setAlpha(150);
    }

    public void showFlashlightOn() {
        this.mBinding.takeDocumentPhotoContent.ibtnFlashlight.getDrawable().setAlpha(255);
    }

    public double getViewfinderCropX() {
        return this.mViewfinderCropX;
    }

    public double getViewfinderCropWidth() {
        return this.mViewfinderCropWidth;
    }

    public double getViewfinderCropY() {
        return this.mViewfinderCropY;
    }

    public double getViewfinderCropHeight() {
        return this.mViewfinderCropHeight;
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        this.mPresenter.onSurfaceDestroyed();
    }

    private void setFullscreen(boolean fullscreen) {
        if (getActivity() != null) {
            Window w = getActivity().getWindow();
            if (fullscreen) {
                w.setFlags(134217728, 134217728);
                w.setFlags(67108864, 67108864);
                getActivity().getWindow().getDecorView().setSystemUiVisibility(1280);
            } else {
                w.clearFlags(134217728);
                w.clearFlags(67108864);
                getActivity().getWindow().getDecorView().setSystemUiVisibility(256);
            }
            if (((AppCompatActivity) getActivity()).getSupportActionBar() == null) {
                getRouter().popCurrentController();
            } else if (fullscreen) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
            } else {
                ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            }
        }
    }
}
