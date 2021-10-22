package org.starcoin.serde.format.cli;

import com.beust.jcommander.JCommander;
import org.starcoin.serde.format.utils.SerdeGenJavaUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.starcoin.serde.format.jackson.utils.MappingUtils.getObjectMapper;

public class SerdeGenJava {

    public static void main(String[] args) {
        SerdeGenJavaArgs serdeGenJavaArgs = new SerdeGenJavaArgs();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(serdeGenJavaArgs)
                .build();
        jCommander.parse(args);
        //System.out.println(serdeGenJavaArgs);
        if (serdeGenJavaArgs.isHelp()) {
            jCommander.usage();
            return;
        }

        String targetSourceDirectoryPath = serdeGenJavaArgs.getTargetSourceDirectoryPath();
        List<SerdeGenJavaUtils.SerdeFormatFile> serdeFormatFiles = serdeGenJavaArgs.getParameters().stream().map(i -> {
            String[] fields = i.split(":");
            if (fields.length < 2) throw new IllegalArgumentException("List item error.");
            String formatFilePath = fields[0];
            String packageName = fields[1];
            return new SerdeGenJavaUtils.SerdeFormatFile(formatFilePath, packageName, targetSourceDirectoryPath);
        }).collect(Collectors.toList());

        SerdeGenJavaUtils.processSerdeFormatFiles(serdeGenJavaArgs.getWorkingDirectory(),
                serdeGenJavaArgs.getSerdegenPath(), serdeGenJavaArgs.getLanguage(), serdeFormatFiles, getObjectMapper(),
                serdeGenJavaArgs.getTempYamlFileExtension(), serdeGenJavaArgs.getOnlyRetainDependenciesOfLast());

        System.out.println("All seem ok.");
    }
}
