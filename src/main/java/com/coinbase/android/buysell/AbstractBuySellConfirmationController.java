package com.coinbase.android.buysell;

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
import com.coinbase.android.R;
import com.coinbase.android.confirmation.ConfirmationDetailListAdapter;
import com.coinbase.android.confirmation.ConfirmationItemDecorator;
import com.coinbase.android.databinding.ControllerBuysellConfirmationBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

abstract class AbstractBuySellConfirmationController extends ActionBarController implements AbstractBuySellConfirmationScreen {
    private ConfirmationDetailListAdapter mAdapter;
    protected ControllerBuysellConfirmationBinding mBinding;
    @Inject
    AbstractBuySellConfirmationPresenter mPresenter;

    public AbstractBuySellConfirmationController(Bundle bundle) {
        super(bundle);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerBuysellConfirmationBinding) DataBindingUtil.inflate(inflater, R.layout.controller_buysell_confirmation, container, false);
        onAttachToolbar(this.mBinding.apbLayout.getToolbar());
        this.mBinding.btnContinue.setOnClickListener(AbstractBuySellConfirmationController$$Lambda$1.lambdaFactory$(this));
        initDetailList();
        this.mPresenter.onViewCreated(getArgs());
        return this.mBinding.getRoot();
    }

    static /* synthetic */ void lambda$onCreateView$0(AbstractBuySellConfirmationController this_, View view) {
        this_.disableConfirmButtonAndShowProgress();
        this_.mPresenter.onConfirmClicked();
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
        this.mBinding.btnContinue.setText(text);
    }

    public void setCryptoAmount(String cryptoAmount) {
        this.mBinding.tvCryptoAmount.setText(cryptoAmount);
    }

    public void showWarningMessage(String message) {
        this.mBinding.tvWarningMessage.setVisibility(0);
        this.mBinding.tvWarningMessage.setText(message);
    }

    public void hideWarningMessage() {
        this.mBinding.tvWarningMessage.setVisibility(8);
    }

    public void displayFeeHelpUrl(String feeHelpUrl) {
        if (!TextUtils.isEmpty(feeHelpUrl)) {
            Uri uri = Uri.parse(feeHelpUrl);
            if (uri != null) {
                startActivity(new Intent("android.intent.action.VIEW", uri));
            }
        }
    }

    public void onConfirmCompleted() {
        if (getRouter() != null) {
            getRouter().popCurrentController();
        }
    }

    public void notifyDataSetChanged() {
        this.mAdapter.notifyDataSetChanged();
    }

    protected void disableConfirmButtonAndShowProgress() {
        this.mBinding.btnContinue.setEnabled(false);
        this.mBinding.btnContinue.setText("");
        this.mBinding.btnProgress.setVisibility(0);
    }

    protected void enableConfirmButtonAndHideProgress() {
        this.mBinding.btnContinue.setEnabled(true);
        this.mBinding.btnContinue.setText(this.mPresenter.getTitle());
        this.mBinding.btnProgress.setVisibility(8);
    }

    protected final void showProgressBarOverlay() {
        disableConfirmButtonAndShowProgress();
        this.mBinding.rlProgressBarOverlay.setVisibility(0);
    }

    protected final void hideProgressBarOverlay() {
        enableConfirmButtonAndHideProgress();
        this.mBinding.rlProgressBarOverlay.setVisibility(8);
    }

    private void initDetailList() {
        this.mAdapter = new ConfirmationDetailListAdapter(getActivity(), this.mPresenter);
        this.mBinding.rvDetails.setAdapter(this.mAdapter);
        this.mBinding.rvDetails.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        ConfirmationItemDecorator itemDecorator = new ConfirmationItemDecorator(getActivity(), (int) getActivity().getResources().getDimension(R.dimen.margin_default));
        itemDecorator.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.linked_accounts_divider));
        this.mBinding.rvDetails.addItemDecoration(itemDecorator);
    }
}
