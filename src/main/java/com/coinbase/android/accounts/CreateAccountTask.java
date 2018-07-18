package com.coinbase.android.accounts;

import android.app.ProgressDialog;
import android.content.Context;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Account;
import java.util.HashMap;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ActivityScope
public class CreateAccountTask {
    Context context;
    @Inject
    LoginManager loginManager;
    @Inject
    RefreshRequestedConnector mRefreshRequestedConnector;
    String name;
    ProgressDialog progressDialog;

    public CreateAccountTask(Context context, String name) {
        this.context = context;
        this.name = name;
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().tasksSubcomponent().inject(this);
    }

    public void createAccount() {
        ProgressDialog progressDialog = this.progressDialog;
        this.progressDialog = ProgressDialog.show(this.context, "", this.context.getString(R.string.creating_account));
        HashMap<String, Object> params = new HashMap();
        params.put("name", this.name);
        this.loginManager.getClient().createAccount(params, new CallbackWithRetrofit<Account>() {
            public void onResponse(Call<Account> call, Response<Account> response, Retrofit retrofit) {
                Utils.dismissDialog(CreateAccountTask.this.progressDialog);
                CreateAccountTask.this.mRefreshRequestedConnector.get().onNext(null);
                if (response.isSuccessful()) {
                    Utils.showMessage(CreateAccountTask.this.context, (int) R.string.wallet_created, 1);
                    MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_NEW_ACCOUNT_CREATED, new String[0]);
                }
            }

            public void onFailure(Call<Account> call, Throwable t) {
                Utils.dismissDialog(CreateAccountTask.this.progressDialog);
                Utils.showMessage(CreateAccountTask.this.context, t, 1);
                CreateAccountTask.this.mRefreshRequestedConnector.get().onNext(null);
            }
        });
    }
}
