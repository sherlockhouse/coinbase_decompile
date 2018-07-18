package com.coinbase.android.paymentmethods.card;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.billing.BillingAddressListAdapter;
import com.coinbase.android.databinding.CardFormControllerBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.billingAddress.Data;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class CardFormController extends ActionBarController implements CardFormScreen {
    @Inject
    LoginManager loginManager;
    @Inject
    BackgroundDimmer mBackgroundDimmer;
    CardFormControllerBinding mBinding;
    private TextWatcher mCardNumberTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            CardFormController.this.mPresenter.onCardNumberTextUpdated(editable);
        }
    };
    private TextWatcher mCvvTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            CardFormController.this.mPresenter.onCvvTextUpdated(editable);
        }
    };
    private TextWatcher mExpireTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            CardFormController.this.mBinding.etExpiry.removeTextChangedListener(CardFormController.this.mExpireTextWatcher);
            CardFormController.this.mPresenter.onExpiryTextUpdated(editable);
            CardFormController.this.mBinding.etExpiry.addTextChangedListener(CardFormController.this.mExpireTextWatcher);
        }
    };
    private OnFocusChangeListener mGenericFocusChangeListener = CardFormController$$Lambda$1.lambdaFactory$(this);
    @Inject
    CardFormPresenter mPresenter;
    ProgressDialog progressDialog;

    static /* synthetic */ void lambda$new$0(CardFormController this_, View v, boolean f) {
        if (!f) {
            this_.mPresenter.onFormUpdated();
        }
    }

    public CardFormController(Bundle args) {
        super(args);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mPresenter != null) {
            this.mPresenter.onDestroy();
        }
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (CardFormControllerBinding) DataBindingUtil.inflate(inflater, R.layout.card_form_controller, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().cardFormControllerSubcomponent(new CardFormPresenterModule(this, this)).inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        this.mBinding.setPresenter(this.mPresenter);
        this.mPresenter.onViewCreated(getArgs());
        this.mBinding.etZip.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                CardFormController.this.mPresenter.onLastFieldEditorAction(actionId);
                return false;
            }
        });
        this.mBinding.ivCardNumberRight.setOnClickListener(CardFormController$$Lambda$2.lambdaFactory$(this));
        this.mBinding.ivExpiryRight.setOnClickListener(CardFormController$$Lambda$3.lambdaFactory$(this));
        this.mBinding.ivCvvRight.setOnClickListener(CardFormController$$Lambda$4.lambdaFactory$(this));
        this.mBinding.etCardNumber.setOnFocusChangeListener(this.mGenericFocusChangeListener);
        this.mBinding.etFullName.setOnFocusChangeListener(this.mGenericFocusChangeListener);
        this.mBinding.etExpiry.setOnFocusChangeListener(this.mGenericFocusChangeListener);
        this.mBinding.etCvv.setOnFocusChangeListener(this.mGenericFocusChangeListener);
        this.mBinding.etZip.setOnFocusChangeListener(this.mGenericFocusChangeListener);
        this.mBinding.etExpiry.addTextChangedListener(this.mExpireTextWatcher);
        this.mBinding.etCardNumber.addTextChangedListener(this.mCardNumberTextWatcher);
        this.mBinding.etCvv.addTextChangedListener(this.mCvvTextWatcher);
        this.mBinding.cardFormAllDone.btnPlaidAllDoneSubmit.setOnClickListener(CardFormController$$Lambda$5.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mPresenter.onResume();
    }

    protected void onHide() {
        super.onHide();
        this.mPresenter.onPause();
    }

    public void popToRoot() {
        getRouter().popToRoot();
    }

    public void hideKeyboard() {
        Utils.hideKeyboard(getActivity());
    }

    public void showContent() {
        this.mBinding.flContainer.setVisibility(0);
    }

    public void hideContent() {
        this.mBinding.flContainer.setVisibility(4);
    }

    public void showRequiresBillingAddressView() {
        this.mBinding.llBottomFields.setWeightSum(2.0f);
        this.mBinding.cvZipContainer.setVisibility(8);
        this.mBinding.cvBillingContainer.setVisibility(0);
    }

    public void showDoesntRequireBillingAddressView() {
        this.mBinding.llBottomFields.setWeightSum(3.0f);
        this.mBinding.cvZipContainer.setVisibility(0);
        this.mBinding.cvBillingContainer.setVisibility(8);
    }

    public void showNonUsZipCode() {
        String hintText = getApplicationContext().getString(R.string.postal_code);
        this.mBinding.cvZipContainer.setHint(hintText);
        this.mBinding.etZip.setHint(hintText);
        this.mBinding.etZip.setInputType(1);
    }

    public void setFullName(String fullName) {
        this.mPresenter.setFullName(fullName);
    }

    public void showAddingCardProgress() {
        this.progressDialog = ProgressDialog.show(getActivity(), null, getApplicationContext().getString(R.string.adding_card));
    }

    public void hideProgress() {
        Utils.dismissDialog(this.progressDialog);
    }

    public void switchToAllDoneView(String allDoneText) {
        this.mBinding.rlForm.setVisibility(8);
        this.mBinding.cardFormAllDone.tvPlaidAllDoneDetails.setText(allDoneText);
        this.mBinding.cardFormAllDone.llPlaidAllDone.setVisibility(0);
    }

    public void setFinishButtonText(String buttonText) {
        this.mBinding.cardFormAllDone.btnPlaidAllDoneSubmit.setText(buttonText);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.mPresenter != null) {
            this.mPresenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void closeForm() {
        getRouter().handleBack();
    }

    public void setExpiry(String expiry) {
        this.mBinding.etExpiry.setText(expiry);
        this.mBinding.etExpiry.setSelection(expiry.length());
    }

    public String getExpiry() {
        return this.mBinding.etExpiry.getText().toString();
    }

    public void clearCardNumberError() {
        this.mBinding.ivCardNumberRight.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_help));
    }

    public void showCardNumberValid() {
        this.mBinding.ivCardNumberRight.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.check_mark));
    }

    public void showCardNumberError() {
        this.mBinding.ivCardNumberRight.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_input_error));
    }

    public void clearFullNameValid() {
        this.mBinding.ivFullNameRight.setVisibility(4);
    }

    public void showFullNameValid() {
        this.mBinding.ivFullNameRight.setVisibility(0);
        this.mBinding.ivFullNameRight.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.check_mark));
    }

    public void showFullNameError() {
        this.mBinding.ivFullNameRight.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_input_error));
    }

    public void clearCvvError() {
        this.mBinding.ivCvvRight.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_help));
    }

    public void showCvvValid() {
        this.mBinding.ivCvvRight.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.check_mark));
    }

    public void showCvvError() {
        this.mBinding.ivCvvRight.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_input_error));
    }

    public void clearExpiryError() {
        this.mBinding.ivExpiryRight.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_help));
    }

    public void showExpiryValid() {
        this.mBinding.ivExpiryRight.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.check_mark));
    }

    public void showExpiryError() {
        this.mBinding.ivExpiryRight.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_input_error));
    }

    public void showExpiryHelpBottomSheet() {
        this.mBackgroundDimmer.dim(this.mBinding.llCardExpiryHelp, null, true, 17);
    }

    public void hideBottomSheet() {
        this.mBackgroundDimmer.undim(null);
    }

    public void showCvvHelpBottomSheet() {
        this.mBackgroundDimmer.dim(this.mBinding.llCardCvvHelp, null, true, 17);
    }

    public void showCardNumberHelpBottomSheet() {
        this.mBackgroundDimmer.dim(this.mBinding.llCardNumberHelp, null, true, 17);
    }

    public void setContinueButtonDisabled() {
        this.mBinding.btnContinue.setEnabled(false);
    }

    public void setContinueButtonEnabled() {
        this.mBinding.btnContinue.setEnabled(true);
    }

    public void showProgress() {
        this.progressDialog = ProgressDialog.show(getActivity(), null, getApplicationContext().getString(R.string.loading));
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.card));
    }

    public void showBillingAddressProgressBar() {
        this.mBinding.billingAddressListSelectorLayout.showProgressBar();
    }

    public void hideBillingAddressProgressBar() {
        this.mBinding.billingAddressListSelectorLayout.hideProgressBar();
    }

    public void notifyBillingAddressAdapterDataSetChanged() {
        this.mBinding.billingAddressListSelectorLayout.notifyAdapterDataSetChanged();
    }

    public void initializeBillingAddress(List<Data> billingAddresses) {
        this.mBinding.billingAddressListSelectorLayout.initializeAdapter(new BillingAddressListAdapter(getActivity(), billingAddresses));
    }

    public void showBillingAddressLayout() {
        hideKeyboard();
        this.mBinding.billingAddressListSelectorLayout.setVisibility(0);
        this.mBackgroundDimmer.dim(this.mBinding.billingAddressListSelectorLayout, null, true, 17);
    }

    public void hideBillingAddressLayout() {
        this.mBinding.billingAddressListSelectorLayout.setVisibility(8);
        this.mBackgroundDimmer.undim(null);
    }

    public FragmentManager getFragmentManager() {
        return ((AppCompatActivity) getActivity()).getSupportFragmentManager();
    }

    public void showWorldPayInfo() {
        this.mBinding.rlWorldPay.setVisibility(0);
    }

    public void hideWorldPayInfo() {
        this.mBinding.rlWorldPay.setVisibility(8);
    }

    public void showContinueProgress() {
        this.mBinding.btnContinue.setEnabled(false);
        this.mBinding.btnContinue.setText("");
        this.mBinding.btnProgress.setVisibility(0);
    }

    public void hideContinueProgress() {
        this.mBinding.btnContinue.setEnabled(true);
        this.mBinding.btnContinue.setText(R.string.continue_button);
        this.mBinding.btnProgress.setVisibility(8);
    }
}
