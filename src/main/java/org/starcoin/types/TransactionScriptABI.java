package org.starcoin.types;


public final class TransactionScriptABI {
    public final String name;
    public final String doc;
    public final com.novi.serde.Bytes code;
    public final java.util.List<TypeArgumentABI> ty_args;
    public final java.util.List<ArgumentABI> args;

    public TransactionScriptABI(String name, String doc, com.novi.serde.Bytes code, java.util.List<TypeArgumentABI> ty_args, java.util.List<ArgumentABI> args) {
        java.util.Objects.requireNonNull(name, "name must not be null");
        java.util.Objects.requireNonNull(doc, "doc must not be null");
        java.util.Objects.requireNonNull(code, "code must not be null");
        java.util.Objects.requireNonNull(ty_args, "ty_args must not be null");
        java.util.Objects.requireNonNull(args, "args must not be null");
        this.name = name;
        this.doc = doc;
        this.code = code;
        this.ty_args = ty_args;
        this.args = args;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_str(name);
        serializer.serialize_str(doc);
        serializer.serialize_bytes(code);
        TraitHelpers.serialize_vector_TypeArgumentABI(ty_args, serializer);
        TraitHelpers.serialize_vector_ArgumentABI(args, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static TransactionScriptABI deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.name = deserializer.deserialize_str();
        builder.doc = deserializer.deserialize_str();
        builder.code = deserializer.deserialize_bytes();
        builder.ty_args = TraitHelpers.deserialize_vector_TypeArgumentABI(deserializer);
        builder.args = TraitHelpers.deserialize_vector_ArgumentABI(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static TransactionScriptABI bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        TransactionScriptABI value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        TransactionScriptABI other = (TransactionScriptABI) obj;
        if (!java.util.Objects.equals(this.name, other.name)) { return false; }
        if (!java.util.Objects.equals(this.doc, other.doc)) { return false; }
        if (!java.util.Objects.equals(this.code, other.code)) { return false; }
        if (!java.util.Objects.equals(this.ty_args, other.ty_args)) { return false; }
        if (!java.util.Objects.equals(this.args, other.args)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.name != null ? this.name.hashCode() : 0);
        value = 31 * value + (this.doc != null ? this.doc.hashCode() : 0);
        value = 31 * value + (this.code != null ? this.code.hashCode() : 0);
        value = 31 * value + (this.ty_args != null ? this.ty_args.hashCode() : 0);
        value = 31 * value + (this.args != null ? this.args.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public String name;
        public String doc;
        public com.novi.serde.Bytes code;
        public java.util.List<TypeArgumentABI> ty_args;
        public java.util.List<ArgumentABI> args;

        public TransactionScriptABI build() {
            return new TransactionScriptABI(
                name,
                doc,
                code,
                ty_args,
                args
            );
        }
    }
}
