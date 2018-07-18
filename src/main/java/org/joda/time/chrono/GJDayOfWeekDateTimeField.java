package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.PreciseDurationDateTimeField;

final class GJDayOfWeekDateTimeField extends PreciseDurationDateTimeField {
    private final BasicChronology iChronology;

    GJDayOfWeekDateTimeField(BasicChronology basicChronology, DurationField durationField) {
        super(DateTimeFieldType.dayOfWeek(), durationField);
        this.iChronology = basicChronology;
    }

    public int get(long j) {
        return this.iChronology.getDayOfWeek(j);
    }

    public String getAsText(int i, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).dayOfWeekValueToText(i);
    }

    public String getAsShortText(int i, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).dayOfWeekValueToShortText(i);
    }

    protected int convertText(String str, Locale locale) {
        return GJLocaleSymbols.forLocale(locale).dayOfWeekTextToValue(str);
    }

    public DurationField getRangeDurationField() {
        return this.iChronology.weeks();
    }

    public int getMinimumValue() {
        return 1;
    }

    public int getMaximumValue() {
        return 7;
    }

    public int getMaximumTextLength(Locale locale) {
        return GJLocaleSymbols.forLocale(locale).getDayOfWeekMaxTextLength();
    }
}
