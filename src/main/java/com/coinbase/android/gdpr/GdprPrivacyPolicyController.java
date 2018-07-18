package com.coinbase.android.gdpr;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ScrollView;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerGdprPrivacyPolicyBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class GdprPrivacyPolicyController extends ActionBarController implements GdprPrivacyPolicyScreen {
    private ControllerGdprPrivacyPolicyBinding mBinding;
    @Inject
    GdprPrivacyPolicyPresenter mPresenter;

    public GdprPrivacyPolicyController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerGdprPrivacyPolicyBinding) DataBindingUtil.inflate(inflater, R.layout.controller_gdpr_privacy_policy, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addGdprPrivacyPolicyControllerSubcomponent(new GdprPrivacyPolicyPresenterModule(this)).inject(this);
        onAttachToolbar(null);
        this.mBinding.btnAcknowledge.setOnClickListener(GdprPrivacyPolicyController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.svPrivacyPolicyText.getViewTreeObserver().addOnScrollChangedListener(GdprPrivacyPolicyController$$Lambda$2.lambdaFactory$(this));
        checkScrollPosition();
        this.mBinding.tvNoticeParagraph.setText(Html.fromHtml(getResources().getString(R.string.gdpr_privacy_notice_paragraph)));
        this.mBinding.tvNoticeParagraph.setMovementMethod(LinkMovementMethod.getInstance());
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
                layoutParams.gravity = 17;
            } else {
                layoutParams.gravity = 0;
            }
            this.mBinding.llWhiteBoxContainer.setLayoutParams(layoutParams);
        }
    }

    public void setBackgroundTranslucent(boolean isBackgroundTranslucent) {
        if (getResources() != null) {
            if (isBackgroundTranslucent) {
                this.mBinding.flContainer.setBackgroundColor(getResources().getColor(R.color.gdpr_modal_background));
            } else {
                this.mBinding.flContainer.setBackground(getResources().getDrawable(R.drawable.signin_background));
            }
        }
    }

    public void showProgressBar() {
        this.mBinding.progressBar.setVisibility(0);
    }

    public void hideProgressBar() {
        this.mBinding.progressBar.setVisibility(8);
    }

    protected boolean onBackPressed() {
        return getArgs().getBoolean(OnboardingRouter.DISABLE_BACK, false);
    }

    private void checkScrollPosition() {
        ScrollView scrollView = this.mBinding.svPrivacyPolicyText;
        if (scrollView == null) {
            return;
        }
        if (scrollView.getChildAt(0).getBottom() <= scrollView.getHeight() + scrollView.getScrollY()) {
            this.mBinding.dividerAboveScrollAcknowledge.setVisibility(4);
            this.mBinding.tvScrollToAcknowledge.setVisibility(4);
            this.mBinding.btnAcknowledge.setEnabled(true);
            return;
        }
        this.mBinding.dividerAboveScrollAcknowledge.setVisibility(0);
        this.mBinding.tvScrollToAcknowledge.setVisibility(0);
        this.mBinding.btnAcknowledge.setEnabled(false);
    }
}
