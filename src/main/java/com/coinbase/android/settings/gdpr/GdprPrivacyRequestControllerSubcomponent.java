package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface GdprPrivacyRequestControllerSubcomponent {
    void inject(GdprPrivacyRequestController gdprPrivacyRequestController);
}
