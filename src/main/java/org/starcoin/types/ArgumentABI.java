package org.starcoin.types;


public final class ArgumentABI {
    public final String name;
    public final TypeTag type_tag;

    public ArgumentABI(String name, TypeTag type_tag) {
        java.util.Objects.requireNonNull(name, "name must not be null");
        java.util.Objects.requireNonNull(type_tag, "type_tag must not be null");
        this.name = name;
        this.type_tag = type_tag;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_str(name);
        type_tag.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static ArgumentABI deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.name = deserializer.deserialize_str();
        builder.type_tag = TypeTag.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static ArgumentABI bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        ArgumentABI value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ArgumentABI other = (ArgumentABI) obj;
        if (!java.util.Objects.equals(this.name, other.name)) { return false; }
        if (!java.util.Objects.equals(this.type_tag, other.type_tag)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.name != null ? this.name.hashCode() : 0);
        value = 31 * value + (this.type_tag != null ? this.type_tag.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public String name;
        public TypeTag type_tag;

        public ArgumentABI build() {
            return new ArgumentABI(
                name,
                type_tag
            );
        }
    }
}
