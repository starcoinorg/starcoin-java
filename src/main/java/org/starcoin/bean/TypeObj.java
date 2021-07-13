package org.starcoin.bean;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.starcoin.types.Identifier;
import org.starcoin.types.StructTag;
import org.starcoin.types.TypeTag;
import org.starcoin.utils.AccountAddressUtils;

@Data
@SuperBuilder
public class TypeObj extends ModuleObj {

    private String name;

    public static TypeObj STC() {
        return TypeObj.builder().moduleName("STC").moduleAddress("0x00000000000000000000000000000001")
                .name("STC").build();
    }

    @Override
    public String toRPCString() {
        return getModuleAddress() + "::" + getModuleName() + "::" + name;
    }

    public TypeTag toTypeTag() {
        return new TypeTag.Struct(new StructTag(
                AccountAddressUtils.create(getModuleAddress()), new Identifier(getModuleName()),
                new Identifier(getName()), Lists.newArrayList()));
    }

}
