/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    public static Bytes parseBytes(JsonNode typeTagNode) {
        return Bytes.valueOf(Hex.decode(typeTagNode.textValue()));
    }

    public static List<Bytes> parseBytesList(JsonNode typeTagNode) {
        List<Bytes> result = new ArrayList<>();
        if (typeTagNode.isArray()) {
            for (final JsonNode objNode : typeTagNode) {
                result.add(parseBytes(objNode));
            }
        }
        return result;
    }

    public static <T> List<T> parseObjectList(JsonParser jsonParser, JsonNode typeTagNode, Class<T> valueType) throws IOException {
        List<T> result = new ArrayList<>();
        if (typeTagNode.isArray()) {
            for (final JsonNode objNode : typeTagNode) {
                T t = parseObject(jsonParser, objNode, valueType);
                result.add(t);
            }
        }
        return result;
    }


}
