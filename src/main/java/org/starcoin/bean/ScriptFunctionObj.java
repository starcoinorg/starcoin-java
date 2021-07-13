package org.starcoin.bean;

import com.google.common.collect.Lists;
import com.novi.serde.Bytes;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.starcoin.types.Identifier;
import org.starcoin.types.ModuleId;
import org.starcoin.types.ScriptFunction;
import org.starcoin.types.TypeTag;
import org.starcoin.utils.AccountAddressUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@SuperBuilder
public class ScriptFunctionObj extends ModuleObj {


    private String functionName;
    private List<TypeObj> tyArgs;
    private List<Bytes> args;

    @Override
    public String toRPCString() {
        return getModuleAddress() + "::" + getModuleName() + "::" + functionName;
    }

    public ScriptFunction toScriptFunction() {


        List<TypeTag> tyAargsTypeTag = Lists.newArrayList();
        if (tyArgs != null) {
            tyAargsTypeTag = tyArgs
                    .stream()
                    .map(TypeObj::toTypeTag)
                    .collect(Collectors.toList());
        }
        Identifier name = new Identifier(getModuleName());
        ModuleId moduleId = new ModuleId(AccountAddressUtils.create(getModuleAddress()), name);
        Identifier function = new Identifier(getFunctionName());
        ScriptFunction scriptFunction = new ScriptFunction(moduleId, function, tyAargsTypeTag, args);
        return scriptFunction;
    }
}
