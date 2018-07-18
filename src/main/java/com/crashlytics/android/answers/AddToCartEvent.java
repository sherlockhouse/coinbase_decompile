package com.crashlytics.android.answers;

import java.math.BigDecimal;

public class AddToCartEvent extends PredefinedEvent<AddToCartEvent> {
    static final BigDecimal MICRO_CONSTANT = BigDecimal.valueOf(1000000);

    String getPredefinedType() {
        return "addToCart";
    }
}
