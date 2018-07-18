package com.coinbase.android.transfers;

import android.app.ProgressDialog;
import android.content.Context;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.event.TransferMadeEvent;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.TransactionUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.transactions.Transaction;
import java.util.HashMap;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ActivityScope
public class RequestMoneyTask {
    Context context;
    ProgressDialog dialog;
    @Inject
    LoginManager mLoginManager;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;
    @Inject
    TransferMadeConnector mTransferMadeConnector;

    public RequestMoneyTask(Context context) {
        this.context = context;
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().tasksSubcomponent().inject(this);
    }

    public void requestMoney(String accountId, final HashMap<String, Object> params, final Runnable callback) {
        this.dialog = ProgressDialog.show(this.context, "", this.context.getString(R.string.transfer_request_progress));
        this.mLoginManager.getClient().requestMoney(accountId, params, new CallbackWithRetrofit<Transaction>() {
            public void onResponse(Call<Transaction> call, Response<Transaction> response, Retrofit retrofit) {
                if (response.isSuccessful()) {
                    String text;
                    try {
                        String amountValue = RequestMoneyTask.this.mMoneyFormatterUtil.formatMoney(TransactionUtils.moneyFromAmount(((Transaction) response.body()).getData().getAmount()).abs());
                        text = String.format(RequestMoneyTask.this.context.getString(R.string.transfer_success_request), new Object[]{amountValue, params.get("to")});
                    } catch (Exception e) {
                        text = "error";
                    }
                    Utils.showMessage(RequestMoneyTask.this.context, text, 0);
                    Utils.dismissDialog(RequestMoneyTask.this.dialog);
                    RequestMoneyTask.this.mTransferMadeConnector.get().onNext(new TransferMadeEvent(((Transaction) response.body()).getData(), (Transaction) response.body()));
                    if (callback != null) {
                        callback.run();
                        return;
                    }
                    return;
                }
                RequestMoneyTask.this.handleRequestError(Utils.getErrorMessage(response, retrofit));
            }

            public void onFailure(Call<Transaction> call, Throwable t) {
                RequestMoneyTask.this.handleRequestError(null);
            }
        });
    }

    private void handleRequestError(String message) {
        if (message == null) {
            Utils.showMessage(this.context, (int) R.string.an_error_occurred, 0);
        } else {
            Utils.showMessage(this.context, message, 0);
        }
        Utils.dismissDialog(this.dialog);
    }
}
