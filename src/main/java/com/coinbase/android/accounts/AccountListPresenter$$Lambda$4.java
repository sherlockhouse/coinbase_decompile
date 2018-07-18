package com.coinbase.android.accounts;

import com.coinbase.v2.models.account.Data;
import java.util.Comparator;
import java.util.List;

final /* synthetic */ class AccountListPresenter$$Lambda$4 implements Comparator {
    private final List arg$1;

    private AccountListPresenter$$Lambda$4(List list) {
        this.arg$1 = list;
    }

    public static Comparator lambdaFactory$(List list) {
        return new AccountListPresenter$$Lambda$4(list);
    }

    public int compare(Object obj, Object obj2) {
        return AccountListPresenter.lambda$sortAccountList$3(this.arg$1, (Data) obj, (Data) obj2);
    }
}
