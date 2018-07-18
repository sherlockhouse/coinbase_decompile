package com.coinbase.android.phone;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.phoneNumber.PhoneNumber;
import com.coinbase.v2.models.errors.ErrorBody;
import com.coinbase.v2.models.errors.Errors;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ActivityScope
public class DeletePhoneTask {
    private boolean isFromSetting;
    private boolean isVerified;
    AppCompatActivity mActivity;
    Context mContext;
    private String mId;
    @Inject
    LoginManager mLoginManager;
    @Inject
    PhoneNumbersUpdatedConnector mPhoneNumbersUpdatedConnector;
    ProgressDialog mProgressDialog;
    private String mTfaToken;

    public DeletePhoneTask(AppCompatActivity activity, Context context, String id, String tfaToken, boolean isVerified, boolean isFromSetting) {
        this.mActivity = activity;
        this.mContext = context;
        this.mId = id;
        this.mTfaToken = tfaToken;
        this.isVerified = isVerified;
        this.isFromSetting = isFromSetting;
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().tasksSubcomponent().inject(this);
    }

    public void deletePhoneNumber() {
        this.mProgressDialog = ProgressDialog.show(this.mActivity, "", this.mContext.getString(R.string.deleting));
        this.mLoginManager.getClient().deletePhoneNumber(this.mTfaToken, this.mId, new CallbackWithRetrofit<PhoneNumber>() {
            public void onResponse(Call<PhoneNumber> call, Response<PhoneNumber> response, Retrofit retrofit) {
                Utils.dismissDialog(DeletePhoneTask.this.mProgressDialog);
                if (!response.isSuccessful()) {
                    Errors errors = Utils.getErrors(response, retrofit);
                    if (errors == null) {
                        DeletePhoneTask.this.mPhoneNumbersUpdatedConnector.get().onNext(null);
                        return;
                    }
                    boolean tfaRequired = false;
                    for (ErrorBody errorBody : errors.getErrors()) {
                        if (errorBody.getId().equalsIgnoreCase(ApiConstants.TWO_FACTOR_REQUIRED_ERROR)) {
                            if (!DeletePhoneTask.this.isFromSetting) {
                                Utils.showMessage(DeletePhoneTask.this.mContext, (int) R.string.token_required, 1);
                            }
                            DeletePhoneNumberFragment.newInstance(DeletePhoneTask.this.mId, DeletePhoneTask.this.isVerified).show(DeletePhoneTask.this.mActivity.getSupportFragmentManager(), "delete");
                            tfaRequired = true;
                            if (tfaRequired) {
                                return;
                            }
                        }
                    }
                    if (tfaRequired) {
                        return;
                    }
                }
                DeletePhoneTask.this.mPhoneNumbersUpdatedConnector.get().onNext(null);
            }

            public void onFailure(Call<PhoneNumber> call, Throwable t) {
                Utils.dismissDialog(DeletePhoneTask.this.mProgressDialog);
                DeletePhoneTask.this.mPhoneNumbersUpdatedConnector.get().onNext(t.getMessage());
            }
        });
    }
}
