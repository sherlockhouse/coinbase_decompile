package org.joda.money.format;

import java.io.IOException;
import org.joda.money.BigMoney;

public interface MoneyPrinter {
    void print(MoneyPrintContext moneyPrintContext, Appendable appendable, BigMoney bigMoney) throws IOException;
}
