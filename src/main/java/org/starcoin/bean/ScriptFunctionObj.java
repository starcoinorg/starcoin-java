/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
