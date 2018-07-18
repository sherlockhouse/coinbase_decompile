package com.coinbase.android.paymentmethods;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.coinbase.android.Constants;
import com.coinbase.android.FragmentScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.AddPlaidAccountControllerBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;

@FragmentScope
public class AddPlaidAccountController extends ActionBarController implements OnQueryTextListener, OnClickListener, AddPlaidAccountScreen {
    private BankAdapter mAdapter;
    AddPlaidAccountControllerBinding mBinding;
    @Inject
    AddPlaidAccountPresenter mPresenter;
    private SearchView mSearchView;

    private class BankAdapter extends Adapter {
        private BankAdapter() {
        }

        public int getItemCount() {
            return AddPlaidAccountController.this.mPresenter.getBanksCount();
        }

        public int getItemViewType(int position) {
            return AddPlaidAccountController.this.mPresenter.getBanksViewType(position);
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return AddPlaidAccountController.this.mPresenter.onCreateBanksViewHolder(parent, viewType);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            AddPlaidAccountController.this.mPresenter.onBindBanksViewHolder(holder, position);
        }
    }

    public AddPlaidAccountController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (AddPlaidAccountControllerBinding) DataBindingUtil.inflate(inflater, R.layout.add_plaid_account_controller, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addPlaidAccountControllerSubcomponent(new AddPlaidAccountPresenterModule(this)).inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        this.mAdapter = new BankAdapter();
        this.mBinding.addPlaidToAccountContent.rvBanksList.setAdapter(this.mAdapter);
        this.mBinding.addPlaidToAccountContent.rvBanksList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
        this.mBinding.addPlaidToAccountContent.btnPlaidBankOther.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), PlaidAccountLoginActivity.class);
        intent.putExtra(PlaidAccountLoginActivity.MANUAL, true);
        Bundle args = getArgs();
        if (args != null) {
            intent.putExtra(Constants.PARENT_SUCCESS_ROUTER, args.getBoolean(Constants.PARENT_SUCCESS_ROUTER, false));
        }
        startActivityForResult(intent, PaymentMethodsPresenter.INTENT_VERIFY);
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_BANK_ACCOUNT_IS_NOT_ON_THE_LIST, new String[0]);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mPresenter == null || !this.mPresenter.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void clearSearchText() {
        this.mSearchView.setQuery("", false);
        this.mSearchView.clearFocus();
    }

    public void notifyBanksDataSetChanged() {
        this.mAdapter.notifyDataSetChanged();
    }

    protected void onShowOptionsMenu(Menu menu) {
        super.onShowOptionsMenu(menu);
        getActivity().getMenuInflater().inflate(R.menu.menu_fragment_plaid_account, menu);
        this.mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        this.mSearchView.setOnQueryTextListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return this.mPresenter.onOptionsItemSelected(item);
    }

    public boolean onQueryTextChange(String newText) {
        this.mPresenter.onSearchTextChange(newText);
        return true;
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.connect_plaid_account));
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    public void popCurrentController() {
        getRouter().popCurrentController();
    }
}
