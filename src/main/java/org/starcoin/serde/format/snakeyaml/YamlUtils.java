package org.starcoin.serde.format.snakeyaml;

import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class YamlUtils {
    private YamlUtils() {}

    public static void dumpToFile(Path filePath, Map<String, Object> map) {
        Yaml yaml = new Yaml();
        try (FileWriter fileWriter = new FileWriter(filePath.toFile())) {
            String content = yaml.dump(map);
            fileWriter.write(content);
            fileWriter.flush();
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Object> loadYamlMap(Path filePath) {
        Yaml yaml = new Yaml();
        String doc = readTextFile(filePath);
        Map<String, Object> yamlMap = yaml.load(doc);
        return yamlMap;
    }

    private static String readTextFile(Path filePath) {
        byte[] textFileBytes;
        try {
            textFileBytes = Files.readAllBytes(filePath);
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
        String doc = new String(textFileBytes);
        return doc;
    }

}
