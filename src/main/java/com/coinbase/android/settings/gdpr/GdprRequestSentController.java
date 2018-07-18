package com.coinbase.android.settings.gdpr;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerGdprRequestSentBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

public class GdprRequestSentController extends ActionBarController implements GdprRequestSentScreen {
    private ControllerGdprRequestSentBinding mBinding;
    @Inject
    GdprRequestSentPresenter mPresenter;

    public GdprRequestSentController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerGdprRequestSentBinding) DataBindingUtil.inflate(inflater, R.layout.controller_gdpr_request_sent, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addGdprRequestSentControllerSubcomponent(new GdprRequestSentPresenterModule(this, this)).inject(this);
        this.mBinding.btGotoSettings.setOnClickListener(GdprRequestSentController$$Lambda$1.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.gdpr_request_sent));
    }

    protected boolean onBackPressed() {
        if (this.mPresenter == null) {
            return super.onBackPressed();
        }
        this.mPresenter.gotoSettings();
        return true;
    }
}
