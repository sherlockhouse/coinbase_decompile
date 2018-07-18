package com.coinbase.android.accounts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.accounts.AccountCryptoAddressButtonConnector.AccountCryptoAddressButtonEvent;
import com.coinbase.android.databinding.LayoutAccountAddressBinding;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.v2.models.account.Data;
import javax.inject.Inject;

public class AccountCryptoAddressLayout extends LinearLayout implements AccountCryptoAddressScreen {
    @Inject
    AccountCryptoAddressButtonConnector mAccountCryptoAddressButtonConnector;
    private LayoutAccountAddressBinding mBinding;
    private Context mContext;
    @Inject
    AccountCryptoAddressPresenter mPresenter;

    public AccountCryptoAddressLayout(Context context) {
        this(context, null);
    }

    public AccountCryptoAddressLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccountCryptoAddressLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutAccountAddressBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().addAccountAddressLayoutSubcomponent(new AccountCryptoAddressPresenterModule(this)).inject(this);
        this.mContext = context;
        this.mPresenter.onViewInit();
        this.mBinding.btShowAddress.setOnClickListener(AccountCryptoAddressLayout$$Lambda$1.lambdaFactory$(this));
    }

    void onDestroy() {
        this.mPresenter.onDestroy();
    }

    void showAddressView(Data selectedAccount, com.coinbase.api.internal.models.currency.Data selectedCurrency) {
        this.mBinding.ivQrCode.setImageDrawable(null);
        this.mBinding.llAddressCopied.setVisibility(8);
        this.mBinding.llAccountAddress.setVisibility(0);
        this.mPresenter.generateAccountCryptoAddress(selectedAccount, selectedCurrency);
    }

    public void setCryptoAddressTitle(String titleText) {
        this.mBinding.tvTitle.setText(titleText);
    }

    public void setCryptoAddressBitmap(Bitmap cryptoAddressBitmap) {
        if (cryptoAddressBitmap != null) {
            this.mBinding.ivQrCode.setImageBitmap(cryptoAddressBitmap);
            this.mBinding.btnCopy.setVisibility(0);
            this.mBinding.btnCopy.setOnClickListener(AccountCryptoAddressLayout$$Lambda$2.lambdaFactory$(this));
        }
    }

    public void setCryptoAddressBitmapError() {
        this.mBinding.llWarningContainer.setVisibility(8);
        this.mBinding.llQrCode.setVisibility(0);
        this.mBinding.ivQrCode.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.qr_error));
        this.mBinding.btnCopy.setVisibility(8);
    }

    public void showCopySuccessfulDialog(String copiedTitleText) {
        this.mBinding.llAccountAddress.setVisibility(8);
        this.mBinding.tvAddressCopied.setText(copiedTitleText);
        this.mBinding.llAddressCopied.setVisibility(0);
    }

    public void hideCopySuccessfulDialog() {
        this.mAccountCryptoAddressButtonConnector.get().onNext(AccountCryptoAddressButtonEvent.CLOSE);
    }

    public void showErrorMessage(String errorMessage) {
        Utils.showMessage(getContext(), errorMessage, 0);
    }

    public void showGenericErrorMessage() {
        Utils.showMessage(getContext(), (int) R.string.an_error_occurred, 1);
    }

    public void showFailureMessage(Throwable throwable) {
        Utils.showMessage(getContext(), throwable, 1);
    }

    public void showHelpTextVisible(boolean isVisible) {
        this.mBinding.btnHelp.setVisibility(isVisible ? 0 : 8);
        this.mBinding.btnHelp.setOnClickListener(AccountCryptoAddressLayout$$Lambda$3.lambdaFactory$(this));
    }

    public void showWarningScreen(com.coinbase.v2.models.address.Data address, Data selectedAccount) {
        AccountUtils.setAccountImage(this.mContext, this.mBinding.ivAccountIcon, selectedAccount);
        this.mBinding.tvWarningTitle.setText(address.getWarningTitle());
        this.mBinding.tvWarningDetails.setText(address.getWarningDetails());
        this.mBinding.llQrCode.setVisibility(8);
        this.mBinding.llWarningContainer.setVisibility(0);
    }

    public void showAddressView() {
        this.mBinding.llWarningContainer.setVisibility(8);
        this.mBinding.llQrCode.setVisibility(0);
    }

    public void displaySupportUrl(Uri supportUri) {
        if (supportUri != null) {
            this.mContext.startActivity(new Intent("android.intent.action.VIEW", supportUri));
        }
    }
}
