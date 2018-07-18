package com.bluelinelabs.conductor;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.Controller.LifecycleListener;
import com.bluelinelabs.conductor.ControllerChangeHandler.ControllerChangeListener;
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler;
import com.bluelinelabs.conductor.internal.NoOpControllerChangeHandler;
import com.bluelinelabs.conductor.internal.ThreadUtils;
import com.bluelinelabs.conductor.internal.TransactionIndexer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class Router {
    final Backstack backstack = new Backstack();
    private final List<ControllerChangeListener> changeListeners = new ArrayList();
    ViewGroup container;
    boolean containerFullyAttached = false;
    final List<Controller> destroyingControllers = new ArrayList();
    private final List<ChangeTransaction> pendingControllerChanges = new ArrayList();
    private boolean popsLastView = false;

    public abstract Activity getActivity();

    abstract Router getRootRouter();

    abstract List<Router> getSiblingRouters();

    abstract TransactionIndexer getTransactionIndexer();

    abstract boolean hasHost();

    abstract void invalidateOptionsMenu();

    abstract void registerForActivityResult(String str, int i);

    abstract void requestPermissions(String str, String[] strArr, int i);

    abstract void startActivity(Intent intent);

    abstract void startActivityForResult(String str, Intent intent, int i);

    abstract void startActivityForResult(String str, Intent intent, int i, Bundle bundle);

    abstract void startIntentSenderForResult(String str, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws SendIntentException;

    abstract void unregisterForActivityResults(String str);

    public void onRequestPermissionsResult(String instanceId, int requestCode, String[] permissions, int[] grantResults) {
        Controller controller = getControllerWithInstanceId(instanceId);
        if (controller != null) {
            controller.requestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public boolean handleBack() {
        ThreadUtils.ensureMainThread();
        if (this.backstack.isEmpty() || (!this.backstack.peek().controller.handleBack() && !popCurrentController())) {
            return false;
        }
        return true;
    }

    public boolean popCurrentController() {
        ThreadUtils.ensureMainThread();
        RouterTransaction transaction = this.backstack.peek();
        if (transaction != null) {
            return popController(transaction.controller);
        }
        throw new IllegalStateException("Trying to pop the current controller when there are none on the backstack.");
    }

    public boolean popController(Controller controller) {
        boolean poppingTopController;
        ThreadUtils.ensureMainThread();
        RouterTransaction topTransaction = this.backstack.peek();
        if (topTransaction == null || topTransaction.controller != controller) {
            poppingTopController = false;
        } else {
            poppingTopController = true;
        }
        if (poppingTopController) {
            trackDestroyingController(this.backstack.pop());
            performControllerChange(this.backstack.peek(), topTransaction, false);
        } else {
            RouterTransaction removedTransaction = null;
            RouterTransaction nextTransaction = null;
            Iterator it = this.backstack.iterator();
            while (it.hasNext()) {
                RouterTransaction transaction = (RouterTransaction) it.next();
                if (transaction.controller == controller) {
                    if (controller.isAttached()) {
                        trackDestroyingController(transaction);
                    }
                    this.backstack.remove(transaction);
                    removedTransaction = transaction;
                } else if (removedTransaction != null) {
                    if (!transaction.controller.isAttached()) {
                        nextTransaction = transaction;
                    }
                    if (removedTransaction != null) {
                        performControllerChange(nextTransaction, removedTransaction, false);
                    }
                }
            }
            if (removedTransaction != null) {
                performControllerChange(nextTransaction, removedTransaction, false);
            }
        }
        if (this.popsLastView) {
            if (topTransaction != null) {
                return true;
            }
            return false;
        } else if (this.backstack.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void pushController(RouterTransaction transaction) {
        ThreadUtils.ensureMainThread();
        RouterTransaction from = this.backstack.peek();
        pushToBackstack(transaction);
        performControllerChange(transaction, from, true);
    }

    public void replaceTopController(RouterTransaction transaction) {
        boolean newHandlerRemovesViews = false;
        ThreadUtils.ensureMainThread();
        RouterTransaction topTransaction = this.backstack.peek();
        if (!this.backstack.isEmpty()) {
            trackDestroyingController(this.backstack.pop());
        }
        ControllerChangeHandler handler = transaction.pushChangeHandler();
        if (topTransaction != null) {
            boolean oldHandlerRemovedViews;
            if (topTransaction.pushChangeHandler() == null || topTransaction.pushChangeHandler().removesFromViewOnPush()) {
                oldHandlerRemovedViews = true;
            } else {
                oldHandlerRemovedViews = false;
            }
            if (handler == null || handler.removesFromViewOnPush()) {
                newHandlerRemovesViews = true;
            }
            if (!oldHandlerRemovedViews && newHandlerRemovesViews) {
                for (RouterTransaction visibleTransaction : getVisibleTransactions(this.backstack.iterator())) {
                    performControllerChange(null, visibleTransaction, true, handler);
                }
            }
        }
        pushToBackstack(transaction);
        if (handler != null) {
            handler.setForceRemoveViewOnPush(true);
        }
        performControllerChange(transaction.pushChangeHandler(handler), topTransaction, true);
    }

    void destroy(boolean popViews) {
        this.popsLastView = true;
        final List<RouterTransaction> poppedControllers = this.backstack.popAll();
        trackDestroyingControllers(poppedControllers);
        if (popViews && poppedControllers.size() > 0) {
            RouterTransaction topTransaction = (RouterTransaction) poppedControllers.get(0);
            topTransaction.controller().addLifecycleListener(new LifecycleListener() {
                public void onChangeEnd(Controller controller, ControllerChangeHandler changeHandler, ControllerChangeType changeType) {
                    if (changeType == ControllerChangeType.POP_EXIT) {
                        for (int i = poppedControllers.size() - 1; i > 0; i--) {
                            Router.this.performControllerChange(null, (RouterTransaction) poppedControllers.get(i), true, new SimpleSwapChangeHandler());
                        }
                    }
                }
            });
            performControllerChange(null, topTransaction, false, topTransaction.popChangeHandler());
        }
    }

    public int getContainerId() {
        return this.container != null ? this.container.getId() : 0;
    }

    public Router setPopsLastView(boolean popsLastView) {
        this.popsLastView = popsLastView;
        return this;
    }

    public boolean popToRoot() {
        ThreadUtils.ensureMainThread();
        return popToRoot(null);
    }

    public boolean popToRoot(ControllerChangeHandler changeHandler) {
        ThreadUtils.ensureMainThread();
        if (this.backstack.size() <= 1) {
            return false;
        }
        popToTransaction(this.backstack.root(), changeHandler);
        return true;
    }

    public boolean popToTag(String tag) {
        ThreadUtils.ensureMainThread();
        return popToTag(tag, null);
    }

    public boolean popToTag(String tag, ControllerChangeHandler changeHandler) {
        ThreadUtils.ensureMainThread();
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            if (tag.equals(transaction.tag())) {
                popToTransaction(transaction, changeHandler);
                return true;
            }
        }
        return false;
    }

    public void setRoot(RouterTransaction transaction) {
        ThreadUtils.ensureMainThread();
        setBackstack(Collections.singletonList(transaction), transaction.pushChangeHandler());
    }

    public Controller getControllerWithInstanceId(String instanceId) {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            Controller controllerWithId = ((RouterTransaction) it.next()).controller.findController(instanceId);
            if (controllerWithId != null) {
                return controllerWithId;
            }
        }
        return null;
    }

    public Controller getControllerWithTag(String tag) {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            if (tag.equals(transaction.tag())) {
                return transaction.controller;
            }
        }
        return null;
    }

    public int getBackstackSize() {
        return this.backstack.size();
    }

    public List<RouterTransaction> getBackstack() {
        List<RouterTransaction> list = new ArrayList();
        Iterator<RouterTransaction> backstackIterator = this.backstack.reverseIterator();
        while (backstackIterator.hasNext()) {
            list.add(backstackIterator.next());
        }
        return list;
    }

    public void setBackstack(List<RouterTransaction> newBackstack, ControllerChangeHandler changeHandler) {
        ThreadUtils.ensureMainThread();
        List<RouterTransaction> oldVisibleTransactions = getVisibleTransactions(this.backstack.iterator());
        boolean newRootRequiresPush = newBackstack.size() <= 0 || !this.backstack.contains((RouterTransaction) newBackstack.get(0));
        removeAllExceptVisibleAndUnowned();
        ensureOrderedTransactionIndices(newBackstack);
        this.backstack.setBackstack(newBackstack);
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            ((RouterTransaction) it.next()).onAttachedToRouter();
        }
        if (newBackstack.size() > 0) {
            RouterTransaction transaction;
            List<RouterTransaction> reverseNewBackstack = new ArrayList(newBackstack);
            Collections.reverse(reverseNewBackstack);
            List<RouterTransaction> newVisibleTransactions = getVisibleTransactions(reverseNewBackstack.iterator());
            if (!backstacksAreEqual(newVisibleTransactions, oldVisibleTransactions)) {
                int i;
                RouterTransaction oldRootTransaction = oldVisibleTransactions.size() > 0 ? (RouterTransaction) oldVisibleTransactions.get(0) : null;
                RouterTransaction newRootTransaction = (RouterTransaction) newVisibleTransactions.get(0);
                if (oldRootTransaction == null || oldRootTransaction.controller != newRootTransaction.controller) {
                    if (oldRootTransaction != null) {
                        ControllerChangeHandler.completeHandlerImmediately(oldRootTransaction.controller.getInstanceId());
                    }
                    performControllerChange(newRootTransaction, oldRootTransaction, newRootRequiresPush, changeHandler);
                }
                for (i = oldVisibleTransactions.size() - 1; i > 0; i--) {
                    transaction = (RouterTransaction) oldVisibleTransactions.get(i);
                    if (!newVisibleTransactions.contains(transaction)) {
                        ControllerChangeHandler localHandler = changeHandler != null ? changeHandler.copy() : new SimpleSwapChangeHandler();
                        localHandler.setForceRemoveViewOnPush(true);
                        ControllerChangeHandler.completeHandlerImmediately(transaction.controller.getInstanceId());
                        performControllerChange(null, transaction, newRootRequiresPush, localHandler);
                    }
                }
                for (i = 1; i < newVisibleTransactions.size(); i++) {
                    transaction = (RouterTransaction) newVisibleTransactions.get(i);
                    if (!oldVisibleTransactions.contains(transaction)) {
                        performControllerChange(transaction, (RouterTransaction) newVisibleTransactions.get(i - 1), true, transaction.pushChangeHandler());
                    }
                }
            }
            for (RouterTransaction transaction2 : newBackstack) {
                transaction2.controller.setRouter(this);
            }
        }
    }

    public boolean hasRootController() {
        return getBackstackSize() > 0;
    }

    public void addChangeListener(ControllerChangeListener changeListener) {
        if (!this.changeListeners.contains(changeListener)) {
            this.changeListeners.add(changeListener);
        }
    }

    public void removeChangeListener(ControllerChangeListener changeListener) {
        this.changeListeners.remove(changeListener);
    }

    public void rebindIfNeeded() {
        ThreadUtils.ensureMainThread();
        Iterator<RouterTransaction> backstackIterator = this.backstack.reverseIterator();
        while (backstackIterator.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) backstackIterator.next();
            if (transaction.controller.getNeedsAttach()) {
                performControllerChange(transaction, null, true, new SimpleSwapChangeHandler(false));
            }
        }
    }

    public final void onActivityResult(String instanceId, int requestCode, int resultCode, Intent data) {
        Controller controller = getControllerWithInstanceId(instanceId);
        if (controller != null) {
            controller.onActivityResult(requestCode, resultCode, data);
        }
    }

    public final void onActivityStarted(Activity activity) {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            transaction.controller.activityStarted(activity);
            for (Router childRouter : transaction.controller.getChildRouters()) {
                childRouter.onActivityStarted(activity);
            }
        }
    }

    public final void onActivityResumed(Activity activity) {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            transaction.controller.activityResumed(activity);
            for (Router childRouter : transaction.controller.getChildRouters()) {
                childRouter.onActivityResumed(activity);
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            transaction.controller.activityPaused(activity);
            for (Router childRouter : transaction.controller.getChildRouters()) {
                childRouter.onActivityPaused(activity);
            }
        }
    }

    public final void onActivityStopped(Activity activity) {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            transaction.controller.activityStopped(activity);
            for (Router childRouter : transaction.controller.getChildRouters()) {
                childRouter.onActivityStopped(activity);
            }
        }
    }

    public void onActivityDestroyed(Activity activity) {
        prepareForContainerRemoval();
        this.changeListeners.clear();
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            transaction.controller.activityDestroyed(activity);
            for (Router childRouter : transaction.controller.getChildRouters()) {
                childRouter.onActivityDestroyed(activity);
            }
        }
        for (int index = this.destroyingControllers.size() - 1; index >= 0; index--) {
            Controller controller = (Controller) this.destroyingControllers.get(index);
            controller.activityDestroyed(activity);
            for (Router childRouter2 : controller.getChildRouters()) {
                childRouter2.onActivityDestroyed(activity);
            }
        }
        this.container = null;
    }

    void prepareForHostDetach() {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            if (ControllerChangeHandler.completeHandlerImmediately(transaction.controller.getInstanceId())) {
                transaction.controller.setNeedsAttach(true);
            }
            transaction.controller.prepareForHostDetach();
        }
    }

    public void saveInstanceState(Bundle outState) {
        prepareForHostDetach();
        Bundle backstackState = new Bundle();
        this.backstack.saveInstanceState(backstackState);
        outState.putParcelable("Router.backstack", backstackState);
        outState.putBoolean("Router.popsLastView", this.popsLastView);
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        this.backstack.restoreInstanceState((Bundle) savedInstanceState.getParcelable("Router.backstack"));
        this.popsLastView = savedInstanceState.getBoolean("Router.popsLastView");
        Iterator<RouterTransaction> backstackIterator = this.backstack.reverseIterator();
        while (backstackIterator.hasNext()) {
            setControllerRouter(((RouterTransaction) backstackIterator.next()).controller);
        }
    }

    public final void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            transaction.controller.createOptionsMenu(menu, inflater);
            for (Router childRouter : transaction.controller.getChildRouters()) {
                childRouter.onCreateOptionsMenu(menu, inflater);
            }
        }
    }

    public final void onPrepareOptionsMenu(Menu menu) {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            transaction.controller.prepareOptionsMenu(menu);
            for (Router childRouter : transaction.controller.getChildRouters()) {
                childRouter.onPrepareOptionsMenu(menu);
            }
        }
    }

    public final boolean onOptionsItemSelected(MenuItem item) {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            if (transaction.controller.optionsItemSelected(item)) {
                return true;
            }
            for (Router childRouter : transaction.controller.getChildRouters()) {
                if (childRouter.onOptionsItemSelected(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void popToTransaction(RouterTransaction transaction, ControllerChangeHandler changeHandler) {
        if (this.backstack.size() > 0) {
            RouterTransaction topTransaction = this.backstack.peek();
            List<RouterTransaction> updatedBackstack = new ArrayList();
            Iterator<RouterTransaction> backstackIterator = this.backstack.reverseIterator();
            while (backstackIterator.hasNext()) {
                RouterTransaction existingTransaction = (RouterTransaction) backstackIterator.next();
                updatedBackstack.add(existingTransaction);
                if (existingTransaction == transaction) {
                    break;
                }
            }
            if (changeHandler == null) {
                changeHandler = topTransaction.popChangeHandler();
            }
            setBackstack(updatedBackstack, changeHandler);
        }
    }

    void watchContainerAttach() {
        this.container.post(new Runnable() {
            public void run() {
                Router.this.containerFullyAttached = true;
            }
        });
    }

    void prepareForContainerRemoval() {
        this.containerFullyAttached = false;
        if (this.container != null) {
            this.container.setOnHierarchyChangeListener(null);
        }
    }

    void onContextAvailable() {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            ((RouterTransaction) it.next()).controller.onContextAvailable();
        }
    }

    final List<Controller> getControllers() {
        List<Controller> controllers = new ArrayList();
        Iterator<RouterTransaction> backstackIterator = this.backstack.reverseIterator();
        while (backstackIterator.hasNext()) {
            controllers.add(((RouterTransaction) backstackIterator.next()).controller);
        }
        return controllers;
    }

    public final Boolean handleRequestedPermission(String permission) {
        Iterator it = this.backstack.iterator();
        while (it.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) it.next();
            if (transaction.controller.didRequestPermission(permission)) {
                return Boolean.valueOf(transaction.controller.shouldShowRequestPermissionRationale(permission));
            }
        }
        return null;
    }

    private void performControllerChange(RouterTransaction to, RouterTransaction from, boolean isPush) {
        ControllerChangeHandler changeHandler;
        if (isPush && to != null) {
            to.onAttachedToRouter();
        }
        if (isPush) {
            changeHandler = to.pushChangeHandler();
        } else if (from != null) {
            changeHandler = from.popChangeHandler();
        } else {
            changeHandler = null;
        }
        performControllerChange(to, from, isPush, changeHandler);
    }

    private void performControllerChange(RouterTransaction to, RouterTransaction from, boolean isPush, ControllerChangeHandler changeHandler) {
        Controller toController;
        Controller fromController = null;
        if (to != null) {
            toController = to.controller;
        } else {
            toController = null;
        }
        if (from != null) {
            fromController = from.controller;
        }
        boolean forceDetachDestroy = false;
        if (to != null) {
            to.ensureValidIndex(getTransactionIndexer());
            setControllerRouter(toController);
        } else if (this.backstack.size() == 0 && !this.popsLastView) {
            changeHandler = new NoOpControllerChangeHandler();
            forceDetachDestroy = true;
        }
        performControllerChange(toController, fromController, isPush, changeHandler);
        if (forceDetachDestroy && fromController != null && fromController.getView() != null) {
            fromController.detach(fromController.getView(), true, false);
        }
    }

    private void performControllerChange(Controller to, Controller from, boolean isPush, ControllerChangeHandler changeHandler) {
        if (isPush && to != null && to.isDestroyed()) {
            throw new IllegalStateException("Trying to push a controller that has already been destroyed. (" + to.getClass().getSimpleName() + ")");
        }
        ChangeTransaction transaction = new ChangeTransaction(to, from, isPush, this.container, changeHandler, this.changeListeners);
        if (this.pendingControllerChanges.size() > 0) {
            this.pendingControllerChanges.add(transaction);
        } else if (from == null || (!(changeHandler == null || changeHandler.removesFromViewOnPush()) || this.containerFullyAttached)) {
            ControllerChangeHandler.executeChange(transaction);
        } else {
            this.pendingControllerChanges.add(transaction);
            this.container.post(new Runnable() {
                public void run() {
                    Router.this.performPendingControllerChanges();
                }
            });
        }
    }

    void performPendingControllerChanges() {
        for (int i = 0; i < this.pendingControllerChanges.size(); i++) {
            ControllerChangeHandler.executeChange((ChangeTransaction) this.pendingControllerChanges.get(i));
        }
        this.pendingControllerChanges.clear();
    }

    protected void pushToBackstack(RouterTransaction entry) {
        this.backstack.push(entry);
    }

    private void trackDestroyingController(RouterTransaction transaction) {
        if (!transaction.controller.isDestroyed()) {
            this.destroyingControllers.add(transaction.controller);
            transaction.controller.addLifecycleListener(new LifecycleListener() {
                public void postDestroy(Controller controller) {
                    Router.this.destroyingControllers.remove(controller);
                }
            });
        }
    }

    private void trackDestroyingControllers(List<RouterTransaction> transactions) {
        for (RouterTransaction transaction : transactions) {
            trackDestroyingController(transaction);
        }
    }

    private void removeAllExceptVisibleAndUnowned() {
        List<View> views = new ArrayList();
        for (RouterTransaction transaction : getVisibleTransactions(this.backstack.iterator())) {
            if (transaction.controller.getView() != null) {
                views.add(transaction.controller.getView());
            }
        }
        for (Router router : getSiblingRouters()) {
            if (router.container == this.container) {
                addRouterViewsToList(router, views);
            }
        }
        int childCount = this.container.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.container.getChildAt(i);
            if (!views.contains(child)) {
                this.container.removeView(child);
            }
        }
    }

    private void ensureOrderedTransactionIndices(List<RouterTransaction> backstack) {
        List<Integer> indices = new ArrayList();
        for (RouterTransaction transaction : backstack) {
            transaction.ensureValidIndex(getTransactionIndexer());
            indices.add(Integer.valueOf(transaction.transactionIndex));
        }
        Collections.sort(indices);
        for (int i = 0; i < backstack.size(); i++) {
            ((RouterTransaction) backstack.get(i)).transactionIndex = ((Integer) indices.get(i)).intValue();
        }
    }

    private void addRouterViewsToList(Router router, List<View> list) {
        for (Controller controller : router.getControllers()) {
            if (controller.getView() != null) {
                list.add(controller.getView());
            }
            for (Router child : controller.getChildRouters()) {
                addRouterViewsToList(child, list);
            }
        }
    }

    private List<RouterTransaction> getVisibleTransactions(Iterator<RouterTransaction> backstackIterator) {
        List<RouterTransaction> transactions = new ArrayList();
        while (backstackIterator.hasNext()) {
            RouterTransaction transaction = (RouterTransaction) backstackIterator.next();
            transactions.add(transaction);
            if (transaction.pushChangeHandler() != null) {
                if (transaction.pushChangeHandler().removesFromViewOnPush()) {
                    break;
                }
            }
            break;
        }
        Collections.reverse(transactions);
        return transactions;
    }

    private boolean backstacksAreEqual(List<RouterTransaction> lhs, List<RouterTransaction> rhs) {
        if (lhs.size() != rhs.size()) {
            return false;
        }
        for (int i = 0; i < rhs.size(); i++) {
            if (((RouterTransaction) rhs.get(i)).controller() != ((RouterTransaction) lhs.get(i)).controller()) {
                return false;
            }
        }
        return true;
    }

    void setControllerRouter(Controller controller) {
        controller.setRouter(this);
        controller.onContextAvailable();
    }
}
