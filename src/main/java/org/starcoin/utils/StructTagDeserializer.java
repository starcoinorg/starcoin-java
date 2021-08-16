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

public class StructTagDeserializer extends StdDeserializer<StructTag> {

    public StructTagDeserializer(){
        super(StructTag.class);
    }

    @Override
    public StructTag deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StructTag structTag = null;
        AccountAddress address = null;
        Identifier module = null;
        Identifier name = null;
        List<TypeTag> type_params = new ArrayList<>();
        while (!jsonParser.isClosed()){
            String token = jsonParser.nextTextValue();
            if(token!=null){
                switch (jsonParser.getCurrentName()){
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
        if (address!=null&&module!=null&name!=null){
            structTag = new StructTag(address,module,name,type_params);
        }
        return structTag;
    }
}
