package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.util.Locale;
import java.util.TimeZone;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JacksonJodaFormat {
    protected static final Locale DEFAULT_LOCALE = Locale.getDefault();
    protected final boolean _explicitLocale;
    protected final boolean _explicitTimezone;
    protected final DateTimeFormatter _formatter;
    protected final TimeZone _jdkTimezone;
    protected final Locale _locale;
    protected final Boolean _useTimestamp;

    public JacksonJodaFormat(DateTimeFormatter defaultFormatter) {
        this._useTimestamp = null;
        this._jdkTimezone = defaultFormatter.getZone().toTimeZone();
        this._locale = DEFAULT_LOCALE;
        this._formatter = defaultFormatter;
        this._explicitTimezone = false;
        this._explicitLocale = false;
    }

    public JacksonJodaFormat(JacksonJodaFormat base, Boolean useTimestamp) {
        this._useTimestamp = useTimestamp;
        this._formatter = base._formatter;
        this._jdkTimezone = base._jdkTimezone;
        this._explicitTimezone = base._explicitTimezone;
        this._locale = base._locale;
        this._explicitLocale = base._explicitLocale;
    }

    public JacksonJodaFormat(JacksonJodaFormat base, DateTimeFormatter formatter) {
        this._useTimestamp = base._useTimestamp;
        this._formatter = formatter;
        this._jdkTimezone = base._jdkTimezone;
        this._explicitTimezone = base._explicitTimezone;
        this._locale = base._locale;
        this._explicitLocale = base._explicitLocale;
    }

    public JacksonJodaFormat(JacksonJodaFormat base, TimeZone jdkTimezone) {
        this._useTimestamp = base._useTimestamp;
        this._jdkTimezone = jdkTimezone;
        this._explicitTimezone = true;
        this._locale = base._locale;
        this._explicitLocale = base._explicitLocale;
        this._formatter = base._formatter.withZone(DateTimeZone.forTimeZone(jdkTimezone));
    }

    public JacksonJodaFormat(JacksonJodaFormat base, Locale locale) {
        this._useTimestamp = base._useTimestamp;
        this._jdkTimezone = base._jdkTimezone;
        this._explicitTimezone = base._explicitTimezone;
        this._locale = locale;
        this._explicitLocale = true;
        this._formatter = base._formatter.withLocale(locale);
    }

    protected JacksonJodaFormat withUseTimestamp(Boolean useTimestamp) {
        return (this._useTimestamp == null || !this._useTimestamp.equals(useTimestamp)) ? new JacksonJodaFormat(this, useTimestamp) : this;
    }

    protected JacksonJodaFormat withFormat(String format) {
        if (format == null || format.isEmpty()) {
            return this;
        }
        DateTimeFormatter formatter;
        if (_isStyle(format)) {
            formatter = DateTimeFormat.forStyle(format);
        } else {
            formatter = DateTimeFormat.forPattern(format);
        }
        if (this._locale != null) {
            formatter = formatter.withLocale(this._locale);
        }
        return new JacksonJodaFormat(this, formatter.withZone(this._formatter.getZone()));
    }

    protected JacksonJodaFormat withTimeZone(TimeZone tz) {
        if (tz != null) {
            return (this._jdkTimezone == null || !this._jdkTimezone.equals(tz)) ? new JacksonJodaFormat(this, tz) : this;
        } else {
            return this;
        }
    }

    protected JacksonJodaFormat withLocale(Locale locale) {
        if (locale != null) {
            return (this._locale == null || !this._locale.equals(locale)) ? new JacksonJodaFormat(this, locale) : this;
        } else {
            return this;
        }
    }

    public boolean useTimestamp(SerializerProvider provider) {
        if (this._useTimestamp != null) {
            return this._useTimestamp.booleanValue();
        }
        return provider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public DateTimeFormatter createFormatter(SerializerProvider provider) {
        DateTimeFormatter formatter = this._formatter;
        if (!this._explicitLocale) {
            Locale loc = provider.getLocale();
            if (!(loc == null || loc.equals(this._locale))) {
                formatter = formatter.withLocale(loc);
            }
        }
        if (this._explicitTimezone) {
            return formatter;
        }
        TimeZone tz = provider.getTimeZone();
        if (tz == null || tz.equals(this._jdkTimezone)) {
            return formatter;
        }
        return formatter.withZone(DateTimeZone.forTimeZone(tz));
    }

    protected static boolean _isStyle(String formatStr) {
        if (formatStr.length() == 2 && "SMLF-".indexOf(formatStr.charAt(0)) >= 0 && "SMLF-".indexOf(formatStr.charAt(0)) >= 0) {
            return true;
        }
        return false;
    }
}
