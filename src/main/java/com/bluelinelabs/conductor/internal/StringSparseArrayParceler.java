package com.bluelinelabs.conductor.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;

public class StringSparseArrayParceler implements Parcelable {
    public static final Creator<StringSparseArrayParceler> CREATOR = new Creator<StringSparseArrayParceler>() {
        public StringSparseArrayParceler createFromParcel(Parcel in) {
            return new StringSparseArrayParceler(in);
        }

        public StringSparseArrayParceler[] newArray(int size) {
            return new StringSparseArrayParceler[size];
        }
    };
    private final SparseArray<String> stringSparseArray;

    public StringSparseArrayParceler(SparseArray<String> stringSparseArray) {
        this.stringSparseArray = stringSparseArray;
    }

    private StringSparseArrayParceler(Parcel in) {
        this.stringSparseArray = new SparseArray();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            this.stringSparseArray.put(in.readInt(), in.readString());
        }
    }

    public SparseArray<String> getStringSparseArray() {
        return this.stringSparseArray;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        int size = this.stringSparseArray.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            int key = this.stringSparseArray.keyAt(i);
            out.writeInt(key);
            out.writeString((String) this.stringSparseArray.get(key));
        }
    }
}
