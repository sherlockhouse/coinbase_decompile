package com.coinbase.android.phone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.R;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.PreferenceUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.phoneNumber.Data;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumber;
import com.coinbase.v2.models.errors.ErrorBody;
import com.coinbase.v2.models.errors.Errors;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ActivityScope
public class VerifyPhoneHandler {
    Handler handler;
    private VerifyPhoneCaller mCaller;
    private String mEnteredCountryCode;
    private String mEnteredNumber;
    private String mEnteredToken;
    @Inject
    LoginManager mLoginManager;
    private Data mPhoneNumber;
    private PhoneNumbersUpdatedConnector mPhoneNumbersUpdatedConnector;
    private ProgressDialog mProgressDialog;
    BroadcastReceiver mSmsReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    try {
                        for (Object pdu : (Object[]) bundle.get("pdus")) {
                            String body = SmsMessage.createFromPdu((byte[]) pdu).getMessageBody();
                            if (body.contains("Coinbase")) {
                                Matcher matcher = Pattern.compile("\\d{5,8}").matcher(body);
                                if (matcher.find()) {
                                    String code = matcher.group();
                                    if (VerifyPhoneHandler.this.mPhoneNumber != null) {
                                        Log.d("Coinbase", "Got code from SMS: " + code);
                                        VerifyPhoneHandler.this.handler.removeCallbacks(VerifyPhoneHandler.this.runnable);
                                        VerifyPhoneHandler.this.verifyPhoneNumber(code);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        Log.e("Coinbase", "Exception in VerifyPhoneHandler BroadcastReceiver");
                        e.printStackTrace();
                    }
                }
            }
        }
    };
    Runnable runnable = new Runnable() {
        public void run() {
            Utils.dismissDialog(VerifyPhoneHandler.this.mProgressDialog);
            try {
                VerifyPhoneHandler.this.mCaller.getContext().unregisterReceiver(VerifyPhoneHandler.this.mSmsReceiver);
            } catch (Exception e) {
                Log.e("VerifyPhoneHandler", "Receiver not registered");
            }
            FragmentManager manager = VerifyPhoneHandler.this.mCaller.getCallerFragmentManager();
            if (manager == null || VerifyPhoneHandler.this.mPhoneNumber == null || !VerifyPhoneHandler.this.mCaller.isForeground()) {
                Log.d("VerifyPhoneHandler", "Dialog not shown as app not in foreground");
            } else {
                VerifyPhoneDialogFragment.newInstance(VerifyPhoneHandler.this.mPhoneNumber.getId(), VerifyPhoneHandler.this.mEnteredNumber, VerifyPhoneHandler.this.mEnteredToken, VerifyPhoneHandler.this.mEnteredCountryCode).show(manager, ApiConstants.VERIFY);
            }
        }
    };

    public interface VerifyPhoneCaller {
        Activity getActivity();

        FragmentManager getCallerFragmentManager();

        ActionBarController getCallingController();

        Context getContext();

        boolean isForeground();
    }

    public static class VerifyAddPhoneDialogController extends AddPhoneDialogController {
        private VerifyPhoneHandler mHandler;

        public VerifyAddPhoneDialogController(Bundle bundle) {
            super(bundle);
        }

        public void setVerifyPhoneHandler(VerifyPhoneHandler handler) {
            this.mHandler = handler;
        }

        protected void onUserConfirm(String input, String tfa, String countryAlpha2) {
            super.onUserConfirm(input, tfa, countryAlpha2);
            if (!TextUtils.isEmpty(input) && this.mHandler != null) {
                this.mHandler.createPhoneNumber(input, tfa, countryAlpha2);
            }
        }
    }

    public VerifyPhoneHandler(VerifyPhoneCaller caller, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector) {
        this.mCaller = caller;
        this.mPhoneNumbersUpdatedConnector = phoneNumbersUpdatedConnector;
        ((ComponentProvider) caller.getContext().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
    }

    public void addPhone() {
        if (this.mCaller.isForeground()) {
            ActionBarController callingController = this.mCaller.getCallingController();
            Bundle bundle = new Bundle();
            bundle.putBoolean(AddPhoneDialogController.IS_NEW, true);
            VerifyAddPhoneDialogController controller = new VerifyAddPhoneDialogController(callingController.appendModalArgs(bundle));
            controller.setVerifyPhoneHandler(this);
            callingController.pushDialogModalController(controller);
        }
    }

    public void createPhoneNumber(String number, String tfaToken, String countryAlpha2) {
        if (VERSION.SDK_INT <= 21) {
            this.mProgressDialog = ProgressDialog.show(this.mCaller.getContext(), "", this.mCaller.getContext().getString(R.string.verifying_phone_number));
            this.handler = new Handler();
            this.handler.postDelayed(this.runnable, 10000);
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.provider.Telephony.SMS_RECEIVED");
            this.mCaller.getContext().registerReceiver(this.mSmsReceiver, filter);
        }
        addPhoneNumber(number, tfaToken, countryAlpha2);
    }

    private void verifyPhoneNumber(String token) {
        HashMap<String, Object> params = new HashMap();
        params.put("token", token);
        this.mLoginManager.getClient().verifyPhoneNumber(params, this.mPhoneNumber.getId(), new CallbackWithRetrofit<PhoneNumber>() {
            public void onResponse(Call<PhoneNumber> call, Response<PhoneNumber> response, Retrofit retrofit) {
                Utils.dismissDialog(VerifyPhoneHandler.this.mProgressDialog);
                VerifyPhoneHandler.this.checkIfSmsWasReceived();
                if (!response.isSuccessful()) {
                    Errors errors = Utils.getErrors(response, retrofit);
                    if (errors == null) {
                        VerifyPhoneHandler.this.mPhoneNumbersUpdatedConnector.get().onNext(null);
                        return;
                    }
                    Iterator it = errors.getErrors().iterator();
                    if (it.hasNext()) {
                        VerifyPhoneHandler.this.mPhoneNumbersUpdatedConnector.get().onNext(((ErrorBody) it.next()).getMessage());
                        return;
                    }
                }
                PreferenceUtils.putPrefsBool(VerifyPhoneHandler.this.mCaller.getContext(), Constants.KEY_PHONE_VERIFIED, true);
                VerifyPhoneHandler.this.mPhoneNumbersUpdatedConnector.get().onNext(null);
                MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_PHONE_NUMBER_VERIFIED_AUTOMATICALLY, new String[0]);
            }

            public void onFailure(Call<PhoneNumber> call, Throwable t) {
                Utils.dismissDialog(VerifyPhoneHandler.this.mProgressDialog);
                VerifyPhoneHandler.this.checkIfSmsWasReceived();
                VerifyPhoneHandler.this.mPhoneNumbersUpdatedConnector.get().onNext(t.getMessage());
            }
        });
    }

    public void checkIfSmsWasReceived() {
        if (this.mSmsReceiver != null) {
            try {
                this.mCaller.getContext().unregisterReceiver(this.mSmsReceiver);
            } catch (Exception e) {
                Log.e("VerifyPhoneHandler", "Receiver not registered");
            }
        }
    }

    private void addPhoneNumber(final String phone, final String token, final String countryCode) {
        HashMap<String, Object> params = new HashMap();
        params.put("number", phone);
        params.put("country", countryCode);
        this.mEnteredNumber = phone;
        this.mEnteredToken = token;
        this.mEnteredCountryCode = countryCode;
        this.mLoginManager.getClient().createPhoneNumber(token, params, new CallbackWithRetrofit<PhoneNumber>() {
            public void onResponse(Call<PhoneNumber> call, Response<PhoneNumber> response, Retrofit retrofit) {
                if (!(response.isSuccessful() || VerifyPhoneHandler.this.mCaller == null || VerifyPhoneHandler.this.mCaller.getActivity() == null)) {
                    Utils.dismissDialog(VerifyPhoneHandler.this.mProgressDialog);
                    Errors errors = Utils.getErrors(response, retrofit);
                    if (errors == null) {
                        VerifyPhoneHandler.this.mPhoneNumbersUpdatedConnector.get().onNext(null);
                        return;
                    }
                    Iterator it = errors.getErrors().iterator();
                    if (it.hasNext()) {
                        ErrorBody errorBody = (ErrorBody) it.next();
                        if (errorBody.getId().equalsIgnoreCase(ApiConstants.TWO_FACTOR_REQUIRED_ERROR)) {
                            VerifyPhoneHandler.this.showVerifyPhoneDialogFragment(null, phone, null, countryCode);
                            return;
                        } else {
                            VerifyPhoneHandler.this.mPhoneNumbersUpdatedConnector.get().onNext(errorBody.getMessage());
                            return;
                        }
                    }
                }
                Log.d("Coinbase", "Phone number added, now waiting for SMS");
                Data number = ((PhoneNumber) response.body()).getData();
                VerifyPhoneHandler.this.mPhoneNumber = number;
                VerifyPhoneHandler.this.showVerifyPhoneDialogFragment(number.getId(), phone, token, null);
            }

            public void onFailure(Call<PhoneNumber> call, Throwable t) {
                VerifyPhoneHandler.this.mPhoneNumbersUpdatedConnector.get().onNext(t.getMessage());
            }
        });
    }

    public void showVerifyPhoneDialogFragment(String id, String phone, String token, String countryCode) {
        if (VERSION.SDK_INT > 21) {
            FragmentManager manager = this.mCaller.getCallerFragmentManager();
            if (manager == null || phone == null || !this.mCaller.isForeground()) {
                Log.d("VerifyPhoneHandler", "Dialog not shown as app not in foreground");
            } else {
                VerifyPhoneDialogFragment.newInstance(id, phone, token, countryCode).show(manager, ApiConstants.VERIFY);
            }
        }
    }
}
