package com.coinbase.android.alerts;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.databinding.LayoutAlertsListBinding;
import com.coinbase.api.internal.models.alerts.Data;
import java.util.List;
import javax.inject.Inject;

public class AlertsListLayout extends LinearLayout implements AlertsListScreen {
    private AlertsListAdapter mAlertsListAdapter;
    private LayoutAlertsListBinding mBinding;
    @Inject
    AlertsListPresenter mPresenter;

    public AlertsListLayout(Context context) {
        this(context, null);
    }

    public AlertsListLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlertsListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void show(Context context, List<Data> alerts, AlertsContainerScreen parentScreen) {
        if (this.mBinding == null) {
            this.mBinding = LayoutAlertsListBinding.inflate(LayoutInflater.from(context), this, true);
        }
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().addAlertsListLayoutSubcomponent(new AlertsListPresenterModule(this, alerts, parentScreen)).inject(this);
        this.mAlertsListAdapter = new AlertsListAdapter(this.mPresenter);
        this.mBinding.rvAlerts.setAdapter(this.mAlertsListAdapter);
        this.mBinding.rvAlerts.setLayoutManager(new LinearLayoutManager(context, 1, false));
    }

    public void notifyDataSetChanged() {
        this.mAlertsListAdapter.notifyDataSetChanged();
    }
}
