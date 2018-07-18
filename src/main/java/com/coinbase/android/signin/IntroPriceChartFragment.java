package com.coinbase.android.signin;

import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.coinbase.android.ActivityProvider;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.BaseActivityModule;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentPricechartBinding;
import com.coinbase.android.pricechart.PriceChartData.HighlightedPrice;
import com.coinbase.android.pricechart.PriceChartData.SpotPrice;
import com.coinbase.android.pricechart.PriceChartPresenter.Period;
import com.coinbase.android.utils.MoneyUtils.Currency;
import com.coinbase.android.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import org.joda.money.CurrencyUnit;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class IntroPriceChartFragment extends Fragment implements IntroPriceChartScreen {
    private static final int FILL_CHART_ALPHA = 0;
    private static final int HIT_AREA_SIZE = 20;
    private static final String TRANSLATION_Y = "translationY";
    private List<TextView> baseCurrencyViews;
    private FragmentPricechartBinding mBinding;
    private final PublishSubject<CurrencyUnit> mCurrencyUnitPublishSubject = PublishSubject.create();
    private final PublishSubject<HighlightedPrice> mHighlightedPricePublishSubject = PublishSubject.create();
    private final PublishSubject<Boolean> mMotionSubject = PublishSubject.create();
    @Inject
    IntroPriceChartPresenter mPresenter;
    private TextView[] mRangeViews;
    private final PublishSubject<SpotPrice> mSpotPriceUpdatedSubject = PublishSubject.create();
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    private class BaseCurrencyOnClickListener implements OnClickListener {
        private Currency mBaseCurrency;

        BaseCurrencyOnClickListener(Currency baseCurrency) {
            this.mBaseCurrency = baseCurrency;
        }

        public void onClick(View v) {
            if (!v.isSelected()) {
                IntroPriceChartFragment.this.mPresenter.updateBaseCurrency(this.mBaseCurrency);
            }
        }
    }

    private class RangeOnClickListener implements OnClickListener {
        private Period mPeriod;

        RangeOnClickListener(Period period) {
            this.mPeriod = period;
        }

        public void onClick(View v) {
            if (!v.isSelected()) {
                for (TextView view : IntroPriceChartFragment.this.mRangeViews) {
                    if (view.isSelected()) {
                        view.setSelected(false);
                    }
                }
                v.setSelected(true);
                IntroPriceChartFragment.this.mPresenter.setPeriod(this.mPeriod);
            }
        }
    }

    public static class TouchDelegateComposite extends TouchDelegate {
        private static final Rect emptyRect = new Rect();
        private final List<TouchDelegate> delegates = new ArrayList();

        public TouchDelegateComposite(View view) {
            super(emptyRect, view);
        }

        public void addDelegate(TouchDelegate delegate) {
            if (delegate != null) {
                this.delegates.add(delegate);
            }
        }

        public boolean onTouchEvent(MotionEvent event) {
            boolean res = false;
            float x = event.getX();
            float y = event.getY();
            for (TouchDelegate delegate : this.delegates) {
                event.setLocation(x, y);
                res = delegate.onTouchEvent(event) || res;
            }
            return res;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentPricechartBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_pricechart, container, false);
        ((ComponentProvider) getActivity().getApplicationContext()).applicationComponent().coinbaseActivitySubcomponent(new BaseActivityModule((ActivityProvider) getActivity())).priceChartFragmentSubcomponent(new IntroPriceChartPresenterModule(this)).inject(this);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mBinding.vPriceChart.setChartFillAlpha(0);
        this.mBinding.vPriceChart.showLimitLines(true);
        this.mBinding.vPriceChart.showStepLines(false);
        this.mBinding.vPriceChart.setLimitLineColor(-1);
        this.mBinding.vPriceChart.showMinMaxMarkersVisible(true);
        this.mBinding.vPriceChart.showXAxisLabel(true);
        this.mBinding.vPriceChart.setMinMaxMarkerColor(ContextCompat.getColor(getContext(), R.color.intro_chart_min_max_color));
        this.mBinding.vPriceChart.setDrawCubicEnabled(true);
        this.mBinding.tvRangeHour.setOnClickListener(new RangeOnClickListener(Period.HOUR));
        this.mBinding.tvRangeDay.setOnClickListener(new RangeOnClickListener(Period.DAY));
        this.mBinding.tvRangeWeek.setOnClickListener(new RangeOnClickListener(Period.WEEK));
        this.mBinding.tvRangeMonth.setOnClickListener(new RangeOnClickListener(Period.MONTH));
        this.mBinding.tvRangeYear.setOnClickListener(new RangeOnClickListener(Period.YEAR));
        this.mBinding.tvRangeAll.setOnClickListener(new RangeOnClickListener(Period.ALL));
        this.mRangeViews = new TextView[]{this.mBinding.tvRangeHour, this.mBinding.tvRangeDay, this.mBinding.tvRangeWeek, this.mBinding.tvRangeMonth, this.mBinding.tvRangeYear, this.mBinding.tvRangeAll};
        this.mRangeViews[1].setSelected(true);
        performAnimation(this.mBinding.rlPriceSection, TRANSLATION_Y, 0.0f, false);
        Utils.setAlpha(this.mBinding.rlPriceSection, 1.0f, false, null);
        this.mBinding.rlPriceHighlightSection.post(new Runnable() {
            public void run() {
                IntroPriceChartFragment.this.performAnimation(IntroPriceChartFragment.this.mBinding.rlPriceHighlightSection, IntroPriceChartFragment.TRANSLATION_Y, (float) (-IntroPriceChartFragment.this.mBinding.rlPriceHighlightSection.getMeasuredHeight()), false);
                Utils.setAlpha(IntroPriceChartFragment.this.mBinding.rlPriceHighlightSection, 0.0f, false, null);
            }
        });
        this.mBinding.tvCurrencyBitcoin.setOnClickListener(new BaseCurrencyOnClickListener(Currency.BTC));
        this.mBinding.tvCurrencyEther.setOnClickListener(new BaseCurrencyOnClickListener(Currency.ETH));
        this.mBinding.tvCurrencyLitecoin.setOnClickListener(new BaseCurrencyOnClickListener(Currency.LTC));
        this.baseCurrencyViews = Arrays.asList(new TextView[]{this.mBinding.tvCurrencyBitcoin, this.mBinding.tvCurrencyEther, this.mBinding.tvCurrencyLitecoin});
        updateTouchHitArea(Arrays.asList(this.mRangeViews), this.baseCurrencyViews);
        this.mPresenter.onViewCreated();
        setPriceChartSubscribers();
    }

    public void onResume() {
        super.onResume();
        this.mSubscription.add(this.mSpotPriceUpdatedSubject.onBackpressureLatest().subscribe(IntroPriceChartFragment$$Lambda$1.lambdaFactory$(this)));
        this.mSubscription.add(this.mHighlightedPricePublishSubject.onBackpressureLatest().subscribe(IntroPriceChartFragment$$Lambda$2.lambdaFactory$(this)));
        this.mSubscription.add(this.mMotionSubject.onBackpressureLatest().subscribe(IntroPriceChartFragment$$Lambda$3.lambdaFactory$(this)));
        this.mBinding.vPriceChart.listenForPeriodUpdate();
        this.mPresenter.onResume();
    }

    public void onPause() {
        super.onPause();
        this.mBinding.vPriceChart.onDestroy();
        this.mSubscription.clear();
    }

    public void setSelectedPrice(String price, String currencySybmol, String date) {
        this.mBinding.tvHighlightedPrice.setText(price);
        this.mBinding.tvHighlightedPriceCurrencySymbol.setText(currencySybmol);
        this.mBinding.tvHighlightedDate.setText(date);
    }

    public void setSpotPrice(String currentPrice, String currentPriceSymbol, String priceChange, boolean changeIsPositive, String changeScope) {
        this.mBinding.tvCurrentPrice.setText(currentPrice);
        this.mBinding.tvPriceCurrencySymbol.setText(currentPriceSymbol);
        this.mBinding.tvPriceChange.setText(priceChange);
        this.mBinding.tvPriceChangeCurrencySymbol.setText(currentPriceSymbol);
        this.mBinding.tvChangeScope.setText(changeScope);
        if (changeIsPositive) {
            this.mBinding.tvPriceChange.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_up, 0);
        } else {
            this.mBinding.tvPriceChange.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.indicator_down, 0);
        }
    }

    public void showCurrentPrice(boolean shouldShow) {
        showPriceSection(this.mBinding.rlPriceSection, !shouldShow);
        showPriceSection(this.mBinding.rlPriceHighlightSection, shouldShow);
    }

    public void updateBaseCurrencyViews(Currency currency) {
        for (TextView view : this.baseCurrencyViews) {
            view.setSelected(false);
        }
        switch (currency) {
            case BTC:
                ((TextView) this.baseCurrencyViews.get(0)).setSelected(true);
                this.mBinding.tvCryptoPrice.setText(getString(R.string.bitcoin_price));
                break;
            case ETH:
                ((TextView) this.baseCurrencyViews.get(1)).setSelected(true);
                this.mBinding.tvCryptoPrice.setText(getString(R.string.ethereum_price));
                break;
            case LTC:
                ((TextView) this.baseCurrencyViews.get(2)).setSelected(true);
                this.mBinding.tvCryptoPrice.setText(getString(R.string.litecoin_price));
                break;
        }
        this.mBinding.vPriceChart.setCurrencyCode(currency.toString(), this.mPresenter.getPeriod());
    }

    private void setPriceChartSubscribers() {
        this.mBinding.vPriceChart.setSpotPriceSubject(this.mSpotPriceUpdatedSubject);
        this.mBinding.vPriceChart.setHighlightedPriceSubject(this.mHighlightedPricePublishSubject);
        this.mBinding.vPriceChart.setMotionSubject(this.mMotionSubject);
    }

    private void showPriceSection(View priceSectionView, boolean shouldShow) {
        float f = 0.0f;
        performAnimation(priceSectionView, TRANSLATION_Y, shouldShow ? 0.0f : (float) (-priceSectionView.getMeasuredHeight()), true);
        if (shouldShow) {
            f = 1.0f;
        }
        Utils.setAlpha(priceSectionView, f, true, null);
    }

    private void performAnimation(View view, String propertyName, float value, boolean animated) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, propertyName, new float[]{value});
        anim.setDuration(animated ? 250 : 0);
        anim.start();
    }

    private void updateTouchHitArea(List<TextView>... textViews) {
        for (List<TextView> textViewList : textViews) {
            View parent = (View) ((TextView) textViewList.get(0)).getParent();
            parent.post(IntroPriceChartFragment$$Lambda$4.lambdaFactory$(parent, textViewList));
        }
    }

    static /* synthetic */ void lambda$updateTouchHitArea$3(View parent, List textViewList) {
        TouchDelegateComposite touchDelegateComposite = new TouchDelegateComposite(parent);
        for (TextView tv : textViewList) {
            Rect rect = new Rect();
            tv.getHitRect(rect);
            rect.top -= 20;
            rect.left -= 20;
            rect.bottom += 20;
            rect.right += 20;
            if (tv.getParent() == parent) {
                touchDelegateComposite.addDelegate(new TouchDelegate(rect, tv));
            }
        }
        parent.setTouchDelegate(touchDelegateComposite);
    }
}
