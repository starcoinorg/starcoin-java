package org.starcoin.serde.format.cli;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

import static org.starcoin.serde.format.utils.SerdeGenJavaUtils.DEFAULT_SERDEGEN_PATH;
import static org.starcoin.serde.format.utils.SerdeGenJavaUtils.DEFAULT_TEMP_YAML_FILE_EXTENSION;

public class SerdeGenJavaArgs {
    @Parameter(names = "--help", description = "Prints the usage", help = true)
    private boolean help;

    @Parameter(description = "{PATH_TO_SERDE_FORMAT_FILE}:{JAVA_PACKAGE_NAME}...(List separated by whitespace)", required = true)
    private List<String> parameters = new ArrayList<>();

//    @Parameter(names = { "-log", "-verbose" }, description = "Level of verbosity")
//    private Integer verbose = 1;

    @Parameter(names = {"--workingDirectory", "-w"}, description = "Working directory", required = true)
    private String workingDirectory;

    @Parameter(names = {"--serdegenPath", "-p"}, description = "Serdegen command path")
    private String serdegenPath = DEFAULT_SERDEGEN_PATH;

    @Parameter(names = "--targetSourceDirectoryPath", description = "Target source directory path")
    private String targetSourceDirectoryPath = "./src/main/java";
//    @Parameter(names = "-debug", description = "Debug mode")
//    private boolean debug = false;

    @Parameter(names = "--tempYamlFileExtension", description = "Temporary generated YAML file extension")
    private String tempYamlFileExtension = DEFAULT_TEMP_YAML_FILE_EXTENSION;

    @Parameter(names = "--onlyRetainDependenciesOfLast", description = "Only retain dependencies of last N file(s)")
    private Integer onlyRetainDependenciesOfLast;

    public List<String> getParameters() {
        return parameters;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public String getSerdegenPath() {
        return serdegenPath;
    }

    public String getTempYamlFileExtension() {
        return tempYamlFileExtension;
    }

    public Integer getOnlyRetainDependenciesOfLast() {
        return onlyRetainDependenciesOfLast;
    }

    public boolean isHelp() {
        return help;
    }

    public String getTargetSourceDirectoryPath() {
        return targetSourceDirectoryPath;
    }

    @Override
    public String toString() {
        return "SerdeGenJavaArgs{" +
                "help=" + help +
                ", parameters=" + parameters +
                ", workingDirectory='" + workingDirectory + '\'' +
                ", serdegenPath='" + serdegenPath + '\'' +
                ", targetSourceDirectoryPath='" + targetSourceDirectoryPath + '\'' +
                ", tempYamlFileExtension='" + tempYamlFileExtension + '\'' +
                ", onlyRetainDependenciesOfLast=" + onlyRetainDependenciesOfLast +
                '}';
    }
}
