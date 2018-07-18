package com.coinbase.android.alerts;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.api.internal.models.alerts.Data;

final /* synthetic */ class AlertsListPresenter$$Lambda$1 implements OnClickListener {
    private final AlertsListPresenter arg$1;
    private final Data arg$2;

    private AlertsListPresenter$$Lambda$1(AlertsListPresenter alertsListPresenter, Data data) {
        this.arg$1 = alertsListPresenter;
        this.arg$2 = data;
    }

    public static OnClickListener lambdaFactory$(AlertsListPresenter alertsListPresenter, Data data) {
        return new AlertsListPresenter$$Lambda$1(alertsListPresenter, data);
    }

    public void onClick(View view) {
        this.arg$1.onDismissClicked(this.arg$2);
    }
}
