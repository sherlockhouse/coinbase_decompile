package com.coinbase.android.dashboard;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ListItemDashboardPricechartBinding;
import com.coinbase.android.pricechart.PriceChartData.SpotPrice;
import com.coinbase.android.pricechart.PriceChartPresenter.Period;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.models.currency.Data;
import java.util.List;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

public class DashboardPriceChartListAdapter extends Adapter<ViewHolder> {
    private static final int CHART_FILL_ALPHA = 50;
    private Context mContext;
    private List<Data> mCurrencyList = this.mPresenter.getCurrencyList();
    private DashboardPriceChartListPresenter mPresenter;

    static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private ListItemDashboardPricechartBinding mBinding;
        final PublishSubject<SpotPrice> mSpotPriceUpdatedSubject = PublishSubject.create();
        final CompositeSubscription mSubscription = new CompositeSubscription();

        public ViewHolder(View itemView) {
            super(itemView);
            this.mBinding = (ListItemDashboardPricechartBinding) DataBindingUtil.bind(itemView);
        }

        ListItemDashboardPricechartBinding getBinding() {
            return this.mBinding;
        }
    }

    public DashboardPriceChartListAdapter(Context context, DashboardPriceChartListPresenter presenter) {
        this.mContext = context;
        this.mPresenter = presenter;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dashboard_pricechart, parent, false));
    }

    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.mSubscription.clear();
        holder.getBinding().priceChartLayout.onDestroy();
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItemDashboardPricechartBinding binding = holder.getBinding();
        Data currency = (Data) this.mCurrencyList.get(position);
        binding.tvCurrencyName.setText(currency.getName());
        binding.tvCurrencyCode.setText(currency.getCode());
        binding.priceChartLayout.setTouchEnabled(false);
        binding.priceChartLayout.setCurrencyCode(currency.getCode(), Period.DAY);
        int color = Utils.getColorInt(currency.getColor());
        if (color != -1) {
            Utils.updateBackgroundColorWithAlpha(binding.currencyView, color);
            binding.priceChartLayout.setChartColor(color);
            binding.priceChartLayout.setChartFillAlpha(50);
        }
        binding.cvDashboardCurrency.setOnClickListener(DashboardPriceChartListAdapter$$Lambda$1.lambdaFactory$(this, currency));
        holder.mSubscription.add(holder.mSpotPriceUpdatedSubject.onBackpressureLatest().subscribe(DashboardPriceChartListAdapter$$Lambda$2.lambdaFactory$(this, binding)));
        binding.priceChartLayout.setSpotPriceSubject(holder.mSpotPriceUpdatedSubject);
    }

    private void updateSpotPriceView(ListItemDashboardPricechartBinding binding, SpotPrice spotPrice) {
        if (spotPrice == null) {
            binding.tvCurrentPrice.setText("");
            binding.tvPriceChange.setVisibility(4);
            return;
        }
        binding.tvCurrentPrice.setText(this.mPresenter.getFormattedSpotPrice(spotPrice));
        binding.tvPriceChange.setText(this.mPresenter.getPriceChange(spotPrice));
        int textColor = R.color.dashboard_currency_price_perc_negative_text;
        if (this.mPresenter.isPriceChangePositive(spotPrice)) {
            textColor = R.color.dashboard_currency_price_perc_positive_text;
        }
        binding.tvPriceChange.setTextColor(ContextCompat.getColor(this.mContext, textColor));
    }

    public int getItemCount() {
        return this.mCurrencyList.size();
    }
}
