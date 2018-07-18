package org.joda.money.format;

import java.util.Locale;

public final class MoneyPrintContext {
    private Locale locale;

    MoneyPrintContext(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }
}
