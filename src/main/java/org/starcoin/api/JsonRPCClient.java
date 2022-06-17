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


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starcoin.jsonrpc.JSONRPC2Request;
import org.starcoin.jsonrpc.JSONRPC2Response;
import org.starcoin.jsonrpc.client.JSONRPC2Session;
import org.starcoin.jsonrpc.client.JSONRPC2SessionException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JsonRPC client 通用的封装类
 *
 * @param <T>
 */
class JsonRPCClient<T> {

    private static final Logger logger = LoggerFactory.getLogger(JsonRPCClient.class);

    /**
     * 获取单个对象的接口
     *
     * @param session
     * @param method    rpc接口的方法名
     * @param params    rpc接口的参数列表
     * @param requestId 请求id
     * @param clazz     返回对象的封装类
     * @return
     * @throws JSONRPC2SessionException
     */
    protected T getObject(JSONRPC2Session session, String method, List<Object> params, int requestId, Class<T> clazz) throws JSONRPC2SessionException {
        JSONRPC2Request request = new JSONRPC2Request(method, params, requestId);
        JSONRPC2Response response = session.send(request);
        if (response.indicatesSuccess()) {
            Object result = response.getResult();
            if (result != null) {
                return new ObjectMapper().convertValue(result, clazz);
            } else {
                logger.warn("get object result is null, method:" + method);
            }
        } else {
            logger.error("get object array error:" + response.getError());
        }
        return null;
    }

    /**
     * 获取单个对象的接口（返回用Jackson封装，某些json格式用fastjson解析有问题，故此增加此方法）
     *
     * @param session
     * @param method    rpc接口的方法名
     * @param params    rpc接口的参数列表
     * @param requestId 请求id
     * @param clazz     返回对象的封装类
     * @return
     * @throws JSONRPC2SessionException
     */
    protected T getObjectParseJackson(JSONRPC2Session session, String method, List<Object> params, int requestId, Class<T> clazz) throws JSONRPC2SessionException, IOException {
        JSONRPC2Request request = new JSONRPC2Request(method, params, requestId);
        JSONRPC2Response response = session.send(request);
        if (response.indicatesSuccess()) {
            Object result = response.getResult();
            if (result != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.convertValue(result, clazz);
            } else {
                logger.warn("get object parse jackson result is null, method:" + method);
            }

        } else {
            logger.error("get object parse jackson array error:" + response.getError());
        }
        return null;
    }


    /**
     * 获取单个对象的属性值接口
     *
     * @param session
     * @param method    rpc接口的方法名
     * @param params    rpc接口的参数列表
     * @param requestId 请求id
     * @param clazz     返回对象的封装类
     * @return
     * @throws JSONRPC2SessionException
     */
    @SuppressWarnings("rawtypes")
    protected T getSubObject(JSONRPC2Session session, String method, List<Object> params, int requestId, String subKey, Class<T> clazz) throws JSONRPC2SessionException, JsonProcessingException {
        JSONRPC2Request request = new JSONRPC2Request(method, params, requestId);
        JSONRPC2Response response = session.send(request);
        if (response.indicatesSuccess()) {
            Object result = response.getResult();
            if (result != null) {
                Map map = new ObjectMapper().convertValue(result, Map.class);
                return map.get(subKey) == null ? null : new ObjectMapper().convertValue(map.get(subKey), clazz);
            } else {
                logger.warn("get sub object result is null, method:" + method);
            }
        } else {
            logger.error("get sub object array error:" + response.getError());
        }
        return null;
    }

    /**
     * 获取对象列表的接口
     *
     * @param session
     * @param method    rpc接口的方法名
     * @param params    rpc接口的参数列表
     * @param requestId 请求id
     * @param clazz     返回对象的封装类
     * @return
     * @throws JSONRPC2SessionException
     */
    protected List<T> getObjectArray(JSONRPC2Session session, String method, List<Object> params, int requestId, Class<T> clazz) throws JSONRPC2SessionException {
        JSONRPC2Request request = new JSONRPC2Request(method, params, requestId);
        JSONRPC2Response response = session.send(request);
        if (response.indicatesSuccess()) {
            Object result = response.getResult();
            if (result != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                List<Object> list = objectMapper.convertValue(result, new TypeReference<List<Object>>() {
                });
                return list.stream().map(item -> objectMapper.convertValue(item, clazz)).collect(Collectors.toList());
            } else {
                logger.warn("get object result is null, method:" + method);
            }
        } else {
            logger.error("get object array error:" + response.getError());
        }
        return null;
    }

}
