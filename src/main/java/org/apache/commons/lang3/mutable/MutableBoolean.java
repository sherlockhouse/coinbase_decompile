package org.apache.commons.lang3.mutable;

import java.io.Serializable;

public class MutableBoolean implements Serializable, Comparable<MutableBoolean> {
    private boolean value;

    public MutableBoolean(boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return Boolean.valueOf(this.value);
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean booleanValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof MutableBoolean) && this.value == ((MutableBoolean) obj).booleanValue()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.value ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode();
    }

    public int compareTo(MutableBoolean other) {
        if (this.value == other.value) {
            return 0;
        }
        return this.value ? 1 : -1;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
