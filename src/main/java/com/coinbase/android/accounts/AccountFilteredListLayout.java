package com.coinbase.android.accounts;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutAccountListBinding;
import com.coinbase.android.ui.LeftOffsetItemDecorator;
import com.coinbase.android.utils.MoneyFormatterUtil;
import javax.inject.Inject;

public class AccountFilteredListLayout extends LinearLayout implements AccountListScreen {
    private AccountListAdapter mAccountListAdapter;
    private LayoutAccountListBinding mBinding;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;
    @Inject
    AccountFilteredListPresenter mPresenter;

    public AccountFilteredListLayout(Context context) {
        this(context, null);
    }

    public AccountFilteredListLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccountFilteredListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutAccountListBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().addAccountListLayoutSubcomponent(new AccountFilteredListPresenterModule(this)).inject(this);
        this.mAccountListAdapter = new AccountListAdapter(context, this.mPresenter, this.mMoneyFormatterUtil);
        this.mBinding.rvAccounts.setAdapter(this.mAccountListAdapter);
        this.mBinding.rvAccounts.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.mBinding.rvAccounts.addItemDecoration(new LeftOffsetItemDecorator(context, (int) context.getResources().getDimension(R.dimen.margin_default)));
    }

    public void onResume() {
        this.mPresenter.onResume();
    }

    public void onDestroy() {
        this.mPresenter.onDestroy();
    }

    public void setCurrencyCode(String currencyCode) {
        this.mPresenter.setCurrencyCode(currencyCode);
    }

    public void notifyAccountListAdapterChanged() {
        this.mAccountListAdapter.notifyDataSetChanged();
    }
}
