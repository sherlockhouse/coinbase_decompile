package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface GdprRequestSentControllerSubcomponent {
    void inject(GdprRequestSentController gdprRequestSentController);
}
