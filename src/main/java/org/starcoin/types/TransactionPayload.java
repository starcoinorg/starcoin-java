package org.starcoin.types;


public abstract class TransactionPayload {

    public static TransactionPayload deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        int index = deserializer.deserialize_variant_index();
        switch (index) {
            case 0:
                return Script.load(deserializer);
            case 1:
                return Package.load(deserializer);
            case 2:
                return ScriptFunction.load(deserializer);
            default:
                throw new com.novi.serde.DeserializationError("Unknown variant index for TransactionPayload: " + index);
        }
    }

    public static TransactionPayload bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        TransactionPayload value = deserialize(deserializer);
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

    public static final class Script extends TransactionPayload {
        public final org.starcoin.types.Script value;

        public Script(org.starcoin.types.Script value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        static Script load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = org.starcoin.types.Script.deserialize(deserializer);
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
            Script other = (Script) obj;
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
            public org.starcoin.types.Script value;

            public Script build() {
                return new Script(
                        value
                );
            }
        }
    }

    public static final class Package extends TransactionPayload {
        public final org.starcoin.types.Package value;

        public Package(org.starcoin.types.Package value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        static Package load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = org.starcoin.types.Package.deserialize(deserializer);
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
            Package other = (Package) obj;
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
            public org.starcoin.types.Package value;

            public Package build() {
                return new Package(
                        value
                );
            }
        }
    }

    public static final class ScriptFunction extends TransactionPayload {
        public final org.starcoin.types.ScriptFunction value;

        public ScriptFunction(org.starcoin.types.ScriptFunction value) {
            java.util.Objects.requireNonNull(value, "value must not be null");
            this.value = value;
        }

        static ScriptFunction load(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
            deserializer.increase_container_depth();
            Builder builder = new Builder();
            builder.value = org.starcoin.types.ScriptFunction.deserialize(deserializer);
            deserializer.decrease_container_depth();
            return builder.build();
        }

        public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
            serializer.increase_container_depth();
            serializer.serialize_variant_index(2);
            value.serialize(serializer);
            serializer.decrease_container_depth();
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ScriptFunction other = (ScriptFunction) obj;
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
            public org.starcoin.types.ScriptFunction value;

            public ScriptFunction build() {
                return new ScriptFunction(
                        value
                );
            }
        }
    }
}

