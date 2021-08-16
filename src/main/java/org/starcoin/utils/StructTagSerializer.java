package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.starcoin.types.StructTag;

import java.io.IOException;

public class StructTagSerializer extends StdSerializer<StructTag> {

    public StructTagSerializer(){
        super(StructTag.class);
    }

    @Override
    public void serialize(StructTag structTag, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("struct_tag_type",Hex.encode(structTag.address.value)+"::"+structTag.module.value+"::"+structTag.name.value);
        jsonGenerator.writeObjectField("struct_tag_params",structTag.type_params);
        jsonGenerator.writeEndObject();
    }
}
