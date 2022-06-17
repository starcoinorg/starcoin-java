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
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starcoin.bean.ListResource;
import org.starcoin.bean.ListResourceOption;
import org.starcoin.bean.Resource;
import org.starcoin.bean.TokenInfo;
import org.starcoin.jsonrpc.client.JSONRPC2Session;
import org.starcoin.jsonrpc.client.JSONRPC2SessionException;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Starcoin State 相关json-rpc接口的封装。
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
     * 用于某个地址的状态
     */
    public ListResource getState(String address, boolean isDecode, String state_root) throws JSONRPC2SessionException {
        JsonRPCClient<ListResource> client = new JsonRPCClient<>();
        List<Object> param = new ArrayList<>();
        param.add(address);
        ListResourceOption option = new ListResourceOption();
        option.setDecode(isDecode);
        option.setStateRoot(state_root);
        param.add(option);//JSON.toJSON(option)
        try {
            return client.getObjectParseJackson(session, "state.list_resource", param, 0, ListResource.class);
        } catch (IOException e) {
            logger.error("get state error:", e);
        }
        return null;
    }

    public TokenInfo getTokenInfo(String address, String tokenCode) throws JSONRPC2SessionException, JsonProcessingException {
        JsonRPCClient<TokenInfo> client = new JsonRPCClient<>();
        List<Object> param = new ArrayList<>();
        param.add(address);
        param.add(tokenInfoParameter(tokenCode));
        ListResourceOption option = new ListResourceOption();
        option.setDecode(true);
        param.add(option);
        TokenInfo tokenInfo = client.getSubObject(session, "state.get_resource", param, 0, "json", TokenInfo.class);
        if (tokenInfo != null) {
            tokenInfo.setTokenCode(tokenCode);
        }
        return tokenInfo;
    }

    /**
     * 用于获取某个地址下的token 数量
     * 由于amount的数量可能超过long的最大值，所以这个方法已经在某些token下会溢出，建议使用getAddressAmountValue方法
     */
    @Deprecated
    public long getAddressAmount(String address, String token) {
        try {
            ListResource listResource = getState(address);
            if (listResource == null) {
                // add result = -1 for resource not exist
                return -1;
            }
            Map<String, Resource> resourceMap = listResource.getResources();
            JsonNode node = resourceMap.get(getResourceMapTokenKey(token)).getJson().get("token");
            if (node != null) {
                return node.get("value").asLong();
            }
        } catch (Exception e) {
            logger.error("get amount error:", e);
        }
        return 0;
    }

    /**
     * 用于获取某个地址下的token 数量
     */
    public BigInteger getAddressAmountValue(String address, String token) {
        try {
            ListResource listResource = getState(address);
            if (listResource == null) {
                // add result = -1 for resource not exist
                return BigInteger.valueOf(-1);
            }
            Map<String, Resource> resourceMap = listResource.getResources();
            JsonNode node = resourceMap.get(getResourceMapTokenKey(token)).getJson().get("token");
            if (node != null) {
                return new BigInteger(node.get("value").asText());
            }
        } catch (Exception e) {
            logger.error("get amount error:", e);
        }
        return BigInteger.valueOf(0);
    }


    private String getResourceMapTokenKey(String token) {
        return "0x00000000000000000000000000000001::Account::Balance<" + token + ">";
    }

    private String tokenInfoParameter(String code) {
        return "0x1::Token::TokenInfo<" + code + ">";
    }
}
