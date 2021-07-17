package org.starcoin.serde.format.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.starcoin.serde.format.ContainerFormat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    private static final String JAVA_SOURCE_CLASS_LINE_START = "public final class ";

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
            Map<String, Object> concatenatedMap = includeExternalObjects(yamlMapList.get(i), containerFormatMapList.get(i),
                    ecMaps, eMaps);
            String packageName = serdeFormatFiles.get(i).getPackageName();
            String targetSourceDirectoryPath = serdeFormatFiles.get(i).getTargetSourceDirectoryPath();
            String tmpFilePath = serdeFormatFiles.get(i).formatFilePath + tempYamlFileExtension;
            dumpToFile(Paths.get(tmpFilePath), concatenatedMap);
            if (i == 0) {
                int ec_0 = waitForProcess(workingDirectory, serdegenPath, packageName, WITH_RUNTIMES_SERDE, targetSourceDirectoryPath, tmpFilePath);
                System.out.println(ec_0);
            }
            int ec = waitForProcess(workingDirectory, serdegenPath, packageName, WITH_RUNTIMES_BCS, targetSourceDirectoryPath, tmpFilePath);
            System.out.println(ec);
            try {
                Files.deleteIfExists(Paths.get(tmpFilePath));
            } catch (IOException e) {
                //e.printStackTrace();
                throw new RuntimeException("Delete temporary file failed.", e);
            }

            List<String> namesToRemove = removeDuplicatedFiles(workingDirectory, packageName,
                    targetSourceDirectoryPath, concatenatedMap, ecMaps);

            Map<String, ContainerFormat> containerFormatMap = containerFormatMapList.get(i);
            modifyGeneratedFiles(workingDirectory, serdeFormatFiles, eMaps, namesToRemove, containerFormatMap, i);
        }
    }

    private static void modifyGeneratedFiles(String workingDirectory, List<SerdeFormatFile> serdeFormatFiles,
                                             List<Map<String, Object>> eMaps,
                                             List<String> namesToRemove,
                                             Map<String, ContainerFormat> containerFormatMap, int i) {
        String packageName = serdeFormatFiles.get(i).getPackageName();
        String targetSourceDirectoryPath = serdeFormatFiles.get(i).getTargetSourceDirectoryPath();
        List<String> retainedNames = new ArrayList<>(containerFormatMap.keySet());
        retainedNames.removeAll(namesToRemove);
        System.out.println(retainedNames);
        retainedNames.forEach(n -> {
            List<Integer> importPackageIds = new ArrayList<>();
            for (String rn : containerFormatMap.get(n).referencedContainerTypeNames()) {
                for (int j = 0; j < eMaps.size(); j++) {
                    if (eMaps.get(j).containsKey(rn)) {
                        if (!importPackageIds.contains(j)) {
                            importPackageIds.add(j);
                        }
                        break;
                    }
                }
            }
            System.out.println(n + ": " + importPackageIds);
            if (!importPackageIds.isEmpty()) {
                Path pathToModify = getJavaFilePathByTypeName(workingDirectory, packageName, targetSourceDirectoryPath, n);
                String importStr = importPackageIds.stream().map(idx ->
                        "import " + serdeFormatFiles.get(idx).getPackageName() + ".*;")
                        .reduce((s1, s2) -> s1 + System.lineSeparator() + s2).get();
                System.out.println(importStr);
                String sourceCode = TextFileUtils.readTextFile(pathToModify);
                int classLineIdx = sourceCode.indexOf(JAVA_SOURCE_CLASS_LINE_START);
                if (classLineIdx != -1) {
                    String newSourceCode = sourceCode.substring(0, classLineIdx)
                            + importStr + System.lineSeparator() + System.lineSeparator()
                            + sourceCode.substring(classLineIdx);
                    TextFileUtils.writeTextFile(pathToModify, newSourceCode);
                }
            }
        });
    }


    private static List<String> removeDuplicatedFiles(String workingDirectory, String packageName,
                                                      String targetSourceDirectoryPath,
                                                      Map<String, Object> concatenatedMap,
                                                      List<Map<String, ContainerFormat>> ecMaps) {
        List<String> namesToRemove = new ArrayList<>(concatenatedMap.keySet());
        namesToRemove.retainAll(ecMaps.stream().flatMap(m -> m.keySet().stream()).collect(Collectors.toSet()));
        System.out.println(namesToRemove);
        namesToRemove.forEach(n -> {
            Path pathToRemove = getJavaFilePathByTypeName(workingDirectory, packageName, targetSourceDirectoryPath, n);
            //System.out.println(pathToRemove);
            try {
                Files.deleteIfExists(pathToRemove);
            } catch (IOException e) {
                //e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
        return namesToRemove;
    }

    private static Path getJavaFilePathByTypeName(String workingDirectory, String packageName, String targetSourceDirectoryPath, String n) {
        return Paths.get(workingDirectory, targetSourceDirectoryPath,
                packageName.replace(".", File.separator), n + ".java");
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

}
