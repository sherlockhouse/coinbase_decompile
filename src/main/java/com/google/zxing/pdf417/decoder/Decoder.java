package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;

public final class Decoder {
    private final ErrorCorrection errorCorrection = new ErrorCorrection();

    public DecoderResult decode(BitMatrix bits) throws FormatException, ChecksumException {
        BitMatrixParser parser = new BitMatrixParser(bits);
        int[] codewords = parser.readCodewords();
        if (codewords.length == 0) {
            throw FormatException.getFormatInstance();
        }
        int numECCodewords = 1 << (parser.getECLevel() + 1);
        correctErrors(codewords, parser.getErasures(), numECCodewords);
        verifyCodewordCount(codewords, numECCodewords);
        return DecodedBitStreamParser.decode(codewords);
    }

    private static void verifyCodewordCount(int[] codewords, int numECCodewords) throws FormatException {
        if (codewords.length < 4) {
            throw FormatException.getFormatInstance();
        }
        int numberOfCodewords = codewords[0];
        if (numberOfCodewords > codewords.length) {
            throw FormatException.getFormatInstance();
        } else if (numberOfCodewords != 0) {
        } else {
            if (numECCodewords < codewords.length) {
                codewords[0] = codewords.length - numECCodewords;
                return;
            }
            throw FormatException.getFormatInstance();
        }
    }

    private void correctErrors(int[] codewords, int[] erasures, int numECCodewords) throws ChecksumException {
        if (erasures.length > (numECCodewords / 2) + 3 || numECCodewords < 0 || numECCodewords > 512) {
            throw ChecksumException.getChecksumInstance();
        }
        this.errorCorrection.decode(codewords, numECCodewords, erasures);
    }
}
