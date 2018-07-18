package org.joda.time.chrono;

import java.lang.ref.WeakReference;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.TreeMap;
import java.util.WeakHashMap;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.IllegalFieldValueException;

class GJLocaleSymbols {
    private static WeakHashMap<Locale, GJLocaleSymbols> cCache = new WeakHashMap();
    private static final GJLocaleSymbols[] cFastCache = new GJLocaleSymbols[64];
    private final String[] iDaysOfWeek;
    private final String[] iEras;
    private final String[] iHalfday;
    private final WeakReference<Locale> iLocale;
    private final int iMaxDayOfWeekLength;
    private final int iMaxEraLength;
    private final int iMaxHalfdayLength;
    private final int iMaxMonthLength;
    private final int iMaxShortDayOfWeekLength;
    private final int iMaxShortMonthLength;
    private final String[] iMonths;
    private final TreeMap<String, Integer> iParseDaysOfWeek;
    private final TreeMap<String, Integer> iParseEras;
    private final TreeMap<String, Integer> iParseMonths;
    private final String[] iShortDaysOfWeek;
    private final String[] iShortMonths;

    public static GJLocaleSymbols forLocale(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        int identityHashCode = System.identityHashCode(locale) & 63;
        GJLocaleSymbols gJLocaleSymbols = cFastCache[identityHashCode];
        if (gJLocaleSymbols == null || gJLocaleSymbols.iLocale.get() != locale) {
            synchronized (cCache) {
                gJLocaleSymbols = (GJLocaleSymbols) cCache.get(locale);
                if (gJLocaleSymbols == null) {
                    gJLocaleSymbols = new GJLocaleSymbols(locale);
                    cCache.put(locale, gJLocaleSymbols);
                }
            }
            cFastCache[identityHashCode] = gJLocaleSymbols;
        }
        return gJLocaleSymbols;
    }

    private static String[] realignMonths(String[] strArr) {
        String[] strArr2 = new String[13];
        for (int i = 1; i < 13; i++) {
            strArr2[i] = strArr[i - 1];
        }
        return strArr2;
    }

    private static String[] realignDaysOfWeek(String[] strArr) {
        String[] strArr2 = new String[8];
        for (int i = 1; i < 8; i++) {
            int i2;
            if (i < 7) {
                i2 = i + 1;
            } else {
                i2 = 1;
            }
            strArr2[i] = strArr[i2];
        }
        return strArr2;
    }

    private static void addSymbols(TreeMap<String, Integer> treeMap, String[] strArr, Integer[] numArr) {
        int length = strArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                Object obj = strArr[length];
                if (obj != null) {
                    treeMap.put(obj, numArr[length]);
                }
            } else {
                return;
            }
        }
    }

    private static void addNumerals(TreeMap<String, Integer> treeMap, int i, int i2, Integer[] numArr) {
        while (i <= i2) {
            treeMap.put(String.valueOf(i).intern(), numArr[i]);
            i++;
        }
    }

    private static int maxLength(String[] strArr) {
        int i = 0;
        int length = strArr.length;
        while (true) {
            int i2 = length - 1;
            if (i2 < 0) {
                return i;
            }
            String str = strArr[i2];
            if (str != null) {
                length = str.length();
                if (length > i) {
                    i = length;
                    length = i2;
                }
            }
            length = i;
            i = length;
            length = i2;
        }
    }

    private GJLocaleSymbols(Locale locale) {
        this.iLocale = new WeakReference(locale);
        DateFormatSymbols dateFormatSymbols = DateTimeUtils.getDateFormatSymbols(locale);
        this.iEras = dateFormatSymbols.getEras();
        this.iDaysOfWeek = realignDaysOfWeek(dateFormatSymbols.getWeekdays());
        this.iShortDaysOfWeek = realignDaysOfWeek(dateFormatSymbols.getShortWeekdays());
        this.iMonths = realignMonths(dateFormatSymbols.getMonths());
        this.iShortMonths = realignMonths(dateFormatSymbols.getShortMonths());
        this.iHalfday = dateFormatSymbols.getAmPmStrings();
        Integer[] numArr = new Integer[13];
        for (int i = 0; i < 13; i++) {
            numArr[i] = Integer.valueOf(i);
        }
        this.iParseEras = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        addSymbols(this.iParseEras, this.iEras, numArr);
        if ("en".equals(locale.getLanguage())) {
            this.iParseEras.put("BCE", numArr[0]);
            this.iParseEras.put("CE", numArr[1]);
        }
        this.iParseDaysOfWeek = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        addSymbols(this.iParseDaysOfWeek, this.iDaysOfWeek, numArr);
        addSymbols(this.iParseDaysOfWeek, this.iShortDaysOfWeek, numArr);
        addNumerals(this.iParseDaysOfWeek, 1, 7, numArr);
        this.iParseMonths = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        addSymbols(this.iParseMonths, this.iMonths, numArr);
        addSymbols(this.iParseMonths, this.iShortMonths, numArr);
        addNumerals(this.iParseMonths, 1, 12, numArr);
        this.iMaxEraLength = maxLength(this.iEras);
        this.iMaxDayOfWeekLength = maxLength(this.iDaysOfWeek);
        this.iMaxShortDayOfWeekLength = maxLength(this.iShortDaysOfWeek);
        this.iMaxMonthLength = maxLength(this.iMonths);
        this.iMaxShortMonthLength = maxLength(this.iShortMonths);
        this.iMaxHalfdayLength = maxLength(this.iHalfday);
    }

    public String eraValueToText(int i) {
        return this.iEras[i];
    }

    public int eraTextToValue(String str) {
        Integer num = (Integer) this.iParseEras.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalFieldValueException(DateTimeFieldType.era(), str);
    }

    public int getEraMaxTextLength() {
        return this.iMaxEraLength;
    }

    public String monthOfYearValueToText(int i) {
        return this.iMonths[i];
    }

    public String monthOfYearValueToShortText(int i) {
        return this.iShortMonths[i];
    }

    public int monthOfYearTextToValue(String str) {
        Integer num = (Integer) this.iParseMonths.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalFieldValueException(DateTimeFieldType.monthOfYear(), str);
    }

    public int getMonthMaxTextLength() {
        return this.iMaxMonthLength;
    }

    public String dayOfWeekValueToText(int i) {
        return this.iDaysOfWeek[i];
    }

    public String dayOfWeekValueToShortText(int i) {
        return this.iShortDaysOfWeek[i];
    }

    public int dayOfWeekTextToValue(String str) {
        Integer num = (Integer) this.iParseDaysOfWeek.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalFieldValueException(DateTimeFieldType.dayOfWeek(), str);
    }

    public int getDayOfWeekMaxTextLength() {
        return this.iMaxDayOfWeekLength;
    }

    public String halfdayValueToText(int i) {
        return this.iHalfday[i];
    }

    public int halfdayTextToValue(String str) {
        String[] strArr = this.iHalfday;
        int length = strArr.length;
        do {
            length--;
            if (length < 0) {
                throw new IllegalFieldValueException(DateTimeFieldType.halfdayOfDay(), str);
            }
        } while (!strArr[length].equalsIgnoreCase(str));
        return length;
    }

    public int getHalfdayMaxTextLength() {
        return this.iMaxHalfdayLength;
    }
}
