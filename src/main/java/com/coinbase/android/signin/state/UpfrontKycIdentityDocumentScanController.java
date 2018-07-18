package com.coinbase.android.signin.state;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.coinbase.android.MainActivity;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerUpfrontKycIdentityDocumentScanBinding;
import com.coinbase.android.signin.RegistrationControllerActivity;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.Utils;
import javax.inject.Inject;

public class UpfrontKycIdentityDocumentScanController extends ActionBarController implements UpfrontKycIdentityDocumentScanScreen {
    protected ControllerUpfrontKycIdentityDocumentScanBinding mBinding;
    ProgressDialog mDialog;
    private HintsAdapter mHintsAdapter;
    @Inject
    protected UpfrontKycIdentityDocumentScanPresenter mPresenter;

    private class HintsAdapter extends Adapter {
        private HintsAdapter() {
        }

        public int getItemCount() {
            return UpfrontKycIdentityDocumentScanController.this.mPresenter.getHintsItemCount();
        }

        public int getItemViewType(int position) {
            return UpfrontKycIdentityDocumentScanController.this.mPresenter.getHintsItemViewType(position);
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return UpfrontKycIdentityDocumentScanController.this.mPresenter.onCreateHintsViewHolder(parent, viewType);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            UpfrontKycIdentityDocumentScanController.this.mPresenter.onBindHintsViewHolder(holder, position);
        }
    }

    public UpfrontKycIdentityDocumentScanController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerUpfrontKycIdentityDocumentScanBinding) DataBindingUtil.inflate(inflater, R.layout.controller_upfront_kyc_identity_document_scan, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addUpfrontKycIdentityDocumentScanControllerSubcomponent(new UpfrontKycIdentityDocumentScanPresenterModule(this, this)).inject(this);
        this.mPresenter.onCreate(getArgs());
        this.mBinding.btnContinue.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                UpfrontKycIdentityDocumentScanController.this.mPresenter.onContinueClicked();
            }
        });
        this.mBinding.btnRetake.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                UpfrontKycIdentityDocumentScanController.this.mPresenter.onRetakeClicked();
            }
        });
        this.mHintsAdapter = new HintsAdapter();
        this.mBinding.rvHints.setAdapter(this.mHintsAdapter);
        this.mBinding.rvHints.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        return this.mBinding.getRoot();
    }

    protected int getThemeColor() {
        return ContextCompat.getColor(getActivity(), 17170445);
    }

    protected int getStatusBarColor() {
        return ContextCompat.getColor(getActivity(), R.color.immersive_background);
    }

    protected int getToolbarTextColor() {
        return R.color.white;
    }

    protected void onDestroyView(View view) {
        super.onDestroyView(view);
        hideProgressDialog();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder("");
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
            if (((getActivity() instanceof MainActivity) || (getActivity() instanceof RegistrationControllerActivity)) && getRouter().hasRootController()) {
                getRouter().popToRoot();
            } else {
                getActivity().finish();
            }
        }
    }

    public void routeTakeDocumentPhoto(Bundle extras) {
        replaceModalController(new UpfrontKycTakeDocumentPhotoController(appendModalArgs(extras)));
    }

    public void showNoFaceDetected() {
        this.mBinding.rvHints.setVisibility(8);
        this.mBinding.tvVerifyHeader.setText(R.string.upfront_kyc_identity_no_face_detected_header);
        this.mBinding.btnContinue.setText(R.string.upfront_kyc_identity_no_face_detected_button);
        this.mBinding.tvNoFaceDetected.setVisibility(0);
    }

    public void showContinueConfirmPicture(Bitmap picture) {
        this.mBinding.ivJumioImagePreview.setImageBitmap(picture);
        this.mBinding.btnRetake.setVisibility(0);
    }

    public void hideButtons() {
        this.mBinding.btnContinue.setEnabled(false);
        this.mBinding.btnRetake.setEnabled(false);
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

    public void setContinueButtonText(int text) {
        this.mBinding.btnContinue.setText(text);
    }

    public void notifyHintsChanged() {
        this.mBinding.rvHints.setVisibility(0);
        this.mHintsAdapter.notifyDataSetChanged();
    }
}
