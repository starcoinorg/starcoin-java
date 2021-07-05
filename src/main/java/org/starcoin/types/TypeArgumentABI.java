package org.starcoin.types;


public final class TypeArgumentABI {
    public final String name;

    public TypeArgumentABI(String name) {
        java.util.Objects.requireNonNull(name, "name must not be null");
        this.name = name;
    }

    public static TypeArgumentABI deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.name = deserializer.deserialize_str();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static TypeArgumentABI bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        TypeArgumentABI value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_str(name);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        TypeArgumentABI other = (TypeArgumentABI) obj;
        if (!java.util.Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.name != null ? this.name.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public String name;

        public TypeArgumentABI build() {
            return new TypeArgumentABI(
                    name
            );
        }
    }
}
