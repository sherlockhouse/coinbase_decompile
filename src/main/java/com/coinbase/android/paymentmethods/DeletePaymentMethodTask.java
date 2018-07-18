package com.coinbase.android.paymentmethods;

import android.app.ProgressDialog;
import android.content.Context;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ActivityScope
public class DeletePaymentMethodTask {
    Context context;
    private DeletePaymentMethodListener listener;
    @Inject
    LoginManager loginManager;
    @Inject
    PaymentMethodsUpdatedConnector mConnector;
    String paymentMethodId;
    ProgressDialog progressDialog;

    public interface DeletePaymentMethodListener {
        void onFinally();
    }

    public static class DeletePaymentMethodsTaskWrapper {
        public DeletePaymentMethodTask get(Context context, String id, DeletePaymentMethodListener listener) {
            return new DeletePaymentMethodTask(context, id, listener);
        }
    }

    public DeletePaymentMethodTask(Context context, String id, DeletePaymentMethodListener listener) {
        this.context = context;
        this.paymentMethodId = id;
        this.listener = listener;
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().tasksSubcomponent().inject(this);
    }

    public void deletePaymentMethod() {
        this.progressDialog = ProgressDialog.show(this.context, "", this.context.getString(R.string.deleting));
        this.loginManager.getClient().deletePaymentmethod(this.paymentMethodId, new CallbackWithRetrofit<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response, Retrofit retrofit) {
                new SyncAccountsTask(DeletePaymentMethodTask.this.context, null).syncAccounts();
                Utils.dismissDialog(DeletePaymentMethodTask.this.progressDialog);
                if (response.isSuccessful()) {
                    DeletePaymentMethodTask.this.mConnector.get().onNext(new ClassConsumableEvent());
                    if (DeletePaymentMethodTask.this.listener != null) {
                        DeletePaymentMethodTask.this.listener.onFinally();
                        return;
                    }
                    return;
                }
                Utils.showRetrofitErrorMessage(response, retrofit, DeletePaymentMethodTask.this.context);
            }

            public void onFailure(Call<Void> call, Throwable t) {
                Utils.dismissDialog(DeletePaymentMethodTask.this.progressDialog);
                Utils.showMessage(DeletePaymentMethodTask.this.context, t, 1);
            }
        });
    }
}
