package org.joda.time.format;

import java.util.Arrays;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.IllegalInstantException;

public class DateTimeParserBucket {
    private final Chronology iChrono;
    private int iDefaultYear;
    private Locale iLocale;
    private final long iMillis;
    private Integer iOffset;
    private Integer iPivotYear;
    private SavedField[] iSavedFields = new SavedField[8];
    private int iSavedFieldsCount;
    private boolean iSavedFieldsShared;
    private Object iSavedState;
    private DateTimeZone iZone;

    static class SavedField implements Comparable<SavedField> {
        final DateTimeField iField;
        final Locale iLocale;
        final String iText;
        final int iValue;

        SavedField(DateTimeField dateTimeField, int i) {
            this.iField = dateTimeField;
            this.iValue = i;
            this.iText = null;
            this.iLocale = null;
        }

        SavedField(DateTimeField dateTimeField, String str, Locale locale) {
            this.iField = dateTimeField;
            this.iValue = 0;
            this.iText = str;
            this.iLocale = locale;
        }

        long set(long j, boolean z) {
            long j2;
            if (this.iText == null) {
                j2 = this.iField.set(j, this.iValue);
            } else {
                j2 = this.iField.set(j, this.iText, this.iLocale);
            }
            if (z) {
                return this.iField.roundFloor(j2);
            }
            return j2;
        }

        public int compareTo(SavedField savedField) {
            DateTimeField dateTimeField = savedField.iField;
            int compareReverse = DateTimeParserBucket.compareReverse(this.iField.getRangeDurationField(), dateTimeField.getRangeDurationField());
            return compareReverse != 0 ? compareReverse : DateTimeParserBucket.compareReverse(this.iField.getDurationField(), dateTimeField.getDurationField());
        }
    }

    class SavedState {
        final Integer iOffset;
        final SavedField[] iSavedFields;
        final int iSavedFieldsCount;
        final DateTimeZone iZone;

        SavedState() {
            this.iZone = DateTimeParserBucket.this.iZone;
            this.iOffset = DateTimeParserBucket.this.iOffset;
            this.iSavedFields = DateTimeParserBucket.this.iSavedFields;
            this.iSavedFieldsCount = DateTimeParserBucket.this.iSavedFieldsCount;
        }

        boolean restoreState(DateTimeParserBucket dateTimeParserBucket) {
            if (dateTimeParserBucket != DateTimeParserBucket.this) {
                return false;
            }
            dateTimeParserBucket.iZone = this.iZone;
            dateTimeParserBucket.iOffset = this.iOffset;
            dateTimeParserBucket.iSavedFields = this.iSavedFields;
            if (this.iSavedFieldsCount < dateTimeParserBucket.iSavedFieldsCount) {
                dateTimeParserBucket.iSavedFieldsShared = true;
            }
            dateTimeParserBucket.iSavedFieldsCount = this.iSavedFieldsCount;
            return true;
        }
    }

    public DateTimeParserBucket(long j, Chronology chronology, Locale locale, Integer num, int i) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iMillis = j;
        this.iZone = chronology2.getZone();
        this.iChrono = chronology2.withUTC();
        if (locale == null) {
            locale = Locale.getDefault();
        }
        this.iLocale = locale;
        this.iPivotYear = num;
        this.iDefaultYear = i;
    }

    public Chronology getChronology() {
        return this.iChrono;
    }

    public Locale getLocale() {
        return this.iLocale;
    }

    public DateTimeZone getZone() {
        return this.iZone;
    }

    public void setZone(DateTimeZone dateTimeZone) {
        this.iSavedState = null;
        this.iZone = dateTimeZone;
    }

    public Integer getOffsetInteger() {
        return this.iOffset;
    }

    public void setOffset(Integer num) {
        this.iSavedState = null;
        this.iOffset = num;
    }

    public Integer getPivotYear() {
        return this.iPivotYear;
    }

    public void saveField(DateTimeField dateTimeField, int i) {
        saveField(new SavedField(dateTimeField, i));
    }

    public void saveField(DateTimeFieldType dateTimeFieldType, int i) {
        saveField(new SavedField(dateTimeFieldType.getField(this.iChrono), i));
    }

    public void saveField(DateTimeFieldType dateTimeFieldType, String str, Locale locale) {
        saveField(new SavedField(dateTimeFieldType.getField(this.iChrono), str, locale));
    }

    private void saveField(SavedField savedField) {
        Object obj;
        Object obj2 = this.iSavedFields;
        int i = this.iSavedFieldsCount;
        if (i == obj2.length || this.iSavedFieldsShared) {
            obj = new SavedField[(i == obj2.length ? i * 2 : obj2.length)];
            System.arraycopy(obj2, 0, obj, 0, i);
            this.iSavedFields = obj;
            this.iSavedFieldsShared = false;
        } else {
            obj = obj2;
        }
        this.iSavedState = null;
        obj[i] = savedField;
        this.iSavedFieldsCount = i + 1;
    }

    public Object saveState() {
        if (this.iSavedState == null) {
            this.iSavedState = new SavedState();
        }
        return this.iSavedState;
    }

    public boolean restoreState(Object obj) {
        if (!(obj instanceof SavedState) || !((SavedState) obj).restoreState(this)) {
            return false;
        }
        this.iSavedState = obj;
        return true;
    }

    public long computeMillis(boolean z, String str) {
        SavedField[] savedFieldArr = this.iSavedFields;
        int i = this.iSavedFieldsCount;
        if (this.iSavedFieldsShared) {
            savedFieldArr = (SavedField[]) this.iSavedFields.clone();
            this.iSavedFields = savedFieldArr;
            this.iSavedFieldsShared = false;
        }
        sort(savedFieldArr, i);
        if (i > 0) {
            DurationField field = DurationFieldType.months().getField(this.iChrono);
            DurationField field2 = DurationFieldType.days().getField(this.iChrono);
            DurationField durationField = savedFieldArr[0].iField.getDurationField();
            if (compareReverse(durationField, field) >= 0 && compareReverse(durationField, field2) <= 0) {
                saveField(DateTimeFieldType.year(), this.iDefaultYear);
                return computeMillis(z, str);
            }
        }
        long j = this.iMillis;
        int i2 = 0;
        while (i2 < i) {
            try {
                j = savedFieldArr[i2].set(j, z);
                i2++;
            } catch (IllegalFieldValueException e) {
                if (str != null) {
                    e.prependMessage("Cannot parse \"" + str + '\"');
                }
                throw e;
            }
        }
        if (z) {
            for (int i3 = 0; i3 < i; i3++) {
                boolean z2;
                SavedField savedField = savedFieldArr[i3];
                if (i3 == i - 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                j = savedField.set(j, z2);
            }
        }
        long j2 = j;
        if (this.iOffset != null) {
            return j2 - ((long) this.iOffset.intValue());
        }
        if (this.iZone == null) {
            return j2;
        }
        int offsetFromLocal = this.iZone.getOffsetFromLocal(j2);
        j2 -= (long) offsetFromLocal;
        if (offsetFromLocal == this.iZone.getOffset(j2)) {
            return j2;
        }
        String str2 = "Illegal instant due to time zone offset transition (" + this.iZone + ')';
        if (str != null) {
            str2 = "Cannot parse \"" + str + "\": " + str2;
        }
        throw new IllegalInstantException(str2);
    }

    private static void sort(SavedField[] savedFieldArr, int i) {
        int i2 = 0;
        if (i > 10) {
            Arrays.sort(savedFieldArr, 0, i);
            return;
        }
        while (i2 < i) {
            int i3 = i2;
            while (i3 > 0 && savedFieldArr[i3 - 1].compareTo(savedFieldArr[i3]) > 0) {
                SavedField savedField = savedFieldArr[i3];
                savedFieldArr[i3] = savedFieldArr[i3 - 1];
                savedFieldArr[i3 - 1] = savedField;
                i3--;
            }
            i2++;
        }
    }

    static int compareReverse(DurationField durationField, DurationField durationField2) {
        if (durationField == null || !durationField.isSupported()) {
            if (durationField2 == null || !durationField2.isSupported()) {
                return 0;
            }
            return -1;
        } else if (durationField2 == null || !durationField2.isSupported()) {
            return 1;
        } else {
            return -durationField.compareTo(durationField2);
        }
    }
}
