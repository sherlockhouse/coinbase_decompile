package com.coinbase.android.signin.state;

import java.util.HashMap;
import rx.functions.Func1;

/* compiled from: StateSelectorPresenter.kt */
final class StateSelectorPresenter$onShow$1<T, R> implements Func1<HashMap<String, String>, Boolean> {
    public static final StateSelectorPresenter$onShow$1 INSTANCE = new StateSelectorPresenter$onShow$1();

    StateSelectorPresenter$onShow$1() {
    }

    public final boolean call(HashMap<String, String> state) {
        return state != null;
    }
}
