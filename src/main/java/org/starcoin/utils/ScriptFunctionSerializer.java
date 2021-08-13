package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.starcoin.types.ScriptFunction;

import java.io.IOException;
import java.util.stream.Collectors;

public class ScriptFunctionSerializer extends StdSerializer<ScriptFunction> {
    protected ScriptFunctionSerializer(Class<ScriptFunction> t) {
        super(t);
    }

    public ScriptFunctionSerializer(){
        super(ScriptFunction.class);
    }

    @Override
    public void serialize(ScriptFunction scriptFunction, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("module",scriptFunction.module.name);
        jsonGenerator.writeObjectField("function",scriptFunction.function.value);
        jsonGenerator.writeObjectField("type_args",scriptFunction.ty_args);
        jsonGenerator.writeObjectField("args",scriptFunction.args.stream()
                .map( arg -> Hex.encode(arg) )
                .collect( Collectors.toList()));
        jsonGenerator.writeEndObject();
    }
}
