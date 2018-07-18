package com.coinbase.android.accounts;

import android.app.ProgressDialog;
import android.content.Context;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.R;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePrimaryAccountTask {
    private final Context context;
    private final String mAccountId;
    private final LoginManager mLoginManager;
    private ProgressDialog mProgressDialog;
    private final RefreshRequestedConnector mRefreshRequestedConnector;

    public ChangePrimaryAccountTask(Context context, String accountId, LoginManager loginManager, RefreshRequestedConnector refreshRequestedConnector) {
        this.context = context;
        this.mAccountId = accountId;
        this.mLoginManager = loginManager;
        this.mRefreshRequestedConnector = refreshRequestedConnector;
    }

    public void setPrimaryAccount() {
        this.mProgressDialog = ProgressDialog.show(this.context, "", this.context.getString(R.string.updating_account));
        this.mLoginManager.getClient().setAccountPrimary(this.mAccountId, new CallbackWithRetrofit<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response, Retrofit retrofit) {
                ChangePrimaryAccountTask.this.mRefreshRequestedConnector.get().onNext(null);
                Utils.dismissDialog(ChangePrimaryAccountTask.this.mProgressDialog);
                if (!response.isSuccessful()) {
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {
                Utils.dismissDialog(ChangePrimaryAccountTask.this.mProgressDialog);
                Utils.showMessage(ChangePrimaryAccountTask.this.context, t, 1);
                ChangePrimaryAccountTask.this.mRefreshRequestedConnector.get().onNext(null);
            }
        });
    }
}
