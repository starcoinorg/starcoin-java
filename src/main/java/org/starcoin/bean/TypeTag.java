package org.starcoin.bean;

public class TypeTag {
    private Struct struct;

    public Struct getStruct() {
        return struct;
    }

    public void setStruct(Struct struct) {
        this.struct = struct;
    }

    @Override
    public String toString() {
        return "TypeTag{" +
                "struct=" + struct +
                '}';
    }
}
