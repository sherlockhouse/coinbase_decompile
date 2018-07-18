package com.coinbase.android.deposits.fiat;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import com.coinbase.android.quickstart.QuickstartItem;
import com.coinbase.v2.models.paymentMethods.Data;
import java.util.List;

public interface WithdrawFiatScreen {
    Bundle getArgs();

    void hideProgressDialog();

    void setAmountText(SpannableStringBuilder spannableStringBuilder);

    void showInvalidKeystroke();

    void showMissingPaymentMethod();

    void showPaymentMethod(Data data);

    void showProgressDialog();

    void showQuickstartContent(List<QuickstartItem> list);

    void showRegionUnsupported(String str);

    void showWithdrawContent();
}
