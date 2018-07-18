package com.coinbase.android.wbl;

import android.text.TextUtils;
import com.coinbase.ApiConstants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.utils.ApiErrorHandler;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ControllerScope
public class WithdrawalBasedLimitsApiErrorHandler implements ApiErrorHandler {
    private static final String ERRORS = "errors";
    private static final Set<String> HANDLED_APIS = new HashSet();
    private final WithdrawalBasedLimitsErrorRouter mWithdrawalBasedLimitsErrorRouter;

    static {
        HANDLED_APIS.add("send");
        HANDLED_APIS.add(ApiConstants.SELLS);
        HANDLED_APIS.add(ApiConstants.WITHDRAWALS);
    }

    @Inject
    public WithdrawalBasedLimitsApiErrorHandler(WithdrawalBasedLimitsErrorRouter withdrawalBasedLimitsErrorRouter) {
        this.mWithdrawalBasedLimitsErrorRouter = withdrawalBasedLimitsErrorRouter;
    }

    public boolean handleApiError(String errorMessage, String apiEndpoint) {
        if (TextUtils.isEmpty(errorMessage) || !HANDLED_APIS.contains(apiEndpoint)) {
            return false;
        }
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create();
        try {
            JSONArray array = new JSONObject(errorMessage).getJSONArray(ERRORS);
            if (array == null || array.length() == 0) {
                return false;
            }
            WithdrawalBasedLimitsApiError apiError = (WithdrawalBasedLimitsApiError) gson.fromJson(array.get(0).toString(), WithdrawalBasedLimitsApiError.class);
            if (!TextUtils.equals(apiError.getId(), WithdrawalBasedLimitsErrorRouter.WITHDRAWAL_BASED_LIMIT_EXCEEDED) || !TextUtils.equals(apiError.getLearnMoreLocation(), WithdrawalBasedLimitsErrorRouter.AVAILABLE_BALANCE_LOCATION)) {
                return false;
            }
            this.mWithdrawalBasedLimitsErrorRouter.routeWithdrawalBasedLimitsError(apiError.getMessage(), apiError.getTitle(), apiError.getLearnMoreText(), apiError.getDismissText());
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        } catch (JSONException e2) {
            return false;
        }
    }
}
