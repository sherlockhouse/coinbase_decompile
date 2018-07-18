package com.coinbase.android.signin.state;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Pair;
import android.view.ViewGroup;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.identityverification.IdentityVerificationBitmapConnector;
import com.coinbase.android.identityverification.IdentityVerificationBitmapContainer;
import com.coinbase.android.identityverification.IdentityVerificationConstants;
import com.coinbase.android.identityverification.PhotoTakenConnector;
import com.coinbase.android.identityverification.RetakeAndContinueConnector;
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
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.mutable.MutableBoolean;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class UpfrontKycIdentityDocumentScanPresenter {
    private final Scheduler mBackgroundScheduler;
    private final Context mContext;
    private final FaceDetectionUtils mFaceDetectionUtils;
    private final List<Integer> mHints = new LinkedList();
    private final AdapterDelegatesManager<List<Integer>> mHintsAdapterDelegate = new AdapterDelegatesManager();
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
    private final UpfrontKycIdentityDocumentScanRouter mRouter;
    private final UpfrontKycIdentityDocumentScanScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    boolean mTakingBack = false;
    boolean mTakingFaceMatch = false;

    @Inject
    public UpfrontKycIdentityDocumentScanPresenter(UpfrontKycIdentityDocumentScanScreen screen, UpfrontKycIdentityDocumentScanRouter router, RetakeAndContinueConnector retakeAndContinueConnector, PhotoTakenConnector photoTakenConnector, IdentityVerificationBitmapConnector identityVerificationBitmapConnector, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler, LoginManager loginManager, SnackBarWrapper snackBarWrapper, FaceDetectionUtils faceDetectionUtils, Application app) {
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
        this.mHintsAdapterDelegate.addDelegate(new VerifyHintsAdapterDelegate());
    }

    public void onCreate(Bundle args) {
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
        advanceToNextScreen();
    }

    void onRetakeClicked() {
        takePhoto();
    }

    private void updateLabels() {
        Type type = this.mJumioType.getType();
        this.mHints.clear();
        if (this.mTakingBack) {
            switch (type) {
                case DRIVERS_LICENCE:
                case ID_CARD:
                    this.mHints.addAll(Arrays.asList(new Integer[]{Integer.valueOf(R.string.upfront_kyc_identity_take_photo_back_hint_barcode), Integer.valueOf(R.string.upfront_kyc_identity_verify_photo_hint_blurry), Integer.valueOf(R.string.upfront_kyc_identity_verify_photo_hint_frame)}));
                    break;
            }
            this.mScreen.setContinueButtonText(R.string.upfront_kyc_identity_verify_photo_id_confirm);
        } else if (this.mTakingFaceMatch) {
            this.mScreen.setContinueButtonText(R.string.upfront_kyc_identity_verify_photo_facematch_confirm);
            this.mHints.addAll(Arrays.asList(new Integer[]{Integer.valueOf(R.string.upfront_kyc_identity_verify_photo_facematch_hint_hats), Integer.valueOf(R.string.upfront_kyc_identity_verify_photo_facematch_hint_frame)}));
        } else {
            switch (type) {
                case DRIVERS_LICENCE:
                case ID_CARD:
                    this.mScreen.setContinueButtonText(R.string.upfront_kyc_identity_verify_photo_id_confirm);
                    break;
                case PASSPORT:
                    this.mScreen.setContinueButtonText(R.string.upfront_kyc_identity_verify_photo_passport_confirm);
                    break;
            }
            this.mHints.addAll(Arrays.asList(new Integer[]{Integer.valueOf(R.string.upfront_kyc_identity_verify_photo_hint_info), Integer.valueOf(R.string.upfront_kyc_identity_verify_photo_hint_blurry), Integer.valueOf(R.string.upfront_kyc_identity_verify_photo_hint_frame)}));
        }
        this.mScreen.notifyHintsChanged();
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
        this.mSubscription.add(Observable.combineLatest(this.mRetakeAndContinueConnector.getRetake().onBackpressureLatest(), this.mRetakeAndContinueConnector.getContinue().onBackpressureLatest(), this.mPhototakenConnector.get().onBackpressureLatest(), this.mIdentityVerificationBitmapConnector.get(), UpfrontKycIdentityDocumentScanPresenter$$Lambda$1.lambdaFactory$(this)).observeOn(this.mMainScheduler).subscribe(UpfrontKycIdentityDocumentScanPresenter$$Lambda$2.lambdaFactory$()));
    }

    static /* synthetic */ Runnable lambda$onShow$4(UpfrontKycIdentityDocumentScanPresenter this_, MutableBoolean retakeVal, MutableBoolean continueVal, MutableBoolean photoTakenVal, IdentityVerificationBitmapContainer container) {
        boolean photoTaken;
        if (photoTakenVal == null || !photoTakenVal.getValue().booleanValue()) {
            photoTaken = false;
        } else {
            photoTaken = true;
        }
        this_.mLastContainer = container;
        Runnable nextStep = UpfrontKycIdentityDocumentScanPresenter$$Lambda$8.lambdaFactory$();
        if (retakeVal != null && retakeVal.getValue().booleanValue()) {
            retakeVal.setValue(false);
            return UpfrontKycIdentityDocumentScanPresenter$$Lambda$9.lambdaFactory$(this_);
        } else if (continueVal == null || !continueVal.getValue().booleanValue()) {
            if (photoTakenVal != null) {
                photoTakenVal.setValue(false);
            }
            return UpfrontKycIdentityDocumentScanPresenter$$Lambda$11.lambdaFactory$(this_, photoTaken);
        } else if (!this_.shouldAdvanceToNextScreen()) {
            return nextStep;
        } else {
            continueVal.setValue(false);
            return UpfrontKycIdentityDocumentScanPresenter$$Lambda$10.lambdaFactory$(this_);
        }
    }

    static /* synthetic */ void lambda$null$0() {
    }

    static /* synthetic */ void lambda$null$3(UpfrontKycIdentityDocumentScanPresenter this_, boolean photoTaken) {
        if (photoTaken) {
            this_.handleOnPhotoTaken();
        }
    }

    void onHide() {
        this.mSubscription.clear();
        ((MutableBoolean) this.mRetakeAndContinueConnector.getContinue().getValue()).setValue(false);
        ((MutableBoolean) this.mRetakeAndContinueConnector.getRetake().getValue()).setValue(false);
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

    private void handleOnPhotoTaken() {
        if (this.mLastContainer != null) {
            if (this.mLastContainer.getFrontBitmap() != null && !this.mTakingBack && !this.mTakingFaceMatch) {
                detectFace(this.mLastContainer.getFrontBitmap());
            } else if (this.mLastContainer.getFaceMatchBitmap() != null) {
                detectFace(this.mLastContainer.getFaceMatchBitmap());
            } else {
                updateLabels();
                this.mScreen.showContinueConfirmPicture(jumioPicturePreviewResource());
            }
        }
    }

    private void detectFace(Bitmap bmp) {
        this.mScreen.showPleaseWaitProgress();
        this.mSubscription.add(this.mFaceDetectionUtils.hasFace(this.mContext, bmp).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(UpfrontKycIdentityDocumentScanPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$detectFace$6(UpfrontKycIdentityDocumentScanPresenter this_, Boolean hasFace) {
        this_.mScreen.hideProgressDialog();
        if (!hasFace.booleanValue() && this_.mScreen.isShown()) {
            if (this_.mTakingFaceMatch) {
                this_.mSnackBarWrapper.show((int) R.string.retake_selfie);
            }
            this_.mScreen.showNoFaceDetected();
            this_.mScreen.showContinueConfirmPicture(this_.jumioPicturePreviewResource());
        } else if (this_.mScreen.isShown()) {
            this_.updateLabels();
            this_.mScreen.showContinueConfirmPicture(this_.jumioPicturePreviewResource());
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
            this.mRouter.routeToTakePhoto(createScanDocumentExtras(true, false));
        } else if (this.mLastContainer.getFaceMatchBitmap() == null && !this.mTakingFaceMatch && this.mRequiresFaceMatch) {
            this.mRouter.routeToTakePhoto(createScanDocumentExtras(false, true));
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

    private byte[] createImageByteArray(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            bmp.compress(CompressFormat.JPEG, 95, stream);
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
        this.mSubscription.add(Observable.create(UpfrontKycIdentityDocumentScanPresenter$$Lambda$4.lambdaFactory$(this, frontImage, backImage, faceMatchImage)).subscribeOn(this.mBackgroundScheduler).flatMap(UpfrontKycIdentityDocumentScanPresenter$$Lambda$5.lambdaFactory$(this, countryCode, idType)).observeOn(this.mMainScheduler).onBackpressureLatest().subscribe(UpfrontKycIdentityDocumentScanPresenter$$Lambda$6.lambdaFactory$(this), UpfrontKycIdentityDocumentScanPresenter$$Lambda$7.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$createJumioProfile$7(UpfrontKycIdentityDocumentScanPresenter this_, Bitmap frontImage, Bitmap backImage, Bitmap faceMatchImage, Subscriber subscriber) {
        byte[] frontData = this_.createImageByteArray(frontImage);
        byte[] backData = null;
        byte[] faceMatchData = null;
        if (backImage != null) {
            backData = this_.createImageByteArray(backImage);
        }
        if (faceMatchImage != null) {
            faceMatchData = this_.createImageByteArray(faceMatchImage);
        }
        subscriber.onNext(Arrays.asList(new byte[][]{frontData, backData, faceMatchData}));
        subscriber.onCompleted();
    }

    static /* synthetic */ void lambda$createJumioProfile$9(UpfrontKycIdentityDocumentScanPresenter this_, Pair pair) {
        Response<JumioProfile> response = pair.first;
        this_.mScreen.hideProgressDialog();
        if (!this_.mScreen.isShown()) {
            return;
        }
        if (response.isSuccessful()) {
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_SUBMIT_SUCCESS, new String[0]);
            this_.mRouter.routeToComplete();
            return;
        }
        this_.mSnackBarWrapper.showError(response);
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_SUBMIT_FAILURE, new String[0]);
    }

    static /* synthetic */ void lambda$createJumioProfile$10(UpfrontKycIdentityDocumentScanPresenter this_, Throwable t) {
        this_.mScreen.hideProgressDialog();
        if (this_.mScreen.isShown()) {
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_SUBMIT_FAILURE, new String[0]);
            this_.mSnackBarWrapper.showFailure(t);
        }
    }
}
