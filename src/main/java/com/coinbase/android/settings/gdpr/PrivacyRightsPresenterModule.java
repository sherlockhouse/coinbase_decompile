package com.coinbase.android.settings.gdpr;

import android.app.Application;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.settings.SettingsPreferenceItem;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.ui.ActionBarController;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import rx.functions.Func0;

public class PrivacyRightsPresenterModule {
    private final ActionBarController mController;
    private final PrivacyRightsScreen mScreen;

    public PrivacyRightsPresenterModule(PrivacyRightsScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public PrivacyRightsScreen providesScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }

    @ControllerScope
    List<Func0<SettingsPreferenceItem>> providesPrefsItemList(Map<Integer, Func0<SettingsPreferenceItem>> prefsItemMap) {
        List<Func0<SettingsPreferenceItem>> itemList = new LinkedList();
        int i = 0;
        while (i < prefsItemMap.size()) {
            if (prefsItemMap.containsKey(Integer.valueOf(i))) {
                itemList.add(i, prefsItemMap.get(Integer.valueOf(i)));
                i++;
            } else {
                throw new IllegalStateException("Map doesn't contain index [" + i + "] should never happen");
            }
        }
        return itemList;
    }

    @ControllerScope
    public Func0<SettingsPreferenceItem> providesPrivacyRightsHeader(Application application) {
        return PrivacyRightsPresenterModule$$Lambda$1.lambdaFactory$(application);
    }

    @ControllerScope
    public Func0<SettingsPreferenceItem> providesRequestDataItem(Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return PrivacyRightsPresenterModule$$Lambda$2.lambdaFactory$(application, itemClickedConnector);
    }

    @ControllerScope
    public Func0<SettingsPreferenceItem> providesRequestDeletion(Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return PrivacyRightsPresenterModule$$Lambda$3.lambdaFactory$(application, itemClickedConnector);
    }

    @ControllerScope
    public Func0<SettingsPreferenceItem> providesRequestExport(Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return PrivacyRightsPresenterModule$$Lambda$4.lambdaFactory$(application, itemClickedConnector);
    }

    @ControllerScope
    public Func0<SettingsPreferenceItem> providesRequestRestrictionItem(Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return PrivacyRightsPresenterModule$$Lambda$5.lambdaFactory$(application, itemClickedConnector);
    }

    @ControllerScope
    public Func0<SettingsPreferenceItem> providesRequestCorrectionItem(Application application, SettingsPreferenceItemClickedConnector itemClickedConnector) {
        return PrivacyRightsPresenterModule$$Lambda$6.lambdaFactory$(application, itemClickedConnector);
    }
}
