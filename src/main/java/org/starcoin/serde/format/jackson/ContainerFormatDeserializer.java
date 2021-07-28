package org.starcoin.serde.format.jackson;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.jetbrains.annotations.NotNull;
import org.starcoin.serde.format.ContainerFormat;
import org.starcoin.serde.format.Format;
import org.starcoin.serde.format.NamedFormat;
import org.starcoin.serde.format.NamedVariantFormat;

import java.io.IOException;
import java.util.*;

public class ContainerFormatDeserializer extends JsonDeserializer<ContainerFormat> {
    @NotNull
    static List<NamedFormat> arrayNodeToNamedFormatList(ObjectCodec oc, ArrayNode namedFormatsNode) throws JsonProcessingException {
        List<NamedFormat> namedFormats = new ArrayList<>();
        for (JsonNode childNode : namedFormatsNode) {
            ObjectNode namedFormatNode = (ObjectNode) childNode;
            NamedFormat namedFormat = oc.treeToValue(namedFormatNode, NamedFormat.class);
            namedFormats.add(namedFormat);
        }
        return namedFormats;
    }

    @Override
    public ContainerFormat deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);
        if (node instanceof TextNode) {
            if ("UNITSTRUCT".equals(node.asText())) {
                //System.out.println("Read text node: " + node);
                return new ContainerFormat.UnitStruct();
            }
        } else if (node instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) node;
            String firstFieldName = objectNode.fieldNames().next();
            if ("NEWTYPESTRUCT".equals(firstFieldName)) {
                JsonNode formatNode = objectNode.get(firstFieldName);
                return new ContainerFormat.NewTypeStruct(oc.treeToValue(formatNode, Format.class));
            } else if ("TUPLESTRUCT".equals(firstFieldName)) {
                ArrayNode formatsNode = (ArrayNode) objectNode.get(firstFieldName);
                List<Format> formats = new ArrayList<>();
                //System.out.println("TUPLESTRUCT: " + formatsNode);
                for (JsonNode formatNode : formatsNode) {
                    formats.add(oc.treeToValue(formatNode, Format.class));
                }
                return new ContainerFormat.TupleStruct(formats);
            } else if ("STRUCT".equals(firstFieldName)) {
                ArrayNode namedFormatsNode = (ArrayNode) objectNode.get(firstFieldName);
                List<NamedFormat> namedFormats = arrayNodeToNamedFormatList(oc, namedFormatsNode);
                return new ContainerFormat.Struct(namedFormats);
            } else if ("ENUM".equals(firstFieldName)) {
                ObjectNode indexedNamedVariantFormatsNode = (ObjectNode) objectNode.get(firstFieldName);
                Map<Integer, NamedVariantFormat> indexedNamedVariantFormats = new HashMap<>();
                Iterator<Map.Entry<String, JsonNode>> iterator = indexedNamedVariantFormatsNode.fields();
                while (iterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = iterator.next();
                    Integer index = Integer.valueOf(entry.getKey());
                    NamedVariantFormat namedVariantFormat = oc.treeToValue(entry.getValue(), NamedVariantFormat.class);
                    indexedNamedVariantFormats.put(index, namedVariantFormat);
                }
                return new ContainerFormat.Enum(indexedNamedVariantFormats);
            }

        }
        throw new JsonParseException(p, "Unknown node type.");
    }
}
