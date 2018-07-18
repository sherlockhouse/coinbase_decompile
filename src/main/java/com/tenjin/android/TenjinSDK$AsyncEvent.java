package com.tenjin.android;

import android.os.AsyncTask;
import android.util.Base64;
import com.coinbase.android.utils.CryptoUri;
import java.util.HashMap;
import java.util.Map;

class TenjinSDK$AsyncEvent extends AsyncTask<Void, Void, Boolean> {
    private String currencyCode;
    private String dataSignature;
    private String eventId;
    private int intValue;
    private String name;
    private String postURL;
    private String productId;
    private String purchaseData;
    private int quantity;
    final /* synthetic */ TenjinSDK this$0;
    private String type;
    private double unitPrice;
    private String value;

    private TenjinSDK$AsyncEvent(TenjinSDK tenjinSDK, String name) {
        this.this$0 = tenjinSDK;
        this.postURL = "https://track.tenjin.io/v0/event";
        this.intValue = 0;
        this.type = "eventName";
        this.eventId = TenjinSDK.access$3200(tenjinSDK, name);
        this.name = name;
    }

    private TenjinSDK$AsyncEvent(TenjinSDK tenjinSDK, String name, String value) {
        this.this$0 = tenjinSDK;
        this.postURL = "https://track.tenjin.io/v0/event";
        this.intValue = 0;
        this.type = "eventNameValue";
        this.eventId = TenjinSDK.access$3300(tenjinSDK, name, value);
        this.name = name;
        this.value = value;
    }

    private TenjinSDK$AsyncEvent(TenjinSDK tenjinSDK, String name, int intValue) {
        this.this$0 = tenjinSDK;
        this.postURL = "https://track.tenjin.io/v0/event";
        this.intValue = 0;
        this.type = "eventNameIntValue";
        this.eventId = TenjinSDK.access$3400(tenjinSDK, name, intValue);
        this.name = name;
        this.intValue = intValue;
    }

    private TenjinSDK$AsyncEvent(TenjinSDK tenjinSDK, String productId, String currencyCode, int quantity, double unitPrice) {
        this.this$0 = tenjinSDK;
        this.postURL = "https://track.tenjin.io/v0/event";
        this.intValue = 0;
        this.type = "eventNameTransaction";
        this.eventId = TenjinSDK.access$3500(tenjinSDK, productId, currencyCode, quantity, unitPrice);
        this.postURL = "https://track.tenjin.io/v0/purchase";
        this.productId = productId;
        this.currencyCode = currencyCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    private TenjinSDK$AsyncEvent(TenjinSDK tenjinSDK, String productId, String currencyCode, int quantity, double unitPrice, String purchaseData, String dataSignature) {
        this.this$0 = tenjinSDK;
        this.postURL = "https://track.tenjin.io/v0/event";
        this.intValue = 0;
        this.type = "eventNameTransactionData";
        this.eventId = TenjinSDK.access$3600(tenjinSDK, productId, currencyCode, quantity, unitPrice, purchaseData, dataSignature);
        this.postURL = "https://track.tenjin.io/v0/purchase";
        this.productId = productId;
        this.currencyCode = currencyCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.purchaseData = purchaseData;
        this.dataSignature = dataSignature;
    }

    protected Boolean doInBackground(Void... params) {
        try {
            String eventName = this.name;
            if (this.name == null) {
                eventName = "";
            }
            Map<String, String> postParams = TenjinSDK.access$3700(this.this$0);
            try {
                postParams.put("event", eventName);
                if (this.value == null && this.intValue != 0) {
                    postParams.put(CryptoUri.VALUE, Integer.toString(this.intValue));
                }
                if (this.value != null) {
                    postParams.put(CryptoUri.VALUE, this.value);
                }
                if (this.postURL.equals("https://track.tenjin.io/v0/purchase")) {
                    postParams.put("currency", this.currencyCode);
                    postParams.put("product_id", this.productId);
                    postParams.put("quantity", String.valueOf(this.quantity));
                    postParams.put("price", String.valueOf(this.unitPrice));
                    if (this.dataSignature != null) {
                        postParams.put("signature", this.dataSignature);
                    }
                    if (this.purchaseData != null) {
                        postParams.put("receipt", this.purchaseData);
                    }
                }
                String type = "Basic " + Base64.encodeToString(TenjinSDK.access$500(this.this$0).getBytes(), 10);
                Map<String, String> headers = new HashMap();
                headers.put("Authorization", type);
                return Boolean.valueOf(new HttpConnection().connect(this.postURL, postParams, headers));
            } catch (Exception e) {
                return Boolean.valueOf(false);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    protected void onPostExecute(Boolean success) {
        if (success.booleanValue()) {
            TenjinSDK.access$3800(this.this$0, this.eventId);
            TenjinSDK.access$3900(this.this$0, this.eventId);
            return;
        }
        TenjinSDK.access$4000(this.this$0, this);
    }
}
