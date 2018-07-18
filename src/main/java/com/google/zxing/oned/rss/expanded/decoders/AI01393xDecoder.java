package com.google.zxing.oned.rss.expanded.decoders;

import com.coinbase.android.ui.NumericKeypadConnector;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI01393xDecoder extends AI01decoder {
    AI01393xDecoder(BitArray information) {
        super(information);
    }

    public String parseInformation() throws NotFoundException {
        if (getInformation().getSize() < 48) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder buf = new StringBuilder();
        encodeCompressedGtin(buf, 8);
        int lastAIdigit = getGeneralDecoder().extractNumericValueFromBitArray(48, 2);
        buf.append("(393");
        buf.append(lastAIdigit);
        buf.append(')');
        int firstThreeDigits = getGeneralDecoder().extractNumericValueFromBitArray(50, 10);
        if (firstThreeDigits / 100 == 0) {
            buf.append(NumericKeypadConnector.ZERO);
        }
        if (firstThreeDigits / 10 == 0) {
            buf.append(NumericKeypadConnector.ZERO);
        }
        buf.append(firstThreeDigits);
        buf.append(getGeneralDecoder().decodeGeneralPurposeField(60, null).getNewString());
        return buf.toString();
    }
}
