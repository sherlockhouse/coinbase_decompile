package com.coinbase.android.settings.tiers;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface InvestmentTiersSettingsControllerSubcomponent {
    void inject(InvestmentTiersSettingsController investmentTiersSettingsController);
}
