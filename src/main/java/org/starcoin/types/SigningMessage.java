package org.starcoin.types;


import com.novi.serde.SerializationError;

import static org.starcoin.constant.Constant.STARCOIN_HASH_PREFIX;

public final class SigningMessage {
    public static final String CLASS_NAME = "SigningMessage";
    public static final byte[] PREFIX_BYTES = HashValue.sha3Of((STARCOIN_HASH_PREFIX + CLASS_NAME).getBytes()).value.content();
    public final java.util.List<@com.novi.serde.Unsigned Byte> value;

    public SigningMessage(java.util.List<@com.novi.serde.Unsigned Byte> value) {
        java.util.Objects.requireNonNull(value, "value must not be null");
        this.value = value;
    }

    public static byte[] hashBytes(SigningMessage signingMessage) throws SerializationError {
        return com.google.common.primitives.Bytes
                .concat(PREFIX_BYTES, signingMessage.bcsSerialize());
    }

    public static SigningMessage deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.value = TraitHelpers.deserialize_vector_u8(deserializer);
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

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        TraitHelpers.serialize_vector_u8(value, serializer);
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
        SigningMessage other = (SigningMessage) obj;
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
        public java.util.List<@com.novi.serde.Unsigned Byte> value;

        public SigningMessage build() {
            return new SigningMessage(
                    value
            );
        }
    }
}
