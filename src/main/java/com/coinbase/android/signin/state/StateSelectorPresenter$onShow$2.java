package com.coinbase.android.signin.state;

import com.coinbase.android.utils.analytics.MixpanelTracking;
import java.util.HashMap;
import rx.functions.Action1;

/* compiled from: StateSelectorPresenter.kt */
final class StateSelectorPresenter$onShow$2<T> implements Action1<HashMap<String, String>> {
    final /* synthetic */ StateSelectorPresenter this$0;

    StateSelectorPresenter$onShow$2(StateSelectorPresenter stateSelectorPresenter) {
        this.this$0 = stateSelectorPresenter;
    }

    public final void call(HashMap<String, String> state) {
        this.this$0.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_SELECT_STATE_TAPPED_STATE, "state", (String) state.get("name"));
        this.this$0.mSelectedState = state;
        this.this$0.mScreen.setStateChosen((String) state.get("name"));
    }
}
