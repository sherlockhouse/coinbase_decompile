package org.joda.time.chrono;

import java.util.Locale;

final class GJMonthOfYearDateTimeField extends BasicMonthOfYearDateTimeField {
    GJMonthOfYearDateTimeField(BasicChronology basicChronology) {
        super(basicChronology, 2);
    }

    public String getAsText(int i, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).monthOfYearValueToText(i);
    }

    public String getAsShortText(int i, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).monthOfYearValueToShortText(i);
    }

    protected int convertText(String str, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).monthOfYearTextToValue(str);
    }

    public int getMaximumTextLength(Locale locale) {
        return GJLocaleSymbols.forLocale(locale).getMonthMaxTextLength();
    }
}
