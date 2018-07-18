package com.coinbase.android.paymentmethods;

import android.util.Pair;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.v2.models.paymentMethods.PaymentMethods;
import java.util.HashMap;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;

public class GetPaymentMethodsTaskRx {
    private final LoginManager mLoginManager;

    public GetPaymentMethodsTaskRx(LoginManager loginManager) {
        this.mLoginManager = loginManager;
    }

    public synchronized Observable<Pair<Response<PaymentMethods>, Retrofit>> getPaymentMethods() {
        return getPaymentMethods(false);
    }

    public synchronized Observable<Pair<Response<PaymentMethods>, Retrofit>> getPaymentMethodsExcludeLimits() {
        return getPaymentMethods(true);
    }

    private Observable<Pair<Response<PaymentMethods>, Retrofit>> getPaymentMethods(boolean excludeLimits) {
        HashMap<String, Object> options = new HashMap();
        options.put(ApiConstants.LIMIT, Integer.valueOf(100));
        if (excludeLimits) {
            options.put(ApiConstants.EXCLUDE_LIMITS, Boolean.valueOf(true));
        }
        return this.mLoginManager.getClient().getPaymentMethodsRx(options);
    }
}
