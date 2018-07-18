package com.coinbase.android.signin.state;

import android.util.Pair;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.models.address.Address;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.functions.Action1;

/* compiled from: StateSelectorPresenter.kt */
final class StateSelectorPresenter$createAddress$1<T> implements Action1<Pair<Response<Address>, Retrofit>> {
    final /* synthetic */ StateSelectorPresenter this$0;

    StateSelectorPresenter$createAddress$1(StateSelectorPresenter stateSelectorPresenter) {
        this.this$0 = stateSelectorPresenter;
    }

    public final void call(Pair<Response<Address>, Retrofit> pair) {
        this.this$0.mScreen.hideProgressBar();
        Response response = pair.first;
        if (response.isSuccessful()) {
            this.this$0.mStateDisclosureFinishedConnector.get().onNext(new ClassConsumableEvent(this.this$0.mSelectedState));
            this.this$0.mScreen.showProgressBar();
            this.this$0.mSubscription.add(this.this$0.mAuthRouter.routeToNext());
            return;
        }
        this.this$0.mSnackBarWrapper.show(Utils.getErrorMessage(response, (Retrofit) pair.second));
    }
}
