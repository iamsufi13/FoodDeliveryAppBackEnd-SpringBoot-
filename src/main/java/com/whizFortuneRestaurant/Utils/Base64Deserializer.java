package com.whizFortuneRestaurant.Utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Base64;

public class Base64Deserializer extends JsonDeserializer<byte[]> {
    @Override
    public byte[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String base64String = p.getValueAsString();
        if (base64String == null || base64String.isEmpty()) {
            return null;
        }
        return Base64.getDecoder().decode(base64String);
    }
}
