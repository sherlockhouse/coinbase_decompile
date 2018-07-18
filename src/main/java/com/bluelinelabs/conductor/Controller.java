package com.bluelinelabs.conductor;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.internal.ClassUtils;
import com.bluelinelabs.conductor.internal.RouterRequiringFunc;
import com.bluelinelabs.conductor.internal.ViewAttachHandler;
import com.bluelinelabs.conductor.internal.ViewAttachHandler.ViewAttachListener;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class Controller {
    private static final String KEY_ARGS = "Controller.args";
    private static final String KEY_CHILD_ROUTERS = "Controller.childRouters";
    private static final String KEY_CLASS_NAME = "Controller.className";
    private static final String KEY_INSTANCE_ID = "Controller.instanceId";
    private static final String KEY_NEEDS_ATTACH = "Controller.needsAttach";
    private static final String KEY_OVERRIDDEN_POP_HANDLER = "Controller.overriddenPopHandler";
    private static final String KEY_OVERRIDDEN_PUSH_HANDLER = "Controller.overriddenPushHandler";
    private static final String KEY_REQUESTED_PERMISSIONS = "Controller.requestedPermissions";
    private static final String KEY_RETAIN_VIEW_MODE = "Controller.retainViewMode";
    private static final String KEY_SAVED_STATE = "Controller.savedState";
    private static final String KEY_TARGET_INSTANCE_ID = "Controller.target.instanceId";
    private static final String KEY_VIEW_STATE = "Controller.viewState";
    static final String KEY_VIEW_STATE_BUNDLE = "Controller.viewState.bundle";
    private static final String KEY_VIEW_STATE_HIERARCHY = "Controller.viewState.hierarchy";
    private final Bundle args;
    private boolean attached;
    private boolean attachedToUnownedParent;
    private final List<ControllerHostedRouter> childRouters;
    private boolean destroyed;
    private WeakReference<View> destroyedView;
    private boolean hasOptionsMenu;
    private boolean hasSavedViewState;
    private String instanceId;
    private boolean isBeingDestroyed;
    private boolean isContextAvailable;
    private boolean isDetachFrozen;
    private boolean isPerformingExitTransition;
    private final List<LifecycleListener> lifecycleListeners;
    private boolean needsAttach;
    private final ArrayList<RouterRequiringFunc> onRouterSetListeners;
    private boolean optionsMenuHidden;
    private ControllerChangeHandler overriddenPopHandler;
    private ControllerChangeHandler overriddenPushHandler;
    private Controller parentController;
    private final ArrayList<String> requestedPermissions;
    private RetainViewMode retainViewMode;
    private Router router;
    private Bundle savedInstanceState;
    private String targetInstanceId;
    private View view;
    private ViewAttachHandler viewAttachHandler;
    private boolean viewIsAttached;
    Bundle viewState;
    private boolean viewWasDetached;

    public static abstract class LifecycleListener {
        public void onChangeStart(Controller controller, ControllerChangeHandler changeHandler, ControllerChangeType changeType) {
        }

        public void onChangeEnd(Controller controller, ControllerChangeHandler changeHandler, ControllerChangeType changeType) {
        }

        public void preCreateView(Controller controller) {
        }

        public void postCreateView(Controller controller, View view) {
        }

        public void preAttach(Controller controller, View view) {
        }

        public void postAttach(Controller controller, View view) {
        }

        public void preDetach(Controller controller, View view) {
        }

        public void postDetach(Controller controller, View view) {
        }

        public void preDestroyView(Controller controller, View view) {
        }

        public void postDestroyView(Controller controller) {
        }

        public void preDestroy(Controller controller) {
        }

        public void postDestroy(Controller controller) {
        }

        public void preContextAvailable(Controller controller) {
        }

        public void postContextAvailable(Controller controller, Context context) {
        }

        public void preContextUnavailable(Controller controller, Context context) {
        }

        public void postContextUnavailable(Controller controller) {
        }

        public void onSaveInstanceState(Controller controller, Bundle outState) {
        }

        public void onRestoreInstanceState(Controller controller, Bundle savedInstanceState) {
        }

        public void onSaveViewState(Controller controller, Bundle outState) {
        }

        public void onRestoreViewState(Controller controller, Bundle savedViewState) {
        }
    }

    public enum RetainViewMode {
        RELEASE_DETACH,
        RETAIN_DETACH
    }

    protected abstract View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    static Controller newInstance(Bundle bundle) {
        Controller controller;
        String className = bundle.getString(KEY_CLASS_NAME);
        Class cls = ClassUtils.classForName(className, false);
        Constructor[] constructors = cls.getConstructors();
        Constructor bundleConstructor = getBundleConstructor(constructors);
        Bundle args = bundle.getBundle(KEY_ARGS);
        if (args != null) {
            args.setClassLoader(cls.getClassLoader());
        }
        if (bundleConstructor != null) {
            try {
                controller = (Controller) bundleConstructor.newInstance(new Object[]{args});
            } catch (Exception e) {
                throw new RuntimeException("An exception occurred while creating a new instance of " + className + ". " + e.getMessage(), e);
            }
        }
        controller = (Controller) getDefaultConstructor(constructors).newInstance(new Object[0]);
        if (args != null) {
            controller.args.putAll(args);
        }
        controller.restoreInstanceState(bundle);
        return controller;
    }

    protected Controller() {
        this(null);
    }

    protected Controller(Bundle args) {
        this.retainViewMode = RetainViewMode.RELEASE_DETACH;
        this.childRouters = new ArrayList();
        this.lifecycleListeners = new ArrayList();
        this.requestedPermissions = new ArrayList();
        this.onRouterSetListeners = new ArrayList();
        if (args == null) {
            args = new Bundle(getClass().getClassLoader());
        }
        this.args = args;
        this.instanceId = UUID.randomUUID().toString();
        ensureRequiredConstructor();
    }

    public final Router getRouter() {
        return this.router;
    }

    public Bundle getArgs() {
        return this.args;
    }

    public final Router getChildRouter(ViewGroup container) {
        return getChildRouter(container, null);
    }

    public final Router getChildRouter(ViewGroup container, String tag) {
        return getChildRouter(container, tag, true);
    }

    public final Router getChildRouter(ViewGroup container, String tag, boolean createIfNeeded) {
        int containerId = container.getId();
        ControllerHostedRouter childRouter = null;
        for (ControllerHostedRouter router : this.childRouters) {
            if (router.getHostId() == containerId && TextUtils.equals(tag, router.getTag())) {
                childRouter = router;
                break;
            }
        }
        if (childRouter == null) {
            if (createIfNeeded) {
                childRouter = new ControllerHostedRouter(container.getId(), tag);
                childRouter.setHost(this, container);
                this.childRouters.add(childRouter);
                if (this.isPerformingExitTransition) {
                    childRouter.setDetachFrozen(true);
                }
            }
        } else if (!childRouter.hasHost()) {
            childRouter.setHost(this, container);
            childRouter.rebindIfNeeded();
        }
        return childRouter;
    }

    public final void removeChildRouter(Router childRouter) {
        if ((childRouter instanceof ControllerHostedRouter) && this.childRouters.remove(childRouter)) {
            childRouter.destroy(true);
        }
    }

    public final boolean isDestroyed() {
        return this.destroyed;
    }

    public final boolean isBeingDestroyed() {
        return this.isBeingDestroyed;
    }

    public final boolean isAttached() {
        return this.attached;
    }

    public final View getView() {
        return this.view;
    }

    public final Activity getActivity() {
        return this.router != null ? this.router.getActivity() : null;
    }

    public final Resources getResources() {
        Activity activity = getActivity();
        return activity != null ? activity.getResources() : null;
    }

    public final Context getApplicationContext() {
        Activity activity = getActivity();
        return activity != null ? activity.getApplicationContext() : null;
    }

    public final Controller getParentController() {
        return this.parentController;
    }

    public final String getInstanceId() {
        return this.instanceId;
    }

    final Controller findController(String instanceId) {
        if (this.instanceId.equals(instanceId)) {
            return this;
        }
        for (Router router : this.childRouters) {
            Controller matchingChild = router.getControllerWithInstanceId(instanceId);
            if (matchingChild != null) {
                return matchingChild;
            }
        }
        return null;
    }

    public final List<Router> getChildRouters() {
        List<Router> routers = new ArrayList();
        for (Router router : this.childRouters) {
            routers.add(router);
        }
        return routers;
    }

    public void setTargetController(Controller target) {
        if (this.targetInstanceId != null) {
            throw new RuntimeException("Target controller already set. A controller's target may only be set once.");
        }
        this.targetInstanceId = target != null ? target.getInstanceId() : null;
    }

    public final Controller getTargetController() {
        if (this.targetInstanceId != null) {
            return this.router.getRootRouter().getControllerWithInstanceId(this.targetInstanceId);
        }
        return null;
    }

    protected void onDestroyView(View view) {
    }

    protected void onChangeStarted(ControllerChangeHandler changeHandler, ControllerChangeType changeType) {
    }

    protected void onChangeEnded(ControllerChangeHandler changeHandler, ControllerChangeType changeType) {
    }

    protected void onContextAvailable(Context context) {
    }

    protected void onContextUnavailable() {
    }

    protected void onAttach(View view) {
    }

    protected void onDetach(View view) {
    }

    protected void onDestroy() {
    }

    protected void onActivityStarted(Activity activity) {
    }

    protected void onActivityResumed(Activity activity) {
    }

    protected void onActivityPaused(Activity activity) {
    }

    protected void onActivityStopped(Activity activity) {
    }

    protected void onSaveViewState(View view, Bundle outState) {
    }

    protected void onRestoreViewState(View view, Bundle savedViewState) {
    }

    protected void onSaveInstanceState(Bundle outState) {
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    public final void startActivity(final Intent intent) {
        executeWithRouter(new RouterRequiringFunc() {
            public void execute() {
                Controller.this.router.startActivity(intent);
            }
        });
    }

    public final void startActivityForResult(final Intent intent, final int requestCode) {
        executeWithRouter(new RouterRequiringFunc() {
            public void execute() {
                Controller.this.router.startActivityForResult(Controller.this.instanceId, intent, requestCode);
            }
        });
    }

    public final void startActivityForResult(final Intent intent, final int requestCode, final Bundle options) {
        executeWithRouter(new RouterRequiringFunc() {
            public void execute() {
                Controller.this.router.startActivityForResult(Controller.this.instanceId, intent, requestCode, options);
            }
        });
    }

    public final void startIntentSenderForResult(IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws SendIntentException {
        this.router.startIntentSenderForResult(this.instanceId, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    public final void registerForActivityResult(final int requestCode) {
        executeWithRouter(new RouterRequiringFunc() {
            public void execute() {
                Controller.this.router.registerForActivityResult(Controller.this.instanceId, requestCode);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @TargetApi(23)
    public final void requestPermissions(final String[] permissions, final int requestCode) {
        this.requestedPermissions.addAll(Arrays.asList(permissions));
        executeWithRouter(new RouterRequiringFunc() {
            public void execute() {
                Controller.this.router.requestPermissions(Controller.this.instanceId, permissions, requestCode);
            }
        });
    }

    public boolean shouldShowRequestPermissionRationale(String permission) {
        return VERSION.SDK_INT >= 23 && getActivity().shouldShowRequestPermissionRationale(permission);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }

    public boolean handleBack() {
        List<RouterTransaction> childTransactions = new ArrayList();
        for (ControllerHostedRouter childRouter : this.childRouters) {
            childTransactions.addAll(childRouter.getBackstack());
        }
        Collections.sort(childTransactions, new Comparator<RouterTransaction>() {
            public int compare(RouterTransaction o1, RouterTransaction o2) {
                return o2.transactionIndex - o1.transactionIndex;
            }
        });
        for (RouterTransaction transaction : childTransactions) {
            Controller childController = transaction.controller;
            if (childController.isAttached() && childController.getRouter().handleBack()) {
                return true;
            }
        }
        return false;
    }

    public final void addLifecycleListener(LifecycleListener lifecycleListener) {
        if (!this.lifecycleListeners.contains(lifecycleListener)) {
            this.lifecycleListeners.add(lifecycleListener);
        }
    }

    public final void removeLifecycleListener(LifecycleListener lifecycleListener) {
        this.lifecycleListeners.remove(lifecycleListener);
    }

    public RetainViewMode getRetainViewMode() {
        return this.retainViewMode;
    }

    public void setRetainViewMode(RetainViewMode retainViewMode) {
        if (retainViewMode == null) {
            retainViewMode = RetainViewMode.RELEASE_DETACH;
        }
        this.retainViewMode = retainViewMode;
        if (this.retainViewMode == RetainViewMode.RELEASE_DETACH && !this.attached) {
            removeViewReference();
        }
    }

    public final ControllerChangeHandler getOverriddenPushHandler() {
        return this.overriddenPushHandler;
    }

    public void overridePushHandler(ControllerChangeHandler overriddenPushHandler) {
        this.overriddenPushHandler = overriddenPushHandler;
    }

    public ControllerChangeHandler getOverriddenPopHandler() {
        return this.overriddenPopHandler;
    }

    public void overridePopHandler(ControllerChangeHandler overriddenPopHandler) {
        this.overriddenPopHandler = overriddenPopHandler;
    }

    public final void setHasOptionsMenu(boolean hasOptionsMenu) {
        boolean invalidate = (!this.attached || this.optionsMenuHidden || this.hasOptionsMenu == hasOptionsMenu) ? false : true;
        this.hasOptionsMenu = hasOptionsMenu;
        if (invalidate) {
            this.router.invalidateOptionsMenu();
        }
    }

    public final void setOptionsMenuHidden(boolean optionsMenuHidden) {
        boolean invalidate = this.attached && this.hasOptionsMenu && this.optionsMenuHidden != optionsMenuHidden;
        this.optionsMenuHidden = optionsMenuHidden;
        if (invalidate) {
            this.router.invalidateOptionsMenu();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    final void setNeedsAttach(boolean needsAttach) {
        this.needsAttach = needsAttach;
    }

    final void prepareForHostDetach() {
        boolean z = this.needsAttach || this.attached;
        this.needsAttach = z;
        for (ControllerHostedRouter router : this.childRouters) {
            router.prepareForHostDetach();
        }
    }

    final boolean getNeedsAttach() {
        return this.needsAttach;
    }

    final boolean didRequestPermission(String permission) {
        return this.requestedPermissions.contains(permission);
    }

    final void requestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        this.requestedPermissions.removeAll(Arrays.asList(permissions));
        onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    final void setRouter(Router router) {
        if (this.router != router) {
            this.router = router;
            performOnRestoreInstanceState();
            Iterator it = this.onRouterSetListeners.iterator();
            while (it.hasNext()) {
                ((RouterRequiringFunc) it.next()).execute();
            }
            this.onRouterSetListeners.clear();
            return;
        }
        performOnRestoreInstanceState();
    }

    final void onContextAvailable() {
        Context context = this.router.getActivity();
        if (!(context == null || this.isContextAvailable)) {
            for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener.preContextAvailable(this);
            }
            this.isContextAvailable = true;
            onContextAvailable(context);
            for (LifecycleListener lifecycleListener2 : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener2.postContextAvailable(this, context);
            }
        }
        for (Router childRouter : this.childRouters) {
            childRouter.onContextAvailable();
        }
    }

    final void executeWithRouter(RouterRequiringFunc listener) {
        if (this.router != null) {
            listener.execute();
        } else {
            this.onRouterSetListeners.add(listener);
        }
    }

    final void activityStarted(Activity activity) {
        if (this.viewAttachHandler != null) {
            this.viewAttachHandler.onActivityStarted();
        }
        onActivityStarted(activity);
    }

    final void activityResumed(Activity activity) {
        if (!this.attached && this.view != null && this.viewIsAttached) {
            attach(this.view);
        } else if (this.attached) {
            this.needsAttach = false;
            this.hasSavedViewState = false;
        }
        onActivityResumed(activity);
    }

    final void activityPaused(Activity activity) {
        onActivityPaused(activity);
    }

    final void activityStopped(Activity activity) {
        if (this.viewAttachHandler != null) {
            this.viewAttachHandler.onActivityStopped();
        }
        onActivityStopped(activity);
    }

    final void activityDestroyed(Activity activity) {
        if (activity.isChangingConfigurations()) {
            detach(this.view, true, false);
        } else {
            destroy(true);
        }
        if (this.isContextAvailable) {
            for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener.preContextUnavailable(this, activity);
            }
            this.isContextAvailable = false;
            onContextUnavailable();
            for (LifecycleListener lifecycleListener2 : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener2.postContextUnavailable(this);
            }
        }
    }

    private void attach(View view) {
        boolean z = this.router == null || view.getParent() != this.router.container;
        this.attachedToUnownedParent = z;
        if (!this.attachedToUnownedParent) {
            this.hasSavedViewState = false;
            for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener.preAttach(this, view);
            }
            this.attached = true;
            this.needsAttach = false;
            onAttach(view);
            if (this.hasOptionsMenu && !this.optionsMenuHidden) {
                this.router.invalidateOptionsMenu();
            }
            for (LifecycleListener lifecycleListener2 : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener2.postAttach(this, view);
            }
        }
    }

    void detach(View view, boolean forceViewRefRemoval, boolean blockViewRefRemoval) {
        boolean removeViewRef;
        if (!this.attachedToUnownedParent) {
            for (ControllerHostedRouter router : this.childRouters) {
                router.prepareForHostDetach();
            }
        }
        if (blockViewRefRemoval || !(forceViewRefRemoval || this.retainViewMode == RetainViewMode.RELEASE_DETACH || this.isBeingDestroyed)) {
            removeViewRef = false;
        } else {
            removeViewRef = true;
        }
        if (this.attached) {
            for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener.preDetach(this, view);
            }
            this.attached = false;
            onDetach(view);
            if (this.hasOptionsMenu && !this.optionsMenuHidden) {
                this.router.invalidateOptionsMenu();
            }
            for (LifecycleListener lifecycleListener2 : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener2.postDetach(this, view);
            }
        }
        if (removeViewRef) {
            removeViewReference();
        }
    }

    private void removeViewReference() {
        if (this.view != null) {
            if (!(this.isBeingDestroyed || this.hasSavedViewState)) {
                saveViewState(this.view);
            }
            for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener.preDestroyView(this, this.view);
            }
            onDestroyView(this.view);
            this.viewAttachHandler.unregisterAttachListener(this.view);
            this.viewAttachHandler = null;
            this.viewIsAttached = false;
            if (this.isBeingDestroyed) {
                this.destroyedView = new WeakReference(this.view);
            }
            this.view = null;
            for (LifecycleListener lifecycleListener2 : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener2.postDestroyView(this);
            }
            for (ControllerHostedRouter childRouter : this.childRouters) {
                childRouter.removeHost();
            }
        }
        if (this.isBeingDestroyed) {
            performDestroy();
        }
    }

    final View inflate(ViewGroup parent) {
        if (!(this.view == null || this.view.getParent() == null || this.view.getParent() == parent)) {
            detach(this.view, true, false);
            removeViewReference();
        }
        if (this.view == null) {
            for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener.preCreateView(this);
            }
            this.view = onCreateView(LayoutInflater.from(parent.getContext()), parent);
            if (this.view == parent) {
                throw new IllegalStateException("Controller's onCreateView method returned the parent ViewGroup. Perhaps you forgot to pass false for LayoutInflater.inflate's attachToRoot parameter?");
            }
            for (LifecycleListener lifecycleListener2 : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener2.postCreateView(this, this.view);
            }
            restoreViewState(this.view);
            this.viewAttachHandler = new ViewAttachHandler(new ViewAttachListener() {
                public void onAttached() {
                    Controller.this.viewIsAttached = true;
                    Controller.this.viewWasDetached = false;
                    Controller.this.attach(Controller.this.view);
                }

                public void onDetached(boolean fromActivityStop) {
                    Controller.this.viewIsAttached = false;
                    Controller.this.viewWasDetached = true;
                    if (!Controller.this.isDetachFrozen) {
                        Controller.this.detach(Controller.this.view, false, fromActivityStop);
                    }
                }

                public void onViewDetachAfterStop() {
                    if (!Controller.this.isDetachFrozen) {
                        Controller.this.detach(Controller.this.view, false, false);
                    }
                }
            });
            this.viewAttachHandler.listenForAttach(this.view);
        } else if (this.retainViewMode == RetainViewMode.RETAIN_DETACH) {
            restoreChildControllerHosts();
        }
        return this.view;
    }

    private void restoreChildControllerHosts() {
        for (ControllerHostedRouter childRouter : this.childRouters) {
            if (!childRouter.hasHost()) {
                View containerView = this.view.findViewById(childRouter.getHostId());
                if (containerView != null && (containerView instanceof ViewGroup)) {
                    childRouter.setHost(this, (ViewGroup) containerView);
                    childRouter.rebindIfNeeded();
                }
            }
        }
    }

    private void performDestroy() {
        if (this.isContextAvailable) {
            for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener.preContextUnavailable(this, getActivity());
            }
            this.isContextAvailable = false;
            onContextUnavailable();
            for (LifecycleListener lifecycleListener2 : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener2.postContextUnavailable(this);
            }
        }
        if (!this.destroyed) {
            for (LifecycleListener lifecycleListener22 : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener22.preDestroy(this);
            }
            this.destroyed = true;
            onDestroy();
            this.parentController = null;
            for (LifecycleListener lifecycleListener222 : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener222.postDestroy(this);
            }
        }
    }

    final void destroy() {
        destroy(false);
    }

    private void destroy(boolean removeViews) {
        this.isBeingDestroyed = true;
        if (this.router != null) {
            this.router.unregisterForActivityResults(this.instanceId);
        }
        for (ControllerHostedRouter childRouter : this.childRouters) {
            childRouter.destroy(false);
        }
        if (!this.attached) {
            removeViewReference();
        } else if (removeViews) {
            detach(this.view, true, false);
        }
    }

    private void saveViewState(View view) {
        this.hasSavedViewState = true;
        this.viewState = new Bundle(getClass().getClassLoader());
        SparseArray<Parcelable> hierarchyState = new SparseArray();
        view.saveHierarchyState(hierarchyState);
        this.viewState.putSparseParcelableArray(KEY_VIEW_STATE_HIERARCHY, hierarchyState);
        Bundle stateBundle = new Bundle(getClass().getClassLoader());
        onSaveViewState(view, stateBundle);
        this.viewState.putBundle(KEY_VIEW_STATE_BUNDLE, stateBundle);
        for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
            lifecycleListener.onSaveViewState(this, this.viewState);
        }
    }

    private void restoreViewState(View view) {
        if (this.viewState != null) {
            view.restoreHierarchyState(this.viewState.getSparseParcelableArray(KEY_VIEW_STATE_HIERARCHY));
            Bundle savedViewState = this.viewState.getBundle(KEY_VIEW_STATE_BUNDLE);
            savedViewState.setClassLoader(getClass().getClassLoader());
            onRestoreViewState(view, savedViewState);
            restoreChildControllerHosts();
            for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener.onRestoreViewState(this, this.viewState);
            }
        }
    }

    final Bundle saveInstanceState() {
        if (!(this.hasSavedViewState || this.view == null)) {
            saveViewState(this.view);
        }
        Bundle outState = new Bundle();
        outState.putString(KEY_CLASS_NAME, getClass().getName());
        outState.putBundle(KEY_VIEW_STATE, this.viewState);
        outState.putBundle(KEY_ARGS, this.args);
        outState.putString(KEY_INSTANCE_ID, this.instanceId);
        outState.putString(KEY_TARGET_INSTANCE_ID, this.targetInstanceId);
        outState.putStringArrayList(KEY_REQUESTED_PERMISSIONS, this.requestedPermissions);
        String str = KEY_NEEDS_ATTACH;
        boolean z = this.needsAttach || this.attached;
        outState.putBoolean(str, z);
        outState.putInt(KEY_RETAIN_VIEW_MODE, this.retainViewMode.ordinal());
        if (this.overriddenPushHandler != null) {
            outState.putBundle(KEY_OVERRIDDEN_PUSH_HANDLER, this.overriddenPushHandler.toBundle());
        }
        if (this.overriddenPopHandler != null) {
            outState.putBundle(KEY_OVERRIDDEN_POP_HANDLER, this.overriddenPopHandler.toBundle());
        }
        ArrayList<Bundle> childBundles = new ArrayList();
        for (ControllerHostedRouter childRouter : this.childRouters) {
            Bundle routerBundle = new Bundle();
            childRouter.saveInstanceState(routerBundle);
            childBundles.add(routerBundle);
        }
        outState.putParcelableArrayList(KEY_CHILD_ROUTERS, childBundles);
        Bundle savedState = new Bundle(getClass().getClassLoader());
        onSaveInstanceState(savedState);
        for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
            lifecycleListener.onSaveInstanceState(this, savedState);
        }
        outState.putBundle(KEY_SAVED_STATE, savedState);
        return outState;
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        this.viewState = savedInstanceState.getBundle(KEY_VIEW_STATE);
        if (this.viewState != null) {
            this.viewState.setClassLoader(getClass().getClassLoader());
        }
        this.instanceId = savedInstanceState.getString(KEY_INSTANCE_ID);
        this.targetInstanceId = savedInstanceState.getString(KEY_TARGET_INSTANCE_ID);
        this.requestedPermissions.addAll(savedInstanceState.getStringArrayList(KEY_REQUESTED_PERMISSIONS));
        this.overriddenPushHandler = ControllerChangeHandler.fromBundle(savedInstanceState.getBundle(KEY_OVERRIDDEN_PUSH_HANDLER));
        this.overriddenPopHandler = ControllerChangeHandler.fromBundle(savedInstanceState.getBundle(KEY_OVERRIDDEN_POP_HANDLER));
        this.needsAttach = savedInstanceState.getBoolean(KEY_NEEDS_ATTACH);
        this.retainViewMode = RetainViewMode.values()[savedInstanceState.getInt(KEY_RETAIN_VIEW_MODE, 0)];
        for (Bundle childBundle : savedInstanceState.getParcelableArrayList(KEY_CHILD_ROUTERS)) {
            ControllerHostedRouter childRouter = new ControllerHostedRouter();
            childRouter.restoreInstanceState(childBundle);
            this.childRouters.add(childRouter);
        }
        this.savedInstanceState = savedInstanceState.getBundle(KEY_SAVED_STATE);
        if (this.savedInstanceState != null) {
            this.savedInstanceState.setClassLoader(getClass().getClassLoader());
        }
        performOnRestoreInstanceState();
    }

    private void performOnRestoreInstanceState() {
        if (this.savedInstanceState != null && this.router != null) {
            onRestoreInstanceState(this.savedInstanceState);
            for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
                lifecycleListener.onRestoreInstanceState(this, this.savedInstanceState);
            }
            this.savedInstanceState = null;
        }
    }

    final void changeStarted(ControllerChangeHandler changeHandler, ControllerChangeType changeType) {
        if (!changeType.isEnter) {
            this.isPerformingExitTransition = true;
            for (ControllerHostedRouter router : this.childRouters) {
                router.setDetachFrozen(true);
            }
        }
        onChangeStarted(changeHandler, changeType);
        for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
            lifecycleListener.onChangeStart(this, changeHandler, changeType);
        }
    }

    final void changeEnded(ControllerChangeHandler changeHandler, ControllerChangeType changeType) {
        if (!changeType.isEnter) {
            this.isPerformingExitTransition = false;
            for (ControllerHostedRouter router : this.childRouters) {
                router.setDetachFrozen(false);
            }
        }
        onChangeEnded(changeHandler, changeType);
        for (LifecycleListener lifecycleListener : new ArrayList(this.lifecycleListeners)) {
            lifecycleListener.onChangeEnd(this, changeHandler, changeType);
        }
        if (this.isBeingDestroyed && !this.viewIsAttached && !this.attached && this.destroyedView != null) {
            View view = (View) this.destroyedView.get();
            if (!(this.router.container == null || view == null || view.getParent() != this.router.container)) {
                this.router.container.removeView(view);
            }
            this.destroyedView = null;
        }
    }

    final void setDetachFrozen(boolean frozen) {
        if (this.isDetachFrozen != frozen) {
            this.isDetachFrozen = frozen;
            for (ControllerHostedRouter router : this.childRouters) {
                router.setDetachFrozen(frozen);
            }
            if (!frozen && this.view != null && this.viewWasDetached) {
                detach(this.view, false, false);
            }
        }
    }

    final void createOptionsMenu(Menu menu, MenuInflater inflater) {
        if (this.attached && this.hasOptionsMenu && !this.optionsMenuHidden) {
            onCreateOptionsMenu(menu, inflater);
        }
    }

    final void prepareOptionsMenu(Menu menu) {
        if (this.attached && this.hasOptionsMenu && !this.optionsMenuHidden) {
            onPrepareOptionsMenu(menu);
        }
    }

    final boolean optionsItemSelected(MenuItem item) {
        return this.attached && this.hasOptionsMenu && !this.optionsMenuHidden && onOptionsItemSelected(item);
    }

    final void setParentController(Controller controller) {
        this.parentController = controller;
    }

    private void ensureRequiredConstructor() {
        Constructor[] constructors = getClass().getConstructors();
        if (getBundleConstructor(constructors) == null && getDefaultConstructor(constructors) == null) {
            throw new RuntimeException(getClass() + " does not have a constructor that takes a Bundle argument or a default constructor. Controllers must have one of these in order to restore their states.");
        }
    }

    private static Constructor getDefaultConstructor(Constructor[] constructors) {
        for (Constructor constructor : constructors) {
            if (constructor.getParameterTypes().length == 0) {
                return constructor;
            }
        }
        return null;
    }

    private static Constructor getBundleConstructor(Constructor[] constructors) {
        for (Constructor constructor : constructors) {
            if (constructor.getParameterTypes().length == 1 && constructor.getParameterTypes()[0] == Bundle.class) {
                return constructor;
            }
        }
        return null;
    }
}
