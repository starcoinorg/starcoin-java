package org.starcoin.types.event;


import org.starcoin.types.TokenCode;

public final class AcceptTokenEvent {
    public final TokenCode token_code;

    public AcceptTokenEvent(TokenCode token_code) {
        java.util.Objects.requireNonNull(token_code, "token_code must not be null");
        this.token_code = token_code;
    }

    public static AcceptTokenEvent deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.token_code = TokenCode.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static AcceptTokenEvent bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        AcceptTokenEvent value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        token_code.serialize(serializer);
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
        AcceptTokenEvent other = (AcceptTokenEvent) obj;
        if (!java.util.Objects.equals(this.token_code, other.token_code)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.token_code != null ? this.token_code.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public TokenCode token_code;

        public AcceptTokenEvent build() {
            return new AcceptTokenEvent(
                    token_code
            );
        }
    }
}
