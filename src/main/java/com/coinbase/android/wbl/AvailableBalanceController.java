package com.coinbase.android.wbl;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerAvailableBalanceBinding;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class AvailableBalanceController extends ActionBarController {
    ControllerAvailableBalanceBinding mBinding;
    @Inject
    AvailableBalancePresenter mPresenter;

    public AvailableBalanceController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerAvailableBalanceBinding) DataBindingUtil.inflate(inflater, R.layout.controller_available_balance, container, false);
        onAttachToolbar(this.mBinding.apbLayout.getToolbar());
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addAvailableBalanceControllerSubcomponent(new AvailableBalancePresenterModule(this.mBinding.vAvailableBalanceLayout)).inject(this);
        this.mBinding.vAvailableBalanceLayout.init(getActivity(), this.mPresenter, getArgs());
        return this.mBinding.getRoot();
    }

    protected int getThemeColor() {
        return ContextCompat.getColor(getApplicationContext(), R.color.white);
    }

    protected int getToolbarTextColor() {
        return R.color.primary_mystique_text_color;
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.available_balance));
    }

    protected void onShow() {
        super.onShow();
        this.mBinding.vAvailableBalanceLayout.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mBinding.vAvailableBalanceLayout.onHide();
    }
}
