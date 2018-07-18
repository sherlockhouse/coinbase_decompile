package com.coinbase.android.settings.tiers;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.tiers.Requirement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class InvestmentTiersSettingsRouter {
    private final ActionBarController mController;

    @Inject
    public InvestmentTiersSettingsRouter(ActionBarController controller) {
        this.mController = controller;
    }

    void routeRequirements(List<Requirement> requirements, int nextTier) {
        Bundle args = new Bundle();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create();
        args.putInt(InvestmentTiersRequirementsPresenter.NEXT_TIER, nextTier);
        args.putString("requirements", gson.toJson((Object) requirements));
        this.mController.pushModalController(new InvestmentTiersRequirementsController(this.mController.appendModalArgs(args)));
    }
}
