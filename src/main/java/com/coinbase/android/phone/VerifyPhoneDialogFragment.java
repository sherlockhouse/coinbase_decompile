package com.coinbase.android.phone;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.DialogVerifyPhoneBinding;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumber;
import java.util.HashMap;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ActivityScope
public class VerifyPhoneDialogFragment extends DialogFragment {
    public static final String COUNTRY_CODE = "country_code";
    public static final String PHONE_ID = "phone_id";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String TFA_TOKEN = "tfa_token";
    private DialogVerifyPhoneBinding mBinding;
    private String mCountryCode;
    @Inject
    LoginManager mLoginManager;
    private String mPhoneId;
    private String mPhoneNumber;
    @Inject
    PhoneNumbersUpdatedConnector mPhoneNumbersUpdatedConnector;
    private String mTfaToken;

    public static VerifyPhoneDialogFragment newInstance(String phoneId, String phoneNumber, String tfaToken, String countryCode) {
        VerifyPhoneDialogFragment f = new VerifyPhoneDialogFragment();
        Bundle args = new Bundle();
        args.putString("phone_id", phoneId);
        args.putString("phone_number", phoneNumber);
        args.putString(TFA_TOKEN, tfaToken);
        args.putString("country_code", countryCode);
        f.setArguments(args);
        return f;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ComponentProvider) getActivity().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
        this.mPhoneId = getArguments().getString("phone_id");
        this.mPhoneNumber = getArguments().getString("phone_number");
        this.mTfaToken = getArguments().getString(TFA_TOKEN);
        this.mCountryCode = getArguments().getString("country_code");
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.mBinding = (DialogVerifyPhoneBinding) DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.dialog_verify_phone, null, false);
        Builder builder = new Builder(getActivity());
        builder.setView(this.mBinding.getRoot()).setPositiveButton(R.string.confirm, null).setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                VerifyPhoneDialogFragment.this.mPhoneNumbersUpdatedConnector.get().onNext(null);
            }
        });
        return builder.create();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        this.mPhoneNumbersUpdatedConnector.get().onNext(null);
    }

    public void onStart() {
        super.onStart();
        Button okButton = ((AlertDialog) getDialog()).getButton(-1);
        okButton.setTextColor(ContextCompat.getColor(getContext(), R.color.dialog_positive_button_text_color));
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VerifyPhoneDialogFragment.this.onSubmit(VerifyPhoneDialogFragment.this.mBinding.etInput2fa.getText().toString());
            }
        });
        ((AlertDialog) getDialog()).getButton(-2).setTextColor(ContextCompat.getColor(getContext(), R.color.dialog_negative_button_text_color));
    }

    private void onSubmit(String input) {
        if (input.trim().isEmpty()) {
            Utils.showMessage(getContext(), (int) R.string.token_empty_message, 1);
        } else if (this.mPhoneId == null) {
            createPhoneNumber(input);
            dismiss();
        } else {
            verifyPhoneNumber(input);
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_PHONE_NUMBER_CODE_ENTERED, new String[0]);
            dismiss();
        }
    }

    private void createPhoneNumber(String token) {
        if (TextUtils.isEmpty(token)) {
            Utils.showMessage(getContext(), (int) R.string.token_empty_message, 0);
            return;
        }
        HashMap<String, Object> params = new HashMap();
        params.put("number", this.mPhoneNumber);
        params.put("country", this.mCountryCode);
        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", getContext().getString(R.string.verifying));
        this.mLoginManager.getClient().createPhoneNumber(token, params, new CallbackWithRetrofit<PhoneNumber>() {
            public void onResponse(Call<PhoneNumber> call, Response<PhoneNumber> response, Retrofit retrofit) {
                Utils.dismissDialog(progressDialog);
                if (response.isSuccessful()) {
                    MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_PHONE_NUMBER_VERIFIED_MANUALLY, new String[0]);
                    VerifyPhoneDialogFragment.this.mPhoneNumbersUpdatedConnector.get().onNext(Integer.valueOf(R.string.number_verified));
                    return;
                }
                VerifyPhoneDialogFragment.this.mPhoneNumbersUpdatedConnector.get().onNext(Utils.getErrorMessage(response, retrofit));
            }

            public void onFailure(Call<PhoneNumber> call, Throwable t) {
                Utils.dismissDialog(progressDialog);
                VerifyPhoneDialogFragment.this.mPhoneNumbersUpdatedConnector.get().onNext(t.getMessage());
            }
        });
    }

    private void verifyPhoneNumber(String token) {
        if (TextUtils.isEmpty(token)) {
            Utils.showMessage(getContext(), getContext().getString(R.string.token_empty_message), 0);
            return;
        }
        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", getContext().getString(R.string.verifying));
        HashMap<String, Object> params = new HashMap();
        params.put("token", token);
        this.mLoginManager.getClient().verifyPhoneNumber(params, this.mPhoneId, new CallbackWithRetrofit<PhoneNumber>() {
            public void onResponse(Call<PhoneNumber> call, Response<PhoneNumber> response, Retrofit retrofit) {
                Utils.dismissDialog(progressDialog);
                if (response.isSuccessful()) {
                    MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_PHONE_NUMBER_VERIFIED_MANUALLY, new String[0]);
                    VerifyPhoneDialogFragment.this.mPhoneNumbersUpdatedConnector.get().onNext(Integer.valueOf(R.string.number_verified));
                    return;
                }
                VerifyPhoneDialogFragment.this.mPhoneNumbersUpdatedConnector.get().onNext(Utils.getErrorMessage(response, retrofit));
            }

            public void onFailure(Call<PhoneNumber> call, Throwable t) {
                Utils.dismissDialog(progressDialog);
                VerifyPhoneDialogFragment.this.mPhoneNumbersUpdatedConnector.get().onNext(t.getMessage());
            }
        });
    }
}
