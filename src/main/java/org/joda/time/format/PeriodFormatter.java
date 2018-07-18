package org.joda.time.format;

import java.util.Locale;
import org.joda.time.MutablePeriod;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

public class PeriodFormatter {
    private final Locale iLocale;
    private final PeriodType iParseType;
    private final PeriodParser iParser;
    private final PeriodPrinter iPrinter;

    public PeriodFormatter(PeriodPrinter periodPrinter, PeriodParser periodParser) {
        this.iPrinter = periodPrinter;
        this.iParser = periodParser;
        this.iLocale = null;
        this.iParseType = null;
    }

    private PeriodFormatter(PeriodPrinter periodPrinter, PeriodParser periodParser, Locale locale, PeriodType periodType) {
        this.iPrinter = periodPrinter;
        this.iParser = periodParser;
        this.iLocale = locale;
        this.iParseType = periodType;
    }

    public PeriodPrinter getPrinter() {
        return this.iPrinter;
    }

    public PeriodParser getParser() {
        return this.iParser;
    }

    public PeriodFormatter withParseType(PeriodType periodType) {
        return periodType == this.iParseType ? this : new PeriodFormatter(this.iPrinter, this.iParser, this.iLocale, periodType);
    }

    public String print(ReadablePeriod readablePeriod) {
        checkPrinter();
        checkPeriod(readablePeriod);
        PeriodPrinter printer = getPrinter();
        StringBuffer stringBuffer = new StringBuffer(printer.calculatePrintedLength(readablePeriod, this.iLocale));
        printer.printTo(stringBuffer, readablePeriod, this.iLocale);
        return stringBuffer.toString();
    }

    private void checkPrinter() {
        if (this.iPrinter == null) {
            throw new UnsupportedOperationException("Printing not supported");
        }
    }

    private void checkPeriod(ReadablePeriod readablePeriod) {
        if (readablePeriod == null) {
            throw new IllegalArgumentException("Period must not be null");
        }
    }

    public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i) {
        checkParser();
        checkPeriod(readWritablePeriod);
        return getParser().parseInto(readWritablePeriod, str, i, this.iLocale);
    }

    public MutablePeriod parseMutablePeriod(String str) {
        checkParser();
        Object mutablePeriod = new MutablePeriod(0, this.iParseType);
        int parseInto = getParser().parseInto(mutablePeriod, str, 0, this.iLocale);
        if (parseInto < 0) {
            parseInto ^= -1;
        } else if (parseInto >= str.length()) {
            return mutablePeriod;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(str, parseInto));
    }

    private void checkParser() {
        if (this.iParser == null) {
            throw new UnsupportedOperationException("Parsing not supported");
        }
    }
}
