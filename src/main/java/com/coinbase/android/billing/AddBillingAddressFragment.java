package com.coinbase.android.billing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.coinbase.android.CoinbaseActivityMystiqueSubcomponentProvider;
import com.coinbase.android.FragmentScope;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentAddBillingAddressBinding;
import com.coinbase.android.signin.StateListDialogFragment;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import javax.inject.Inject;

@FragmentScope
public class AddBillingAddressFragment extends Fragment implements AddBillingAddressScreen {
    @Inject
    LoginManager loginManager;
    FragmentAddBillingAddressBinding mBinding;
    private ProgressDialog mDialog;
    private TextWatcher mEditTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            AddBillingAddressFragment.this.mPresenter.onFormUpdated();
        }
    };
    @Inject
    AddBillingAddressPresenter mPresenter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentAddBillingAddressBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_add_billing_address, container, false);
        ((CoinbaseActivityMystiqueSubcomponentProvider) getActivity()).coinbaseActivityMystiqueSubcomponent().addBillingAddressFragmentSubcomponent(new AddBillingAddressPresenterModule(this)).inject(this);
        this.mBinding.setPresenter(this.mPresenter);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mPresenter.onViewCreated();
        this.mBinding.etZip.setOnEditorActionListener(AddBillingAddressFragment$$Lambda$1.lambdaFactory$(this));
        this.mBinding.etCity.setOnEditorActionListener(AddBillingAddressFragment$$Lambda$2.lambdaFactory$(this));
        this.mBinding.etAddressLine1.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etCity.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etState.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etZip.addTextChangedListener(this.mEditTextWatcher);
        this.mBinding.etCountry.addTextChangedListener(this.mEditTextWatcher);
    }

    static /* synthetic */ boolean lambda$onViewCreated$0(AddBillingAddressFragment this_, TextView v, int actionId, KeyEvent event) {
        if (actionId == 6) {
            Utils.hideKeyboard(this_.getActivity());
        }
        return false;
    }

    static /* synthetic */ boolean lambda$onViewCreated$1(AddBillingAddressFragment this_, TextView v, int actionId, KeyEvent event) {
        if (actionId == 5) {
            this_.mPresenter.onStateClicked();
        }
        return false;
    }

    public void onResume() {
        super.onResume();
        this.mPresenter.onResume();
    }

    public void onPause() {
        super.onPause();
        this.mPresenter.onPause();
    }

    public void setResult(int result, Intent intent) {
        getActivity().setResult(result, intent);
    }

    public void finish() {
        getActivity().finish();
    }

    public void setAddAddressButtonDisabled() {
        this.mBinding.btnContinue.setEnabled(false);
    }

    public void setAddAddressButtonEnabled() {
        this.mBinding.btnContinue.setEnabled(true);
    }

    public void displayStateList() {
        new StateListDialogFragment().show(getActivity().getSupportFragmentManager(), "change_state");
    }

    public void setStateChosen(String abbreviation) {
        this.mBinding.etState.setText(abbreviation);
        this.mBinding.etZip.requestFocus();
    }

    public void showNonUSPostalFields() {
        this.mBinding.tlState.setHint(getString(R.string.province));
        this.mBinding.etState.setFilters(new InputFilter[0]);
        this.mBinding.etState.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        this.mBinding.etState.setFocusableInTouchMode(true);
        this.mBinding.etState.setFocusable(true);
        this.mBinding.tlZip.setHint(getString(R.string.postal_code));
        this.mBinding.etZip.setInputType(1);
    }

    public void showProgressDialog() {
        this.mDialog = ProgressDialog.show(getActivity(), "", getString(R.string.please_wait));
    }

    public void dismissProgressDialog() {
        Utils.dismissDialog(this.mDialog);
    }
}
