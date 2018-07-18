package com.coinbase.android.dashboard;

import android.content.Context;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.databinding.LayoutDashboardTabPeriodBinding;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class DashboardTabPeriodLayout extends LinearLayout implements DashboardTabPeriodScreen {
    private LayoutDashboardTabPeriodBinding mBinding;
    @Inject
    DashboardTabPeriodPresenter mPresenter;

    public DashboardTabPeriodLayout(Context context) {
        this(context, null);
    }

    public DashboardTabPeriodLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardTabPeriodLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutDashboardTabPeriodBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().addDashboardTabPeriodLayoutSubcomponent(new DashboardTabPeriodPresenterModule(this)).inject(this);
        this.mPresenter.init();
    }

    public void setBackgroundColor(int colorRes) {
        this.mBinding.tbLayout.setBackgroundColor(colorRes);
    }

    public void initTabs(List<TabPeriod> tabPeriodList) {
        if (tabPeriodList != null) {
            for (TabPeriod period : tabPeriodList) {
                Tab tab = this.mBinding.tbLayout.newTab();
                tab.setTag(period);
                tab.setText(getResources().getString(period.getTabTextResourceId()));
                this.mBinding.tbLayout.addTab(tab);
            }
            this.mBinding.tbLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
                public void onTabSelected(Tab tab) {
                    if (tab != null && (tab.getTag() instanceof TabPeriod)) {
                        DashboardTabPeriodLayout.this.mPresenter.onTabSelected((TabPeriod) tab.getTag());
                    }
                }

                public void onTabUnselected(Tab tab) {
                }

                public void onTabReselected(Tab tab) {
                }
            });
        }
    }

    public void initTabPeriod(TabPeriod period) {
        if (period != null) {
            this.mBinding.tbLayout.getTabAt(period.getIndex()).select();
        }
    }
}
