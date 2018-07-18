package com.coinbase.android.notifications.priceAlerts;

import android.app.Application;
import android.util.Log;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.dashboard.SpotPriceUpdatedConnector;
import com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder;
import com.coinbase.android.ui.CurrencyTabFilter;
import com.coinbase.android.ui.CurrencyTabSelectorConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.DeviceUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.PriceAlertUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.currency.Data;
import com.coinbase.v2.models.price.Price;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import org.joda.money.CurrencyUnit;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class PriceAlertsPresenter {
    private final Scheduler mBackgroundScheduler;
    private final Application mContext;
    private final CurrencyTabSelectorConnector mCurrencyTabSelectorConnector;
    private CurrencyUnit mCurrencyUnit;
    private String mDeviceId;
    private List<LocalPriceAlert> mFilteredPriceAlerts = new ArrayList();
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final MoneyFormatterUtil mMoneyFormatterUtil;
    private final PriceAlertsConnector mPriceAlertsConnector;
    private final PriceAlertsRouter mPriceAlertsRouter;
    private final PriceAlertsScreen mScreen;
    private Data mSelectedCurrency;
    private final SpotPriceUpdatedConnector mSpotPriceUpdatedConnector;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    static class PriceAlertsCurrencyTabFilter implements CurrencyTabFilter {
        PriceAlertsCurrencyTabFilter() {
        }

        public List<Data> filter(List<Data> currencies) {
            List<Data> filteredCurrencies = new LinkedList();
            for (Data currency : currencies) {
                if (currency.getPriceAlertsEnabled()) {
                    filteredCurrencies.add(currency);
                }
            }
            return filteredCurrencies;
        }

        public Observable<Void> filterUpdated() {
            return null;
        }
    }

    @Inject
    public PriceAlertsPresenter(PriceAlertsScreen screen, Application app, CurrencyTabSelectorConnector currencySelectorConnector, PriceAlertsConnector priceAlertsConnector, PriceAlertsRouter priceAlertsRouter, LoginManager loginManager, MixpanelTracking mixpanelTracking, MoneyFormatterUtil moneyFormatterUtil, SpotPriceUpdatedConnector spotPriceUpdatedConnector, @MainScheduler Scheduler mainScheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mScreen = screen;
        this.mContext = app;
        this.mCurrencyTabSelectorConnector = currencySelectorConnector;
        this.mPriceAlertsConnector = priceAlertsConnector;
        this.mPriceAlertsRouter = priceAlertsRouter;
        this.mLoginManager = loginManager;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mSpotPriceUpdatedConnector = spotPriceUpdatedConnector;
        this.mMainScheduler = mainScheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    void onCreateView() {
        this.mDeviceId = DeviceUtils.deviceId(this.mContext);
        registerGcmToken();
    }

    void onCreatePriceAlertClicked() {
        if (this.mSelectedCurrency != null) {
            this.mPriceAlertsRouter.routeCreatePriceAlert(this.mSelectedCurrency);
        }
    }

    void onResume() {
        this.mSubscription.add(this.mCurrencyTabSelectorConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(PriceAlertsPresenter$$Lambda$1.lambdaFactory$(this)));
        this.mSubscription.add(this.mPriceAlertsConnector.get().observeOn(this.mMainScheduler).subscribe(PriceAlertsPresenter$$Lambda$2.lambdaFactory$(this)));
        updatePriceAlerts();
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_PRICE_ALERT_VIEWED, new String[0]);
    }

    void onDestroy() {
        this.mSubscription.clear();
    }

    List<LocalPriceAlert> getFilteredPriceAlerts() {
        return this.mFilteredPriceAlerts;
    }

    void createPriceAlert(LocalPriceAlert localPriceAlert) {
        if (localPriceAlert != null) {
            this.mPriceAlertsConnector.get().onNext(localPriceAlert);
            this.mSubscription.add(this.mLoginManager.getClient().createPriceAlertRx(generateParams(localPriceAlert)).observeOn(this.mBackgroundScheduler).subscribe(PriceAlertsPresenter$$Lambda$3.lambdaFactory$(), PriceAlertsPresenter$$Lambda$4.lambdaFactory$()));
        }
    }

    static /* synthetic */ void lambda$createPriceAlert$2(Pair pair) {
    }

    static /* synthetic */ void lambda$createPriceAlert$3(Throwable t) {
    }

    void deletePriceAlert(LocalPriceAlert localPriceAlert) {
        if (localPriceAlert != null) {
            PriceAlertUtils.deletePriceAlert(this.mContext, localPriceAlert);
            this.mPriceAlertsConnector.get().onNext(localPriceAlert);
            this.mSubscription.add(this.mLoginManager.getClient().deletePriceAlertRx(this.mDeviceId, generateParams(localPriceAlert)).observeOn(this.mBackgroundScheduler).subscribe(PriceAlertsPresenter$$Lambda$5.lambdaFactory$(), PriceAlertsPresenter$$Lambda$6.lambdaFactory$()));
        }
    }

    static /* synthetic */ void lambda$deletePriceAlert$4(Pair pair) {
    }

    static /* synthetic */ void lambda$deletePriceAlert$5(Throwable t) {
    }

    String getNotificationTimeDisplayText(LocalPriceAlert localPriceAlert) {
        if (localPriceAlert == null) {
            return null;
        }
        String preposition;
        boolean isAlertNotTriggered = localPriceAlert.getTriggeredOn() == -1;
        String dateString = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(isAlertNotTriggered ? new Date(localPriceAlert.getCreatedOn()) : new Date(localPriceAlert.getTriggeredOn()));
        if (isAlertNotTriggered) {
            preposition = this.mContext.getString(R.string.notification_created_on);
        } else {
            preposition = this.mContext.getString(R.string.notification_triggered_on);
        }
        return preposition + " " + dateString;
    }

    int getNotificationResourceId(LocalPriceAlert localPriceAlert) {
        if (localPriceAlert == null) {
            return R.drawable.default_circle;
        }
        return localPriceAlert.getIsAbove() ? R.drawable.price_alert_up : R.drawable.price_alert_down;
    }

    void setNotificationEnabled(LocalPriceAlert localPriceAlert, boolean isEnabled) {
        Builder builder = LocalPriceAlert.builder();
        builder.setAmount(localPriceAlert.getAmount()).setCreatedOn(localPriceAlert.getCreatedOn()).setCurrency(localPriceAlert.getCurrency()).setCurrencyUnit(localPriceAlert.getCurrencyUnit()).setId(localPriceAlert.getId()).setIsAbove(localPriceAlert.getIsAbove()).setDisplayText(localPriceAlert.getDisplayText()).setNotificationTitle(localPriceAlert.getNotificationTitle()).setNotificationMessage(localPriceAlert.getNotificationMessage()).setTriggeredOn(localPriceAlert.getTriggeredOn());
        if (isEnabled) {
            builder.setTriggeredOn(-1);
            createPriceAlert(localPriceAlert);
        } else {
            deletePriceAlert(localPriceAlert);
        }
        builder.setEnabled(isEnabled);
        PriceAlertUtils.savePriceAlert(this.mContext, builder.build());
    }

    void determineCurrency() {
        this.mCurrencyUnit = this.mLoginManager.getCurrencyUnit();
    }

    private void refreshData() {
        filterPriceAlerts();
        this.mScreen.notifyDataSetChanged();
    }

    void filterPriceAlerts() {
        this.mFilteredPriceAlerts.clear();
        if (this.mSelectedCurrency != null) {
            LocalPriceAlerts localPriceAlerts = PriceAlertUtils.getSavedPriceAlerts(this.mContext);
            if (localPriceAlerts != null && localPriceAlerts.getPriceAlerts() != null) {
                for (LocalPriceAlert alert : localPriceAlerts.getPriceAlerts()) {
                    if (alert.getCurrency().getCode().equals(this.mSelectedCurrency.getCode())) {
                        this.mFilteredPriceAlerts.add(alert);
                    }
                }
            }
        }
    }

    void updateCurrencyText() {
        if (this.mSelectedCurrency != null) {
            this.mScreen.updateCurrencyView(this.mSelectedCurrency);
        }
    }

    private void onCurrencyUpdated(Data currency) {
        if (currency != null) {
            this.mSelectedCurrency = currency;
            updatePriceAlerts();
        }
    }

    private void updatePriceAlerts() {
        determineCurrency();
        if (this.mSelectedCurrency != null) {
            getSpotPrice();
            updateCurrencyText();
            refreshData();
        }
    }

    String getFormattedSpotPrice(com.coinbase.v2.models.price.Data price) {
        if (price == null || price.getAmount() == null) {
            return null;
        }
        return this.mMoneyFormatterUtil.formatMoney(this.mMoneyFormatterUtil.moneyFromValue(this.mCurrencyUnit.getCurrencyCode(), price.getAmount()));
    }

    void setTestCurrency(Data currency) {
        this.mSelectedCurrency = currency;
    }

    void setTestDeviceId(String deviceId) {
        this.mDeviceId = deviceId;
    }

    private HashMap generateParams(LocalPriceAlert alert) {
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.DEVICE_ID, this.mDeviceId);
        params.put(ApiConstants.PLATFORM, ApiConstants.GCM);
        params.put(ApiConstants.APPLICATION_ID, ApiConstants.COINBASE_ANDROID);
        params.put("above", PriceAlertUtils.aboveString(alert.getIsAbove()));
        params.put("currency_code", alert.getCurrencyUnit().getCurrencyCode());
        params.put("message", alert.getDisplayText());
        params.put("price", alert.getAmount());
        if (alert.getCurrency() != null) {
            params.put(ApiConstants.BASE_CURRENCY_CODE, alert.getCurrency().getCode());
        }
        return params;
    }

    void getSpotPrice() {
        if (this.mCurrencyUnit == null || this.mSelectedCurrency == null) {
            handleGetSpotPriceFailure();
        } else {
            this.mSubscription.add(this.mSpotPriceUpdatedConnector.fetch(this.mSelectedCurrency.getCode(), this.mCurrencyUnit.getCode()).observeOn(this.mMainScheduler).subscribe(PriceAlertsPresenter$$Lambda$7.lambdaFactory$(this), PriceAlertsPresenter$$Lambda$8.lambdaFactory$(this)));
        }
    }

    static /* synthetic */ void lambda$getSpotPrice$6(PriceAlertsPresenter this_, Pair pair) {
        Response<Price> response = pair.first;
        if (response.isSuccessful()) {
            String amountString = this_.getFormattedSpotPrice(((Price) response.body()).getData());
            if (amountString == null) {
                this_.mScreen.showCurrentPriceNotAvailable();
                return;
            } else {
                this_.mScreen.showCurrentPrice(amountString);
                return;
            }
        }
        this_.mScreen.showCurrentPriceNotAvailable();
    }

    private void handleGetSpotPriceFailure() {
    }

    private void registerGcmToken() {
        String token = DeviceUtils.FCMToken();
        if (token != null) {
            Log.d("FCM Token", token);
            Log.d("device Id", this.mDeviceId);
            this.mLoginManager.getClient().createDeviceTokenRx(this.mDeviceId, token).subscribe(PriceAlertsPresenter$$Lambda$9.lambdaFactory$(), PriceAlertsPresenter$$Lambda$10.lambdaFactory$());
        }
    }

    static /* synthetic */ void lambda$registerGcmToken$8(Pair pair) {
        Response<Void> response = pair.first;
        Retrofit retrofit = pair.second;
        if (response.isSuccessful()) {
            Log.d("create device token", "Fcm token successfully created");
        } else {
            Log.w("create device token", Utils.getErrorMessage(response, retrofit));
        }
    }
}
