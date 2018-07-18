package com.coinbase.android.ui;

import com.coinbase.api.internal.models.currency.Data;
import java.util.List;
import rx.Observable;

public interface CurrencyTabFilter {
    List<Data> filter(List<Data> list);

    Observable<Void> filterUpdated();
}
