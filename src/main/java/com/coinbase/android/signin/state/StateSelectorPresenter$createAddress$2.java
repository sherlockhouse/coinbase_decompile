package com.coinbase.android.signin.state;

import com.coinbase.android.utils.Utils;
import rx.functions.Action1;

/* compiled from: StateSelectorPresenter.kt */
final class StateSelectorPresenter$createAddress$2<T> implements Action1<Throwable> {
    final /* synthetic */ StateSelectorPresenter this$0;

    StateSelectorPresenter$createAddress$2(StateSelectorPresenter stateSelectorPresenter) {
        this.this$0 = stateSelectorPresenter;
    }

    public final void call(Throwable t) {
        this.this$0.mScreen.hideProgressBar();
        this.this$0.mSnackBarWrapper.show(Utils.getMessage(this.this$0.mContext, t));
    }
}
