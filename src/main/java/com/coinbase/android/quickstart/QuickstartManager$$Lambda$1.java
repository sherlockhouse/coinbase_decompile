package com.coinbase.android.quickstart;

import android.util.Pair;
import com.coinbase.android.quickstart.QuickstartManager.Type;
import java.util.List;
import rx.functions.Func1;

final /* synthetic */ class QuickstartManager$$Lambda$1 implements Func1 {
    private final QuickstartManager arg$1;
    private final List arg$2;
    private final Type arg$3;

    private QuickstartManager$$Lambda$1(QuickstartManager quickstartManager, List list, Type type) {
        this.arg$1 = quickstartManager;
        this.arg$2 = list;
        this.arg$3 = type;
    }

    public static Func1 lambdaFactory$(QuickstartManager quickstartManager, List list, Type type) {
        return new QuickstartManager$$Lambda$1(quickstartManager, list, type);
    }

    public Object call(Object obj) {
        return QuickstartManager.lambda$updateQuickstartItems$0(this.arg$1, this.arg$2, this.arg$3, (Pair) obj);
    }
}
