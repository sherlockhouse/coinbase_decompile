package com.tenjin.android;

class TenjinSDK$EventName {
    private String currencyCode;
    private String dataSignature;
    private Callback deferredDeeplinkCallback;
    private int intValue;
    private String name;
    private String productId;
    private String purchaseData;
    private int quantity;
    final /* synthetic */ TenjinSDK this$0;
    private String type;
    private double unitPrice;
    private String value;

    TenjinSDK$EventName(TenjinSDK this$0, String name) {
        this.this$0 = this$0;
        this.type = null;
        this.name = null;
        this.value = null;
        this.intValue = 0;
        this.productId = null;
        this.currencyCode = null;
        this.quantity = 0;
        this.unitPrice = 0.0d;
        this.purchaseData = null;
        this.dataSignature = null;
        this.deferredDeeplinkCallback = null;
        this.type = "eventName";
        this.name = name;
    }

    TenjinSDK$EventName(TenjinSDK this$0, String name, String value) {
        this.this$0 = this$0;
        this.type = null;
        this.name = null;
        this.value = null;
        this.intValue = 0;
        this.productId = null;
        this.currencyCode = null;
        this.quantity = 0;
        this.unitPrice = 0.0d;
        this.purchaseData = null;
        this.dataSignature = null;
        this.deferredDeeplinkCallback = null;
        this.type = "eventNameValue";
        this.name = name;
        this.value = value;
    }

    TenjinSDK$EventName(TenjinSDK this$0, String name, int intValue) {
        this.this$0 = this$0;
        this.type = null;
        this.name = null;
        this.value = null;
        this.intValue = 0;
        this.productId = null;
        this.currencyCode = null;
        this.quantity = 0;
        this.unitPrice = 0.0d;
        this.purchaseData = null;
        this.dataSignature = null;
        this.deferredDeeplinkCallback = null;
        this.type = "eventNameIntValue";
        this.name = name;
        this.intValue = intValue;
    }

    TenjinSDK$EventName(TenjinSDK this$0, String productId, String currencyCode, int quantity, double unitPrice) {
        this.this$0 = this$0;
        this.type = null;
        this.name = null;
        this.value = null;
        this.intValue = 0;
        this.productId = null;
        this.currencyCode = null;
        this.quantity = 0;
        this.unitPrice = 0.0d;
        this.purchaseData = null;
        this.dataSignature = null;
        this.deferredDeeplinkCallback = null;
        this.type = "eventNameTransaction";
        this.productId = productId;
        this.currencyCode = currencyCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    TenjinSDK$EventName(TenjinSDK this$0, String productId, String currencyCode, int quantity, double unitPrice, String purchaseData, String dataSignature) {
        this.this$0 = this$0;
        this.type = null;
        this.name = null;
        this.value = null;
        this.intValue = 0;
        this.productId = null;
        this.currencyCode = null;
        this.quantity = 0;
        this.unitPrice = 0.0d;
        this.purchaseData = null;
        this.dataSignature = null;
        this.deferredDeeplinkCallback = null;
        this.type = "eventNameTransactionData";
        this.productId = productId;
        this.currencyCode = currencyCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.purchaseData = purchaseData;
        this.dataSignature = dataSignature;
    }

    TenjinSDK$EventName(TenjinSDK this$0, String eventId, Callback deferredDeeplinkCallback) {
        this.this$0 = this$0;
        this.type = null;
        this.name = null;
        this.value = null;
        this.intValue = 0;
        this.productId = null;
        this.currencyCode = null;
        this.quantity = 0;
        this.unitPrice = 0.0d;
        this.purchaseData = null;
        this.dataSignature = null;
        this.deferredDeeplinkCallback = null;
        this.type = eventId;
        this.deferredDeeplinkCallback = deferredDeeplinkCallback;
    }

    private String getType() {
        return this.type;
    }

    private String getName() {
        return this.name;
    }

    private String getValue() {
        return this.value;
    }

    private int getIntValue() {
        return this.intValue;
    }

    private String getProductId() {
        return this.productId;
    }

    private String getCurrencyCode() {
        return this.currencyCode;
    }

    private int getQuantity() {
        return this.quantity;
    }

    private double getUnitPrice() {
        return this.unitPrice;
    }

    private String getPurchaseData() {
        return this.purchaseData;
    }

    private String getDataSignature() {
        return this.dataSignature;
    }

    private Callback getDeferredDeeplinkCallback() {
        return this.deferredDeeplinkCallback;
    }
}
