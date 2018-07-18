package org.joda.time.convert;

class ConverterSet {
    private final Converter[] iConverters;
    private Entry[] iSelectEntries = new Entry[16];

    static class Entry {
        final Converter iConverter;
        final Class<?> iType;

        Entry(Class<?> cls, Converter converter) {
            this.iType = cls;
            this.iConverter = converter;
        }
    }

    ConverterSet(Converter[] converterArr) {
        this.iConverters = converterArr;
    }

    Converter select(Class<?> cls) throws IllegalStateException {
        Object obj = this.iSelectEntries;
        int length = obj.length;
        int hashCode = cls == null ? 0 : cls.hashCode() & (length - 1);
        while (true) {
            Entry entry = obj[hashCode];
            if (entry == null) {
                break;
            } else if (entry.iType == cls) {
                return entry.iConverter;
            } else {
                int i = hashCode + 1;
                if (i >= length) {
                    hashCode = 0;
                } else {
                    hashCode = i;
                }
            }
        }
        Converter selectSlow = selectSlow(this, cls);
        Entry[] entryArr = (Entry[]) obj.clone();
        entryArr[hashCode] = new Entry(cls, selectSlow);
        for (hashCode = 0; hashCode < length; hashCode++) {
            if (entryArr[hashCode] == null) {
                this.iSelectEntries = entryArr;
                return selectSlow;
            }
        }
        int i2 = length << 1;
        Entry[] entryArr2 = new Entry[i2];
        for (int i3 = 0; i3 < length; i3++) {
            Entry entry2 = entryArr[i3];
            Class cls2 = entry2.iType;
            hashCode = cls2 == null ? 0 : cls2.hashCode() & (i2 - 1);
            while (entryArr2[hashCode] != null) {
                hashCode++;
                if (hashCode >= i2) {
                    hashCode = 0;
                }
            }
            entryArr2[hashCode] = entry2;
        }
        this.iSelectEntries = entryArr2;
        return selectSlow;
    }

    int size() {
        return this.iConverters.length;
    }

    ConverterSet remove(int i, Converter[] converterArr) {
        Converter[] converterArr2 = this.iConverters;
        int length = converterArr2.length;
        if (i >= length) {
            throw new IndexOutOfBoundsException();
        }
        if (converterArr != null) {
            converterArr[0] = converterArr2[i];
        }
        Converter[] converterArr3 = new Converter[(length - 1)];
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4;
            if (i2 != i) {
                i4 = i3 + 1;
                converterArr3[i3] = converterArr2[i2];
            } else {
                i4 = i3;
            }
            i2++;
            i3 = i4;
        }
        return new ConverterSet(converterArr3);
    }

    private static Converter selectSlow(ConverterSet converterSet, Class<?> cls) {
        Converter[] converterArr = converterSet.iConverters;
        int length = converterArr.length;
        int i = length;
        ConverterSet converterSet2 = converterSet;
        while (true) {
            int i2 = i - 1;
            if (i2 < 0) {
                break;
            }
            Converter converter = converterArr[i2];
            Class<?> supportedType = converter.getSupportedType();
            if (supportedType == cls) {
                return converter;
            }
            if (supportedType == null || !(cls == null || supportedType.isAssignableFrom(cls))) {
                converterSet2 = converterSet2.remove(i2, null);
                converterArr = converterSet2.iConverters;
                length = converterArr.length;
            }
            i = i2;
        }
        if (cls == null || length == 0) {
            return null;
        }
        if (length == 1) {
            return converterArr[0];
        }
        Converter[] converterArr2 = converterArr;
        ConverterSet converterSet3 = converterSet2;
        int i3 = length;
        while (true) {
            length--;
            if (length < 0) {
                break;
            }
            Class supportedType2 = converterArr2[length].getSupportedType();
            ConverterSet converterSet4 = converterSet3;
            int i4 = length;
            length = i3;
            while (true) {
                length--;
                if (length < 0) {
                    break;
                } else if (length != i4 && r4[length].getSupportedType().isAssignableFrom(supportedType2)) {
                    converterSet4 = converterSet4.remove(length, null);
                    converterArr2 = converterSet4.iConverters;
                    i3 = converterArr2.length;
                    i4 = i3 - 1;
                }
            }
            length = i4;
            converterSet3 = converterSet4;
        }
        if (i3 == 1) {
            return converterArr2[0];
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to find best converter for type \"");
        stringBuilder.append(cls.getName());
        stringBuilder.append("\" from remaining set: ");
        for (i4 = 0; i4 < i3; i4++) {
            Converter converter2 = converterArr2[i4];
            Class supportedType3 = converter2.getSupportedType();
            stringBuilder.append(converter2.getClass().getName());
            stringBuilder.append('[');
            stringBuilder.append(supportedType3 == null ? null : supportedType3.getName());
            stringBuilder.append("], ");
        }
        throw new IllegalStateException(stringBuilder.toString());
    }
}
