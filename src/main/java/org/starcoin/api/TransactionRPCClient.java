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
import org.starcoin.bean.*;
import org.starcoin.jsonrpc.client.JSONRPC2Session;
import org.starcoin.jsonrpc.client.JSONRPC2SessionException;

import java.net.URL;
import java.util.*;

/**
 * Starcoin Transaction 相关json-rpc接口的封装。
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class TransactionRPCClient {

    private static final Logger log = LoggerFactory.getLogger(TransactionRPCClient.class);
    JSONRPC2Session session;

    public TransactionRPCClient(URL baseUrl) {
        session = new JSONRPC2Session(baseUrl);
    }

    /**
     * 通过 transaction hash 获取某个 PendingTransaction
     */
    public PendingTransaction getPendingTransaction(String hash) throws JSONRPC2SessionException {
        JsonRPCClient<PendingTransaction> client = new JsonRPCClient<>();
        return client.getObject(session, "txpool.pending_txn", Collections.singletonList(hash), 0, PendingTransaction.class);
    }

    /**
     * 通过 transaction hash 获取某个 Transaction
     */
    public Transaction getTransactionByHash(String hash) throws JSONRPC2SessionException {
        JsonRPCClient<Transaction> client = new JsonRPCClient<>();
        List<Object> parameter = new ArrayList<>();
        parameter.add(hash);
        GetTransactionOption option = new GetTransactionOption();
        option.setDecode(true);
        parameter.add(option);
        return client.getObject(session, "chain.get_transaction", parameter, 0, Transaction.class);
    }

    /**
     * 通过 transaction hash 获取某个 TransactionInfo
     */
    public Transaction getTransactionInfoByHash(String hash) throws JSONRPC2SessionException {
        JsonRPCClient<Transaction> client = new JsonRPCClient<>();
        return client.getObject(session, "chain.get_transaction_info", Collections.singletonList(hash), 0, Transaction.class);
    }

    /**
     * 通过 transaction hash 获取某个 TransactionInfo
     */
    public List<Transaction> getTransactionInfos(long startGlobalIndex, boolean reverse, int count) throws JSONRPC2SessionException {
        JsonRPCClient<Transaction> client = new JsonRPCClient<>();
        List<Object> parameter = new ArrayList<>();
        parameter.add(startGlobalIndex);
        parameter.add(reverse);
        parameter.add(count);
        return client.getObjectArray(session, "chain.get_transaction_infos", parameter, 0, Transaction.class);
    }


    /**
     * 通过 block hash 获取所有 Transaction
     */
    public List<Transaction> getBlockTransactions(String blockHash) throws JSONRPC2SessionException {
        JsonRPCClient<Transaction> client = new JsonRPCClient<>();
        return client.getObjectArray(session, "chain.get_block_txn_infos", Collections.singletonList(blockHash), 0, Transaction.class);
    }

    /**
     * 通过 transaction hash 获取某个 Transaction 的所有 Event
     */
    public List<Event> getTransactionEvents(String transactionHash) throws JSONRPC2SessionException {
        JsonRPCClient<Event> client = new JsonRPCClient<>();
        return client.getObjectArray(session, "chain.get_events_by_txn_hash", Collections.singletonList(transactionHash), 0, Event.class);
    }

    public List<Event> getEvents(Long fromBlock, Long toBlock, List<String> eventKeys, List<String> accountAddress, List<String> typeTags, Integer limit) throws JSONRPC2SessionException {
//        chain.get_events
        JsonRPCClient<Event> client = new JsonRPCClient<>();
        List<Object> parameter = new ArrayList<>();
        Map<String, Object> mapParameter = new HashMap<>();
        mapParameter.put("from_block", fromBlock);
        mapParameter.put("to_block", toBlock);
        mapParameter.put("event_keys", eventKeys);
        mapParameter.put("addrs", accountAddress);
        mapParameter.put("type_tags", typeTags);
        mapParameter.put("limit", limit);
        parameter.add(mapParameter);
        parameter.add(new GetEventOption(true));
        return client.getObjectArray(session, "chain.get_events", parameter, 0, Event.class);
    }

}
