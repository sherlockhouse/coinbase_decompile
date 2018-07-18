package org.joda.time.tz;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;

public class DateTimeZoneBuilder {
    private final ArrayList<Object> iRuleSets = new ArrayList(10);

    private static final class DSTZone extends DateTimeZone {
        final Recurrence iEndRecurrence;
        final int iStandardOffset;
        final Recurrence iStartRecurrence;

        static DSTZone readFrom(DataInput dataInput, String str) throws IOException {
            return new DSTZone(str, (int) DateTimeZoneBuilder.readMillis(dataInput), Recurrence.readFrom(dataInput), Recurrence.readFrom(dataInput));
        }

        DSTZone(String str, int i, Recurrence recurrence, Recurrence recurrence2) {
            super(str);
            this.iStandardOffset = i;
            this.iStartRecurrence = recurrence;
            this.iEndRecurrence = recurrence2;
        }

        public String getNameKey(long j) {
            return findMatchingRecurrence(j).getNameKey();
        }

        public int getOffset(long j) {
            return this.iStandardOffset + findMatchingRecurrence(j).getSaveMillis();
        }

        public boolean isFixed() {
            return false;
        }

        public long nextTransition(long j) {
            long next;
            long j2;
            int i = this.iStandardOffset;
            Recurrence recurrence = this.iStartRecurrence;
            Recurrence recurrence2 = this.iEndRecurrence;
            try {
                next = recurrence.next(j, i, recurrence2.getSaveMillis());
                if (j > 0 && next < 0) {
                    next = j;
                }
                j2 = next;
            } catch (IllegalArgumentException e) {
                j2 = j;
            } catch (ArithmeticException e2) {
                j2 = j;
            }
            try {
                next = recurrence2.next(j, i, recurrence.getSaveMillis());
                if (j <= 0 || next >= 0) {
                    j = next;
                }
                next = j;
            } catch (IllegalArgumentException e3) {
                next = j;
            } catch (ArithmeticException e4) {
                next = j;
            }
            if (j2 > next) {
                return next;
            }
            return j2;
        }

        public long previousTransition(long j) {
            long previous;
            long j2;
            long j3 = j + 1;
            int i = this.iStandardOffset;
            Recurrence recurrence = this.iStartRecurrence;
            Recurrence recurrence2 = this.iEndRecurrence;
            try {
                previous = recurrence.previous(j3, i, recurrence2.getSaveMillis());
                if (j3 < 0 && previous > 0) {
                    previous = j3;
                }
                j2 = previous;
            } catch (IllegalArgumentException e) {
                j2 = j3;
            } catch (ArithmeticException e2) {
                j2 = j3;
            }
            try {
                previous = recurrence2.previous(j3, i, recurrence.getSaveMillis());
                if (j3 >= 0 || previous <= 0) {
                    j3 = previous;
                }
                previous = j3;
            } catch (IllegalArgumentException e3) {
                previous = j3;
            } catch (ArithmeticException e4) {
                previous = j3;
            }
            if (j2 > previous) {
                previous = j2;
            }
            return previous - 1;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DSTZone)) {
                return false;
            }
            DSTZone dSTZone = (DSTZone) obj;
            if (getID().equals(dSTZone.getID()) && this.iStandardOffset == dSTZone.iStandardOffset && this.iStartRecurrence.equals(dSTZone.iStartRecurrence) && this.iEndRecurrence.equals(dSTZone.iEndRecurrence)) {
                return true;
            }
            return false;
        }

        private Recurrence findMatchingRecurrence(long j) {
            long next;
            int i = this.iStandardOffset;
            Recurrence recurrence = this.iStartRecurrence;
            Recurrence recurrence2 = this.iEndRecurrence;
            try {
                next = recurrence.next(j, i, recurrence2.getSaveMillis());
            } catch (IllegalArgumentException e) {
                next = j;
            } catch (ArithmeticException e2) {
                next = j;
            }
            try {
                j = recurrence2.next(j, i, recurrence.getSaveMillis());
            } catch (IllegalArgumentException e3) {
            } catch (ArithmeticException e4) {
            }
            if (next > j) {
                return recurrence;
            }
            return recurrence2;
        }
    }

    private static final class OfYear {
        final boolean iAdvance;
        final int iDayOfMonth;
        final int iDayOfWeek;
        final int iMillisOfDay;
        final char iMode;
        final int iMonthOfYear;

        static OfYear readFrom(DataInput dataInput) throws IOException {
            return new OfYear((char) dataInput.readUnsignedByte(), dataInput.readUnsignedByte(), dataInput.readByte(), dataInput.readUnsignedByte(), dataInput.readBoolean(), (int) DateTimeZoneBuilder.readMillis(dataInput));
        }

        OfYear(char c, int i, int i2, int i3, boolean z, int i4) {
            if (c == 'u' || c == 'w' || c == 's') {
                this.iMode = c;
                this.iMonthOfYear = i;
                this.iDayOfMonth = i2;
                this.iDayOfWeek = i3;
                this.iAdvance = z;
                this.iMillisOfDay = i4;
                return;
            }
            throw new IllegalArgumentException("Unknown mode: " + c);
        }

        public long next(long j, int i, int i2) {
            if (this.iMode == 'w') {
                i += i2;
            } else if (this.iMode != 's') {
                i = 0;
            }
            long j2 = ((long) i) + j;
            Chronology instanceUTC = ISOChronology.getInstanceUTC();
            long dayOfMonthNext = setDayOfMonthNext(instanceUTC, instanceUTC.millisOfDay().add(instanceUTC.millisOfDay().set(instanceUTC.monthOfYear().set(j2, this.iMonthOfYear), 0), this.iMillisOfDay));
            if (this.iDayOfWeek != 0) {
                dayOfMonthNext = setDayOfWeek(instanceUTC, dayOfMonthNext);
                if (dayOfMonthNext <= j2) {
                    dayOfMonthNext = setDayOfWeek(instanceUTC, setDayOfMonthNext(instanceUTC, instanceUTC.monthOfYear().set(instanceUTC.year().add(dayOfMonthNext, 1), this.iMonthOfYear)));
                }
            } else if (dayOfMonthNext <= j2) {
                dayOfMonthNext = setDayOfMonthNext(instanceUTC, instanceUTC.year().add(dayOfMonthNext, 1));
            }
            return dayOfMonthNext - ((long) i);
        }

        public long previous(long j, int i, int i2) {
            if (this.iMode == 'w') {
                i += i2;
            } else if (this.iMode != 's') {
                i = 0;
            }
            long j2 = ((long) i) + j;
            Chronology instanceUTC = ISOChronology.getInstanceUTC();
            long dayOfMonthPrevious = setDayOfMonthPrevious(instanceUTC, instanceUTC.millisOfDay().add(instanceUTC.millisOfDay().set(instanceUTC.monthOfYear().set(j2, this.iMonthOfYear), 0), this.iMillisOfDay));
            if (this.iDayOfWeek != 0) {
                dayOfMonthPrevious = setDayOfWeek(instanceUTC, dayOfMonthPrevious);
                if (dayOfMonthPrevious >= j2) {
                    dayOfMonthPrevious = setDayOfWeek(instanceUTC, setDayOfMonthPrevious(instanceUTC, instanceUTC.monthOfYear().set(instanceUTC.year().add(dayOfMonthPrevious, -1), this.iMonthOfYear)));
                }
            } else if (dayOfMonthPrevious >= j2) {
                dayOfMonthPrevious = setDayOfMonthPrevious(instanceUTC, instanceUTC.year().add(dayOfMonthPrevious, -1));
            }
            return dayOfMonthPrevious - ((long) i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OfYear)) {
                return false;
            }
            OfYear ofYear = (OfYear) obj;
            if (this.iMode == ofYear.iMode && this.iMonthOfYear == ofYear.iMonthOfYear && this.iDayOfMonth == ofYear.iDayOfMonth && this.iDayOfWeek == ofYear.iDayOfWeek && this.iAdvance == ofYear.iAdvance && this.iMillisOfDay == ofYear.iMillisOfDay) {
                return true;
            }
            return false;
        }

        private long setDayOfMonthNext(Chronology chronology, long j) {
            try {
                return setDayOfMonth(chronology, j);
            } catch (IllegalArgumentException e) {
                if (this.iMonthOfYear == 2 && this.iDayOfMonth == 29) {
                    while (!chronology.year().isLeap(j)) {
                        j = chronology.year().add(j, 1);
                    }
                    return setDayOfMonth(chronology, j);
                }
                throw e;
            }
        }

        private long setDayOfMonthPrevious(Chronology chronology, long j) {
            try {
                return setDayOfMonth(chronology, j);
            } catch (IllegalArgumentException e) {
                if (this.iMonthOfYear == 2 && this.iDayOfMonth == 29) {
                    while (!chronology.year().isLeap(j)) {
                        j = chronology.year().add(j, -1);
                    }
                    return setDayOfMonth(chronology, j);
                }
                throw e;
            }
        }

        private long setDayOfMonth(Chronology chronology, long j) {
            if (this.iDayOfMonth >= 0) {
                return chronology.dayOfMonth().set(j, this.iDayOfMonth);
            }
            return chronology.dayOfMonth().add(chronology.monthOfYear().add(chronology.dayOfMonth().set(j, 1), 1), this.iDayOfMonth);
        }

        private long setDayOfWeek(Chronology chronology, long j) {
            int i = this.iDayOfWeek - chronology.dayOfWeek().get(j);
            if (i == 0) {
                return j;
            }
            if (this.iAdvance) {
                if (i < 0) {
                    i += 7;
                }
            } else if (i > 0) {
                i -= 7;
            }
            return chronology.dayOfWeek().add(j, i);
        }
    }

    private static final class PrecalculatedZone extends DateTimeZone {
        private final String[] iNameKeys;
        private final int[] iStandardOffsets;
        private final DSTZone iTailZone;
        private final long[] iTransitions;
        private final int[] iWallOffsets;

        static PrecalculatedZone readFrom(DataInput dataInput, String str) throws IOException {
            int i;
            int readUnsignedShort = dataInput.readUnsignedShort();
            String[] strArr = new String[readUnsignedShort];
            for (i = 0; i < readUnsignedShort; i++) {
                strArr[i] = dataInput.readUTF();
            }
            int readInt = dataInput.readInt();
            long[] jArr = new long[readInt];
            int[] iArr = new int[readInt];
            int[] iArr2 = new int[readInt];
            String[] strArr2 = new String[readInt];
            for (i = 0; i < readInt; i++) {
                int readUnsignedByte;
                jArr[i] = DateTimeZoneBuilder.readMillis(dataInput);
                iArr[i] = (int) DateTimeZoneBuilder.readMillis(dataInput);
                iArr2[i] = (int) DateTimeZoneBuilder.readMillis(dataInput);
                if (readUnsignedShort < 256) {
                    try {
                        readUnsignedByte = dataInput.readUnsignedByte();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new IOException("Invalid encoding");
                    }
                }
                readUnsignedByte = dataInput.readUnsignedShort();
                strArr2[i] = strArr[readUnsignedByte];
            }
            DSTZone dSTZone = null;
            if (dataInput.readBoolean()) {
                dSTZone = DSTZone.readFrom(dataInput, str);
            }
            return new PrecalculatedZone(str, jArr, iArr, iArr2, strArr2, dSTZone);
        }

        private PrecalculatedZone(String str, long[] jArr, int[] iArr, int[] iArr2, String[] strArr, DSTZone dSTZone) {
            super(str);
            this.iTransitions = jArr;
            this.iWallOffsets = iArr;
            this.iStandardOffsets = iArr2;
            this.iNameKeys = strArr;
            this.iTailZone = dSTZone;
        }

        public String getNameKey(long j) {
            long[] jArr = this.iTransitions;
            int binarySearch = Arrays.binarySearch(jArr, j);
            if (binarySearch >= 0) {
                return this.iNameKeys[binarySearch];
            }
            binarySearch ^= -1;
            if (binarySearch < jArr.length) {
                if (binarySearch > 0) {
                    return this.iNameKeys[binarySearch - 1];
                }
                return "UTC";
            } else if (this.iTailZone == null) {
                return this.iNameKeys[binarySearch - 1];
            } else {
                return this.iTailZone.getNameKey(j);
            }
        }

        public int getOffset(long j) {
            long[] jArr = this.iTransitions;
            int binarySearch = Arrays.binarySearch(jArr, j);
            if (binarySearch >= 0) {
                return this.iWallOffsets[binarySearch];
            }
            binarySearch ^= -1;
            if (binarySearch < jArr.length) {
                if (binarySearch > 0) {
                    return this.iWallOffsets[binarySearch - 1];
                }
                return 0;
            } else if (this.iTailZone == null) {
                return this.iWallOffsets[binarySearch - 1];
            } else {
                return this.iTailZone.getOffset(j);
            }
        }

        public boolean isFixed() {
            return false;
        }

        public long nextTransition(long j) {
            long[] jArr = this.iTransitions;
            int binarySearch = Arrays.binarySearch(jArr, j);
            binarySearch = binarySearch >= 0 ? binarySearch + 1 : binarySearch ^ -1;
            if (binarySearch < jArr.length) {
                return jArr[binarySearch];
            }
            if (this.iTailZone == null) {
                return j;
            }
            long j2 = jArr[jArr.length - 1];
            if (j < j2) {
                j = j2;
            }
            return this.iTailZone.nextTransition(j);
        }

        public long previousTransition(long j) {
            long[] jArr = this.iTransitions;
            int binarySearch = Arrays.binarySearch(jArr, j);
            if (binarySearch < 0) {
                int i = binarySearch ^ -1;
                long previousTransition;
                if (i >= jArr.length) {
                    if (this.iTailZone != null) {
                        previousTransition = this.iTailZone.previousTransition(j);
                        if (previousTransition < j) {
                            return previousTransition;
                        }
                    }
                    previousTransition = jArr[i - 1];
                    if (previousTransition > Long.MIN_VALUE) {
                        return previousTransition - 1;
                    }
                    return j;
                } else if (i <= 0) {
                    return j;
                } else {
                    previousTransition = jArr[i - 1];
                    if (previousTransition > Long.MIN_VALUE) {
                        return previousTransition - 1;
                    }
                    return j;
                }
            } else if (j > Long.MIN_VALUE) {
                return j - 1;
            } else {
                return j;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PrecalculatedZone)) {
                return false;
            }
            PrecalculatedZone precalculatedZone = (PrecalculatedZone) obj;
            if (getID().equals(precalculatedZone.getID()) && Arrays.equals(this.iTransitions, precalculatedZone.iTransitions) && Arrays.equals(this.iNameKeys, precalculatedZone.iNameKeys) && Arrays.equals(this.iWallOffsets, precalculatedZone.iWallOffsets) && Arrays.equals(this.iStandardOffsets, precalculatedZone.iStandardOffsets)) {
                if (this.iTailZone == null) {
                    if (precalculatedZone.iTailZone == null) {
                        return true;
                    }
                } else if (this.iTailZone.equals(precalculatedZone.iTailZone)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static final class Recurrence {
        final String iNameKey;
        final OfYear iOfYear;
        final int iSaveMillis;

        static Recurrence readFrom(DataInput dataInput) throws IOException {
            return new Recurrence(OfYear.readFrom(dataInput), dataInput.readUTF(), (int) DateTimeZoneBuilder.readMillis(dataInput));
        }

        Recurrence(OfYear ofYear, String str, int i) {
            this.iOfYear = ofYear;
            this.iNameKey = str;
            this.iSaveMillis = i;
        }

        public long next(long j, int i, int i2) {
            return this.iOfYear.next(j, i, i2);
        }

        public long previous(long j, int i, int i2) {
            return this.iOfYear.previous(j, i, i2);
        }

        public String getNameKey() {
            return this.iNameKey;
        }

        public int getSaveMillis() {
            return this.iSaveMillis;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Recurrence)) {
                return false;
            }
            Recurrence recurrence = (Recurrence) obj;
            if (this.iSaveMillis == recurrence.iSaveMillis && this.iNameKey.equals(recurrence.iNameKey) && this.iOfYear.equals(recurrence.iOfYear)) {
                return true;
            }
            return false;
        }
    }

    public static DateTimeZone readFrom(InputStream inputStream, String str) throws IOException {
        if (inputStream instanceof DataInput) {
            return readFrom((DataInput) inputStream, str);
        }
        return readFrom(new DataInputStream(inputStream), str);
    }

    public static DateTimeZone readFrom(DataInput dataInput, String str) throws IOException {
        switch (dataInput.readUnsignedByte()) {
            case 67:
                return CachedDateTimeZone.forZone(PrecalculatedZone.readFrom(dataInput, str));
            case 70:
                DateTimeZone fixedDateTimeZone = new FixedDateTimeZone(str, dataInput.readUTF(), (int) readMillis(dataInput), (int) readMillis(dataInput));
                if (fixedDateTimeZone.equals(DateTimeZone.UTC)) {
                    return DateTimeZone.UTC;
                }
                return fixedDateTimeZone;
            case 80:
                return PrecalculatedZone.readFrom(dataInput, str);
            default:
                throw new IOException("Invalid encoding");
        }
    }

    static long readMillis(DataInput dataInput) throws IOException {
        int readUnsignedByte = dataInput.readUnsignedByte();
        switch (readUnsignedByte >> 6) {
            case 1:
                return ((long) (((((readUnsignedByte << 26) >> 2) | (dataInput.readUnsignedByte() << 16)) | (dataInput.readUnsignedByte() << 8)) | dataInput.readUnsignedByte())) * 60000;
            case 2:
                return ((((((((long) readUnsignedByte) << 58) >> 26) | ((long) (dataInput.readUnsignedByte() << 24))) | ((long) (dataInput.readUnsignedByte() << 16))) | ((long) (dataInput.readUnsignedByte() << 8))) | ((long) dataInput.readUnsignedByte())) * 1000;
            case 3:
                return dataInput.readLong();
            default:
                return ((long) ((readUnsignedByte << 26) >> 26)) * 1800000;
        }
    }
}
