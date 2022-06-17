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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starcoin.bean.Block;
import org.starcoin.bean.BlockHeader;
import org.starcoin.jsonrpc.client.JSONRPC2Session;
import org.starcoin.jsonrpc.client.JSONRPC2SessionException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Starcoin Block相关json-rpc接口的封装。
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class BlockRPCClient {
    private static Logger log = LoggerFactory.getLogger(BlockRPCClient.class);
    JSONRPC2Session session;

    public BlockRPCClient(URL baseUrl) {
        session = new JSONRPC2Session(baseUrl);
    }

    /**
     * 获取当前主链的block header
     */
    @SuppressWarnings("unchecked")
    public BlockHeader getChainHeader() throws JSONRPC2SessionException, JsonProcessingException {
        JsonRPCClient<BlockHeader> client = new JsonRPCClient<>();
        return client.getSubObject(session, "chain.info", Collections.EMPTY_LIST, 0, "head", BlockHeader.class);
    }

    /**
     * 通过block_hash 获取block详细数据
     */
    public Block getBlockByHash(String hash) throws JSONRPC2SessionException {
        JsonRPCClient<Block> client = new JsonRPCClient<>();
        return client.getObject(session, "chain.get_block_by_hash", Collections.singletonList(hash), 0, Block.class);
    }

    /**
     * 通过block 高度 获取block详细数据
     */
    public Block getBlockByHeight(long height) throws JSONRPC2SessionException {
        JsonRPCClient<Block> client = new JsonRPCClient<>();
        return client.getObject(session, "chain.get_block_by_number", Collections.singletonList(height), 0, Block.class);
    }

    /**
     * 从某个高度开始获取之后的block，可以通过参数count指定获取多少个
     */
    public List<Block> getBlockListFromHeight(long height, int count) throws JSONRPC2SessionException {
        JsonRPCClient<Block> client = new JsonRPCClient<>();
        List<Object> params = new ArrayList<>();
        params.add(height);
        params.add(count);
        return client.getObjectArray(session, "chain.get_blocks_by_number", params, 0, Block.class);
    }

}
