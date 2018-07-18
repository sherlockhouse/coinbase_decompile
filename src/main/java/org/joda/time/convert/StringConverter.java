package org.joda.time.convert;

import com.coinbase.android.ui.NumericKeypadConnector;
import org.joda.time.Chronology;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

class StringConverter extends AbstractConverter implements DurationConverter, InstantConverter, IntervalConverter, PartialConverter, PeriodConverter {
    static final StringConverter INSTANCE = new StringConverter();

    protected StringConverter() {
    }

    public long getInstantMillis(Object obj, Chronology chronology) {
        return ISODateTimeFormat.dateTimeParser().withChronology(chronology).parseMillis((String) obj);
    }

    public long getDurationMillis(Object obj) {
        int i = 1;
        String str = (String) obj;
        int length = str.length();
        if (length < 4 || !((str.charAt(0) == 'P' || str.charAt(0) == 'p') && ((str.charAt(1) == 'T' || str.charAt(1) == 't') && (str.charAt(length - 1) == 'S' || str.charAt(length - 1) == 's')))) {
            throw new IllegalArgumentException("Invalid format: \"" + str + '\"');
        }
        long parseLong;
        long j;
        String substring = str.substring(2, length - 1);
        length = 0;
        int i2 = 0;
        int i3 = -1;
        while (length < substring.length()) {
            if (substring.charAt(length) < NumericKeypadConnector.ZERO || substring.charAt(length) > '9') {
                if (length == 0 && substring.charAt(0) == '-') {
                    i2 = 1;
                } else {
                    int i4;
                    if (i2 != 0) {
                        i4 = 1;
                    } else {
                        i4 = 0;
                    }
                    if (length > i4 && substring.charAt(length) == NumericKeypadConnector.DOT && i3 == -1) {
                        i3 = length;
                    } else {
                        throw new IllegalArgumentException("Invalid format: \"" + str + '\"');
                    }
                }
            }
            length++;
        }
        if (i2 == 0) {
            i = 0;
        }
        if (i3 > 0) {
            parseLong = Long.parseLong(substring.substring(i, i3));
            String substring2 = substring.substring(i3 + 1);
            if (substring2.length() != 3) {
                substring2 = (substring2 + "000").substring(0, 3);
            }
            long j2 = parseLong;
            parseLong = (long) Integer.parseInt(substring2);
            j = j2;
        } else if (i2 != 0) {
            j = Long.parseLong(substring.substring(i, substring.length()));
            parseLong = 0;
        } else {
            j = Long.parseLong(substring);
            parseLong = 0;
        }
        if (i2 != 0) {
            return FieldUtils.safeAdd(FieldUtils.safeMultiply(-j, 1000), -parseLong);
        }
        return FieldUtils.safeAdd(FieldUtils.safeMultiply(j, 1000), parseLong);
    }

    public void setInto(ReadWritablePeriod readWritablePeriod, Object obj, Chronology chronology) {
        String str = (String) obj;
        PeriodFormatter standard = ISOPeriodFormat.standard();
        readWritablePeriod.clear();
        int parseInto = standard.parseInto(readWritablePeriod, str, 0);
        if (parseInto < str.length()) {
            if (parseInto < 0) {
                standard.withParseType(readWritablePeriod.getPeriodType()).parseMutablePeriod(str);
            }
            throw new IllegalArgumentException("Invalid format: \"" + str + '\"');
        }
    }

    public Class<?> getSupportedType() {
        return String.class;
    }
}
