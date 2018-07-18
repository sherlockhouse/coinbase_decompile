package com.github.mikephil.charting.data;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Entry implements Parcelable {
    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        public Entry createFromParcel(Parcel source) {
            return new Entry(source);
        }

        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };
    private Object mData = null;
    private float mVal = 0.0f;
    private int mXIndex = 0;

    public Entry(float val, int xIndex) {
        this.mVal = val;
        this.mXIndex = xIndex;
    }

    public int getXIndex() {
        return this.mXIndex;
    }

    public float getVal() {
        return this.mVal;
    }

    public boolean equalTo(Entry e) {
        if (e != null && e.mData == this.mData && e.mXIndex == this.mXIndex && Math.abs(e.mVal - this.mVal) <= 1.0E-5f) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Entry, xIndex: " + this.mXIndex + " val (sum): " + getVal();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.mVal);
        dest.writeInt(this.mXIndex);
        if (this.mData == null) {
            dest.writeInt(0);
        } else if (this.mData instanceof Parcelable) {
            dest.writeInt(1);
            dest.writeParcelable((Parcelable) this.mData, flags);
        } else {
            throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
        }
    }

    protected Entry(Parcel in) {
        this.mVal = in.readFloat();
        this.mXIndex = in.readInt();
        if (in.readInt() == 1) {
            this.mData = in.readParcelable(Object.class.getClassLoader());
        }
    }
}
