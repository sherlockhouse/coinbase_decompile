package com.coinbase.android.paymentmethods;

import android.content.Intent;
import com.coinbase.android.FragmentScope;
import com.coinbase.android.MainActivity;
import com.coinbase.api.LoginManager;
import javax.inject.Inject;

@FragmentScope
public class IAVLoginRouter {
    private final LoginManager mLoginManager;
    private final IAVLoginScreen mScreen;

    @Inject
    IAVLoginRouter(IAVLoginScreen screen, LoginManager loginManager) {
        this.mScreen = screen;
        this.mLoginManager = loginManager;
    }

    void routeToBuyView() {
        Intent intent = new Intent(this.mScreen.getActivity(), MainActivity.class);
        intent.setFlags(67108864);
        intent.setAction(MainActivity.ACTION_BUY);
        this.mScreen.getActivity().startActivity(intent);
    }
}
