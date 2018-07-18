package org.joda.money.format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joda.money.BigMoney;

public final class MoneyFormatterBuilder {
    private final List<MoneyParser> parsers = new ArrayList();
    private final List<MoneyPrinter> printers = new ArrayList();

    private enum SingletonPrinters implements MoneyPrinter {
        LOCALIZED_SYMBOL;

        public void print(MoneyPrintContext context, Appendable appendable, BigMoney money) throws IOException {
            appendable.append(money.getCurrencyUnit().getSymbol(context.getLocale()));
        }

        public String toString() {
            return "${symbolLocalized}";
        }
    }

    private enum Singletons implements MoneyParser, MoneyPrinter {
        CODE("${code}") {
            public void print(MoneyPrintContext context, Appendable appendable, BigMoney money) throws IOException {
                appendable.append(money.getCurrencyUnit().getCode());
            }
        },
        NUMERIC_3_CODE("${numeric3Code}") {
            public void print(MoneyPrintContext context, Appendable appendable, BigMoney money) throws IOException {
                appendable.append(money.getCurrencyUnit().getNumeric3Code());
            }
        },
        NUMERIC_CODE("${numericCode}") {
            public void print(MoneyPrintContext context, Appendable appendable, BigMoney money) throws IOException {
                appendable.append(Integer.toString(money.getCurrencyUnit().getNumericCode()));
            }
        };
        
        private final String toString;

        private Singletons(String toString) {
            this.toString = toString;
        }

        public String toString() {
            return this.toString;
        }
    }

    public MoneyFormatterBuilder appendAmountLocalized() {
        AmountPrinterParser pp = new AmountPrinterParser(MoneyAmountStyle.LOCALIZED_GROUPING);
        return appendInternal(pp, pp);
    }

    public MoneyFormatterBuilder appendAmount(MoneyAmountStyle style) {
        MoneyFormatter.checkNotNull(style, "MoneyAmountStyle must not be null");
        AmountPrinterParser pp = new AmountPrinterParser(style);
        return appendInternal(pp, pp);
    }

    public MoneyFormatterBuilder appendCurrencyCode() {
        return appendInternal(Singletons.CODE, Singletons.CODE);
    }

    public MoneyFormatterBuilder appendCurrencySymbolLocalized() {
        return appendInternal(SingletonPrinters.LOCALIZED_SYMBOL, null);
    }

    public MoneyFormatterBuilder appendLiteral(CharSequence literal) {
        if (literal == null || literal.length() == 0) {
            return this;
        }
        LiteralPrinterParser pp = new LiteralPrinterParser(literal.toString());
        return appendInternal(pp, pp);
    }

    private MoneyFormatterBuilder appendInternal(MoneyPrinter printer, MoneyParser parser) {
        this.printers.add(printer);
        this.parsers.add(parser);
        return this;
    }

    public MoneyFormatter toFormatter() {
        return toFormatter(Locale.getDefault());
    }

    public MoneyFormatter toFormatter(Locale locale) {
        MoneyFormatter.checkNotNull(locale, "Locale must not be null");
        return new MoneyFormatter(locale, (MoneyPrinter[]) this.printers.toArray(new MoneyPrinter[this.printers.size()]), (MoneyParser[]) this.parsers.toArray(new MoneyParser[this.parsers.size()]));
    }
}
