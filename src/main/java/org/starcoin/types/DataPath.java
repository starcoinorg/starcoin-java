package org.starcoin.types;


public abstract class DataPath {

    public static DataPath deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0:
                return Code.load(deserializer);
            case 1:
                return Resource.load(deserializer);
            default:
                throw new com.novi.serde.DeserializationError("Unknown variant index for DataPath: " + index);
        }
    }

    public static DataPath bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        DataPath value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    abstract public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError;

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static final class Code extends DataPath {
        public final Identifier value;

        public Code(Identifier value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        static Code load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = Identifier.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(0);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Code other = (Code) obj;
            if (!java.util.Objects.equals(this.value, other.value)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public Identifier value;

            public Code build() {
                return new Code(
                        value
                );
            }
        }
    }

    public static final class Resource extends DataPath {
        public final StructTag value;

        public Resource(StructTag value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        static Resource load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = StructTag.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(1);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Resource other = (Resource) obj;
            if (!java.util.Objects.equals(this.value, other.value)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public StructTag value;

            public Resource build() {
                return new Resource(
                        value
                );
            }
        }
    }
}

