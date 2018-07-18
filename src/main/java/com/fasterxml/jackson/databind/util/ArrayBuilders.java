package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public final class ArrayBuilders {
    private BooleanBuilder _booleanBuilder = null;
    private ByteBuilder _byteBuilder = null;
    private DoubleBuilder _doubleBuilder = null;
    private FloatBuilder _floatBuilder = null;
    private IntBuilder _intBuilder = null;
    private LongBuilder _longBuilder = null;
    private ShortBuilder _shortBuilder = null;

    public static final class BooleanBuilder extends PrimitiveArrayBuilder<boolean[]> {
        public final boolean[] _constructArray(int len) {
            return new boolean[len];
        }
    }

    public static final class ByteBuilder extends PrimitiveArrayBuilder<byte[]> {
        public final byte[] _constructArray(int len) {
            return new byte[len];
        }
    }

    public static final class DoubleBuilder extends PrimitiveArrayBuilder<double[]> {
        public final double[] _constructArray(int len) {
            return new double[len];
        }
    }

    public static final class FloatBuilder extends PrimitiveArrayBuilder<float[]> {
        public final float[] _constructArray(int len) {
            return new float[len];
        }
    }

    public static final class IntBuilder extends PrimitiveArrayBuilder<int[]> {
        public final int[] _constructArray(int len) {
            return new int[len];
        }
    }

    public static final class LongBuilder extends PrimitiveArrayBuilder<long[]> {
        public final long[] _constructArray(int len) {
            return new long[len];
        }
    }

    public static final class ShortBuilder extends PrimitiveArrayBuilder<short[]> {
        public final short[] _constructArray(int len) {
            return new short[len];
        }
    }

    public BooleanBuilder getBooleanBuilder() {
        if (this._booleanBuilder == null) {
            this._booleanBuilder = new BooleanBuilder();
        }
        return this._booleanBuilder;
    }

    public ByteBuilder getByteBuilder() {
        if (this._byteBuilder == null) {
            this._byteBuilder = new ByteBuilder();
        }
        return this._byteBuilder;
    }

    public ShortBuilder getShortBuilder() {
        if (this._shortBuilder == null) {
            this._shortBuilder = new ShortBuilder();
        }
        return this._shortBuilder;
    }

    public IntBuilder getIntBuilder() {
        if (this._intBuilder == null) {
            this._intBuilder = new IntBuilder();
        }
        return this._intBuilder;
    }

    public LongBuilder getLongBuilder() {
        if (this._longBuilder == null) {
            this._longBuilder = new LongBuilder();
        }
        return this._longBuilder;
    }

    public FloatBuilder getFloatBuilder() {
        if (this._floatBuilder == null) {
            this._floatBuilder = new FloatBuilder();
        }
        return this._floatBuilder;
    }

    public DoubleBuilder getDoubleBuilder() {
        if (this._doubleBuilder == null) {
            this._doubleBuilder = new DoubleBuilder();
        }
        return this._doubleBuilder;
    }

    public static Object getArrayComparator(final Object defaultValue) {
        final int length = Array.getLength(defaultValue);
        final Class<?> defaultValueType = defaultValue.getClass();
        return new Object() {
            public boolean equals(Object other) {
                if (other == this) {
                    return true;
                }
                if (other == null || other.getClass() != defaultValueType) {
                    return false;
                }
                if (Array.getLength(other) != length) {
                    return false;
                }
                for (int i = 0; i < length; i++) {
                    Object value1 = Array.get(defaultValue, i);
                    Object value2 = Array.get(other, i);
                    if (value1 != value2 && value1 != null && !value1.equals(value2)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    public static <T> HashSet<T> arrayToSet(T[] elements) {
        HashSet<T> result = new HashSet();
        if (elements != null) {
            for (T elem : elements) {
                result.add(elem);
            }
        }
        return result;
    }

    public static <T> HashSet<T> setAndArray(Set<T> set, T[] elements) {
        HashSet<T> result = new HashSet();
        if (set != null) {
            result.addAll(set);
        }
        if (elements != null) {
            for (T value : elements) {
                result.add(value);
            }
        }
        return result;
    }

    public static <T> T[] insertInListNoDup(T[] array, T element) {
        Object[] result;
        int len = array.length;
        int ix = 0;
        while (ix < len) {
            if (array[ix] != element) {
                ix++;
            } else if (ix == 0) {
                return array;
            } else {
                result = (Object[]) ((Object[]) Array.newInstance(array.getClass().getComponentType(), len));
                System.arraycopy(array, 0, result, 1, ix);
                result[0] = element;
                ix++;
                int left = len - ix;
                if (left <= 0) {
                    return result;
                }
                System.arraycopy(array, ix, result, ix, left);
                return result;
            }
        }
        result = (Object[]) Array.newInstance(array.getClass().getComponentType(), len + 1);
        if (len > 0) {
            System.arraycopy(array, 0, result, 1, len);
        }
        result[0] = element;
        return result;
    }
}
