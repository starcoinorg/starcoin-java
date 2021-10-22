package org.starcoin.serde.format.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.starcoin.serde.format.ContainerFormat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.starcoin.serde.format.jackson.utils.MappingUtils.toContainerFormatMap;
import static org.starcoin.serde.format.snakeyaml.YamlUtils.dumpToFile;
import static org.starcoin.serde.format.snakeyaml.YamlUtils.loadYamlMap;
import static org.starcoin.serde.format.utils.ReferenceUtils.includeExternalObjects;

public class SerdeGenJavaUtils {

    public static final String WITH_RUNTIMES_SERDE = "--with-runtimes=Serde";

    public static final String WITH_RUNTIMES_BCS = "--with-runtimes=Bcs";

    public static final String DEFAULT_TEMP_YAML_FILE_EXTENSION = ".temp";

    public static final String DEFAULT_SERDEGEN_PATH = "serdegen";

    private static final String JAVA_SOURCE_CLASS_LINE_START = "public final class ";


    public static void processSerdeFormatFiles(String workingDirectory, String serdegenPath,
                                               String language,
                                               List<SerdeFormatFile> serdeFormatFiles,
                                               ObjectMapper objectMapper, String tempYamlFileExtension,
                                               Integer onlyRetainDependenciesOfLast) {
        if (workingDirectory == null || workingDirectory.isEmpty()) {
            throw new IllegalArgumentException("workingDirectory is null or empty.");
        }
        if (serdegenPath == null || serdegenPath.isEmpty()) {
            throw new IllegalArgumentException("serdegenPath is null or empty.");
        }
        if (tempYamlFileExtension == null || tempYamlFileExtension.isEmpty()) {
            tempYamlFileExtension = DEFAULT_TEMP_YAML_FILE_EXTENSION;
        }
        List<Map<String, Object>> yamlMapList = serdeFormatFiles.stream()
                .map(f -> loadYamlMap(Paths.get(f.getFormatFilePath()))).collect(Collectors.toList());
        List<Map<String, ContainerFormat>> containerFormatMapList = yamlMapList.stream()
                .map(m -> toContainerFormatMap(objectMapper, m)).collect(Collectors.toList());
        List<String> onlyRetainedNames = null;
        if (onlyRetainDependenciesOfLast != null) {
            if (onlyRetainDependenciesOfLast < 1) {
                onlyRetainDependenciesOfLast = 1;
            }
            onlyRetainedNames = new ArrayList<>();
        }

        List<Map<String, Object>> concatenatedMapList = new ArrayList<>();
        for (int i = 0; i < serdeFormatFiles.size(); i++) {
            List<Map<String, Object>> eMaps = yamlMapList.subList(0, i);
            List<Map<String, ContainerFormat>> ecMaps = containerFormatMapList.subList(0, i);
            Map<String, Object> concatenatedMap = includeExternalObjects(yamlMapList.get(i), containerFormatMapList.get(i),
                    ecMaps, eMaps);
            concatenatedMapList.add(concatenatedMap);
            if (onlyRetainDependenciesOfLast != null && i >= serdeFormatFiles.size() - onlyRetainDependenciesOfLast) {
                onlyRetainedNames.addAll(concatenatedMap.keySet());
            }
        }
        //System.out.println("onlyRetainedNames: " + onlyRetainedNames);
        if (onlyRetainDependenciesOfLast != null) {
            for (int i = 0; i < concatenatedMapList.size(); i++) {
                Map<String, Object> concatenatedMap = new HashMap<>();
                for (String n : concatenatedMapList.get(i).keySet()) {
                    if (onlyRetainedNames.contains(n)) {
                        concatenatedMap.put(n, concatenatedMapList.get(i).get(n));
                    }
                }
                concatenatedMapList.set(i, concatenatedMap);
                //System.out.println(concatenatedMap);
            }
        }

        for (int i = 0; i < serdeFormatFiles.size(); i++) {
            List<Set<String>> externalNamesList = concatenatedMapList.subList(0, i)
                    .stream().map(Map::keySet).collect(Collectors.toList());
            Map<String, Object> concatenatedMap = concatenatedMapList.get(i);
            String packageName = serdeFormatFiles.get(i).getPackageName();
            String targetSourceDirectoryPath = serdeFormatFiles.get(i).getTargetSourceDirectoryPath();
            String tmpFilePath = serdeFormatFiles.get(i).formatFilePath + tempYamlFileExtension;
            dumpToFile(Paths.get(tmpFilePath), concatenatedMap);
            if (i == 0) {
                int ec_0 = waitForSerdegenProcess(workingDirectory, serdegenPath, language, packageName, WITH_RUNTIMES_SERDE, targetSourceDirectoryPath, tmpFilePath);
                if (ec_0 != 0) throw new RuntimeException("Run serdegen command failed.");//System.out.println(ec_0);
            }
            int ec = waitForSerdegenProcess(workingDirectory, serdegenPath, language, packageName, WITH_RUNTIMES_BCS, targetSourceDirectoryPath, tmpFilePath);
            if (ec != 0) throw new RuntimeException("Run serdegen command failed.");//System.out.println(ec);
            try {
                Files.deleteIfExists(Paths.get(tmpFilePath));
            } catch (IOException e) {
                //e.printStackTrace();
                throw new RuntimeException("Delete temporary file failed.", e);
            }

            List<String> removedNames = removeDuplicatedFiles(workingDirectory, packageName,
                    targetSourceDirectoryPath, concatenatedMap, externalNamesList);

            Map<String, ContainerFormat> containerFormatMap = containerFormatMapList.get(i);
            modifyGeneratedFiles(workingDirectory, language, serdeFormatFiles, externalNamesList, containerFormatMap, i,
                    removedNames, onlyRetainedNames);
        }

//        for (int i = 0; i < serdeFormatFiles.size() - onlyRetainDependenciesOfLast; i++) {
//            String packageName = serdeFormatFiles.get(i).getPackageName();
//            String targetSourceDirectoryPath = serdeFormatFiles.get(i).getTargetSourceDirectoryPath();
//            for (String typeName : yamlMapList.get(i).keySet()) {
//                if (onlyRetainedNames.contains(typeName)) {
//                    continue;
//                }
//                Path fp = getJavaFilePathByTypeName(workingDirectory, packageName, targetSourceDirectoryPath, typeName);
//                try {
//                    Files.deleteIfExists(fp);
//                } catch (IOException e) {
//                    throw new RuntimeException("Delete file failed.", e);
//                }
//            }
//        }

    }

    private static void modifyGeneratedFiles(String workingDirectory, String language,
                                             List<SerdeFormatFile> serdeFormatFiles,
                                             List<Set<String>> externalNamesList,
                                             Map<String, ContainerFormat> containerFormatMap, int currentFileIndex, List<String> removedNames,
                                             List<String> onlyRetainedNames) {
        String packageName = serdeFormatFiles.get(currentFileIndex).getPackageName();
        String targetSourceDirectoryPath = serdeFormatFiles.get(currentFileIndex).getTargetSourceDirectoryPath();
        List<String> retainedNames = new ArrayList<>(containerFormatMap.keySet());
        retainedNames.removeAll(removedNames);
        if (onlyRetainedNames != null) {
            retainedNames.retainAll(onlyRetainedNames);
        }
        //System.out.println(retainedNames);
        retainedNames.forEach(n -> {
            List<Integer> importPackageIds = new ArrayList<>();
            for (String rn : containerFormatMap.get(n).referencedContainerTypeNames()) {
                for (int j = 0; j < externalNamesList.size(); j++) {
                    if (externalNamesList.get(j).contains(rn)) {
                        if (!importPackageIds.contains(j)) {
                            importPackageIds.add(j);
                        }
                        break;
                    }
                }
            }
            //System.out.println(n + ": " + importPackageIds);
            if (!importPackageIds.isEmpty()) {
                if ("java".equalsIgnoreCase(language)) {
                    Path pathToModify = getJavaFilePathByTypeName(workingDirectory, packageName, targetSourceDirectoryPath, n);
                    String importStr = importPackageIds.stream().map(idx ->
                            "import " + serdeFormatFiles.get(idx).getPackageName() + ".*;")
                            .reduce((s1, s2) -> s1 + System.lineSeparator() + s2).get();
                    //System.out.println(importStr);
                    String sourceCode = TextFileUtils.readTextFile(pathToModify);
                    int classLineIdx = sourceCode.indexOf(JAVA_SOURCE_CLASS_LINE_START);
                    if (classLineIdx != -1) {
                        String newSourceCode = sourceCode.substring(0, classLineIdx)
                                + importStr + System.lineSeparator() + System.lineSeparator()
                                + sourceCode.substring(classLineIdx);
                        TextFileUtils.writeTextFile(pathToModify, newSourceCode);
                    }
                } //todo other languages...
            }
        });
    }


    private static List<String> removeDuplicatedFiles(String workingDirectory, String packageName,
                                                      String targetSourceDirectoryPath,
                                                      Map<String, Object> concatenatedMap,
                                                      List<Set<String>> externalNamesList) {
        List<String> namesToRemove = new ArrayList<>(concatenatedMap.keySet());
        namesToRemove.retainAll(externalNamesList.stream().flatMap(Collection::stream).collect(Collectors.toSet()));
        //System.out.println(namesToRemove);
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

    /**
     * @param language Java, Go, etc...
     * @param moduleName module name. For java it is package name
     */
    public static int waitForSerdegenProcess(String workingDirectory, String serdegenPath,
                                             String language,
                                             String moduleName, String withRuntimesOrEmpty,
                                             String targetSrcDir, String yamlFilePath) {
        String shellPath = "/bin/sh";
        String cmdFormat = "%1$s --language %2$s --module-name %3$s %4$s --target-source-dir %5$s %6$s";
        String cmd = String.format(cmdFormat, serdegenPath, language == null ? "java" : language,
                moduleName, withRuntimesOrEmpty, targetSrcDir, yamlFilePath);
        //System.out.println(cmd);
        Process process;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(shellPath, "-c", cmd);
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
        private final String formatFilePath;
        private final String packageName;
        private final String targetSourceDirectoryPath;

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
