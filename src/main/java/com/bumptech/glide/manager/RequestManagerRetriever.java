package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RequestManagerRetriever implements Callback {
    private static final RequestManagerFactory DEFAULT_FACTORY = new RequestManagerFactory() {
        public RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode) {
            return new RequestManager(glide, lifecycle, requestManagerTreeNode);
        }
    };
    private volatile RequestManager applicationManager;
    private final RequestManagerFactory factory;
    private final Handler handler;
    final Map<FragmentManager, RequestManagerFragment> pendingRequestManagerFragments = new HashMap();
    final Map<android.support.v4.app.FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments = new HashMap();
    private final Bundle tempBundle = new Bundle();
    private final ArrayMap<View, Fragment> tempViewToFragment = new ArrayMap();
    private final ArrayMap<View, android.support.v4.app.Fragment> tempViewToSupportFragment = new ArrayMap();

    public interface RequestManagerFactory {
        RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode);
    }

    public RequestManagerRetriever(RequestManagerFactory factory) {
        if (factory == null) {
            factory = DEFAULT_FACTORY;
        }
        this.factory = factory;
        this.handler = new Handler(Looper.getMainLooper(), this);
    }

    private RequestManager getApplicationManager(Context context) {
        if (this.applicationManager == null) {
            synchronized (this) {
                if (this.applicationManager == null) {
                    this.applicationManager = this.factory.build(Glide.get(context), new ApplicationLifecycle(), new EmptyRequestManagerTreeNode());
                }
            }
        }
        return this.applicationManager;
    }

    public RequestManager get(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (Util.isOnMainThread() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return get((FragmentActivity) context);
            }
            if (context instanceof Activity) {
                return get((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                return get(((ContextWrapper) context).getBaseContext());
            }
        }
        return getApplicationManager(context);
    }

    public RequestManager get(FragmentActivity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        }
        assertNotDestroyed(activity);
        return supportFragmentGet(activity, activity.getSupportFragmentManager(), null);
    }

    public RequestManager get(android.support.v4.app.Fragment fragment) {
        Preconditions.checkNotNull(fragment.getActivity(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getActivity().getApplicationContext());
        }
        return supportFragmentGet(fragment.getActivity(), fragment.getChildFragmentManager(), fragment);
    }

    public RequestManager get(Activity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        }
        assertNotDestroyed(activity);
        return fragmentGet(activity, activity.getFragmentManager(), null);
    }

    public RequestManager get(View view) {
        if (Util.isOnBackgroundThread()) {
            return get(view.getContext().getApplicationContext());
        }
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(view.getContext(), "Unable to obtain a request manager for a view without a Context");
        Activity activity = findActivity(view.getContext());
        if (activity == null) {
            return get(view.getContext().getApplicationContext());
        }
        if (activity instanceof FragmentActivity) {
            android.support.v4.app.Fragment fragment = findSupportFragment(view, (FragmentActivity) activity);
            if (fragment == null) {
                return get(activity);
            }
            return get(fragment);
        }
        Fragment fragment2 = findFragment(view, activity);
        if (fragment2 == null) {
            return get(activity);
        }
        return get(fragment2);
    }

    private static void findAllSupportFragmentsWithViews(Collection<android.support.v4.app.Fragment> topLevelFragments, Map<View, android.support.v4.app.Fragment> result) {
        if (topLevelFragments != null) {
            for (android.support.v4.app.Fragment fragment : topLevelFragments) {
                if (!(fragment == null || fragment.getView() == null)) {
                    result.put(fragment.getView(), fragment);
                    findAllSupportFragmentsWithViews(fragment.getChildFragmentManager().getFragments(), result);
                }
            }
        }
    }

    private android.support.v4.app.Fragment findSupportFragment(View target, FragmentActivity activity) {
        this.tempViewToSupportFragment.clear();
        findAllSupportFragmentsWithViews(activity.getSupportFragmentManager().getFragments(), this.tempViewToSupportFragment);
        android.support.v4.app.Fragment result = null;
        View activityRoot = activity.findViewById(16908290);
        View current = target;
        while (!current.equals(activityRoot)) {
            result = (android.support.v4.app.Fragment) this.tempViewToSupportFragment.get(current);
            if (result != null || !(current.getParent() instanceof View)) {
                break;
            }
            current = (View) current.getParent();
        }
        this.tempViewToSupportFragment.clear();
        return result;
    }

    private Fragment findFragment(View target, Activity activity) {
        this.tempViewToFragment.clear();
        findAllFragmentsWithViews(activity.getFragmentManager(), this.tempViewToFragment);
        Fragment result = null;
        View activityRoot = activity.findViewById(16908290);
        View current = target;
        while (!current.equals(activityRoot)) {
            result = (Fragment) this.tempViewToFragment.get(current);
            if (result != null || !(current.getParent() instanceof View)) {
                break;
            }
            current = (View) current.getParent();
        }
        this.tempViewToFragment.clear();
        return result;
    }

    private void findAllFragmentsWithViews(FragmentManager fragmentManager, ArrayMap<View, Fragment> result) {
        int index = 0;
        while (true) {
            int index2 = index + 1;
            this.tempBundle.putInt("key", index);
            Fragment fragment = null;
            try {
                fragment = fragmentManager.getFragment(this.tempBundle, "i");
            } catch (Exception e) {
            }
            if (fragment != null) {
                if (fragment.getView() != null) {
                    result.put(fragment.getView(), fragment);
                    if (VERSION.SDK_INT >= 17) {
                        findAllFragmentsWithViews(fragment.getChildFragmentManager(), result);
                    }
                }
                index = index2;
            } else {
                return;
            }
        }
    }

    private Activity findActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    @TargetApi(17)
    private static void assertNotDestroyed(Activity activity) {
        if (VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @TargetApi(17)
    public RequestManager get(Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (Util.isOnBackgroundThread() || VERSION.SDK_INT < 17) {
            return get(fragment.getActivity().getApplicationContext());
        } else {
            return fragmentGet(fragment.getActivity(), fragment.getChildFragmentManager(), fragment);
        }
    }

    @TargetApi(17)
    RequestManagerFragment getRequestManagerFragment(FragmentManager fm, Fragment parentHint) {
        RequestManagerFragment current = (RequestManagerFragment) fm.findFragmentByTag("com.bumptech.glide.manager");
        if (current != null) {
            return current;
        }
        current = (RequestManagerFragment) this.pendingRequestManagerFragments.get(fm);
        if (current != null) {
            return current;
        }
        current = new RequestManagerFragment();
        current.setParentFragmentHint(parentHint);
        this.pendingRequestManagerFragments.put(fm, current);
        fm.beginTransaction().add(current, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.handler.obtainMessage(1, fm).sendToTarget();
        return current;
    }

    private RequestManager fragmentGet(Context context, FragmentManager fm, Fragment parentHint) {
        RequestManagerFragment current = getRequestManagerFragment(fm, parentHint);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager != null) {
            return requestManager;
        }
        requestManager = this.factory.build(Glide.get(context), current.getGlideLifecycle(), current.getRequestManagerTreeNode());
        current.setRequestManager(requestManager);
        return requestManager;
    }

    SupportRequestManagerFragment getSupportRequestManagerFragment(android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment parentHint) {
        SupportRequestManagerFragment current = (SupportRequestManagerFragment) fm.findFragmentByTag("com.bumptech.glide.manager");
        if (current != null) {
            return current;
        }
        current = (SupportRequestManagerFragment) this.pendingSupportRequestManagerFragments.get(fm);
        if (current != null) {
            return current;
        }
        current = new SupportRequestManagerFragment();
        current.setParentFragmentHint(parentHint);
        this.pendingSupportRequestManagerFragments.put(fm, current);
        fm.beginTransaction().add(current, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.handler.obtainMessage(2, fm).sendToTarget();
        return current;
    }

    private RequestManager supportFragmentGet(Context context, android.support.v4.app.FragmentManager fm, android.support.v4.app.Fragment parentHint) {
        SupportRequestManagerFragment current = getSupportRequestManagerFragment(fm, parentHint);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager != null) {
            return requestManager;
        }
        requestManager = this.factory.build(Glide.get(context), current.getGlideLifecycle(), current.getRequestManagerTreeNode());
        current.setRequestManager(requestManager);
        return requestManager;
    }

    public boolean handleMessage(Message message) {
        boolean handled = true;
        Object removed = null;
        Object key = null;
        switch (message.what) {
            case 1:
                FragmentManager fm = message.obj;
                FragmentManager key2 = fm;
                removed = this.pendingRequestManagerFragments.remove(fm);
                break;
            case 2:
                android.support.v4.app.FragmentManager supportFm = message.obj;
                android.support.v4.app.FragmentManager key3 = supportFm;
                removed = this.pendingSupportRequestManagerFragments.remove(supportFm);
                break;
            default:
                handled = false;
                break;
        }
        if (handled && r3 == null && Log.isLoggable("RMRetriever", 5)) {
            Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + key);
        }
        return handled;
    }
}
