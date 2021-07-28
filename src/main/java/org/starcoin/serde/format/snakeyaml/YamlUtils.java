package org.starcoin.serde.format.snakeyaml;

import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import static org.starcoin.serde.format.utils.TextFileUtils.readTextFile;

public class YamlUtils {
    private YamlUtils() {
    }

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
        return yaml.load(doc);
    }


}
