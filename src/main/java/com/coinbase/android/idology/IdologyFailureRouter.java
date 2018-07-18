package com.coinbase.android.idology;

import com.coinbase.api.internal.models.idology.Data;

public interface IdologyFailureRouter {
    void routeToFailure(Data data);
}
