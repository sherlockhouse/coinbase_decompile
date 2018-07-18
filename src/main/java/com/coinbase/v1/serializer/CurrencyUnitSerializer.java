package com.coinbase.v1.serializer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import org.joda.money.CurrencyUnit;

public class CurrencyUnitSerializer extends StdSerializer<CurrencyUnit> {
    public CurrencyUnitSerializer() {
        super(CurrencyUnit.class);
    }

    public void serialize(CurrencyUnit value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        jgen.writeString(value.getCurrencyCode());
    }
}
