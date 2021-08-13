package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.starcoin.types.StructTag;

import java.io.IOException;

public class StructTagSerializer extends StdSerializer<StructTag> {
    protected StructTagSerializer(Class<StructTag> t) {
        super(t);
    }

    public StructTagSerializer(){
        super(StructTag.class);
    }

    @Override
    public void serialize(StructTag structTag, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(Hex.encode(structTag.address.value)+"::"+structTag.module.value+"::"+structTag.name.value);
    }
}
