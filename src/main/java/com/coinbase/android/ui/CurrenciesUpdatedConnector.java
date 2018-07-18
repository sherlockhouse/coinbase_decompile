package com.coinbase.android.ui;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.coinbase.android.Log;
import com.coinbase.android.R;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.currency.Currencies;
import com.coinbase.api.internal.models.currency.Data;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.joda.money.CurrencyUnit;
import rx.subjects.BehaviorSubject;

public class CurrenciesUpdatedConnector {
    private final Context mContext;
    private BehaviorSubject<List<Data>> mCurrenciesUpdatedSubject;
    private Map<String, Data> mCurrencyDataMap = new HashMap();

    public CurrenciesUpdatedConnector(Application application) {
        this.mContext = application;
        List<Data> currencyList = fetchCurrenciesLocally();
        registerCurrencies(currencyList);
        this.mCurrenciesUpdatedSubject = BehaviorSubject.create(currencyList);
    }

    public BehaviorSubject<List<Data>> get() {
        return this.mCurrenciesUpdatedSubject;
    }

    public synchronized void registerCurrencies(List<Data> currencyList) {
        if (currencyList != null) {
            try {
                this.mCurrencyDataMap.clear();
                for (Data currency : currencyList) {
                    if (currency.getCode() != null) {
                        CurrencyUnit.registerCurrency(currency.getCode(), -1, currency.getExponent(), new ArrayList(), true);
                        this.mCurrencyDataMap.put(currency.getCode().toUpperCase(), currency);
                    }
                }
            } catch (Exception e) {
                Log.e("RegisterCurrency", "Exception registering currency: ", e);
            }
        }
    }

    public synchronized Data getCurrencyByCode(String code) {
        Data data;
        if (TextUtils.isEmpty(code)) {
            data = null;
        } else {
            data = (Data) this.mCurrencyDataMap.get(code.toUpperCase());
        }
        return data;
    }

    public boolean isValidCurrency(String code) {
        return getCurrencyByCode(code) != null;
    }

    private List<Data> fetchCurrenciesLocally() {
        List<Data> currencyList = new ArrayList();
        try {
            String jsonString = IOUtils.toString(this.mContext.getResources().openRawResource(R.raw.currencies), "UTF-8");
            if (jsonString != null) {
                Currencies currencies = (Currencies) new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().fromJson(jsonString, Currencies.class);
                if (!(currencies == null || currencies.getData() == null)) {
                    currencyList.addAll(currencies.getData());
                }
            }
        } catch (Exception e) {
        }
        return currencyList;
    }
}
