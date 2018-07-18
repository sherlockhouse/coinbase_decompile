package com.coinbase.android.accounts;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutAccountRefreshListBinding;
import com.coinbase.android.ui.LeftOffsetItemDecorator;
import com.coinbase.android.utils.MoneyFormatterUtil;
import javax.inject.Inject;

public class AccountRefreshListLayout extends LinearLayout implements AccountListScreen {
    private AccountListAdapter mAccountListAdapter;
    private LayoutAccountRefreshListBinding mBinding;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;
    @Inject
    AccountListPresenter mPresenter;

    public AccountRefreshListLayout(Context context) {
        this(context, null);
    }

    public AccountRefreshListLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccountRefreshListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutAccountRefreshListBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().addAccountRefreshListLayoutSubcomponent(new AccountRefreshListPresenterModule(this)).inject(this);
        this.mAccountListAdapter = new AccountListAdapter(context, this.mPresenter, this.mMoneyFormatterUtil);
        this.mBinding.rvAccounts.setAdapter(this.mAccountListAdapter);
        this.mBinding.rvAccounts.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.mBinding.rvAccounts.addItemDecoration(new LeftOffsetItemDecorator(context, (int) context.getResources().getDimension(R.dimen.margin_default)));
        this.mBinding.vAccountsRefreshLayout.setColorSchemeResources(R.color.coinbase_blue);
        this.mBinding.vAccountsRefreshLayout.setOnRefreshListener(AccountRefreshListLayout$$Lambda$1.lambdaFactory$(this));
    }

    public void onResume() {
        this.mPresenter.onResume();
    }

    public void onDestroy() {
        this.mPresenter.onDestroy();
    }

    public void notifyAccountListAdapterChanged() {
        this.mAccountListAdapter.notifyDataSetChanged();
        if (this.mBinding.vAccountsRefreshLayout.isRefreshing()) {
            this.mBinding.vAccountsRefreshLayout.setRefreshing(false);
        }
    }
}
