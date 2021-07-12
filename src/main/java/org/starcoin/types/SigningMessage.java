package org.starcoin.types;


public final class SigningMessage {
    public final java.util.List<@com.novi.serde.Unsigned Byte> message;

    public SigningMessage(java.util.List<@com.novi.serde.Unsigned Byte> message) {
        java.util.Objects.requireNonNull(message, "message must not be null");
        this.message = message;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        TraitHelpers.serialize_vector_u8(message, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static SigningMessage deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.message = TraitHelpers.deserialize_vector_u8(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static SigningMessage bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        SigningMessage value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SigningMessage other = (SigningMessage) obj;
        if (!java.util.Objects.equals(this.message, other.message)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.message != null ? this.message.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public java.util.List<@com.novi.serde.Unsigned Byte> message;

        public SigningMessage build() {
            return new SigningMessage(
                message
            );
        }
    }
}
