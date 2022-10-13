package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.primitives.Longs;
import com.novi.serde.Bytes;
import com.novi.serde.DeserializationError;
import org.junit.Test;
import org.starcoin.types.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.junit.Assert.assertEquals;
import static org.starcoin.utils.AccountAddressUtils.from_hex_literal;

public class BcsTestCase {

    @Test
    public void testSerialize() throws DeserializationError, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TransactionPayloadSerializer payloadSerializer = new TransactionPayloadSerializer();
        SimpleModule module = new SimpleModule();
        module.addSerializer(TransactionPayload.class, payloadSerializer);
        module.addSerializer(TypeTag.class, new TypeTagSerializer());
        module.addSerializer(StructTag.class, new StructTagSerializer());
        module.addSerializer(ScriptFunction.class, new ScriptFunctionSerializer());
        module.addSerializer(ModuleId.class, new ModuleIdSerializer());
        objectMapper.registerModule(module);

        String scriptFunctionPayloadHex = "0x02bd7e8be8fae9f60f2f5136433e36a09110546f6b656e53776170536372697074731a737761705f65786163745f746f6b656e5f666f725f746f6b656e02070000000000000000000000000000000103535443035354430007bd7e8be8fae9f60f2f5136433e36a0910455736478045573647800021000e40b540200000000000000000000001019a3f42b800000000000000000000000";
        TransactionPayload scriptFunctionPayload = TransactionPayload.bcsDeserialize(Hex.decode(scriptFunctionPayloadHex));
        String scriptFunctionPayloadString = objectMapper.writeValueAsString(scriptFunctionPayload);
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

    @Test
    public void testAddress() throws DeserializationError {
//        String address = "00000000000000000000000000000001";
//        AccountAddress accountAddress = AccountAddress.bcsDeserialize(Hex.decode(address));
//        System.out.println(accountAddress);
//        address = "1";
//        accountAddress = from_hex_literal(address);
//        System.out.println(accountAddress);
//        assertEquals(accountAddress, AccountAddress.valueOf(Hex.decode("0x00000000000000000000000000000001")));
//        address = "a550c18";
//        accountAddress = from_hex_literal(address);
//        System.out.println(accountAddress);
//        assertEquals(accountAddress, AccountAddress.valueOf(Hex.decode("0x0000000000000000000000000a550c18")));
//        String key ="0x01000000000000001fa1bf569672003a5871c0151bacccff";
//        EventKey eventKey = new EventKey(Bytes.valueOf(Hex.decode(key)));
//        System.out.println(eventKey);

//        long seq = Longs.fromByteArray(Hex.decode("0x0100000000000000"));
        System.out.println("0x010000000000000068cec2df4bca601dc35351a8b7d1e567".substring(0,18));
        long seq = fromArray(Hex.decode("0x0100000000000000"));
        System.out.println(seq);

    }

    public static  long fromArray(byte[] payload){
        ByteBuffer buffer = ByteBuffer.wrap(payload);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getLong();
    }

    @Test
    public void testDeserializeAndSerialize() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(StructTag.class, new StructTagDeserializer());
        module.addDeserializer(TypeTag.class, new TypeTagDeserializer());
        module.addDeserializer(ModuleId.class, new ModuleDeserializer());
        module.addDeserializer(ScriptFunction.class, new ScriptFunctionDeserializer());
        module.addDeserializer(TransactionPayload.class, new TransactionPayloadDeserializer());

        module.addSerializer(TransactionPayload.class, new TransactionPayloadSerializer());
        module.addSerializer(TypeTag.class, new TypeTagSerializer());
        module.addSerializer(StructTag.class, new StructTagSerializer());
        module.addSerializer(ScriptFunction.class, new ScriptFunctionSerializer());
        module.addSerializer(ModuleId.class, new ModuleIdSerializer());

        objectMapper.registerModule(module);

        String structTagString = "{\"struct_tag_type\":\"0xbd7e8be8fae9f60f2f5136433e36a091::Usdx::Usdx\",\"struct_tag_params\":[]}";
        StructTag structTag = objectMapper.readValue(structTagString, StructTag.class);

        assertEquals(structTagString, objectMapper.writeValueAsString(structTag));

        String typeTagString = "{\"type_name\":\"Struct\",\"value\":{\"struct_tag_type\":\"0x00000000000000000000000000000001::STC::STC\",\"struct_tag_params\":[]}}";
        TypeTag typeTag = objectMapper.readValue(typeTagString, TypeTag.class);

        assertEquals(typeTagString, objectMapper.writeValueAsString(typeTag));

        String scriptFunctionString = "{\"module\":{\"address\":\"0xbd7e8be8fae9f60f2f5136433e36a091\",\"name\":\"TokenSwapScripts\"},\"function\":\"swap_exact_token_for_token\",\"type_args\":[{\"type_name\":\"Struct\",\"value\":{\"struct_tag_type\":\"0x00000000000000000000000000000001::STC::STC\",\"struct_tag_params\":[]}},{\"type_name\":\"Struct\",\"value\":{\"struct_tag_type\":\"0xbd7e8be8fae9f60f2f5136433e36a091::Usdx::Usdx\",\"struct_tag_params\":[]}}],\"args\":[\"0x00e40b54020000000000000000000000\",\"0x19a3f42b800000000000000000000000\"]}";
        ScriptFunction scriptFunction = objectMapper.readValue(scriptFunctionString, ScriptFunction.class);

        assertEquals(scriptFunctionString, objectMapper.writeValueAsString(scriptFunction));

        String scriptFunctionPayloadString = "{\"type_name\":\"ScriptFunction\",\"value\":{\"module\":{\"address\":\"0xbd7e8be8fae9f60f2f5136433e36a091\",\"name\":\"TokenSwapScripts\"},\"function\":\"swap_exact_token_for_token\",\"type_args\":[{\"type_name\":\"Struct\",\"value\":{\"struct_tag_type\":\"0x00000000000000000000000000000001::STC::STC\",\"struct_tag_params\":[]}},{\"type_name\":\"Struct\",\"value\":{\"struct_tag_type\":\"0xbd7e8be8fae9f60f2f5136433e36a091::Usdx::Usdx\",\"struct_tag_params\":[]}}],\"args\":[\"0x00e40b54020000000000000000000000\",\"0x19a3f42b800000000000000000000000\"]}}";
        TransactionPayload transactionPayload = objectMapper.readValue(scriptFunctionPayloadString, TransactionPayload.class);

        assertEquals(scriptFunctionPayloadString, objectMapper.writeValueAsString(transactionPayload));

        String packagePayloadString = "{\"type_name\":\"Package\",\"value\":{\"package_address\":\"0xd501465255d22d1751aae83651421198\",\"modules\":[\"0xa11ceb0b020000000701000403043c04400a054a24076e4908b701100cc701450000000100020001010400030201010400040301010400050401010400060301010401020601010401030701010401040801010401050901010401060801010405050605070508050905050c0404040500010c020c04020c0201090005060c0404040501060c02060c0402060c020f4f66666572696e6753637269707438094f66666572696e6738066372656174650865786368616e6765077374616b696e670c73746174655f6368616e676509756e7374616b696e67d501465255d22d1751aae836514211980002000001070e000a010a020a030a043800020102000001030e003801020202000001040e000a013802020302000001040e000a013803020402000001040e000a0138040200\"]}}";
        TransactionPayload packageTransactionPayload = objectMapper.readValue(packagePayloadString, TransactionPayload.class);

        assertEquals(packagePayloadString, objectMapper.writeValueAsString(packageTransactionPayload));

        String scriptPayloadString = "{\"type_name\":\"Script\",\"value\":{\"args\":[],\"code\":\"0xa11ceb0b0200000006010002030206040802050a0b07150c082110000000010300010400020003010202010201060900054465627567057072696e74000000000000000000000000000000010000010531000c010e01380002\",\"type_args\":[]}}";
        TransactionPayload scriptTransactionPayload = objectMapper.readValue(scriptPayloadString, TransactionPayload.class);

        assertEquals(scriptPayloadString, objectMapper.writeValueAsString(scriptTransactionPayload));

    }
}