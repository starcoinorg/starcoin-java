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
import com.novi.serde.Bytes;
import org.starcoin.types.Identifier;
import org.starcoin.types.ModuleId;
import org.starcoin.types.ScriptFunction;
import org.starcoin.types.TypeTag;

import java.io.IOException;
import java.util.List;

/**
 * 用于 ScriptFunction 类在 jackson 库的反序列化。
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class ScriptFunctionDeserializer extends StdDeserializer<ScriptFunction> {

    public ScriptFunctionDeserializer() {
        super(ScriptFunction.class);
    }

    @Override
    public ScriptFunction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        ModuleId module = ParseUtil.parseObject(jsonParser, node.get("module"), ModuleId.class);
        Identifier function = new Identifier(node.get("function").asText());
        List<TypeTag> tyArgs = ParseUtil.parseObjectList(jsonParser, node.get("type_args"), TypeTag.class);
        List<Bytes> args = ParseUtil.parseBytesList(node.get("args"));
        ;

        return new ScriptFunction(module, function, tyArgs, args);
    }
}
