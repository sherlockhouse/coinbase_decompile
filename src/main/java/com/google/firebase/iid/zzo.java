package com.google.firebase.iid;

import android.content.Intent;
import android.os.ConditionVariable;
import android.util.Log;
import java.io.IOException;

final class zzo implements zzp {
    private Intent intent;
    private final ConditionVariable zznfy;
    private String zznfz;

    private zzo() {
        this.zznfy = new ConditionVariable();
    }

    public final void onError(String str) {
        this.zznfz = str;
        this.zznfy.open();
    }

    public final Intent zzcgd() throws IOException {
        if (!this.zznfy.block(30000)) {
            Log.w("InstanceID/Rpc", "No response");
            throw new IOException("TIMEOUT");
        } else if (this.zznfz == null) {
            return this.intent;
        } else {
            throw new IOException(this.zznfz);
        }
    }

    public final void zzq(Intent intent) {
        this.intent = intent;
        this.zznfy.open();
    }
}
