package org.starcoin.types;


public final class EventHandle {
    public final @com.novi.serde.Unsigned Long count;
    public final EventKey key;

    public EventHandle(@com.novi.serde.Unsigned Long count, EventKey key) {
        java.util.Objects.requireNonNull(count, "count must not be null");
        java.util.Objects.requireNonNull(key, "key must not be null");
        this.count = count;
        this.key = key;
    }

    public static EventHandle deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.count = deserializer.deserialize_u64();
        builder.key = EventKey.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static EventHandle bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        EventHandle value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u64(count);
        key.serialize(serializer);
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
        EventHandle other = (EventHandle) obj;
        if (!java.util.Objects.equals(this.count, other.count)) {
            return false;
        }
        if (!java.util.Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.count != null ? this.count.hashCode() : 0);
        value = 31 * value + (this.key != null ? this.key.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public @com.novi.serde.Unsigned Long count;
        public EventKey key;

        public EventHandle build() {
            return new EventHandle(
                    count,
                    key
            );
        }
    }
}
