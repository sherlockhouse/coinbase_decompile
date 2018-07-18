package org.joda.time.format;

import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

public interface DateTimePrinter {
    int estimatePrintedLength();

    void printTo(StringBuffer stringBuffer, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale);

    void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial, Locale locale);
}
