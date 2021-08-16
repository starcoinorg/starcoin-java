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
