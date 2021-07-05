package org.starcoin.types.event;


import org.starcoin.types.AccountAddress;
import org.starcoin.types.HashValue;

public final class UpgradeEvent {
    public final AccountAddress package_address;
    public final HashValue package_hash;
    public final @com.novi.serde.Unsigned Long version;

    public UpgradeEvent(AccountAddress package_address, HashValue package_hash, @com.novi.serde.Unsigned Long version) {
        java.util.Objects.requireNonNull(package_address, "package_address must not be null");
        java.util.Objects.requireNonNull(package_hash, "package_hash must not be null");
        java.util.Objects.requireNonNull(version, "version must not be null");
        this.package_address = package_address;
        this.package_hash = package_hash;
        this.version = version;
    }

    public static UpgradeEvent deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.package_address = AccountAddress.deserialize(deserializer);
        builder.package_hash = HashValue.deserialize(deserializer);
        builder.version = deserializer.deserialize_u64();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static UpgradeEvent bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        UpgradeEvent value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        package_address.serialize(serializer);
        package_hash.serialize(serializer);
        serializer.serialize_u64(version);
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
        UpgradeEvent other = (UpgradeEvent) obj;
        if (!java.util.Objects.equals(this.package_address, other.package_address)) {
            return false;
        }
        if (!java.util.Objects.equals(this.package_hash, other.package_hash)) {
            return false;
        }
        if (!java.util.Objects.equals(this.version, other.version)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.package_address != null ? this.package_address.hashCode() : 0);
        value = 31 * value + (this.package_hash != null ? this.package_hash.hashCode() : 0);
        value = 31 * value + (this.version != null ? this.version.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public AccountAddress package_address;
        public HashValue package_hash;
        public @com.novi.serde.Unsigned Long version;

        public UpgradeEvent build() {
            return new UpgradeEvent(
                    package_address,
                    package_hash,
                    version
            );
        }
    }
}
