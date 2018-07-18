package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public final class zzu<R extends Result> extends Handler {
    public zzu() {
        this(Looper.getMainLooper());
    }

    public zzu(Looper looper) {
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                Pair pair = (Pair) message.obj;
                ResultCallback resultCallback = (ResultCallback) pair.first;
                Result result = (Result) pair.second;
                try {
                    resultCallback.onResult(result);
                    return;
                } catch (RuntimeException e) {
                    zzs.zzd(result);
                    throw e;
                }
            case 2:
                ((zzs) message.obj).zzu(Status.zzfhy);
                return;
            default:
                Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, new Exception());
                return;
        }
    }

    public final void zza(ResultCallback<? super R> resultCallback, R r) {
        sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
    }
}
