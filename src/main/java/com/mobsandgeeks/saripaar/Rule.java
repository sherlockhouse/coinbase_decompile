package com.mobsandgeeks.saripaar;

public abstract class Rule<T> {
    private String mFailureMessage;

    public abstract boolean isValid(T t);

    public Rule(String failureMessage) {
        this.mFailureMessage = failureMessage;
    }

    public String getFailureMessage() {
        return this.mFailureMessage;
    }
}
