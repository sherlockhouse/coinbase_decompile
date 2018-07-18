package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.ReadablePeriod;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;

public class ReadablePeriodDeserializer extends JodaDeserializerBase<ReadablePeriod> {
    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser x0, DeserializationContext x1, TypeDeserializer x2) throws IOException, JsonProcessingException {
        return super.deserializeWithType(x0, x1, x2);
    }

    public ReadablePeriodDeserializer() {
        super(ReadablePeriod.class);
    }

    public ReadablePeriod deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode treeNode = (JsonNode) jsonParser.readValueAsTree();
        String periodType = treeNode.path("fieldType").path("name").asText();
        String periodName = treeNode.path("periodType").path("name").asText();
        int periodValue = treeNode.path(periodType).asInt();
        if (periodName.equals("Seconds")) {
            return Seconds.seconds(periodValue);
        }
        if (periodName.equals("Minutes")) {
            return Minutes.minutes(periodValue);
        }
        if (periodName.equals("Hours")) {
            return Hours.hours(periodValue);
        }
        if (periodName.equals("Days")) {
            return Days.days(periodValue);
        }
        if (periodName.equals("Weeks")) {
            return Weeks.weeks(periodValue);
        }
        if (periodName.equals("Months")) {
            return Months.months(periodValue);
        }
        if (periodName.equals("Years")) {
            return Years.years(periodValue);
        }
        throw ctxt.mappingException("Don't know how to deserialize ReadablePeriod using periodName '" + periodName + "'");
    }
}
