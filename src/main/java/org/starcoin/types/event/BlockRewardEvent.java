package org.starcoin.types.event;


import org.starcoin.types.AccountAddress;

public final class BlockRewardEvent {
    public final @com.novi.serde.Unsigned Long numbblock_numberer;
    public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger block_reward;
    public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger gas_fees;
    public final AccountAddress miner;

    public BlockRewardEvent(@com.novi.serde.Unsigned Long numbblock_numberer, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger block_reward, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger gas_fees, AccountAddress miner) {
        java.util.Objects.requireNonNull(numbblock_numberer, "numbblock_numberer must not be null");
        java.util.Objects.requireNonNull(block_reward, "block_reward must not be null");
        java.util.Objects.requireNonNull(gas_fees, "gas_fees must not be null");
        java.util.Objects.requireNonNull(miner, "miner must not be null");
        this.numbblock_numberer = numbblock_numberer;
        this.block_reward = block_reward;
        this.gas_fees = gas_fees;
        this.miner = miner;
    }

    public static BlockRewardEvent deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.numbblock_numberer = deserializer.deserialize_u64();
        builder.block_reward = deserializer.deserialize_u128();
        builder.gas_fees = deserializer.deserialize_u128();
        builder.miner = AccountAddress.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static BlockRewardEvent bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        BlockRewardEvent value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u64(numbblock_numberer);
        serializer.serialize_u128(block_reward);
        serializer.serialize_u128(gas_fees);
        miner.serialize(serializer);
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
        BlockRewardEvent other = (BlockRewardEvent) obj;
        if (!java.util.Objects.equals(this.numbblock_numberer, other.numbblock_numberer)) {
            return false;
        }
        if (!java.util.Objects.equals(this.block_reward, other.block_reward)) {
            return false;
        }
        if (!java.util.Objects.equals(this.gas_fees, other.gas_fees)) {
            return false;
        }
        if (!java.util.Objects.equals(this.miner, other.miner)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.numbblock_numberer != null ? this.numbblock_numberer.hashCode() : 0);
        value = 31 * value + (this.block_reward != null ? this.block_reward.hashCode() : 0);
        value = 31 * value + (this.gas_fees != null ? this.gas_fees.hashCode() : 0);
        value = 31 * value + (this.miner != null ? this.miner.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public @com.novi.serde.Unsigned Long numbblock_numberer;
        public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger block_reward;
        public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger gas_fees;
        public AccountAddress miner;

        public BlockRewardEvent build() {
            return new BlockRewardEvent(
                    numbblock_numberer,
                    block_reward,
                    gas_fees,
                    miner
            );
        }
    }
}
