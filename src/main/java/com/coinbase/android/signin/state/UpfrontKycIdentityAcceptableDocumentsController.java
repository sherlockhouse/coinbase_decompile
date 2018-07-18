package com.coinbase.android.signin.state;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerUpfrontKycIdentityAcceptableDocumentsBinding;
import com.coinbase.android.ui.ActionBarController;
import java.util.List;
import javax.inject.Inject;
import permissions.dispatcher.PermissionRequest;

@ActivityScope
public class UpfrontKycIdentityAcceptableDocumentsController extends ActionBarController implements UpfrontKycIdentityAcceptableDocumentsScreen {
    UpfrontKycAcceptableDocumentsAdapter mAdapter;
    ControllerUpfrontKycIdentityAcceptableDocumentsBinding mBinding;
    @Inject
    UpfrontKycIdentityAcceptableDocumentsPresenter mPresenter;

    public UpfrontKycIdentityAcceptableDocumentsController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerUpfrontKycIdentityAcceptableDocumentsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_upfront_kyc_identity_acceptable_documents, container, false);
        onAttachToolbar(null);
        setForceDisplayHomeAsUp(true);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addUpfrontKycIdentityAcceptableDocumentsControllerSubcomponent(new UpfrontKycIdentityAcceptableDocumentsPresenterModule(this, this)).inject(this);
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    protected boolean onBackPressed() {
        return this.mPresenter.onBackPressed();
    }

    public void hideProgress() {
        this.mBinding.progress.setVisibility(8);
        this.mBinding.nsvIdentityFormContainer.setVisibility(0);
    }

    public void initializeDocsList(List<String> docsList, List<Integer> docsIcons) {
        this.mAdapter = new UpfrontKycAcceptableDocumentsAdapter(getActivity(), docsList, docsIcons);
        this.mBinding.acceptableDocumentsContent.lvList.setAdapter(this.mAdapter);
        this.mBinding.acceptableDocumentsContent.lvList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                UpfrontKycIdentityAcceptableDocumentsController.this.mPresenter.onItemClick(position);
            }
        });
    }

    public void showRationaleDialog(int messageResId, int reasonResId, PermissionRequest request) {
        new Builder(getActivity()).setPositiveButton(R.string.button_allow, UpfrontKycIdentityAcceptableDocumentsController$$Lambda$1.lambdaFactory$(request)).setNegativeButton(R.string.button_deny, UpfrontKycIdentityAcceptableDocumentsController$$Lambda$2.lambdaFactory$(request)).setCancelable(false).setTitle(messageResId).setMessage(reasonResId).show();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.mPresenter.onRequestPermissionsResult(requestCode, grantResults);
    }
}
