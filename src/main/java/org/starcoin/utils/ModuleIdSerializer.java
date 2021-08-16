package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.starcoin.types.ModuleId;

import java.io.IOException;

public class ModuleIdSerializer extends StdSerializer<ModuleId> {

    public ModuleIdSerializer(){
        super(ModuleId.class);
    }

    @Override
    public void serialize(ModuleId moduleId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("address",Hex.encode(moduleId.address.toBytes()));
        jsonGenerator.writeStringField("name", moduleId.name.value);
        jsonGenerator.writeEndObject();
    }
}
