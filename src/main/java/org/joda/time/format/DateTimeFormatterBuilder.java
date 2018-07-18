package org.joda.time.format;

import com.coinbase.android.ui.NumericKeypadConnector;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.MutableDateTime.Property;
import org.joda.time.ReadablePartial;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;

public class DateTimeFormatterBuilder {
    private ArrayList<Object> iElementPairs = new ArrayList();
    private Object iFormatter;

    static class CharacterLiteral implements DateTimeParser, DateTimePrinter {
        private final char iValue;

        CharacterLiteral(char c) {
            this.iValue = c;
        }

        public int estimatePrintedLength() {
            return 1;
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            stringBuffer.append(this.iValue);
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
            stringBuffer.append(this.iValue);
        }

        public int estimateParsedLength() {
            return 1;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            if (i >= str.length()) {
                return i ^ -1;
            }
            char charAt = str.charAt(i);
            char c = this.iValue;
            if (charAt != c) {
                charAt = Character.toUpperCase(charAt);
                c = Character.toUpperCase(c);
                if (!(charAt == c || Character.toLowerCase(charAt) == Character.toLowerCase(c))) {
                    return i ^ -1;
                }
            }
            return i + 1;
        }
    }

    static class Composite implements DateTimeParser, DateTimePrinter {
        private final int iParsedLengthEstimate;
        private final DateTimeParser[] iParsers;
        private final int iPrintedLengthEstimate;
        private final DateTimePrinter[] iPrinters;

        Composite(List<Object> list) {
            int i;
            int i2;
            int i3 = 0;
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (arrayList.contains(null) || arrayList.isEmpty()) {
                this.iPrinters = null;
                this.iPrintedLengthEstimate = 0;
            } else {
                int size = arrayList.size();
                this.iPrinters = new DateTimePrinter[size];
                i = 0;
                for (i2 = 0; i2 < size; i2++) {
                    DateTimePrinter dateTimePrinter = (DateTimePrinter) arrayList.get(i2);
                    i += dateTimePrinter.estimatePrintedLength();
                    this.iPrinters[i2] = dateTimePrinter;
                }
                this.iPrintedLengthEstimate = i;
            }
            if (arrayList2.contains(null) || arrayList2.isEmpty()) {
                this.iParsers = null;
                this.iParsedLengthEstimate = 0;
                return;
            }
            i = arrayList2.size();
            this.iParsers = new DateTimeParser[i];
            i2 = 0;
            while (i3 < i) {
                DateTimeParser dateTimeParser = (DateTimeParser) arrayList2.get(i3);
                i2 += dateTimeParser.estimateParsedLength();
                this.iParsers[i3] = dateTimeParser;
                i3++;
            }
            this.iParsedLengthEstimate = i2;
        }

        public int estimatePrintedLength() {
            return this.iPrintedLengthEstimate;
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            DateTimePrinter[] dateTimePrinterArr = this.iPrinters;
            if (dateTimePrinterArr == null) {
                throw new UnsupportedOperationException();
            }
            Locale locale2;
            if (locale == null) {
                locale2 = Locale.getDefault();
            } else {
                locale2 = locale;
            }
            for (DateTimePrinter printTo : dateTimePrinterArr) {
                printTo.printTo(stringBuffer, j, chronology, i, dateTimeZone, locale2);
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
            DateTimePrinter[] dateTimePrinterArr = this.iPrinters;
            if (dateTimePrinterArr == null) {
                throw new UnsupportedOperationException();
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }
            for (DateTimePrinter printTo : dateTimePrinterArr) {
                printTo.printTo(stringBuffer, readablePartial, locale);
            }
        }

        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            DateTimeParser[] dateTimeParserArr = this.iParsers;
            if (dateTimeParserArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = dateTimeParserArr.length;
            for (int i2 = 0; i2 < length && i >= 0; i2++) {
                i = dateTimeParserArr[i2].parseInto(dateTimeParserBucket, str, i);
            }
            return i;
        }

        boolean isPrinter() {
            return this.iPrinters != null;
        }

        boolean isParser() {
            return this.iParsers != null;
        }

        private void decompose(List<Object> list, List<Object> list2, List<Object> list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof Composite) {
                    addArrayToList(list2, ((Composite) obj).iPrinters);
                } else {
                    list2.add(obj);
                }
                obj = list.get(i + 1);
                if (obj instanceof Composite) {
                    addArrayToList(list3, ((Composite) obj).iParsers);
                } else {
                    list3.add(obj);
                }
            }
        }

        private void addArrayToList(List<Object> list, Object[] objArr) {
            if (objArr != null) {
                for (Object add : objArr) {
                    list.add(add);
                }
            }
        }
    }

    static abstract class NumberFormatter implements DateTimeParser, DateTimePrinter {
        protected final DateTimeFieldType iFieldType;
        protected final int iMaxParsedDigits;
        protected final boolean iSigned;

        NumberFormatter(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iMaxParsedDigits = i;
            this.iSigned = z;
        }

        public int estimateParsedLength() {
            return this.iMaxParsedDigits;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            Object obj;
            Object obj2 = null;
            int min = Math.min(this.iMaxParsedDigits, str.length() - i);
            int i2 = i;
            int i3 = 0;
            while (i3 < min) {
                char charAt = str.charAt(i2 + i3);
                if (i3 != 0 || ((charAt != '-' && charAt != '+') || !this.iSigned)) {
                    if (charAt < NumericKeypadConnector.ZERO) {
                        break;
                    } else if (charAt > '9') {
                        obj = obj2;
                        break;
                    } else {
                        i3++;
                    }
                } else {
                    obj2 = charAt == '-' ? 1 : null;
                    if (i3 + 1 >= min) {
                        break;
                    }
                    charAt = str.charAt((i2 + i3) + 1);
                    if (charAt < NumericKeypadConnector.ZERO) {
                        break;
                    } else if (charAt > '9') {
                        obj = obj2;
                        break;
                    } else {
                        if (obj2 != null) {
                            i3++;
                        } else {
                            i2++;
                        }
                        min = Math.min(min + 1, str.length() - i2);
                    }
                }
            }
            obj = obj2;
            if (i3 == 0) {
                return i2 ^ -1;
            }
            int i4;
            if (i3 >= 9) {
                i4 = i2 + i3;
                i3 = Integer.parseInt(str.substring(i2, i4));
                i2 = i4;
            } else {
                int i5;
                if (obj != null) {
                    i5 = i2 + 1;
                } else {
                    i5 = i2;
                }
                try {
                    i2 += i3;
                    i3 = str.charAt(i5) - 48;
                    for (i4 = i5 + 1; i4 < i2; i4++) {
                        i3 = (str.charAt(i4) + ((i3 << 3) + (i3 << 1))) - 48;
                    }
                    if (obj != null) {
                        i3 = -i3;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    return i2 ^ -1;
                }
            }
            dateTimeParserBucket.saveField(this.iFieldType, i3);
            return i2;
        }
    }

    static class PaddedNumber extends NumberFormatter {
        protected final int iMinPrintedDigits;

        protected PaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z, int i2) {
            super(dateTimeFieldType, i, z);
            this.iMinPrintedDigits = i2;
        }

        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            try {
                FormatUtils.appendPaddedInteger(stringBuffer, this.iFieldType.getField(chronology).get(j), this.iMinPrintedDigits);
            } catch (RuntimeException e) {
                DateTimeFormatterBuilder.appendUnknownString(stringBuffer, this.iMinPrintedDigits);
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendPaddedInteger(stringBuffer, readablePartial.get(this.iFieldType), this.iMinPrintedDigits);
                    return;
                } catch (RuntimeException e) {
                    DateTimeFormatterBuilder.appendUnknownString(stringBuffer, this.iMinPrintedDigits);
                    return;
                }
            }
            DateTimeFormatterBuilder.appendUnknownString(stringBuffer, this.iMinPrintedDigits);
        }
    }

    static class FixedNumber extends PaddedNumber {
        protected FixedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z, i);
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            int parseInto = super.parseInto(dateTimeParserBucket, str, i);
            if (parseInto < 0) {
                return parseInto;
            }
            int i2 = this.iMaxParsedDigits + i;
            if (parseInto == i2) {
                return parseInto;
            }
            if (this.iSigned) {
                char charAt = str.charAt(i);
                if (charAt == '-' || charAt == '+') {
                    i2++;
                }
            }
            if (parseInto > i2) {
                return (i2 + 1) ^ -1;
            }
            if (parseInto < i2) {
                return parseInto ^ -1;
            }
            return parseInto;
        }
    }

    static class Fraction implements DateTimeParser, DateTimePrinter {
        private final DateTimeFieldType iFieldType;
        protected int iMaxDigits;
        protected int iMinDigits;

        protected Fraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
            this.iFieldType = dateTimeFieldType;
            if (i2 > 18) {
                i2 = 18;
            }
            this.iMinDigits = i;
            this.iMaxDigits = i2;
        }

        public int estimatePrintedLength() {
            return this.iMaxDigits;
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            try {
                printTo(stringBuffer, null, j, chronology);
            } catch (IOException e) {
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
            try {
                printTo(stringBuffer, null, readablePartial.getChronology().set(readablePartial, 0), readablePartial.getChronology());
            } catch (IOException e) {
            }
        }

        protected void printTo(StringBuffer stringBuffer, Writer writer, long j, Chronology chronology) throws IOException {
            DateTimeField field = this.iFieldType.getField(chronology);
            int i = this.iMinDigits;
            try {
                long remainder = field.remainder(j);
                if (remainder != 0) {
                    String num;
                    long[] fractionData = getFractionData(remainder, field);
                    remainder = fractionData[0];
                    int i2 = (int) fractionData[1];
                    if ((2147483647L & remainder) == remainder) {
                        num = Integer.toString((int) remainder);
                    } else {
                        num = Long.toString(remainder);
                    }
                    int length = num.length();
                    while (length < i2) {
                        if (stringBuffer != null) {
                            stringBuffer.append(NumericKeypadConnector.ZERO);
                        } else {
                            writer.write(48);
                        }
                        i--;
                        i2--;
                    }
                    if (i < i2) {
                        while (i < i2 && length > 1 && num.charAt(length - 1) == NumericKeypadConnector.ZERO) {
                            i2--;
                            length--;
                        }
                        if (length < num.length()) {
                            if (stringBuffer != null) {
                                for (i2 = 0; i2 < length; i2++) {
                                    stringBuffer.append(num.charAt(i2));
                                }
                                return;
                            }
                            for (i2 = 0; i2 < length; i2++) {
                                writer.write(num.charAt(i2));
                            }
                            return;
                        }
                    }
                    if (stringBuffer != null) {
                        stringBuffer.append(num);
                    } else {
                        writer.write(num);
                    }
                } else if (stringBuffer != null) {
                    while (true) {
                        i--;
                        if (i >= 0) {
                            stringBuffer.append(NumericKeypadConnector.ZERO);
                        } else {
                            return;
                        }
                    }
                } else {
                    while (true) {
                        i--;
                        if (i >= 0) {
                            writer.write(48);
                        } else {
                            return;
                        }
                    }
                }
            } catch (RuntimeException e) {
                if (stringBuffer != null) {
                    DateTimeFormatterBuilder.appendUnknownString(stringBuffer, i);
                } else {
                    DateTimeFormatterBuilder.printUnknownString(writer, i);
                }
            }
        }

        private long[] getFractionData(long j, DateTimeField dateTimeField) {
            long unitMillis = dateTimeField.getDurationField().getUnitMillis();
            int i = this.iMaxDigits;
            while (true) {
                long j2;
                switch (i) {
                    case 1:
                        j2 = 10;
                        break;
                    case 2:
                        j2 = 100;
                        break;
                    case 3:
                        j2 = 1000;
                        break;
                    case 4:
                        j2 = 10000;
                        break;
                    case 5:
                        j2 = 100000;
                        break;
                    case 6:
                        j2 = 1000000;
                        break;
                    case 7:
                        j2 = 10000000;
                        break;
                    case 8:
                        j2 = 100000000;
                        break;
                    case 9:
                        j2 = 1000000000;
                        break;
                    case 10:
                        j2 = 10000000000L;
                        break;
                    case 11:
                        j2 = 100000000000L;
                        break;
                    case 12:
                        j2 = 1000000000000L;
                        break;
                    case 13:
                        j2 = 10000000000000L;
                        break;
                    case 14:
                        j2 = 100000000000000L;
                        break;
                    case 15:
                        j2 = 1000000000000000L;
                        break;
                    case 16:
                        j2 = 10000000000000000L;
                        break;
                    case 17:
                        j2 = 100000000000000000L;
                        break;
                    case 18:
                        j2 = 1000000000000000000L;
                        break;
                    default:
                        j2 = 1;
                        break;
                }
                if ((unitMillis * j2) / j2 == unitMillis) {
                    return new long[]{(j2 * j) / unitMillis, (long) i};
                }
                i--;
            }
        }

        public int estimateParsedLength() {
            return this.iMaxDigits;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            DateTimeField field = this.iFieldType.getField(dateTimeParserBucket.getChronology());
            int min = Math.min(this.iMaxDigits, str.length() - i);
            long j = 0;
            long unitMillis = field.getDurationField().getUnitMillis() * 10;
            int i2 = 0;
            while (i2 < min) {
                char charAt = str.charAt(i + i2);
                if (charAt < NumericKeypadConnector.ZERO || charAt > '9') {
                    break;
                }
                i2++;
                unitMillis /= 10;
                j += ((long) (charAt - 48)) * unitMillis;
            }
            unitMillis = j / 10;
            if (i2 == 0) {
                return i ^ -1;
            }
            if (unitMillis > 2147483647L) {
                return i ^ -1;
            }
            dateTimeParserBucket.saveField(new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, field.getDurationField()), (int) unitMillis);
            return i2 + i;
        }
    }

    static class MatchingParser implements DateTimeParser {
        private final int iParsedLengthEstimate;
        private final DateTimeParser[] iParsers;

        MatchingParser(DateTimeParser[] dateTimeParserArr) {
            this.iParsers = dateTimeParserArr;
            int i = 0;
            int length = dateTimeParserArr.length;
            while (true) {
                int i2 = length - 1;
                if (i2 >= 0) {
                    DateTimeParser dateTimeParser = dateTimeParserArr[i2];
                    if (dateTimeParser != null) {
                        length = dateTimeParser.estimateParsedLength();
                        if (length > i) {
                            i = length;
                            length = i2;
                        }
                    }
                    length = i;
                    i = length;
                    length = i2;
                } else {
                    this.iParsedLengthEstimate = i;
                    return;
                }
            }
        }

        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            Object saveState;
            DateTimeParser[] dateTimeParserArr = this.iParsers;
            int length = dateTimeParserArr.length;
            Object saveState2 = dateTimeParserBucket.saveState();
            Object obj = null;
            int i2 = 0;
            int i3 = i;
            int i4 = i;
            while (i2 < length) {
                DateTimeParser dateTimeParser = dateTimeParserArr[i2];
                if (dateTimeParser != null) {
                    int i5;
                    int parseInto = dateTimeParser.parseInto(dateTimeParserBucket, str, i);
                    if (parseInto >= i) {
                        if (parseInto > i4) {
                            if (parseInto >= str.length() || i2 + 1 >= length || dateTimeParserArr[i2 + 1] == null) {
                                return parseInto;
                            }
                            saveState = dateTimeParserBucket.saveState();
                            i5 = parseInto;
                        }
                        saveState = obj;
                        i5 = i4;
                    } else {
                        if (parseInto < 0) {
                            int i6 = parseInto ^ -1;
                            if (i6 > i3) {
                                i3 = i6;
                                saveState = obj;
                                i5 = i4;
                            }
                        }
                        saveState = obj;
                        i5 = i4;
                    }
                    dateTimeParserBucket.restoreState(saveState2);
                    i2++;
                    i4 = i5;
                    obj = saveState;
                } else if (i4 <= i) {
                    return i;
                } else {
                    saveState = 1;
                    if (i4 > i && (i4 != i || r1 == null)) {
                        return i3 ^ -1;
                    }
                    if (obj != null) {
                        dateTimeParserBucket.restoreState(obj);
                    }
                    return i4;
                }
            }
            saveState = null;
            if (i4 > i) {
            }
            if (obj != null) {
                dateTimeParserBucket.restoreState(obj);
            }
            return i4;
        }
    }

    static class StringLiteral implements DateTimeParser, DateTimePrinter {
        private final String iValue;

        StringLiteral(String str) {
            this.iValue = str;
        }

        public int estimatePrintedLength() {
            return this.iValue.length();
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            stringBuffer.append(this.iValue);
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
            stringBuffer.append(this.iValue);
        }

        public int estimateParsedLength() {
            return this.iValue.length();
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            if (str.regionMatches(true, i, this.iValue, 0, this.iValue.length())) {
                return this.iValue.length() + i;
            }
            return i ^ -1;
        }
    }

    static class TextField implements DateTimeParser, DateTimePrinter {
        private static Map<Locale, Map<DateTimeFieldType, Object[]>> cParseCache = new HashMap();
        private final DateTimeFieldType iFieldType;
        private final boolean iShort;

        TextField(DateTimeFieldType dateTimeFieldType, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iShort = z;
        }

        public int estimatePrintedLength() {
            return this.iShort ? 6 : 20;
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            try {
                stringBuffer.append(print(j, chronology, locale));
            } catch (RuntimeException e) {
                stringBuffer.append('�');
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
            try {
                stringBuffer.append(print(readablePartial, locale));
            } catch (RuntimeException e) {
                stringBuffer.append('�');
            }
        }

        private String print(long j, Chronology chronology, Locale locale) {
            DateTimeField field = this.iFieldType.getField(chronology);
            if (this.iShort) {
                return field.getAsShortText(j, locale);
            }
            return field.getAsText(j, locale);
        }

        private String print(ReadablePartial readablePartial, Locale locale) {
            if (!readablePartial.isSupported(this.iFieldType)) {
                return "�";
            }
            DateTimeField field = this.iFieldType.getField(readablePartial.getChronology());
            if (this.iShort) {
                return field.getAsShortText(readablePartial, locale);
            }
            return field.getAsText(readablePartial, locale);
        }

        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            Locale locale = dateTimeParserBucket.getLocale();
            synchronized (cParseCache) {
                Map map;
                Map map2 = (Map) cParseCache.get(locale);
                if (map2 == null) {
                    HashMap hashMap = new HashMap();
                    cParseCache.put(locale, hashMap);
                    map = hashMap;
                } else {
                    map = map2;
                }
                Object[] objArr = (Object[]) map.get(this.iFieldType);
                Set hashSet;
                int i2;
                if (objArr == null) {
                    hashSet = new HashSet(32);
                    Property property = new MutableDateTime(0, DateTimeZone.UTC).property(this.iFieldType);
                    int minimumValueOverall = property.getMinimumValueOverall();
                    int maximumValueOverall = property.getMaximumValueOverall();
                    if (maximumValueOverall - minimumValueOverall > 32) {
                        i2 = i ^ -1;
                        return i2;
                    }
                    i2 = property.getMaximumTextLength(locale);
                    while (minimumValueOverall <= maximumValueOverall) {
                        property.set(minimumValueOverall);
                        hashSet.add(property.getAsShortText(locale));
                        hashSet.add(property.getAsShortText(locale).toLowerCase(locale));
                        hashSet.add(property.getAsShortText(locale).toUpperCase(locale));
                        hashSet.add(property.getAsText(locale));
                        hashSet.add(property.getAsText(locale).toLowerCase(locale));
                        hashSet.add(property.getAsText(locale).toUpperCase(locale));
                        minimumValueOverall++;
                    }
                    if ("en".equals(locale.getLanguage()) && this.iFieldType == DateTimeFieldType.era()) {
                        hashSet.add("BCE");
                        hashSet.add("bce");
                        hashSet.add("CE");
                        hashSet.add("ce");
                        i2 = 3;
                    }
                    map.put(this.iFieldType, new Object[]{hashSet, Integer.valueOf(i2)});
                } else {
                    hashSet = (Set) objArr[0];
                    i2 = ((Integer) objArr[1]).intValue();
                }
            }
        }
    }

    enum TimeZoneId implements DateTimeParser, DateTimePrinter {
        INSTANCE;
        
        static final Set<String> ALL_IDS = null;
        static final int MAX_LENGTH = 0;

        static {
            ALL_IDS = DateTimeZone.getAvailableIDs();
            int i = 0;
            for (String length : ALL_IDS) {
                i = Math.max(i, length.length());
            }
            MAX_LENGTH = i;
        }

        public int estimatePrintedLength() {
            return MAX_LENGTH;
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            stringBuffer.append(dateTimeZone != null ? dateTimeZone.getID() : "");
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
        }

        public int estimateParsedLength() {
            return MAX_LENGTH;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            String substring = str.substring(i);
            String str2 = null;
            for (String str3 : ALL_IDS) {
                String str32;
                if (!substring.startsWith(str32) || (str2 != null && str32.length() <= str2.length())) {
                    str32 = str2;
                }
                str2 = str32;
            }
            if (str2 == null) {
                return i ^ -1;
            }
            dateTimeParserBucket.setZone(DateTimeZone.forID(str2));
            return str2.length() + i;
        }
    }

    static class TimeZoneName implements DateTimeParser, DateTimePrinter {
        private final Map<String, DateTimeZone> iParseLookup;
        private final int iType;

        TimeZoneName(int i, Map<String, DateTimeZone> map) {
            this.iType = i;
            this.iParseLookup = map;
        }

        public int estimatePrintedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            stringBuffer.append(print(j - ((long) i), dateTimeZone, locale));
        }

        private String print(long j, DateTimeZone dateTimeZone, Locale locale) {
            if (dateTimeZone == null) {
                return "";
            }
            switch (this.iType) {
                case 0:
                    return dateTimeZone.getName(j, locale);
                case 1:
                    return dateTimeZone.getShortName(j, locale);
                default:
                    return "";
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
        }

        public int estimateParsedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            Map map = this.iParseLookup;
            Map defaultTimeZoneNames = map != null ? map : DateTimeUtils.getDefaultTimeZoneNames();
            String substring = str.substring(i);
            String str2 = null;
            for (String str3 : defaultTimeZoneNames.keySet()) {
                String str32;
                if (!substring.startsWith(str32) || (str2 != null && str32.length() <= str2.length())) {
                    str32 = str2;
                }
                str2 = str32;
            }
            if (str2 == null) {
                return i ^ -1;
            }
            dateTimeParserBucket.setZone((DateTimeZone) defaultTimeZoneNames.get(str2));
            return str2.length() + i;
        }
    }

    static class TimeZoneOffset implements DateTimeParser, DateTimePrinter {
        private final int iMaxFields;
        private final int iMinFields;
        private final boolean iShowSeparators;
        private final String iZeroOffsetParseText;
        private final String iZeroOffsetPrintText;

        TimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
            int i3 = 4;
            this.iZeroOffsetPrintText = str;
            this.iZeroOffsetParseText = str2;
            this.iShowSeparators = z;
            if (i <= 0 || i2 < i) {
                throw new IllegalArgumentException();
            }
            if (i > 4) {
                i2 = 4;
            } else {
                i3 = i;
            }
            this.iMinFields = i3;
            this.iMaxFields = i2;
        }

        public int estimatePrintedLength() {
            int i = (this.iMinFields + 1) << 1;
            if (this.iShowSeparators) {
                i += this.iMinFields - 1;
            }
            if (this.iZeroOffsetPrintText == null || this.iZeroOffsetPrintText.length() <= i) {
                return i;
            }
            return this.iZeroOffsetPrintText.length();
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            if (dateTimeZone != null) {
                if (i != 0 || this.iZeroOffsetPrintText == null) {
                    if (i >= 0) {
                        stringBuffer.append('+');
                    } else {
                        stringBuffer.append('-');
                        i = -i;
                    }
                    int i2 = i / 3600000;
                    FormatUtils.appendPaddedInteger(stringBuffer, i2, 2);
                    if (this.iMaxFields != 1) {
                        i2 = i - (i2 * 3600000);
                        if (i2 != 0 || this.iMinFields > 1) {
                            int i3 = i2 / 60000;
                            if (this.iShowSeparators) {
                                stringBuffer.append(':');
                            }
                            FormatUtils.appendPaddedInteger(stringBuffer, i3, 2);
                            if (this.iMaxFields != 2) {
                                i2 -= i3 * 60000;
                                if (i2 != 0 || this.iMinFields > 2) {
                                    i3 = i2 / 1000;
                                    if (this.iShowSeparators) {
                                        stringBuffer.append(':');
                                    }
                                    FormatUtils.appendPaddedInteger(stringBuffer, i3, 2);
                                    if (this.iMaxFields != 3) {
                                        i2 -= i3 * 1000;
                                        if (i2 != 0 || this.iMinFields > 3) {
                                            if (this.iShowSeparators) {
                                                stringBuffer.append(NumericKeypadConnector.DOT);
                                            }
                                            FormatUtils.appendPaddedInteger(stringBuffer, i2, 3);
                                            return;
                                        }
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                stringBuffer.append(this.iZeroOffsetPrintText);
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
        }

        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            char charAt;
            int i2 = 0;
            int length = str.length() - i;
            if (this.iZeroOffsetParseText != null) {
                if (this.iZeroOffsetParseText.length() == 0) {
                    if (length > 0) {
                        charAt = str.charAt(i);
                        if (charAt != '-') {
                        }
                    }
                    dateTimeParserBucket.setOffset(Integer.valueOf(0));
                    return i;
                }
                if (str.regionMatches(true, i, this.iZeroOffsetParseText, 0, this.iZeroOffsetParseText.length())) {
                    dateTimeParserBucket.setOffset(Integer.valueOf(0));
                    return i + this.iZeroOffsetParseText.length();
                }
            }
            if (length <= 1) {
                return i ^ -1;
            }
            boolean z;
            charAt = str.charAt(i);
            if (charAt == '-') {
                z = true;
            } else if (charAt != '+') {
                return i ^ -1;
            } else {
                z = false;
            }
            int i3 = length - 1;
            int i4 = i + 1;
            if (digitCount(str, i4, 2) < 2) {
                return i4 ^ -1;
            }
            int parseTwoDigits = FormatUtils.parseTwoDigits(str, i4);
            if (parseTwoDigits > 23) {
                return i4 ^ -1;
            }
            int i5;
            int i6;
            parseTwoDigits *= 3600000;
            i3 -= 2;
            i4 += 2;
            if (i3 <= 0) {
                i5 = parseTwoDigits;
                i = i4;
            } else {
                char charAt2 = str.charAt(i4);
                if (charAt2 == ':') {
                    i3--;
                    i4++;
                    i2 = 1;
                } else if (charAt2 < NumericKeypadConnector.ZERO || charAt2 > '9') {
                    i5 = parseTwoDigits;
                    i = i4;
                }
                length = digitCount(str, i4, 2);
                if (length == 0 && i2 == 0) {
                    i5 = parseTwoDigits;
                    i = i4;
                } else if (length < 2) {
                    return i4 ^ -1;
                } else {
                    length = FormatUtils.parseTwoDigits(str, i4);
                    if (length > 59) {
                        return i4 ^ -1;
                    }
                    parseTwoDigits += length * 60000;
                    i3 -= 2;
                    i4 += 2;
                    if (i3 <= 0) {
                        i5 = parseTwoDigits;
                        i = i4;
                    } else {
                        if (i2 != 0) {
                            if (str.charAt(i4) != ':') {
                                i5 = parseTwoDigits;
                                i = i4;
                            } else {
                                i3--;
                                i4++;
                            }
                        }
                        length = digitCount(str, i4, 2);
                        if (length == 0 && i2 == 0) {
                            i5 = parseTwoDigits;
                            i = i4;
                        } else if (length < 2) {
                            return i4 ^ -1;
                        } else {
                            length = FormatUtils.parseTwoDigits(str, i4);
                            if (length > 59) {
                                return i4 ^ -1;
                            }
                            parseTwoDigits += length * 1000;
                            length = i3 - 2;
                            i3 = i4 + 2;
                            if (length <= 0) {
                                i5 = parseTwoDigits;
                                i = i3;
                            } else {
                                if (i2 != 0) {
                                    if (str.charAt(i3) == NumericKeypadConnector.DOT || str.charAt(i3) == ',') {
                                        i4 = length - 1;
                                        i3++;
                                    } else {
                                        i5 = parseTwoDigits;
                                        i = i3;
                                    }
                                }
                                length = digitCount(str, i3, 3);
                                if (length == 0 && i2 == 0) {
                                    i5 = parseTwoDigits;
                                    i = i3;
                                } else if (length < 1) {
                                    return i3 ^ -1;
                                } else {
                                    i4 = i3 + 1;
                                    i3 = ((str.charAt(i3) - 48) * 100) + parseTwoDigits;
                                    if (length > 1) {
                                        i = i4 + 1;
                                        i5 = ((str.charAt(i4) - 48) * 10) + i3;
                                        if (length > 2) {
                                            i5 += str.charAt(i) - 48;
                                            i++;
                                        }
                                    } else {
                                        i5 = i3;
                                        i = i4;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (z) {
                i6 = -i5;
            } else {
                i6 = i5;
            }
            dateTimeParserBucket.setOffset(Integer.valueOf(i6));
            return i;
        }

        private int digitCount(String str, int i, int i2) {
            int i3 = 0;
            for (int min = Math.min(str.length() - i, i2); min > 0; min--) {
                char charAt = str.charAt(i + i3);
                if (charAt < NumericKeypadConnector.ZERO || charAt > '9') {
                    break;
                }
                i3++;
            }
            return i3;
        }
    }

    static class TwoDigitYear implements DateTimeParser, DateTimePrinter {
        private final boolean iLenientParse;
        private final int iPivot;
        private final DateTimeFieldType iType;

        TwoDigitYear(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iType = dateTimeFieldType;
            this.iPivot = i;
            this.iLenientParse = z;
        }

        public int estimateParsedLength() {
            return this.iLenientParse ? 4 : 2;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            int i2;
            char charAt;
            int charAt2;
            int i3 = 0;
            int length = str.length() - i;
            if (this.iLenientParse) {
                i2 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = length;
                while (i2 < i6) {
                    charAt = str.charAt(i + i2);
                    if (i2 != 0 || (charAt != '-' && charAt != '+')) {
                        if (charAt < NumericKeypadConnector.ZERO || charAt > '9') {
                            break;
                        }
                        i2++;
                    } else {
                        if (charAt == '-') {
                            length = 1;
                        } else {
                            length = 0;
                        }
                        if (length != 0) {
                            i2++;
                            i4 = length;
                            i5 = 1;
                        } else {
                            i++;
                            i5 = 1;
                            i6--;
                            i4 = length;
                        }
                    }
                }
                if (i2 == 0) {
                    return i ^ -1;
                }
                if (!(i5 == 0 && i2 == 2)) {
                    if (i2 >= 9) {
                        length = i + i2;
                        i3 = Integer.parseInt(str.substring(i, length));
                    } else {
                        if (i4 != 0) {
                            length = i + 1;
                        } else {
                            length = i;
                        }
                        i3 = length + 1;
                        try {
                            charAt2 = str.charAt(length) - 48;
                            length = i + i2;
                            int i7 = i3;
                            i3 = charAt2;
                            charAt2 = i7;
                            while (charAt2 < length) {
                                i2 = (i3 << 3) + (i3 << 1);
                                i7 = charAt2 + 1;
                                i3 = (str.charAt(charAt2) + i2) - 48;
                                charAt2 = i7;
                            }
                            if (i4 != 0) {
                                i3 = -i3;
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                            return i ^ -1;
                        }
                    }
                    dateTimeParserBucket.saveField(this.iType, i3);
                    return length;
                }
            } else if (Math.min(2, length) < 2) {
                return i ^ -1;
            }
            charAt = str.charAt(i);
            if (charAt < NumericKeypadConnector.ZERO || charAt > '9') {
                return i ^ -1;
            }
            length = charAt - 48;
            char charAt3 = str.charAt(i + 1);
            if (charAt3 < NumericKeypadConnector.ZERO || charAt3 > '9') {
                return i ^ -1;
            }
            charAt2 = (((length << 1) + (length << 3)) + charAt3) - 48;
            length = this.iPivot;
            if (dateTimeParserBucket.getPivotYear() != null) {
                length = dateTimeParserBucket.getPivotYear().intValue();
            }
            i2 = length - 50;
            if (i2 >= 0) {
                length = i2 % 100;
            } else {
                length = ((i2 + 1) % 100) + 99;
            }
            if (charAt2 < length) {
                i3 = 100;
            }
            dateTimeParserBucket.saveField(this.iType, ((i3 + i2) - length) + charAt2);
            return i + 2;
        }

        public int estimatePrintedLength() {
            return 2;
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            int twoDigitYear = getTwoDigitYear(j, chronology);
            if (twoDigitYear < 0) {
                stringBuffer.append('�');
                stringBuffer.append('�');
                return;
            }
            FormatUtils.appendPaddedInteger(stringBuffer, twoDigitYear, 2);
        }

        private int getTwoDigitYear(long j, Chronology chronology) {
            try {
                int i = this.iType.getField(chronology).get(j);
                if (i < 0) {
                    i = -i;
                }
                return i % 100;
            } catch (RuntimeException e) {
                return -1;
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
            int twoDigitYear = getTwoDigitYear(readablePartial);
            if (twoDigitYear < 0) {
                stringBuffer.append('�');
                stringBuffer.append('�');
                return;
            }
            FormatUtils.appendPaddedInteger(stringBuffer, twoDigitYear, 2);
        }

        private int getTwoDigitYear(ReadablePartial readablePartial) {
            if (readablePartial.isSupported(this.iType)) {
                try {
                    int i = readablePartial.get(this.iType);
                    if (i < 0) {
                        i = -i;
                    }
                    return i % 100;
                } catch (RuntimeException e) {
                }
            }
            return -1;
        }
    }

    static class UnpaddedNumber extends NumberFormatter {
        protected UnpaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z);
        }

        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            try {
                FormatUtils.appendUnpaddedInteger(stringBuffer, this.iFieldType.getField(chronology).get(j));
            } catch (RuntimeException e) {
                stringBuffer.append('�');
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendUnpaddedInteger(stringBuffer, readablePartial.get(this.iFieldType));
                    return;
                } catch (RuntimeException e) {
                    stringBuffer.append('�');
                    return;
                }
            }
            stringBuffer.append('�');
        }
    }

    public DateTimeFormatter toFormatter() {
        DateTimePrinter dateTimePrinter;
        DateTimeParser dateTimeParser;
        Object formatter = getFormatter();
        if (isPrinter(formatter)) {
            dateTimePrinter = (DateTimePrinter) formatter;
        } else {
            dateTimePrinter = null;
        }
        if (isParser(formatter)) {
            dateTimeParser = (DateTimeParser) formatter;
        } else {
            dateTimeParser = null;
        }
        if (dateTimePrinter != null || dateTimeParser != null) {
            return new DateTimeFormatter(dateTimePrinter, dateTimeParser);
        }
        throw new UnsupportedOperationException("Both printing and parsing not supported");
    }

    public DateTimeParser toParser() {
        Object formatter = getFormatter();
        if (isParser(formatter)) {
            return (DateTimeParser) formatter;
        }
        throw new UnsupportedOperationException("Parsing is not supported");
    }

    public boolean canBuildFormatter() {
        return isFormatter(getFormatter());
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter != null) {
            return append0(dateTimeFormatter.getPrinter(), dateTimeFormatter.getParser());
        }
        throw new IllegalArgumentException("No formatter supplied");
    }

    public DateTimeFormatterBuilder append(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, dateTimeParser);
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter, DateTimeParser[] dateTimeParserArr) {
        int i = 0;
        if (dateTimePrinter != null) {
            checkPrinter(dateTimePrinter);
        }
        if (dateTimeParserArr == null) {
            throw new IllegalArgumentException("No parsers supplied");
        }
        int length = dateTimeParserArr.length;
        if (length != 1) {
            DateTimeParser[] dateTimeParserArr2 = new DateTimeParser[length];
            while (i < length - 1) {
                DateTimeParser dateTimeParser = dateTimeParserArr[i];
                dateTimeParserArr2[i] = dateTimeParser;
                if (dateTimeParser == null) {
                    throw new IllegalArgumentException("Incomplete parser array");
                }
                i++;
            }
            dateTimeParserArr2[i] = dateTimeParserArr[i];
            return append0(dateTimePrinter, new MatchingParser(dateTimeParserArr2));
        } else if (dateTimeParserArr[0] != null) {
            return append0(dateTimePrinter, dateTimeParserArr[0]);
        } else {
            throw new IllegalArgumentException("No parser supplied");
        }
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, new MatchingParser(new DateTimeParser[]{dateTimeParser, null}));
    }

    private void checkParser(DateTimeParser dateTimeParser) {
        if (dateTimeParser == null) {
            throw new IllegalArgumentException("No parser supplied");
        }
    }

    private void checkPrinter(DateTimePrinter dateTimePrinter) {
        if (dateTimePrinter == null) {
            throw new IllegalArgumentException("No printer supplied");
        }
    }

    private DateTimeFormatterBuilder append0(Object obj) {
        this.iFormatter = null;
        this.iElementPairs.add(obj);
        this.iElementPairs.add(obj);
        return this;
    }

    private DateTimeFormatterBuilder append0(DateTimePrinter dateTimePrinter, DateTimeParser dateTimeParser) {
        this.iFormatter = null;
        this.iElementPairs.add(dateTimePrinter);
        this.iElementPairs.add(dateTimeParser);
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(char c) {
        return append0(new CharacterLiteral(c));
    }

    public DateTimeFormatterBuilder appendLiteral(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        switch (str.length()) {
            case 0:
                return this;
            case 1:
                return append0(new CharacterLiteral(str.charAt(0)));
            default:
                return append0(new StringLiteral(str));
        }
    }

    public DateTimeFormatterBuilder appendDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        } else if (i <= 1) {
            return append0(new UnpaddedNumber(dateTimeFieldType, i2, false));
        } else {
            return append0(new PaddedNumber(dateTimeFieldType, i2, false, i));
        }
    }

    public DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType dateTimeFieldType, int i) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        } else if (i > 0) {
            return append0(new FixedNumber(dateTimeFieldType, i, false));
        } else {
            throw new IllegalArgumentException("Illegal number of digits: " + i);
        }
    }

    public DateTimeFormatterBuilder appendSignedDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        } else if (i <= 1) {
            return append0(new UnpaddedNumber(dateTimeFieldType, i2, true));
        } else {
            return append0(new PaddedNumber(dateTimeFieldType, i2, true, i));
        }
    }

    public DateTimeFormatterBuilder appendText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType != null) {
            return append0(new TextField(dateTimeFieldType, false));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendShortText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType != null) {
            return append0(new TextField(dateTimeFieldType, true));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendFraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i >= 0 && i2 > 0) {
            return append0(new Fraction(dateTimeFieldType, i, i2));
        }
        throw new IllegalArgumentException();
    }

    public DateTimeFormatterBuilder appendFractionOfSecond(int i, int i2) {
        return appendFraction(DateTimeFieldType.secondOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfMinute(int i, int i2) {
        return appendFraction(DateTimeFieldType.minuteOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfHour(int i, int i2) {
        return appendFraction(DateTimeFieldType.hourOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendMillisOfSecond(int i) {
        return appendDecimal(DateTimeFieldType.millisOfSecond(), i, 3);
    }

    public DateTimeFormatterBuilder appendSecondOfMinute(int i) {
        return appendDecimal(DateTimeFieldType.secondOfMinute(), i, 2);
    }

    public DateTimeFormatterBuilder appendMinuteOfHour(int i) {
        return appendDecimal(DateTimeFieldType.minuteOfHour(), i, 2);
    }

    public DateTimeFormatterBuilder appendHourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.hourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendHourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.hourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfWeek(int i) {
        return appendDecimal(DateTimeFieldType.dayOfWeek(), i, 1);
    }

    public DateTimeFormatterBuilder appendDayOfMonth(int i) {
        return appendDecimal(DateTimeFieldType.dayOfMonth(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfYear(int i) {
        return appendDecimal(DateTimeFieldType.dayOfYear(), i, 3);
    }

    public DateTimeFormatterBuilder appendWeekOfWeekyear(int i) {
        return appendDecimal(DateTimeFieldType.weekOfWeekyear(), i, 2);
    }

    public DateTimeFormatterBuilder appendWeekyear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.weekyear(), i, i2);
    }

    public DateTimeFormatterBuilder appendMonthOfYear(int i) {
        return appendDecimal(DateTimeFieldType.monthOfYear(), i, 2);
    }

    public DateTimeFormatterBuilder appendYear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.year(), i, i2);
    }

    public DateTimeFormatterBuilder appendTwoDigitYear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.year(), i, z));
    }

    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.weekyear(), i, z));
    }

    public DateTimeFormatterBuilder appendYearOfEra(int i, int i2) {
        return appendDecimal(DateTimeFieldType.yearOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendCenturyOfEra(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.centuryOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendHalfdayOfDayText() {
        return appendText(DateTimeFieldType.halfdayOfDay());
    }

    public DateTimeFormatterBuilder appendDayOfWeekText() {
        return appendText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendDayOfWeekShortText() {
        return appendShortText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendMonthOfYearText() {
        return appendText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendMonthOfYearShortText() {
        return appendShortText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendEraText() {
        return appendText(DateTimeFieldType.era());
    }

    public DateTimeFormatterBuilder appendTimeZoneName() {
        return append0(new TimeZoneName(0, null), null);
    }

    public DateTimeFormatterBuilder appendTimeZoneShortName(Map<String, DateTimeZone> map) {
        Object timeZoneName = new TimeZoneName(1, map);
        return append0(timeZoneName, timeZoneName);
    }

    public DateTimeFormatterBuilder appendTimeZoneId() {
        return append0(TimeZoneId.INSTANCE, TimeZoneId.INSTANCE);
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str, z, i, i2));
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str2, z, i, i2));
    }

    private Object getFormatter() {
        Object obj = this.iFormatter;
        if (obj == null) {
            if (this.iElementPairs.size() == 2) {
                Object obj2 = this.iElementPairs.get(0);
                Object obj3 = this.iElementPairs.get(1);
                if (obj2 == null) {
                    obj = obj3;
                } else if (obj2 == obj3 || obj3 == null) {
                    obj = obj2;
                }
            }
            if (obj == null) {
                obj = new Composite(this.iElementPairs);
            }
            this.iFormatter = obj;
        }
        return obj;
    }

    private boolean isPrinter(Object obj) {
        if (!(obj instanceof DateTimePrinter)) {
            return false;
        }
        if (obj instanceof Composite) {
            return ((Composite) obj).isPrinter();
        }
        return true;
    }

    private boolean isParser(Object obj) {
        if (!(obj instanceof DateTimeParser)) {
            return false;
        }
        if (obj instanceof Composite) {
            return ((Composite) obj).isParser();
        }
        return true;
    }

    private boolean isFormatter(Object obj) {
        return isPrinter(obj) || isParser(obj);
    }

    static void appendUnknownString(StringBuffer stringBuffer, int i) {
        while (true) {
            i--;
            if (i >= 0) {
                stringBuffer.append('�');
            } else {
                return;
            }
        }
    }

    static void printUnknownString(Writer writer, int i) throws IOException {
        while (true) {
            i--;
            if (i >= 0) {
                writer.write(65533);
            } else {
                return;
            }
        }
    }
}
