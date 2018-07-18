package com.coinbase.android.wbl;

public interface WithdrawalBasedLimitsErrorRouter {
    public static final String AVAILABLE_BALANCE_LOCATION = "available_balance_details";
    public static final String WITHDRAWAL_BASED_LIMIT_EXCEEDED = "withdrawal_limit_exceeded";

    void routeWithdrawalBasedLimitsError(String str, String str2, String str3, String str4);
}
