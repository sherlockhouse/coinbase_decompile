package com.fasterxml.jackson.databind.util;

import com.coinbase.android.ui.NumericKeypadConnector;
import com.fasterxml.jackson.core.io.NumberInput;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StdDateFormat extends DateFormat {
    protected static final String[] ALL_FORMATS = new String[]{"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"};
    protected static final DateFormat DATE_FORMAT_ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", DEFAULT_LOCALE);
    protected static final DateFormat DATE_FORMAT_ISO8601_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", DEFAULT_LOCALE);
    protected static final DateFormat DATE_FORMAT_PLAIN = new SimpleDateFormat("yyyy-MM-dd", DEFAULT_LOCALE);
    protected static final DateFormat DATE_FORMAT_RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", DEFAULT_LOCALE);
    private static final Locale DEFAULT_LOCALE = Locale.US;
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("GMT");
    public static final StdDateFormat instance = new StdDateFormat();
    protected transient DateFormat _formatISO8601;
    protected transient DateFormat _formatISO8601_z;
    protected transient DateFormat _formatPlain;
    protected transient DateFormat _formatRFC1123;
    protected final Locale _locale;
    protected transient TimeZone _timezone;

    static {
        DATE_FORMAT_RFC1123.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_ISO8601.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_ISO8601_Z.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_PLAIN.setTimeZone(DEFAULT_TIMEZONE);
    }

    public StdDateFormat() {
        this._locale = DEFAULT_LOCALE;
    }

    public StdDateFormat(TimeZone tz, Locale loc) {
        this._timezone = tz;
        this._locale = loc;
    }

    public StdDateFormat withTimeZone(TimeZone tz) {
        if (tz == null) {
            tz = DEFAULT_TIMEZONE;
        }
        return tz.equals(this._timezone) ? this : new StdDateFormat(tz, this._locale);
    }

    public StdDateFormat withLocale(Locale loc) {
        return loc.equals(this._locale) ? this : new StdDateFormat(this._timezone, loc);
    }

    public StdDateFormat clone() {
        return new StdDateFormat(this._timezone, this._locale);
    }

    public static DateFormat getISO8601Format(TimeZone tz, Locale loc) {
        return _cloneFormat(DATE_FORMAT_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", tz, loc);
    }

    public void setTimeZone(TimeZone tz) {
        if (!tz.equals(this._timezone)) {
            this._formatRFC1123 = null;
            this._formatISO8601 = null;
            this._formatISO8601_z = null;
            this._formatPlain = null;
            this._timezone = tz;
        }
    }

    public Date parse(String dateStr) throws ParseException {
        dateStr = dateStr.trim();
        ParsePosition pos = new ParsePosition(0);
        Date result = parse(dateStr, pos);
        if (result != null) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        for (String f : ALL_FORMATS) {
            if (sb.length() > 0) {
                sb.append("\", \"");
            } else {
                sb.append('\"');
            }
            sb.append(f);
        }
        sb.append('\"');
        throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", new Object[]{dateStr, sb.toString()}), pos.getErrorIndex());
    }

    public Date parse(String dateStr, ParsePosition pos) {
        if (looksLikeISO8601(dateStr)) {
            return parseAsISO8601(dateStr, pos);
        }
        int i = dateStr.length();
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            char ch = dateStr.charAt(i);
            if ((ch < NumericKeypadConnector.ZERO || ch > '9') && (i > 0 || ch != '-')) {
                break;
            }
        }
        if (i >= 0 || (dateStr.charAt(0) != '-' && !NumberInput.inLongRange(dateStr, false))) {
            return parseAsRFC1123(dateStr, pos);
        }
        return new Date(Long.parseLong(dateStr));
    }

    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        if (this._formatISO8601 == null) {
            this._formatISO8601 = _cloneFormat(DATE_FORMAT_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", this._timezone, this._locale);
        }
        return this._formatISO8601.format(date, toAppendTo, fieldPosition);
    }

    public String toString() {
        String str = "DateFormat " + getClass().getName();
        TimeZone tz = this._timezone;
        if (tz != null) {
            str = str + " (timezone: " + tz + ")";
        }
        return str + "(locale: " + this._locale + ")";
    }

    protected boolean looksLikeISO8601(String dateStr) {
        if (dateStr.length() >= 5 && Character.isDigit(dateStr.charAt(0)) && Character.isDigit(dateStr.charAt(3)) && dateStr.charAt(4) == '-') {
            return true;
        }
        return false;
    }

    protected Date parseAsISO8601(String dateStr, ParsePosition pos) {
        DateFormat df;
        int len = dateStr.length();
        char c = dateStr.charAt(len - 1);
        if (len <= 10 && Character.isDigit(c)) {
            df = this._formatPlain;
            if (df == null) {
                df = _cloneFormat(DATE_FORMAT_PLAIN, "yyyy-MM-dd", this._timezone, this._locale);
                this._formatPlain = df;
            }
        } else if (c == 'Z') {
            df = this._formatISO8601_z;
            if (df == null) {
                df = _cloneFormat(DATE_FORMAT_ISO8601_Z, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", this._timezone, this._locale);
                this._formatISO8601_z = df;
            }
            if (dateStr.charAt(len - 4) == ':') {
                sb = new StringBuilder(dateStr);
                sb.insert(len - 1, ".000");
                dateStr = sb.toString();
            }
        } else if (hasTimeZone(dateStr)) {
            c = dateStr.charAt(len - 3);
            if (c == ':') {
                sb = new StringBuilder(dateStr);
                sb.delete(len - 3, len - 2);
                dateStr = sb.toString();
            } else if (c == '+' || c == '-') {
                dateStr = dateStr + "00";
            }
            len = dateStr.length();
            if (Character.isDigit(dateStr.charAt(len - 9))) {
                sb = new StringBuilder(dateStr);
                sb.insert(len - 5, ".000");
                dateStr = sb.toString();
            }
            df = this._formatISO8601;
            if (this._formatISO8601 == null) {
                df = _cloneFormat(DATE_FORMAT_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", this._timezone, this._locale);
                this._formatISO8601 = df;
            }
        } else {
            sb = new StringBuilder(dateStr);
            if ((len - dateStr.lastIndexOf(84)) - 1 <= 8) {
                sb.append(".000");
            }
            sb.append('Z');
            dateStr = sb.toString();
            df = this._formatISO8601_z;
            if (df == null) {
                df = _cloneFormat(DATE_FORMAT_ISO8601_Z, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", this._timezone, this._locale);
                this._formatISO8601_z = df;
            }
        }
        return df.parse(dateStr, pos);
    }

    protected Date parseAsRFC1123(String dateStr, ParsePosition pos) {
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = _cloneFormat(DATE_FORMAT_RFC1123, "EEE, dd MMM yyyy HH:mm:ss zzz", this._timezone, this._locale);
        }
        return this._formatRFC1123.parse(dateStr, pos);
    }

    private static final boolean hasTimeZone(String str) {
        int len = str.length();
        if (len >= 6) {
            char c = str.charAt(len - 6);
            if (c == '+' || c == '-') {
                return true;
            }
            c = str.charAt(len - 5);
            if (c == '+' || c == '-') {
                return true;
            }
            c = str.charAt(len - 3);
            if (c == '+' || c == '-') {
                return true;
            }
        }
        return false;
    }

    private static final DateFormat _cloneFormat(DateFormat df, String format, TimeZone tz, Locale loc) {
        if (loc.equals(DEFAULT_LOCALE)) {
            df = (DateFormat) df.clone();
            if (tz != null) {
                df.setTimeZone(tz);
            }
        } else {
            df = new SimpleDateFormat(format, loc);
            if (tz == null) {
                tz = DEFAULT_TIMEZONE;
            }
            df.setTimeZone(tz);
        }
        return df;
    }
}
