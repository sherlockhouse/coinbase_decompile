package com.bluelinelabs.conductor;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler;
import com.bluelinelabs.conductor.internal.ClassUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ControllerChangeHandler {
    private static final Map<String, ChangeHandlerData> inProgressChangeHandlers = new HashMap();
    private boolean forceRemoveViewOnPush;
    private boolean hasBeenUsed;

    public interface ControllerChangeListener {
        void onChangeCompleted(Controller controller, Controller controller2, boolean z, ViewGroup viewGroup, ControllerChangeHandler controllerChangeHandler);

        void onChangeStarted(Controller controller, Controller controller2, boolean z, ViewGroup viewGroup, ControllerChangeHandler controllerChangeHandler);
    }

    public interface ControllerChangeCompletedListener {
        void onChangeCompleted();
    }

    private static class ChangeHandlerData {
        public final ControllerChangeHandler changeHandler;
        public final boolean isPush;

        public ChangeHandlerData(ControllerChangeHandler changeHandler, boolean isPush) {
            this.changeHandler = changeHandler;
            this.isPush = isPush;
        }
    }

    static class ChangeTransaction {
        final ControllerChangeHandler changeHandler;
        final ViewGroup container;
        final Controller from;
        final boolean isPush;
        final List<ControllerChangeListener> listeners;
        final Controller to;

        public ChangeTransaction(Controller to, Controller from, boolean isPush, ViewGroup container, ControllerChangeHandler changeHandler, List<ControllerChangeListener> listeners) {
            this.to = to;
            this.from = from;
            this.isPush = isPush;
            this.container = container;
            this.changeHandler = changeHandler;
            this.listeners = listeners;
        }
    }

    public abstract void performChange(ViewGroup viewGroup, View view, View view2, boolean z, ControllerChangeCompletedListener controllerChangeCompletedListener);

    public ControllerChangeHandler() {
        ensureDefaultConstructor();
    }

    public void saveToBundle(Bundle bundle) {
    }

    public void restoreFromBundle(Bundle bundle) {
    }

    public void onAbortPush(ControllerChangeHandler newHandler, Controller newTop) {
    }

    public void completeImmediately() {
    }

    public ControllerChangeHandler copy() {
        return fromBundle(toBundle());
    }

    public boolean isReusable() {
        return false;
    }

    final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("ControllerChangeHandler.className", getClass().getName());
        Bundle savedState = new Bundle();
        saveToBundle(savedState);
        bundle.putBundle("ControllerChangeHandler.savedState", savedState);
        return bundle;
    }

    private void ensureDefaultConstructor() {
        try {
            getClass().getConstructor(new Class[0]);
        } catch (Exception e) {
            throw new RuntimeException(getClass() + " does not have a default constructor.");
        }
    }

    public static ControllerChangeHandler fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        ControllerChangeHandler changeHandler = (ControllerChangeHandler) ClassUtils.newInstance(bundle.getString("ControllerChangeHandler.className"));
        changeHandler.restoreFromBundle(bundle.getBundle("ControllerChangeHandler.savedState"));
        return changeHandler;
    }

    static boolean completeHandlerImmediately(String controllerInstanceId) {
        ChangeHandlerData changeHandlerData = (ChangeHandlerData) inProgressChangeHandlers.get(controllerInstanceId);
        if (changeHandlerData == null) {
            return false;
        }
        changeHandlerData.changeHandler.completeImmediately();
        inProgressChangeHandlers.remove(controllerInstanceId);
        return true;
    }

    static void abortOrComplete(Controller toAbort, Controller newController, ControllerChangeHandler newChangeHandler) {
        ChangeHandlerData changeHandlerData = (ChangeHandlerData) inProgressChangeHandlers.get(toAbort.getInstanceId());
        if (changeHandlerData != null) {
            if (changeHandlerData.isPush) {
                changeHandlerData.changeHandler.onAbortPush(newChangeHandler, newController);
            } else {
                changeHandlerData.changeHandler.completeImmediately();
            }
            inProgressChangeHandlers.remove(toAbort.getInstanceId());
        }
    }

    static void executeChange(ChangeTransaction transaction) {
        executeChange(transaction.to, transaction.from, transaction.isPush, transaction.container, transaction.changeHandler, transaction.listeners);
    }

    private static void executeChange(Controller to, Controller from, boolean isPush, ViewGroup container, ControllerChangeHandler inHandler, List<ControllerChangeListener> listeners) {
        if (container != null) {
            ControllerChangeHandler handler;
            View toView;
            View fromView;
            if (inHandler == null) {
                handler = new SimpleSwapChangeHandler();
            } else if (!inHandler.hasBeenUsed || inHandler.isReusable()) {
                handler = inHandler;
            } else {
                handler = inHandler.copy();
            }
            handler.hasBeenUsed = true;
            if (from != null) {
                if (isPush) {
                    completeHandlerImmediately(from.getInstanceId());
                } else {
                    abortOrComplete(from, to, handler);
                }
            }
            if (to != null) {
                inProgressChangeHandlers.put(to.getInstanceId(), new ChangeHandlerData(handler, isPush));
            }
            for (ControllerChangeListener listener : listeners) {
                listener.onChangeStarted(to, from, isPush, container, handler);
            }
            final ControllerChangeType toChangeType = isPush ? ControllerChangeType.PUSH_ENTER : ControllerChangeType.POP_ENTER;
            final ControllerChangeType fromChangeType = isPush ? ControllerChangeType.PUSH_EXIT : ControllerChangeType.POP_EXIT;
            if (to != null) {
                toView = to.inflate(container);
                to.changeStarted(handler, toChangeType);
            } else {
                toView = null;
            }
            if (from != null) {
                fromView = from.getView();
                from.changeStarted(handler, fromChangeType);
            } else {
                fromView = null;
            }
            final Controller controller = from;
            final Controller controller2 = to;
            final List<ControllerChangeListener> list = listeners;
            final boolean z = isPush;
            final ViewGroup viewGroup = container;
            handler.performChange(container, fromView, toView, isPush, new ControllerChangeCompletedListener() {
                public void onChangeCompleted() {
                    if (controller != null) {
                        controller.changeEnded(handler, fromChangeType);
                    }
                    if (controller2 != null) {
                        ControllerChangeHandler.inProgressChangeHandlers.remove(controller2.getInstanceId());
                        controller2.changeEnded(handler, toChangeType);
                    }
                    for (ControllerChangeListener listener : list) {
                        listener.onChangeCompleted(controller2, controller, z, viewGroup, handler);
                    }
                    if (handler.forceRemoveViewOnPush && fromView != null) {
                        ViewParent fromParent = fromView.getParent();
                        if (fromParent != null && (fromParent instanceof ViewGroup)) {
                            ((ViewGroup) fromParent).removeView(fromView);
                        }
                    }
                    if (handler.removesFromViewOnPush() && controller != null) {
                        controller.setNeedsAttach(false);
                    }
                }
            });
        }
    }

    public boolean removesFromViewOnPush() {
        return true;
    }

    public void setForceRemoveViewOnPush(boolean force) {
        this.forceRemoveViewOnPush = force;
    }
}
