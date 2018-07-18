package com.coinbase.android.gdpr;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerGdprIntroBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class GdprIntroController extends ActionBarController implements GdprIntroScreen {
    private ControllerGdprIntroBinding mBinding;
    @Inject
    GdprIntroPresenter mPresenter;

    public GdprIntroController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerGdprIntroBinding) DataBindingUtil.inflate(inflater, R.layout.controller_gdpr_intro, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addGdprIntroControllerSubcomponent(new GdprIntroPresenterModule(this)).inject(this);
        onAttachToolbar(null);
        this.mBinding.btnNext.setOnClickListener(GdprIntroController$$Lambda$1.lambdaFactory$(this));
        this.mPresenter.onCreateView(getArgs());
        return this.mBinding.getRoot();
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

    protected boolean onBackPressed() {
        return getArgs().getBoolean(OnboardingRouter.DISABLE_BACK, false);
    }
}
