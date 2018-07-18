package com.coinbase.android.settings.gdpr;

import com.coinbase.android.utils.analytics.Event;
import com.coinbase.android.utils.analytics.Property;

class GdprSettingsAnalytics {

    enum GdprSettingsEvent implements Event {
        PRIVACY_OPTIONS_VIEWED,
        PRIVACY_OPTIONS_TAPPED_REQUEST_DATA,
        PRIVACY_OPTIONS_TAPPED_REQUEST_DELETION,
        PRIVACY_OPTIONS_TAPPED_REQUEST_EXPORT,
        PRIVACY_OPTIONS_TAPPED_REQUEST_RESTRICTION_OF_PROCESSING,
        PRIVACY_OPTIONS_TAPPED_REQUEST_CORRECTION,
        REQUEST_TAPPED_SEND_REQUEST,
        EMAIL_SETTINGS_VIEWED
    }

    enum GdprSettingsProperty implements Property {
    }

    GdprSettingsAnalytics() {
    }
}
