package org.starcoin.serde.format.jackson;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.starcoin.serde.format.Named;

import java.io.IOException;

public abstract class NamedDeserializer<T extends Named<V>, V> extends JsonDeserializer<T> {

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);
        ObjectNode objectNode = (ObjectNode) node;
        T instance = newNamedInstance();
        instance.setName(objectNode.fieldNames().next());
        instance.setValue(oc.treeToValue(objectNode.get(instance.getName()), valueType()));
        return instance;
    }

    protected abstract Class<V> valueType();

    protected abstract T newNamedInstance();
}