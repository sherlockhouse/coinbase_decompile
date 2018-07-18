package com.coinbase.android.ui;

import android.content.Context;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.databinding.LayoutCurrencyTabBinding;
import com.coinbase.api.internal.models.currency.Data;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class CurrencyTabLayout extends LinearLayout implements CurrencyTabScreen {
    private LayoutCurrencyTabBinding mBinding;
    @Inject
    CurrencyTabPresenter mPresenter;

    public CurrencyTabLayout(Context context) {
        this(context, null);
    }

    public CurrencyTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CurrencyTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutCurrencyTabBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().addCurrencyTabLayoutSubcomponent(new CurrencyTabPresenterModule(this)).inject(this);
    }

    public void initTabLayout(CurrencyTabFilter filter) {
        this.mPresenter.init(filter);
    }

    public void onShow() {
        this.mPresenter.onShow();
    }

    public void onHide() {
        this.mPresenter.onHide();
    }

    public void populateTabLayout(List<Data> currencyList) {
        if (currencyList != null) {
            Data previousCurrency = this.mPresenter.getLastSelectedCurrency();
            int i = 0;
            Tab selectedTab = null;
            this.mBinding.tbLayout.removeAllTabs();
            for (Data currency : currencyList) {
                Tab tab = this.mBinding.tbLayout.newTab();
                tab.setTag(currency);
                tab.setText(currency.getCode());
                if (previousCurrency != null && TextUtils.equals(currency.getCode(), previousCurrency.getCode())) {
                    selectedTab = tab;
                }
                i++;
                this.mBinding.tbLayout.addTab(tab);
            }
            this.mBinding.tbLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
                public void onTabSelected(Tab tab) {
                    CurrencyTabLayout.this.setSelectedTab(tab);
                }

                public void onTabUnselected(Tab tab) {
                }

                public void onTabReselected(Tab tab) {
                }
            });
            if (selectedTab == null) {
                selectedTab = this.mBinding.tbLayout.getTabAt(0);
            }
            if (selectedTab != null) {
                selectedTab.select();
                setSelectedTab(selectedTab);
            }
        }
    }

    private void setSelectedTab(Tab tab) {
        if (tab != null && (tab.getTag() instanceof Data)) {
            this.mPresenter.onTabSelected((Data) tab.getTag());
        }
    }
}
