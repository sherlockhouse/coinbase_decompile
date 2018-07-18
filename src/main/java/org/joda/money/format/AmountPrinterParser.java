package org.joda.money.format;

import com.coinbase.android.ui.NumericKeypadConnector;
import java.io.IOException;
import java.io.Serializable;
import org.joda.money.BigMoney;

final class AmountPrinterParser implements Serializable, MoneyParser, MoneyPrinter {
    private final MoneyAmountStyle style;

    AmountPrinterParser(MoneyAmountStyle style) {
        this.style = style;
    }

    public void print(MoneyPrintContext context, Appendable appendable, BigMoney money) throws IOException {
        int i;
        MoneyAmountStyle activeStyle = this.style.localize(context.getLocale());
        if (money.isNegative()) {
            money = money.negated();
            if (!activeStyle.isAbsValue()) {
                appendable.append(activeStyle.getNegativeSignCharacter().charValue());
            }
        }
        String str = money.getAmount().toPlainString();
        char zeroChar = activeStyle.getZeroCharacter().charValue();
        if (zeroChar != NumericKeypadConnector.ZERO) {
            int diff = zeroChar - 48;
            StringBuilder zeroConvert = new StringBuilder(str);
            for (i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch >= NumericKeypadConnector.ZERO && ch <= '9') {
                    zeroConvert.setCharAt(i, (char) (ch + diff));
                }
            }
            str = zeroConvert.toString();
        }
        int decPoint = str.indexOf(46);
        int afterDecPoint = decPoint + 1;
        if (activeStyle.getGroupingStyle() != GroupingStyle.NONE) {
            int pre;
            int groupingSize = activeStyle.getGroupingSize().intValue();
            int extendedGroupingSize = activeStyle.getExtendedGroupingSize().intValue();
            if (extendedGroupingSize == 0) {
                extendedGroupingSize = groupingSize;
            }
            char groupingChar = activeStyle.getGroupingCharacter().charValue();
            if (decPoint < 0) {
                pre = str.length();
            } else {
                pre = decPoint;
            }
            int post = decPoint < 0 ? 0 : (str.length() - decPoint) - 1;
            appendable.append(str.charAt(0));
            for (i = 1; i < pre; i++) {
                if (isPreGroupingPoint(pre - i, groupingSize, extendedGroupingSize)) {
                    appendable.append(groupingChar);
                }
                appendable.append(str.charAt(i));
            }
            if (decPoint >= 0 || activeStyle.isForcedDecimalPoint()) {
                appendable.append(activeStyle.getDecimalPointCharacter().charValue());
            }
            if (activeStyle.getGroupingStyle() != GroupingStyle.BEFORE_DECIMAL_POINT) {
                for (i = 0; i < post; i++) {
                    appendable.append(str.charAt(i + afterDecPoint));
                    if (isPostGroupingPoint(i, post, groupingSize, extendedGroupingSize)) {
                        appendable.append(groupingChar);
                    }
                }
            } else if (decPoint >= 0) {
                appendable.append(str.substring(afterDecPoint));
            }
        } else if (decPoint < 0) {
            appendable.append(str);
            if (activeStyle.isForcedDecimalPoint()) {
                appendable.append(activeStyle.getDecimalPointCharacter().charValue());
            }
        } else {
            appendable.append(str.subSequence(0, decPoint)).append(activeStyle.getDecimalPointCharacter().charValue()).append(str.substring(afterDecPoint));
        }
    }

    private boolean isPreGroupingPoint(int remaining, int groupingSize, int extendedGroupingSize) {
        if (remaining >= groupingSize + extendedGroupingSize) {
            if ((remaining - groupingSize) % extendedGroupingSize == 0) {
                return true;
            }
            return false;
        } else if (remaining % groupingSize != 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isPostGroupingPoint(int i, int post, int groupingSize, int extendedGroupingSize) {
        boolean atEnd;
        if (i + 1 >= post) {
            atEnd = true;
        } else {
            atEnd = false;
        }
        if (i > groupingSize) {
            return (i - groupingSize) % extendedGroupingSize == extendedGroupingSize + -1 && !atEnd;
        } else {
            if (i % groupingSize != groupingSize - 1 || atEnd) {
                return false;
            }
            return true;
        }
    }

    public String toString() {
        return "${amount}";
    }
}
