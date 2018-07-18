package com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

public abstract class ContextAttributes {

    public static class Impl extends ContextAttributes implements Serializable {
        protected static final Impl EMPTY = new Impl(Collections.emptyMap());
        protected static final Object NULL_SURROGATE = new Object();
        protected transient Map<Object, Object> _nonShared = null;
        protected final Map<Object, Object> _shared;

        protected Impl(Map<Object, Object> shared) {
            this._shared = shared;
        }

        public static ContextAttributes getEmpty() {
            return EMPTY;
        }
    }

    public static ContextAttributes getEmpty() {
        return Impl.getEmpty();
    }
}
