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
import org.starcoin.serde.format.ContainerFormat;
import org.starcoin.serde.format.Format;
import org.starcoin.serde.format.NamedFormat;
import org.starcoin.serde.format.VariantFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VariantFormatDeserializer extends JsonDeserializer<VariantFormat> {

    @Override
    public VariantFormat deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);
        if (node instanceof TextNode) {
            if ("UNIT".equals(node.asText())) {
                //System.out.println("Read text node: " + node);
                return new VariantFormat.Unit();
            }
        } else if (node instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) node;
            String firstFieldName = objectNode.fieldNames().next();
            if ("NEWTYPE".equals(firstFieldName)) {
                JsonNode valueNode = objectNode.get(firstFieldName);
                //System.out.println("NEWTYPE: " + valueNode);
                Format format = oc.treeToValue(valueNode, Format.class);
                return new VariantFormat.NewType(format);
            } else if ("TUPLE".equals(firstFieldName)) {
                ArrayNode formatsNode = (ArrayNode) objectNode.get(firstFieldName);
                List<Format> formats = new ArrayList<>();
                //System.out.println("TUPLE: " + formatsNode);
                for (JsonNode formatNode : formatsNode) {
                    formats.add(oc.treeToValue(formatNode, Format.class));
                }
                return new VariantFormat.Tuple(formats);
            } else if ("STRUCT".equals(firstFieldName)) {
                ArrayNode namedFormatsNode = (ArrayNode) objectNode.get(firstFieldName);
                List<NamedFormat> namedFormats = ContainerFormatDeserializer.arrayNodeToNamedFormatList(oc, namedFormatsNode);
                return new VariantFormat.Struct(namedFormats);
            }
        }
        //System.out.println(node);
        throw new JsonParseException(p, "Unknown node type.");
    }
}