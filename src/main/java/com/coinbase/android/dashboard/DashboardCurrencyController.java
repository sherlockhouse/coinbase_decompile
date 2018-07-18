package com.coinbase.android.dashboard;

import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.accounts.AccountTransactionsController;
import com.coinbase.android.accounts.AccountTransactionsPresenter;
import com.coinbase.android.databinding.FragmentDashboardCurrencyBinding;
import com.coinbase.android.pricechart.PriceChartData.HighlightedPrice;
import com.coinbase.android.pricechart.PriceChartData.SpotPrice;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.BottomNavigationItem.Type;
import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import com.coinbase.android.ui.ScreenshotAllowed;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.currency.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.inject.Inject;
import org.joda.money.CurrencyUnit;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
@ScreenshotAllowed
public class DashboardCurrencyController extends ActionBarController implements DashboardCurrencyScreen {
    private static final int CHART_FILL_ALPHA = 50;
    private static final String TRANSLATION_Y = "translationY";
    private FragmentDashboardCurrencyBinding mBinding;
    @Inject
    BottomNavigationConnector mBottomNavigationConnector;
    private final PublishSubject<CurrencyUnit> mCurrencyUnitPublishSubject = PublishSubject.create();
    private final PublishSubject<HighlightedPrice> mHighlightedPricePublishSubject = PublishSubject.create();
    private final PublishSubject<Boolean> mMotionSubject = PublishSubject.create();
    @Inject
    DashboardCurrencyPresenter mPresenter;
    private final PublishSubject<Boolean> mScrollConnectorSubject = PublishSubject.create();
    private final PublishSubject<SpotPrice> mSpotPriceUpdatedSubject = PublishSubject.create();
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private String mTitle = "";

    public DashboardCurrencyController(Bundle bundle) {
        super(bundle);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (FragmentDashboardCurrencyBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard_currency, container, false);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addDashboardCurrencySubcomponent(new DashboardCurrencyPresenterModule(this, this)).inject(this);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        Bundle args = getArgs();
        if (args == null) {
            return this.mBinding.getRoot();
        }
        Data currency = (Data) new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().fromJson(args.getString("cryptocurrency_data"), Data.class);
        if (currency != null) {
            this.mPresenter.setSelectedCurrency(currency);
            updateViewText(currency);
            updateViewColors(currency);
            initLayout(currency);
        }
        return this.mBinding.getRoot();
    }

    protected void onShow() {
        super.onShow();
        this.mSubscription.add(this.mSpotPriceUpdatedSubject.onBackpressureLatest().subscribe(DashboardCurrencyController$$Lambda$1.lambdaFactory$(this)));
        this.mSubscription.add(this.mHighlightedPricePublishSubject.onBackpressureLatest().subscribe(DashboardCurrencyController$$Lambda$2.lambdaFactory$(this)));
        this.mSubscription.add(this.mMotionSubject.onBackpressureLatest().subscribe(DashboardCurrencyController$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mCurrencyUnitPublishSubject.onBackpressureLatest().distinctUntilChanged().subscribe(DashboardCurrencyController$$Lambda$4.lambdaFactory$(this)));
        this.mSubscription.add(this.mScrollConnectorSubject.onBackpressureLatest().subscribe(DashboardCurrencyController$$Lambda$5.lambdaFactory$(this)));
        this.mBinding.vPriceChart.listenForPeriodUpdate();
        this.mBinding.layoutAccountList.onResume();
        this.mPresenter.onResume();
    }

    protected void onHide() {
        super.onHide();
        this.mBinding.vPriceChart.onDestroy();
        this.mSubscription.clear();
        this.mPresenter.onDestroy();
        this.mBinding.layoutAccountList.onDestroy();
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(this.mTitle);
    }

    private void initLayout(Data currency) {
        if (currency != null) {
            setPriceChartSubscribers();
            this.mBinding.vPriceChart.setCurrencyCode(currency.getCode());
            this.mBinding.layoutAccountList.setCurrencyCode(currency.getCode());
            performAnimation(this.mBinding.rlPriceSection, TRANSLATION_Y, 0.0f, false);
            Utils.setAlpha(this.mBinding.rlPriceSection, 1.0f, false, null);
            this.mBinding.rlPriceHighlightSection.post(DashboardCurrencyController$$Lambda$6.lambdaFactory$(this));
            this.mBinding.btBuy.setOnClickListener(DashboardCurrencyController$$Lambda$7.lambdaFactory$(this));
            this.mBinding.btSell.setOnClickListener(DashboardCurrencyController$$Lambda$8.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$initLayout$5(DashboardCurrencyController this_) {
        this_.performAnimation(this_.mBinding.rlPriceHighlightSection, TRANSLATION_Y, (float) (-this_.mBinding.rlPriceHighlightSection.getMeasuredHeight()), false);
        Utils.setAlpha(this_.mBinding.rlPriceHighlightSection, 0.0f, false, null);
    }

    private void setPriceChartSubscribers() {
        this.mBinding.vPriceChart.setSpotPriceSubject(this.mSpotPriceUpdatedSubject);
        this.mBinding.vPriceChart.setHighlightedPriceSubject(this.mHighlightedPricePublishSubject);
        this.mBinding.vPriceChart.setMotionSubject(this.mMotionSubject);
        this.mBinding.vPriceChart.setCurrencyUpdatedSubject(this.mCurrencyUnitPublishSubject);
        this.mBinding.vPriceChart.setScrollConnectorSubject(this.mScrollConnectorSubject);
    }

    private void updateViewColors(Data currency) {
        if (currency != null) {
            int color = Utils.getColorInt(currency.getColor());
            if (color != -1) {
                this.mBinding.vPriceChart.setChartColor(color);
                this.mBinding.vPriceChart.setChartFillAlpha(50);
                this.mBinding.vPriceChart.showLimitLines(true);
                this.mBinding.vPriceChart.showStepLines(false);
                this.mBinding.vPriceChart.showXAxisLabel(true);
                this.mBinding.vPriceChart.showMinMaxMarkersVisible(true);
                this.mBinding.vPriceChart.setXAxisLabelColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_mystique_light_text_color));
            }
        }
    }

    private void updateViewText(Data currency) {
        if (currency != null) {
            this.mTitle = String.format(getApplicationContext().getString(R.string.currency_price), new Object[]{currency.getName()});
        }
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

    public void setCurrencyUnit(CurrencyUnit unit) {
        this.mBinding.tvPriceCurrencySymbol.setText(unit.getSymbol());
        this.mBinding.tvHighlightedPriceCurrencySymbol.setText(unit.getSymbol());
        this.mBinding.tvPriceChange.setText(getResources().getString(R.string.default_price_text));
        this.mBinding.tvCurrentPrice.setText(getResources().getString(R.string.default_price_text));
    }

    public void setSelectedPrice(String price, String date) {
        this.mBinding.tvHighlightedPrice.setText(price);
        this.mBinding.tvHighlightedDate.setText(date);
    }

    public void setSpotPrice(String currentPrice, String priceChange, int priceChangeTextColor) {
        this.mBinding.tvCurrentPrice.setText(currentPrice);
        this.mBinding.tvPriceChange.setText(priceChange);
        this.mBinding.tvPriceChange.setTextColor(ContextCompat.getColor(getApplicationContext(), priceChangeTextColor));
    }

    public void showCurrentPrice(boolean shouldShow) {
        showPriceSection(this.mBinding.rlPriceSection, !shouldShow);
        showPriceSection(this.mBinding.rlPriceHighlightSection, shouldShow);
    }

    public void setScrollingEnabled(Boolean isEnabled) {
        this.mBinding.nestedScrollView.setScrollingEnabled(isEnabled.booleanValue());
    }

    public void gotoAccountTransactions(com.coinbase.v2.models.account.Data account, Data selectedCurrency) {
        Bundle args = new Bundle();
        if (account != null) {
            args.putString(AccountTransactionsPresenter.ACCOUNT_DATA, new Gson().toJson((Object) account));
        }
        if (selectedCurrency != null) {
            args.putString(AccountTransactionsPresenter.CURRENCY_DATA, new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().toJson((Object) selectedCurrency));
        }
        this.mBottomNavigationConnector.get().onNext(PageDestination.builder().setSourceBottomNavigationItem(Type.DASHBOARD).setBottomNavigationItem(Type.ACCOUNTS).setPushPageController(new AccountTransactionsController(appendArgs(args))).build());
    }
}
