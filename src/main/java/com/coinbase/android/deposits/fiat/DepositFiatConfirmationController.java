package com.coinbase.android.deposits.fiat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.confirmation.ConfirmationDetailListAdapter;
import com.coinbase.android.confirmation.ConfirmationDetailListAdapter.ConfirmationDetail;
import com.coinbase.android.confirmation.ConfirmationItemDecorator;
import com.coinbase.android.databinding.ControllerFiatConfirmationBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.Utils;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class DepositFiatConfirmationController extends ActionBarController implements DepositFiatConfirmationScreen {
    private ControllerFiatConfirmationBinding mBinding;
    private ProgressDialog mDialog;
    @Inject
    DepositConfirmationPresenter mPresenter;

    public DepositFiatConfirmationController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerFiatConfirmationBinding) DataBindingUtil.inflate(inflater, R.layout.controller_fiat_confirmation, container, false);
        onAttachToolbar(this.mBinding.apbLayout.getToolbar());
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().depositFiatConfirmationControllerSubcomponent(new DepositFiatConfirmationPresenterModule(this, this)).inject(this);
        this.mBinding.btContinue.setOnClickListener(DepositFiatConfirmationController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow(getArgs());
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    protected int getThemeColor() {
        return ContextCompat.getColor(getActivity(), R.color.white);
    }

    protected int getUpIndicatorColor() {
        return R.color.new_up_icon_color;
    }

    protected int getToolbarTextColor() {
        return R.color.primary_mystique_text_color;
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(this.mPresenter.getTitle());
    }

    public void setButtonText(String text) {
        this.mBinding.btContinue.setText(text);
    }

    public void setDetailList(List<ConfirmationDetail> list) {
        this.mBinding.rvDetails.setAdapter(new ConfirmationDetailListAdapter(getActivity(), this.mPresenter));
        this.mBinding.rvDetails.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        ConfirmationItemDecorator itemDecorator = new ConfirmationItemDecorator(getActivity(), (int) getActivity().getResources().getDimension(R.dimen.margin_default));
        itemDecorator.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.linked_accounts_divider));
        this.mBinding.rvDetails.addItemDecoration(itemDecorator);
        this.mBinding.rvDetails.setHasFixedSize(true);
    }

    public void setDepositAmount(String amount) {
        this.mBinding.tvAmount.setText(amount);
    }

    public void displayFeeHelpUrl(String feeHelpUrl) {
        if (!TextUtils.isEmpty(feeHelpUrl)) {
            Uri uri = Uri.parse(feeHelpUrl);
            if (uri != null) {
                startActivity(new Intent("android.intent.action.VIEW", uri));
            }
        }
    }

    public void showProgressDialog(String message) {
        this.mDialog = ProgressDialog.show(getActivity(), null, message);
    }

    public void hideProgressDialog() {
        Utils.dismissDialog(this.mDialog);
    }

    public void showWorldPayInfo() {
        this.mBinding.rlWorldPay.setVisibility(0);
    }

    public void hideWorldPayInfo() {
        this.mBinding.rlWorldPay.setVisibility(8);
    }

    public void enableConfirmButton() {
        this.mBinding.btContinue.setEnabled(true);
    }

    public void disableConfirmButton() {
        this.mBinding.btContinue.setEnabled(false);
    }
}
