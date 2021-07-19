package org.starcoin.bean;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class ModuleObj {

    private String moduleAddress;
    private String moduleName;

    public abstract String toRPCString();
}
