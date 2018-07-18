package com.coinbase.v1.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import org.joda.money.CurrencyUnit;
import org.joda.money.IllegalCurrencyException;

public class CurrencyUnitDeserializer extends StdDeserializer<CurrencyUnit> {
    public CurrencyUnitDeserializer() {
        super(CurrencyUnit.class);
    }

    public CurrencyUnit deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        CurrencyUnit result = null;
        try {
            result = CurrencyUnit.getInstance(jp.getText());
        } catch (IllegalCurrencyException e) {
        }
        return result;
    }
}
