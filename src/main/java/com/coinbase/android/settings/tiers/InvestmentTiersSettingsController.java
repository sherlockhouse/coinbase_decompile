package com.coinbase.android.settings.tiers;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerInvestmentTiersSettingsBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BackgroundDimmer;
import javax.inject.Inject;

@ControllerScope
public class InvestmentTiersSettingsController extends ActionBarController implements InvestmentTiersSettingsScreen {
    @Inject
    BackgroundDimmer mBackgroundDimmer;
    ControllerInvestmentTiersSettingsBinding mBinding;
    private BuyDepositAdapter mBuyLimitsAdapter;
    @Inject
    InvestmentTiersSettingsPresenter mPresenter;
    private TiersAdapter mTiersAdapter;

    private class BuyDepositAdapter extends Adapter {
        private BuyDepositAdapter() {
        }

        public int getItemCount() {
            return InvestmentTiersSettingsController.this.mPresenter.getBuyDepositItemCount();
        }

        public int getItemViewType(int position) {
            return InvestmentTiersSettingsController.this.mPresenter.getBuyDepositItemViewType(position);
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return InvestmentTiersSettingsController.this.mPresenter.onCreateBuyDepositViewHolder(parent, viewType);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            InvestmentTiersSettingsController.this.mPresenter.onBindBuyDepositViewHolder(holder, position);
        }
    }

    private class TiersAdapter extends Adapter {
        private TiersAdapter() {
        }

        public int getItemCount() {
            return InvestmentTiersSettingsController.this.mPresenter.getTiersItemCount();
        }

        public int getItemViewType(int position) {
            return InvestmentTiersSettingsController.this.mPresenter.getTiersItemViewType(position);
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return InvestmentTiersSettingsController.this.mPresenter.onCreateTiersViewHolder(parent, viewType);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            InvestmentTiersSettingsController.this.mPresenter.onBindTiersViewHolder(holder, position);
        }
    }

    public InvestmentTiersSettingsController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerInvestmentTiersSettingsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_investment_tiers_settings, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addInvestmentTiersSettingsControllerSubcomponent(new InvestmentTiersSettingsPresenterModule(this)).inject(this);
        this.mBuyLimitsAdapter = new BuyDepositAdapter();
        this.mBinding.rvBuyDepositItems.setAdapter(this.mBuyLimitsAdapter);
        this.mBinding.rvBuyDepositItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        this.mBinding.rvBuyDepositItems.setNestedScrollingEnabled(false);
        this.mTiersAdapter = new TiersAdapter();
        this.mBinding.rvTiers.setAdapter(this.mTiersAdapter);
        this.mBinding.rvTiers.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        this.mBinding.rvTiers.setNestedScrollingEnabled(false);
        this.mBinding.btCompleteAccountSetup.setOnClickListener(InvestmentTiersSettingsController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnIncreaseYourLimits.setOnClickListener(InvestmentTiersSettingsController$$Lambda$2.lambdaFactory$(this));
        this.mBinding.btnContinue.setOnClickListener(InvestmentTiersSettingsController$$Lambda$3.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getResources().getString(R.string.investment_limits));
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow(getArgs());
    }

    protected void onHide() {
        super.onHide();
        if (this.mPresenter != null) {
            this.mPresenter.onHide();
        }
    }

    public void showLevelTitle(String levelTitle) {
        this.mBinding.tvTiersLevelTitle.setText(levelTitle);
    }

    public void showLimitsHeader() {
        this.mBinding.llLimitsContainer.setVisibility(0);
    }

    public void showCompleteAccountSetupHeader(String buttonText) {
        this.mBinding.llCompleteAccountContainer.setVisibility(0);
        this.mBinding.btCompleteAccountSetup.setText(buttonText);
    }

    public void showLimitsHeaderCallToActionButton(String buttonText) {
        this.mBinding.btnIncreaseYourLimits.setVisibility(0);
        this.mBinding.btnIncreaseYourLimits.setText(buttonText);
    }

    public void showLifetimeLimit(String name, String amount, String remaining, int progress) {
        this.mBinding.llLifetimeLimitContainer.setVisibility(0);
        this.mBinding.tvLifetimeLimitName.setText(name);
        this.mBinding.tvLifetimeLimitAmount.setText(getApplicationContext().getString(R.string.life_time_limit_amount, new Object[]{amount}));
        this.mBinding.tvLifetimeLimitRemaining.setText(getApplicationContext().getString(R.string.limit_remaining, new Object[]{remaining}));
        this.mBinding.progLifetimeLimit.setProgress(progress);
    }

    public void showLifetimeLimitDescription(String description) {
        this.mBinding.tvLifetimeLimitDescription.setText(description);
    }

    public void hideLimitsHeaderCallToActionButton() {
        this.mBinding.btnIncreaseYourLimits.setVisibility(8);
    }

    public void hideCompleteAccountSetupHeader() {
        this.mBinding.llCompleteAccountContainer.setVisibility(8);
    }

    public void hideLifetimeLimit() {
        this.mBinding.llLifetimeLimitContainer.setVisibility(8);
    }

    public void hideLimitsHeader() {
        this.mBinding.llLimitsContainer.setVisibility(8);
    }

    public void setBuyLimitsHeaderText(String text) {
        this.mBinding.tvBuyDepositHeader.setText(text);
    }

    public void notifyBuyLimitsAdapter() {
        this.mBuyLimitsAdapter.notifyDataSetChanged();
        this.mBinding.llBuyDepositContainer.setVisibility(0);
    }

    public void hideBuyDepositLimits() {
        this.mBinding.llBuyDepositContainer.setVisibility(8);
    }

    public void hideTiers() {
        this.mBinding.llTiersContainer.setVisibility(8);
    }

    public void notifyTiersAdapter() {
        this.mTiersAdapter.notifyDataSetChanged();
        this.mBinding.llTiersContainer.setVisibility(0);
    }

    public void setTiersHeader(String text) {
        this.mBinding.tvTiersHeader.setText(text);
    }

    public void showTierSuccess() {
        this.mBinding.llTiersSuccess.setVisibility(0);
        this.mBackgroundDimmer.dim(this.mBinding.llTiersSuccess, null, false, 17);
    }

    public void hideTierSuccess() {
        this.mBinding.llTiersSuccess.setVisibility(8);
        this.mBackgroundDimmer.undim(null);
    }

    public boolean isTierSuccessShown() {
        return this.mBinding.llTiersSuccess.getVisibility() == 0;
    }

    protected boolean onBackPressed() {
        if (this.mPresenter == null) {
            return super.onBackPressed();
        }
        return this.mPresenter.onBackPressed();
    }
}
