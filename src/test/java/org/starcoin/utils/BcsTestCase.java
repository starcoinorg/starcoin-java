package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.novi.serde.DeserializationError;
import org.junit.Test;
import org.starcoin.types.ScriptFunction;
import org.starcoin.types.StructTag;
import org.starcoin.types.TransactionPayload;
import org.starcoin.types.TypeTag;

public class BcsTestCase {

    @Test
    public void testCall() throws DeserializationError, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TransactionPayloadSerializer payloadSerializer = new TransactionPayloadSerializer();
        SimpleModule module = new SimpleModule();
        module.addSerializer(TransactionPayload.class,payloadSerializer);
        module.addSerializer(TypeTag.class,new TypeTagSerializer());
        module.addSerializer(StructTag.class,new StructTagSerializer());
        module.addSerializer(ScriptFunction.class,new ScriptFunctionSerializer());

        objectMapper.registerModule(module);

        String scriptFunctionPayloadHex = "0x02bd7e8be8fae9f60f2f5136433e36a09110546f6b656e53776170536372697074731a737761705f65786163745f746f6b656e5f666f725f746f6b656e02070000000000000000000000000000000103535443035354430007bd7e8be8fae9f60f2f5136433e36a0910455736478045573647800021000e40b540200000000000000000000001019a3f42b800000000000000000000000";
        TransactionPayload scriptFunctionPayload = TransactionPayload.bcsDeserialize(Hex.decode(scriptFunctionPayloadHex));
        String scriptFunctionPayloadString =  objectMapper.writeValueAsString(scriptFunctionPayload);;
        System.out.println(scriptFunctionPayloadString);

        String packagePayloadHex = "0x01d501465255d22d1751aae8365142119801ad02a11ceb0b020000000701000403043c04400a054a24076e4908b701100cc701450000000100020001010400030201010400040301010400050401010400060301010401020601010401030701010401040801010401050901010401060801010405050605070508050905050c0404040500010c020c04020c0201090005060c0404040501060c02060c0402060c020f4f66666572696e6753637269707438094f66666572696e6738066372656174650865786368616e6765077374616b696e670c73746174655f6368616e676509756e7374616b696e67d501465255d22d1751aae836514211980002000001070e000a010a020a030a043800020102000001030e003801020202000001040e000a013802020302000001040e000a013803020402000001040e000a013804020000";
        TransactionPayload packagePayload = TransactionPayload.bcsDeserialize(Hex.decode(packagePayloadHex));
        String packagePayloadString = objectMapper.writeValueAsString(packagePayload);
        System.out.println(packagePayloadString);

        String scriptPayloadHex = "0x0059a11ceb0b0200000006010002030206040802050a0b07150c082110000000010300010400020003010202010201060900054465627567057072696e74000000000000000000000000000000010000010531000c010e013800020000";
        TransactionPayload scriptPayload = TransactionPayload.bcsDeserialize(Hex.decode(scriptPayloadHex));
        String scriptPayloadString = objectMapper.writeValueAsString(scriptPayload);
        System.out.println(scriptPayloadString);

    }
}