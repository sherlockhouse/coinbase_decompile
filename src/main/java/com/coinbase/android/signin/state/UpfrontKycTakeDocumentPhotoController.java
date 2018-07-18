package com.coinbase.android.signin.state;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.Log;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerUpfrontKycTakeDocumentPhotoBinding;
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
public class UpfrontKycTakeDocumentPhotoController extends ActionBarController implements Callback, UpfrontKycTakeDocumentPhotoScreen {
    @Inject
    BackNavigationConnector mBackNavigationConnector;
    private ControllerUpfrontKycTakeDocumentPhotoBinding mBinding;
    Drawable mFlashIcon;
    private HintsAdapter mHintsAdapter;
    PictureCallback mJpegCallback;
    private final Logger mLogger = LoggerFactory.getLogger(UpfrontKycTakeDocumentPhotoController.class);
    @Inject
    UpfrontKycTakeDocumentPhotoPresenter mPresenter;
    SurfaceHolder mSurfaceHolder;

    private class HintsAdapter extends Adapter {
        private HintsAdapter() {
        }

        public int getItemCount() {
            return UpfrontKycTakeDocumentPhotoController.this.mPresenter.getHintsItemCount();
        }

        public int getItemViewType(int position) {
            return UpfrontKycTakeDocumentPhotoController.this.mPresenter.getHintsItemViewType(position);
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return UpfrontKycTakeDocumentPhotoController.this.mPresenter.onCreateHintsViewHolder(parent, viewType);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            UpfrontKycTakeDocumentPhotoController.this.mPresenter.onBindHintsViewHolder(holder, position);
        }
    }

    public UpfrontKycTakeDocumentPhotoController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerUpfrontKycTakeDocumentPhotoBinding) DataBindingUtil.inflate(inflater, R.layout.controller_upfront_kyc_take_document_photo, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addUpfrontKycTakeDocumentPhotoControllerSubcomponent(new UpfrontKycTakeDocumentPhotoPresenterModule(this, this)).inject(this);
        this.mHintsAdapter = new HintsAdapter();
        this.mBinding.rvHints.setAdapter(this.mHintsAdapter);
        this.mBinding.rvHints.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        this.mPresenter.onCreate(getArgs());
        this.mSurfaceHolder = this.mBinding.cvJumioCameraImage.getHolder();
        this.mSurfaceHolder.addCallback(this);
        this.mSurfaceHolder.setType(3);
        this.mPresenter.onCreateView();
        this.mBinding.tvFlashLight.getCompoundDrawables()[0].setAlpha(153);
        this.mBinding.tvFlashLight.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                UpfrontKycTakeDocumentPhotoController.this.mPresenter.onToggleClicked();
            }
        });
        this.mFlashIcon = getResources().getDrawable(R.drawable.qr_flash_icon);
        this.mJpegCallback = new PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {
                UpfrontKycTakeDocumentPhotoController.this.mPresenter.onPictureTaken(data, camera, UpfrontKycTakeDocumentPhotoController.this.mSurfaceHolder.getSurface());
            }
        };
        this.mBinding.rlJumioButtonsContainer.setVisibility(0);
        this.mBinding.ibtnTakePhoto.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                    UpfrontKycTakeDocumentPhotoController.this.captureImage(v);
                } catch (IOException e) {
                    UpfrontKycTakeDocumentPhotoController.this.mLogger.error("Exception capturing image", e);
                }
            }
        });
        return this.mBinding.getRoot();
    }

    protected void onDestroyView(View view) {
        super.onDestroyView(view);
        this.mSurfaceHolder.removeCallback(this);
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected int getThemeColor() {
        return ContextCompat.getColor(getActivity(), R.color.immersive_background);
    }

    protected int getToolbarTextColor() {
        return R.color.white;
    }

    public void popBackstack() {
        onBackPressed();
    }

    public void setJumioTitle(int title) {
        this.mBinding.tvTakeDocumentHeader.setText(title);
    }

    public void showFaceMatchGuide() {
        this.mThemeUpdater.setActionBarColor(ContextCompat.getColor(getApplicationContext(), 17170445));
        this.mBinding.flFacematchContainer.setVisibility(0);
        this.mBinding.vBlankSpaceLeftMargin.setVisibility(4);
        this.mBinding.vBlankSpaceRightMargin.setVisibility(4);
        this.mBinding.flHeaderContainer.setBackground(null);
        this.mBinding.vJumioScanGuides.setVisibility(4);
        this.mBinding.jumioBottomOverlay.setVisibility(4);
    }

    public void hideFaceMatchGuide() {
        this.mBinding.flFacematchContainer.setVisibility(4);
        this.mBinding.vBlankSpaceLeftMargin.setVisibility(0);
        this.mBinding.vBlankSpaceRightMargin.setVisibility(0);
        this.mBinding.flHeaderContainer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.immersive_background));
        this.mBinding.vJumioScanGuides.setVisibility(0);
        this.mBinding.jumioBottomOverlay.setVisibility(0);
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder("");
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
        this.mPresenter.onParametersReady(param);
    }

    public void tryPreviewDisplay(Camera camera) throws Exception {
        camera.setPreviewDisplay(this.mSurfaceHolder);
    }

    public void showFlashlightOff() {
        this.mBinding.tvFlashLight.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white_60));
        this.mBinding.tvFlashLight.getCompoundDrawables()[0].setAlpha(153);
    }

    public void showFlashlightOn() {
        this.mBinding.tvFlashLight.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        this.mBinding.tvFlashLight.getCompoundDrawables()[0].setAlpha(255);
    }

    public void notifyHintsChanged() {
        this.mHintsAdapter.notifyDataSetChanged();
    }

    public void setGuidelinesRatio(float ratio, float widthRatio) {
        int fullWidth = getActivity().getResources().getDisplayMetrics().widthPixels;
        int displayWidth = (int) (((float) fullWidth) * widthRatio);
        int marginWidth = (fullWidth - displayWidth) / 2;
        this.mBinding.vBlankSpaceLeftMargin.getLayoutParams().width = marginWidth;
        this.mBinding.vBlankSpaceLeftMargin.requestLayout();
        this.mBinding.vBlankSpaceRightMargin.getLayoutParams().width = marginWidth;
        this.mBinding.vBlankSpaceRightMargin.requestLayout();
        this.mBinding.flBlankSpaceContainer.getLayoutParams().height = (int) (((float) displayWidth) * ratio);
        this.mBinding.flBlankSpaceContainer.requestLayout();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        this.mPresenter.onSurfaceDestroyed();
    }
}
