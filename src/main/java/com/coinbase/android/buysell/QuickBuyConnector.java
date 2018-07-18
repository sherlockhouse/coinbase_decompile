package com.coinbase.android.buysell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import rx.subjects.PublishSubject;

public class QuickBuyConnector {
    static final List<String> QUICK_BUY_VALUES = new ArrayList(Arrays.asList(new String[]{"50", "250", "1000", "5000"}));
    private final PublishSubject<String> mSubject;

    public QuickBuyConnector() {
        this(PublishSubject.create());
    }

    public QuickBuyConnector(PublishSubject<String> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<String> get() {
        return this.mSubject;
    }
}
