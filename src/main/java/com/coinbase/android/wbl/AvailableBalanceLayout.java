package com.coinbase.android.wbl;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutAvailableBalanceBinding;
import com.coinbase.android.ui.LeftOffsetItemDecorator;

public class AvailableBalanceLayout extends FrameLayout implements AvailableBalanceScreen {
    private Bundle mArgs;
    private LayoutAvailableBalanceBinding mBinding;
    private FundsOnHoldAdapter mFundsOnHoldAdapter;
    private AvailableBalancePresenter mPresenter;

    private class FundsOnHoldAdapter extends Adapter {
        private FundsOnHoldAdapter() {
        }

        public int getItemCount() {
            return AvailableBalanceLayout.this.mPresenter.getPendingHoldsItemCount();
        }

        public int getItemViewType(int position) {
            return AvailableBalanceLayout.this.mPresenter.getPendingHoldsItemViewType(position);
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return AvailableBalanceLayout.this.mPresenter.onCreatePendingHoldsViewHolder(parent, viewType);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            AvailableBalanceLayout.this.mPresenter.onBindPendingHoldsViewHolder(holder, position);
        }
    }

    public AvailableBalanceLayout(Context context) {
        this(context, null);
    }

    public AvailableBalanceLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvailableBalanceLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context, AvailableBalancePresenter presenter, Bundle args) {
        if (presenter != null && context != null && args != null) {
            this.mArgs = args;
            this.mPresenter = presenter;
            this.mBinding = LayoutAvailableBalanceBinding.inflate(LayoutInflater.from(context), this, true);
            this.mFundsOnHoldAdapter = new FundsOnHoldAdapter();
            this.mBinding.rvFundsOnHold.setAdapter(this.mFundsOnHoldAdapter);
            this.mBinding.rvFundsOnHold.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(), 1, false));
            this.mBinding.rvFundsOnHold.setNestedScrollingEnabled(false);
            this.mBinding.rvFundsOnHold.addItemDecoration(new LeftOffsetItemDecorator(context, (int) context.getResources().getDimension(R.dimen.margin_small)));
            if (VERSION.SDK_INT >= 26) {
                this.mBinding.tvFundsOnHoldExplanation.setJustificationMode(1);
            }
        }
    }

    public void onShow() {
        if (this.mPresenter != null) {
            this.mPresenter.onShow(this.mArgs);
        }
    }

    public void onHide() {
        if (this.mPresenter != null) {
            this.mPresenter.onHide();
        }
    }

    public void setTotalAvailableBalance(String totalAvailableBalance) {
        this.mBinding.tvAvailableBalance.setText(totalAvailableBalance);
    }

    public void setTotalAvailableBalanceSum(String totalAvailableBalance) {
        this.mBinding.tvAvailableBalanceSum.setText(totalAvailableBalance);
    }

    public void setTotalPortfolioBalance(String portfolioBalance) {
        this.mBinding.tvPortfolioBalance.setText(portfolioBalance);
    }

    public void setTotalPendingFunds(String totalPendingFunds) {
        this.mBinding.tvTotalPendingFunds.setText(totalPendingFunds);
    }

    public void notifyFundsOnHold() {
        this.mBinding.rlPendingHoldsHeader.setVisibility(0);
        this.mFundsOnHoldAdapter.notifyDataSetChanged();
    }

    public void showProgress() {
        this.mBinding.nsvContainer.setVisibility(8);
        this.mBinding.progress.setVisibility(0);
    }

    public void hideProgress() {
        this.mBinding.nsvContainer.setVisibility(0);
        this.mBinding.progress.setVisibility(8);
    }
}
