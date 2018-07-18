package io.fabric.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.PriorityThreadPoolExecutor;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class Fabric {
    static final Logger DEFAULT_LOGGER = new DefaultLogger();
    static volatile Fabric singleton;
    private WeakReference<Activity> activity;
    private ActivityLifecycleManager activityLifecycleManager;
    private final Context context;
    final boolean debuggable;
    private final ExecutorService executorService;
    private final IdManager idManager;
    private final InitializationCallback<Fabric> initializationCallback;
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private final InitializationCallback<?> kitInitializationCallback;
    private final Map<Class<? extends Kit>, Kit> kits;
    final Logger logger;
    private final Handler mainHandler;

    static Fabric singleton() {
        if (singleton != null) {
            return singleton;
        }
        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    Fabric(Context context, Map<Class<? extends Kit>, Kit> kits, PriorityThreadPoolExecutor threadPoolExecutor, Handler mainHandler, Logger logger, boolean debuggable, InitializationCallback callback, IdManager idManager, Activity rootActivity) {
        this.context = context;
        this.kits = kits;
        this.executorService = threadPoolExecutor;
        this.mainHandler = mainHandler;
        this.logger = logger;
        this.debuggable = debuggable;
        this.initializationCallback = callback;
        this.kitInitializationCallback = createKitInitializationCallback(kits.size());
        this.idManager = idManager;
        setCurrentActivity(rootActivity);
    }

    public static Fabric with(Context context, Kit... kits) {
        if (singleton == null) {
            synchronized (Fabric.class) {
                if (singleton == null) {
                    setFabric(new Builder(context).kits(kits).build());
                }
            }
        }
        return singleton;
    }

    private static void setFabric(Fabric fabric) {
        singleton = fabric;
        fabric.init();
    }

    public Fabric setCurrentActivity(Activity activity) {
        this.activity = new WeakReference(activity);
        return this;
    }

    public Activity getCurrentActivity() {
        if (this.activity != null) {
            return (Activity) this.activity.get();
        }
        return null;
    }

    private void init() {
        this.activityLifecycleManager = new ActivityLifecycleManager(this.context);
        this.activityLifecycleManager.registerCallbacks(new 1(this));
        initializeKits(this.context);
    }

    public String getVersion() {
        return "1.4.0.18";
    }

    public String getIdentifier() {
        return "io.fabric.sdk.android:fabric";
    }

    void initializeKits(Context context) {
        StringBuilder initInfo;
        Future<Map<String, KitInfo>> installedKitsFuture = getKitsFinderFuture(context);
        Collection<Kit> providedKits = getKits();
        Onboarding onboarding = new Onboarding(installedKitsFuture, providedKits);
        List<Kit> kits = new ArrayList(providedKits);
        Collections.sort(kits);
        onboarding.injectParameters(context, this, InitializationCallback.EMPTY, this.idManager);
        for (Kit kit : kits) {
            kit.injectParameters(context, this, this.kitInitializationCallback, this.idManager);
        }
        onboarding.initialize();
        if (getLogger().isLoggable("Fabric", 3)) {
            initInfo = new StringBuilder("Initializing ").append(getIdentifier()).append(" [Version: ").append(getVersion()).append("], with the following kits:\n");
        } else {
            initInfo = null;
        }
        for (Kit kit2 : kits) {
            kit2.initializationTask.addDependency(onboarding.initializationTask);
            addAnnotatedDependencies(this.kits, kit2);
            kit2.initialize();
            if (initInfo != null) {
                initInfo.append(kit2.getIdentifier()).append(" [Version: ").append(kit2.getVersion()).append("]\n");
            }
        }
        if (initInfo != null) {
            getLogger().d("Fabric", initInfo.toString());
        }
    }

    void addAnnotatedDependencies(Map<Class<? extends Kit>, Kit> kits, Kit dependentKit) {
        DependsOn dependsOn = dependentKit.dependsOnAnnotation;
        if (dependsOn != null) {
            for (Class<?> dependency : dependsOn.value()) {
                if (dependency.isInterface()) {
                    for (Kit kit : kits.values()) {
                        if (dependency.isAssignableFrom(kit.getClass())) {
                            dependentKit.initializationTask.addDependency(kit.initializationTask);
                        }
                    }
                } else if (((Kit) kits.get(dependency)) == null) {
                    throw new UnmetDependencyException("Referenced Kit was null, does the kit exist?");
                } else {
                    dependentKit.initializationTask.addDependency(((Kit) kits.get(dependency)).initializationTask);
                }
            }
        }
    }

    private static Activity extractActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    public ActivityLifecycleManager getActivityLifecycleManager() {
        return this.activityLifecycleManager;
    }

    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    public Collection<Kit> getKits() {
        return this.kits.values();
    }

    public static <T extends Kit> T getKit(Class<T> cls) {
        return (Kit) singleton().kits.get(cls);
    }

    public static Logger getLogger() {
        if (singleton == null) {
            return DEFAULT_LOGGER;
        }
        return singleton.logger;
    }

    public static boolean isDebuggable() {
        if (singleton == null) {
            return false;
        }
        return singleton.debuggable;
    }

    public static boolean isInitialized() {
        return singleton != null && singleton.initialized.get();
    }

    private static Map<Class<? extends Kit>, Kit> getKitMap(Collection<? extends Kit> kits) {
        HashMap<Class<? extends Kit>, Kit> map = new HashMap(kits.size());
        addToKitMap(map, kits);
        return map;
    }

    private static void addToKitMap(Map<Class<? extends Kit>, Kit> map, Collection<? extends Kit> kits) {
        for (Kit kit : kits) {
            map.put(kit.getClass(), kit);
            if (kit instanceof KitGroup) {
                addToKitMap(map, ((KitGroup) kit).getKits());
            }
        }
    }

    InitializationCallback<?> createKitInitializationCallback(int size) {
        return new 2(this, size);
    }

    Future<Map<String, KitInfo>> getKitsFinderFuture(Context context) {
        return getExecutorService().submit(new FabricKitsFinder(context.getPackageCodePath()));
    }
}
