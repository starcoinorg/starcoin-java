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
