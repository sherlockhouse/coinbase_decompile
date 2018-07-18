package com.coinbase.android.accounts;

import android.app.ProgressDialog;
import android.os.Bundle;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.dialog.InputTextDialogFragment;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Account;
import java.util.HashMap;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RenameAccountFragment extends InputTextDialogFragment {
    public static final String ACCOUNT_ID = "RenameAccountFragment_AccountId";
    @Inject
    LoginManager loginManager;
    private String mAccountId;
    @Inject
    RefreshRequestedConnector mRefreshRequestedConnector;

    public static RenameAccountFragment newInstance(String accountId) {
        RenameAccountFragment f = new RenameAccountFragment();
        Bundle args = new Bundle();
        args.putString(ACCOUNT_ID, accountId);
        f.setArguments(args);
        return f;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ComponentProvider) getActivity().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
        if (getArguments() != null) {
            this.mAccountId = getArguments().getString(ACCOUNT_ID);
        }
        if (this.mAccountId == null) {
            throw new RuntimeException("RenameAccount needs account id");
        }
    }

    public String getTitle() {
        return getString(R.string.rename_account);
    }

    public String getLabel() {
        return getString(R.string.new_name);
    }

    public void onSubmit(String enteredValue) {
        renameAccount(enteredValue);
    }

    public void renameAccount(String name) {
        final ProgressDialog progressDialog = ProgressDialog.show(getContext(), "", getString(R.string.updating_account));
        HashMap<String, Object> params = new HashMap();
        params.put("name", name);
        this.loginManager.getClient().updateAccount(this.mAccountId, params, new CallbackWithRetrofit<Account>() {
            public void onResponse(Call<Account> call, Response<Account> response, Retrofit retrofit) {
                Utils.dismissDialog(progressDialog);
                RenameAccountFragment.this.mRefreshRequestedConnector.get().onNext(null);
            }

            public void onFailure(Call<Account> call, Throwable t) {
                Utils.dismissDialog(progressDialog);
                RenameAccountFragment.this.mRefreshRequestedConnector.get().onNext(null);
            }
        });
    }
}
