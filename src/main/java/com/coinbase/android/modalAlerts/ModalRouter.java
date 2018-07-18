package com.coinbase.android.modalAlerts;

import rx.Observable;
import rx.functions.Action0;

public interface ModalRouter {
    Observable<Action0> route();
}
