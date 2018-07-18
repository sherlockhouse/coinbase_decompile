package com.coinbase.android.widgets;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.Constants;
import com.coinbase.android.Log;
import com.coinbase.android.R;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.supportedCurrencies.Data;
import com.coinbase.v2.models.supportedCurrencies.SupportedCurrencies;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import org.joda.money.CurrencyUnit;
import org.joda.money.IllegalCurrencyException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ActivityScope
public class WidgetChooseCurrencyActivity extends ListActivity {
    @Inject
    LoginManager mLoginManager;

    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        ((ComponentProvider) getApplicationContext()).applicationComponent().genericActivitySubcomponent().inject(this);
        if (getListView() == null) {
            Log.e("WidgetChooseCurrencyActivity", "No list view for widget");
            finish();
            return;
        }
        this.mLoginManager.getClient().getSupportedCurrencies(new CallbackWithRetrofit<SupportedCurrencies>() {
            public void onResponse(Call<SupportedCurrencies> call, Response<SupportedCurrencies> response, Retrofit retrofit) {
                if (response.isSuccessful()) {
                    List<CurrencyUnit> currencies = new ArrayList();
                    for (Data currency : ((SupportedCurrencies) response.body()).getData()) {
                        try {
                            currencies.add(CurrencyUnit.getInstance(currency.getId()));
                        } catch (IllegalCurrencyException e) {
                        }
                    }
                    Collections.sort(currencies, new Comparator<CurrencyUnit>() {
                        public int compare(CurrencyUnit lhs, CurrencyUnit rhs) {
                            return lhs.getCurrencyCode().compareToIgnoreCase(rhs.getCurrencyCode());
                        }
                    });
                    WidgetChooseCurrencyActivity.this.loadCurrencies(currencies);
                    return;
                }
                Utils.showMessage(WidgetChooseCurrencyActivity.this, (int) R.string.account_list_error, 0);
            }

            public void onFailure(Call<SupportedCurrencies> call, Throwable t) {
                Utils.showMessage(WidgetChooseCurrencyActivity.this, (int) R.string.account_list_error, 0);
            }
        });
        setResult(0);
    }

    private void loadCurrencies(final List<CurrencyUnit> currencies) {
        setListAdapter(new BaseAdapter() {
            public int getCount() {
                if (currencies == null) {
                    return 0;
                }
                return currencies.size();
            }

            public CurrencyUnit getItem(int i) {
                return (CurrencyUnit) currencies.get(i);
            }

            public long getItemId(int i) {
                return (long) i;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = View.inflate(WidgetChooseCurrencyActivity.this, 17367043, null);
                }
                ((TextView) view.findViewById(16908308)).setText(getItem(i).getCurrencyCode());
                return view;
            }
        });
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        onCurrencyChosen(((CurrencyUnit) l.getItemAtPosition(position)).getCurrencyCode());
    }

    public void onCurrencyChosen(String currency) {
        int widgetId = getIntent().getIntExtra("appWidgetId", -1);
        Editor e = PreferenceManager.getDefaultSharedPreferences(this).edit();
        e.putString(String.format(Constants.KEY_WIDGET_CURRENCY, new Object[]{Integer.valueOf(widgetId)}), currency);
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
