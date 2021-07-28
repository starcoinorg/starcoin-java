# Starcoin Java SDK

## 从 Serde 数据格式生成 Java 代码

虽然已经存在一个 Rust 版的 [`serdegen`](https://github.com/novifinancial/serde-reflection/tree/master/serde-generate) 工具，支持将 [`serde-reflection`](https://crates.io/crates/serde-reflection) 从 Rust 提取出来的数据格式（一般是 YAML 文档）编译为其他语言的类型定义以及序列化/反序列化方法。 但是这个工具目前存在一些问题：

* 生成代码需要将所有关联的数据结构的描述（format）需要放到同一个 YAML 文件中，不能实现一个文件中的数据格式“引用”其他文件的数据格式。这会造成在不同的 YAML 文件中存在重复的代码，不利于维护。
* 以生成 Java 代码为例，所有生成的代码都在一个 package（可以理解为目录、命名空间、模块）中。我们有必要将标准库（stdlib）中定义的数据结构打包在 starcoin-java 项目（JAR 包）中，特定应用如 starswap 定义的数据结构打包到应用自己的项目中，并引用（依赖）starcoin-java 的代码即可。如果我们能将数据结构的 format 切分到不同的文件中，则可以按照文件各自指定生成代码所在的 package。

所以 `org.starcoin.serde.format.cli.SerdeGenJava` 这个工具的存在就是为了解决以上问题。这是一个 Java 开发的 serdegen 外围工具，运行它之前仍然需要先安装好（Rust 版的）serdegen，保证在 shell 中可以正常执行 serdegen 命令。

SerdeGenJava 工具的用法：

```
Usage: <main class> [options] 
      {PATH_TO_SERDE_FORMAT_FILE}:{JAVA_PACKAGE_NAME}...(List items separated by 
      whitespaces) 
  Options:
    --help
      Prints the usage
    --onlyRetainDependenciesOfLast
      Only retains dependencies of last N file(s)
    --serdegenPath, -p
      Serdegen command path
      Default: serdegen
    --targetSourceDirectoryPath
      Target source directory path
      Default: ./src/main/java
    --tempYamlFileExtension
      Temporary generated YAML file extension
      Default: .temp
  * --workingDirectory, -w
      Working directory
```

以 Maven 调用该工具的示例：

```
mvn exec:java -Dexec.mainClass="org.starcoin.serde.format.cli.SerdeGenJava" -X -Dexec.args="-w /{PATH_TO_MY_WORKING_REPOS_DIR}/starcoinorg/starswap-api --targetSourceDirectoryPath ./src/test/java /{PATH_TO_MY_WORKING_REPOS_DIR}/starcoinorg/starcoin/etc/starcoin_types.yml:org.starcoin.types /{PATH_TO_MY_WORKING_REPOS_DIR}/starcoinorg/starcoin/etc/onchain_events.yml:org.starcoin.types.event /{PATH_TO_MY_WORKING_REPOS_DIR}/starcoinorg/starswap-api/generate-format/starswap_types.yaml:org.starcoin.starswap.types"

```

### SerdeGenJava 工具的设计思路

我们可以给 SerdeGenJava 传入一个数据格式文件的列表，列表中排列在后面的文件，其数据格式可以引用（依赖）前面的文件的数据格式。SerdeGenJava 工具处理文件列表的方法如下：

1. 将数据格式文件（YAML）反序列化为强类型（或者说静态类型）的 Java 对象，取得数据格式（或者说它描述的数据结构）之间的依赖关系。PS：这些 YAML 文件其实是一些 Rust 定义的数据结构序列化的结果，Rust 语言和 Java 语言的特性并不是一一对应的，比如 Rust 的 enum 和 Java 的 enum 就基本是两回事，怎么将这些 YAML/JSON 文档反序列化为强类型的 Java 对象，这个工具提供了一些可能后面可以借鉴的思路。

2. 将每个 YAML 文件中的数据格式，以及它依赖的其他文件的数据格式，合并到一个临时文件中。然后调用 Rust 版的 serdegen 工具，使用这个临时文件生成 Java 代码。

3. 删除重复生成的 Java 文件。也就是说，上一步可能会生成一些几乎完全一样的 Java 类型的代码，只是它们位于不同的 package（目录）中。

4. 按照数据结构的依赖关系，修改生成的源代码。给那些依赖其他 package 中的数据结构的 Java 代码增加必要的 import 语句（引入所依赖的包）。

