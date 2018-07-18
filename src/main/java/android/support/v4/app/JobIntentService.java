package android.support.v4.app;

import android.app.Service;
import android.content.ComponentName;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service {
    static final HashMap<ComponentName, Object> sClassWorkEnqueuer = new HashMap();
    static final Object sLock = new Object();
    final ArrayList<Object> mCompatQueue;
    boolean mInterruptIfStopped = false;
    boolean mStopped = false;

    public JobIntentService() {
        if (VERSION.SDK_INT >= 26) {
            this.mCompatQueue = null;
        } else {
            this.mCompatQueue = new ArrayList();
        }
    }
}
