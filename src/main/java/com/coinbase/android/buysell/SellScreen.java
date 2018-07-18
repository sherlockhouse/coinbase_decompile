package com.coinbase.android.buysell;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import com.coinbase.android.quickstart.QuickstartItem;
import com.coinbase.v2.models.paymentMethods.Data;
import java.util.List;

interface SellScreen {
    Bundle getArgs();

    void hideProgressOverlay();

    void showFiatPaymentMethod(Data data, String str);

    void showInvalidKeystroke();

    void showMissingPaymentMethod();

    void showPaymentMethod(Data data);

    void showProgressOverlay();

    void showQuickstartItems(List<QuickstartItem> list);

    void showRegionUnsupported(String str);

    void showSellContent(boolean z, boolean z2);

    void updatePrimaryAmountText(SpannableStringBuilder spannableStringBuilder);

    void updateSecondaryCurrencyCode(String str);

    void updateTitle(String str);
}
