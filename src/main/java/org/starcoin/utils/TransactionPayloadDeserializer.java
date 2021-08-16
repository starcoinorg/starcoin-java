package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.novi.serde.Bytes;
import org.starcoin.types.*;
import org.starcoin.types.Package;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionPayloadDeserializer  extends StdDeserializer<TransactionPayload> {

    public TransactionPayloadDeserializer(){
        super(TransactionPayload.class);
    }

    @Override
    public TransactionPayload deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode typeTagNode = jsonParser.getCodec().readTree(jsonParser);

        String typeName = typeTagNode.get("type_name").textValue();
        TransactionPayload transactionPayload = null;
        JsonNode node = typeTagNode.get("value");

        switch (typeName){
            case "Script":
                Bytes code = Bytes.valueOf(Hex.decode(node.get("code").textValue()));
                List<TypeTag> typeArgs = ParseUtil.parseObjectList(jsonParser,node.get("type_args"),TypeTag.class);
                List<Bytes> args = ParseUtil.parseBytesList(node.get("args"));
                transactionPayload = new TransactionPayload.Script(new Script(code,typeArgs,args));
                break;
            case "ScriptFunction":
                ModuleId moduleId = ParseUtil.parseObject(jsonParser,node.get("module"),ModuleId.class);
                Identifier function = ParseUtil.parseObject(jsonParser,node.get("function"),Identifier.class);
                typeArgs = ParseUtil.parseObjectList(jsonParser,node.get("type_args"),TypeTag.class);
                args = ParseUtil.parseBytesList(node.get("args"));
                transactionPayload = new TransactionPayload.ScriptFunction(new ScriptFunction(moduleId,function,typeArgs,args));
                break;
            case "Package":
                AccountAddress address = AccountAddress.valueOf(Hex.decode(node.get("package_address").textValue()));
                List<org.starcoin.types.Module> bytesList = ParseUtil.parseBytesList(node.get("modules")).stream().map(bytes-> new org.starcoin.types.Module(bytes)).collect(Collectors.toList());
                Optional<ScriptFunction> scriptFunction;
                if(node.has("init_script")){
                    scriptFunction = Optional.of(ParseUtil.parseObject(jsonParser,node.get("init_script"),ScriptFunction.class));
                }else {
                    scriptFunction = Optional.empty();
                }
                transactionPayload = new TransactionPayload.Package(new Package(address,bytesList,scriptFunction));
                break;
        }
        return transactionPayload;
    }
}
