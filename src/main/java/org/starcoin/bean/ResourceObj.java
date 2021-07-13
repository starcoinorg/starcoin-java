package org.starcoin.bean;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@SuperBuilder
@Data
public class ResourceObj extends ModuleObj {

    private String resourceName;
    private Optional<TypeObj> resourceType = Optional.empty();

    public String toRPCString() {
        String rst = getModuleAddress() + "::" + getModuleName() + "::" + getResourceName();
        if (resourceType != null && resourceType.isPresent()) {
            rst = rst + "<" + resourceType.get().toRPCString() + ">";
        }
        return rst;
    }
}
