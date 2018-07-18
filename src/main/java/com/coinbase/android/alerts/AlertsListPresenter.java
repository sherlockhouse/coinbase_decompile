package com.coinbase.android.alerts;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.coinbase.android.ControllerScope;
import com.coinbase.api.internal.models.alerts.Data;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class AlertsListPresenter {
    private final List<Data> mAlertsList;
    private final AlertsUtils mAlertsUtils;
    private final AlertsContainerScreen mParentScreen;
    private final AlertsListRouter mRouter;
    private final AlertsListScreen mScreen;

    @Inject
    public AlertsListPresenter(AlertsListScreen screen, List<Data> alertsList, AlertsUtils alertsUtils, AlertsContainerScreen parentScreen, AlertsListRouter router) {
        this.mScreen = screen;
        this.mAlertsList = new LinkedList(alertsList);
        this.mAlertsUtils = alertsUtils;
        this.mParentScreen = parentScreen;
        this.mRouter = router;
    }

    public int getItemCount() {
        return this.mAlertsList.size();
    }

    private void onDismissClicked(Data alertData) {
        if (alertData.getDismissable() != null && alertData.getDismissable().booleanValue()) {
            this.mAlertsUtils.updateDismissed(alertData);
            this.mAlertsList.remove(alertData);
            this.mScreen.notifyDataSetChanged();
            if (this.mAlertsList.isEmpty()) {
                this.mParentScreen.hideAlerts();
            }
        }
    }

    public void onLearnMoreClicked(Uri uri) {
        this.mRouter.showLearnMore(uri);
    }

    public void onBindViewHolder(AlertsListItemScreen itemScreen, int position) {
        Data alertData = (Data) this.mAlertsList.get(position);
        String title = alertData.getTitle();
        String description = alertData.getDescription();
        if (TextUtils.isEmpty(description) && TextUtils.isEmpty(title)) {
            itemScreen.hideSelf();
            return;
        }
        if (TextUtils.isEmpty(description)) {
            itemScreen.setText(title);
        } else {
            itemScreen.setText(description);
        }
        if (alertData.getDismissable() == null || !alertData.getDismissable().booleanValue()) {
            itemScreen.hideDismissButton();
            itemScreen.setOnDismissClickListener(AlertsListPresenter$$Lambda$2.lambdaFactory$());
        } else {
            itemScreen.showDismissButton();
            itemScreen.setOnDismissClickListener(AlertsListPresenter$$Lambda$1.lambdaFactory$(this, alertData));
        }
        Uri uri = getLearnMoreUri(alertData.getUrl());
        if (uri == null) {
            itemScreen.hideLearnMoreButton();
            itemScreen.setOnLearnMoreClickListener(AlertsListPresenter$$Lambda$3.lambdaFactory$());
            return;
        }
        itemScreen.showLearnMoreButton();
        itemScreen.setOnLearnMoreClickListener(AlertsListPresenter$$Lambda$4.lambdaFactory$(this, uri));
    }

    static /* synthetic */ void lambda$onBindViewHolder$1(View v) {
    }

    static /* synthetic */ void lambda$onBindViewHolder$2(View v) {
    }

    Uri getLearnMoreUri(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return Uri.parse(url);
    }
}
