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
package org.starcoin.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;

import java.io.IOException;
import java.util.List;

class JsonRPCClient<T> {

    protected T getObject(JSONRPC2Session session, String method, List<Object> params, int requestId, Class<T> clazz) throws JSONRPC2SessionException {
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

    protected T getObjectParseJackson(JSONRPC2Session session, String method, List<Object> params, int requestId, Class<T> clazz) throws JSONRPC2SessionException,IOException {
        JSONRPC2Request request = new JSONRPC2Request(method, params, requestId);
        JSONRPC2Response response = session.send(request);
        if (response.indicatesSuccess()) {
            Object result = response.getResult();
            if (result != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                return  objectMapper.readValue(result.toString(), clazz);
            }
        }
        return null;
    }


    protected T getSubObject(JSONRPC2Session session, String method, List<Object> params, int requestId, String subKey, Class<T> clazz) throws JSONRPC2SessionException {
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

    protected List<T> getObjectArray(JSONRPC2Session session, String method, List<Object> params, int requestId, Class<T> clazz) throws JSONRPC2SessionException {
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
