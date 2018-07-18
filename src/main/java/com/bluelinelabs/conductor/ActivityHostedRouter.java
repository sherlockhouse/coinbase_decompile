package com.bluelinelabs.conductor;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.ControllerChangeHandler.ControllerChangeListener;
import com.bluelinelabs.conductor.internal.LifecycleHandler;
import com.bluelinelabs.conductor.internal.TransactionIndexer;
import java.util.List;

public class ActivityHostedRouter extends Router {
    private LifecycleHandler lifecycleHandler;
    private final TransactionIndexer transactionIndexer = new TransactionIndexer();

    public final void setHost(LifecycleHandler lifecycleHandler, ViewGroup container) {
        if (this.lifecycleHandler != lifecycleHandler || this.container != container) {
            if (this.container != null && (this.container instanceof ControllerChangeListener)) {
                removeChangeListener((ControllerChangeListener) this.container);
            }
            if (container instanceof ControllerChangeListener) {
                addChangeListener((ControllerChangeListener) container);
            }
            this.lifecycleHandler = lifecycleHandler;
            this.container = container;
            watchContainerAttach();
        }
    }

    public void saveInstanceState(Bundle outState) {
        super.saveInstanceState(outState);
        this.transactionIndexer.saveInstanceState(outState);
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        super.restoreInstanceState(savedInstanceState);
        this.transactionIndexer.restoreInstanceState(savedInstanceState);
    }

    public Activity getActivity() {
        return this.lifecycleHandler != null ? this.lifecycleHandler.getLifecycleActivity() : null;
    }

    public void onActivityDestroyed(Activity activity) {
        super.onActivityDestroyed(activity);
        this.lifecycleHandler = null;
    }

    public final void invalidateOptionsMenu() {
        if (this.lifecycleHandler != null && this.lifecycleHandler.getFragmentManager() != null) {
            this.lifecycleHandler.getFragmentManager().invalidateOptionsMenu();
        }
    }

    void startActivity(Intent intent) {
        this.lifecycleHandler.startActivity(intent);
    }

    void startActivityForResult(String instanceId, Intent intent, int requestCode) {
        this.lifecycleHandler.startActivityForResult(instanceId, intent, requestCode);
    }

    void startActivityForResult(String instanceId, Intent intent, int requestCode, Bundle options) {
        this.lifecycleHandler.startActivityForResult(instanceId, intent, requestCode, options);
    }

    void startIntentSenderForResult(String instanceId, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws SendIntentException {
        this.lifecycleHandler.startIntentSenderForResult(instanceId, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    void registerForActivityResult(String instanceId, int requestCode) {
        this.lifecycleHandler.registerForActivityResult(instanceId, requestCode);
    }

    void unregisterForActivityResults(String instanceId) {
        this.lifecycleHandler.unregisterForActivityResults(instanceId);
    }

    void requestPermissions(String instanceId, String[] permissions, int requestCode) {
        this.lifecycleHandler.requestPermissions(instanceId, permissions, requestCode);
    }

    boolean hasHost() {
        return this.lifecycleHandler != null;
    }

    List<Router> getSiblingRouters() {
        return this.lifecycleHandler.getRouters();
    }

    Router getRootRouter() {
        return this;
    }

    TransactionIndexer getTransactionIndexer() {
        return this.transactionIndexer;
    }

    public void onContextAvailable() {
        super.onContextAvailable();
    }
}
