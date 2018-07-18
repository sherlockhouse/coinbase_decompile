package com.coinbase.android.gdpr;

import com.coinbase.android.utils.analytics.Event;
import com.coinbase.android.utils.analytics.Property;

class GdprAnalytics {

    enum GdprEvent implements Event {
        PRIVACY_VIEWED,
        PRIVACY_TAPPED_ACKNOWLEDGE,
        MARKETING_VIEWED,
        MARKETING_TAPPED_YES,
        MARKETING_TAPPED_NO
    }

    enum GdprProperty implements Property {
        LOGGED_IN
    }

    GdprAnalytics() {
    }
}
