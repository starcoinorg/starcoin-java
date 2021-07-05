package org.starcoin.types;


public final class ScriptFunctionABI {
    public final String name;
    public final ModuleId module_name;
    public final String doc;
    public final java.util.List<TypeArgumentABI> ty_args;
    public final java.util.List<ArgumentABI> args;

    public ScriptFunctionABI(String name, ModuleId module_name, String doc, java.util.List<TypeArgumentABI> ty_args, java.util.List<ArgumentABI> args) {
        java.util.Objects.requireNonNull(name, "name must not be null");
        java.util.Objects.requireNonNull(module_name, "module_name must not be null");
        java.util.Objects.requireNonNull(doc, "doc must not be null");
        java.util.Objects.requireNonNull(ty_args, "ty_args must not be null");
        java.util.Objects.requireNonNull(args, "args must not be null");
        this.name = name;
        this.module_name = module_name;
        this.doc = doc;
        this.ty_args = ty_args;
        this.args = args;
    }

    public static ScriptFunctionABI deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.name = deserializer.deserialize_str();
        builder.module_name = ModuleId.deserialize(deserializer);
        builder.doc = deserializer.deserialize_str();
        builder.ty_args = TraitHelpers.deserialize_vector_TypeArgumentABI(deserializer);
        builder.args = TraitHelpers.deserialize_vector_ArgumentABI(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static ScriptFunctionABI bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        ScriptFunctionABI value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_str(name);
        module_name.serialize(serializer);
        serializer.serialize_str(doc);
        TraitHelpers.serialize_vector_TypeArgumentABI(ty_args, serializer);
        TraitHelpers.serialize_vector_ArgumentABI(args, serializer);
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
        ScriptFunctionABI other = (ScriptFunctionABI) obj;
        if (!java.util.Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!java.util.Objects.equals(this.module_name, other.module_name)) {
            return false;
        }
        if (!java.util.Objects.equals(this.doc, other.doc)) {
            return false;
        }
        if (!java.util.Objects.equals(this.ty_args, other.ty_args)) {
            return false;
        }
        if (!java.util.Objects.equals(this.args, other.args)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.name != null ? this.name.hashCode() : 0);
        value = 31 * value + (this.module_name != null ? this.module_name.hashCode() : 0);
        value = 31 * value + (this.doc != null ? this.doc.hashCode() : 0);
        value = 31 * value + (this.ty_args != null ? this.ty_args.hashCode() : 0);
        value = 31 * value + (this.args != null ? this.args.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public String name;
        public ModuleId module_name;
        public String doc;
        public java.util.List<TypeArgumentABI> ty_args;
        public java.util.List<ArgumentABI> args;

        public ScriptFunctionABI build() {
            return new ScriptFunctionABI(
                    name,
                    module_name,
                    doc,
                    ty_args,
                    args
            );
        }
    }
}
