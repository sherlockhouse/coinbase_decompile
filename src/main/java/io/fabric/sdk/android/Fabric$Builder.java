package io.fabric.sdk.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.PriorityThreadPoolExecutor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Fabric$Builder {
    private String appIdentifier;
    private String appInstallIdentifier;
    private final Context context;
    private boolean debuggable;
    private Handler handler;
    private InitializationCallback<Fabric> initializationCallback;
    private Kit[] kits;
    private Logger logger;
    private PriorityThreadPoolExecutor threadPoolExecutor;

    public Fabric$Builder(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        this.context = context;
    }

    public Fabric$Builder kits(Kit... kits) {
        if (this.kits != null) {
            throw new IllegalStateException("Kits already set.");
        }
        this.kits = kits;
        return this;
    }

    public Fabric build() {
        Map<Class<? extends Kit>, Kit> kitMap;
        if (this.threadPoolExecutor == null) {
            this.threadPoolExecutor = PriorityThreadPoolExecutor.create();
        }
        if (this.handler == null) {
            this.handler = new Handler(Looper.getMainLooper());
        }
        if (this.logger == null) {
            if (this.debuggable) {
                this.logger = new DefaultLogger(3);
            } else {
                this.logger = new DefaultLogger();
            }
        }
        if (this.appIdentifier == null) {
            this.appIdentifier = this.context.getPackageName();
        }
        if (this.initializationCallback == null) {
            this.initializationCallback = InitializationCallback.EMPTY;
        }
        if (this.kits == null) {
            kitMap = new HashMap();
        } else {
            kitMap = Fabric.access$000(Arrays.asList(this.kits));
        }
        Context appContext = this.context.getApplicationContext();
        return new Fabric(appContext, kitMap, this.threadPoolExecutor, this.handler, this.logger, this.debuggable, this.initializationCallback, new IdManager(appContext, this.appIdentifier, this.appInstallIdentifier, kitMap.values()), Fabric.access$100(this.context));
    }
}
