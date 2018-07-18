package com.coinbase.android.alerts;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AlertsListPresenter$$Lambda$2 implements OnClickListener {
    private static final AlertsListPresenter$$Lambda$2 instance = new AlertsListPresenter$$Lambda$2();

    private AlertsListPresenter$$Lambda$2() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        AlertsListPresenter.lambda$onBindViewHolder$1(view);
    }
}
