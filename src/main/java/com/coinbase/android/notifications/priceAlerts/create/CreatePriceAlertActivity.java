package com.coinbase.android.notifications.priceAlerts.create;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.Coinbase;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ActivityCreatePriceAlertBinding;
import com.coinbase.android.notifications.priceAlerts.LocalPriceAlert;
import com.coinbase.android.notifications.priceAlerts.PriceAlertsConnector;
import com.coinbase.android.notifications.priceAlerts.PriceAlertsRouter;
import com.coinbase.android.utils.DeviceUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.PriceAlertUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.currency.Data;
import com.coinbase.v2.models.price.Price;
import com.coinbase.v2.models.supportedCurrencies.SupportedCurrencies;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import org.joda.money.CurrencyUnit;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreatePriceAlertActivity extends Activity {
    private static final int MIN_ITEM_COUNT = 20;
    private static int MIN_NUM_DIGITS_TO_SHOW_DECIMAL = 2;
    public static int TICK_MARKS_PER_INCREMENT = 5;
    List<View> aboveViews;
    List<View> belowViews;
    private double currentPrice;
    private double currentPriceUSD;
    final Handler handler = new Handler();
    private int initialPosition;
    private LinearLayoutManager linearLayoutManager;
    @Inject
    LoginManager loginManager;
    ActivityCreatePriceAlertBinding mBinding;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;
    @Inject
    PriceAlertsConnector mPriceAlertsConnector;
    private Data mSelectedCurrency;
    private String nativeCurrency;
    OnScrollListener scrollListener;
    private int scrollTaskDelay = 100;
    private Runnable scrollerTask;
    private double selectedPrice;
    private CurrencyUnit unit;
    public int xOffset;
    private List<String> zeroDecimalCurrencies;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ComponentProvider) getApplicationContext()).applicationComponent().genericActivitySubcomponent().inject(this);
        this.mBinding = (ActivityCreatePriceAlertBinding) DataBindingUtil.setContentView(this, R.layout.activity_create_price_alert);
        this.mBinding.progressBar.setVisibility(0);
        this.mBinding.progressCreatePriceAlert.setVisibility(8);
        this.mBinding.rvPrice.setVisibility(8);
        this.nativeCurrency = PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.KEY_ACCOUNT_NATIVE_CURRENCY, null);
        this.unit = Utils.getCurrencyUnitByCode(this.nativeCurrency);
        this.currentPrice = -1.0d;
        this.currentPriceUSD = -1.0d;
        String currencyJson = getIntent().getStringExtra(PriceAlertsRouter.CURRENCY_DATA);
        if (TextUtils.isEmpty(currencyJson)) {
            errorOccurred();
            return;
        }
        this.mSelectedCurrency = (Data) new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().fromJson(currencyJson, Data.class);
        if (this.mSelectedCurrency == null) {
            errorOccurred();
            return;
        }
        Coinbase client = this.loginManager.getClient();
        client.getSpotPrice(this.mSelectedCurrency.getCode(), this.nativeCurrency, null, new CallbackWithRetrofit<Price>() {
            public void onResponse(Call<Price> call, Response<Price> response, Retrofit retrofit) {
                if (response.isSuccessful()) {
                    CreatePriceAlertActivity.this.currentPrice = Double.valueOf(((Price) response.body()).getData().getAmount()).doubleValue();
                    CreatePriceAlertActivity.this.checkDataLoadCompletion();
                    return;
                }
                CreatePriceAlertActivity.this.errorOccurred();
            }

            public void onFailure(Call<Price> call, Throwable t) {
                CreatePriceAlertActivity.this.failureOccurred(t);
            }
        });
        client.getSpotPrice(this.mSelectedCurrency.getCode(), "USD", null, new CallbackWithRetrofit<Price>() {
            public void onResponse(Call<Price> call, Response<Price> response, Retrofit retrofit) {
                if (response.isSuccessful()) {
                    CreatePriceAlertActivity.this.currentPriceUSD = Double.valueOf(((Price) response.body()).getData().getAmount()).doubleValue();
                    CreatePriceAlertActivity.this.checkDataLoadCompletion();
                    return;
                }
                CreatePriceAlertActivity.this.errorOccurred();
            }

            public void onFailure(Call<Price> call, Throwable t) {
                CreatePriceAlertActivity.this.failureOccurred(t);
            }
        });
        client.getSupportedCurrencies(new CallbackWithRetrofit<SupportedCurrencies>() {
            public void onResponse(Call<SupportedCurrencies> call, Response<SupportedCurrencies> response, Retrofit retrofit) {
                if (response.isSuccessful()) {
                    CreatePriceAlertActivity.this.zeroDecimalCurrencies = Utils.zeroDecimalCurrencyCodes(((SupportedCurrencies) response.body()).getData());
                    CreatePriceAlertActivity.this.checkDataLoadCompletion();
                    return;
                }
                CreatePriceAlertActivity.this.errorOccurred();
            }

            public void onFailure(Call<SupportedCurrencies> call, Throwable t) {
            }
        });
        this.belowViews = new ArrayList();
        this.belowViews.add(this.mBinding.btnBelow);
        this.belowViews.add(this.mBinding.vLeftDivider);
        this.aboveViews = new ArrayList();
        this.aboveViews.add(this.mBinding.btnAbove);
        this.aboveViews.add(this.mBinding.vRightDivider);
        this.mBinding.btnCreateNotification.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CreatePriceAlertActivity.this.createPriceAlert();
            }
        });
        enableViews(this.belowViews, false);
        enableViews(this.aboveViews, false);
        enableButton(this.mBinding.btnCreateNotification, false);
        this.mBinding.ibtnCanel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CreatePriceAlertActivity.this.finish();
            }
        });
    }

    private boolean isZeroDecimalCurrency(String currencyCode) {
        if (this.zeroDecimalCurrencies.contains(currencyCode)) {
            return true;
        }
        return false;
    }

    private boolean shouldShowDecimal(double price, String currencyCode) {
        if (!isZeroDecimalCurrency(currencyCode) && numberOfDigits(price) <= MIN_NUM_DIGITS_TO_SHOW_DECIMAL) {
            return true;
        }
        return false;
    }

    private void errorOccurred() {
        Utils.showMessage((Context) this, (int) R.string.error_occurred_try_again, 0);
        finish();
    }

    private void failureOccurred(Throwable t) {
        Utils.showMessage((Context) this, t, 0);
        finish();
    }

    private void createPriceAlert() {
        enableButton(this.mBinding.btnCreateNotification, false);
        this.mBinding.progressCreatePriceAlert.setVisibility(0);
        final boolean isAbove = this.selectedPrice > this.currentPrice;
        String messagePrefix = isAbove ? getString(R.string.notification_above) : getString(R.string.notification_below);
        String tmpPriceString = this.mBinding.tvDollar.getText().toString();
        String centString = this.mBinding.tvCent.getText().toString();
        if (shouldShowDecimal(this.currentPrice, this.nativeCurrency) && !centString.isEmpty()) {
            tmpPriceString = tmpPriceString + "." + centString;
        }
        String priceString = tmpPriceString;
        final String formattedPriceString = priceString.replace(",", "");
        final String displayText = messagePrefix + " " + (this.unit.getSymbol() + priceString);
        final String notificationTitle = String.format(getString(R.string.price_alerts_notification_title), new Object[]{this.mSelectedCurrency.getCode()});
        final String notificationMessage = String.format(getString(R.string.price_alerts_notification_message), new Object[]{this.mSelectedCurrency.getCode(), messagePrefix.toLowerCase(), priceStringWithCurrency});
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.DEVICE_ID, DeviceUtils.deviceId(this));
        params.put(ApiConstants.PLATFORM, ApiConstants.GCM);
        params.put(ApiConstants.APPLICATION_ID, ApiConstants.COINBASE_ANDROID);
        params.put("above", PriceAlertUtils.aboveString(isAbove));
        params.put("currency_code", this.unit.getCurrencyCode());
        params.put("message", notificationMessage);
        params.put("price", formattedPriceString);
        params.put(ApiConstants.BASE_CURRENCY_CODE, this.mSelectedCurrency.getCode());
        this.loginManager.getClient().createPriceAlert(params, new CallbackWithRetrofit<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response, Retrofit retrofit) {
                if (response.isSuccessful()) {
                    LocalPriceAlert localPriceAlert = LocalPriceAlert.builder().setId(UUID.randomUUID().toString()).setAmount(formattedPriceString).setIsAbove(isAbove).setEnabled(true).setCreatedOn(System.currentTimeMillis()).setTriggeredOn(-1).setCurrencyUnit(CreatePriceAlertActivity.this.unit).setNotificationTitle(notificationTitle).setNotificationMessage(notificationMessage).setDisplayText(displayText).setCurrency(CreatePriceAlertActivity.this.mSelectedCurrency).build();
                    PriceAlertUtils.savePriceAlert(CreatePriceAlertActivity.this, localPriceAlert);
                    CreatePriceAlertActivity.this.mPriceAlertsConnector.get().onNext(localPriceAlert);
                    CreatePriceAlertActivity.this.finish();
                    Utils.showMessage(CreatePriceAlertActivity.this, (int) R.string.notification_create_success, 1);
                    CreatePriceAlertActivity.this.mBinding.progressCreatePriceAlert.setVisibility(8);
                    CreatePriceAlertActivity.this.enableButton(CreatePriceAlertActivity.this.mBinding.btnCreateNotification, true);
                    return;
                }
                CreatePriceAlertActivity.this.handleRequestError(Utils.getErrorMessage(response, retrofit));
            }

            public void onFailure(Call<Void> call, Throwable t) {
                CreatePriceAlertActivity.this.handleRequestError(null);
            }
        });
    }

    private void handleRequestError(String message) {
        if (message == null) {
            Utils.showMessage((Context) this, (int) R.string.an_error_occurred, 1);
        } else {
            Utils.showMessage((Context) this, message, 1);
        }
        this.mBinding.progressCreatePriceAlert.setVisibility(8);
        enableButton(this.mBinding.btnCreateNotification, true);
    }

    private void enableViews(List<View> views, boolean shouldEnable) {
        int newColor;
        int i = R.color.text_notification;
        Resources resources;
        if (VERSION.SDK_INT >= 23) {
            resources = getResources();
            if (!shouldEnable) {
                i = R.color.text_notification_disabled;
            }
            newColor = resources.getColor(i, null);
        } else {
            resources = getResources();
            if (!shouldEnable) {
                i = R.color.text_notification_disabled;
            }
            newColor = resources.getColor(i);
        }
        for (View view : views) {
            setViewColor(view, newColor);
        }
    }

    private void enableButton(Button button, boolean shouldEnable) {
        int newColor;
        int i = R.color.create_notification_text;
        button.setEnabled(shouldEnable);
        Resources resources;
        if (VERSION.SDK_INT >= 23) {
            resources = getResources();
            if (!shouldEnable) {
                i = R.color.create_notification_text_disabled;
            }
            newColor = resources.getColor(i, null);
        } else {
            resources = getResources();
            if (!shouldEnable) {
                i = R.color.create_notification_text_disabled;
            }
            newColor = resources.getColor(i);
        }
        setButtonColor(button, newColor);
    }

    private int numberOfDigits(double num) {
        return (int) Math.ceil(Math.log10(num));
    }

    public void startScrollerTask() {
        this.initialPosition = this.xOffset;
        this.handler.postDelayed(this.scrollerTask, (long) this.scrollTaskDelay);
    }

    private void prepareViewWithPrice(double price) {
        double increment = 5.0d;
        final int incrementSignificantDigits = numberOfDigits(price / this.currentPriceUSD);
        if (incrementSignificantDigits > 1) {
            increment = ((double) TICK_MARKS_PER_INCREMENT) * Math.pow(10.0d, (double) (incrementSignificantDigits - 1));
        }
        final double roundedIncrement = increment;
        final double roundedPrice = shouldShowDecimal(price, this.nativeCurrency) ? Utils.floorOneDecimal(price) : (double) Math.round(price);
        this.mBinding.tvDollar.setText(this.mMoneyFormatterUtil.formatMoney(this.mMoneyFormatterUtil.moneyFromValue(this.unit.toString(), Double.toString(roundedPrice)), EnumSet.of(Options.ROUND_0_DIGITS, Options.EXCLUDE_CURRENCY_SYMBOL)));
        String centString = "";
        final boolean shouldShowDecimal = shouldShowDecimal(roundedPrice, this.nativeCurrency);
        if (shouldShowDecimal) {
            centString = "00";
        }
        this.mBinding.tvCent.setText(centString);
        final double d = price;
        this.mBinding.rlScrollLayout.post(new Runnable() {
            public void run() {
                double maxValue = ((roundedIncrement - (roundedPrice % roundedIncrement)) + roundedPrice) * 2.0d;
                if (((int) (maxValue / roundedIncrement)) < 20) {
                    maxValue = 20.0d * roundedIncrement;
                }
                int itemCount = ((int) (maxValue / roundedIncrement)) + 2;
                int scrollViewContentWidth = CreatePriceAlertActivity.this.getResources().getDimensionPixelSize(R.dimen.create_price_alert_item_width) * (itemCount - 2);
                double positionPrice = shouldShowDecimal ? d : roundedPrice;
                double currentPosition = ((double) scrollViewContentWidth) * (positionPrice / maxValue);
                CreatePriceAlertActivity.this.prepareScrollView(maxValue, positionPrice, incrementSignificantDigits, roundedIncrement, itemCount);
                CreatePriceAlertActivity.this.prepareItems(itemCount, roundedIncrement, currentPosition, positionPrice);
            }
        });
    }

    private String roundedCentString(double price) {
        int value = (int) (((price % 1.0d) - (price % 0.1d)) * 100.0d);
        if (value < 9) {
            value = 0;
        }
        String strValue = String.valueOf(value);
        return strValue.equals("0") ? "00" : strValue;
    }

    private void prepareScrollView(double maxValue, double currentValue, int significantDigits, double increment, int itemCount) {
        final int cellWidth = getResources().getDimensionPixelSize(R.dimen.create_price_alert_item_width);
        final int scrollViewContentWidth = cellWidth * (itemCount - 2);
        final int singleTickWidth = cellWidth / TICK_MARKS_PER_INCREMENT;
        this.scrollListener = new OnScrollListener() {
            public void onScrollChanged(int xOffset) {
            }

            public void onScrollStopped() {
                if (!CreatePriceAlertActivity.this.shouldShowDecimal(CreatePriceAlertActivity.this.currentPrice, CreatePriceAlertActivity.this.nativeCurrency)) {
                    int remainder = CreatePriceAlertActivity.this.xOffset % singleTickWidth;
                    int snapToPosition = singleTickWidth - remainder;
                    if (remainder < singleTickWidth / 2) {
                        snapToPosition = -remainder;
                    }
                    CreatePriceAlertActivity.this.mBinding.rvPrice.smoothScrollBy(snapToPosition, 0);
                }
            }
        };
        this.scrollerTask = new Runnable() {
            public void run() {
                if (CreatePriceAlertActivity.this.initialPosition - CreatePriceAlertActivity.this.xOffset != 0) {
                    CreatePriceAlertActivity.this.initialPosition = CreatePriceAlertActivity.this.xOffset;
                    CreatePriceAlertActivity.this.handler.postDelayed(CreatePriceAlertActivity.this.scrollerTask, (long) CreatePriceAlertActivity.this.scrollTaskDelay);
                } else if (CreatePriceAlertActivity.this.scrollListener != null) {
                    CreatePriceAlertActivity.this.scrollListener.onScrollStopped();
                }
            }
        };
        final int placeholderWidth = (this.mBinding.rlScrollLayout.getWidth() / 2) - (getResources().getDimensionPixelSize(R.dimen.create_price_alert_item_width) / 2);
        final double d = maxValue;
        final int i = significantDigits;
        final double d2 = increment;
        final double d3 = currentValue;
        this.mBinding.rvPrice.setOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstItemPosition = CreatePriceAlertActivity.this.linearLayoutManager.findFirstVisibleItemPosition();
                int firstVisibleItemOffset = Math.abs(CreatePriceAlertActivity.this.linearLayoutManager.findViewByPosition(firstItemPosition).getLeft());
                if (firstItemPosition == 0) {
                    CreatePriceAlertActivity.this.xOffset = firstVisibleItemOffset;
                } else {
                    CreatePriceAlertActivity.this.xOffset = (placeholderWidth + ((firstItemPosition - 1) * cellWidth)) + firstVisibleItemOffset;
                }
                double percentageOffset = ((double) CreatePriceAlertActivity.this.xOffset) / ((double) scrollViewContentWidth);
                if (percentageOffset < 0.0d) {
                    percentageOffset = 0.0d;
                } else if (percentageOffset > 1.0d) {
                    percentageOffset = 1.0d;
                }
                CreatePriceAlertActivity.this.selectedPrice = d * percentageOffset;
                if (CreatePriceAlertActivity.this.shouldShowDecimal(CreatePriceAlertActivity.this.currentPrice, CreatePriceAlertActivity.this.nativeCurrency)) {
                    CreatePriceAlertActivity.this.mBinding.tvCent.setText(CreatePriceAlertActivity.this.roundedCentString(CreatePriceAlertActivity.this.selectedPrice));
                }
                int selectedPriceSignificantDigits = (CreatePriceAlertActivity.this.numberOfDigits(CreatePriceAlertActivity.this.selectedPrice) - i) + 1;
                if (selectedPriceSignificantDigits < 0) {
                    selectedPriceSignificantDigits = 0;
                }
                if (CreatePriceAlertActivity.this.selectedPrice < d2 / ((double) CreatePriceAlertActivity.TICK_MARKS_PER_INCREMENT)) {
                    CreatePriceAlertActivity.this.selectedPrice = 0.0d;
                }
                CreatePriceAlertActivity.this.selectedPrice = Utils.roundToSignificantFigures(CreatePriceAlertActivity.this.selectedPrice, selectedPriceSignificantDigits, true);
                CreatePriceAlertActivity.this.updateOperatorViews(d3, CreatePriceAlertActivity.this.selectedPrice);
                CreatePriceAlertActivity.this.mBinding.tvDollar.setText(CreatePriceAlertActivity.this.mMoneyFormatterUtil.formatMoney(CreatePriceAlertActivity.this.mMoneyFormatterUtil.moneyFromValue(CreatePriceAlertActivity.this.unit.toString(), Long.toString((long) CreatePriceAlertActivity.this.selectedPrice)), EnumSet.of(Options.ROUND_0_DIGITS, Options.EXCLUDE_CURRENCY_SYMBOL)));
            }
        });
    }

    private void prepareItems(int itemCount, double increment, final double startPosition, double price) {
        this.mBinding.tvCurrency.setText(this.unit.getSymbol());
        this.mBinding.tvCurrency.setAlpha(1.0f);
        this.mBinding.tvDollar.setAlpha(1.0f);
        this.mBinding.tvCent.setAlpha(1.0f);
        this.mBinding.progressBar.setVisibility(8);
        this.mBinding.rvPrice.setVisibility(0);
        List<String> items = new ArrayList();
        for (int i = -1; i < itemCount; i++) {
            items.add(Integer.toString(i * ((int) increment)));
        }
        this.mBinding.rvPrice.setAdapter(new CreatePriceAlertAdapter(this, items, this.mBinding.rlScrollLayout.getWidth(), price, increment));
        this.linearLayoutManager = new LinearLayoutManager(this, 0, false);
        this.mBinding.rvPrice.setLayoutManager(this.linearLayoutManager);
        this.mBinding.rvPrice.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 1) {
                    CreatePriceAlertActivity.this.startScrollerTask();
                }
                return false;
            }
        });
        this.mBinding.rvPrice.post(new Runnable() {
            public void run() {
                CreatePriceAlertActivity.this.mBinding.rvPrice.scrollBy((int) startPosition, 0);
            }
        });
    }

    private void updateOperatorViews(double value, double selectedValue) {
        boolean createEnabled = true;
        value = Utils.floorOneDecimal(value);
        selectedValue = Utils.floorOneDecimal(selectedValue);
        if ((!shouldShowDecimal(this.currentPrice, this.nativeCurrency) && ((long) selectedValue) == ((long) value)) || (shouldShowDecimal(this.currentPrice, this.nativeCurrency) && (selectedValue == value || selectedValue == 0.0d))) {
            enableViews(this.belowViews, false);
            enableViews(this.aboveViews, false);
            createEnabled = false;
        } else if (selectedValue > value) {
            enableViews(this.belowViews, false);
            enableViews(this.aboveViews, true);
        } else if (selectedValue < value) {
            enableViews(this.belowViews, true);
            enableViews(this.aboveViews, false);
        }
        enableButton(this.mBinding.btnCreateNotification, createEnabled);
    }

    private void setViewColor(View view, int newColor) {
        if (view instanceof Button) {
            ((Button) view).setTextColor(newColor);
        } else {
            view.setBackgroundColor(newColor);
        }
    }

    private void setButtonColor(Button button, int newColor) {
        button.setTextColor(newColor);
    }

    public void finishActivity(View v) {
        finish();
    }

    private void checkDataLoadCompletion() {
        if (this.currentPriceUSD > 0.0d && this.currentPrice > 0.0d && this.zeroDecimalCurrencies != null) {
            prepareViewWithPrice(this.currentPrice);
        }
    }
}
