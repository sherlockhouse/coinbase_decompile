package com.coinbase.android.alerts;

import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AlertsListPresenter$$Lambda$4 implements OnClickListener {
    private final AlertsListPresenter arg$1;
    private final Uri arg$2;

    private AlertsListPresenter$$Lambda$4(AlertsListPresenter alertsListPresenter, Uri uri) {
        this.arg$1 = alertsListPresenter;
        this.arg$2 = uri;
    }

    public static OnClickListener lambdaFactory$(AlertsListPresenter alertsListPresenter, Uri uri) {
        return new AlertsListPresenter$$Lambda$4(alertsListPresenter, uri);
    }

    public void onClick(View view) {
        this.arg$1.onLearnMoreClicked(this.arg$2);
    }
}
