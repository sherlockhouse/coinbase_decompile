package com.coinbase.android.settings;

import com.coinbase.android.task.GetUserTask;
import com.coinbase.android.task.GetUserTask.GetUserListener;
import com.coinbase.v2.models.user.Data;
import com.coinbase.v2.models.user.User;
import retrofit2.Response;
import rx.functions.Func0;

final /* synthetic */ class AccountSettingsPresenter$$Lambda$1 implements Func0 {
    private final AccountSettingsPresenter arg$1;

    private AccountSettingsPresenter$$Lambda$1(AccountSettingsPresenter accountSettingsPresenter) {
        this.arg$1 = accountSettingsPresenter;
    }

    public static Func0 lambdaFactory$(AccountSettingsPresenter accountSettingsPresenter) {
        return new AccountSettingsPresenter$$Lambda$1(accountSettingsPresenter);
    }

    public Object call() {
        return new GetUserTask(this.arg$1.mContext, new GetUserListener() {
            public void onPreExecute() {
            }

            public void onException(boolean unauthorized) {
            }

            public void onFinally(Response<User> response) {
                Data user = ((User) response.body()).getData();
                if (user != null) {
                    AccountSettingsPresenter.this.cacheUserResponseInBundle(user);
                    AccountSettingsPresenter.this.handleGetUserResponse(user);
                }
            }
        });
    }
}
