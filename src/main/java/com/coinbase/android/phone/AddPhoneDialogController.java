package com.coinbase.android.phone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.DialogAddPhoneBinding;
import com.coinbase.android.ui.DialogController;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import java.util.Scanner;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

@ControllerScope
public class AddPhoneDialogController extends DialogController implements OnItemSelectedListener {
    public static final String COUNTRY = "country";
    public static final String IS_NEW = "IS_NEW";
    public static final String NUMBER = "number";
    public static final String SHOW_TFA = "show_tfa";
    DialogAddPhoneBinding mBinding;
    private String mCountryAlpha2;
    @Inject
    MixpanelTracking mMixpanelTracking;
    private boolean mShowTfa = false;
    @Inject
    SnackBarWrapper mSnackBarWrapper;
    private String mUserPhoneNumber;

    private class CountryCode {
        String alpha2;
        String code;
        String name;

        CountryCode(String code, String name, String alpha2) {
            this.code = code;
            this.name = name;
            this.alpha2 = alpha2;
        }

        public String toString() {
            return this.name;
        }

        public String getAlpha2() {
            return this.alpha2;
        }

        public String getCode() {
            return this.code;
        }
    }

    public AddPhoneDialogController(Bundle args) {
        super(args);
    }

    protected View inflateContent(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().inject(this);
        this.mBinding = DialogAddPhoneBinding.inflate(inflater, container, true);
        if (getArgs() != null) {
            this.mUserPhoneNumber = getArgs().getString("number");
            this.mCountryAlpha2 = getArgs().getString("country");
            this.mShowTfa = getArgs().getBoolean(SHOW_TFA);
        }
        if (this.mUserPhoneNumber != null) {
            this.mBinding.etVerifyPhoneInput.setText(this.mUserPhoneNumber);
        }
        if (this.mShowTfa) {
            this.mBinding.etVerifyPhone2faInput.setVisibility(0);
        }
        CountryCode[] countryCodes = getCountryCodes();
        this.mBinding.spinnerVerifyPhoneCountry.setAdapter(new ArrayAdapter(getActivity(), R.layout.spinner_option_country, countryCodes));
        if (this.mCountryAlpha2 != null) {
            int index = 0;
            for (int i = 0; i < countryCodes.length; i++) {
                if (countryCodes[i].alpha2.equals(this.mCountryAlpha2)) {
                    index = i;
                    break;
                }
            }
            this.mBinding.spinnerVerifyPhoneCountry.setSelection(index);
        }
        this.mBinding.spinnerVerifyPhoneCountry.setOnItemSelectedListener(this);
        this.mBinding.btnVerifyPhoneSkip.setOnClickListener(AddPhoneDialogController$$Lambda$1.lambdaFactory$(this));
        this.mBinding.btnVerifyPhoneVerify.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (AddPhoneDialogController.this.mBinding.etVerifyPhoneInput.getText().length() <= 0) {
                    AddPhoneDialogController.this.mSnackBarWrapper.show((int) R.string.invalid_phone);
                    return;
                }
                AddPhoneDialogController.this.onUserConfirm(AddPhoneDialogController.this.mBinding.etVerifyPhoneInput.getText().toString(), AddPhoneDialogController.this.mShowTfa ? AddPhoneDialogController.this.mBinding.etVerifyPhone2faInput.getText().toString() : null, ((CountryCode) AddPhoneDialogController.this.mBinding.spinnerVerifyPhoneCountry.getSelectedItem()).getAlpha2());
                AddPhoneDialogController.this.dismiss();
            }
        });
        if (getArgs() != null && getArgs().getBoolean(IS_NEW)) {
            this.mBinding.btnVerifyPhoneSkip.setText(getApplicationContext().getString(R.string.cancel));
            this.mBinding.btnVerifyPhoneVerify.setText(getApplicationContext().getString(R.string.add));
        }
        return this.mBinding.getRoot();
    }

    protected void onUserConfirm(String input, String tfaToken, String countryAlpha2) {
        if (!(this.mUserPhoneNumber == null || this.mUserPhoneNumber.equals(input))) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_EDIT_PHONE_NUMBER, new String[0]);
        }
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_PHONE_VERIFICATION_BUTTON_CLICKED, new String[0]);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.mBinding.tvVerifyPhoneCountryCode.setText("+" + ((CountryCode) parent.getItemAtPosition(position)).getCode());
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private CountryCode[] getCountryCodes() {
        try {
            JSONArray array = new JSONArray(new JSONTokener(new Scanner(getResources().openRawResource(R.raw.countries)).useDelimiter("\\A").next()));
            CountryCode[] result = new CountryCode[array.length()];
            for (int i = 0; i < array.length(); i++) {
                JSONArray country = array.getJSONArray(i);
                result[i] = new CountryCode(country.getString(1), country.getString(0), country.getString(2));
            }
            return result;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
