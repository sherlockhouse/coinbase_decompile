package com.coinbase.android.pricechart;

import com.coinbase.android.pricechart.PriceChartPresenter.Period;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.util.TextUtils;
import rx.subjects.BehaviorSubject;

public class PriceChartDataUpdatedConnector {
    private final Map<String, BehaviorSubject<PriceChartData>> mSubjectMap;

    public PriceChartDataUpdatedConnector() {
        this(new HashMap());
    }

    public PriceChartDataUpdatedConnector(Map<String, BehaviorSubject<PriceChartData>> subjectMap) {
        this.mSubjectMap = subjectMap;
    }

    public synchronized BehaviorSubject<PriceChartData> get(String baseCurrencyCode, Period period) {
        BehaviorSubject<PriceChartData> create;
        String key = getPriceChartDataKey(baseCurrencyCode, period);
        if (TextUtils.isEmpty(key)) {
            create = BehaviorSubject.create();
        } else if (this.mSubjectMap.containsKey(key)) {
            create = (BehaviorSubject) this.mSubjectMap.get(key);
        } else {
            this.mSubjectMap.put(key, BehaviorSubject.create());
            create = (BehaviorSubject) this.mSubjectMap.get(key);
        }
        return create;
    }

    private String getPriceChartDataKey(String baseCurrencyCode, Period period) {
        if (baseCurrencyCode == null || period == null) {
            return null;
        }
        return baseCurrencyCode.toLowerCase() + "_" + period.toString().toLowerCase();
    }
}
