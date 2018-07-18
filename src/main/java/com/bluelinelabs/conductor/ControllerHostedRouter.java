package com.bluelinelabs.conductor;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.ControllerChangeHandler.ControllerChangeListener;
import com.bluelinelabs.conductor.internal.TransactionIndexer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ControllerHostedRouter extends Router {
    private final String KEY_HOST_ID = "ControllerHostedRouter.hostId";
    private final String KEY_TAG = "ControllerHostedRouter.tag";
    private Controller hostController;
    private int hostId;
    private boolean isDetachFrozen;
    private String tag;

    ControllerHostedRouter() {
    }

    ControllerHostedRouter(int hostId, String tag) {
        this.hostId = hostId;
        this.tag = tag;
    }

    final void setHost(Controller controller, ViewGroup container) {
        if (this.hostController != controller || this.container != container) {
            removeHost();
            if (container instanceof ControllerChangeListener) {
                addChangeListener((ControllerChangeListener) container);
            }
            this.hostController = controller;
            this.container = container;
            Iterator it = this.backstack.iterator();
            while (it.hasNext()) {
                ((RouterTransaction) it.next()).controller.setParentController(controller);
            }
            watchContainerAttach();
        }
    }

    final void removeHost() {
        if (this.container != null && (this.container instanceof ControllerChangeListener)) {
            removeChangeListener((ControllerChangeListener) this.container);
        }
        for (Controller controller : new ArrayList(this.destroyingControllers)) {
            if (controller.getView() != null) {
                controller.detach(controller.getView(), true, false);
            }
        }
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            if (transaction.controller.getView() != null) {
                transaction.controller.detach(transaction.controller.getView(), true, false);
            }
        }
        prepareForContainerRemoval();
        this.hostController = null;
        this.container = null;
    }

    final void setDetachFrozen(boolean frozen) {
        this.isDetachFrozen = frozen;
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            ((RouterTransaction) it.next()).controller.setDetachFrozen(frozen);
        }
    }

    void destroy(boolean popViews) {
        setDetachFrozen(false);
        super.destroy(popViews);
    }

    protected void pushToBackstack(RouterTransaction entry) {
        if (this.isDetachFrozen) {
            entry.controller.setDetachFrozen(true);
        }
        super.pushToBackstack(entry);
    }

    public void setBackstack(List<RouterTransaction> newBackstack, ControllerChangeHandler changeHandler) {
        if (this.isDetachFrozen) {
            for (RouterTransaction transaction : newBackstack) {
                transaction.controller.setDetachFrozen(true);
            }
        }
        super.setBackstack(newBackstack, changeHandler);
    }

    public Activity getActivity() {
        return this.hostController != null ? this.hostController.getActivity() : null;
    }

    public void onActivityDestroyed(Activity activity) {
        super.onActivityDestroyed(activity);
        removeHost();
    }

    public void invalidateOptionsMenu() {
        if (this.hostController != null && this.hostController.getRouter() != null) {
            this.hostController.getRouter().invalidateOptionsMenu();
        }
    }

    void startActivity(Intent intent) {
        if (this.hostController != null && this.hostController.getRouter() != null) {
            this.hostController.getRouter().startActivity(intent);
        }
    }

    void startActivityForResult(String instanceId, Intent intent, int requestCode) {
        if (this.hostController != null && this.hostController.getRouter() != null) {
            this.hostController.getRouter().startActivityForResult(instanceId, intent, requestCode);
        }
    }

    void startActivityForResult(String instanceId, Intent intent, int requestCode, Bundle options) {
        if (this.hostController != null && this.hostController.getRouter() != null) {
            this.hostController.getRouter().startActivityForResult(instanceId, intent, requestCode, options);
        }
    }

    void startIntentSenderForResult(String instanceId, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws SendIntentException {
        if (this.hostController != null && this.hostController.getRouter() != null) {
            this.hostController.getRouter().startIntentSenderForResult(instanceId, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
        }
    }

    void registerForActivityResult(String instanceId, int requestCode) {
        if (this.hostController != null && this.hostController.getRouter() != null) {
            this.hostController.getRouter().registerForActivityResult(instanceId, requestCode);
        }
    }

    void unregisterForActivityResults(String instanceId) {
        if (this.hostController != null && this.hostController.getRouter() != null) {
            this.hostController.getRouter().unregisterForActivityResults(instanceId);
        }
    }

    void requestPermissions(String instanceId, String[] permissions, int requestCode) {
        if (this.hostController != null && this.hostController.getRouter() != null) {
            this.hostController.getRouter().requestPermissions(instanceId, permissions, requestCode);
        }
    }

    boolean hasHost() {
        return this.hostController != null;
    }

    public void saveInstanceState(Bundle outState) {
        super.saveInstanceState(outState);
        outState.putInt("ControllerHostedRouter.hostId", this.hostId);
        outState.putString("ControllerHostedRouter.tag", this.tag);
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        super.restoreInstanceState(savedInstanceState);
        this.hostId = savedInstanceState.getInt("ControllerHostedRouter.hostId");
        this.tag = savedInstanceState.getString("ControllerHostedRouter.tag");
    }

    void setControllerRouter(Controller controller) {
        super.setControllerRouter(controller);
        controller.setParentController(this.hostController);
    }

    int getHostId() {
        return this.hostId;
    }

    String getTag() {
        return this.tag;
    }

    List<Router> getSiblingRouters() {
        List<Router> list = new ArrayList();
        list.addAll(this.hostController.getChildRouters());
        list.addAll(this.hostController.getRouter().getSiblingRouters());
        return list;
    }

    Router getRootRouter() {
        if (this.hostController == null || this.hostController.getRouter() == null) {
            return this;
        }
        return this.hostController.getRouter().getRootRouter();
    }

    TransactionIndexer getTransactionIndexer() {
        return getRootRouter().getTransactionIndexer();
    }
}
