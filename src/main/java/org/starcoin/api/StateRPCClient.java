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

import com.fasterxml.jackson.databind.JsonNode;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starcoin.bean.ListResource;
import org.starcoin.bean.ListResourceOption;
import org.starcoin.bean.Resource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * Starcoin State 相关json-rpc接口的封装。
 *
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class StateRPCClient {
    private static Logger logger = LoggerFactory.getLogger(StateRPCClient.class);
    JSONRPC2Session session;

    public StateRPCClient(URL baseUrl) {
        session = new JSONRPC2Session(baseUrl);
    }

    /**
     * 用于某个地址的状态
     *
     */
    public ListResource getState(String address) throws JSONRPC2SessionException {
        JsonRPCClient<ListResource> client = new JsonRPCClient<>();
        List<Object> param = new ArrayList<>();
        param.add(address);
        ListResourceOption option = new ListResourceOption();
        option.setDecode(true);
        param.add(option);
        try {
            return client.getObjectParseJackson(session, "state.list_resource", param, 0, ListResource.class);
        } catch (IOException e) {
            logger.error("get state error:", e);
        }
        return null;
    }

    /**
     * 用于获取某个地址下的token 数量
     *
     */
    public long getAddressAmount(String address) {
        try {
            ListResource listResource = getState(address);
            Map<String, Resource> resourceMap = listResource.getResources();
        for(String key: resourceMap.keySet()) {
            JsonNode node = resourceMap.get(key).getJson().get("token");
            if (node != null) {
                return node.get("value").asLong();
            }
        }
        } catch (JSONRPC2SessionException e) {
           logger.error("get amount error:", e);
        }
        return 0;
    }
}
