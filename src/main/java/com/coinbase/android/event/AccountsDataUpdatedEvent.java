package com.coinbase.android.event;

import org.json.JSONObject;

public class AccountsDataUpdatedEvent {
    public JSONObject data;

    public AccountsDataUpdatedEvent(JSONObject data) {
        this.data = data;
    }
}
