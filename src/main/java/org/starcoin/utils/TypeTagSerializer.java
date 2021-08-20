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
import org.starcoin.types.TypeTag;

import java.io.IOException;

/**
 *
 * 用于 TypeTag 类在 jackson 库的序列化。
 *
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class TypeTagSerializer extends StdSerializer<TypeTag> {

    public TypeTagSerializer(){
        super(TypeTag.class);
    }
    @Override
    public void serialize(TypeTag typeTag, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        if(typeTag instanceof TypeTag.U8){
            jsonGenerator.writeObjectField("type_name","U8");
        }
        if(typeTag instanceof TypeTag.U64){
            jsonGenerator.writeObjectField("type_name","U64");
        }
        if(typeTag instanceof TypeTag.U128){
            jsonGenerator.writeObjectField("type_name","U128");
        }
        if(typeTag instanceof TypeTag.Bool){
            jsonGenerator.writeObjectField("type_name","Bool");

        }
        if(typeTag instanceof TypeTag.Address){
            jsonGenerator.writeObjectField("type_name","Address");
        }
        if(typeTag instanceof TypeTag.Signer){
            jsonGenerator.writeObjectField("type_name","Signer");
        }
        if(typeTag instanceof TypeTag.Vector){
            jsonGenerator.writeObjectField("type_name","Vector");
            jsonGenerator.writeObjectField("value",((TypeTag.Vector) typeTag).value);
        }
        if(typeTag instanceof TypeTag.Struct){
            jsonGenerator.writeObjectField("type_name","Struct");
            jsonGenerator.writeObjectField("value",((TypeTag.Struct) typeTag).value);
        }
        jsonGenerator.writeEndObject();
    }
}
