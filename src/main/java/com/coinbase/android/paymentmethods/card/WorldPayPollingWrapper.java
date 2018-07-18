package com.coinbase.android.paymentmethods.card;

public interface WorldPayPollingWrapper {
    long getPollDelay();

    long getPollMaxTime();
}
