package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityRecord;

public class AccessibilityRecordCompat {
    private static final AccessibilityRecordCompatBaseImpl IMPL;
    private final AccessibilityRecord mRecord;

    static class AccessibilityRecordCompatBaseImpl {
        AccessibilityRecordCompatBaseImpl() {
        }

        public void setMaxScrollX(AccessibilityRecord record, int maxScrollX) {
        }

        public void setMaxScrollY(AccessibilityRecord record, int maxScrollY) {
        }
    }

    static class AccessibilityRecordCompatApi15Impl extends AccessibilityRecordCompatBaseImpl {
        AccessibilityRecordCompatApi15Impl() {
        }

        public void setMaxScrollX(AccessibilityRecord record, int maxScrollX) {
            record.setMaxScrollX(maxScrollX);
        }

        public void setMaxScrollY(AccessibilityRecord record, int maxScrollY) {
            record.setMaxScrollY(maxScrollY);
        }
    }

    static class AccessibilityRecordCompatApi16Impl extends AccessibilityRecordCompatApi15Impl {
        AccessibilityRecordCompatApi16Impl() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            IMPL = new AccessibilityRecordCompatApi16Impl();
        } else if (VERSION.SDK_INT >= 15) {
            IMPL = new AccessibilityRecordCompatApi15Impl();
        } else {
            IMPL = new AccessibilityRecordCompatBaseImpl();
        }
    }

    public static void setMaxScrollX(AccessibilityRecord record, int maxScrollX) {
        IMPL.setMaxScrollX(record, maxScrollX);
    }

    public static void setMaxScrollY(AccessibilityRecord record, int maxScrollY) {
        IMPL.setMaxScrollY(record, maxScrollY);
    }

    @Deprecated
    public int hashCode() {
        return this.mRecord == null ? 0 : this.mRecord.hashCode();
    }

    @Deprecated
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityRecordCompat other = (AccessibilityRecordCompat) obj;
        if (this.mRecord == null) {
            if (other.mRecord != null) {
                return false;
            }
            return true;
        } else if (this.mRecord.equals(other.mRecord)) {
            return true;
        } else {
            return false;
        }
    }
}
