package org.starcoin.bean;

public enum TransactionType {
    Script("Script"),
    Package("Package"),
    ScriptFunction("ScriptFunction");

    public final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
