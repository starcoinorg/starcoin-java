# Starcoin Java SDK

starcoin-java
[![GitHub Action](https://github.com/starcoinorg/starcoin-java/workflows/Build/badge.svg)](https://github.com/starcoinorg/starcoin-java/actions?query=workflow%3A%22Build%22)

Starcoin Java SDK implementation.

**Maven**

```xml

<dependency>
    <groupId>org.starcoin</groupId>
    <artifactId>sdk</artifactId>
    <version>1.1.10</version>
</dependency>
```

## Content

### BCS Serializer and Deserializer

Inside the [bcs](./src/main/java/com/novi) package, there are universal tools for BCS Serializer and Deserializer.

### JSON-RPC client implements

Inside the [api](src/main/java/org/starcoin/api) package, there are JSON-RPC client implements of some common
interfaces.

### SerdeGenJava [SerdeGenJava](./src/main/java/org/starcoin/serde/format/README.md)

A tools of generate java code from serde format file.

## Version History

## License

starcoin-java is licensed as [Apache 2.0](./LICENSE).