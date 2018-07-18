package org.joda.money.format;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import org.joda.money.BigMoney;
import org.joda.money.BigMoneyProvider;

public final class MoneyFormatter implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = (!MoneyFormatter.class.desiredAssertionStatus());
    private final Locale locale;
    private final MultiPrinterParser printerParser;

    static void checkNotNull(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }

    MoneyFormatter(Locale locale, MoneyPrinter[] printers, MoneyParser[] parsers) {
        if (!$assertionsDisabled && locale == null) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && printers == null) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && parsers == null) {
            throw new AssertionError();
        } else if ($assertionsDisabled || printers.length == parsers.length) {
            this.locale = locale;
            this.printerParser = new MultiPrinterParser(printers, parsers);
        } else {
            throw new AssertionError();
        }
    }

    public boolean isPrinter() {
        return this.printerParser.isPrinter();
    }

    public String print(BigMoneyProvider moneyProvider) {
        StringBuilder buf = new StringBuilder();
        print(buf, moneyProvider);
        return buf.toString();
    }

    public void print(Appendable appendable, BigMoneyProvider moneyProvider) {
        try {
            printIO(appendable, moneyProvider);
        } catch (IOException ex) {
            throw new MoneyFormatException(ex.getMessage(), ex);
        }
    }

    public void printIO(Appendable appendable, BigMoneyProvider moneyProvider) throws IOException {
        checkNotNull(moneyProvider, "BigMoneyProvider must not be null");
        if (isPrinter()) {
            BigMoney money = BigMoney.of(moneyProvider);
            this.printerParser.print(new MoneyPrintContext(this.locale), appendable, money);
            return;
        }
        throw new UnsupportedOperationException("MoneyFomatter has not been configured to be able to print");
    }

    public String toString() {
        return this.printerParser.toString();
    }
}
