package org.starcoin.serde.format.jackson.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.starcoin.serde.format.*;
import org.starcoin.serde.format.jackson.*;

import java.util.Map;

public class MappingUtils {
    private MappingUtils() {}

    /**
     * Convert 'dynamic' map(probably loaded directly from YAML/JSON) to ContainerFormat map.
     * @param objectMapper ObjectMapper
     * @param map Origin map.
     * @return ContainerFormat map.
     */
    public static Map<String, ContainerFormat> toContainerFormatMap(ObjectMapper objectMapper, Map<String, Object> map) {
        Map<String, ContainerFormat> containerFormatMap = objectMapper.convertValue(map,
                new TypeReference<Map<String, ContainerFormat>>() {
                });
        return containerFormatMap;
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(ContainerFormat.class, new ContainerFormatDeserializer());
        module.addDeserializer(Format.class, new FormatDeserializer());
        module.addDeserializer(NamedFormat.class, new NamedFormatDeserializer());
        module.addDeserializer(Format.class, new FormatDeserializer());
        module.addDeserializer(NamedVariantFormat.class, new NamedVariantFormatDeserializer());
        module.addDeserializer(VariantFormat.class, new VariantFormatDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
