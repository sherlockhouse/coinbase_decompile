package com.coinbase.android.paymentmethods;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.android.utils.analytics.MixpanelTracking;

final /* synthetic */ class IAVLoginFragment$$Lambda$14 implements OnClickListener {
    private static final IAVLoginFragment$$Lambda$14 instance = new IAVLoginFragment$$Lambda$14();

    private IAVLoginFragment$$Lambda$14() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.VERIFY_CARD_TAPPED_ISSUES, new String[0]);
    }
}
