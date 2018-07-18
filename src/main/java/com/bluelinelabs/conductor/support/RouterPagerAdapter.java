package com.bluelinelabs.conductor.support;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.coinbase.android.utils.CryptoUri;
import java.util.ArrayList;

public abstract class RouterPagerAdapter extends PagerAdapter {
    private static final String KEY_MAX_PAGES_TO_STATE_SAVE = "RouterPagerAdapter.maxPagesToStateSave";
    private static final String KEY_SAVED_PAGES = "RouterPagerAdapter.savedStates";
    private static final String KEY_SAVE_PAGE_HISTORY = "RouterPagerAdapter.savedPageHistory";
    private final Controller host;
    private int maxPagesToStateSave = Integer.MAX_VALUE;
    private ArrayList<Integer> savedPageHistory = new ArrayList();
    private SparseArray<Bundle> savedPages = new SparseArray();
    private SparseArray<Router> visibleRouters = new SparseArray();

    public abstract void configureRouter(Router router, int i);

    public RouterPagerAdapter(Controller host) {
        this.host = host;
    }

    public void setMaxPagesToStateSave(int maxPagesToStateSave) {
        if (maxPagesToStateSave < 0) {
            throw new IllegalArgumentException("Only positive integers may be passed for maxPagesToStateSave.");
        }
        this.maxPagesToStateSave = maxPagesToStateSave;
        ensurePagesSaved();
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Router router = this.host.getChildRouter(container, makeRouterName(container.getId(), getItemId(position)));
        if (!router.hasRootController()) {
            Bundle routerSavedState = (Bundle) this.savedPages.get(position);
            if (routerSavedState != null) {
                router.restoreInstanceState(routerSavedState);
                this.savedPages.remove(position);
            }
        }
        router.rebindIfNeeded();
        configureRouter(router, position);
        this.visibleRouters.put(position, router);
        return router;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        Router router = (Router) object;
        Bundle savedState = new Bundle();
        router.saveInstanceState(savedState);
        this.savedPages.put(position, savedState);
        this.savedPageHistory.remove(Integer.valueOf(position));
        this.savedPageHistory.add(Integer.valueOf(position));
        ensurePagesSaved();
        this.host.removeChildRouter(router);
        this.visibleRouters.remove(position);
    }

    public boolean isViewFromObject(View view, Object object) {
        for (RouterTransaction transaction : ((Router) object).getBackstack()) {
            if (transaction.controller().getView() == view) {
                return true;
            }
        }
        return false;
    }

    public Parcelable saveState() {
        Bundle bundle = new Bundle();
        bundle.putSparseParcelableArray(KEY_SAVED_PAGES, this.savedPages);
        bundle.putInt(KEY_MAX_PAGES_TO_STATE_SAVE, this.maxPagesToStateSave);
        bundle.putIntegerArrayList(KEY_SAVE_PAGE_HISTORY, this.savedPageHistory);
        return bundle;
    }

    public void restoreState(Parcelable state, ClassLoader loader) {
        Bundle bundle = (Bundle) state;
        if (state != null) {
            this.savedPages = bundle.getSparseParcelableArray(KEY_SAVED_PAGES);
            this.maxPagesToStateSave = bundle.getInt(KEY_MAX_PAGES_TO_STATE_SAVE);
            this.savedPageHistory = bundle.getIntegerArrayList(KEY_SAVE_PAGE_HISTORY);
        }
    }

    public Router getRouter(int position) {
        return (Router) this.visibleRouters.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    SparseArray<Bundle> getSavedPages() {
        return this.savedPages;
    }

    private void ensurePagesSaved() {
        while (this.savedPages.size() > this.maxPagesToStateSave) {
            this.savedPages.remove(((Integer) this.savedPageHistory.remove(0)).intValue());
        }
    }

    private static String makeRouterName(int viewId, long id) {
        return viewId + CryptoUri.URI_SCHEME_DELIMETER + id;
    }
}
