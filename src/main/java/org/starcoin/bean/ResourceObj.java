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
