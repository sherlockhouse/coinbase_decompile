package com.crashlytics.android.answers;

public abstract class PredefinedEvent<T extends PredefinedEvent> extends AnswersEvent<T> {
    final AnswersAttributes predefinedAttributes = new AnswersAttributes(this.validator);

    abstract String getPredefinedType();

    public String toString() {
        return "{type:\"" + getPredefinedType() + '\"' + ", predefinedAttributes:" + this.predefinedAttributes + ", customAttributes:" + this.customAttributes + "}";
    }
}
