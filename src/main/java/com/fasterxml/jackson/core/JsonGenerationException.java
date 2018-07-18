package com.fasterxml.jackson.core;

public class JsonGenerationException extends JsonProcessingException {
    public JsonGenerationException(String msg) {
        super(msg, (JsonLocation) null);
    }
}
