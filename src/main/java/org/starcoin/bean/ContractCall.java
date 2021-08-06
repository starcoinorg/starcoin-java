package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractCall {

    @JSONField(name = "function_id")
    private String functionId;

    @JSONField(name = "type_args")
    private List<String> typeArgs;

    private List<String> args;

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public List<String> getTypeArgs() {
        return typeArgs;
    }

    public void setTypeArgs(List<String> typeArgs) {
        this.typeArgs = typeArgs;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> result = new HashMap<>();
        result.put("function_id",this.functionId);
        result.put("type_args",this.typeArgs);
        result.put("args",this.args);
        return result;
    }
}
