package okhttp3.internal.http;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

public final class HttpHeaders {
    private HttpHeaders() {
    }

    public static long contentLength(Response response) {
        return contentLength(response.headers());
    }

    public static long contentLength(Headers headers) {
        return stringToLong(headers.get("Content-Length"));
    }

    private static long stringToLong(String s) {
        long j = -1;
        if (s != null) {
            try {
                j = Long.parseLong(s);
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    public static boolean varyMatches(Response cachedResponse, Headers cachedRequest, Request newRequest) {
        for (String field : varyFields(cachedResponse)) {
            if (!Util.equal(cachedRequest.values(field), newRequest.headers(field))) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasVaryAll(Response response) {
        return hasVaryAll(response.headers());
    }

    public static boolean hasVaryAll(Headers responseHeaders) {
        return varyFields(responseHeaders).contains("*");
    }

    private static Set<String> varyFields(Response response) {
        return varyFields(response.headers());
    }

    public static Set<String> varyFields(Headers responseHeaders) {
        Set<String> result = Collections.emptySet();
        int size = responseHeaders.size();
        for (int i = 0; i < size; i++) {
            if ("Vary".equalsIgnoreCase(responseHeaders.name(i))) {
                String value = responseHeaders.value(i);
                if (result.isEmpty()) {
                    result = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                }
                for (String varyField : value.split(",")) {
                    result.add(varyField.trim());
                }
            }
        }
        return result;
    }

    public static Headers varyHeaders(Response response) {
        return varyHeaders(response.networkResponse().request().headers(), response.headers());
    }

    public static Headers varyHeaders(Headers requestHeaders, Headers responseHeaders) {
        Set<String> varyFields = varyFields(responseHeaders);
        if (varyFields.isEmpty()) {
            return new Builder().build();
        }
        Builder result = new Builder();
        int size = requestHeaders.size();
        for (int i = 0; i < size; i++) {
            String fieldName = requestHeaders.name(i);
            if (varyFields.contains(fieldName)) {
                result.add(fieldName, requestHeaders.value(i));
            }
        }
        return result.build();
    }

    public static List<Challenge> parseChallenges(Headers responseHeaders, String challengeHeader) {
        List<Challenge> result = new ArrayList();
        int size = responseHeaders.size();
        for (int i = 0; i < size; i++) {
            if (challengeHeader.equalsIgnoreCase(responseHeaders.name(i))) {
                String value = responseHeaders.value(i);
                int pos = 0;
                while (pos < value.length()) {
                    int tokenStart = pos;
                    pos = skipUntil(value, pos, " ");
                    String scheme = value.substring(tokenStart, pos).trim();
                    pos = skipWhitespace(value, pos);
                    if (!value.regionMatches(true, pos, "realm=\"", 0, "realm=\"".length())) {
                        break;
                    }
                    pos += "realm=\"".length();
                    int realmStart = pos;
                    pos = skipUntil(value, pos, "\"");
                    String realm = value.substring(realmStart, pos);
                    pos = skipWhitespace(value, skipUntil(value, pos + 1, ",") + 1);
                    result.add(new Challenge(scheme, realm));
                }
            }
        }
        return result;
    }

    public static void receiveHeaders(CookieJar cookieJar, HttpUrl url, Headers headers) {
        if (cookieJar != CookieJar.NO_COOKIES) {
            List<Cookie> cookies = Cookie.parseAll(url, headers);
            if (!cookies.isEmpty()) {
                cookieJar.saveFromResponse(url, cookies);
            }
        }
    }

    public static boolean hasBody(Response response) {
        if (response.request().method().equals("HEAD")) {
            return false;
        }
        int responseCode = response.code();
        if ((responseCode < 100 || responseCode >= Callback.DEFAULT_DRAG_ANIMATION_DURATION) && responseCode != 204 && responseCode != 304) {
            return true;
        }
        if (contentLength(response) != -1 || "chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return true;
        }
        return false;
    }

    public static int skipUntil(String input, int pos, String characters) {
        while (pos < input.length() && characters.indexOf(input.charAt(pos)) == -1) {
            pos++;
        }
        return pos;
    }

    public static int skipWhitespace(String input, int pos) {
        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (c != ' ' && c != '\t') {
                break;
            }
            pos++;
        }
        return pos;
    }

    public static int parseSeconds(String value, int defaultValue) {
        try {
            long seconds = Long.parseLong(value);
            if (seconds > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (seconds < 0) {
                return 0;
            }
            return (int) seconds;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
