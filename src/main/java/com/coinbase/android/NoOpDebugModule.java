package com.coinbase.android;

import com.coinbase.android.settings.AccountSettingsPreferenceItem;
import com.coinbase.android.settings.AccountSettingsPresenter;
import com.coinbase.api.internal.models.wbl.PendingHold;
import java.util.LinkedList;
import java.util.List;
import rx.functions.Func0;
import rx.functions.Func1;

public class NoOpDebugModule {
    @ApplicationScope
    List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>> providesAccountSettingsPreferenceItemList() {
        return new LinkedList();
    }

    @ApplicationScope
    Func0<List<PendingHold>> providesAdditionalPendingHolds() {
        return NoOpDebugModule$$Lambda$1.lambdaFactory$();
    }
}
