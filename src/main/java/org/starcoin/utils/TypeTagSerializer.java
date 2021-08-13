package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.starcoin.types.TypeTag;

import java.io.IOException;

public class TypeTagSerializer extends StdSerializer<TypeTag> {
    protected TypeTagSerializer(Class<TypeTag> t) {
        super(t);
    }

    public TypeTagSerializer(){
        super(TypeTag.class);
    }
    @Override
    public void serialize(TypeTag typeTag, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(typeTag instanceof TypeTag.U8){
            jsonGenerator.writeString("U8");
        }
        if(typeTag instanceof TypeTag.U64){
            jsonGenerator.writeString("U64");
        }
        if(typeTag instanceof TypeTag.U128){
            jsonGenerator.writeString("U128");
        }
        if(typeTag instanceof TypeTag.Bool){
            jsonGenerator.writeString("Bool");
        }
        if(typeTag instanceof TypeTag.Address){
            jsonGenerator.writeString("Address");
        }
        if(typeTag instanceof TypeTag.Signer){
            jsonGenerator.writeString("Signer");
        }
        if(typeTag instanceof TypeTag.Vector){
            jsonGenerator.writeObjectField("type_name","TypeTag");
            jsonGenerator.writeObjectField("value",((TypeTag.Vector) typeTag).value);
        }
        if(typeTag instanceof TypeTag.Struct){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("type_name","Struct");
            jsonGenerator.writeObjectField("value",((TypeTag.Struct) typeTag).value);
            jsonGenerator.writeEndObject();
        }
    }
}
