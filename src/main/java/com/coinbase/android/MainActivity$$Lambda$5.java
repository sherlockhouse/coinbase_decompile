package com.coinbase.android;

import android.os.Bundle;
import com.coinbase.android.buysell.SellController;
import rx.functions.Func1;

final /* synthetic */ class MainActivity$$Lambda$5 implements Func1 {
    private static final MainActivity$$Lambda$5 instance = new MainActivity$$Lambda$5();

    private MainActivity$$Lambda$5() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return new SellController((Bundle) obj);
    }
}
