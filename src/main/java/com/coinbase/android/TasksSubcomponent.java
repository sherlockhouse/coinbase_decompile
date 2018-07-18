package com.coinbase.android;

import com.coinbase.android.accounts.CreateAccountTask;
import com.coinbase.android.paymentmethods.DeletePaymentMethodTask;
import com.coinbase.android.phone.DeletePhoneTask;
import com.coinbase.android.settings.UpdateUserTask;
import com.coinbase.android.signin.OAuthTask;
import com.coinbase.android.task.FetchAccountTask;
import com.coinbase.android.task.GetUserTask;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.transfers.RequestMoneyTask;

@ActivityScope
public interface TasksSubcomponent {
    void inject(CreateAccountTask createAccountTask);

    void inject(DeletePaymentMethodTask deletePaymentMethodTask);

    void inject(DeletePhoneTask deletePhoneTask);

    void inject(UpdateUserTask updateUserTask);

    void inject(OAuthTask oAuthTask);

    void inject(FetchAccountTask fetchAccountTask);

    void inject(GetUserTask getUserTask);

    void inject(SyncAccountsTask syncAccountsTask);

    void inject(RequestMoneyTask requestMoneyTask);
}
