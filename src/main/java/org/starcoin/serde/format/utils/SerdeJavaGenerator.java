package org.starcoin.serde.format.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.starcoin.serde.format.ContainerFormat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.starcoin.serde.format.jackson.utils.MappingUtils.toContainerFormatMap;
import static org.starcoin.serde.format.snakeyaml.YamlUtils.dumpToFile;
import static org.starcoin.serde.format.snakeyaml.YamlUtils.loadYamlMap;
import static org.starcoin.serde.format.utils.ReferenceUtils.includeExternalObjects;

public class SerdeJavaGenerator {

    public static final String WITH_RUNTIMES_SERDE = "--with-runtimes=Serde";
    public static final String WITH_RUNTIMES_BCS = "--with-runtimes=Bcs";


    public static void processSerdeFormatFiles(String workingDirectory, String serdegenPath,
                                               List<SerdeFormatFile> serdeFormatFiles,
                                               ObjectMapper objectMapper, String tempYamlFileExtension) {
        List<Map<String, Object>> yamlMapList = serdeFormatFiles.stream()
                .map(f -> loadYamlMap(Paths.get(f.getFormatFilePath()))).collect(Collectors.toList());
        List<Map<String, ContainerFormat>> containerFormatMapList = yamlMapList.stream()
                .map(m -> toContainerFormatMap(objectMapper, m)).collect(Collectors.toList());
        for (int i = 0; i < serdeFormatFiles.size(); i++) {
            List<Map<String, Object>> eMaps = yamlMapList.subList(0, i);
            List<Map<String, ContainerFormat>> ecMaps = containerFormatMapList.subList(0, i);
            Map<String, Object> cMap = includeExternalObjects(yamlMapList.get(i), containerFormatMapList.get(i),
                    ecMaps, eMaps);
            String tmpFilePath = serdeFormatFiles.get(i).formatFilePath + tempYamlFileExtension;
            dumpToFile(Paths.get(tmpFilePath), cMap);
            if (i == 0) {
                int ec_0 = waitForProcess(workingDirectory, serdegenPath,
                        serdeFormatFiles.get(i).getPackageName(), WITH_RUNTIMES_SERDE,
                        serdeFormatFiles.get(i).getTargetSourceDirectoryPath(), tmpFilePath);
                System.out.println(ec_0);
            }
            int ec = waitForProcess(workingDirectory, serdegenPath,
                    serdeFormatFiles.get(i).getPackageName(), WITH_RUNTIMES_BCS,
                    serdeFormatFiles.get(i).getTargetSourceDirectoryPath(), tmpFilePath);
            System.out.println(ec);
        }
    }

    public static class SerdeFormatFile {
        private String formatFilePath;
        private String packageName;
        private String targetSourceDirectoryPath;

        public SerdeFormatFile(String formatFilePath, String packageName, String targetSourceDirectoryPath) {
            this.formatFilePath = formatFilePath;
            this.packageName = packageName;
            this.targetSourceDirectoryPath = targetSourceDirectoryPath;
        }

        public String getFormatFilePath() {
            return formatFilePath;
        }

        public String getPackageName() {
            return packageName;
        }

        public String getTargetSourceDirectoryPath() {
            return targetSourceDirectoryPath;
        }
    }

    public static int waitForProcess(String workingDirectory, String serdegenPath,
                                     String packageName, String withRuntimesOrEmpty,
                                     String targetSrcDir, String yamlFilePath) {
        String shellPath = "/bin/sh";
        String cmdFormat = "%1$s --language java --module-name %2$s %3$s --target-source-dir %4$s %5$s";
        String cmd = String.format(cmdFormat, serdegenPath, packageName, withRuntimesOrEmpty,
                targetSrcDir, yamlFilePath);
        //System.out.println(cmd);
        Process process;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(new String[]{shellPath, "-c", cmd});
            processBuilder.directory(new File(workingDirectory));
            //processBuilder.inheritIO();
            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
        try {
            return process.waitFor();
        } catch (InterruptedException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
