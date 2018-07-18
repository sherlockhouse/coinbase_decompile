package com.coinbase.android.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.preference.PreferenceManager;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.Constants;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.MoneyFormatterUtil.Options;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Account;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.account.Data.Type;
import java.util.EnumSet;
import org.joda.money.BigMoneyProvider;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateWidgetBalanceTask {
    private final LoginManager mLoginManager;
    private final MixpanelTracking mMixpanelTracking;
    private final MoneyFormatterUtil mMoneyFormatterUtil;

    public interface WidgetUpdater {
        void updateWidget(Context context, AppWidgetManager appWidgetManager, int i, String str);
    }

    public UpdateWidgetBalanceTask(LoginManager loginManager, MoneyFormatterUtil moneyFormatterUtil, MixpanelTracking mixpanelTracking) {
        this.mLoginManager = loginManager;
        this.mMoneyFormatterUtil = moneyFormatterUtil;
        this.mMixpanelTracking = mixpanelTracking;
    }

    public void run(int widgetId, WidgetUpdater updater, Context context, Runnable serviceCallback) {
        String accountId;
        Type accountType;
        this.mMixpanelTracking.trackEventOnce(MixpanelTracking.EVENT_BALANCE_WIDGET_ENABLED);
        try {
            Object _accountId = PreferenceManager.getDefaultSharedPreferences(context).getAll().get(String.format(Constants.KEY_WIDGET_ACCOUNT, new Object[]{Integer.valueOf(widgetId)}));
            Object _accountType = PreferenceManager.getDefaultSharedPreferences(context).getAll().get(String.format(Constants.KEY_WIDGET_ACCOUNT_TYPE, new Object[]{Integer.valueOf(widgetId)}));
            accountId = _accountId instanceof String ? (String) _accountId : null;
            accountType = _accountType instanceof String ? Type.toType((String) _accountType) : null;
        } catch (ClassCastException e) {
            accountId = null;
            accountType = null;
        }
        if (accountId == null || accountType == null) {
            accountId = this.mLoginManager.getActiveAccountId();
            Data account = AccountUtils.getAccount(this.mLoginManager.getAccounts(), accountId);
            if (account != null) {
                accountType = account.getType();
            }
        }
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        updateWidget(updater, manager, widgetId, null, context);
        if (accountId == null || accountType == null) {
            updateWidget(updater, manager, widgetId, "Invalid", context);
            return;
        }
        final WidgetUpdater widgetUpdater = updater;
        final AppWidgetManager appWidgetManager = manager;
        final int i = widgetId;
        final Context context2 = context;
        final Runnable runnable = serviceCallback;
        this.mLoginManager.getClient().getAccount(accountId, new CallbackWithRetrofit<Account>() {
            public void onResponse(Call<Account> call, Response<Account> response, Retrofit retrofit) {
                if (response.isSuccessful()) {
                    String balanceText;
                    Data account = ((Account) response.body()).getData();
                    BigMoneyProvider balance = AccountUtils.getAccountBalanceMoney(account, UpdateWidgetBalanceTask.this.mMoneyFormatterUtil);
                    if (account.getType() == Type.FIAT) {
                        balanceText = UpdateWidgetBalanceTask.this.mMoneyFormatterUtil.formatMoney(balance);
                    } else {
                        balanceText = UpdateWidgetBalanceTask.this.mMoneyFormatterUtil.formatMoney(balance, EnumSet.of(Options.ROUND_4_DIGITS));
                    }
                    UpdateWidgetBalanceTask.this.updateWidget(widgetUpdater, appWidgetManager, i, balanceText, context2);
                    runnable.run();
                    return;
                }
                UpdateWidgetBalanceTask.this.updateWidget(widgetUpdater, appWidgetManager, i, "Invalid", context2);
            }

            public void onFailure(Call<Account> call, Throwable t) {
                UpdateWidgetBalanceTask.this.updateWidget(widgetUpdater, appWidgetManager, i, "Invalid", context2);
                runnable.run();
            }
        });
    }

    private void updateWidget(WidgetUpdater updater, AppWidgetManager manager, int widgetId, String balanceText, Context context) {
        updater.updateWidget(context, manager, widgetId, balanceText);
    }
}
