package com.coinbase.android.dashboard;

final /* synthetic */ class DashboardCurrencyController$$Lambda$6 implements Runnable {
    private final DashboardCurrencyController arg$1;

    private DashboardCurrencyController$$Lambda$6(DashboardCurrencyController dashboardCurrencyController) {
        this.arg$1 = dashboardCurrencyController;
    }

    public static Runnable lambdaFactory$(DashboardCurrencyController dashboardCurrencyController) {
        return new DashboardCurrencyController$$Lambda$6(dashboardCurrencyController);
    }

    public void run() {
        DashboardCurrencyController.lambda$initLayout$5(this.arg$1);
    }
}
