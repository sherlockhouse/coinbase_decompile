package com.coinbase.android.alerts;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AlertsListPresenter$$Lambda$3 implements OnClickListener {
    private static final AlertsListPresenter$$Lambda$3 instance = new AlertsListPresenter$$Lambda$3();

    private AlertsListPresenter$$Lambda$3() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        AlertsListPresenter.lambda$onBindViewHolder$2(view);
    }
}
