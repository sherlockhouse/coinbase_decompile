package com.coinbase.android.signin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.SigninPhoneNumberControllerBinding;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.OptionalViewHider;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import java.util.Arrays;
import java.util.LinkedList;
import javax.inject.Inject;

@ControllerScope
public class SignInPhoneNumberController extends ActionBarController implements OnItemSelectedListener, SignInPhoneNumberScreen {
    SigninPhoneNumberControllerBinding mBinding;
    @Inject
    MixpanelTracking mMixpanelTracking;
    @Inject
    OptionalViewHider mOptionalViewHider;
    @Inject
    SignInPhoneNumberPresenter mPresenter;
    private ProgressDialog mProgressDialog;

    public SignInPhoneNumberController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addSignInPhoneNumberControllerSubcomponent(new SignInPhoneNumberPresenterModule(this)).inject(this);
        this.mBinding = SigninPhoneNumberControllerBinding.inflate(inflater, container, false);
        onAttachToolbar(null);
        setShouldNotHideKeyboard(true);
        this.mPresenter.onCreate();
        this.mBinding.spinnerVerifyPhoneCountry.setOnItemSelectedListener(this);
        this.mBinding.btnVerifyPhoneVerify.setOnClickListener(SignInPhoneNumberController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.etVerifyPhoneInput.setOnEditorActionListener(SignInPhoneNumberController$$Lambda$2.lambdaFactory$(this));
        this.mBinding.btnSkip.setOnClickListener(SignInPhoneNumberController$$Lambda$3.lambdaFactory$(this));
        return this.mBinding.getRoot();
    }

    static /* synthetic */ boolean lambda$onCreateView$1(SignInPhoneNumberController this_, TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId != 6) {
            return false;
        }
        Object selectedItem = this_.mBinding.spinnerVerifyPhoneCountry.getSelectedItem();
        if (selectedItem == null || !(selectedItem instanceof CountryCode)) {
            return false;
        }
        this_.mPresenter.onUserConfirm(this_.mBinding.etVerifyPhoneInput.getText().toString(), ((CountryCode) selectedItem).getAlpha2());
        return true;
    }

    protected void onShow() {
        super.onShow();
        this.mOptionalViewHider.registerViews(new LinkedList(Arrays.asList(new ImageView[]{this.mBinding.ivPhoneLogo})), new LinkedList(Arrays.asList(new TextView[]{this.mBinding.btnVerifyPhoneVerify})));
        this.mBinding.etVerifyPhoneInput.requestFocus();
        this.mPresenter.onShow(getArgs());
    }

    protected void onHide() {
        super.onHide();
        this.mOptionalViewHider.clearOptionalViews();
        if (this.mPresenter != null) {
            this.mPresenter.onHide();
        }
    }

    protected boolean onBackPressed() {
        return this.mPresenter.onBackPressed();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CountryCode country = (CountryCode) parent.getItemAtPosition(position);
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_PHONE_TAPPED_CHOOSE_COUNTRY, "country", country.getCode());
        this.mBinding.tvVerifyPhoneCountryCode.setText("+" + country.getCode());
    }

    public void populateCountryAdapter(CountryCode[] countryCodes) {
        this.mBinding.spinnerVerifyPhoneCountry.setAdapter(new ArrayAdapter(getActivity(), R.layout.spinner_option_country, countryCodes));
    }

    public void setSelectedCountry(int countryIndex) {
        this.mBinding.spinnerVerifyPhoneCountry.setSelection(countryIndex);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void showProgressDialog() {
        this.mProgressDialog = ProgressDialog.show(getActivity(), "", getApplicationContext().getString(R.string.verifying_phone_number));
    }

    public void hideProgressDialog() {
        if (this.mProgressDialog != null && getActivity() != null) {
            Utils.dismissDialog(this.mProgressDialog);
        }
    }

    public void showProgressBar() {
        this.mBinding.progress.setVisibility(0);
    }

    public void hideProgressBar() {
        this.mBinding.progress.setVisibility(8);
    }

    public void showAddPhoneNumber() {
        this.mBinding.llAddPhoneContainer.setVisibility(0);
        this.mBinding.btnVerifyPhoneVerify.setVisibility(0);
    }

    public void hideAddPhoneNumber() {
        this.mBinding.llAddPhoneContainer.setVisibility(8);
        this.mBinding.btnVerifyPhoneVerify.setVisibility(8);
    }

    public void showSkipPhoneNumber() {
        this.mBinding.vButtonDivider.setVisibility(0);
        this.mBinding.btnSkip.setVisibility(0);
    }

    public void hideSkipPhoneNumber() {
        this.mBinding.vButtonDivider.setVisibility(8);
        this.mBinding.btnSkip.setVisibility(8);
    }
}
