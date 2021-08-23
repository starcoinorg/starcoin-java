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
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.starcoin.types.AccountAddress;
import org.starcoin.types.Identifier;
import org.starcoin.types.StructTag;
import org.starcoin.types.TypeTag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于 StructTag 类在 jackson 库的反序列化。
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class StructTagDeserializer extends StdDeserializer<StructTag> {

    public StructTagDeserializer() {
        super(StructTag.class);
    }

    @Override
    public StructTag deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StructTag structTag = null;
        AccountAddress address = null;
        Identifier module = null;
        Identifier name = null;
        List<TypeTag> type_params = new ArrayList<>();
        while (!jsonParser.isClosed()) {
            String token = jsonParser.nextTextValue();
            if (token != null) {
                switch (jsonParser.getCurrentName()) {
                    case "struct_tag_type":
                        String[] tokens = token.split("::");
                        address = AccountAddress.valueOf(Hex.decode(tokens[0]));
                        module = new Identifier(tokens[1]);
                        name = new Identifier(tokens[2]);
                        break;
                    case "struct_tag_params":
                        break;// TODO parse
                }
            }
        }
        if (address != null && module != null & name != null) {
            structTag = new StructTag(address, module, name, type_params);
        }
        return structTag;
    }
}
