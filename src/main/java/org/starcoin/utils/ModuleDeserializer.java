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
import org.starcoin.types.AccountAddress;
import org.starcoin.types.Identifier;
import org.starcoin.types.ModuleId;

import java.io.IOException;

/**
 *
 * 用于 Module 类在 jackson 库的反序列化。
 *
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class ModuleDeserializer extends StdDeserializer<ModuleId> {

    public ModuleDeserializer(){
        super(ModuleId.class);
    }

    @Override
    public ModuleId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode typeTagNode = jsonParser.getCodec().readTree(jsonParser);

        String name = typeTagNode.get("name").textValue();
        AccountAddress address = AccountAddress.valueOf(Hex.decode(typeTagNode.get("address").textValue()));
        return new ModuleId(address,new Identifier(name));
    }
}
