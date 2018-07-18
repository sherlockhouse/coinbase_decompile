package com.fasterxml.jackson.annotation;

public class ObjectIdGenerators {

    private static abstract class Base<T> extends ObjectIdGenerator<T> {
        protected final Class<?> _scope;

        protected Base(Class<?> scope) {
            this._scope = scope;
        }

        public final Class<?> getScope() {
            return this._scope;
        }

        public boolean canUseFor(ObjectIdGenerator<?> gen) {
            return gen.getClass() == getClass() && gen.getScope() == this._scope;
        }
    }

    public static abstract class None extends ObjectIdGenerator<Object> {
    }

    public static abstract class PropertyGenerator extends Base<Object> {
        public /* bridge */ /* synthetic */ boolean canUseFor(ObjectIdGenerator x0) {
            return super.canUseFor(x0);
        }

        protected PropertyGenerator(Class<?> scope) {
            super(scope);
        }
    }
}
