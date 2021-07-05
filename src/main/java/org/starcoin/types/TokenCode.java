package org.starcoin.types;


public final class TokenCode {
    public final AccountAddress address;
    public final String module;
    public final String name;

    public TokenCode(AccountAddress address, String module, String name) {
        java.util.Objects.requireNonNull(address, "address must not be null");
        java.util.Objects.requireNonNull(module, "module must not be null");
        java.util.Objects.requireNonNull(name, "name must not be null");
        this.address = address;
        this.module = module;
        this.name = name;
    }

    public static TokenCode deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.address = AccountAddress.deserialize(deserializer);
        builder.module = deserializer.deserialize_str();
        builder.name = deserializer.deserialize_str();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static TokenCode bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        TokenCode value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        address.serialize(serializer);
        serializer.serialize_str(module);
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
        TokenCode other = (TokenCode) obj;
        if (!java.util.Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!java.util.Objects.equals(this.module, other.module)) {
            return false;
        }
        if (!java.util.Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.address != null ? this.address.hashCode() : 0);
        value = 31 * value + (this.module != null ? this.module.hashCode() : 0);
        value = 31 * value + (this.name != null ? this.name.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public AccountAddress address;
        public String module;
        public String name;

        public TokenCode build() {
            return new TokenCode(
                    address,
                    module,
                    name
            );
        }
    }
}
