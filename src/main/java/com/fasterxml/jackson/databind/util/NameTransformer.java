package com.fasterxml.jackson.databind.util;

public abstract class NameTransformer {
    public static final NameTransformer NOP = new NameTransformer() {
        public String transform(String name) {
            return name;
        }
    };

    public static class Chained extends NameTransformer {
        protected final NameTransformer _t1;
        protected final NameTransformer _t2;

        public Chained(NameTransformer t1, NameTransformer t2) {
            this._t1 = t1;
            this._t2 = t2;
        }

        public String transform(String name) {
            return this._t1.transform(this._t2.transform(name));
        }

        public String toString() {
            return "[ChainedTransformer(" + this._t1 + ", " + this._t2 + ")]";
        }
    }

    public abstract String transform(String str);

    protected NameTransformer() {
    }

    public static NameTransformer simpleTransformer(final String prefix, final String suffix) {
        boolean hasPrefix;
        boolean hasSuffix = true;
        if (prefix == null || prefix.length() <= 0) {
            hasPrefix = false;
        } else {
            hasPrefix = true;
        }
        if (suffix == null || suffix.length() <= 0) {
            hasSuffix = false;
        }
        if (!hasPrefix) {
            return hasSuffix ? new NameTransformer() {
                public String transform(String name) {
                    return name + suffix;
                }

                public String toString() {
                    return "[SuffixTransformer('" + suffix + "')]";
                }
            } : NOP;
        } else {
            if (hasSuffix) {
                return new NameTransformer() {
                    public String transform(String name) {
                        return prefix + name + suffix;
                    }

                    public String toString() {
                        return "[PreAndSuffixTransformer('" + prefix + "','" + suffix + "')]";
                    }
                };
            }
            return new NameTransformer() {
                public String transform(String name) {
                    return prefix + name;
                }

                public String toString() {
                    return "[PrefixTransformer('" + prefix + "')]";
                }
            };
        }
    }

    public static NameTransformer chainedTransformer(NameTransformer t1, NameTransformer t2) {
        return new Chained(t1, t2);
    }
}
