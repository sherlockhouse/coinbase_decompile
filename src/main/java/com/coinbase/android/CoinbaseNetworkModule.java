package com.coinbase.android;

import android.app.Application;
import android.content.SharedPreferences;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.paymentmethods.card.WorldPayPollingWrapper;
import com.coinbase.android.settings.UserUpdatedConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SignOutConnector;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.CoinbaseInternal;
import rx.Scheduler;

public class CoinbaseNetworkModule {
    @ApplicationScope
    LoginManager providesLoginManager(Application context, DatabaseManager dbManager, @MainScheduler Scheduler mainScheduler, UserUpdatedConnector userUpdatedConnector, SharedPreferences sharedPrefs, SignOutConnector signOutConnector) {
        return new LoginManager(context, dbManager, CoinbaseInternal.getInstance(), mainScheduler, userUpdatedConnector, signOutConnector, sharedPrefs);
    }

    @ApplicationScope
    CoinbaseInternal providesCoinbaseInternal() {
        return CoinbaseInternal.getInstance();
    }

    @ApplicationScope
    WorldPayPollingWrapper providesWorldPayPollingWrapper() {
        return new WorldPayPollingWrapper() {
            private static final long POLL_DELAY = 5;
            private static final long POLL_MAX_TIME = 60000;

            public long getPollDelay() {
                return POLL_DELAY;
            }

            public long getPollMaxTime() {
                return POLL_MAX_TIME;
            }
        };
    }
}
