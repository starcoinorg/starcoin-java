package org.starcoin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.starcoin.serde.format.ContainerFormat;
import org.starcoin.serde.format.utils.ReferenceUtils;
import org.starcoin.serde.format.utils.SerdeJavaGenerator;
import org.yaml.snakeyaml.Yaml;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.starcoin.serde.format.jackson.utils.MappingUtils.getObjectMapper;
import static org.starcoin.serde.format.jackson.utils.MappingUtils.toContainerFormatMap;
import static org.starcoin.serde.format.snakeyaml.YamlUtils.loadYamlMap;
import static org.starcoin.serde.format.utils.SerdeJavaGenerator.processSerdeFormatFiles;

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
        //System.out.println(map);

        try {
            System.out.println(objectMapper.writeValueAsString(map));
            ContainerFormat containerFormat = objectMapper.convertValue(map, ContainerFormat.class);
            System.out.println(containerFormat);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.out.println("---------------------");
        String textFilePath1 = "/Users/yangjiefeng/Documents/starcoinorg/starcoin/etc/starcoin_types.yml";
        String textFilePath2 = "/Users/yangjiefeng/Documents/starcoinorg/starcoin/etc/onchain_events.yml";
        Map<String, Object> yamlMap1 = loadYamlMap(Paths.get(textFilePath1));
        Map<String, Object> yamlMap2 = loadYamlMap(Paths.get(textFilePath2));
        Map<String, Object>[] externalMaps = new Map[]{yamlMap1, yamlMap2};
        Map<String, ContainerFormat> containerFormatMap1 = toContainerFormatMap(objectMapper, yamlMap1);
        Map<String, ContainerFormat> containerFormatMap2 = toContainerFormatMap(objectMapper, yamlMap2);
        Map<String, ContainerFormat>[] externalContainerFormatMap = new Map[]{containerFormatMap1, containerFormatMap2};
        // ---------
        String textFilePath3 = "/Users/yangjiefeng/Documents/starcoinorg/starswap-api/generate-format/starswap_types.yaml";
        Map<String, Object> originMap = loadYamlMap(Paths.get(textFilePath3));
        Map<String, Object> concatenatedMap = ReferenceUtils.includeExternalObjects(originMap, objectMapper,
                Arrays.asList(externalContainerFormatMap),
                Arrays.asList(externalMaps));
        System.out.println(concatenatedMap);

        // ----------
        String serdegenPath = "serdegen";
        String tempYamlFileExtension = ".temp";
        String workingDirectory = "/Users/yangjiefeng/Documents/starcoinorg/starswap-api";
        String targetSrcDir = "./src/main/java";

        //String packageName = "org.starcoin.starswap.types";
        //String tempYamlFilePath = textFilePath3.concat(tempYamlFileExtension);
        //dumpToFile(Paths.get(tempYamlFilePath), concatenatedMap);
        //String yamlFilePath = tempYamlFilePath; //"./generate-format/starcoin_types.yaml";
        //int exitCode = waitForProcess(workingDirectory, serdegenPath, packageName, WITH_RUNTIMES_SERDE, targetSrcDir, yamlFilePath);
        //System.out.println(exitCode);
        //exitCode = waitForProcess(workingDirectory, serdegenPath, packageName, WITH_RUNTIMES_BCS, targetSrcDir, yamlFilePath);
        //System.out.println(exitCode);

        List<SerdeJavaGenerator.SerdeFormatFile> serdeFormatFiles = Arrays.asList(
                new SerdeJavaGenerator.SerdeFormatFile(textFilePath1, "org.starcoin.types", targetSrcDir),
                new SerdeJavaGenerator.SerdeFormatFile(textFilePath2, "org.starcoin.types.event", targetSrcDir),
                new SerdeJavaGenerator.SerdeFormatFile(textFilePath3, "org.starcoin.starswap.types", targetSrcDir)
        );

        processSerdeFormatFiles(workingDirectory, serdegenPath, serdeFormatFiles, objectMapper, tempYamlFileExtension,1);
    }


}
