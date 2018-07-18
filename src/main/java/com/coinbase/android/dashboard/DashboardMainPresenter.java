package com.coinbase.android.dashboard;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.alerts.AlertsUtils;
import com.coinbase.android.buysell.BuyRouter;
import com.coinbase.android.buysell.BuyRouter.BuySource;
import com.coinbase.android.modalAlerts.ModalRouterAggregator;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.StatusBarUpdater;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.alerts.Alerts;
import com.coinbase.api.internal.models.currency.Data;
import com.coinbase.api.internal.models.dashboard.Balance;
import com.coinbase.api.internal.models.dashboard.Dashboard;
import com.coinbase.v2.models.price.Prices;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class DashboardMainPresenter {
    static final String DASHBOARD_ALERT_VIEW_TYPE = "dashboard";
    private final AlertsUtils mAlertsUtils;
    private final Scheduler mBackgroundScheduler;
    private final BuyRouter mBuyRouter;
    private final Context mContext;
    private final DashboardAlertsConnector mDashboardAlertsConnector;
    private final DashboardBalanceUpdatedConnector mDashboardBalanceUpdatedConnector;
    private final DashboardRefreshConnector mDashboardRefreshConnector;
    private final CompositeSubscription mDashboardSubscription = new CompositeSubscription();
    private final DashboardVerificationConnector mDashboardVerificationConnector;
    private final Logger mLogger = LoggerFactory.getLogger(DashboardMainPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final ModalRouterAggregator mModalRouterAggregator;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private final DashboardPriceChartItemClickedConnector mPriceChartItemClickedConnector;
    private final DashboardMainScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;
    private final StatusBarUpdater mStatusBarUpdater;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public DashboardMainPresenter(LoginManager loginManager, DashboardMainScreen screen, SnackBarWrapper snackBarWrapper, DashboardVerificationConnector dashboardVerificationConnector, DashboardBalanceUpdatedConnector dashboardBalanceUpdatedConnector, DashboardPriceChartItemClickedConnector priceChartItemClickedConnector, DashboardRefreshConnector dashboardRefreshConnector, DashboardAlertsConnector dashboardAlertsConnector, BuyRouter buyRouter, MixpanelTracking mixpanelTracking, MoneyFormatterUtil moneyFormatterUtil, AlertsUtils alertsUtils, Application app, StatusBarUpdater statusBarUpdater, ModalRouterAggregator modalRouterAggregator, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mDashboardVerificationConnector = dashboardVerificationConnector;
        this.mDashboardBalanceUpdatedConnector = dashboardBalanceUpdatedConnector;
        this.mPriceChartItemClickedConnector = priceChartItemClickedConnector;
        this.mDashboardRefreshConnector = dashboardRefreshConnector;
        this.mDashboardAlertsConnector = dashboardAlertsConnector;
        this.mBuyRouter = buyRouter;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mAlertsUtils = alertsUtils;
        this.mContext = app;
        this.mStatusBarUpdater = statusBarUpdater;
        this.mModalRouterAggregator = modalRouterAggregator;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    void onResume() {
        this.mStatusBarUpdater.setStatusBarColorFromBackgroundColor(this.mScreen.getThemeColor());
        this.mSubscription.add(this.mPriceChartItemClickedConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(DashboardMainPresenter$$Lambda$1.lambdaFactory$(this), DashboardMainPresenter$$Lambda$2.lambdaFactory$(this)));
        this.mSubscription.add(this.mDashboardRefreshConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(DashboardMainPresenter$$Lambda$3.lambdaFactory$(this), DashboardMainPresenter$$Lambda$4.lambdaFactory$(this)));
        this.mSubscription.add(this.mDashboardVerificationConnector.get().distinctUntilChanged().observeOn(this.mMainScheduler).subscribe(DashboardMainPresenter$$Lambda$5.lambdaFactory$(this), DashboardMainPresenter$$Lambda$6.lambdaFactory$(this)));
        getUpdatedDashboardData();
        this.mSubscription.add(this.mModalRouterAggregator.route());
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_DASHBOARD_VIEWED, new String[0]);
    }

    static /* synthetic */ void lambda$onResume$0(DashboardMainPresenter this_, Data currency) {
        this_.mScreen.gotoDashboardCurrency(currency);
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_DASHBOARD_TAPPED_CHART, "currency", currency.getCode());
    }

    static /* synthetic */ void lambda$onResume$2(DashboardMainPresenter this_, Void e) {
        this_.getUpdatedDashboardData();
        this_.mScreen.refreshPriceCharts();
    }

    static /* synthetic */ void lambda$onResume$6(DashboardMainPresenter this_, String firstBuy) {
        if (firstBuy == null) {
            this_.mScreen.showFirstBuyHeader();
        } else {
            this_.mScreen.showPortfolioBalanceHeader();
        }
        this_.mSubscription.add(this_.mDashboardAlertsConnector.get().first().observeOn(this_.mMainScheduler).subscribe(DashboardMainPresenter$$Lambda$13.lambdaFactory$(this_), DashboardMainPresenter$$Lambda$14.lambdaFactory$(this_)));
    }

    static /* synthetic */ void lambda$null$4(DashboardMainPresenter this_, Alerts alerts) {
        List<com.coinbase.api.internal.models.alerts.Data> filteredAlertsData = this_.filterAlertsForDashboard(alerts.getData());
        if (filteredAlertsData.isEmpty()) {
            this_.mScreen.hideAlerts();
        } else if (this_.mScreen.isShowingNewUser()) {
            this_.mScreen.showAlertsNewUser(filteredAlertsData);
        } else {
            this_.mScreen.showAlertsPortfolio(filteredAlertsData);
        }
    }

    void onDestroy() {
        this.mDashboardSubscription.clear();
        this.mSubscription.clear();
    }

    void onFirstBuyAccountSetupClicked() {
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_DASHBOARD_TAPPED_FIRST_BUY, new String[0]);
        routeToBuyModal();
    }

    private void routeToBuyModal() {
        this.mBuyRouter.routeToBuyModal(BuySource.DASHBOARD);
    }

    private void getUpdatedDashboardData() {
        fetchDashboardData();
        this.mDashboardSubscription.clear();
        this.mDashboardSubscription.add(this.mDashboardBalanceUpdatedConnector.get().distinctUntilChanged().observeOn(this.mMainScheduler).subscribe(DashboardMainPresenter$$Lambda$7.lambdaFactory$(this), DashboardMainPresenter$$Lambda$8.lambdaFactory$(this)));
    }

    void fetchDashboardData() {
        this.mSubscription.add(Observable.combineLatest(this.mLoginManager.getClient().getDashboardRx(), this.mLoginManager.getClient().getSpotPricesRx(this.mLoginManager.getCurrencyUnit().getCurrencyCode(), new HashMap()), this.mLoginManager.getClient().getAlertsRx(), DashboardMainPresenter$$Lambda$9.lambdaFactory$()).map(DashboardMainPresenter$$Lambda$10.lambdaFactory$(this)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(DashboardMainPresenter$$Lambda$11.lambdaFactory$(this), DashboardMainPresenter$$Lambda$12.lambdaFactory$(this)));
    }

    static /* synthetic */ Pair lambda$fetchDashboardData$11(DashboardMainPresenter this_, List response) {
        Pair<Response<Dashboard>, Retrofit> dashboardResponsePair = (Pair) response.get(0);
        Response<Dashboard> dashboardResponse = dashboardResponsePair.first;
        if (dashboardResponse.isSuccessful()) {
            Response<Prices> spotPricesResponse = ((Pair) response.get(1)).first;
            if (spotPricesResponse.isSuccessful()) {
                this_.mDashboardVerificationConnector.get().onNext(((Dashboard) dashboardResponse.body()).getData().getFirstBuyStatus());
                String portfolioBalance = this_.calculatePortfolioBalance(dashboardResponse, spotPricesResponse, this_.mLoginManager.getCurrencyUnit().getCurrencyCode());
                if (portfolioBalance != null) {
                    this_.mDashboardBalanceUpdatedConnector.get().onNext(portfolioBalance);
                }
                this_.handleAlertsResponse((Pair) response.get(2));
            }
        }
        return dashboardResponsePair;
    }

    static /* synthetic */ void lambda$fetchDashboardData$12(DashboardMainPresenter this_, Pair dashboardResponsePair) {
        Response<Dashboard> dashboardResponse = dashboardResponsePair.first;
        Retrofit retrofit = dashboardResponsePair.second;
        if (!dashboardResponse.isSuccessful()) {
            this_.showMessageOrGenericError(Utils.getErrorMessage(dashboardResponse, retrofit));
        }
    }

    static /* synthetic */ void lambda$fetchDashboardData$13(DashboardMainPresenter this_, Throwable t) {
        this_.mLogger.error("Error from fetchDashboardData", t);
        this_.showMessageOrGenericError(Utils.getMessage(this_.mContext, t));
    }

    private void showMessageOrGenericError(String errorMessage) {
        if (TextUtils.isEmpty(errorMessage)) {
            this.mSnackBarWrapper.showGenericError();
        } else {
            this.mSnackBarWrapper.show(errorMessage);
        }
    }

    private String calculatePortfolioBalance(Response<Dashboard> dashboardResponse, Response<Prices> spotPricesResponse, String userCurrencyCode) {
        if (dashboardResponse == null || spotPricesResponse == null) {
            return null;
        }
        com.coinbase.api.internal.models.dashboard.Data dashboardData = ((Dashboard) dashboardResponse.body()).getData();
        if (dashboardData == null) {
            return null;
        }
        List<Balance> balanceList = dashboardData.getBalance();
        List<com.coinbase.v2.models.price.Data> spotPriceList = ((Prices) spotPricesResponse.body()).getData();
        if (balanceList == null || spotPriceList == null) {
            return null;
        }
        com.coinbase.v2.models.price.Data spotPrice;
        Map<String, com.coinbase.v2.models.price.Data> spotPriceMap = new HashMap();
        for (com.coinbase.v2.models.price.Data spotPrice2 : spotPriceList) {
            spotPriceMap.put(spotPrice2.getBase().toLowerCase(), spotPrice2);
        }
        List<Money> nativeMoneyList = new ArrayList();
        for (Balance balance : balanceList) {
            spotPrice2 = (com.coinbase.v2.models.price.Data) spotPriceMap.get(balance.getCurrency().getCode().toLowerCase());
            if (spotPrice2 != null) {
                double total = Double.parseDouble(spotPrice2.getAmount()) * Double.parseDouble(balance.getAmount().getAmount());
                Money nativeTotalMoney = Money.of(this.mLoginManager.getCurrencyUnit(), total, RoundingMode.HALF_EVEN);
                if (nativeTotalMoney != null) {
                    nativeMoneyList.add(nativeTotalMoney);
                }
            } else if (TextUtils.equals(balance.getAmount().getCurrency().toLowerCase(), userCurrencyCode.toLowerCase())) {
                Money balanceMoney = this.mMoneyFormatterUtil.moneyFromValue(balance.getAmount().getCurrency(), balance.getAmount().getAmount());
                if (balanceMoney == null) {
                    return null;
                }
                nativeMoneyList.add(balanceMoney);
            } else {
                continue;
            }
        }
        return getFormattedPortfolioBalance(Money.total(nativeMoneyList));
    }

    private String getFormattedPortfolioBalance(Money portfolioBalance) {
        if (portfolioBalance == null) {
            return null;
        }
        return this.mMoneyFormatterUtil.formatMoney(portfolioBalance);
    }

    private void handleAlertsResponse(Pair pair) {
        if (pair.first instanceof Response) {
            Response<?> response = pair.first;
            if (response.isSuccessful() && (response.body() instanceof Alerts)) {
                this.mDashboardAlertsConnector.get().onNext((Alerts) response.body());
            }
        }
    }

    private List<com.coinbase.api.internal.models.alerts.Data> filterAlertsForDashboard(List<com.coinbase.api.internal.models.alerts.Data> alertsData) {
        List<com.coinbase.api.internal.models.alerts.Data> filteredAlerts = new LinkedList();
        if (!(alertsData == null || alertsData.isEmpty())) {
            for (com.coinbase.api.internal.models.alerts.Data data : alertsData) {
                List<String> views = data.getViews();
                if (!(views == null || !views.contains(DASHBOARD_ALERT_VIEW_TYPE) || this.mAlertsUtils.isDismissed(data))) {
                    filteredAlerts.add(data);
                }
            }
        }
        return filteredAlerts;
    }
}
