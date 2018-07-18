package com.coinbase.android.signin.state;

import rx.functions.Action1;

/* compiled from: StateSelectorPresenter.kt */
final class StateSelectorPresenter$onShow$3<T> implements Action1<Throwable> {
    final /* synthetic */ StateSelectorPresenter this$0;

    StateSelectorPresenter$onShow$3(StateSelectorPresenter stateSelectorPresenter) {
        this.this$0 = stateSelectorPresenter;
    }

    public final void call(Throwable t) {
        this.this$0.mLogger.error("Error setting state chosen", t);
    }
}
