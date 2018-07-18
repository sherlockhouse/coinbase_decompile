package com.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import java.util.TimeZone;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFormat {

    public enum Shape {
        ANY,
        SCALAR,
        ARRAY,
        OBJECT,
        NUMBER,
        NUMBER_FLOAT,
        NUMBER_INT,
        STRING,
        BOOLEAN;

        public boolean isNumeric() {
            return this == NUMBER || this == NUMBER_INT || this == NUMBER_FLOAT;
        }
    }

    public static class Value {
        private TimeZone _timezone;
        private final Locale locale;
        private final String pattern;
        private final Shape shape;
        private final String timezoneStr;

        public Value() {
            this("", Shape.ANY, "", "");
        }

        public Value(JsonFormat ann) {
            this(ann.pattern(), ann.shape(), ann.locale(), ann.timezone());
        }

        public Value(String p, Shape sh, String localeStr, String tzStr) {
            Locale locale = (localeStr == null || localeStr.length() == 0 || "##default".equals(localeStr)) ? null : new Locale(localeStr);
            String str = (tzStr == null || tzStr.length() == 0 || "##default".equals(tzStr)) ? null : tzStr;
            this(p, sh, locale, str, null);
        }

        public Value(String p, Shape sh, Locale l, String tzStr, TimeZone tz) {
            this.pattern = p;
            this.shape = sh;
            this.locale = l;
            this._timezone = tz;
            this.timezoneStr = tzStr;
        }

        public String getPattern() {
            return this.pattern;
        }

        public Shape getShape() {
            return this.shape;
        }

        public Locale getLocale() {
            return this.locale;
        }

        public TimeZone getTimeZone() {
            TimeZone tz = this._timezone;
            if (tz == null) {
                if (this.timezoneStr == null) {
                    return null;
                }
                tz = TimeZone.getTimeZone(this.timezoneStr);
                this._timezone = tz;
            }
            return tz;
        }

        public boolean hasPattern() {
            return this.pattern != null && this.pattern.length() > 0;
        }

        public boolean hasLocale() {
            return this.locale != null;
        }
    }

    String locale() default "##default";

    String pattern() default "";

    Shape shape() default Shape.ANY;

    String timezone() default "##default";
}
