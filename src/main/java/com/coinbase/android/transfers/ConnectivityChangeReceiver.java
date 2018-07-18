package com.coinbase.android.transfers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.coinbase.android.utils.Utils;

public class ConnectivityChangeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (Utils.isConnectedOrConnecting(context)) {
            context.startService(new Intent(context, DelayedTxSenderService.class));
        }
    }
}
