package com.coinbase.android.notifications.fcm;

import android.util.Log;
import android.util.Pair;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.ServiceScope;
import com.coinbase.android.utils.DeviceUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Retrofit;

@ServiceScope
public class InstanceIDService extends FirebaseInstanceIdService {
    @Inject
    LoginManager mLoginManager;

    public void onCreate() {
        super.onCreate();
        ((ComponentProvider) getApplicationContext()).applicationComponent().coinbaseServiceSubcomponent().inject(this);
    }

    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCM Token", refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        this.mLoginManager.getClient().createDeviceTokenRx(DeviceUtils.deviceId(getApplicationContext()), token).subscribe(InstanceIDService$$Lambda$1.lambdaFactory$(), InstanceIDService$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$sendRegistrationToServer$0(Pair pair) {
        Response<Void> response = pair.first;
        Retrofit retrofit = pair.second;
        if (response.isSuccessful()) {
            Log.d("create device token", "Fcm token successfully created");
        } else {
            Log.w("create device token", Utils.getErrorMessage(response, retrofit));
        }
    }
}
