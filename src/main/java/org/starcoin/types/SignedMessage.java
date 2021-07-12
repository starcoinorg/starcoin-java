package org.starcoin.types;


public final class SignedMessage {
    public final AccountAddress account;
    public final SigningMessage message;
    public final TransactionAuthenticator authenticator;

    public SignedMessage(AccountAddress account, SigningMessage message, TransactionAuthenticator authenticator) {
        java.util.Objects.requireNonNull(account, "account must not be null");
        java.util.Objects.requireNonNull(message, "message must not be null");
        java.util.Objects.requireNonNull(authenticator, "authenticator must not be null");
        this.account = account;
        this.message = message;
        this.authenticator = authenticator;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        account.serialize(serializer);
        message.serialize(serializer);
        authenticator.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static SignedMessage deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.account = AccountAddress.deserialize(deserializer);
        builder.message = SigningMessage.deserialize(deserializer);
        builder.authenticator = TransactionAuthenticator.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static SignedMessage bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        SignedMessage value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SignedMessage other = (SignedMessage) obj;
        if (!java.util.Objects.equals(this.account, other.account)) { return false; }
        if (!java.util.Objects.equals(this.message, other.message)) { return false; }
        if (!java.util.Objects.equals(this.authenticator, other.authenticator)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.account != null ? this.account.hashCode() : 0);
        value = 31 * value + (this.message != null ? this.message.hashCode() : 0);
        value = 31 * value + (this.authenticator != null ? this.authenticator.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public AccountAddress account;
        public SigningMessage message;
        public TransactionAuthenticator authenticator;

        public SignedMessage build() {
            return new SignedMessage(
                account,
                message,
                authenticator
            );
        }
    }
}
