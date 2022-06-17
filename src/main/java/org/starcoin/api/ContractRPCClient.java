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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starcoin.bean.ContractCall;
import org.starcoin.jsonrpc.client.JSONRPC2Session;
import org.starcoin.jsonrpc.client.JSONRPC2SessionException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Starcoin Contract 相关json-rpc接口的封装。
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class ContractRPCClient {
    private static Logger log = LoggerFactory.getLogger(ContractRPCClient.class);
    JSONRPC2Session session;

    public ContractRPCClient(URL baseUrl) {
        session = new JSONRPC2Session(baseUrl);
    }

    /**
     * 调用 某个contract 的方法，获取运算结果
     */
    public List<Object> call(ContractCall call) throws JSONRPC2SessionException {
        JsonRPCClient<Object> client = new JsonRPCClient<>();
        List<Object> parameter = new ArrayList<>();
        parameter.add(call.toMap());
        return client.getObjectArray(session, "contract.call_v2", parameter, 0, Object.class);
    }

}
