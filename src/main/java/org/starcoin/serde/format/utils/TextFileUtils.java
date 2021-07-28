package org.starcoin.serde.format.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class TextFileUtils {

    public static String readTextFile(Path filePath) {
        byte[] textFileBytes;
        try {
            textFileBytes = Files.readAllBytes(filePath);
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new String(textFileBytes);
    }

    public static void writeTextFile(Path filePath, String content) {
        try {
            Files.write(filePath, Collections.singleton(content));
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
