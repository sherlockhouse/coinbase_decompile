package com.coinbase.android.settings.tiers;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import com.coinbase.android.databinding.ControllerInvestmentTiersRequirementsBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.LeftOffsetItemDecorator;
import javax.inject.Inject;

@ControllerScope
public class InvestmentTiersRequirementsController extends ActionBarController implements InvestmentTiersRequirementsScreen {
    ControllerInvestmentTiersRequirementsBinding mBinding;
    InvestmentTiersRequirementsAdapter mInvestmentTiersRequirementsAdapter;
    @Inject
    InvestmentTiersRequirementsPresenter mPresenter;

    private class InvestmentTiersRequirementsAdapter extends Adapter {
        private InvestmentTiersRequirementsAdapter() {
        }

        public int getItemCount() {
            return InvestmentTiersRequirementsController.this.mPresenter.getInvestmentTiersRequirementsItemCount();
        }

        public int getItemViewType(int position) {
            return InvestmentTiersRequirementsController.this.mPresenter.getInvestmentTiersRequirementsItemViewType(position);
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return InvestmentTiersRequirementsController.this.mPresenter.onCreateInvestmentTiersRequirementsViewHolder(parent, viewType);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            InvestmentTiersRequirementsController.this.mPresenter.onBindInvestmentTiersRequirementsViewHolder(holder, position);
        }
    }

    public InvestmentTiersRequirementsController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerInvestmentTiersRequirementsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_investment_tiers_requirements, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addInvestmentTiersRequirementsControllerSubcomponent(new InvestmentTiersRequirementsPresenterModule(this)).inject(this);
        this.mInvestmentTiersRequirementsAdapter = new InvestmentTiersRequirementsAdapter();
        this.mBinding.rvRequirements.setAdapter(this.mInvestmentTiersRequirementsAdapter);
        this.mBinding.rvRequirements.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 1, false));
        Context context = getApplicationContext();
        LeftOffsetItemDecorator itemDecoration = new LeftOffsetItemDecorator(context, (int) context.getResources().getDimension(R.dimen.margin_default));
        itemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.tiers_requirements_divider));
        this.mBinding.rvRequirements.addItemDecoration(itemDecoration);
        this.mBinding.btnContinue.setOnClickListener(InvestmentTiersRequirementsController$$Lambda$1.lambdaFactory$(this));
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
        this.mPresenter.onHide();
    }

    public void notifyInvestmentTiersRequirementsAdapter() {
        this.mInvestmentTiersRequirementsAdapter.notifyDataSetChanged();
    }
}
