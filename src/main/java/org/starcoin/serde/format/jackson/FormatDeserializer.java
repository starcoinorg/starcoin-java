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
import org.starcoin.serde.format.Format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormatDeserializer extends JsonDeserializer<Format> {

    @Override
    public Format deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);

        if (node instanceof TextNode) {
            //System.out.println("Read text node: " + node);
            return Format.Primitive.valueOf(node.textValue());
        } else if (node instanceof ObjectNode) {
            //System.out.println(node);
            ObjectNode objectNode = (ObjectNode) node;
            String firstFieldName = objectNode.fieldNames().next();
            if ("TYPENAME".equals(firstFieldName)) {
                TextNode textNode = (TextNode) objectNode.get(firstFieldName);
                return new Format.TypeName(textNode.asText());
            } else if ("OPTION".equals(firstFieldName)) {
                JsonNode formatNode = objectNode.get(firstFieldName);
                return new Format.Option(oc.treeToValue(formatNode, Format.class));
            } else if ("SEQ".equals(firstFieldName)) {
                JsonNode formatNode = objectNode.get(firstFieldName);
                return new Format.Seq(oc.treeToValue(formatNode, Format.class));
            } else if ("MAP".equals(firstFieldName)) {
                ObjectNode kvFormatNode = (ObjectNode) objectNode.get(firstFieldName);
                Format keyFormat = oc.treeToValue(kvFormatNode.get("KEY"), Format.class);
                Format valueFormat = oc.treeToValue(kvFormatNode.get("VALUE"), Format.class);
                return new Format.Map(keyFormat, valueFormat);
            } else if ("TUPLE".equals(firstFieldName)) {
                ArrayNode formatsNode = (ArrayNode) objectNode.get(firstFieldName);
                List<Format> formats = new ArrayList<>();
                //System.out.println("TUPLE: " + formatsNode);
                for (JsonNode formatNode : formatsNode) {
                    formats.add(oc.treeToValue(formatNode, Format.class));
                }
                return new Format.Tuple(formats);
            } else if ("TUPLEARRAY".equals(firstFieldName)) {
                ObjectNode czNode = (ObjectNode) objectNode.get(firstFieldName);
                Format contentFormat = oc.treeToValue(czNode.get("CONTENT"), Format.class);
                int size = Integer.valueOf(czNode.get("SIZE").asText());
                return new Format.TupleArray(contentFormat, size);
            }
        }
        throw new JsonParseException(p, "Unknown node type.");
    }
}