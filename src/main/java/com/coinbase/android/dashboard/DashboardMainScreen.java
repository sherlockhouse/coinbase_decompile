package com.coinbase.android.dashboard;

import com.coinbase.android.alerts.AlertsContainerScreen;
import com.coinbase.api.internal.models.currency.Data;
import java.util.List;

public interface DashboardMainScreen extends AlertsContainerScreen {
    int getThemeColor();

    void gotoDashboardCurrency(Data data);

    boolean isShowingNewUser();

    void refreshPriceCharts();

    void showAlertsNewUser(List<com.coinbase.api.internal.models.alerts.Data> list);

    void showAlertsPortfolio(List<com.coinbase.api.internal.models.alerts.Data> list);

    void showFirstBuyHeader();

    void showPortfolioBalanceHeader();

    void updatePortfolioBalanceHeader(String str);
}
