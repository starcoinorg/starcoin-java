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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.starcoin.types.ScriptFunction;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * 用于 ScriptFunction 类在 jackson 库的序列化。
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class ScriptFunctionSerializer extends StdSerializer<ScriptFunction> {

    public ScriptFunctionSerializer() {
        super(ScriptFunction.class);
    }

    @Override
    public void serialize(ScriptFunction scriptFunction, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("module", scriptFunction.module);
        jsonGenerator.writeObjectField("function", scriptFunction.function.value);
        jsonGenerator.writeObjectField("type_args", scriptFunction.ty_args);
        jsonGenerator.writeObjectField("args", scriptFunction.args.stream()
                .map(arg -> Hex.encode(arg))
                .collect(Collectors.toList()));
        jsonGenerator.writeEndObject();
    }
}
