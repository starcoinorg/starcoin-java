package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.starcoin.types.TransactionPayload;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionPayloadSerializer extends StdSerializer<TransactionPayload> {
    protected TransactionPayloadSerializer(Class<TransactionPayload> t) {
        super(t);
    }

    protected TransactionPayloadSerializer(JavaType type) {
        super(type);
    }

    protected TransactionPayloadSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    protected TransactionPayloadSerializer(StdSerializer<?> src) {
        super(src);
    }

    public TransactionPayloadSerializer(){
        super(TransactionPayload.class);
    }

    @Override
    public void serialize(TransactionPayload payload, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(payload instanceof TransactionPayload.Script){
            handleScript(jsonGenerator,serializerProvider,(TransactionPayload.Script) payload);
        }
        if(payload instanceof TransactionPayload.ScriptFunction){
            handleScriptFunction(jsonGenerator,serializerProvider,(TransactionPayload.ScriptFunction) payload);
        }
        if(payload instanceof TransactionPayload.Package){
            handlePackage(jsonGenerator,serializerProvider,(TransactionPayload.Package) payload);
        }
    }

    private void handleScript(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TransactionPayload.Script script) throws IOException {
        Map<Object,Object> data = new HashMap<>();
        data.put("code",Hex.encode(script.value.code));
        data.put("args",script.value.args.stream()
                .map( arg -> Hex.encode(arg) )
                .collect( Collectors.toList()));
        data.put("type_args",script.value.ty_args);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("type_name","script");
        jsonGenerator.writeObjectField("value",data);
        jsonGenerator.writeEndObject();
    }

    private void handleScriptFunction(JsonGenerator jsonGenerator, SerializerProvider serializerProvider,TransactionPayload.ScriptFunction scriptFunction) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("type_name","ScriptFunction");
        jsonGenerator.writeObjectField("value",scriptFunction.value);
        jsonGenerator.writeEndObject();
    }

    private void handlePackage(JsonGenerator jsonGenerator, SerializerProvider serializerProvider,TransactionPayload.Package pkg) throws IOException {
        Map<Object,Object> data = new HashMap<>();
        data.put("modules",pkg.value.modules.stream().map(
                module -> Hex.encode(module.code)
        ).collect( Collectors.toList()));
        data.put("package_address",Hex.encode(pkg.value.package_address.value));
        if(pkg.value.init_script.isPresent()){
            data.put("init_script",pkg.value.init_script.get());
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("type_name","package");
        jsonGenerator.writeObjectField("value",data);
        jsonGenerator.writeEndObject();
    }

}
