package org.joda.time.format;

import com.coinbase.android.ui.NumericKeypadConnector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import org.joda.time.DurationFieldType;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

public class PeriodFormatterBuilder {
    private List<Object> iElementPairs;
    private FieldFormatter[] iFieldFormatters;
    private int iMaxParsedDigits;
    private int iMinPrintedDigits;
    private boolean iNotParser;
    private boolean iNotPrinter;
    private PeriodFieldAffix iPrefix;
    private int iPrintZeroSetting;
    private boolean iRejectSignedValues;

    static class Composite implements PeriodParser, PeriodPrinter {
        private final PeriodParser[] iParsers;
        private final PeriodPrinter[] iPrinters;

        Composite(List<Object> list) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (arrayList.size() <= 0) {
                this.iPrinters = null;
            } else {
                this.iPrinters = (PeriodPrinter[]) arrayList.toArray(new PeriodPrinter[arrayList.size()]);
            }
            if (arrayList2.size() <= 0) {
                this.iParsers = null;
            } else {
                this.iParsers = (PeriodParser[]) arrayList2.toArray(new PeriodParser[arrayList2.size()]);
            }
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            int i2 = 0;
            PeriodPrinter[] periodPrinterArr = this.iPrinters;
            int length = periodPrinterArr.length;
            while (i2 < i) {
                length--;
                if (length < 0) {
                    break;
                }
                i2 += periodPrinterArr[length].countFieldsToPrint(readablePeriod, Integer.MAX_VALUE, locale);
            }
            return i2;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            int i = 0;
            PeriodPrinter[] periodPrinterArr = this.iPrinters;
            int length = periodPrinterArr.length;
            while (true) {
                length--;
                if (length < 0) {
                    return i;
                }
                i += periodPrinterArr[length].calculatePrintedLength(readablePeriod, locale);
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            for (PeriodPrinter printTo : this.iPrinters) {
                printTo.printTo(stringBuffer, readablePeriod, locale);
            }
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            PeriodParser[] periodParserArr = this.iParsers;
            if (periodParserArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = periodParserArr.length;
            for (int i2 = 0; i2 < length && i >= 0; i2++) {
                i = periodParserArr[i2].parseInto(readWritablePeriod, str, i, locale);
            }
            return i;
        }

        private void decompose(List<Object> list, List<Object> list2, List<Object> list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof PeriodPrinter) {
                    if (obj instanceof Composite) {
                        addArrayToList(list2, ((Composite) obj).iPrinters);
                    } else {
                        list2.add(obj);
                    }
                }
                obj = list.get(i + 1);
                if (obj instanceof PeriodParser) {
                    if (obj instanceof Composite) {
                        addArrayToList(list3, ((Composite) obj).iParsers);
                    } else {
                        list3.add(obj);
                    }
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

    interface PeriodFieldAffix {
        int calculatePrintedLength(int i);

        int parse(String str, int i);

        void printTo(StringBuffer stringBuffer, int i);

        int scan(String str, int i);
    }

    static class CompositeAffix implements PeriodFieldAffix {
        private final PeriodFieldAffix iLeft;
        private final PeriodFieldAffix iRight;

        CompositeAffix(PeriodFieldAffix periodFieldAffix, PeriodFieldAffix periodFieldAffix2) {
            this.iLeft = periodFieldAffix;
            this.iRight = periodFieldAffix2;
        }

        public int calculatePrintedLength(int i) {
            return this.iLeft.calculatePrintedLength(i) + this.iRight.calculatePrintedLength(i);
        }

        public void printTo(StringBuffer stringBuffer, int i) {
            this.iLeft.printTo(stringBuffer, i);
            this.iRight.printTo(stringBuffer, i);
        }

        public int parse(String str, int i) {
            int parse = this.iLeft.parse(str, i);
            if (parse >= 0) {
                return this.iRight.parse(str, parse);
            }
            return parse;
        }

        public int scan(String str, int i) {
            int scan = this.iLeft.scan(str, i);
            if (scan >= 0) {
                return this.iRight.scan(str, scan);
            }
            return i ^ -1;
        }
    }

    static class FieldFormatter implements PeriodParser, PeriodPrinter {
        private final FieldFormatter[] iFieldFormatters;
        private final int iFieldType;
        private final int iMaxParsedDigits;
        private final int iMinPrintedDigits;
        private final PeriodFieldAffix iPrefix;
        private final int iPrintZeroSetting;
        private final boolean iRejectSignedValues;
        private final PeriodFieldAffix iSuffix;

        FieldFormatter(int i, int i2, int i3, boolean z, int i4, FieldFormatter[] fieldFormatterArr, PeriodFieldAffix periodFieldAffix, PeriodFieldAffix periodFieldAffix2) {
            this.iMinPrintedDigits = i;
            this.iPrintZeroSetting = i2;
            this.iMaxParsedDigits = i3;
            this.iRejectSignedValues = z;
            this.iFieldType = i4;
            this.iFieldFormatters = fieldFormatterArr;
            this.iPrefix = periodFieldAffix;
            this.iSuffix = periodFieldAffix2;
        }

        FieldFormatter(FieldFormatter fieldFormatter, PeriodFieldAffix periodFieldAffix) {
            this.iMinPrintedDigits = fieldFormatter.iMinPrintedDigits;
            this.iPrintZeroSetting = fieldFormatter.iPrintZeroSetting;
            this.iMaxParsedDigits = fieldFormatter.iMaxParsedDigits;
            this.iRejectSignedValues = fieldFormatter.iRejectSignedValues;
            this.iFieldType = fieldFormatter.iFieldType;
            this.iFieldFormatters = fieldFormatter.iFieldFormatters;
            this.iPrefix = fieldFormatter.iPrefix;
            if (fieldFormatter.iSuffix != null) {
                periodFieldAffix = new CompositeAffix(fieldFormatter.iSuffix, periodFieldAffix);
            }
            this.iSuffix = periodFieldAffix;
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            if (i <= 0) {
                return 0;
            }
            if (this.iPrintZeroSetting == 4 || getFieldValue(readablePeriod) != Long.MAX_VALUE) {
                return 1;
            }
            return 0;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue == Long.MAX_VALUE) {
                return 0;
            }
            int max = Math.max(FormatUtils.calculateDigitCount(fieldValue), this.iMinPrintedDigits);
            if (this.iFieldType >= 8) {
                max = (fieldValue < 0 ? Math.max(max, 5) : Math.max(max, 4)) + 1;
                if (this.iFieldType == 9 && Math.abs(fieldValue) % 1000 == 0) {
                    max -= 4;
                }
                fieldValue /= 1000;
            }
            int i = (int) fieldValue;
            if (this.iPrefix != null) {
                max += this.iPrefix.calculatePrintedLength(i);
            }
            if (this.iSuffix != null) {
                return max + this.iSuffix.calculatePrintedLength(i);
            }
            return max;
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue != Long.MAX_VALUE) {
                int i = (int) fieldValue;
                if (this.iFieldType >= 8) {
                    i = (int) (fieldValue / 1000);
                }
                if (this.iPrefix != null) {
                    this.iPrefix.printTo(stringBuffer, i);
                }
                int length = stringBuffer.length();
                int i2 = this.iMinPrintedDigits;
                if (i2 <= 1) {
                    FormatUtils.appendUnpaddedInteger(stringBuffer, i);
                } else {
                    FormatUtils.appendPaddedInteger(stringBuffer, i, i2);
                }
                if (this.iFieldType >= 8) {
                    i2 = (int) (Math.abs(fieldValue) % 1000);
                    if (this.iFieldType == 8 || i2 > 0) {
                        if (fieldValue < 0 && fieldValue > -1000) {
                            stringBuffer.insert(length, '-');
                        }
                        stringBuffer.append(NumericKeypadConnector.DOT);
                        FormatUtils.appendPaddedInteger(stringBuffer, i2, 3);
                    }
                }
                if (this.iSuffix != null) {
                    this.iSuffix.printTo(stringBuffer, i);
                }
            }
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            Object obj = this.iPrintZeroSetting == 4 ? 1 : null;
            if (i >= str.length()) {
                return obj != null ? i ^ -1 : i;
            } else {
                int i2;
                int scan;
                if (this.iPrefix != null) {
                    i = this.iPrefix.parse(str, i);
                    if (i >= 0) {
                        obj = 1;
                    } else if (obj == null) {
                        return i ^ -1;
                    } else {
                        return i;
                    }
                }
                if (this.iSuffix == null || obj != null) {
                    i2 = -1;
                } else {
                    scan = this.iSuffix.scan(str, i);
                    if (scan < 0) {
                        return obj == null ? scan ^ -1 : scan;
                    } else {
                        obj = 1;
                        i2 = scan;
                    }
                }
                if (obj == null && !isSupported(readWritablePeriod.getPeriodType(), this.iFieldType)) {
                    return i;
                }
                int min;
                if (i2 > 0) {
                    min = Math.min(this.iMaxParsedDigits, i2 - i);
                } else {
                    min = Math.min(this.iMaxParsedDigits, str.length() - i);
                }
                int i3 = -1;
                Object obj2 = null;
                int i4 = min;
                min = 0;
                scan = i;
                while (min < i4) {
                    char charAt = str.charAt(scan + min);
                    if (min == 0 && ((charAt == '-' || charAt == '+') && !this.iRejectSignedValues)) {
                        Object obj3 = charAt == '-' ? 1 : null;
                        if (min + 1 >= i4) {
                            break;
                        }
                        char charAt2 = str.charAt((scan + min) + 1);
                        if (charAt2 < NumericKeypadConnector.ZERO || charAt2 > '9') {
                            break;
                        }
                        if (obj3 != null) {
                            min++;
                        } else {
                            scan++;
                        }
                        i4 = Math.min(i4 + 1, str.length() - scan);
                    } else {
                        if (charAt < NumericKeypadConnector.ZERO || charAt > '9') {
                            if ((charAt != NumericKeypadConnector.DOT && charAt != ',') || ((this.iFieldType != 8 && this.iFieldType != 9) || i3 >= 0)) {
                                break;
                            }
                            i3 = (scan + min) + 1;
                            i4 = Math.min(i4 + 1, str.length() - scan);
                        } else {
                            obj2 = 1;
                        }
                        min++;
                    }
                }
                if (obj2 == null) {
                    return scan ^ -1;
                }
                if (i2 >= 0 && scan + min != i2) {
                    return scan;
                }
                if (this.iFieldType != 8 && this.iFieldType != 9) {
                    setFieldValue(readWritablePeriod, this.iFieldType, parseInt(str, scan, min));
                } else if (i3 < 0) {
                    setFieldValue(readWritablePeriod, 6, parseInt(str, scan, min));
                    setFieldValue(readWritablePeriod, 7, 0);
                } else {
                    i4 = parseInt(str, scan, (i3 - scan) - 1);
                    setFieldValue(readWritablePeriod, 6, i4);
                    int i5 = (scan + min) - i3;
                    if (i5 <= 0) {
                        i5 = 0;
                    } else {
                        if (i5 >= 3) {
                            i5 = parseInt(str, i3, 3);
                        } else {
                            i3 = parseInt(str, i3, i5);
                            if (i5 == 1) {
                                i5 = i3 * 100;
                            } else {
                                i5 = i3 * 10;
                            }
                        }
                        if (i4 < 0) {
                            i5 = -i5;
                        }
                    }
                    setFieldValue(readWritablePeriod, 7, i5);
                }
                min += scan;
                if (min >= 0 && this.iSuffix != null) {
                    min = this.iSuffix.parse(str, min);
                }
                return min;
            }
        }

        private int parseInt(String str, int i, int i2) {
            int i3 = 0;
            if (i2 >= 10) {
                return Integer.parseInt(str.substring(i, i + i2));
            }
            if (i2 <= 0) {
                return 0;
            }
            int i4;
            int i5 = i + 1;
            int charAt = str.charAt(i);
            int i6 = i2 - 1;
            if (charAt == 45) {
                i6--;
                if (i6 < 0) {
                    return 0;
                }
                i3 = 1;
                i4 = i5 + 1;
                charAt = str.charAt(i5);
            } else {
                i4 = i5;
            }
            charAt -= 48;
            i5 = i4;
            while (true) {
                i4 = i6 - 1;
                if (i6 <= 0) {
                    break;
                }
                charAt = (((charAt << 1) + (charAt << 3)) + str.charAt(i5)) - 48;
                i5++;
                i6 = i4;
            }
            return i3 != 0 ? -charAt : charAt;
        }

        long getFieldValue(ReadablePeriod readablePeriod) {
            PeriodType periodType;
            if (this.iPrintZeroSetting == 4) {
                periodType = null;
            } else {
                periodType = readablePeriod.getPeriodType();
            }
            if (periodType != null && !isSupported(periodType, this.iFieldType)) {
                return Long.MAX_VALUE;
            }
            long j;
            switch (this.iFieldType) {
                case 0:
                    j = (long) readablePeriod.get(DurationFieldType.years());
                    break;
                case 1:
                    j = (long) readablePeriod.get(DurationFieldType.months());
                    break;
                case 2:
                    j = (long) readablePeriod.get(DurationFieldType.weeks());
                    break;
                case 3:
                    j = (long) readablePeriod.get(DurationFieldType.days());
                    break;
                case 4:
                    j = (long) readablePeriod.get(DurationFieldType.hours());
                    break;
                case 5:
                    j = (long) readablePeriod.get(DurationFieldType.minutes());
                    break;
                case 6:
                    j = (long) readablePeriod.get(DurationFieldType.seconds());
                    break;
                case 7:
                    j = (long) readablePeriod.get(DurationFieldType.millis());
                    break;
                case 8:
                case 9:
                    j = (long) readablePeriod.get(DurationFieldType.millis());
                    j += ((long) readablePeriod.get(DurationFieldType.seconds())) * 1000;
                    break;
                default:
                    return Long.MAX_VALUE;
            }
            if (j == 0) {
                int min;
                switch (this.iPrintZeroSetting) {
                    case 1:
                        if (!isZero(readablePeriod) || this.iFieldFormatters[this.iFieldType] != this) {
                            return Long.MAX_VALUE;
                        }
                        min = Math.min(this.iFieldType, 8) - 1;
                        while (min >= 0 && min <= 9) {
                            if (isSupported(periodType, min) && this.iFieldFormatters[min] != null) {
                                return Long.MAX_VALUE;
                            }
                            min--;
                        }
                        break;
                    case 2:
                        if (isZero(readablePeriod) && this.iFieldFormatters[this.iFieldType] == this) {
                            min = this.iFieldType + 1;
                            while (min <= 9) {
                                if (isSupported(periodType, min) && this.iFieldFormatters[min] != null) {
                                    return Long.MAX_VALUE;
                                }
                                min++;
                            }
                            break;
                        }
                        return Long.MAX_VALUE;
                        break;
                    case 5:
                        return Long.MAX_VALUE;
                }
            }
            return j;
        }

        boolean isZero(ReadablePeriod readablePeriod) {
            int size = readablePeriod.size();
            for (int i = 0; i < size; i++) {
                if (readablePeriod.getValue(i) != 0) {
                    return false;
                }
            }
            return true;
        }

        boolean isSupported(PeriodType periodType, int i) {
            switch (i) {
                case 0:
                    return periodType.isSupported(DurationFieldType.years());
                case 1:
                    return periodType.isSupported(DurationFieldType.months());
                case 2:
                    return periodType.isSupported(DurationFieldType.weeks());
                case 3:
                    return periodType.isSupported(DurationFieldType.days());
                case 4:
                    return periodType.isSupported(DurationFieldType.hours());
                case 5:
                    return periodType.isSupported(DurationFieldType.minutes());
                case 6:
                    return periodType.isSupported(DurationFieldType.seconds());
                case 7:
                    return periodType.isSupported(DurationFieldType.millis());
                case 8:
                case 9:
                    return periodType.isSupported(DurationFieldType.seconds()) || periodType.isSupported(DurationFieldType.millis());
                default:
                    return false;
            }
        }

        void setFieldValue(ReadWritablePeriod readWritablePeriod, int i, int i2) {
            switch (i) {
                case 0:
                    readWritablePeriod.setYears(i2);
                    return;
                case 1:
                    readWritablePeriod.setMonths(i2);
                    return;
                case 2:
                    readWritablePeriod.setWeeks(i2);
                    return;
                case 3:
                    readWritablePeriod.setDays(i2);
                    return;
                case 4:
                    readWritablePeriod.setHours(i2);
                    return;
                case 5:
                    readWritablePeriod.setMinutes(i2);
                    return;
                case 6:
                    readWritablePeriod.setSeconds(i2);
                    return;
                case 7:
                    readWritablePeriod.setMillis(i2);
                    return;
                default:
                    return;
            }
        }

        int getFieldType() {
            return this.iFieldType;
        }
    }

    static class Literal implements PeriodParser, PeriodPrinter {
        static final Literal EMPTY = new Literal("");
        private final String iText;

        Literal(String str) {
            this.iText = str;
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            return 0;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            return this.iText.length();
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            stringBuffer.append(this.iText);
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            if (str.regionMatches(true, i, this.iText, 0, this.iText.length())) {
                return this.iText.length() + i;
            }
            return i ^ -1;
        }
    }

    static class Separator implements PeriodParser, PeriodPrinter {
        private volatile PeriodParser iAfterParser;
        private volatile PeriodPrinter iAfterPrinter;
        private final PeriodParser iBeforeParser;
        private final PeriodPrinter iBeforePrinter;
        private final String iFinalText;
        private final String[] iParsedForms;
        private final String iText;
        private final boolean iUseAfter;
        private final boolean iUseBefore;

        Separator(String str, String str2, String[] strArr, PeriodPrinter periodPrinter, PeriodParser periodParser, boolean z, boolean z2) {
            this.iText = str;
            this.iFinalText = str2;
            if ((str2 == null || str.equals(str2)) && (strArr == null || strArr.length == 0)) {
                this.iParsedForms = new String[]{str};
            } else {
                Collection treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                treeSet.add(str);
                treeSet.add(str2);
                if (strArr != null) {
                    int length = strArr.length;
                    while (true) {
                        length--;
                        if (length < 0) {
                            break;
                        }
                        treeSet.add(strArr[length]);
                    }
                }
                ArrayList arrayList = new ArrayList(treeSet);
                Collections.reverse(arrayList);
                this.iParsedForms = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            this.iBeforePrinter = periodPrinter;
            this.iBeforeParser = periodParser;
            this.iUseBefore = z;
            this.iUseAfter = z2;
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            int countFieldsToPrint = this.iBeforePrinter.countFieldsToPrint(readablePeriod, i, locale);
            if (countFieldsToPrint < i) {
                return countFieldsToPrint + this.iAfterPrinter.countFieldsToPrint(readablePeriod, i, locale);
            }
            return countFieldsToPrint;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            int calculatePrintedLength = periodPrinter.calculatePrintedLength(readablePeriod, locale) + periodPrinter2.calculatePrintedLength(readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) <= 0) {
                    return calculatePrintedLength;
                }
                if (!this.iUseAfter) {
                    return calculatePrintedLength + this.iText.length();
                }
                int countFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                if (countFieldsToPrint > 0) {
                    countFieldsToPrint = (countFieldsToPrint > 1 ? this.iText : this.iFinalText).length() + calculatePrintedLength;
                } else {
                    countFieldsToPrint = calculatePrintedLength;
                }
                return countFieldsToPrint;
            } else if (!this.iUseAfter || periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) <= 0) {
                return calculatePrintedLength;
            } else {
                return calculatePrintedLength + this.iText.length();
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            periodPrinter.printTo(stringBuffer, readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        int countFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                        if (countFieldsToPrint > 0) {
                            stringBuffer.append(countFieldsToPrint > 1 ? this.iText : this.iFinalText);
                        }
                    } else {
                        stringBuffer.append(this.iText);
                    }
                }
            } else if (this.iUseAfter && periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                stringBuffer.append(this.iText);
            }
            periodPrinter2.printTo(stringBuffer, readablePeriod, locale);
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            boolean z = true;
            int i2 = 0;
            int parseInto = this.iBeforeParser.parseInto(readWritablePeriod, str, i, locale);
            if (parseInto < 0) {
                return parseInto;
            }
            int parseInto2;
            if (parseInto > i) {
                String[] strArr = this.iParsedForms;
                int length = strArr.length;
                int i3 = 0;
                while (i3 < length) {
                    String str2 = strArr[i3];
                    if (!(str2 == null || str2.length() == 0)) {
                        if (!str.regionMatches(true, parseInto, str2, 0, str2.length())) {
                            i3++;
                        }
                    }
                    if (str2 != null) {
                        i2 = str2.length();
                    }
                    parseInto += i2;
                    parseInto2 = this.iAfterParser.parseInto(readWritablePeriod, str, parseInto, locale);
                    if (parseInto2 < 0) {
                        return parseInto2;
                    }
                    if (z || parseInto2 != parseInto || r4 <= 0) {
                        return (parseInto2 > parseInto || z || this.iUseBefore) ? parseInto2 : parseInto ^ -1;
                    } else {
                        return parseInto ^ -1;
                    }
                }
            }
            z = false;
            i2 = -1;
            parseInto2 = this.iAfterParser.parseInto(readWritablePeriod, str, parseInto, locale);
            if (parseInto2 < 0) {
                return parseInto2;
            }
            if (z) {
            }
            if (parseInto2 > parseInto) {
            }
        }

        Separator finish(PeriodPrinter periodPrinter, PeriodParser periodParser) {
            this.iAfterPrinter = periodPrinter;
            this.iAfterParser = periodParser;
            return this;
        }
    }

    static class SimpleAffix implements PeriodFieldAffix {
        private final String iText;

        SimpleAffix(String str) {
            this.iText = str;
        }

        public int calculatePrintedLength(int i) {
            return this.iText.length();
        }

        public void printTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append(this.iText);
        }

        public int parse(String str, int i) {
            String str2 = this.iText;
            int length = str2.length();
            if (str.regionMatches(true, i, str2, 0, length)) {
                return i + length;
            }
            return i ^ -1;
        }

        public int scan(String str, int i) {
            String str2 = this.iText;
            int length = str2.length();
            int length2 = str.length();
            int i2 = i;
            while (i2 < length2) {
                if (str.regionMatches(true, i2, str2, 0, length)) {
                    return i2;
                }
                switch (str.charAt(i2)) {
                    case '+':
                    case ',':
                    case '-':
                    case '.':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        i2++;
                    default:
                        break;
                }
                return i ^ -1;
            }
            return i ^ -1;
        }
    }

    public PeriodFormatterBuilder() {
        clear();
    }

    public PeriodFormatter toFormatter() {
        PeriodFormatter toFormatter = toFormatter(this.iElementPairs, this.iNotPrinter, this.iNotParser);
        this.iFieldFormatters = (FieldFormatter[]) this.iFieldFormatters.clone();
        return toFormatter;
    }

    public void clear() {
        this.iMinPrintedDigits = 1;
        this.iPrintZeroSetting = 2;
        this.iMaxParsedDigits = 10;
        this.iRejectSignedValues = false;
        this.iPrefix = null;
        if (this.iElementPairs == null) {
            this.iElementPairs = new ArrayList();
        } else {
            this.iElementPairs.clear();
        }
        this.iNotPrinter = false;
        this.iNotParser = false;
        this.iFieldFormatters = new FieldFormatter[10];
    }

    public PeriodFormatterBuilder appendLiteral(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        clearPrefix();
        Object literal = new Literal(str);
        append0(literal, literal);
        return this;
    }

    public PeriodFormatterBuilder appendYears() {
        appendField(0);
        return this;
    }

    public PeriodFormatterBuilder appendMonths() {
        appendField(1);
        return this;
    }

    public PeriodFormatterBuilder appendWeeks() {
        appendField(2);
        return this;
    }

    public PeriodFormatterBuilder appendDays() {
        appendField(3);
        return this;
    }

    public PeriodFormatterBuilder appendHours() {
        appendField(4);
        return this;
    }

    public PeriodFormatterBuilder appendMinutes() {
        appendField(5);
        return this;
    }

    public PeriodFormatterBuilder appendSecondsWithOptionalMillis() {
        appendField(9);
        return this;
    }

    private void appendField(int i) {
        appendField(i, this.iMinPrintedDigits);
    }

    private void appendField(int i, int i2) {
        Object fieldFormatter = new FieldFormatter(i2, this.iPrintZeroSetting, this.iMaxParsedDigits, this.iRejectSignedValues, i, this.iFieldFormatters, this.iPrefix, null);
        append0(fieldFormatter, fieldFormatter);
        this.iFieldFormatters[i] = fieldFormatter;
        this.iPrefix = null;
    }

    public PeriodFormatterBuilder appendSuffix(String str) {
        if (str != null) {
            return appendSuffix(new SimpleAffix(str));
        }
        throw new IllegalArgumentException();
    }

    private PeriodFormatterBuilder appendSuffix(PeriodFieldAffix periodFieldAffix) {
        Object obj = null;
        Object obj2;
        if (this.iElementPairs.size() > 0) {
            obj2 = this.iElementPairs.get(this.iElementPairs.size() - 2);
            obj = obj2;
            obj2 = this.iElementPairs.get(this.iElementPairs.size() - 1);
        } else {
            obj2 = null;
        }
        if (obj == null || r1 == null || obj != r1 || !(obj instanceof FieldFormatter)) {
            throw new IllegalStateException("No field to apply suffix to");
        }
        clearPrefix();
        FieldFormatter fieldFormatter = new FieldFormatter((FieldFormatter) obj, periodFieldAffix);
        this.iElementPairs.set(this.iElementPairs.size() - 2, fieldFormatter);
        this.iElementPairs.set(this.iElementPairs.size() - 1, fieldFormatter);
        this.iFieldFormatters[fieldFormatter.getFieldType()] = fieldFormatter;
        return this;
    }

    public PeriodFormatterBuilder appendSeparatorIfFieldsAfter(String str) {
        return appendSeparator(str, str, null, false, true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private PeriodFormatterBuilder appendSeparator(String str, String str2, String[] strArr, boolean z, boolean z2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException();
        }
        clearPrefix();
        List list = this.iElementPairs;
        if (list.size() != 0) {
            Object[] createComposite;
            Separator separator = null;
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                } else if (list.get(size) instanceof Separator) {
                    break;
                } else {
                    size--;
                }
                if (separator == null && r8.size() == 0) {
                    throw new IllegalStateException("Cannot have two adjacent separators");
                }
                createComposite = createComposite(r8);
                r8.clear();
                separator = new Separator(str, str2, strArr, (PeriodPrinter) createComposite[0], (PeriodParser) createComposite[1], z, z2);
                r8.add(separator);
                r8.add(separator);
            }
            separator = (Separator) list.get(size);
            List subList = list.subList(size + 1, list.size());
            if (separator == null) {
            }
            createComposite = createComposite(subList);
            subList.clear();
            separator = new Separator(str, str2, strArr, (PeriodPrinter) createComposite[0], (PeriodParser) createComposite[1], z, z2);
            subList.add(separator);
            subList.add(separator);
        } else if (z2 && !z) {
            Object separator2 = new Separator(str, str2, strArr, Literal.EMPTY, Literal.EMPTY, z, z2);
            append0(separator2, separator2);
        }
        return this;
    }

    private void clearPrefix() throws IllegalStateException {
        if (this.iPrefix != null) {
            throw new IllegalStateException("Prefix not followed by field");
        }
        this.iPrefix = null;
    }

    private PeriodFormatterBuilder append0(PeriodPrinter periodPrinter, PeriodParser periodParser) {
        int i;
        int i2 = 1;
        this.iElementPairs.add(periodPrinter);
        this.iElementPairs.add(periodParser);
        boolean z = this.iNotPrinter;
        if (periodPrinter == null) {
            i = 1;
        } else {
            i = 0;
        }
        this.iNotPrinter = i | z;
        boolean z2 = this.iNotParser;
        if (periodParser != null) {
            i2 = 0;
        }
        this.iNotParser = z2 | i2;
        return this;
    }

    private static PeriodFormatter toFormatter(List<Object> list, boolean z, boolean z2) {
        if (z && z2) {
            throw new IllegalStateException("Builder has created neither a printer nor a parser");
        }
        int size = list.size();
        if (size >= 2 && (list.get(0) instanceof Separator)) {
            Separator separator = (Separator) list.get(0);
            if (separator.iAfterParser == null && separator.iAfterPrinter == null) {
                PeriodFormatter toFormatter = toFormatter(list.subList(2, size), z, z2);
                Object finish = separator.finish(toFormatter.getPrinter(), toFormatter.getParser());
                return new PeriodFormatter(finish, finish);
            }
        }
        Object[] createComposite = createComposite(list);
        if (z) {
            return new PeriodFormatter(null, (PeriodParser) createComposite[1]);
        }
        if (z2) {
            return new PeriodFormatter((PeriodPrinter) createComposite[0], null);
        }
        return new PeriodFormatter((PeriodPrinter) createComposite[0], (PeriodParser) createComposite[1]);
    }

    private static Object[] createComposite(List<Object> list) {
        switch (list.size()) {
            case 0:
                return new Object[]{Literal.EMPTY, Literal.EMPTY};
            case 1:
                return new Object[]{list.get(0), list.get(1)};
            default:
                Composite composite = new Composite(list);
                return new Object[]{composite, composite};
        }
    }
}
