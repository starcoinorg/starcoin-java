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

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Struct {
    @JSONField(name = "type_params")
    List<String> typeParams;
    String address;
    String module;
    String name;

    public static Struct fromRPC(String typeString) {
        if (typeString != null) {
            String[] split = typeString.split("::");
            if (split.length == 3) {
                Struct struct = new Struct();
                struct.setAddress(split[0]);
                struct.setModule(split[1]);
                struct.setName(split[2]);
                return struct;
            }
        }
        return null;
    }

    public List<String> getTypeParams() {
        return typeParams;
    }

    public void setTypeParams(List<String> typeParams) {
        this.typeParams = typeParams;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Struct{" +
                "typeParams=" + typeParams +
                ", address='" + address + '\'' +
                ", module='" + module + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
