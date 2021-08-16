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

public class ScriptFunctionDeserializer  extends StdDeserializer<ScriptFunction> {

    public ScriptFunctionDeserializer(){
        super(ScriptFunction.class);
    }

    @Override
    public ScriptFunction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        ModuleId module = ParseUtil.parseObject(jsonParser,node.get("module"),ModuleId.class);
        Identifier function = new Identifier(node.get("function").asText());
        List<TypeTag> tyArgs = ParseUtil.parseObjectList(jsonParser,node.get("type_args"),TypeTag.class);
        List<Bytes> args = ParseUtil.parseBytesList(node.get("args"));;

        return new ScriptFunction(module,function,tyArgs,args);
    }
}
