package org.joda.time.format;

import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

public class DateTimeFormatter {
    private final Chronology iChrono;
    private final int iDefaultYear;
    private final Locale iLocale;
    private final boolean iOffsetParsed;
    private final DateTimeParser iParser;
    private final Integer iPivotYear;
    private final DateTimePrinter iPrinter;
    private final DateTimeZone iZone;

    public DateTimeFormatter(DateTimePrinter dateTimePrinter, DateTimeParser dateTimeParser) {
        this.iPrinter = dateTimePrinter;
        this.iParser = dateTimeParser;
        this.iLocale = null;
        this.iOffsetParsed = false;
        this.iChrono = null;
        this.iZone = null;
        this.iPivotYear = null;
        this.iDefaultYear = 2000;
    }

    private DateTimeFormatter(DateTimePrinter dateTimePrinter, DateTimeParser dateTimeParser, Locale locale, boolean z, Chronology chronology, DateTimeZone dateTimeZone, Integer num, int i) {
        this.iPrinter = dateTimePrinter;
        this.iParser = dateTimeParser;
        this.iLocale = locale;
        this.iOffsetParsed = z;
        this.iChrono = chronology;
        this.iZone = dateTimeZone;
        this.iPivotYear = num;
        this.iDefaultYear = i;
    }

    public DateTimePrinter getPrinter() {
        return this.iPrinter;
    }

    public DateTimeParser getParser() {
        return this.iParser;
    }

    public DateTimeFormatter withLocale(Locale locale) {
        if (locale == getLocale() || (locale != null && locale.equals(getLocale()))) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, locale, this.iOffsetParsed, this.iChrono, this.iZone, this.iPivotYear, this.iDefaultYear);
    }

    public Locale getLocale() {
        return this.iLocale;
    }

    public DateTimeFormatter withOffsetParsed() {
        return this.iOffsetParsed ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, true, this.iChrono, null, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeFormatter withChronology(Chronology chronology) {
        if (this.iChrono == chronology) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, chronology, this.iZone, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeFormatter withZoneUTC() {
        return withZone(DateTimeZone.UTC);
    }

    public DateTimeFormatter withZone(DateTimeZone dateTimeZone) {
        if (this.iZone == dateTimeZone) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, false, this.iChrono, dateTimeZone, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeZone getZone() {
        return this.iZone;
    }

    public void printTo(StringBuffer stringBuffer, ReadableInstant readableInstant) {
        printTo(stringBuffer, DateTimeUtils.getInstantMillis(readableInstant), DateTimeUtils.getInstantChronology(readableInstant));
    }

    public void printTo(StringBuffer stringBuffer, long j) {
        printTo(stringBuffer, j, null);
    }

    public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial) {
        DateTimePrinter requirePrinter = requirePrinter();
        if (readablePartial == null) {
            throw new IllegalArgumentException("The partial must not be null");
        }
        requirePrinter.printTo(stringBuffer, readablePartial, this.iLocale);
    }

    public String print(ReadableInstant readableInstant) {
        StringBuffer stringBuffer = new StringBuffer(requirePrinter().estimatePrintedLength());
        printTo(stringBuffer, readableInstant);
        return stringBuffer.toString();
    }

    public String print(ReadablePartial readablePartial) {
        StringBuffer stringBuffer = new StringBuffer(requirePrinter().estimatePrintedLength());
        printTo(stringBuffer, readablePartial);
        return stringBuffer.toString();
    }

    private void printTo(StringBuffer stringBuffer, long j, Chronology chronology) {
        DateTimePrinter requirePrinter = requirePrinter();
        Chronology selectChronology = selectChronology(chronology);
        DateTimeZone zone = selectChronology.getZone();
        int offset = zone.getOffset(j);
        long j2 = ((long) offset) + j;
        if ((j ^ j2) < 0 && (((long) offset) ^ j) >= 0) {
            zone = DateTimeZone.UTC;
            offset = 0;
            j2 = j;
        }
        requirePrinter.printTo(stringBuffer, j2, selectChronology.withUTC(), offset, zone, this.iLocale);
    }

    private DateTimePrinter requirePrinter() {
        DateTimePrinter dateTimePrinter = this.iPrinter;
        if (dateTimePrinter != null) {
            return dateTimePrinter;
        }
        throw new UnsupportedOperationException("Printing not supported");
    }

    public long parseMillis(String str) {
        DateTimeParser requireParser = requireParser();
        DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0, selectChronology(this.iChrono), this.iLocale, this.iPivotYear, this.iDefaultYear);
        int parseInto = requireParser.parseInto(dateTimeParserBucket, str, 0);
        if (parseInto < 0) {
            parseInto ^= -1;
        } else if (parseInto >= str.length()) {
            return dateTimeParserBucket.computeMillis(true, str);
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(str, parseInto));
    }

    public LocalDate parseLocalDate(String str) {
        return parseLocalDateTime(str).toLocalDate();
    }

    public LocalTime parseLocalTime(String str) {
        return parseLocalDateTime(str).toLocalTime();
    }

    public LocalDateTime parseLocalDateTime(String str) {
        DateTimeParser requireParser = requireParser();
        Chronology withUTC = selectChronology(null).withUTC();
        DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0, withUTC, this.iLocale, this.iPivotYear, this.iDefaultYear);
        int parseInto = requireParser.parseInto(dateTimeParserBucket, str, 0);
        if (parseInto < 0) {
            parseInto ^= -1;
        } else if (parseInto >= str.length()) {
            long computeMillis = dateTimeParserBucket.computeMillis(true, str);
            if (dateTimeParserBucket.getOffsetInteger() != null) {
                withUTC = withUTC.withZone(DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffsetInteger().intValue()));
            } else if (dateTimeParserBucket.getZone() != null) {
                withUTC = withUTC.withZone(dateTimeParserBucket.getZone());
            }
            return new LocalDateTime(computeMillis, withUTC);
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(str, parseInto));
    }

    public DateTime parseDateTime(String str) {
        DateTimeParser requireParser = requireParser();
        Chronology selectChronology = selectChronology(null);
        DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0, selectChronology, this.iLocale, this.iPivotYear, this.iDefaultYear);
        int parseInto = requireParser.parseInto(dateTimeParserBucket, str, 0);
        if (parseInto < 0) {
            parseInto ^= -1;
        } else if (parseInto >= str.length()) {
            long computeMillis = dateTimeParserBucket.computeMillis(true, str);
            if (this.iOffsetParsed && dateTimeParserBucket.getOffsetInteger() != null) {
                selectChronology = selectChronology.withZone(DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffsetInteger().intValue()));
            } else if (dateTimeParserBucket.getZone() != null) {
                selectChronology = selectChronology.withZone(dateTimeParserBucket.getZone());
            }
            DateTime dateTime = new DateTime(computeMillis, selectChronology);
            if (this.iZone != null) {
                return dateTime.withZone(this.iZone);
            }
            return dateTime;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(str, parseInto));
    }

    private DateTimeParser requireParser() {
        DateTimeParser dateTimeParser = this.iParser;
        if (dateTimeParser != null) {
            return dateTimeParser;
        }
        throw new UnsupportedOperationException("Parsing not supported");
    }

    private Chronology selectChronology(Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        if (this.iChrono != null) {
            chronology2 = this.iChrono;
        }
        if (this.iZone != null) {
            return chronology2.withZone(this.iZone);
        }
        return chronology2;
    }
}
