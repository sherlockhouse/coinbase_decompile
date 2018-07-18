package com.coinbase.android.ui;

import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class CurrencyTabPresenter$$Lambda$3 implements Action1 {
    private final CurrencyTabPresenter arg$1;

    private CurrencyTabPresenter$$Lambda$3(CurrencyTabPresenter currencyTabPresenter) {
        this.arg$1 = currencyTabPresenter;
    }

    public static Action1 lambdaFactory$(CurrencyTabPresenter currencyTabPresenter) {
        return new CurrencyTabPresenter$$Lambda$3(currencyTabPresenter);
    }

    public void call(Object obj) {
        this.arg$1.showCurrencies((List) obj);
    }
}
