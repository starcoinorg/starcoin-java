package org.starcoin.types;


public final class AccountResource {
    public final java.util.List<@com.novi.serde.Unsigned Byte> authentication_key;
    public final java.util.Optional<WithdrawCapabilityResource> withdrawal_capability;
    public final java.util.Optional<KeyRotationCapabilityResource> key_rotation_capability;
    public final EventHandle withdraw_events;
    public final EventHandle deposit_events;
    public final EventHandle accept_token_events;
    public final @com.novi.serde.Unsigned Long sequence_number;

    public AccountResource(java.util.List<@com.novi.serde.Unsigned Byte> authentication_key, java.util.Optional<WithdrawCapabilityResource> withdrawal_capability, java.util.Optional<KeyRotationCapabilityResource> key_rotation_capability, EventHandle withdraw_events, EventHandle deposit_events, EventHandle accept_token_events, @com.novi.serde.Unsigned Long sequence_number) {
        java.util.Objects.requireNonNull(authentication_key, "authentication_key must not be null");
        java.util.Objects.requireNonNull(withdrawal_capability, "withdrawal_capability must not be null");
        java.util.Objects.requireNonNull(key_rotation_capability, "key_rotation_capability must not be null");
        java.util.Objects.requireNonNull(withdraw_events, "withdraw_events must not be null");
        java.util.Objects.requireNonNull(deposit_events, "deposit_events must not be null");
        java.util.Objects.requireNonNull(accept_token_events, "accept_token_events must not be null");
        java.util.Objects.requireNonNull(sequence_number, "sequence_number must not be null");
        this.authentication_key = authentication_key;
        this.withdrawal_capability = withdrawal_capability;
        this.key_rotation_capability = key_rotation_capability;
        this.withdraw_events = withdraw_events;
        this.deposit_events = deposit_events;
        this.accept_token_events = accept_token_events;
        this.sequence_number = sequence_number;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        TraitHelpers.serialize_vector_u8(authentication_key, serializer);
        TraitHelpers.serialize_option_WithdrawCapabilityResource(withdrawal_capability, serializer);
        TraitHelpers.serialize_option_KeyRotationCapabilityResource(key_rotation_capability, serializer);
        withdraw_events.serialize(serializer);
        deposit_events.serialize(serializer);
        accept_token_events.serialize(serializer);
        serializer.serialize_u64(sequence_number);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static AccountResource deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.authentication_key = TraitHelpers.deserialize_vector_u8(deserializer);
        builder.withdrawal_capability = TraitHelpers.deserialize_option_WithdrawCapabilityResource(deserializer);
        builder.key_rotation_capability = TraitHelpers.deserialize_option_KeyRotationCapabilityResource(deserializer);
        builder.withdraw_events = EventHandle.deserialize(deserializer);
        builder.deposit_events = EventHandle.deserialize(deserializer);
        builder.accept_token_events = EventHandle.deserialize(deserializer);
        builder.sequence_number = deserializer.deserialize_u64();
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static AccountResource bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        AccountResource value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AccountResource other = (AccountResource) obj;
        if (!java.util.Objects.equals(this.authentication_key, other.authentication_key)) { return false; }
        if (!java.util.Objects.equals(this.withdrawal_capability, other.withdrawal_capability)) { return false; }
        if (!java.util.Objects.equals(this.key_rotation_capability, other.key_rotation_capability)) { return false; }
        if (!java.util.Objects.equals(this.withdraw_events, other.withdraw_events)) { return false; }
        if (!java.util.Objects.equals(this.deposit_events, other.deposit_events)) { return false; }
        if (!java.util.Objects.equals(this.accept_token_events, other.accept_token_events)) { return false; }
        if (!java.util.Objects.equals(this.sequence_number, other.sequence_number)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.authentication_key != null ? this.authentication_key.hashCode() : 0);
        value = 31 * value + (this.withdrawal_capability != null ? this.withdrawal_capability.hashCode() : 0);
        value = 31 * value + (this.key_rotation_capability != null ? this.key_rotation_capability.hashCode() : 0);
        value = 31 * value + (this.withdraw_events != null ? this.withdraw_events.hashCode() : 0);
        value = 31 * value + (this.deposit_events != null ? this.deposit_events.hashCode() : 0);
        value = 31 * value + (this.accept_token_events != null ? this.accept_token_events.hashCode() : 0);
        value = 31 * value + (this.sequence_number != null ? this.sequence_number.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public java.util.List<@com.novi.serde.Unsigned Byte> authentication_key;
        public java.util.Optional<WithdrawCapabilityResource> withdrawal_capability;
        public java.util.Optional<KeyRotationCapabilityResource> key_rotation_capability;
        public EventHandle withdraw_events;
        public EventHandle deposit_events;
        public EventHandle accept_token_events;
        public @com.novi.serde.Unsigned Long sequence_number;

        public AccountResource build() {
            return new AccountResource(
                authentication_key,
                withdrawal_capability,
                key_rotation_capability,
                withdraw_events,
                deposit_events,
                accept_token_events,
                sequence_number
            );
        }
    }
}
