package com.fasterxml.jackson.core;

public interface SerializableString {
    char[] asQuotedChars();

    String getValue();
}
