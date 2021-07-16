package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.starcoin.serde.format.*;
import org.starcoin.serde.format.jackson.*;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class SerdeUtilsTest {

    public static void main(String[] args) {
        String testDoc1 = "  STRUCT:\n" +
                "    - authentication_key:\n" +
                "        SEQ: U8\n" +
                "    - withdrawal_capability:\n" +
                "        OPTION:\n" +
                "          TYPENAME: WithdrawCapabilityResource\n" +
                "    - key_rotation_capability:\n" +
                "        OPTION:\n" +
                "          TYPENAME: KeyRotationCapabilityResource\n" +
                "    - withdraw_events:\n" +
                "        TYPENAME: EventHandle\n" +
                "    - deposit_events:\n" +
                "        TYPENAME: EventHandle\n" +
                "    - accept_token_events:\n" +
                "        TYPENAME: EventHandle\n" +
                "    - sequence_number: U64";
        String testDoc2 = "  ENUM:\n" +
                "    0:\n" +
                "      Deletion: UNIT\n" +
                "    1:\n" +
                "      Value:\n" +
                "        NEWTYPE: BYTES";

        ObjectMapper objectMapper = getObjectMapper();


        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.load(testDoc1);
        System.out.println(map);

        try {
            System.out.println(objectMapper.writeValueAsString(map));
            ContainerFormat containerFormat = objectMapper.convertValue(map, ContainerFormat.class);
            System.out.println(containerFormat);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String textFilePath1 = "/Users/yangjiefeng/Documents/starcoinorg/starcoin/etc/starcoin_types.yml";
        String textFilePath2 = "/Users/yangjiefeng/Documents/starcoinorg/starcoin/etc/onchain_events.yml";
        byte[] textFileBytes = new byte[0];
        try {
            textFileBytes = Files.readAllBytes(Paths.get(textFilePath1));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String testDoc3 = new String(textFileBytes);
        System.out.println(testDoc3);
        Map<String, Object> map3 = yaml.load(testDoc3);
        System.out.println(map3);
        Map<String, ContainerFormat> containerFormatMap = objectMapper.convertValue(map3,
                new TypeReference<Map<String, ContainerFormat>>() {});
        System.out.println(containerFormatMap);
        containerFormatMap.entrySet().stream().forEach(c -> {
            System.out.println(String.format("===== %1$s =====", c.getKey()));
            System.out.println(c.getValue().referencedContainerTypeNames());
        });
    }


    private static ObjectMapper getObjectMapper() {
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
