package org.joda.money.format;

import java.io.IOException;
import java.io.Serializable;
import org.joda.money.BigMoney;

final class LiteralPrinterParser implements Serializable, MoneyParser, MoneyPrinter {
    private final String literal;

    LiteralPrinterParser(String literal) {
        this.literal = literal;
    }

    public void print(MoneyPrintContext context, Appendable appendable, BigMoney money) throws IOException {
        appendable.append(this.literal);
    }

    public String toString() {
        return "'" + this.literal + "'";
    }
}
