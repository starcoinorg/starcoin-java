package org.starcoin.types.event;


import org.starcoin.types.AccountAddress;

public final class VoteChangedEvent {
    public final @com.novi.serde.Unsigned Long proposal_id;
    public final AccountAddress voter;
    public final AccountAddress proposer;
    public final Boolean agree;
    public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger vote;

    public VoteChangedEvent(@com.novi.serde.Unsigned Long proposal_id, AccountAddress voter, AccountAddress proposer, Boolean agree, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger vote) {
        java.util.Objects.requireNonNull(proposal_id, "proposal_id must not be null");
        java.util.Objects.requireNonNull(voter, "voter must not be null");
        java.util.Objects.requireNonNull(proposer, "proposer must not be null");
        java.util.Objects.requireNonNull(agree, "agree must not be null");
        java.util.Objects.requireNonNull(vote, "vote must not be null");
        this.proposal_id = proposal_id;
        this.voter = voter;
        this.proposer = proposer;
        this.agree = agree;
        this.vote = vote;
    }

    public static VoteChangedEvent deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.proposal_id = deserializer.deserialize_u64();
        builder.voter = AccountAddress.deserialize(deserializer);
        builder.proposer = AccountAddress.deserialize(deserializer);
        builder.agree = deserializer.deserialize_bool();
        builder.vote = deserializer.deserialize_u128();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static VoteChangedEvent bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        VoteChangedEvent value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u64(proposal_id);
        voter.serialize(serializer);
        proposer.serialize(serializer);
        serializer.serialize_bool(agree);
        serializer.serialize_u128(vote);
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
        VoteChangedEvent other = (VoteChangedEvent) obj;
        if (!java.util.Objects.equals(this.proposal_id, other.proposal_id)) {
            return false;
        }
        if (!java.util.Objects.equals(this.voter, other.voter)) {
            return false;
        }
        if (!java.util.Objects.equals(this.proposer, other.proposer)) {
            return false;
        }
        if (!java.util.Objects.equals(this.agree, other.agree)) {
            return false;
        }
        if (!java.util.Objects.equals(this.vote, other.vote)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.proposal_id != null ? this.proposal_id.hashCode() : 0);
        value = 31 * value + (this.voter != null ? this.voter.hashCode() : 0);
        value = 31 * value + (this.proposer != null ? this.proposer.hashCode() : 0);
        value = 31 * value + (this.agree != null ? this.agree.hashCode() : 0);
        value = 31 * value + (this.vote != null ? this.vote.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public @com.novi.serde.Unsigned Long proposal_id;
        public AccountAddress voter;
        public AccountAddress proposer;
        public Boolean agree;
        public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger vote;

        public VoteChangedEvent build() {
            return new VoteChangedEvent(
                    proposal_id,
                    voter,
                    proposer,
                    agree,
                    vote
            );
        }
    }
}
