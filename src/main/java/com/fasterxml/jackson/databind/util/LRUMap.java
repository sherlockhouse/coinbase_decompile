package com.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUMap<K, V> extends LinkedHashMap<K, V> implements Serializable {
    protected final transient int _maxEntries;
    protected final transient Lock _readLock;
    protected final transient Lock _writeLock;

    public LRUMap(int initialEntries, int maxEntries) {
        super(initialEntries, 0.8f, true);
        this._maxEntries = maxEntries;
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        this._readLock = rwl.readLock();
        this._writeLock = rwl.writeLock();
    }

    protected boolean removeEldestEntry(Entry<K, V> entry) {
        return size() > this._maxEntries;
    }

    public V get(Object key) {
        this._readLock.lock();
        try {
            V v = super.get(key);
            return v;
        } finally {
            this._readLock.unlock();
        }
    }

    public V put(K key, V value) {
        this._writeLock.lock();
        try {
            V put = super.put(key, value);
            return put;
        } finally {
            this._writeLock.unlock();
        }
    }

    public V remove(Object key) {
        this._writeLock.lock();
        try {
            V remove = super.remove(key);
            return remove;
        } finally {
            this._writeLock.unlock();
        }
    }
}
