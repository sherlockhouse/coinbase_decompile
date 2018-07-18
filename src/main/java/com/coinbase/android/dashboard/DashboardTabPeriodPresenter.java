package com.coinbase.android.dashboard;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.pricechart.PriceChartPresenter.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class DashboardTabPeriodPresenter {
    private static final TabPeriod DEFAULT_TAB = TabPeriod.DAY;
    private static final List<TabPeriod> TAB_PERIOD_LIST = new ArrayList(Arrays.asList(new TabPeriod[]{TabPeriod.HOUR, TabPeriod.DAY, TabPeriod.WEEK, TabPeriod.MONTH, TabPeriod.YEAR, TabPeriod.ALL}));
    private final DashboardTabPeriodScreen mScreen;
    private final DashboardTabPeriodSelectionConnector mTabPeriodSelectionConnector;

    enum TabPeriod {
        HOUR(0, Period.HOUR, R.string.one_hour),
        DAY(1, Period.DAY, R.string.one_day),
        WEEK(2, Period.WEEK, R.string.one_week),
        MONTH(3, Period.MONTH, R.string.one_month),
        YEAR(4, Period.YEAR, R.string.one_year),
        ALL(5, Period.ALL, R.string.all);
        
        private int mIndex;
        private Period mTabPeriod;
        private int mTabTextResourceId;

        private TabPeriod(int index, Period period, int tabTextResourceId) {
            this.mIndex = index;
            this.mTabPeriod = period;
            this.mTabTextResourceId = tabTextResourceId;
        }

        int getIndex() {
            return this.mIndex;
        }

        Period getPriceChartPeriod() {
            return this.mTabPeriod;
        }

        int getTabTextResourceId() {
            return this.mTabTextResourceId;
        }
    }

    @Inject
    public DashboardTabPeriodPresenter(DashboardTabPeriodScreen screen, DashboardTabPeriodSelectionConnector tabPeriodSelectionConnector) {
        this.mScreen = screen;
        this.mTabPeriodSelectionConnector = tabPeriodSelectionConnector;
    }

    void init() {
        this.mScreen.initTabs(TAB_PERIOD_LIST);
        this.mScreen.initTabPeriod(DEFAULT_TAB);
    }

    void onTabSelected(TabPeriod period) {
        if (period != null) {
            this.mTabPeriodSelectionConnector.get().onNext(period);
        }
    }
}
