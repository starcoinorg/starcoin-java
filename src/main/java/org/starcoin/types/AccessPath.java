package org.starcoin.types;


public final class AccessPath {
    public final AccountAddress field0;
    public final DataPath field1;

    public AccessPath(AccountAddress field0, DataPath field1) {
        java.util.Objects.requireNonNull(field0, "field0 must not be null");
        java.util.Objects.requireNonNull(field1, "field1 must not be null");
        this.field0 = field0;
        this.field1 = field1;
    }

    public static AccessPath deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.field0 = AccountAddress.deserialize(deserializer);
        builder.field1 = DataPath.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static AccessPath bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        AccessPath value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        field0.serialize(serializer);
        field1.serialize(serializer);
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
        AccessPath other = (AccessPath) obj;
        if (!java.util.Objects.equals(this.field0, other.field0)) {
            return false;
        }
        if (!java.util.Objects.equals(this.field1, other.field1)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.field0 != null ? this.field0.hashCode() : 0);
        value = 31 * value + (this.field1 != null ? this.field1.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public AccountAddress field0;
        public DataPath field1;

        public AccessPath build() {
            return new AccessPath(
                    field0,
                    field1
            );
        }
    }
}
