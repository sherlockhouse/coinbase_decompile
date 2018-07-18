package com.coinbase.zxing.client.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;
import com.coinbase.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.coinbase.zxing.client.android.common.executor.AsyncTaskExecManager;

public final class InactivityTimer {
    private static final long INACTIVITY_DELAY_MS = 300000;
    private static final String TAG = InactivityTimer.class.getSimpleName();
    private final Activity activity;
    private InactivityAsyncTask inactivityTask;
    private final BroadcastReceiver powerStatusReceiver = new PowerStatusReceiver();
    private final AsyncTaskExecInterface taskExec = ((AsyncTaskExecInterface) new AsyncTaskExecManager().build());

    private final class InactivityAsyncTask extends AsyncTask<Object, Object, Object> {
        private InactivityAsyncTask() {
        }

        protected Object doInBackground(Object... objects) {
            try {
                Thread.sleep(InactivityTimer.INACTIVITY_DELAY_MS);
                Log.i(InactivityTimer.TAG, "Finishing activity due to inactivity");
                InactivityTimer.this.activity.finish();
            } catch (InterruptedException e) {
            }
            return null;
        }
    }

    private final class PowerStatusReceiver extends BroadcastReceiver {
        private PowerStatusReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                if (intent.getIntExtra("plugged", -1) <= 0) {
                    InactivityTimer.this.onActivity();
                } else {
                    InactivityTimer.this.cancel();
                }
            }
        }
    }

    public InactivityTimer(Activity activity) {
        this.activity = activity;
        onActivity();
    }

    synchronized void onActivity() {
        cancel();
        this.inactivityTask = new InactivityAsyncTask();
        this.taskExec.execute(this.inactivityTask, new Object[0]);
    }

    public void onPause() {
        cancel();
        this.activity.unregisterReceiver(this.powerStatusReceiver);
    }

    public void onResume() {
        this.activity.registerReceiver(this.powerStatusReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        onActivity();
    }

    private synchronized void cancel() {
        AsyncTask<?, ?, ?> task = this.inactivityTask;
        if (task != null) {
            task.cancel(true);
            this.inactivityTask = null;
        }
    }

    public void shutdown() {
        cancel();
    }
}
