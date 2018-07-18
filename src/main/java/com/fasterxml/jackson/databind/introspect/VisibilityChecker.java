package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public interface VisibilityChecker<T extends VisibilityChecker<T>> {

    @JsonAutoDetect(creatorVisibility = Visibility.ANY, fieldVisibility = Visibility.PUBLIC_ONLY, getterVisibility = Visibility.PUBLIC_ONLY, isGetterVisibility = Visibility.PUBLIC_ONLY, setterVisibility = Visibility.ANY)
    public static class Std implements VisibilityChecker<Std>, Serializable {
        protected static final Std DEFAULT = new Std((JsonAutoDetect) Std.class.getAnnotation(JsonAutoDetect.class));
        protected final Visibility _creatorMinLevel;
        protected final Visibility _fieldMinLevel;
        protected final Visibility _getterMinLevel;
        protected final Visibility _isGetterMinLevel;
        protected final Visibility _setterMinLevel;

        public static Std defaultInstance() {
            return DEFAULT;
        }

        public Std(JsonAutoDetect ann) {
            this._getterMinLevel = ann.getterVisibility();
            this._isGetterMinLevel = ann.isGetterVisibility();
            this._setterMinLevel = ann.setterVisibility();
            this._creatorMinLevel = ann.creatorVisibility();
            this._fieldMinLevel = ann.fieldVisibility();
        }

        public Std(Visibility getter, Visibility isGetter, Visibility setter, Visibility creator, Visibility field) {
            this._getterMinLevel = getter;
            this._isGetterMinLevel = isGetter;
            this._setterMinLevel = setter;
            this._creatorMinLevel = creator;
            this._fieldMinLevel = field;
        }

        public Std with(JsonAutoDetect ann) {
            Std curr = this;
            if (ann != null) {
                return withGetterVisibility(ann.getterVisibility()).withIsGetterVisibility(ann.isGetterVisibility()).withSetterVisibility(ann.setterVisibility()).withCreatorVisibility(ann.creatorVisibility()).withFieldVisibility(ann.fieldVisibility());
            }
            return curr;
        }

        public Std withGetterVisibility(Visibility v) {
            if (v == Visibility.DEFAULT) {
                v = DEFAULT._getterMinLevel;
            }
            if (this._getterMinLevel == v) {
                return this;
            }
            return new Std(v, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        public Std withIsGetterVisibility(Visibility v) {
            if (v == Visibility.DEFAULT) {
                v = DEFAULT._isGetterMinLevel;
            }
            if (this._isGetterMinLevel == v) {
                return this;
            }
            return new Std(this._getterMinLevel, v, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        public Std withSetterVisibility(Visibility v) {
            if (v == Visibility.DEFAULT) {
                v = DEFAULT._setterMinLevel;
            }
            if (this._setterMinLevel == v) {
                return this;
            }
            return new Std(this._getterMinLevel, this._isGetterMinLevel, v, this._creatorMinLevel, this._fieldMinLevel);
        }

        public Std withCreatorVisibility(Visibility v) {
            if (v == Visibility.DEFAULT) {
                v = DEFAULT._creatorMinLevel;
            }
            if (this._creatorMinLevel == v) {
                return this;
            }
            return new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, v, this._fieldMinLevel);
        }

        public Std withFieldVisibility(Visibility v) {
            if (v == Visibility.DEFAULT) {
                v = DEFAULT._fieldMinLevel;
            }
            return this._fieldMinLevel == v ? this : new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, v);
        }

        public boolean isCreatorVisible(Member m) {
            return this._creatorMinLevel.isVisible(m);
        }

        public boolean isCreatorVisible(AnnotatedMember m) {
            return isCreatorVisible(m.getMember());
        }

        public boolean isFieldVisible(Field f) {
            return this._fieldMinLevel.isVisible(f);
        }

        public boolean isFieldVisible(AnnotatedField f) {
            return isFieldVisible(f.getAnnotated());
        }

        public boolean isGetterVisible(Method m) {
            return this._getterMinLevel.isVisible(m);
        }

        public boolean isGetterVisible(AnnotatedMethod m) {
            return isGetterVisible(m.getAnnotated());
        }

        public boolean isIsGetterVisible(Method m) {
            return this._isGetterMinLevel.isVisible(m);
        }

        public boolean isIsGetterVisible(AnnotatedMethod m) {
            return isIsGetterVisible(m.getAnnotated());
        }

        public boolean isSetterVisible(Method m) {
            return this._setterMinLevel.isVisible(m);
        }

        public boolean isSetterVisible(AnnotatedMethod m) {
            return isSetterVisible(m.getAnnotated());
        }

        public String toString() {
            return "[Visibility:" + " getter: " + this._getterMinLevel + ", isGetter: " + this._isGetterMinLevel + ", setter: " + this._setterMinLevel + ", creator: " + this._creatorMinLevel + ", field: " + this._fieldMinLevel + "]";
        }
    }

    boolean isCreatorVisible(AnnotatedMember annotatedMember);

    boolean isFieldVisible(AnnotatedField annotatedField);

    boolean isGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isIsGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isSetterVisible(AnnotatedMethod annotatedMethod);

    T with(JsonAutoDetect jsonAutoDetect);

    T withCreatorVisibility(Visibility visibility);

    T withFieldVisibility(Visibility visibility);

    T withGetterVisibility(Visibility visibility);

    T withIsGetterVisibility(Visibility visibility);

    T withSetterVisibility(Visibility visibility);
}
