package com.coinbase.android.accounts;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentAccountTransactionsBinding;
import com.coinbase.android.deposits.FiatTransactionsController;
import com.coinbase.android.deposits.FiatTransactionsController.Type;
import com.coinbase.android.deposits.SepaDepositActivity;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.BottomNavigationItem;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.v2.models.account.Data;
import com.google.gson.Gson;
import javax.inject.Inject;

@ControllerScope
public class AccountTransactionsController extends ActionBarController implements AccountTransactionsScreen {
    @Inject
    BackgroundDimmer mBackgroundDimmer;
    FragmentAccountTransactionsBinding mBinding;
    @Inject
    BottomNavigationConnector mBottomNavigationConnector;
    @Inject
    MixpanelTracking mMixpanelTracking;
    @Inject
    AccountTransactionsPresenter mPresenter;
    private String mTitle = "";

    public AccountTransactionsController(Bundle bundle) {
        super(bundle);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (FragmentAccountTransactionsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_account_transactions, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addAccountDetailsControllerSubcomponent(new AccountTransactionsPresenterModule(this, this)).inject(this);
        this.mBinding.enableSendReceiveLayout.setPresenter(this.mPresenter);
        this.mPresenter.onViewCreated(getArgs());
        return this.mBinding.getRoot();
    }

    protected void onDestroyView(View view) {
        super.onDestroyView(view);
        this.mPresenter.onDestroy();
        this.mBinding.transactionsListLayout.onDestroy();
        this.mBinding.accountCryptoAddressLayout.onDestroy();
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onShow();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onHide();
    }

    protected boolean onBackPressed() {
        if (this.mPresenter == null) {
            return super.onBackPressed();
        }
        return this.mPresenter.onBackPressed();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(this.mTitle);
    }

    public void onShowOptionsMenu(Menu menu) {
        if (this.mPresenter != null) {
            getActivity().getMenuInflater().inflate(R.menu.menu_account_transactions, menu);
            this.mPresenter.onShowOptionsMenu(menu);
        }
    }

    public boolean onShownOptionsItemSelected(MenuItem item) {
        if (this.mPresenter == null) {
            return super.onShownOptionsItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.menu_my_address:
                this.mPresenter.onShowCryptoAddressClicked();
                return true;
            case R.id.menu_send:
                this.mPresenter.onShowSendClicked();
                return true;
            default:
                return super.onShownOptionsItemSelected(item);
        }
    }

    public void showAccountLabels(String title, String balance, String nativeBalance) {
        this.mTitle = title;
        this.mBinding.tvAccountBalance.setText(balance);
        this.mBinding.tvAccountNativeBalance.setText(nativeBalance);
    }

    public void updateTransactionWalletList(Data account) {
        this.mBinding.transactionsListLayout.updateWallet(account);
    }

    public void updateTransactionFiatList(Data account) {
        this.mBinding.transactionsListLayout.updateFiat(account);
    }

    public void updateTransactionDefaultList(Data account) {
        this.mBinding.transactionsListLayout.updateDefault(account);
    }

    public void showSepaDeposits() {
        Intent intent = new Intent(getActivity(), SepaDepositActivity.class);
        intent.putExtra(SepaDepositActivity.FROM_DEPOSIT, true);
        startActivity(intent);
    }

    public void showFiatDepositWithdraw(Data account, Type type) {
        Bundle args = new Bundle();
        args.putString(FiatTransactionsController.ACCOUNT, new Gson().toJson((Object) account));
        args.putSerializable(FiatTransactionsController.TYPE, type);
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(BottomNavigationItem.Type.ACCOUNTS).setBottomNavigationItem(BottomNavigationItem.Type.ACCOUNTS).setPushPageController(new FiatTransactionsController(appendArgs(args))).build());
    }

    public void showDepositOption(boolean isEnabled) {
        showButtonLayout();
        this.mBinding.btLeft.setEnabled(isEnabled);
        this.mBinding.btLeft.setText(getApplicationContext().getString(R.string.deposit));
        this.mBinding.btLeft.setOnClickListener(AccountTransactionsController$$Lambda$1.lambdaFactory$(this));
    }

    public void showWithdrawOption(boolean isEnabled) {
        showButtonLayout();
        this.mBinding.btRight.setEnabled(isEnabled);
        this.mBinding.btRight.setText(getApplicationContext().getString(R.string.withdraw));
        this.mBinding.btRight.setOnClickListener(AccountTransactionsController$$Lambda$2.lambdaFactory$(this));
    }

    public void showBuyOption() {
        showButtonLayout();
        this.mBinding.btLeft.setEnabled(true);
        this.mBinding.btLeft.setText(getApplicationContext().getString(R.string.buy));
        this.mBinding.btLeft.setOnClickListener(AccountTransactionsController$$Lambda$3.lambdaFactory$(this));
    }

    public void showSellOption() {
        showButtonLayout();
        this.mBinding.btRight.setEnabled(true);
        this.mBinding.btRight.setText(getApplicationContext().getString(R.string.sell));
        this.mBinding.btRight.setOnClickListener(AccountTransactionsController$$Lambda$4.lambdaFactory$(this));
    }

    public void hideButtonLayout() {
        this.mBinding.llButton.setVisibility(8);
    }

    public void hideAccountAddressLayout() {
        this.mBinding.accountCryptoAddressLayout.setVisibility(8);
        this.mBackgroundDimmer.undim(null);
    }

    public void showSendMenu(Menu menu, boolean isVisible) {
        MenuItem sendItem = menu.findItem(R.id.menu_send);
        if (sendItem != null) {
            sendItem.setVisible(isVisible);
        }
    }

    public void showAddressMenu(Menu menu, boolean isVisible) {
        MenuItem addressItem = menu.findItem(R.id.menu_my_address);
        if (addressItem != null) {
            addressItem.setVisible(isVisible);
        }
    }

    public boolean isAccountAddressLayoutVisible() {
        return this.mBinding.accountCryptoAddressLayout.getVisibility() == 0;
    }

    public boolean isDetailLayoutVisible() {
        return this.mBinding.transactionsListLayout.isDetailLayoutVisible();
    }

    public void hideDetailLayout() {
        this.mBinding.transactionsListLayout.hideDetailLayout();
    }

    private void showButtonLayout() {
        this.mBinding.llButton.setVisibility(0);
        this.mBinding.btLeft.setVisibility(0);
        this.mBinding.btRight.setVisibility(0);
    }

    public void showAccountAddressLayout() {
        this.mBinding.accountCryptoAddressLayout.showAddressView(this.mPresenter.getSelectedAccount(), this.mPresenter.getSelectedCurrency());
        this.mBinding.accountCryptoAddressLayout.setVisibility(0);
        if (isShowingEnableSendReceive()) {
            this.mBackgroundDimmer.transition(this.mBinding.accountCryptoAddressLayout);
        } else {
            this.mBackgroundDimmer.dim(this.mBinding.accountCryptoAddressLayout, null, true, 17);
        }
        Data selectedAccount = this.mPresenter.getSelectedAccount();
        if (selectedAccount != null && selectedAccount.getCurrency() != null && selectedAccount.getCurrency().getCode() != null) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_RECEIVE_VIEWED, "currency", selectedAccount.getCurrency().getCode());
        }
    }

    public void showEnableSendReceive(String title, String message, String button, int icon) {
        this.mBinding.enableSendReceiveLayout.showEnableView(title, message, button, icon);
        boolean isShowingEnableSendReceive = isShowingEnableSendReceive();
        this.mBinding.enableSendReceiveLayout.setVisibility(0);
        if (!isShowingEnableSendReceive) {
            this.mBackgroundDimmer.dim(this.mBinding.enableSendReceiveLayout, null, true, 17);
        }
    }

    public boolean isShowingEnableSendReceive() {
        return this.mBinding.enableSendReceiveLayout.getVisibility() == 0;
    }

    public void hideEnableSendReceive(boolean transitioningToAnotherScreen) {
        this.mBinding.enableSendReceiveLayout.setVisibility(8);
        this.mBackgroundDimmer.undim(null, transitioningToAnotherScreen);
    }

    public void showEnableSendReceiveProgress(boolean send) {
        this.mBinding.enableSendReceiveLayout.showProgressView(send);
        boolean isShowingEnableSendReceive = isShowingEnableSendReceive();
        this.mBinding.enableSendReceiveLayout.setVisibility(0);
        if (!isShowingEnableSendReceive) {
            this.mBackgroundDimmer.dim(this.mBinding.enableSendReceiveLayout, null, true, 17);
        }
    }
}
