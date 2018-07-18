package com.coinbase.android.paymentmethods;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.android.utils.analytics.MixpanelTracking;

final /* synthetic */ class IAVLoginFragment$$Lambda$13 implements OnClickListener {
    private static final IAVLoginFragment$$Lambda$13 instance = new IAVLoginFragment$$Lambda$13();

    private IAVLoginFragment$$Lambda$13() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.VERIFY_BANK_ACCOUNT_TAPPED_AMOUNT_2, new String[0]);
    }
}
