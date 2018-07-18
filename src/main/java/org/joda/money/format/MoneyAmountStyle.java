package org.joda.money.format;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class MoneyAmountStyle implements Serializable {
    public static final MoneyAmountStyle ASCII_DECIMAL_COMMA_GROUP3_DOT = new MoneyAmountStyle(48, 43, 45, 44, GroupingStyle.FULL, 46, 3, 0, false, false);
    public static final MoneyAmountStyle ASCII_DECIMAL_COMMA_GROUP3_SPACE = new MoneyAmountStyle(48, 43, 45, 44, GroupingStyle.FULL, 32, 3, 0, false, false);
    public static final MoneyAmountStyle ASCII_DECIMAL_COMMA_NO_GROUPING = new MoneyAmountStyle(48, 43, 45, 44, GroupingStyle.NONE, 46, 3, 0, false, false);
    public static final MoneyAmountStyle ASCII_DECIMAL_POINT_GROUP3_COMMA = new MoneyAmountStyle(48, 43, 45, 46, GroupingStyle.FULL, 44, 3, 0, false, false);
    public static final MoneyAmountStyle ASCII_DECIMAL_POINT_GROUP3_SPACE = new MoneyAmountStyle(48, 43, 45, 46, GroupingStyle.FULL, 32, 3, 0, false, false);
    public static final MoneyAmountStyle ASCII_DECIMAL_POINT_NO_GROUPING = new MoneyAmountStyle(48, 43, 45, 46, GroupingStyle.NONE, 44, 3, 0, false, false);
    private static final ConcurrentMap<Locale, MoneyAmountStyle> LOCALIZED_CACHE = new ConcurrentHashMap();
    public static final MoneyAmountStyle LOCALIZED_GROUPING = new MoneyAmountStyle(-1, -1, -1, -1, GroupingStyle.FULL, -1, -1, -1, false, false);
    public static final MoneyAmountStyle LOCALIZED_NO_GROUPING = new MoneyAmountStyle(-1, -1, -1, -1, GroupingStyle.NONE, -1, -1, -1, false, false);
    private final boolean absValue;
    private final int decimalPointCharacter;
    private final int extendedGroupingSize;
    private final boolean forceDecimalPoint;
    private final int groupingCharacter;
    private final int groupingSize;
    private final GroupingStyle groupingStyle;
    private final int negativeCharacter;
    private final int positiveCharacter;
    private final int zeroCharacter;

    private MoneyAmountStyle(int zeroCharacter, int positiveCharacter, int negativeCharacter, int decimalPointCharacter, GroupingStyle groupingStyle, int groupingCharacter, int groupingSize, int extendedGroupingSize, boolean forceDecimalPoint, boolean absValue) {
        this.zeroCharacter = zeroCharacter;
        this.positiveCharacter = positiveCharacter;
        this.negativeCharacter = negativeCharacter;
        this.decimalPointCharacter = decimalPointCharacter;
        this.groupingStyle = groupingStyle;
        this.groupingCharacter = groupingCharacter;
        this.groupingSize = groupingSize;
        this.extendedGroupingSize = extendedGroupingSize;
        this.forceDecimalPoint = forceDecimalPoint;
        this.absValue = absValue;
    }

    public MoneyAmountStyle localize(Locale locale) {
        MoneyFormatter.checkNotNull(locale, "Locale must not be null");
        MoneyAmountStyle result = this;
        MoneyAmountStyle protoStyle = null;
        if (this.zeroCharacter < 0) {
            protoStyle = getLocalizedStyle(locale);
            result = withZeroCharacter(protoStyle.getZeroCharacter());
        }
        if (this.positiveCharacter < 0) {
            protoStyle = getLocalizedStyle(locale);
            result = result.withPositiveSignCharacter(protoStyle.getPositiveSignCharacter());
        }
        if (this.negativeCharacter < 0) {
            protoStyle = getLocalizedStyle(locale);
            result = result.withNegativeSignCharacter(protoStyle.getNegativeSignCharacter());
        }
        if (this.decimalPointCharacter < 0) {
            if (protoStyle == null) {
                protoStyle = getLocalizedStyle(locale);
            }
            result = result.withDecimalPointCharacter(protoStyle.getDecimalPointCharacter());
        }
        if (this.groupingCharacter < 0) {
            if (protoStyle == null) {
                protoStyle = getLocalizedStyle(locale);
            }
            result = result.withGroupingCharacter(protoStyle.getGroupingCharacter());
        }
        if (this.groupingSize < 0 && this.groupingStyle != GroupingStyle.NONE) {
            if (protoStyle == null) {
                protoStyle = getLocalizedStyle(locale);
            }
            result = result.withGroupingSize(protoStyle.getGroupingSize());
        }
        if (this.extendedGroupingSize >= 0) {
            return result;
        }
        if (protoStyle == null) {
            protoStyle = getLocalizedStyle(locale);
        }
        return result.withExtendedGroupingSize(protoStyle.getExtendedGroupingSize());
    }

    private static MoneyAmountStyle getLocalizedStyle(Locale locale) {
        MoneyAmountStyle protoStyle = (MoneyAmountStyle) LOCALIZED_CACHE.get(locale);
        if (protoStyle != null) {
            return protoStyle;
        }
        DecimalFormatSymbols symbols;
        try {
            symbols = (DecimalFormatSymbols) DecimalFormatSymbols.class.getMethod("getInstance", new Class[]{Locale.class}).invoke(null, new Object[]{locale});
        } catch (Exception e) {
            symbols = new DecimalFormatSymbols(locale);
        }
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        protoStyle = new MoneyAmountStyle(symbols.getZeroDigit(), 43, symbols.getMinusSign(), symbols.getMonetaryDecimalSeparator(), GroupingStyle.FULL, symbols.getGroupingSeparator(), format instanceof DecimalFormat ? ((DecimalFormat) format).getGroupingSize() : 3, 0, false, false);
        LOCALIZED_CACHE.putIfAbsent(locale, protoStyle);
        return protoStyle;
    }

    public Character getZeroCharacter() {
        return this.zeroCharacter < 0 ? null : Character.valueOf((char) this.zeroCharacter);
    }

    public MoneyAmountStyle withZeroCharacter(Character zeroCharacter) {
        int zeroVal = zeroCharacter == null ? -1 : zeroCharacter.charValue();
        return zeroVal == this.zeroCharacter ? this : new MoneyAmountStyle(zeroVal, this.positiveCharacter, this.negativeCharacter, this.decimalPointCharacter, this.groupingStyle, this.groupingCharacter, this.groupingSize, this.extendedGroupingSize, this.forceDecimalPoint, this.absValue);
    }

    public Character getPositiveSignCharacter() {
        return this.positiveCharacter < 0 ? null : Character.valueOf((char) this.positiveCharacter);
    }

    public MoneyAmountStyle withPositiveSignCharacter(Character positiveCharacter) {
        int positiveVal = positiveCharacter == null ? -1 : positiveCharacter.charValue();
        return positiveVal == this.positiveCharacter ? this : new MoneyAmountStyle(this.zeroCharacter, positiveVal, this.negativeCharacter, this.decimalPointCharacter, this.groupingStyle, this.groupingCharacter, this.groupingSize, this.extendedGroupingSize, this.forceDecimalPoint, this.absValue);
    }

    public Character getNegativeSignCharacter() {
        return this.negativeCharacter < 0 ? null : Character.valueOf((char) this.negativeCharacter);
    }

    public MoneyAmountStyle withNegativeSignCharacter(Character negativeCharacter) {
        int negativeVal = negativeCharacter == null ? -1 : negativeCharacter.charValue();
        return negativeVal == this.negativeCharacter ? this : new MoneyAmountStyle(this.zeroCharacter, this.positiveCharacter, negativeVal, this.decimalPointCharacter, this.groupingStyle, this.groupingCharacter, this.groupingSize, this.extendedGroupingSize, this.forceDecimalPoint, this.absValue);
    }

    public Character getDecimalPointCharacter() {
        return this.decimalPointCharacter < 0 ? null : Character.valueOf((char) this.decimalPointCharacter);
    }

    public MoneyAmountStyle withDecimalPointCharacter(Character decimalPointCharacter) {
        int dpVal = decimalPointCharacter == null ? -1 : decimalPointCharacter.charValue();
        return dpVal == this.decimalPointCharacter ? this : new MoneyAmountStyle(this.zeroCharacter, this.positiveCharacter, this.negativeCharacter, dpVal, this.groupingStyle, this.groupingCharacter, this.groupingSize, this.extendedGroupingSize, this.forceDecimalPoint, this.absValue);
    }

    public Character getGroupingCharacter() {
        return this.groupingCharacter < 0 ? null : Character.valueOf((char) this.groupingCharacter);
    }

    public MoneyAmountStyle withGroupingCharacter(Character groupingCharacter) {
        int groupingVal = groupingCharacter == null ? -1 : groupingCharacter.charValue();
        return groupingVal == this.groupingCharacter ? this : new MoneyAmountStyle(this.zeroCharacter, this.positiveCharacter, this.negativeCharacter, this.decimalPointCharacter, this.groupingStyle, groupingVal, this.groupingSize, this.extendedGroupingSize, this.forceDecimalPoint, this.absValue);
    }

    public Integer getGroupingSize() {
        return this.groupingSize < 0 ? null : Integer.valueOf(this.groupingSize);
    }

    public MoneyAmountStyle withGroupingSize(Integer groupingSize) {
        int sizeVal = groupingSize == null ? -1 : groupingSize.intValue();
        if (groupingSize == null || sizeVal > 0) {
            return sizeVal == this.groupingSize ? this : new MoneyAmountStyle(this.zeroCharacter, this.positiveCharacter, this.negativeCharacter, this.decimalPointCharacter, this.groupingStyle, this.groupingCharacter, sizeVal, this.extendedGroupingSize, this.forceDecimalPoint, this.absValue);
        } else {
            throw new IllegalArgumentException("Grouping size must be greater than zero");
        }
    }

    public Integer getExtendedGroupingSize() {
        return this.extendedGroupingSize < 0 ? null : Integer.valueOf(this.extendedGroupingSize);
    }

    public MoneyAmountStyle withExtendedGroupingSize(Integer extendedGroupingSize) {
        int sizeVal = extendedGroupingSize == null ? -1 : extendedGroupingSize.intValue();
        if (extendedGroupingSize == null || sizeVal >= 0) {
            return sizeVal == this.extendedGroupingSize ? this : new MoneyAmountStyle(this.zeroCharacter, this.positiveCharacter, this.negativeCharacter, this.decimalPointCharacter, this.groupingStyle, this.groupingCharacter, this.groupingSize, sizeVal, this.forceDecimalPoint, this.absValue);
        } else {
            throw new IllegalArgumentException("Extended grouping size must not be negative");
        }
    }

    public GroupingStyle getGroupingStyle() {
        return this.groupingStyle;
    }

    public boolean isForcedDecimalPoint() {
        return this.forceDecimalPoint;
    }

    public boolean isAbsValue() {
        return this.absValue;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MoneyAmountStyle)) {
            return false;
        }
        MoneyAmountStyle otherStyle = (MoneyAmountStyle) other;
        if (this.zeroCharacter == otherStyle.zeroCharacter && this.positiveCharacter == otherStyle.positiveCharacter && this.negativeCharacter == otherStyle.negativeCharacter && this.decimalPointCharacter == otherStyle.decimalPointCharacter && this.groupingStyle == otherStyle.groupingStyle && this.groupingCharacter == otherStyle.groupingCharacter && this.groupingSize == otherStyle.groupingSize && this.forceDecimalPoint == otherStyle.forceDecimalPoint && this.absValue == otherStyle.absValue) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((13 + (this.zeroCharacter * 17)) + (this.positiveCharacter * 17)) + (this.negativeCharacter * 17)) + (this.decimalPointCharacter * 17)) + (this.groupingStyle.hashCode() * 17)) + (this.groupingCharacter * 17)) + (this.groupingSize * 17)) + (this.forceDecimalPoint ? 2 : 4)) + (this.absValue ? 3 : 9);
    }

    public String toString() {
        return "MoneyAmountStyle['" + getZeroCharacter() + "','" + getPositiveSignCharacter() + "','" + getNegativeSignCharacter() + "','" + getDecimalPointCharacter() + "','" + getGroupingStyle() + "," + getGroupingCharacter() + "','" + getGroupingSize() + "'," + isForcedDecimalPoint() + "'," + isAbsValue() + "]";
    }
}
