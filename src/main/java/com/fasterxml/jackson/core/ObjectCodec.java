package com.fasterxml.jackson.core;

import java.io.IOException;

public abstract class ObjectCodec extends TreeCodec {
    public abstract <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException, JsonProcessingException;

    public abstract void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonProcessingException;

    protected ObjectCodec() {
    }
}
