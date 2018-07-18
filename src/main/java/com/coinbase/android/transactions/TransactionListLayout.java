package com.coinbase.android.transactions;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutTransactionsListBinding;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.ui.LeftOffsetItemDecorator;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.v2.models.account.Data;
import javax.inject.Inject;

@ControllerScope
public class TransactionListLayout extends LinearLayout implements TransactionListScreen {
    @Inject
    BackgroundDimmer mBackgroundDimmer;
    private LayoutTransactionsListBinding mBinding;
    private Context mContext;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;
    @Inject
    TransactionListPresenter mPresenter;
    private TransactionListAdapter mTransactionListAdapter;

    private class TransactionListInfiniteScrollListener extends OnScrollListener {
        private LinearLayoutManager mLayoutManager;

        private TransactionListInfiniteScrollListener(LinearLayoutManager layoutManager) {
            this.mLayoutManager = layoutManager;
        }

        public void onScrolled(RecyclerView recyclerView, int horizontalScrollDelta, int verticalScrollDelta) {
            if (verticalScrollDelta > 0) {
                if (this.mLayoutManager.getChildCount() + this.mLayoutManager.findFirstVisibleItemPosition() >= this.mLayoutManager.getItemCount() && TransactionListLayout.this.mPresenter.canLoadMoreTransactions()) {
                    TransactionListLayout.this.mPresenter.loadMoreTransactions();
                }
            }
        }
    }

    public TransactionListLayout(Context context) {
        this(context, null);
    }

    public TransactionListLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransactionListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutTransactionsListBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().addTransactionListLayoutSubcomponent(new TransactionListPresenterModule(this)).inject(this);
        this.mContext = context;
        this.mTransactionListAdapter = new TransactionListAdapter(this.mContext, this.mPresenter, this.mMoneyFormatterUtil);
        this.mBinding.rvTransactions.setAdapter(this.mTransactionListAdapter);
        this.mBinding.rvTransactions.setEmptyView(this.mBinding.rlEmpty);
        this.mBinding.rvTransactions.addItemDecoration(new LeftOffsetItemDecorator(context, (int) context.getResources().getDimension(R.dimen.margin_default)));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), 1, false);
        this.mBinding.rvTransactions.setLayoutManager(layoutManager);
        this.mBinding.rvTransactions.addOnScrollListener(new TransactionListInfiniteScrollListener(layoutManager));
        this.mBinding.vTransactionRefreshLayout.setColorSchemeResources(R.color.coinbase_blue);
        this.mBinding.vTransactionRefreshLayout.setOnRefreshListener(TransactionListLayout$$Lambda$1.lambdaFactory$(this));
        this.mPresenter.onInit();
    }

    public void updateWallet(Data account) {
        if (account != null) {
            updateWalletEmptyView(account);
            setSelectedAccountAndLoadTransactions(account);
        }
    }

    public void updateFiat(Data account) {
        if (account != null) {
            updateFiatEmptyView(account);
            setSelectedAccountAndLoadTransactions(account);
        }
    }

    public void updateDefault(Data account) {
        if (account != null) {
            updateEmptyView();
            setSelectedAccountAndLoadTransactions(account);
        }
    }

    private void setSelectedAccountAndLoadTransactions(Data account) {
        this.mPresenter.setSelectedAccount(account);
        this.mPresenter.loadTransactions();
    }

    public void onDestroy() {
        this.mPresenter.onDestroy();
    }

    public void startFetchingTransactions() {
        this.mBinding.vTransactionRefreshLayout.setRefreshing(true);
    }

    public void fetchingTransactionsComplete() {
        this.mBinding.vTransactionRefreshLayout.setRefreshing(false);
    }

    public void showDetailLayout(com.coinbase.v2.models.transactions.Data transaction) {
        this.mBinding.transactionDetailLayout.updateView(this.mPresenter.getSelectedAccount(), transaction);
        this.mBinding.transactionDetailLayout.setVisibility(0);
        this.mBackgroundDimmer.dim(this.mBinding.transactionDetailLayout, null, true, 17);
    }

    public void hideDetailLayout() {
        this.mBinding.transactionDetailLayout.setVisibility(8);
        this.mBackgroundDimmer.undim(null);
    }

    public boolean isDetailLayoutVisible() {
        return this.mBinding.transactionDetailLayout.getVisibility() == 0;
    }

    public void notifyDataSetChanged() {
        this.mTransactionListAdapter.notifyDataSetChanged();
    }

    void updateWalletEmptyView(Data account) {
        this.mBinding.tvEmptyMessage.setText(String.format(this.mContext.getString(R.string.empty_wallet_transactions_msg), new Object[]{AccountUtils.getDisplayableAccountName(account)}));
    }

    void updateFiatEmptyView(Data account) {
        this.mBinding.tvEmptyMessage.setText(String.format(this.mContext.getString(R.string.empty_fiat_transactions_msg), new Object[]{AccountUtils.getDisplayableAccountName(account)}));
    }

    private void updateEmptyView() {
        this.mBinding.tvEmptyMessage.setText("");
    }
}
