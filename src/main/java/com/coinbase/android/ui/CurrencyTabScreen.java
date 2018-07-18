package com.coinbase.android.ui;

import com.coinbase.api.internal.models.currency.Data;
import java.util.List;

public interface CurrencyTabScreen {
    void populateTabLayout(List<Data> list);
}
