package com.bluelinelabs.conductor.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.ActivityHostedRouter;
import com.bluelinelabs.conductor.Router;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LifecycleHandler extends Fragment implements ActivityLifecycleCallbacks {
    private static final Map<Activity, LifecycleHandler> activeLifecycleHandlers = new HashMap();
    private Activity activity;
    private SparseArray<String> activityRequestMap = new SparseArray();
    private boolean attached;
    private boolean destroyed;
    private boolean hasRegisteredCallbacks;
    private ArrayList<PendingPermissionRequest> pendingPermissionRequests = new ArrayList();
    private SparseArray<String> permissionRequestMap = new SparseArray();
    private final Map<Integer, ActivityHostedRouter> routerMap = new HashMap();

    private static class PendingPermissionRequest implements Parcelable {
        public static final Creator<PendingPermissionRequest> CREATOR = new Creator<PendingPermissionRequest>() {
            public PendingPermissionRequest createFromParcel(Parcel in) {
                return new PendingPermissionRequest(in);
            }

            public PendingPermissionRequest[] newArray(int size) {
                return new PendingPermissionRequest[size];
            }
        };
        final String instanceId;
        final String[] permissions;
        final int requestCode;

        PendingPermissionRequest(String instanceId, String[] permissions, int requestCode) {
            this.instanceId = instanceId;
            this.permissions = permissions;
            this.requestCode = requestCode;
        }

        private PendingPermissionRequest(Parcel in) {
            this.instanceId = in.readString();
            this.permissions = in.createStringArray();
            this.requestCode = in.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeString(this.instanceId);
            out.writeStringArray(this.permissions);
            out.writeInt(this.requestCode);
        }
    }

    public LifecycleHandler() {
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    private static LifecycleHandler findInActivity(Activity activity) {
        LifecycleHandler lifecycleHandler = (LifecycleHandler) activeLifecycleHandlers.get(activity);
        if (lifecycleHandler == null) {
            lifecycleHandler = (LifecycleHandler) activity.getFragmentManager().findFragmentByTag("LifecycleHandler");
        }
        if (lifecycleHandler != null) {
            lifecycleHandler.registerActivityListener(activity);
        }
        return lifecycleHandler;
    }

    public static LifecycleHandler install(Activity activity) {
        LifecycleHandler lifecycleHandler = findInActivity(activity);
        if (lifecycleHandler == null) {
            lifecycleHandler = new LifecycleHandler();
            activity.getFragmentManager().beginTransaction().add(lifecycleHandler, "LifecycleHandler").commit();
        }
        lifecycleHandler.registerActivityListener(activity);
        return lifecycleHandler;
    }

    public Router getRouter(ViewGroup container, Bundle savedInstanceState) {
        ActivityHostedRouter router = (ActivityHostedRouter) this.routerMap.get(Integer.valueOf(getRouterHashKey(container)));
        if (router == null) {
            router = new ActivityHostedRouter();
            router.setHost(this, container);
            if (savedInstanceState != null) {
                Bundle routerSavedState = savedInstanceState.getBundle("LifecycleHandler.routerState" + router.getContainerId());
                if (routerSavedState != null) {
                    router.restoreInstanceState(routerSavedState);
                }
            }
            this.routerMap.put(Integer.valueOf(getRouterHashKey(container)), router);
        } else {
            router.setHost(this, container);
        }
        return router;
    }

    public List<Router> getRouters() {
        return new ArrayList(this.routerMap.values());
    }

    public Activity getLifecycleActivity() {
        return this.activity;
    }

    private static int getRouterHashKey(ViewGroup viewGroup) {
        return viewGroup.getId();
    }

    private void registerActivityListener(Activity activity) {
        this.activity = activity;
        if (!this.hasRegisteredCallbacks) {
            this.hasRegisteredCallbacks = true;
            activity.getApplication().registerActivityLifecycleCallbacks(this);
            activeLifecycleHandlers.put(activity, this);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            StringSparseArrayParceler permissionParcel = (StringSparseArrayParceler) savedInstanceState.getParcelable("LifecycleHandler.permissionRequests");
            this.permissionRequestMap = permissionParcel != null ? permissionParcel.getStringSparseArray() : new SparseArray();
            StringSparseArrayParceler activityParcel = (StringSparseArrayParceler) savedInstanceState.getParcelable("LifecycleHandler.activityRequests");
            this.activityRequestMap = activityParcel != null ? activityParcel.getStringSparseArray() : new SparseArray();
            ArrayList<PendingPermissionRequest> pendingRequests = savedInstanceState.getParcelableArrayList("LifecycleHandler.pendingPermissionRequests");
            if (pendingRequests == null) {
                pendingRequests = new ArrayList();
            }
            this.pendingPermissionRequests = pendingRequests;
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("LifecycleHandler.permissionRequests", new StringSparseArrayParceler(this.permissionRequestMap));
        outState.putParcelable("LifecycleHandler.activityRequests", new StringSparseArrayParceler(this.activityRequestMap));
        outState.putParcelableArrayList("LifecycleHandler.pendingPermissionRequests", this.pendingPermissionRequests);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.activity != null) {
            this.activity.getApplication().unregisterActivityLifecycleCallbacks(this);
            activeLifecycleHandlers.remove(this.activity);
            destroyRouters();
            this.activity = null;
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.destroyed = false;
        setAttached();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.destroyed = false;
        setAttached();
    }

    public void onDetach() {
        super.onDetach();
        this.attached = false;
        destroyRouters();
    }

    private void setAttached() {
        if (!this.attached) {
            this.attached = true;
            for (int i = this.pendingPermissionRequests.size() - 1; i >= 0; i--) {
                PendingPermissionRequest request = (PendingPermissionRequest) this.pendingPermissionRequests.remove(i);
                requestPermissions(request.instanceId, request.permissions, request.requestCode);
            }
        }
        for (ActivityHostedRouter router : this.routerMap.values()) {
            router.onContextAvailable();
        }
    }

    private void destroyRouters() {
        if (!this.destroyed) {
            this.destroyed = true;
            if (this.activity != null) {
                for (Router router : this.routerMap.values()) {
                    router.onActivityDestroyed(this.activity);
                }
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String instanceId = (String) this.activityRequestMap.get(requestCode);
        if (instanceId != null) {
            for (Router router : this.routerMap.values()) {
                router.onActivityResult(instanceId, requestCode, resultCode, data);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        String instanceId = (String) this.permissionRequestMap.get(requestCode);
        if (instanceId != null) {
            for (Router router : this.routerMap.values()) {
                router.onRequestPermissionsResult(instanceId, requestCode, permissions, grantResults);
            }
        }
    }

    public boolean shouldShowRequestPermissionRationale(String permission) {
        for (Router router : this.routerMap.values()) {
            Boolean handled = router.handleRequestedPermission(permission);
            if (handled != null) {
                return handled.booleanValue();
            }
        }
        return super.shouldShowRequestPermissionRationale(permission);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        for (Router router : this.routerMap.values()) {
            router.onCreateOptionsMenu(menu, inflater);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        for (Router router : this.routerMap.values()) {
            router.onPrepareOptionsMenu(menu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        for (Router router : this.routerMap.values()) {
            if (router.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void registerForActivityResult(String instanceId, int requestCode) {
        this.activityRequestMap.put(requestCode, instanceId);
    }

    public void unregisterForActivityResults(String instanceId) {
        for (int i = this.activityRequestMap.size() - 1; i >= 0; i--) {
            if (instanceId.equals(this.activityRequestMap.get(this.activityRequestMap.keyAt(i)))) {
                this.activityRequestMap.removeAt(i);
            }
        }
    }

    public void startActivityForResult(String instanceId, Intent intent, int requestCode) {
        registerForActivityResult(instanceId, requestCode);
        startActivityForResult(intent, requestCode);
    }

    public void startActivityForResult(String instanceId, Intent intent, int requestCode, Bundle options) {
        registerForActivityResult(instanceId, requestCode);
        startActivityForResult(intent, requestCode, options);
    }

    @TargetApi(24)
    public void startIntentSenderForResult(String instanceId, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws SendIntentException {
        registerForActivityResult(instanceId, requestCode);
        startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    @TargetApi(23)
    public void requestPermissions(String instanceId, String[] permissions, int requestCode) {
        if (this.attached) {
            this.permissionRequestMap.put(requestCode, instanceId);
            requestPermissions(permissions, requestCode);
            return;
        }
        this.pendingPermissionRequests.add(new PendingPermissionRequest(instanceId, permissions, requestCode));
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (this.activity == null && findInActivity(activity) == this) {
            this.activity = activity;
            for (ActivityHostedRouter router : this.routerMap.values()) {
                router.onContextAvailable();
            }
        }
    }

    public void onActivityStarted(Activity activity) {
        if (this.activity == activity) {
            for (Router router : this.routerMap.values()) {
                router.onActivityStarted(activity);
            }
        }
    }

    public void onActivityResumed(Activity activity) {
        if (this.activity == activity) {
            for (Router router : this.routerMap.values()) {
                router.onActivityResumed(activity);
            }
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.activity == activity) {
            for (Router router : this.routerMap.values()) {
                router.onActivityPaused(activity);
            }
        }
    }

    public void onActivityStopped(Activity activity) {
        if (this.activity == activity) {
            for (Router router : this.routerMap.values()) {
                router.onActivityStopped(activity);
            }
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        if (this.activity == activity) {
            for (Router router : this.routerMap.values()) {
                Bundle bundle = new Bundle();
                router.saveInstanceState(bundle);
                outState.putBundle("LifecycleHandler.routerState" + router.getContainerId(), bundle);
            }
        }
    }

    public void onActivityDestroyed(Activity activity) {
        activeLifecycleHandlers.remove(activity);
    }
}
