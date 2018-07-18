package com.coinbase.android.buysell;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import com.coinbase.android.quickstart.QuickstartItem;
import com.coinbase.v2.models.paymentMethods.Data;
import java.util.List;

interface BuyScreen {
    Bundle getArgs();

    void hideProgressOverlay();

    void hideQuickBuys();

    void showBuyContent(boolean z);

    void showFiatPaymentMethod(Data data, String str, String str2);

    void showInvalidKeystroke();

    void showMissingPaymentMethod();

    void showPaymentMethod(Data data, String str, boolean z);

    void showProgressOverlay();

    void showQuickBuys(String str);

    void showQuickstartItems(List<QuickstartItem> list);

    void showRegionUnsupported(String str);

    void updatePrimaryAmountText(SpannableStringBuilder spannableStringBuilder);

    void updateSecondaryCurrencyCode(String str);

    void updateTitle(String str);
}
