package com.coinbase.android.settings;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.coinbase.ApiConstants;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.user.Data;
import com.coinbase.v2.models.user.User;
import java.util.HashMap;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ActivityScope
public class UpdateUserTask {
    Context context;
    private UpdateUserListener listener;
    @Inject
    LoginManager loginManager;
    @Inject
    LocalUserDataUpdatedConnector mLocalUserDataUpdatedConnector;
    @Inject
    UserUpdatedConnector mUserUpdatedConnector;

    public interface UpdateUserListener {
        void onException();

        void onFinally();

        void onPreExecute();
    }

    public UpdateUserTask(Context context, UpdateUserListener listener) {
        this.context = context;
        this.listener = listener;
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().tasksSubcomponent().inject(this);
    }

    public void updateUser(String name, String nativeCurrency, String timeZone) {
        if (this.loginManager.isSignedIn()) {
            HashMap<String, Object> params = new HashMap();
            if (name != null) {
                params.put("name", name);
            }
            if (nativeCurrency != null) {
                params.put("native_currency", nativeCurrency);
            }
            if (timeZone != null) {
                params.put(ApiConstants.TIME_ZONE, timeZone);
            }
            if (this.listener != null) {
                this.listener.onPreExecute();
            }
            this.loginManager.getClient().updateUser(params, new CallbackWithRetrofit<User>() {
                public void onResponse(Call<User> call, Response<User> response, Retrofit retrofit) {
                    if (response.isSuccessful()) {
                        Editor e = PreferenceManager.getDefaultSharedPreferences(UpdateUserTask.this.context).edit();
                        Data user = ((User) response.body()).getData();
                        e.putString(Constants.KEY_ACCOUNT_NATIVE_CURRENCY, user.getNativeCurrency());
                        e.putString(Constants.KEY_ACCOUNT_FULL_NAME, user.getName());
                        e.putString(Constants.KEY_ACCOUNT_TIME_ZONE, user.getTimeZone());
                        e.apply();
                        UpdateUserTask.this.mLocalUserDataUpdatedConnector.get().onNext(null);
                        UpdateUserTask.this.mUserUpdatedConnector.get().onNext(user);
                        if (UpdateUserTask.this.listener != null) {
                            UpdateUserTask.this.listener.onFinally();
                        }
                    } else if (UpdateUserTask.this.listener != null) {
                        UpdateUserTask.this.listener.onException();
                    }
                }

                public void onFailure(Call<User> call, Throwable t) {
                    if (UpdateUserTask.this.listener != null) {
                        UpdateUserTask.this.listener.onException();
                    }
                }
            });
        }
    }
}
