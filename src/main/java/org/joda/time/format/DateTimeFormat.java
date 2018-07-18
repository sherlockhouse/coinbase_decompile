package org.joda.time.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

public class DateTimeFormat {
    private static final Map<String, DateTimeFormatter> PATTERN_CACHE = new LinkedHashMap<String, DateTimeFormatter>(7) {
        protected boolean removeEldestEntry(Entry<String, DateTimeFormatter> entry) {
            return size() > 500;
        }
    };
    private static final DateTimeFormatter[] STYLE_CACHE = new DateTimeFormatter[25];

    static class StyleFormatter implements DateTimeParser, DateTimePrinter {
        private static final Map<String, DateTimeFormatter> cCache = new HashMap();
        private final int iDateStyle;
        private final int iTimeStyle;
        private final int iType;

        StyleFormatter(int i, int i2, int i3) {
            this.iDateStyle = i;
            this.iTimeStyle = i2;
            this.iType = i3;
        }

        public int estimatePrintedLength() {
            return 40;
        }

        public void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) {
            getFormatter(locale).getPrinter().printTo(stringBuffer, j, chronology, i, dateTimeZone, locale);
        }

        public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale) {
            getFormatter(locale).getPrinter().printTo(stringBuffer, readablePartial, locale);
        }

        public int estimateParsedLength() {
            return 40;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, String str, int i) {
            return getFormatter(dateTimeParserBucket.getLocale()).getParser().parseInto(dateTimeParserBucket, str, i);
        }

        private DateTimeFormatter getFormatter(Locale locale) {
            DateTimeFormatter dateTimeFormatter;
            if (locale == null) {
                locale = Locale.getDefault();
            }
            String str = Integer.toString((this.iType + (this.iDateStyle << 4)) + (this.iTimeStyle << 8)) + locale.toString();
            synchronized (cCache) {
                dateTimeFormatter = (DateTimeFormatter) cCache.get(str);
                if (dateTimeFormatter == null) {
                    dateTimeFormatter = DateTimeFormat.forPattern(getPattern(locale));
                    cCache.put(str, dateTimeFormatter);
                }
            }
            return dateTimeFormatter;
        }

        String getPattern(Locale locale) {
            DateFormat dateFormat = null;
            switch (this.iType) {
                case 0:
                    dateFormat = DateFormat.getDateInstance(this.iDateStyle, locale);
                    break;
                case 1:
                    dateFormat = DateFormat.getTimeInstance(this.iTimeStyle, locale);
                    break;
                case 2:
                    dateFormat = DateFormat.getDateTimeInstance(this.iDateStyle, this.iTimeStyle, locale);
                    break;
            }
            if (dateFormat instanceof SimpleDateFormat) {
                return ((SimpleDateFormat) dateFormat).toPattern();
            }
            throw new IllegalArgumentException("No datetime pattern for locale: " + locale);
        }
    }

    public static DateTimeFormatter forPattern(String str) {
        return createFormatterForPattern(str);
    }

    public static DateTimeFormatter forStyle(String str) {
        return createFormatterForStyle(str);
    }

    private static void parsePatternTo(DateTimeFormatterBuilder dateTimeFormatterBuilder, String str) {
        int length = str.length();
        int[] iArr = new int[1];
        int i = 0;
        while (i < length) {
            iArr[0] = i;
            String parseToken = parseToken(str, iArr);
            int i2 = iArr[0];
            int length2 = parseToken.length();
            if (length2 != 0) {
                char charAt = parseToken.charAt(0);
                switch (charAt) {
                    case '\'':
                        parseToken = parseToken.substring(1);
                        if (parseToken.length() != 1) {
                            dateTimeFormatterBuilder.appendLiteral(new String(parseToken));
                            break;
                        } else {
                            dateTimeFormatterBuilder.appendLiteral(parseToken.charAt(0));
                            break;
                        }
                    case 'C':
                        dateTimeFormatterBuilder.appendCenturyOfEra(length2, length2);
                        break;
                    case 'D':
                        dateTimeFormatterBuilder.appendDayOfYear(length2);
                        break;
                    case 'E':
                        if (length2 < 4) {
                            dateTimeFormatterBuilder.appendDayOfWeekShortText();
                            break;
                        } else {
                            dateTimeFormatterBuilder.appendDayOfWeekText();
                            break;
                        }
                    case 'G':
                        dateTimeFormatterBuilder.appendEraText();
                        break;
                    case 'H':
                        dateTimeFormatterBuilder.appendHourOfDay(length2);
                        break;
                    case 'K':
                        dateTimeFormatterBuilder.appendHourOfHalfday(length2);
                        break;
                    case 'M':
                        if (length2 >= 3) {
                            if (length2 < 4) {
                                dateTimeFormatterBuilder.appendMonthOfYearShortText();
                                break;
                            } else {
                                dateTimeFormatterBuilder.appendMonthOfYearText();
                                break;
                            }
                        }
                        dateTimeFormatterBuilder.appendMonthOfYear(length2);
                        break;
                    case 'S':
                        dateTimeFormatterBuilder.appendFractionOfSecond(length2, length2);
                        break;
                    case 'Y':
                    case 'x':
                    case 'y':
                        if (length2 != 2) {
                            i = 9;
                            if (i2 + 1 < length) {
                                iArr[0] = iArr[0] + 1;
                                if (isNumericToken(parseToken(str, iArr))) {
                                    i = length2;
                                }
                                iArr[0] = iArr[0] - 1;
                            }
                            switch (charAt) {
                                case 'Y':
                                    dateTimeFormatterBuilder.appendYearOfEra(length2, i);
                                    break;
                                case 'x':
                                    dateTimeFormatterBuilder.appendWeekyear(length2, i);
                                    break;
                                case 'y':
                                    dateTimeFormatterBuilder.appendYear(length2, i);
                                    break;
                                default:
                                    break;
                            }
                        }
                        boolean z = true;
                        if (i2 + 1 < length) {
                            iArr[0] = iArr[0] + 1;
                            if (isNumericToken(parseToken(str, iArr))) {
                                z = false;
                            }
                            iArr[0] = iArr[0] - 1;
                        }
                        switch (charAt) {
                            case 'x':
                                dateTimeFormatterBuilder.appendTwoDigitWeekyear(new DateTime().getWeekyear() - 30, z);
                                break;
                            default:
                                dateTimeFormatterBuilder.appendTwoDigitYear(new DateTime().getYear() - 30, z);
                                break;
                        }
                    case 'Z':
                        if (length2 != 1) {
                            if (length2 != 2) {
                                dateTimeFormatterBuilder.appendTimeZoneId();
                                break;
                            }
                            dateTimeFormatterBuilder.appendTimeZoneOffset(null, "Z", true, 2, 2);
                            break;
                        }
                        dateTimeFormatterBuilder.appendTimeZoneOffset(null, "Z", false, 2, 2);
                        break;
                    case 'a':
                        dateTimeFormatterBuilder.appendHalfdayOfDayText();
                        break;
                    case 'd':
                        dateTimeFormatterBuilder.appendDayOfMonth(length2);
                        break;
                    case 'e':
                        dateTimeFormatterBuilder.appendDayOfWeek(length2);
                        break;
                    case 'h':
                        dateTimeFormatterBuilder.appendClockhourOfHalfday(length2);
                        break;
                    case 'k':
                        dateTimeFormatterBuilder.appendClockhourOfDay(length2);
                        break;
                    case 'm':
                        dateTimeFormatterBuilder.appendMinuteOfHour(length2);
                        break;
                    case 's':
                        dateTimeFormatterBuilder.appendSecondOfMinute(length2);
                        break;
                    case 'w':
                        dateTimeFormatterBuilder.appendWeekOfWeekyear(length2);
                        break;
                    case 'z':
                        if (length2 < 4) {
                            dateTimeFormatterBuilder.appendTimeZoneShortName(null);
                            break;
                        } else {
                            dateTimeFormatterBuilder.appendTimeZoneName();
                            break;
                        }
                    default:
                        throw new IllegalArgumentException("Illegal pattern component: " + parseToken);
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    private static String parseToken(String str, int[] iArr) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = iArr[0];
        int length = str.length();
        char charAt = str.charAt(i);
        if ((charAt < 'A' || charAt > 'Z') && (charAt < 'a' || charAt > 'z')) {
            stringBuilder.append('\'');
            int i2 = 0;
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 != '\'') {
                    if (i2 == 0 && ((charAt2 >= 'A' && charAt2 <= 'Z') || (charAt2 >= 'a' && charAt2 <= 'z'))) {
                        i--;
                        break;
                    }
                    stringBuilder.append(charAt2);
                } else if (i + 1 >= length || str.charAt(i + 1) != '\'') {
                    i2 = i2 == 0 ? 1 : 0;
                } else {
                    i++;
                    stringBuilder.append(charAt2);
                }
                i++;
            }
        } else {
            stringBuilder.append(charAt);
            while (i + 1 < length && str.charAt(i + 1) == charAt) {
                stringBuilder.append(charAt);
                i++;
            }
        }
        iArr[0] = i;
        return stringBuilder.toString();
    }

    private static boolean isNumericToken(String str) {
        int length = str.length();
        if (length > 0) {
            switch (str.charAt(0)) {
                case 'C':
                case 'D':
                case 'F':
                case 'H':
                case 'K':
                case 'S':
                case 'W':
                case 'Y':
                case 'c':
                case 'd':
                case 'e':
                case 'h':
                case 'k':
                case 'm':
                case 's':
                case 'w':
                case 'x':
                case 'y':
                    return true;
                case 'M':
                    if (length <= 2) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    private static DateTimeFormatter createFormatterForPattern(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Invalid pattern specification");
        }
        DateTimeFormatter dateTimeFormatter;
        synchronized (PATTERN_CACHE) {
            dateTimeFormatter = (DateTimeFormatter) PATTERN_CACHE.get(str);
            if (dateTimeFormatter == null) {
                DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
                parsePatternTo(dateTimeFormatterBuilder, str);
                dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();
                PATTERN_CACHE.put(str, dateTimeFormatter);
            }
        }
        return dateTimeFormatter;
    }

    private static DateTimeFormatter createFormatterForStyle(String str) {
        if (str == null || str.length() != 2) {
            throw new IllegalArgumentException("Invalid style specification: " + str);
        }
        int selectStyle = selectStyle(str.charAt(0));
        int selectStyle2 = selectStyle(str.charAt(1));
        if (selectStyle != 4 || selectStyle2 != 4) {
            return createFormatterForStyleIndex(selectStyle, selectStyle2);
        }
        throw new IllegalArgumentException("Style '--' is invalid");
    }

    private static DateTimeFormatter createFormatterForStyleIndex(int i, int i2) {
        int i3 = ((i << 2) + i) + i2;
        if (i3 >= STYLE_CACHE.length) {
            return createDateTimeFormatter(i, i2);
        }
        DateTimeFormatter dateTimeFormatter;
        synchronized (STYLE_CACHE) {
            dateTimeFormatter = STYLE_CACHE[i3];
            if (dateTimeFormatter == null) {
                dateTimeFormatter = createDateTimeFormatter(i, i2);
                STYLE_CACHE[i3] = dateTimeFormatter;
            }
        }
        return dateTimeFormatter;
    }

    private static DateTimeFormatter createDateTimeFormatter(int i, int i2) {
        int i3 = 2;
        if (i == 4) {
            i3 = 1;
        } else if (i2 == 4) {
            i3 = 0;
        }
        Object styleFormatter = new StyleFormatter(i, i2, i3);
        return new DateTimeFormatter(styleFormatter, styleFormatter);
    }

    private static int selectStyle(char c) {
        switch (c) {
            case '-':
                return 4;
            case 'F':
                return 0;
            case 'L':
                return 1;
            case 'M':
                return 2;
            case 'S':
                return 3;
            default:
                throw new IllegalArgumentException("Invalid style character: " + c);
        }
    }
}
