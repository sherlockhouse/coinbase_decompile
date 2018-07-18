package org.joda.money.format;

import com.coinbase.android.utils.CryptoUri;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import org.joda.money.BigMoney;

final class MultiPrinterParser implements Serializable, MoneyParser, MoneyPrinter {
    private final MoneyParser[] parsers;
    private final MoneyPrinter[] printers;

    MultiPrinterParser(MoneyPrinter[] printers, MoneyParser[] parsers) {
        this.printers = printers;
        this.parsers = parsers;
    }

    boolean isPrinter() {
        return !Arrays.asList(this.printers).contains(null);
    }

    boolean isParser() {
        return !Arrays.asList(this.parsers).contains(null);
    }

    public void print(MoneyPrintContext context, Appendable appendable, BigMoney money) throws IOException {
        for (MoneyPrinter printer : this.printers) {
            printer.print(context, appendable, money);
        }
    }

    public String toString() {
        int i = 0;
        StringBuilder buf1 = new StringBuilder();
        if (isPrinter()) {
            for (MoneyPrinter printer : this.printers) {
                buf1.append(printer.toString());
            }
        }
        StringBuilder buf2 = new StringBuilder();
        if (isParser()) {
            MoneyParser[] moneyParserArr = this.parsers;
            int length = moneyParserArr.length;
            while (i < length) {
                buf2.append(moneyParserArr[i].toString());
                i++;
            }
        }
        String str1 = buf1.toString();
        String str2 = buf2.toString();
        if (isPrinter() && !isParser()) {
            return str1;
        }
        if (!isParser() || isPrinter()) {
            return !str1.equals(str2) ? str1 + CryptoUri.URI_SCHEME_DELIMETER + str2 : str1;
        } else {
            return str2;
        }
    }
}
