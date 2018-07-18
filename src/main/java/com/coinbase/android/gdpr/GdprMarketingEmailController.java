package com.coinbase.android.gdpr;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerGdprMarketingEmailPromptBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class GdprMarketingEmailController extends ActionBarController implements GdprMarketingEmailScreen {
    private ControllerGdprMarketingEmailPromptBinding mBinding;
    @Inject
    GdprMarketingEmailPresenter mPresenter;

    public GdprMarketingEmailController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerGdprMarketingEmailPromptBinding) DataBindingUtil.inflate(inflater, R.layout.controller_gdpr_marketing_email_prompt, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addGdprMarketingEmailControllerSubcomponent(new GdprMarketingEmailPresenterModule(this)).inject(this);
        onAttachToolbar(null);
        this.mBinding.btnYes.setOnClickListener(GdprMarketingEmailController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnNo.setOnClickListener(GdprMarketingEmailController$$Lambda$2.lambdaFactory$(this));
        this.mPresenter.onCreateView(getArgs());
        return this.mBinding.getRoot();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    public void setModalCentered(boolean isCentered) {
        if (getResources() != null) {
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            int sidesMargin = getResources().getDimensionPixelSize(R.dimen.signup_form_horizontal_margin);
            int topBottomMargin = getResources().getDimensionPixelSize(R.dimen.margin_4x);
            layoutParams.setMargins(sidesMargin, topBottomMargin, sidesMargin, topBottomMargin);
            if (isCentered) {
                this.mBinding.rlContainer.setGravity(17);
            } else {
                this.mBinding.rlContainer.setGravity(0);
            }
            this.mBinding.llWhiteBoxContainer.setLayoutParams(layoutParams);
        }
    }

    public void setBackgroundTranslucent(boolean isBackgroundTranslucent) {
        if (getResources() != null) {
            if (isBackgroundTranslucent) {
                this.mBinding.rlContainer.setBackgroundColor(getResources().getColor(R.color.gdpr_modal_background));
            } else {
                this.mBinding.rlContainer.setBackground(getResources().getDrawable(R.drawable.signin_background));
            }
        }
    }

    protected boolean onBackPressed() {
        return getArgs().getBoolean(OnboardingRouter.DISABLE_BACK, false);
    }

    public void hideProgressBar() {
        this.mBinding.progressBar.setVisibility(8);
    }

    public void showProgressBar() {
        this.mBinding.progressBar.setVisibility(0);
    }
}
