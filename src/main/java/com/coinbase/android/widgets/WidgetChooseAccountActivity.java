package com.coinbase.android.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.R;
import com.coinbase.android.accounts.AccountsFragment;
import com.coinbase.android.accounts.AccountsFragment.ParentActivity;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Data;
import javax.inject.Inject;

@ActivityScope
public class WidgetChooseAccountActivity extends FragmentActivity implements ParentActivity {
    @Inject
    LoginManager loginManager;

    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        ((ComponentProvider) getApplicationContext()).applicationComponent().genericActivitySubcomponent().inject(this);
        if (this.loginManager.getAccounts().size() <= 0) {
            Utils.showMessage((Context) this, (int) R.string.please_sign_in_widget, 0);
            return;
        }
        AccountsFragment f = new AccountsFragment();
        Bundle args = new Bundle();
        args.putBoolean("widgetMode", true);
        f.setArguments(args);
        f.show(getSupportFragmentManager(), "accounts");
        setResult(0);
    }

    public void onAccountChosen(Data account) {
        int widgetId = getIntent().getIntExtra("appWidgetId", -1);
        Editor e = PreferenceManager.getDefaultSharedPreferences(this).edit();
        e.putString(String.format(Constants.KEY_WIDGET_ACCOUNT, new Object[]{Integer.valueOf(widgetId)}), account.getId());
        e.putString(String.format(Constants.KEY_WIDGET_ACCOUNT_TYPE, new Object[]{Integer.valueOf(widgetId)}), account.getType().toString());
        e.commit();
        Intent refresh = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
        refresh.putExtra("appWidgetIds", new int[]{widgetId});
        refresh.setPackage(getPackageName());
        sendBroadcast(refresh);
        Intent resultValue = new Intent();
        resultValue.putExtra("appWidgetId", widgetId);
        setResult(-1, resultValue);
        finish();
    }
}
