package com.coinbase.android.ui;

import android.app.Application;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

public class MystiqueBottomNavigationPresenter {
    private final AdapterDelegatesManager<List<NavigationItem>> mAdapterDelegatesManager = new AdapterDelegatesManager();
    private final BottomNavigationConnector mBottomNavigationConnector;
    private final List<NavigationItem> mBottomNavigationItemList;
    private final Map<Type, BottomNavigationItem> mBottomNavigationItemMap;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final MystiqueBottomNavigationScreen mScreen;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public MystiqueBottomNavigationPresenter(MystiqueBottomNavigationScreen screen, List<NavigationItem> bottomNavigationItemList, Application app, LayoutInflater layoutInflater, BottomNavigationConnector bottomNavigationConnector, MixpanelTracking mixpanelTracking, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mBottomNavigationItemList = bottomNavigationItemList;
        this.mBottomNavigationItemMap = createBottomNavigationItemMap(this.mBottomNavigationItemList);
        this.mAdapterDelegatesManager.addDelegate(new MystiqueBottomNavigationAdapter(app, layoutInflater, this, this.mBottomNavigationItemList.size()));
        this.mAdapterDelegatesManager.addDelegate(new MystiqueBottomNavigationModalAdapter(app, layoutInflater, this, this.mBottomNavigationItemList.size()));
        this.mBottomNavigationConnector = bottomNavigationConnector;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMainScheduler = mainScheduler;
    }

    void onShow() {
        this.mSubscription.add(this.mBottomNavigationConnector.get().onBackpressureLatest().distinctUntilChanged().map(MystiqueBottomNavigationPresenter$$Lambda$1.lambdaFactory$(this)).observeOn(this.mMainScheduler).subscribe(MystiqueBottomNavigationPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    void onHide() {
        this.mSubscription.clear();
    }

    public void onSelected(BottomNavigationItem selectedItem) {
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setBottomNavigationItem(selectedItem.getType()).build());
    }

    public void onModalSelected(ModalBottomNavigationItem selectedItem) {
        if (selectedItem.getType().equals(ModalBottomNavigationItem.Type.NEW_BUY)) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_NAVIGATION_BAR_TAPPED_BUY_BUTTON, new String[0]);
        }
        this.mBottomNavigationConnector.getModal().onNext(ModalDestination.builder().setType(selectedItem.getType()).build());
    }

    private void setSelected(BottomNavigationItem selectedItem) {
        for (NavigationItem item : this.mBottomNavigationItemList) {
            if (item instanceof BottomNavigationItem) {
                BottomNavigationItem bottomNavigationItem = (BottomNavigationItem) item;
                if (bottomNavigationItem == selectedItem) {
                    bottomNavigationItem.setDisabled(false);
                    bottomNavigationItem.clearHasNotification();
                } else {
                    bottomNavigationItem.setDisabled(true);
                }
            }
        }
        this.mScreen.notifyAdapterDataSetChanged();
    }

    int getBottomNavigationItemCount() {
        return this.mBottomNavigationItemList.size();
    }

    int getBottomNavigationItemViewType(int position) {
        return this.mAdapterDelegatesManager.getItemViewType(this.mBottomNavigationItemList, position);
    }

    ViewHolder onCreateBottomNavigationItemViewHolder(ViewGroup parent, int viewType) {
        return this.mAdapterDelegatesManager.onCreateViewHolder(parent, viewType);
    }

    void onBindBottomNavigationItemViewHolder(ViewHolder holder, int position) {
        this.mAdapterDelegatesManager.onBindViewHolder(this.mBottomNavigationItemList, position, holder);
    }

    private Map<Type, BottomNavigationItem> createBottomNavigationItemMap(List<NavigationItem> list) {
        Map<Type, BottomNavigationItem> bottomNavigationItemMap = new HashMap();
        for (NavigationItem item : list) {
            if (item instanceof BottomNavigationItem) {
                BottomNavigationItem bottomNavigationItem = (BottomNavigationItem) item;
                bottomNavigationItemMap.put(bottomNavigationItem.getType(), bottomNavigationItem);
            }
        }
        return bottomNavigationItemMap;
    }
}
