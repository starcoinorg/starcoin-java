package org.starcoin.types.event;


import org.starcoin.types.AccountAddress;

public final class NewBlockEvent {
    public final @com.novi.serde.Unsigned Long number;
    public final AccountAddress author;
    public final @com.novi.serde.Unsigned Long timestamp;
    public final @com.novi.serde.Unsigned Long uncles;

    public NewBlockEvent(@com.novi.serde.Unsigned Long number, AccountAddress author, @com.novi.serde.Unsigned Long timestamp, @com.novi.serde.Unsigned Long uncles) {
        java.util.Objects.requireNonNull(number, "number must not be null");
        java.util.Objects.requireNonNull(author, "author must not be null");
        java.util.Objects.requireNonNull(timestamp, "timestamp must not be null");
        java.util.Objects.requireNonNull(uncles, "uncles must not be null");
        this.number = number;
        this.author = author;
        this.timestamp = timestamp;
        this.uncles = uncles;
    }

    public static NewBlockEvent deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.number = deserializer.deserialize_u64();
        builder.author = AccountAddress.deserialize(deserializer);
        builder.timestamp = deserializer.deserialize_u64();
        builder.uncles = deserializer.deserialize_u64();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static NewBlockEvent bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        NewBlockEvent value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u64(number);
        author.serialize(serializer);
        serializer.serialize_u64(timestamp);
        serializer.serialize_u64(uncles);
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
        NewBlockEvent other = (NewBlockEvent) obj;
        if (!java.util.Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!java.util.Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!java.util.Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        if (!java.util.Objects.equals(this.uncles, other.uncles)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.number != null ? this.number.hashCode() : 0);
        value = 31 * value + (this.author != null ? this.author.hashCode() : 0);
        value = 31 * value + (this.timestamp != null ? this.timestamp.hashCode() : 0);
        value = 31 * value + (this.uncles != null ? this.uncles.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public @com.novi.serde.Unsigned Long number;
        public AccountAddress author;
        public @com.novi.serde.Unsigned Long timestamp;
        public @com.novi.serde.Unsigned Long uncles;

        public NewBlockEvent build() {
            return new NewBlockEvent(
                    number,
                    author,
                    timestamp,
                    uncles
            );
        }
    }
}
