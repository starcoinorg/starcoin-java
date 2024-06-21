package org.starcoin.types;


import com.novi.serde.Unsigned;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class BlockMetadata {
    public final HashValue parent_hash;
    public final @com.novi.serde.Unsigned Long timestamp;
    public final AccountAddress author;
    public final java.util.Optional<AuthenticationKey> author_auth_key;
    public final @com.novi.serde.Unsigned Long uncles;
    public final @com.novi.serde.Unsigned Long number;
    public final ChainId chain_id;
    public final @com.novi.serde.Unsigned Long parent_gas_used;
    public final List<HashValue> parents_hash;

    public BlockMetadata(
            HashValue parent_hash,
            @Unsigned Long timestamp,
            AccountAddress author,
            Optional<AuthenticationKey> author_auth_key,
            @Unsigned Long uncles,
            @Unsigned Long number,
            ChainId chain_id,
            @Unsigned Long parent_gas_used,
            List<HashValue> parents_hash
    ) {
        java.util.Objects.requireNonNull(parent_hash, "parent_hash must not be null");
        java.util.Objects.requireNonNull(timestamp, "timestamp must not be null");
        java.util.Objects.requireNonNull(author, "author must not be null");
        java.util.Objects.requireNonNull(author_auth_key, "author_auth_key must not be null");
        java.util.Objects.requireNonNull(uncles, "uncles must not be null");
        java.util.Objects.requireNonNull(number, "number must not be null");
        java.util.Objects.requireNonNull(chain_id, "chain_id must not be null");
        java.util.Objects.requireNonNull(parent_gas_used, "parent_gas_used must not be null");
        this.parent_hash = parent_hash;
        this.timestamp = timestamp;
        this.author = author;
        this.author_auth_key = author_auth_key;
        this.uncles = uncles;
        this.number = number;
        this.chain_id = chain_id;
        this.parent_gas_used = parent_gas_used;
        this.parents_hash = parents_hash;
    }

    public static BlockMetadata deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.parent_hash = HashValue.deserialize(deserializer);
        builder.timestamp = deserializer.deserialize_u64();
        builder.author = AccountAddress.deserialize(deserializer);
        builder.author_auth_key = TraitHelpers.deserialize_option_AuthenticationKey(deserializer);
        builder.uncles = deserializer.deserialize_u64();
        builder.number = deserializer.deserialize_u64();
        builder.chain_id = ChainId.deserialize(deserializer);
        builder.parent_gas_used = deserializer.deserialize_u64();
        builder.parents_hash = TraitHelpers.deserialize_vector_hashvalue(deserializer);

        return builder.build();
    }

    public static BlockMetadata bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        BlockMetadata value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        parent_hash.serialize(serializer);
        serializer.serialize_u64(timestamp);
        author.serialize(serializer);
        TraitHelpers.serialize_option_AuthenticationKey(author_auth_key, serializer);
        serializer.serialize_u64(uncles);
        serializer.serialize_u64(number);
        chain_id.serialize(serializer);
        serializer.serialize_u64(parent_gas_used);
        TraitHelpers.serialize_vector_hashvalue(parents_hash, serializer);
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
        BlockMetadata other = (BlockMetadata) obj;
        if (!java.util.Objects.equals(this.parent_hash, other.parent_hash)) {
            return false;
        }
        if (!java.util.Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        if (!java.util.Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!java.util.Objects.equals(this.author_auth_key, other.author_auth_key)) {
            return false;
        }
        if (!java.util.Objects.equals(this.uncles, other.uncles)) {
            return false;
        }
        if (!java.util.Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!java.util.Objects.equals(this.chain_id, other.chain_id)) {
            return false;
        }
        if (!java.util.Objects.equals(this.parent_gas_used, other.parent_gas_used)) {
            return false;
        }
        return java.util.Objects.equals(this.parents_hash, other.parents_hash);
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.parent_hash != null ? this.parent_hash.hashCode() : 0);
        value = 31 * value + (this.timestamp != null ? this.timestamp.hashCode() : 0);
        value = 31 * value + (this.author != null ? this.author.hashCode() : 0);
        value = 31 * value + (this.author_auth_key != null ? this.author_auth_key.hashCode() : 0);
        value = 31 * value + (this.uncles != null ? this.uncles.hashCode() : 0);
        value = 31 * value + (this.number != null ? this.number.hashCode() : 0);
        value = 31 * value + (this.chain_id != null ? this.chain_id.hashCode() : 0);
        value = 31 * value + (this.parent_gas_used != null ? this.parent_gas_used.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public HashValue parent_hash;
        public @com.novi.serde.Unsigned Long timestamp;
        public AccountAddress author;
        public java.util.Optional<AuthenticationKey> author_auth_key;
        public @com.novi.serde.Unsigned Long uncles;
        public @com.novi.serde.Unsigned Long number;
        public ChainId chain_id;
        public @com.novi.serde.Unsigned Long parent_gas_used;
        public List<HashValue> parents_hash;

        public BlockMetadata build() {
            return new BlockMetadata(
                    parent_hash,
                    timestamp,
                    author,
                    author_auth_key,
                    uncles,
                    number,
                    chain_id,
                    parent_gas_used,
                    parents_hash
            );
        }
    }
}
