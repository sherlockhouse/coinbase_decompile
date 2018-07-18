package com.coinbase.android.identityverification;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.task.GetUserTask.AdminFlags;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.FaceDetectionUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ModelGsonAdapterFactory;
import com.coinbase.api.internal.models.jumio.JumioProfile;
import com.coinbase.api.internal.models.jumio.JumioProfiles.Type;
import com.coinbase.api.internal.models.jumio.supportedDocuments.Data;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedIdType;
import com.google.gson.GsonBuilder;
import java.io.ByteArrayOutputStream;
import javax.inject.Inject;
import org.apache.commons.lang3.mutable.MutableBoolean;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class JumioDocumentScanPresenter {
    private Bundle mArgs;
    private final Scheduler mBackgroundScheduler;
    private final Context mContext;
    private final FaceDetectionUtils mFaceDetectionUtils;
    private final IdentityVerificationBitmapConnector mIdentityVerificationBitmapConnector;
    Data mJumioDoc;
    SupportedIdType mJumioType;
    int mJumioTypeIndex;
    private IdentityVerificationBitmapContainer mLastContainer;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final PhotoTakenConnector mPhototakenConnector;
    boolean mRequiresFaceMatch = false;
    private final RetakeAndContinueConnector mRetakeAndContinueConnector;
    private final InAppIdentityDocumentScanRouter mRouter;
    private final JumioDocumentScanScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private SplitTesting mSplitTesting;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    boolean mTakingBack = false;
    boolean mTakingFaceMatch = false;

    private enum ImageType {
        PNG,
        JPEG
    }

    @Inject
    public JumioDocumentScanPresenter(JumioDocumentScanScreen screen, InAppIdentityDocumentScanRouter router, RetakeAndContinueConnector retakeAndContinueConnector, PhotoTakenConnector photoTakenConnector, IdentityVerificationBitmapConnector identityVerificationBitmapConnector, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler, LoginManager loginManager, SnackBarWrapper snackBarWrapper, FaceDetectionUtils faceDetectionUtils, Application app, SplitTesting splitTesting) {
        this.mScreen = screen;
        this.mRouter = router;
        this.mRetakeAndContinueConnector = retakeAndContinueConnector;
        this.mPhototakenConnector = photoTakenConnector;
        this.mIdentityVerificationBitmapConnector = identityVerificationBitmapConnector;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
        this.mLoginManager = loginManager;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mFaceDetectionUtils = faceDetectionUtils;
        this.mContext = app;
        this.mSplitTesting = splitTesting;
    }

    public void onCreate(Bundle args) {
        this.mArgs = args;
        this.mJumioDoc = (Data) new GsonBuilder().registerTypeAdapterFactory(ModelGsonAdapterFactory.create()).create().fromJson(args.getString(IdentityVerificationConstants.JUMIO_DOCUMENT_KEY), Data.class);
        if (this.mJumioDoc == null) {
            this.mScreen.finish();
            return;
        }
        this.mJumioTypeIndex = args.getInt(IdentityVerificationConstants.JUMIO_DOCUMENT_INDEX_KEY);
        this.mJumioType = (SupportedIdType) this.mJumioDoc.getSupportedIdTypes().get(this.mJumioTypeIndex);
        this.mTakingBack = args.getBoolean(IdentityVerificationConstants.EXTRA_TAKING_BACK);
        this.mTakingFaceMatch = args.getBoolean(IdentityVerificationConstants.EXTRA_TAKING_FACE_MATCH);
        boolean z = Utils.hasAdminFlag(AdminFlags.REQUIRE_JUMIO_FACE_MATCH, this.mContext) || args.getBoolean(AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString());
        this.mRequiresFaceMatch = z;
    }

    public void onContinueClicked() {
        if (shouldAdvanceToNextScreen()) {
            advanceToNextScreen();
        } else {
            takePhoto();
        }
    }

    public void onRetakeClicked() {
        takePhoto();
    }

    public void onCameraPermissionGranted() {
        updateLabels();
    }

    private void updateLabels() {
        Type type = this.mJumioType.getType();
        if (!this.mTakingBack) {
            if (!this.mTakingFaceMatch) {
                int text;
                if (type == Type.PASSPORT) {
                    text = R.string.jumio_scan_the_photo_page;
                } else {
                    text = R.string.jumio_scan_the_front;
                }
                switch (type) {
                    case DRIVERS_LICENCE:
                        this.mScreen.updateScanText(text, R.string.jumio_scan_drivers_license);
                        break;
                    case ID_CARD:
                        this.mScreen.updateScanText(text, R.string.jumio_scan_id_card);
                        break;
                    case PASSPORT:
                        this.mScreen.updateScanText(text, R.string.jumio_scan_passport);
                        break;
                    default:
                        break;
                }
            }
            this.mScreen.updateScanText(R.string.jumio_scan_selfie, R.string.jumio_selfie_description);
        } else {
            switch (type) {
                case DRIVERS_LICENCE:
                    this.mScreen.updateScanText(R.string.jumio_scan_the_back, R.string.jumio_scan_back_drivers_license);
                    break;
                case ID_CARD:
                    this.mScreen.updateScanText(R.string.jumio_scan_the_back, R.string.jumio_scan_back_id_card);
                    break;
                default:
                    this.mScreen.updateScanText(R.string.jumio_scan_the_back, R.string.jumio_scan_back);
                    break;
            }
        }
        if (shouldAdvanceToNextScreen()) {
            this.mScreen.showContinueConfirmPicture(jumioPicturePreviewResource());
        } else if (this.mTakingFaceMatch) {
            this.mScreen.showTakeSelfie();
        } else if (this.mTakingBack) {
            this.mScreen.showTakeBack();
        } else {
            this.mScreen.showTakeFront();
        }
    }

    private boolean shouldAdvanceToNextScreen() {
        if (this.mLastContainer == null || this.mLastContainer.getFrontBitmap() == null) {
            return false;
        }
        if (this.mTakingBack && this.mLastContainer.getBackBitmap() == null) {
            return false;
        }
        if (this.mTakingFaceMatch && this.mLastContainer.getFaceMatchBitmap() == null) {
            return false;
        }
        return true;
    }

    public void takePhoto() {
        Bundle extras = new Bundle();
        extras.putString(IdentityVerificationConstants.JUMIO_DOCUMENT_KEY, new GsonBuilder().registerTypeAdapterFactory(ModelGsonAdapterFactory.create()).create().toJson(this.mJumioDoc));
        extras.putInt(IdentityVerificationConstants.JUMIO_DOCUMENT_INDEX_KEY, this.mJumioDoc.getSupportedIdTypes().indexOf(this.mJumioType));
        extras.putBoolean(IdentityVerificationConstants.EXTRA_TAKING_BACK, this.mTakingBack);
        extras.putBoolean(IdentityVerificationConstants.EXTRA_TAKING_FACE_MATCH, this.mTakingFaceMatch);
        this.mScreen.routeTakeDocumentPhoto(extras);
        if (this.mTakingBack) {
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_TAKE_BACK_IMAGE, new String[0]);
        } else if (this.mTakingFaceMatch) {
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_TAKE_FACE_MATCH, new String[0]);
        } else {
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_TAKE_FRONT_IMAGE, new String[0]);
        }
    }

    public Bitmap jumioPicturePreviewResource() {
        if (this.mTakingFaceMatch) {
            return this.mLastContainer.getFaceMatchBitmap();
        }
        if (this.mTakingBack) {
            return this.mLastContainer.getBackBitmap();
        }
        return this.mLastContainer.getFrontBitmap();
    }

    void onShow() {
        this.mSubscription.add(Observable.combineLatest(this.mRetakeAndContinueConnector.getRetake().onBackpressureLatest(), this.mRetakeAndContinueConnector.getContinue().onBackpressureLatest(), this.mPhototakenConnector.get().onBackpressureLatest(), this.mIdentityVerificationBitmapConnector.get(), JumioDocumentScanPresenter$$Lambda$1.lambdaFactory$(this)).observeOn(this.mMainScheduler).subscribe(JumioDocumentScanPresenter$$Lambda$2.lambdaFactory$()));
    }

    static /* synthetic */ Runnable lambda$onShow$4(JumioDocumentScanPresenter this_, MutableBoolean retakeVal, MutableBoolean continueVal, MutableBoolean photoTakenVal, IdentityVerificationBitmapContainer container) {
        boolean photoTaken;
        if (photoTakenVal == null || !photoTakenVal.getValue().booleanValue()) {
            photoTaken = false;
        } else {
            photoTaken = true;
        }
        this_.mLastContainer = container;
        Runnable nextStep = JumioDocumentScanPresenter$$Lambda$4.lambdaFactory$();
        if (retakeVal != null && retakeVal.getValue().booleanValue()) {
            retakeVal.setValue(false);
            return JumioDocumentScanPresenter$$Lambda$5.lambdaFactory$(this_);
        } else if (continueVal == null || !continueVal.getValue().booleanValue()) {
            if (photoTakenVal != null) {
                photoTakenVal.setValue(false);
            }
            return JumioDocumentScanPresenter$$Lambda$7.lambdaFactory$(this_, photoTaken);
        } else if (!this_.shouldAdvanceToNextScreen()) {
            return nextStep;
        } else {
            continueVal.setValue(false);
            return JumioDocumentScanPresenter$$Lambda$6.lambdaFactory$(this_);
        }
    }

    static /* synthetic */ void lambda$null$0() {
    }

    static /* synthetic */ void lambda$null$3(JumioDocumentScanPresenter this_, boolean photoTaken) {
        this_.updateLabels();
        if (photoTaken) {
            this_.handleOnPhotoTaken();
        }
    }

    void onHide() {
        this.mSubscription.clear();
        ((MutableBoolean) this.mRetakeAndContinueConnector.getContinue().getValue()).setValue(false);
        ((MutableBoolean) this.mRetakeAndContinueConnector.getRetake().getValue()).setValue(false);
    }

    private void handleOnPhotoTaken() {
        if (this.mLastContainer != null) {
            if (this.mLastContainer.getFrontBitmap() != null && !this.mTakingBack && !this.mTakingFaceMatch) {
                detectFace(this.mLastContainer.getFrontBitmap());
            } else if (this.mLastContainer.getFaceMatchBitmap() != null) {
                detectFace(this.mLastContainer.getFaceMatchBitmap());
            }
        }
    }

    private void detectFace(Bitmap bmp) {
        this.mScreen.showPleaseWaitProgress();
        this.mSubscription.add(this.mFaceDetectionUtils.hasFace(this.mContext, bmp).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(JumioDocumentScanPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$detectFace$6(JumioDocumentScanPresenter this_, Boolean hasFace) {
        this_.mScreen.hideProgressDialog();
        if (!hasFace.booleanValue() && this_.mScreen.isShown()) {
            if (this_.mTakingFaceMatch) {
                this_.mSnackBarWrapper.show((int) R.string.retake_selfie);
            } else {
                this_.mScreen.routeRetakeDocumentPhoto();
            }
        }
    }

    private void retakePhoto() {
        this.mTakingBack = false;
        this.mLastContainer = null;
        takePhoto();
    }

    private void continueToNextScreen() {
        advanceToNextScreen();
    }

    private void advanceToNextScreen() {
        if (this.mLastContainer.getBackBitmap() == null && !this.mTakingBack && this.mJumioType.getBacksideImageRequired().booleanValue()) {
            this.mRouter.routeToScanDocument(createScanDocumentExtras(true, false));
        } else if (this.mLastContainer.getFaceMatchBitmap() == null && !this.mTakingFaceMatch && this.mRequiresFaceMatch) {
            this.mRouter.routeToScanDocument(createScanDocumentExtras(false, true));
        } else {
            this.mScreen.hideButtons();
            createJumioProfile(this.mJumioDoc.getCountry().getCode(), this.mJumioType.getType().toString(), this.mLastContainer.getFrontBitmap(), this.mLastContainer.getBackBitmap(), this.mLastContainer.getFaceMatchBitmap());
        }
    }

    private Bundle createScanDocumentExtras(boolean isTakingBack, boolean isTakingFaceMatch) {
        Bundle extras = new Bundle();
        extras.putString(IdentityVerificationConstants.JUMIO_DOCUMENT_KEY, new GsonBuilder().registerTypeAdapterFactory(ModelGsonAdapterFactory.create()).create().toJson(this.mJumioDoc));
        extras.putInt(IdentityVerificationConstants.JUMIO_DOCUMENT_INDEX_KEY, this.mJumioTypeIndex);
        extras.putBoolean(IdentityVerificationConstants.EXTRA_TAKING_BACK, isTakingBack);
        extras.putBoolean(IdentityVerificationConstants.EXTRA_TAKING_FACE_MATCH, isTakingFaceMatch);
        extras.putBoolean(AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString(), this.mRequiresFaceMatch);
        return extras;
    }

    private byte[] createImageByteArray(Bitmap bmp, ImageType imageType) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            bmp.compress(imageType == ImageType.PNG ? CompressFormat.PNG : CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            stream.close();
            return byteArray;
        } catch (Exception e) {
            return null;
        }
    }

    private void createJumioProfile(String countryCode, String idType, Bitmap frontImage, Bitmap backImage, Bitmap faceMatchImage) {
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_SUBMIT, new String[0]);
        this.mScreen.showJumioUploadingProgress();
        final Bitmap bitmap = frontImage;
        final Bitmap bitmap2 = backImage;
        final Bitmap bitmap3 = faceMatchImage;
        final String str = countryCode;
        final String str2 = idType;
        AsyncTask.execute(new Runnable() {
            public void run() {
                ImageType imageType = ImageType.JPEG;
                byte[] frontData = JumioDocumentScanPresenter.this.createImageByteArray(bitmap, imageType);
                byte[] backData = null;
                byte[] faceMatchData = null;
                if (bitmap2 != null) {
                    backData = JumioDocumentScanPresenter.this.createImageByteArray(bitmap2, imageType);
                }
                if (bitmap3 != null) {
                    faceMatchData = JumioDocumentScanPresenter.this.createImageByteArray(bitmap3, imageType);
                }
                JumioDocumentScanPresenter.this.mLoginManager.getClient().createJumioProfile(str, str2, frontData, backData, faceMatchData, new CallbackWithRetrofit<JumioProfile>() {
                    public void onResponse(Call<JumioProfile> call, Response<JumioProfile> response, Retrofit retrofit) {
                        JumioDocumentScanPresenter.this.mScreen.hideProgressDialog();
                        if (!JumioDocumentScanPresenter.this.mScreen.isShown()) {
                            return;
                        }
                        if (response.isSuccessful()) {
                            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_SUBMIT_SUCCESS, new String[0]);
                            if (JumioDocumentScanPresenter.this.mArgs.getBoolean(IdentityVerificationConstants.SHOULD_NAVIGATE_TO_DEBIT, false)) {
                                JumioDocumentScanPresenter.this.mScreen.routeAddCard();
                                JumioDocumentScanPresenter.this.mScreen.finish();
                                return;
                            }
                            JumioDocumentScanPresenter.this.mRouter.routeToComplete();
                            return;
                        }
                        JumioDocumentScanPresenter.this.mScreen.showErrorMessage(Utils.getErrorMessage(response, retrofit));
                        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_SUBMIT_FAILURE, new String[0]);
                        JumioDocumentScanPresenter.this.mScreen.finish();
                    }

                    public void onFailure(Call<JumioProfile> call, Throwable t) {
                        JumioDocumentScanPresenter.this.mScreen.hideProgressDialog();
                        if (JumioDocumentScanPresenter.this.mScreen.isShown()) {
                            JumioDocumentScanPresenter.this.mScreen.finish();
                            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_SUBMIT_FAILURE, new String[0]);
                            JumioDocumentScanPresenter.this.mScreen.showException(t);
                        }
                    }
                });
            }
        });
    }
}
