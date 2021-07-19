package org.starcoin.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;

import java.util.List;

public class JsonRPCClient<T> {

    public T getObject(JSONRPC2Session session, String method, List<Object> params, int requestId, Class<T> clazz) throws JSONRPC2SessionException {
        JSONRPC2Request request = new JSONRPC2Request(method, params, requestId);
        JSONRPC2Response response = session.send(request);
        if (response.indicatesSuccess()) {
            Object result = response.getResult();
            if (result != null) {
                return JSON.parseObject(result.toString(), clazz);
            }
        }
        return null;
    }

    public T getSubObject(JSONRPC2Session session, String method, List<Object> params, int requestId, String subKey, Class<T> clazz) throws JSONRPC2SessionException {
        JSONRPC2Request request = new JSONRPC2Request(method, params, requestId);
        JSONRPC2Response response = session.send(request);
        if (response.indicatesSuccess()) {
            Object result = response.getResult();
            if (result != null) {
                JSONObject jb = JSON.parseObject(result.toString());
                return jb.getObject(subKey, clazz);
            }
        }
        return null;
    }

    public List<T> getObjectArray(JSONRPC2Session session, String method, List<Object> params, int requestId, Class<T> clazz) throws JSONRPC2SessionException {
        JSONRPC2Request request = new JSONRPC2Request(method, params, requestId);
        JSONRPC2Response response = session.send(request);
        if (response.indicatesSuccess()) {
            Object result = response.getResult();
            if (result != null) {
                return JSON.parseArray(result.toString(), clazz);
            }
        }
        return null;
    }

}
