package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.novi.serde.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseUtil {

    public static <T> T parseObject(JsonParser jsonParser, JsonNode typeTagNode, Class<T> valueType) throws IOException {
        JsonParser parser = typeTagNode.traverse();
        parser.setCodec(jsonParser.getCodec());
        T t = parser.readValueAs(valueType);
        return t;
    }

    public static Bytes parseBytes(JsonNode typeTagNode){
        return Bytes.valueOf(Hex.decode(typeTagNode.textValue()));
    }

    public static List<Bytes> parseBytesList(JsonNode typeTagNode){
        List<Bytes> result = new ArrayList<>();
        if(typeTagNode.isArray()){
            for (final JsonNode objNode : typeTagNode) {
                result.add(parseBytes(objNode));
            }
        }
        return result;
    }

    public static <T> List<T> parseObjectList(JsonParser jsonParser, JsonNode typeTagNode, Class<T> valueType) throws IOException {
        List<T> result = new ArrayList<>();
        if(typeTagNode.isArray()){
            for (final JsonNode objNode : typeTagNode) {
                T t = parseObject(jsonParser,objNode,valueType);
                result.add(t);
            }
        }
        return result;
    }


}
