package org.starcoin.bean;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CustomStringDeserializer extends JsonDeserializer {

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (JsonToken.VALUE_STRING.equals(currentToken)) {
            //System.out.println("It is a string value");
            return jsonParser.getText();
        } else if (JsonToken.VALUE_NULL.equals(currentToken)) {
            return null;
        } else if (currentToken.isScalarValue()) {
            return jsonParser.getText();
        } else if (currentToken.isStructStart()) {//(JsonToken.START_OBJECT.equals(currentToken)) {
            //System.out.println(currentToken.toString());
//            Map<String, Object> currentValue = jsonParser.readValueAs(new TypeReference<Map<String, Object>>() {
//            });
//            Map<String, Object> currentValue = jsonParser.readValueAs(new TypeReference<Map<String, Object>>() {
//            });
            JsonNode currentValue = jsonParser.readValueAsTree();
//            System.out.println(currentValue.getClass().toString());
//            System.out.println(currentValue.toString());
            return currentValue.toString();
        }
        return null;
    }

}
