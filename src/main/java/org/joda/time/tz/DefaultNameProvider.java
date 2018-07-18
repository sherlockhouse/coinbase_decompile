package org.joda.time.tz;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.joda.time.DateTimeUtils;

public class DefaultNameProvider implements NameProvider {
    private HashMap<Locale, Map<String, Map<String, Object>>> iByLocaleCache = createCache();

    public String getShortName(Locale locale, String str, String str2) {
        String[] nameSet = getNameSet(locale, str, str2);
        return nameSet == null ? null : nameSet[0];
    }

    public String getName(Locale locale, String str, String str2) {
        String[] nameSet = getNameSet(locale, str, str2);
        return nameSet == null ? null : nameSet[1];
    }

    private synchronized String[] getNameSet(Locale locale, String str, String str2) {
        String[] strArr;
        Object[] objArr = null;
        synchronized (this) {
            if (locale == null || str == null || str2 == null) {
                strArr = null;
            } else {
                Map map;
                Map map2 = (Map) this.iByLocaleCache.get(locale);
                if (map2 == null) {
                    HashMap hashMap = this.iByLocaleCache;
                    HashMap createCache = createCache();
                    hashMap.put(locale, createCache);
                    map = createCache;
                } else {
                    map = map2;
                }
                map2 = (Map) map.get(str);
                if (map2 == null) {
                    Object[] objArr2;
                    map2 = createCache();
                    map.put(str, map2);
                    for (Object[] objArr3 : DateTimeUtils.getDateFormatSymbols(Locale.ENGLISH).getZoneStrings()) {
                        if (objArr3 != null && objArr3.length == 5 && str.equals(objArr3[0])) {
                            objArr2 = objArr3;
                            break;
                        }
                    }
                    objArr2 = null;
                    for (Object[] objArr4 : DateTimeUtils.getDateFormatSymbols(locale).getZoneStrings()) {
                        if (objArr4 != null && objArr4.length == 5 && str.equals(objArr4[0])) {
                            objArr = objArr4;
                            break;
                        }
                    }
                    if (!(objArr2 == null || objArr == null)) {
                        map2.put(objArr2[2], new String[]{objArr[2], objArr[1]});
                        if (objArr2[2].equals(objArr2[4])) {
                            map2.put(objArr2[4] + "-Summer", new String[]{objArr[4], objArr[3]});
                        } else {
                            map2.put(objArr2[4], new String[]{objArr[4], objArr[3]});
                        }
                    }
                }
                strArr = (String[]) map2.get(str2);
            }
        }
        return strArr;
    }

    private HashMap createCache() {
        return new HashMap(7);
    }
}
