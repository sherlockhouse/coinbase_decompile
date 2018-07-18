package com.coinbase.android.signin;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerAcceptTermsBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.Utils;
import javax.inject.Inject;

@ControllerScope
public class AcceptTermsController extends ActionBarController implements AcceptTermsScreen {
    @Inject
    AcceptTermsPresenter mAcceptTermsPresenter;
    private ControllerAcceptTermsBinding mBinding;
    private ProgressDialog progressDialog;

    public AcceptTermsController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerAcceptTermsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_accept_terms, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addAcceptTermsControllerSubcomponent(new AcceptTermsPresenterModule(this, this)).inject(this);
        onAttachToolbar(null);
        this.mBinding.tvSignupTncDescription.setMovementMethod(new ScrollingMovementMethod());
        this.mBinding.tvSignupTncCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AcceptTermsController.this.mAcceptTermsPresenter.onCancelClicked();
            }
        });
        this.mBinding.tvSignupTncAgree.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AcceptTermsController.this.mAcceptTermsPresenter.onAcceptTermsClicked();
            }
        });
        return this.mBinding.getRoot();
    }

    protected boolean onBackPressed() {
        return this.mAcceptTermsPresenter.onBackPressed();
    }

    protected void onShow() {
        super.onShow();
        this.mAcceptTermsPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mAcceptTermsPresenter.onHide();
    }

    public void showProgressDialog() {
        this.progressDialog = ProgressDialog.show(getActivity(), null, getApplicationContext().getString(R.string.please_wait));
    }

    public void hideProgressDialog() {
        Utils.dismissDialog(this.progressDialog);
    }

    public void setTermsDescription(Spanned s) {
        this.mBinding.tvSignupTncDescription.setText(s);
        Utils.stripUnderlines(this.mBinding.tvSignupTncDescription);
    }
}
