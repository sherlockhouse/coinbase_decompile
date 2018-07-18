package com.coinbase.android.ui;

import java.util.List;
import rx.functions.Func1;

final /* synthetic */ class CurrencyTabPresenter$$Lambda$1 implements Func1 {
    private final CurrencyTabPresenter arg$1;

    private CurrencyTabPresenter$$Lambda$1(CurrencyTabPresenter currencyTabPresenter) {
        this.arg$1 = currencyTabPresenter;
    }

    public static Func1 lambdaFactory$(CurrencyTabPresenter currencyTabPresenter) {
        return new CurrencyTabPresenter$$Lambda$1(currencyTabPresenter);
    }

    public Object call(Object obj) {
        return this.arg$1.mCurrencyTabFilter.filter((List) obj);
    }
}
