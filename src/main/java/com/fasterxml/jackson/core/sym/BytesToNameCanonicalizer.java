package com.fasterxml.jackson.core.sym;

import java.util.concurrent.atomic.AtomicReference;

public final class BytesToNameCanonicalizer {
    protected final boolean _failOnDoS;
    protected boolean _intern;
    protected final BytesToNameCanonicalizer _parent = null;
    private final int _seed;
    protected final AtomicReference<TableInfo> _tableInfo;

    private static final class Bucket {
    }

    private static final class TableInfo {
        public final int collCount;
        public final int collEnd;
        public final Bucket[] collList;
        public final int count;
        public final int longestCollisionList;
        public final int[] mainHash;
        public final int mainHashMask;
        public final Name[] mainNames;

        public TableInfo(int count, int mainHashMask, int[] mainHash, Name[] mainNames, Bucket[] collList, int collCount, int collEnd, int longestCollisionList) {
            this.count = count;
            this.mainHashMask = mainHashMask;
            this.mainHash = mainHash;
            this.mainNames = mainNames;
            this.collList = collList;
            this.collCount = collCount;
            this.collEnd = collEnd;
            this.longestCollisionList = longestCollisionList;
        }
    }

    private BytesToNameCanonicalizer(int sz, boolean intern, int seed, boolean failOnDoS) {
        this._seed = seed;
        this._intern = intern;
        this._failOnDoS = failOnDoS;
        if (sz < 16) {
            sz = 16;
        } else if (((sz - 1) & sz) != 0) {
            int curr = 16;
            while (curr < sz) {
                curr += curr;
            }
            sz = curr;
        }
        this._tableInfo = new AtomicReference(initTableInfo(sz));
    }

    private TableInfo initTableInfo(int sz) {
        return new TableInfo(0, sz - 1, new int[sz], new Name[sz], null, 0, 0, 0);
    }

    public static BytesToNameCanonicalizer createRoot() {
        long now = System.currentTimeMillis();
        return createRoot((((int) now) + ((int) (now >>> 32))) | 1);
    }

    protected static BytesToNameCanonicalizer createRoot(int seed) {
        return new BytesToNameCanonicalizer(64, true, seed, true);
    }
}
