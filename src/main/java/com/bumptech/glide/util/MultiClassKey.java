package com.bumptech.glide.util;

public class MultiClassKey {
    private Class<?> first;
    private Class<?> second;
    private Class<?> third;

    public MultiClassKey(Class<?> first, Class<?> second) {
        set(first, second);
    }

    public MultiClassKey(Class<?> first, Class<?> second, Class<?> third) {
        set(first, second, third);
    }

    public void set(Class<?> first, Class<?> second) {
        set(first, second, null);
    }

    public void set(Class<?> first, Class<?> second, Class<?> third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public String toString() {
        return "MultiClassKey{first=" + this.first + ", second=" + this.second + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MultiClassKey that = (MultiClassKey) o;
        if (!this.first.equals(that.first)) {
            return false;
        }
        if (!this.second.equals(that.second)) {
            return false;
        }
        if (Util.bothNullOrEqual(this.third, that.third)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.first.hashCode() * 31) + this.second.hashCode()) * 31) + (this.third != null ? this.third.hashCode() : 0);
    }
}
