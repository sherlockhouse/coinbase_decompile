package com.coinbase.android.ui;

import android.app.Application;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MystiqueBottomNavigationPresenterModule {
    private final MystiqueBottomNavigationScreen mScreen;

    public MystiqueBottomNavigationPresenterModule(MystiqueBottomNavigationScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public MystiqueBottomNavigationScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public List<NavigationItem> providesBottomNavigationItemList(Application app) {
        return new LinkedList(Arrays.asList(new BottomNavigationItem[]{BottomNavigationItem.builder().setType(Type.DASHBOARD).setTitle(R.string.prices).setIconDisabled(R.drawable.prices_bottom_nav).setIconEnabled(R.drawable.prices_selected_bottom_nav).setIconNotification(R.drawable.prices_bottom_nav).setIconEnabledNotification(R.drawable.prices_selected_bottom_nav).setHasNotificationFunc(MystiqueBottomNavigationPresenterModule$$Lambda$1.lambdaFactory$()).setClearHasNotificationFunc(MystiqueBottomNavigationPresenterModule$$Lambda$2.lambdaFactory$()).build(), BottomNavigationItem.builder().setType(Type.ACCOUNTS).setTitle(R.string.accounts).setIconDisabled(R.drawable.accounts_bottom_nav).setIconEnabled(R.drawable.accounts_selected_bottom_nav).setIconNotification(R.drawable.accounts_bottom_nav).setIconEnabledNotification(R.drawable.accounts_selected_bottom_nav).setHasNotificationFunc(MystiqueBottomNavigationPresenterModule$$Lambda$3.lambdaFactory$()).setClearHasNotificationFunc(MystiqueBottomNavigationPresenterModule$$Lambda$4.lambdaFactory$()).build(), BottomNavigationItem.builder().setType(Type.ALERTS).setTitle(R.string.price_alerts).setIconDisabled(R.drawable.alerts_bottom_nav).setIconEnabled(R.drawable.alerts_selected_bottom_nav).setIconNotification(R.drawable.alerts_notification_bottom_nav).setIconEnabledNotification(R.drawable.alerts_notification_selected_bottom_nav).setHasNotificationFunc(MystiqueBottomNavigationPresenterModule$$Lambda$5.lambdaFactory$(app)).setClearHasNotificationFunc(MystiqueBottomNavigationPresenterModule$$Lambda$6.lambdaFactory$(app)).build(), BottomNavigationItem.builder().setType(Type.MORE).setTitle(R.string.title_account).setIconDisabled(R.drawable.settings_bottom_nav).setIconEnabled(R.drawable.settings_selected_bottom_nav).setIconNotification(R.drawable.settings_bottom_nav).setIconEnabledNotification(R.drawable.settings_selected_bottom_nav).setHasNotificationFunc(MystiqueBottomNavigationPresenterModule$$Lambda$7.lambdaFactory$()).setClearHasNotificationFunc(MystiqueBottomNavigationPresenterModule$$Lambda$8.lambdaFactory$()).build()}));
    }

    static /* synthetic */ void lambda$providesBottomNavigationItemList$1() {
    }

    static /* synthetic */ void lambda$providesBottomNavigationItemList$3() {
    }

    static /* synthetic */ void lambda$providesBottomNavigationItemList$7() {
    }
}
