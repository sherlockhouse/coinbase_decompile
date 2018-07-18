package com.coinbase.v1.deserializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.math.BigDecimal;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class MoneyDeserializer extends StdDeserializer<Money> {
    public MoneyDeserializer() {
        super(Money.class);
    }

    public Money deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = (JsonNode) jsonParser.getCodec().readTree(jsonParser);
        String currency = null;
        String amount = null;
        Long cents = null;
        if (node.has("currency")) {
            currency = node.get("currency").textValue();
        } else if (node.has("currency_iso")) {
            currency = node.get("currency_iso").textValue();
        }
        if (node.has("amount")) {
            amount = node.get("amount").textValue();
        } else if (node.has("cents")) {
            cents = Long.valueOf(node.get("cents").longValue());
        }
        if (currency == null || (amount == null && cents == null)) {
            throw new JsonParseException("Wrong format for Money", jsonParser.getCurrentLocation());
        } else if (amount == null) {
            return Money.ofMinor(CurrencyUnit.of(currency), cents.longValue());
        } else {
            try {
                return Money.of(CurrencyUnit.of(currency), Double.valueOf(amount).doubleValue());
            } catch (ArithmeticException e) {
                return Money.of(CurrencyUnit.of(currency), BigDecimal.valueOf(Long.valueOf(Double.valueOf(amount).longValue()).longValue()));
            }
        }
    }
}
