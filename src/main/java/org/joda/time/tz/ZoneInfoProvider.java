package org.joda.time.tz;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.DateTimeZone;

public class ZoneInfoProvider implements Provider {
    private final File iFileDir;
    private final ClassLoader iLoader;
    private final String iResourcePath;
    private final Map<String, Object> iZoneInfoMap;

    public ZoneInfoProvider(String str) throws IOException {
        this(str, null, false);
    }

    private ZoneInfoProvider(String str, ClassLoader classLoader, boolean z) throws IOException {
        if (str == null) {
            throw new IllegalArgumentException("No resource path provided");
        }
        if (!str.endsWith("/")) {
            str = str + '/';
        }
        this.iFileDir = null;
        this.iResourcePath = str;
        if (classLoader == null && !z) {
            classLoader = getClass().getClassLoader();
        }
        this.iLoader = classLoader;
        this.iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
    }

    public DateTimeZone getZone(String str) {
        if (str == null) {
            return null;
        }
        Object obj = this.iZoneInfoMap.get(str);
        if (obj == null) {
            return null;
        }
        if (str.equals(obj)) {
            return loadZoneData(str);
        }
        if (!(obj instanceof SoftReference)) {
            return getZone((String) obj);
        }
        DateTimeZone dateTimeZone = (DateTimeZone) ((SoftReference) obj).get();
        if (dateTimeZone == null) {
            return loadZoneData(str);
        }
        return dateTimeZone;
    }

    public Set<String> getAvailableIDs() {
        return new TreeSet(this.iZoneInfoMap.keySet());
    }

    protected void uncaughtException(Exception exception) {
        Thread currentThread = Thread.currentThread();
        currentThread.getThreadGroup().uncaughtException(currentThread, exception);
    }

    private InputStream openResource(String str) throws IOException {
        if (this.iFileDir != null) {
            return new FileInputStream(new File(this.iFileDir, str));
        }
        InputStream resourceAsStream;
        String concat = this.iResourcePath.concat(str);
        if (this.iLoader != null) {
            resourceAsStream = this.iLoader.getResourceAsStream(concat);
        } else {
            resourceAsStream = ClassLoader.getSystemResourceAsStream(concat);
        }
        if (resourceAsStream != null) {
            return resourceAsStream;
        }
        throw new IOException("Resource not found: \"" + concat + "\" ClassLoader: " + (this.iLoader != null ? this.iLoader.toString() : "system"));
    }

    private DateTimeZone loadZoneData(String str) {
        InputStream openResource;
        Exception e;
        Throwable th;
        try {
            openResource = openResource(str);
            try {
                DateTimeZone readFrom = DateTimeZoneBuilder.readFrom(openResource, str);
                this.iZoneInfoMap.put(str, new SoftReference(readFrom));
                if (openResource == null) {
                    return readFrom;
                }
                try {
                    openResource.close();
                    return readFrom;
                } catch (IOException e2) {
                    return readFrom;
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    uncaughtException(e);
                    this.iZoneInfoMap.remove(str);
                    if (openResource != null) {
                        try {
                            openResource.close();
                        } catch (IOException e4) {
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (openResource != null) {
                        try {
                            openResource.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e6) {
            e = e6;
            openResource = null;
            uncaughtException(e);
            this.iZoneInfoMap.remove(str);
            if (openResource != null) {
                openResource.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            openResource = null;
            if (openResource != null) {
                openResource.close();
            }
            throw th;
        }
    }

    private static Map<String, Object> loadZoneInfoMap(InputStream inputStream) throws IOException {
        Map<String, Object> concurrentHashMap = new ConcurrentHashMap();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            readZoneInfoMap(dataInputStream, concurrentHashMap);
            concurrentHashMap.put("UTC", new SoftReference(DateTimeZone.UTC));
            return concurrentHashMap;
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
            }
        }
    }

    private static void readZoneInfoMap(DataInputStream dataInputStream, Map<String, Object> map) throws IOException {
        int i;
        int i2 = 0;
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        String[] strArr = new String[readUnsignedShort];
        for (i = 0; i < readUnsignedShort; i++) {
            strArr[i] = dataInputStream.readUTF().intern();
        }
        i = dataInputStream.readUnsignedShort();
        while (i2 < i) {
            try {
                map.put(strArr[dataInputStream.readUnsignedShort()], strArr[dataInputStream.readUnsignedShort()]);
                i2++;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IOException("Corrupt zone info map");
            }
        }
    }
}
