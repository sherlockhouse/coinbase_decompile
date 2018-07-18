package com.coinbase.android.signin;

import android.util.Pair;
import com.coinbase.api.internal.models.idology.options.Data;
import com.coinbase.api.internal.models.idology.options.IdologyOptions.OptionType;
import rx.subjects.PublishSubject;

public final class IdologyOptionSelectedConnector {
    private final PublishSubject<Pair<Data, OptionType>> mSubject;

    public IdologyOptionSelectedConnector() {
        this(PublishSubject.create());
    }

    public IdologyOptionSelectedConnector(PublishSubject<Pair<Data, OptionType>> subject) {
        this.mSubject = subject;
    }

    public PublishSubject<Pair<Data, OptionType>> get() {
        return this.mSubject;
    }
}
