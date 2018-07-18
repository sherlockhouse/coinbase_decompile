package com.coinbase.android.identityverification;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivity;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerIdentityDocumentScanBinding;
import com.coinbase.android.dialog.ConfirmationDialogController;
import com.coinbase.android.paymentmethods.card.CardFormController;
import com.coinbase.android.signin.RegistrationControllerActivity;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.Utils;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

@ControllerScope
public class InAppIdentityDocumentScanController extends ActionBarController implements JumioDocumentScanScreen {
    private static final String[] PERMISSION_SETUPCONTINUERETAKEBUTTON = new String[]{"android.permission.CAMERA"};
    private static final int REQUEST_SETUPCONTINUERETAKEBUTTON = 0;
    protected ControllerIdentityDocumentScanBinding mBinding;
    ProgressDialog mDialog;
    private final Logger mLogger = LoggerFactory.getLogger(InAppIdentityDocumentScanController.class);
    @Inject
    protected JumioDocumentScanPresenter mPresenter;

    public static class RetakePhotoDialogController extends ConfirmationDialogController {
        @Inject
        RetakeAndContinueConnector mConnector;

        public RetakePhotoDialogController(Bundle args) {
            super(args);
        }

        protected View inflateContent(LayoutInflater inflater, ViewGroup container) {
            ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().inject(this);
            return super.inflateContent(inflater, container);
        }

        public String getMessage() {
            return getApplicationContext().getString(R.string.retake_photo);
        }

        public void onUserConfirm() {
            this.mConnector.getRetake().onNext(new MutableBoolean(true));
            dismiss();
        }

        public void onUserCancel() {
            this.mConnector.getContinue().onNext(new MutableBoolean(true));
            dismiss();
        }

        protected int getPositiveButtonText() {
            return R.string.retake;
        }

        protected int getNegativeButtonText() {
            return R.string.continue_button_upper_case;
        }
    }

    private static final class SetupContinueRetakeButtonPermissionRequest implements PermissionRequest {
        private final WeakReference<InAppIdentityDocumentScanController> weakTarget;

        private SetupContinueRetakeButtonPermissionRequest(InAppIdentityDocumentScanController target) {
            this.weakTarget = new WeakReference(target);
        }

        public void proceed() {
            InAppIdentityDocumentScanController target = (InAppIdentityDocumentScanController) this.weakTarget.get();
            if (target != null) {
                target.requestPermissions(InAppIdentityDocumentScanController.PERMISSION_SETUPCONTINUERETAKEBUTTON, 0);
            }
        }

        public void cancel() {
            InAppIdentityDocumentScanController target = (InAppIdentityDocumentScanController) this.weakTarget.get();
            if (target != null) {
                target.showDeniedForCamera();
            }
        }
    }

    public InAppIdentityDocumentScanController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerIdentityDocumentScanBinding) DataBindingUtil.inflate(inflater, R.layout.controller_identity_document_scan, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addInAppIdentityDocumentScanControllerSubcomponent(new InAppIdentityDocumentScanPresenterModule(this, this)).inject(this);
        this.mPresenter.onCreate(getArgs());
        return this.mBinding.getRoot();
    }

    protected void onAttach(View view) {
        super.onAttach(view);
        setupContinueRetakeButtonWithCheck();
    }

    protected void onDestroyView(View view) {
        super.onDestroyView(view);
        hideProgressDialog();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.verify_identity_title));
    }

    private void setupContinueRetakeButton() {
        this.mBinding.jumioDocumentScanContent.btnContinue.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                InAppIdentityDocumentScanController.this.mPresenter.onContinueClicked();
            }
        });
        this.mBinding.jumioDocumentScanContent.btnRetake.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                InAppIdentityDocumentScanController.this.mPresenter.onRetakeClicked();
            }
        });
        this.mPresenter.onCameraPermissionGranted();
    }

    private void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog(R.string.permission_jumio_rationale, request);
    }

    void showDeniedForCamera() {
        Utils.showMessage(getActivity(), (int) R.string.permission_jumio_denied, 0);
    }

    private void showRationaleDialog(int messageResId, final PermissionRequest request) {
        new Builder(getActivity()).setPositiveButton(R.string.button_allow, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                request.proceed();
            }
        }).setNegativeButton(R.string.button_deny, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).setCancelable(false).setMessage(messageResId).show();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public void showTakeSelfie() {
        updateTakePicture(R.drawable.id_selfie_phone);
    }

    public void showTakeBack() {
        updateTakePicture(R.drawable.id_licence_back);
    }

    public void showTakeFront() {
        updateTakePicture(R.drawable.id_licence_front);
    }

    private void updateTakePicture(int previewImage) {
        this.mBinding.jumioDocumentScanContent.ivJumioImagePreview.setImageResource(previewImage);
        this.mBinding.jumioDocumentScanContent.btnContinue.setText(R.string.jumio_take_photo_btn);
        this.mBinding.jumioDocumentScanContent.btnRetake.setVisibility(8);
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    public void finish() {
        if (getActivity() != null) {
            if (!(getActivity() instanceof MainActivity) && !(getActivity() instanceof RegistrationControllerActivity)) {
                getActivity().finish();
            } else if (getRouter().hasRootController()) {
                getRouter().popToRoot();
            } else {
                getActivity().finish();
            }
        }
    }

    public void routeTakeDocumentPhoto(Bundle extras) {
        pushModalController(new TakeDocumentPhotoController(appendModalArgs(extras)));
    }

    public void routeRetakeDocumentPhoto() {
        pushDialogModalController(new RetakePhotoDialogController(appendModalArgs(new Bundle())));
    }

    public void routeAddCard() {
        pushModalController(new CardFormController(appendModalArgs(new Bundle())));
    }

    public void updateScanText(int text, int subText) {
        this.mBinding.jumioDocumentScanContent.tvScanTheFrontBack.setText(text);
        this.mBinding.jumioDocumentScanContent.tvJumioScanPrompt.setText(subText);
    }

    public void showContinueConfirmPicture(Bitmap picture) {
        this.mBinding.jumioDocumentScanContent.ivJumioImagePreview.setImageBitmap(picture);
        this.mBinding.jumioDocumentScanContent.btnContinue.setText(R.string.jumio_confrim_title);
        this.mBinding.jumioDocumentScanContent.btnRetake.setVisibility(0);
    }

    public void hideButtons() {
        this.mBinding.jumioDocumentScanContent.btnContinue.setEnabled(false);
        this.mBinding.jumioDocumentScanContent.btnRetake.setEnabled(false);
    }

    public void showJumioUploadingProgress() {
        this.mDialog = ProgressDialog.show(getActivity(), "", getApplicationContext().getString(R.string.jumio_uploading));
    }

    public void showPleaseWaitProgress() {
        this.mDialog = ProgressDialog.show(getActivity(), null, getApplicationContext().getString(R.string.please_wait));
    }

    public void hideProgressDialog() {
        if (getActivity() != null) {
            Utils.dismissDialog(this.mDialog);
        }
    }

    public void showErrorMessage(String message) {
        Utils.showMessage(getActivity(), message, 1);
    }

    public void showException(Throwable t) {
        Utils.showMessage(getApplicationContext(), t, 1);
    }

    void setupContinueRetakeButtonWithCheck() {
        if (PermissionUtils.hasSelfPermissions(getActivity(), PERMISSION_SETUPCONTINUERETAKEBUTTON)) {
            setupContinueRetakeButton();
        } else if (PermissionUtils.shouldShowRequestPermissionRationale(getActivity(), PERMISSION_SETUPCONTINUERETAKEBUTTON)) {
            showRationaleForCamera(new SetupContinueRetakeButtonPermissionRequest());
        } else {
            requestPermissions(PERMISSION_SETUPCONTINUERETAKEBUTTON, 0);
        }
    }

    void onRequestPermissionsResult(InAppIdentityDocumentScanController target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (target.getActivity() == null) {
                    this.mLogger.error("Activity null on request permission results in jumio document scan controller");
                    return;
                } else if (PermissionUtils.getTargetSdkVersion(target.getActivity()) < 23 && !PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_SETUPCONTINUERETAKEBUTTON)) {
                    target.showDeniedForCamera();
                    return;
                } else if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.setupContinueRetakeButton();
                    return;
                } else {
                    target.showDeniedForCamera();
                    return;
                }
            default:
                return;
        }
    }
}
