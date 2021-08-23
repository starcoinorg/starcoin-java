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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.jetbrains.annotations.NotNull;
import org.starcoin.types.StructTag;
import org.starcoin.types.TypeTag;

import java.io.IOException;

/**
 * 用于 TypeTag 类在 jackson 库的反序列化。
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class TypeTagDeserializer extends StdDeserializer<TypeTag> {

    public TypeTagDeserializer() {
        super(TypeTag.class);
    }

    @Override
    public TypeTag deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode typeTagNode = jsonParser.getCodec().readTree(jsonParser);

        TypeTag typeTag = null;
        String typeName = typeTagNode.get("type_name").textValue();
        if (typeName != null) {
            switch (typeName) {
                case "U8":
                    typeTag = new TypeTag.U8();
                    break;
                case "U64":
                    typeTag = new TypeTag.U64();
                    break;
                case "U128":
                    typeTag = new TypeTag.U128();
                    break;
                case "Bool":
                    typeTag = new TypeTag.Bool();
                    break;
                case "Address":
                    typeTag = new TypeTag.Address();
                    break;
                case "Signer":
                    typeTag = new TypeTag.Signer();
                    break;
                case "Vector":
                    TypeTag structTag = getTypeTag(jsonParser, typeTagNode, TypeTag.class);
                    typeTag = new TypeTag.Vector(structTag);
                    break;
                case "Struct":
                    StructTag st = getTypeTag(jsonParser, typeTagNode, StructTag.class);
                    typeTag = new TypeTag.Struct(st);
                    break;
            }
        }

        return typeTag;
    }

    @NotNull
    private <T> T getTypeTag(JsonParser jsonParser, JsonNode typeTagNode, Class<T> valueType) throws IOException {
        JsonNode valueNode = typeTagNode.get("value");
        JsonParser parser = valueNode.traverse();
        parser.setCodec(jsonParser.getCodec());
        T t = parser.readValueAs(valueType);
        return t;
    }


}
