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

public class TypeTagDeserializer extends StdDeserializer<TypeTag> {

    public TypeTagDeserializer(){
        super(TypeTag.class);
    }

    @Override
    public TypeTag deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode typeTagNode = jsonParser.getCodec().readTree(jsonParser);

        TypeTag typeTag = null;
        String typeName = typeTagNode.get("type_name").textValue();
        if(typeName!=null){
            switch (typeName){
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
                    TypeTag structTag = getTypeTag(jsonParser, typeTagNode,TypeTag.class);
                    typeTag = new TypeTag.Vector(structTag);
                    break;
                case "Struct":
                    StructTag st = getTypeTag(jsonParser, typeTagNode,StructTag.class);
                    typeTag = new TypeTag.Struct(st);
                    break;
                }
            }

        return typeTag;
    }

    @NotNull
    private <T> T getTypeTag(JsonParser jsonParser, JsonNode typeTagNode,Class<T> valueType) throws IOException {
        JsonNode valueNode= typeTagNode.get("value");
        JsonParser parser = valueNode.traverse();
        parser.setCodec(jsonParser.getCodec());
        T t = parser.readValueAs(valueType);
        return t;
    }


}
