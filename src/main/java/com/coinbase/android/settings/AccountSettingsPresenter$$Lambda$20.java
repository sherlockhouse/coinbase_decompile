package com.coinbase.android.settings;

import com.coinbase.v2.models.user.Data;
import java.util.List;
import rx.functions.Func2;

final /* synthetic */ class AccountSettingsPresenter$$Lambda$20 implements Func2 {
    private final AccountSettingsPresenter arg$1;
    private final Data arg$2;

    private AccountSettingsPresenter$$Lambda$20(AccountSettingsPresenter accountSettingsPresenter, Data data) {
        this.arg$1 = accountSettingsPresenter;
        this.arg$2 = data;
    }

    public static Func2 lambdaFactory$(AccountSettingsPresenter accountSettingsPresenter, Data data) {
        return new AccountSettingsPresenter$$Lambda$20(accountSettingsPresenter, data);
    }

    public Object call(Object obj, Object obj2) {
        return this.arg$1.combineWblAndPaymentMethodsSplitTest((List) obj2, this.arg$2);
    }
}
