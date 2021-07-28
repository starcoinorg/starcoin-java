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
