package okhttp3;

import com.coinbase.android.utils.CryptoUri;
import java.io.UnsupportedEncodingException;
import okio.ByteString;

public final class Credentials {
    private Credentials() {
    }

    public static String basic(String userName, String password) {
        try {
            return "Basic " + ByteString.of((userName + CryptoUri.URI_SCHEME_DELIMETER + password).getBytes("ISO-8859-1")).base64();
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }
}
