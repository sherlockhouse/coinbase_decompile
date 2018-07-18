package com.coinbase.android.signin.state;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.identityverification.IdentityVerificationBitmapConnector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ModelGsonAdapterFactory;
import com.coinbase.api.internal.models.jumio.JumioProfiles;
import com.coinbase.api.internal.models.jumio.JumioProfiles.Type;
import com.coinbase.api.internal.models.jumio.supportedDocuments.Data;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedDocument;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedIdType;
import com.google.gson.GsonBuilder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;
import retrofit2.Response;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class UpfrontKycIdentityAcceptableDocumentsPresenter {
    private static final String[] PERMISSION_SETUPCONTINUERETAKEBUTTON = new String[]{"android.permission.CAMERA"};
    private static final int REQUEST_SETUPCONTINUERETAKEBUTTON = 0;
    private final AppCompatActivity mAppCompatActivity;
    private final BackNavigationConnector mBackNavigationConnector;
    private final Context mContext;
    private List<Integer> mDocIcons;
    private List<String> mDocList;
    private Data mIdentityDoc;
    private final IdentityVerificationBitmapConnector mIdentityVerificationBitmapConnector;
    private final Logger mLogger = LoggerFactory.getLogger(UpfrontKycIdentityAcceptableDocumentsPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final UpfrontKycIdentityAcceptableDocumentsRouter mRouter;
    private final UpfrontKycIdentityAcceptableDocumentsScreen mScreen;
    private int mSelectedDocument = -1;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    private static final class SetupContinueRetakeButtonPermissionRequest implements PermissionRequest {
        private final WeakReference<UpfrontKycIdentityAcceptableDocumentsPresenter> weakTarget;

        private SetupContinueRetakeButtonPermissionRequest(UpfrontKycIdentityAcceptableDocumentsPresenter target) {
            this.weakTarget = new WeakReference(target);
        }

        public void proceed() {
            UpfrontKycIdentityAcceptableDocumentsPresenter target = (UpfrontKycIdentityAcceptableDocumentsPresenter) this.weakTarget.get();
            if (target != null) {
                target.requestPermissions(UpfrontKycIdentityAcceptableDocumentsPresenter.PERMISSION_SETUPCONTINUERETAKEBUTTON, 0);
            }
        }

        public void cancel() {
            UpfrontKycIdentityAcceptableDocumentsPresenter target = (UpfrontKycIdentityAcceptableDocumentsPresenter) this.weakTarget.get();
            if (target != null) {
                target.showDeniedForCamera();
            }
        }
    }

    @Inject
    public UpfrontKycIdentityAcceptableDocumentsPresenter(UpfrontKycIdentityAcceptableDocumentsScreen screen, AppCompatActivity appCompatActivity, LoginManager loginManager, Application app, SnackBarWrapper snackBarWrapper, BackNavigationConnector backNavigationConnector, IdentityVerificationBitmapConnector bitmapConnector, @MainScheduler Scheduler mainScheduler, UpfrontKycIdentityAcceptableDocumentsRouter router) {
        this.mScreen = screen;
        this.mAppCompatActivity = appCompatActivity;
        this.mLoginManager = loginManager;
        this.mContext = app;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mBackNavigationConnector = backNavigationConnector;
        this.mIdentityVerificationBitmapConnector = bitmapConnector;
        this.mMainScheduler = mainScheduler;
        this.mRouter = router;
    }

    void onShow() {
        this.mSelectedDocument = -1;
        getIdentitySupportedDocument();
    }

    void onHide() {
        this.mSubscription.clear();
    }

    void onItemClick(int position) {
        if (PermissionUtils.hasSelfPermissions(this.mAppCompatActivity, PERMISSION_SETUPCONTINUERETAKEBUTTON)) {
            this.mIdentityVerificationBitmapConnector.get().onNext(null);
            this.mRouter.routeToNext(new GsonBuilder().registerTypeAdapterFactory(ModelGsonAdapterFactory.create()).create().toJson(this.mIdentityDoc), position);
            SupportedIdType idType = (SupportedIdType) this.mIdentityDoc.getSupportedIdTypes().get(position);
            if (idType != null && idType.getType() != null) {
                MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_DOC_TYPE_SELECT, "id_type", idType.getType().toString());
                return;
            }
            return;
        }
        this.mSelectedDocument = position;
        showRequestPermissions();
    }

    public boolean onBackPressed() {
        this.mRouter.routeToBack();
        return true;
    }

    private void getIdentitySupportedDocument() {
        this.mSubscription.add(this.mLoginManager.getClient().getJumioSupportedDocumentRX(this.mLoginManager.getActiveUserCountryCode()).onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(UpfrontKycIdentityAcceptableDocumentsPresenter$$Lambda$1.lambdaFactory$(this), UpfrontKycIdentityAcceptableDocumentsPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$getIdentitySupportedDocument$0(UpfrontKycIdentityAcceptableDocumentsPresenter this_, Pair pair) {
        if (!PermissionUtils.hasSelfPermissions(this_.mAppCompatActivity, PERMISSION_SETUPCONTINUERETAKEBUTTON)) {
            this_.showRequestPermissions();
        }
        Response<SupportedDocument> response = pair.first;
        if (this_.mScreen.isShown()) {
            this_.mScreen.hideProgress();
            if (response.isSuccessful()) {
                this_.mIdentityDoc = ((SupportedDocument) response.body()).getData();
                this_.setUpDocumentViews();
                return;
            }
            this_.mSnackBarWrapper.showError(response);
            this_.handleIdentityError();
        }
    }

    static /* synthetic */ void lambda$getIdentitySupportedDocument$1(UpfrontKycIdentityAcceptableDocumentsPresenter this_, Throwable t) {
        if (this_.mScreen.isShown()) {
            this_.mSnackBarWrapper.showGenericErrorTryAgain();
            this_.mScreen.hideProgress();
            this_.handleIdentityError();
        }
    }

    private void handleIdentityError() {
        if (this.mScreen.isShown()) {
            this.mBackNavigationConnector.get().onNext(null);
        }
    }

    private void setUpDocumentViews() {
        List<SupportedIdType> docIds = this.mIdentityDoc.getSupportedIdTypes();
        this.mDocList = new ArrayList();
        this.mDocIcons = new ArrayList();
        for (SupportedIdType id : docIds) {
            this.mDocList.add(JumioProfiles.getDisplayName(this.mContext, id.getType()));
            if (id.getType() == Type.PASSPORT) {
                this.mDocIcons.add(Integer.valueOf(R.drawable.upfront_kyc_passport));
            } else {
                this.mDocIcons.add(Integer.valueOf(R.drawable.upfront_kyc_id_license));
            }
        }
        this.mScreen.initializeDocsList(this.mDocList, this.mDocIcons);
    }

    void onRequestPermissionsResult(int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (this.mScreen.isShown()) {
                    int selectedDoc = this.mSelectedDocument;
                    this.mSelectedDocument = -1;
                    if (PermissionUtils.getTargetSdkVersion(this.mAppCompatActivity) < 23 && !PermissionUtils.hasSelfPermissions(this.mAppCompatActivity, PERMISSION_SETUPCONTINUERETAKEBUTTON)) {
                        showDeniedForCamera();
                        return;
                    } else if (!PermissionUtils.verifyPermissions(grantResults)) {
                        showDeniedForCamera();
                        return;
                    } else if (selectedDoc >= 0) {
                        onItemClick(selectedDoc);
                        return;
                    } else {
                        return;
                    }
                }
                this.mLogger.error("Activity null on request permission results in document selector controller");
                return;
            default:
                return;
        }
    }

    private void showRequestPermissions() {
        if (PermissionUtils.shouldShowRequestPermissionRationale(this.mAppCompatActivity, PERMISSION_SETUPCONTINUERETAKEBUTTON)) {
            showRationaleForCamera(new SetupContinueRetakeButtonPermissionRequest());
        } else {
            this.mScreen.requestPermissions(PERMISSION_SETUPCONTINUERETAKEBUTTON, 0);
        }
    }

    private void showRationaleForCamera(PermissionRequest request) {
        this.mScreen.showRationaleDialog(R.string.upfront_kyc_identity_camera_permission_request, R.string.upfront_kyc_identity_camera_permission_reason, request);
    }

    void showDeniedForCamera() {
        this.mSelectedDocument = -1;
        this.mSnackBarWrapper.show((int) R.string.permission_jumio_denied);
    }

    void requestPermissions(String[] permissions, int requestCode) {
        this.mScreen.requestPermissions(permissions, requestCode);
    }
}
