package org.joda.time.tz;

import org.joda.time.DateTimeZone;

public class CachedDateTimeZone extends DateTimeZone {
    private static final int cInfoCacheMask;
    private final Info[] iInfoCache = new Info[(cInfoCacheMask + 1)];
    private final DateTimeZone iZone;

    private static final class Info {
        private String iNameKey;
        Info iNextInfo;
        private int iOffset = Integer.MIN_VALUE;
        public final long iPeriodStart;
        private int iStandardOffset = Integer.MIN_VALUE;
        public final DateTimeZone iZoneRef;

        Info(DateTimeZone dateTimeZone, long j) {
            this.iPeriodStart = j;
            this.iZoneRef = dateTimeZone;
        }

        public String getNameKey(long j) {
            if (this.iNextInfo != null && j >= this.iNextInfo.iPeriodStart) {
                return this.iNextInfo.getNameKey(j);
            }
            if (this.iNameKey == null) {
                this.iNameKey = this.iZoneRef.getNameKey(this.iPeriodStart);
            }
            return this.iNameKey;
        }

        public int getOffset(long j) {
            if (this.iNextInfo != null && j >= this.iNextInfo.iPeriodStart) {
                return this.iNextInfo.getOffset(j);
            }
            if (this.iOffset == Integer.MIN_VALUE) {
                this.iOffset = this.iZoneRef.getOffset(this.iPeriodStart);
            }
            return this.iOffset;
        }
    }

    static {
        Integer integer;
        int i;
        try {
            integer = Integer.getInteger("org.joda.time.tz.CachedDateTimeZone.size");
        } catch (SecurityException e) {
            integer = null;
        }
        if (integer == null) {
            i = 512;
        } else {
            i = 0;
            for (int intValue = integer.intValue() - 1; intValue > 0; intValue >>= 1) {
                i++;
            }
            i = 1 << i;
        }
        cInfoCacheMask = i - 1;
    }

    public static CachedDateTimeZone forZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone instanceof CachedDateTimeZone) {
            return (CachedDateTimeZone) dateTimeZone;
        }
        return new CachedDateTimeZone(dateTimeZone);
    }

    private CachedDateTimeZone(DateTimeZone dateTimeZone) {
        super(dateTimeZone.getID());
        this.iZone = dateTimeZone;
    }

    public String getNameKey(long j) {
        return getInfo(j).getNameKey(j);
    }

    public int getOffset(long j) {
        return getInfo(j).getOffset(j);
    }

    public boolean isFixed() {
        return this.iZone.isFixed();
    }

    public long nextTransition(long j) {
        return this.iZone.nextTransition(j);
    }

    public long previousTransition(long j) {
        return this.iZone.previousTransition(j);
    }

    public int hashCode() {
        return this.iZone.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CachedDateTimeZone) {
            return this.iZone.equals(((CachedDateTimeZone) obj).iZone);
        }
        return false;
    }

    private Info getInfo(long j) {
        int i = (int) (j >> 32);
        Info[] infoArr = this.iInfoCache;
        int i2 = i & cInfoCacheMask;
        Info info = infoArr[i2];
        if (info != null && ((int) (info.iPeriodStart >> 32)) == i) {
            return info;
        }
        info = createInfo(j);
        infoArr[i2] = info;
        return info;
    }

    private Info createInfo(long j) {
        long j2 = j & -4294967296L;
        Info info = new Info(this.iZone, j2);
        long j3 = j2 | 4294967295L;
        Info info2 = info;
        while (true) {
            long nextTransition = this.iZone.nextTransition(j2);
            if (nextTransition == j2 || nextTransition > j3) {
                return info;
            }
            Info info3 = new Info(this.iZone, nextTransition);
            info2.iNextInfo = info3;
            info2 = info3;
            j2 = nextTransition;
        }
        return info;
    }
}
