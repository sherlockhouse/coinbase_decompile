package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

public abstract class OutputDecorator implements Serializable {
    public abstract Writer decorate(IOContext iOContext, Writer writer) throws IOException;
}
