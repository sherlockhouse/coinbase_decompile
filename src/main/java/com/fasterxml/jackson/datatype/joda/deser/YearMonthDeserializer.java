package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import org.joda.time.YearMonth;

public class YearMonthDeserializer extends JodaDeserializerBase<YearMonth> {
    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser x0, DeserializationContext x1, TypeDeserializer x2) throws IOException, JsonProcessingException {
        return super.deserializeWithType(x0, x1, x2);
    }

    public YearMonthDeserializer() {
        super(YearMonth.class);
    }

    public YearMonth deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
            String str = jp.getText().trim();
            if (str.isEmpty()) {
                return null;
            }
            return YearMonth.parse(str);
        }
        throw ctxt.wrongTokenException(jp, JsonToken.VALUE_STRING, "expected JSON String");
    }
}
