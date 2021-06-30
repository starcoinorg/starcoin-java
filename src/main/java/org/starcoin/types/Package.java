package org.starcoin.types;


public final class Package {
    public final AccountAddress package_address;
    public final java.util.List<Module> modules;
    public final java.util.Optional<ScriptFunction> init_script;

    public Package(AccountAddress package_address, java.util.List<Module> modules, java.util.Optional<ScriptFunction> init_script) {
        java.util.Objects.requireNonNull(package_address, "package_address must not be null");
        java.util.Objects.requireNonNull(modules, "modules must not be null");
        java.util.Objects.requireNonNull(init_script, "init_script must not be null");
        this.package_address = package_address;
        this.modules = modules;
        this.init_script = init_script;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        package_address.serialize(serializer);
        TraitHelpers.serialize_vector_Module(modules, serializer);
        TraitHelpers.serialize_option_ScriptFunction(init_script, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static Package deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.package_address = AccountAddress.deserialize(deserializer);
        builder.modules = TraitHelpers.deserialize_vector_Module(deserializer);
        builder.init_script = TraitHelpers.deserialize_option_ScriptFunction(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static Package bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        Package value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Package other = (Package) obj;
        if (!java.util.Objects.equals(this.package_address, other.package_address)) { return false; }
        if (!java.util.Objects.equals(this.modules, other.modules)) { return false; }
        if (!java.util.Objects.equals(this.init_script, other.init_script)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.package_address != null ? this.package_address.hashCode() : 0);
        value = 31 * value + (this.modules != null ? this.modules.hashCode() : 0);
        value = 31 * value + (this.init_script != null ? this.init_script.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public AccountAddress package_address;
        public java.util.List<Module> modules;
        public java.util.Optional<ScriptFunction> init_script;

        public Package build() {
            return new Package(
                package_address,
                modules,
                init_script
            );
        }
    }
}
