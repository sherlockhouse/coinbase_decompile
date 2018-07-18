package com.coinbase.android.dashboard;

import java.util.List;

public interface DashboardTabPeriodScreen {
    void initTabPeriod(TabPeriod tabPeriod);

    void initTabs(List<TabPeriod> list);
}
