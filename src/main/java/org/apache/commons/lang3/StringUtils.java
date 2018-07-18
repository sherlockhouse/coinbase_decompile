package org.apache.commons.lang3;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern WHITESPACE_BLOCK = Pattern.compile("\\s+");
    private static boolean java6Available;
    private static Method java6NormalizeMethod;
    private static Object java6NormalizerFormNFD;
    private static final Pattern java6Pattern = sunPattern;
    private static boolean sunAvailable;
    private static Method sunDecomposeMethod;
    private static final Pattern sunPattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    static {
        sunAvailable = false;
        sunDecomposeMethod = null;
        java6Available = false;
        java6NormalizeMethod = null;
        java6NormalizerFormNFD = null;
        try {
            java6NormalizerFormNFD = Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer$Form").getField("NFD").get(null);
            java6NormalizeMethod = Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer").getMethod("normalize", new Class[]{CharSequence.class, normalizerFormClass});
            java6Available = true;
        } catch (ClassNotFoundException e) {
            java6Available = false;
        } catch (NoSuchFieldException e2) {
            java6Available = false;
        } catch (IllegalAccessException e3) {
            java6Available = false;
        } catch (NoSuchMethodException e4) {
            java6Available = false;
        }
        try {
            sunDecomposeMethod = Thread.currentThread().getContextClassLoader().loadClass("sun.text.Normalizer").getMethod("decompose", new Class[]{String.class, Boolean.TYPE, Integer.TYPE});
            sunAvailable = true;
        } catch (ClassNotFoundException e5) {
            sunAvailable = false;
        } catch (NoSuchMethodException e6) {
            sunAvailable = false;
        }
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    public static boolean startsWith(CharSequence str, CharSequence prefix) {
        return startsWith(str, prefix, false);
    }

    private static boolean startsWith(CharSequence str, CharSequence prefix, boolean ignoreCase) {
        if (str == null || prefix == null) {
            if (str == null && prefix == null) {
                return true;
            }
            return false;
        } else if (prefix.length() > str.length()) {
            return false;
        } else {
            return CharSequenceUtils.regionMatches(str, ignoreCase, 0, prefix, 0, prefix.length());
        }
    }

    public static boolean startsWithAny(CharSequence string, CharSequence... searchStrings) {
        if (isEmpty(string) || ArrayUtils.isEmpty(searchStrings)) {
            return false;
        }
        for (CharSequence searchString : searchStrings) {
            if (startsWith(string, searchString)) {
                return true;
            }
        }
        return false;
    }
}
