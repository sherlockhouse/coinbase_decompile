package com.coinbase.android.transfers;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerSendBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class SendController extends ActionBarController implements SendScreen {
    private ControllerSendBinding mBinding;
    @Inject
    SendPresenter mPresenter;

    public SendController(Bundle args) {
        super(args);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerSendBinding) DataBindingUtil.inflate(inflater, R.layout.controller_send, container, false);
        onAttachToolbar(this.mBinding.apbLayout.getToolbar());
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addSendControllerSubcomponent(new SendPresenterModule(this, this, this.mBinding.apbLayout)).inject(this);
        this.mBinding.ivSwap.setOnClickListener(SendController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnContinue.setOnClickListener(SendController$$Lambda$2.lambdaFactory$(this));
        this.mBinding.tvSendAll.setOnClickListener(SendController$$Lambda$3.lambdaFactory$(this));
        this.mBinding.tvSendHalf.setOnClickListener(SendController$$Lambda$4.lambdaFactory$(this));
        this.mBinding.tvSendFourth.setOnClickListener(SendController$$Lambda$5.lambdaFactory$(this));
        this.mPresenter.onViewCreated(getArgs());
        return this.mBinding.getRoot();
    }

    static /* synthetic */ void lambda$onCreateView$1(SendController this_, View view) {
        view.performHapticFeedback(1);
        this_.mPresenter.onContinueButtonClicked();
    }

    protected int getThemeColor() {
        return ContextCompat.getColor(getApplicationContext(), R.color.white);
    }

    protected int getToolbarTextColor() {
        return R.color.primary_mystique_text_color;
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.send));
    }

    public void updateTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            super.setTitle(new SpannableStringBuilder(title));
        }
    }

    public void updatePrimaryAmountText(SpannableStringBuilder stringBuilder) {
        this.mBinding.tvAmount.setTextSize(0, getResources().getDimension(R.dimen.currency_amount));
        this.mBinding.tvAmount.setText(stringBuilder);
    }

    public void showInvalidKeystroke() {
        this.mBinding.tvAmount.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake));
    }

    public void showProgressDialog() {
        this.mBinding.progressBar.setVisibility(0);
    }

    public void hideProgressDialog() {
        this.mBinding.progressBar.setVisibility(8);
    }

    public void showSendMaxButtons() {
        this.mBinding.llSendMaxContainer.setVisibility(0);
    }

    public void hideSendMaxButtons() {
        this.mBinding.llSendMaxContainer.setVisibility(8);
    }
}
