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
import junit.framework.TestCase;
import org.starcoin.bean.Event;
import org.starcoin.bean.Transaction;
import org.starcoin.bean.TransactionInfoWithProof;
import org.starcoin.jsonrpc.client.JSONRPC2SessionException;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TransactionRPCClientTest extends TestCase {

    private TransactionRPCClient client;

    @Override
    protected void setUp() throws Exception {
        //client = new TransactionRPCClient(new URL("http://localhost:9850"));
        client = new TransactionRPCClient(new URL("https://barnard-seed.starcoin.org"));
    }

    public void testGetTransactionByHash() {
        try {
            Transaction transaction = client.getTransactionByHash("0x88e2f199dba3c4bef8b29b8e21a38cb4a6d36f17b5bd5f3394bfbf8268342ff2");
            System.out.println(transaction);
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }

    public void testGetTransactionInfos() throws JSONRPC2SessionException {
        List<Transaction> transactionList = client.getTransactionInfos(5222379, true, 10);
        for (Transaction transaction : transactionList) {
            System.out.println(transaction);
        }
    }

    public void testGetEvents() throws JSONRPC2SessionException {
//        String tag = "0x8c109349c6bd91411d6bc962e080c4a3::TokenSwapFee::SwapFeeEvent";
//        String tag ="0x00000000000000000000000000000001::Block::NewBlockEvent";
        String tag = "0x00000000000000000000000000000001::Dao::ProposalCreatedEvent";
        List<Event> result = client.getEvents(Long.valueOf(5061109), Long.valueOf(5061109), null, null, Collections.singletonList(tag), null);
        for (Event event : result) {
            System.out.println(event);
        }

    }

    public void testGetTransactionInfoByBlockAndIndex() {
        try {
            Transaction transaction = client.getTransactionInfoByBlockAndIndex("0x49622a10f52d88909c09510898c4bc15ebc338d56c22f2a197d76fa8b627b35c", 1);
            System.out.println(transaction);
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试 getTransactionProof 的三个重载函数
     */
    public void testGetTransactionProof() {
        String blockHash = "0x49622a10f52d88909c09510898c4bc15ebc338d56c22f2a197d76fa8b627b35c";
        long transactionGlobalIndex = 10502811;
        int eventIndex = 1;
        String accessPath = "0x00000000000000000000000000000001/1/0x00000000000000000000000000000001::Account::WithdrawEvent";
        try {
            TransactionInfoWithProof transactionInfoWithProof = client.getTransactionProof(blockHash, transactionGlobalIndex);
            System.out.println(transactionInfoWithProof);

            TransactionInfoWithProof transactionInfoWithProof2 = client.getTransactionProof(blockHash, transactionGlobalIndex, eventIndex);
            System.out.println(transactionInfoWithProof2);

            TransactionInfoWithProof transactionInfoWithProof3 = client.getTransactionProof(blockHash, transactionGlobalIndex, eventIndex, accessPath);
            System.out.println(transactionInfoWithProof3);
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }


    public void testJsonDeserialize() throws JsonProcessingException {
//        String json = "{\"foo\":{\"k\":\"v\"}}";
//        System.out.println(json);
//        C c = new ObjectMapper().readValue(json, C.class);
//        //C c = JSON.parseObject(json, C.class);
//        String json_2 = new ObjectMapper().writeValueAsString(c);
//        //String json_2 = JSON.toJSONString(c);
//        System.out.println(json_2.equals(json));//it is ture!
//        System.out.println(json_2);
//        if(true) return;

        String jsonT = "{\n" +
                "  \"status\": \"Ok\"\n" +
                "}\n";
        String jsonO = "{\n" +
                "  \"status\": {\n" +
                "    \"ExecutionFailure\": {\n" +
                "      \"function\": 10,\n" +
                "      \"code_offset\": 38,\n" +
                "      \"location\": {\n" +
                "        \"Module\": {\n" +
                "          \"address\": \"0x416b32009fe49fcab1d5f2ba0153838f\",\n" +
                "          \"name\": \"LockProxy\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        String jsonS = "{\n" +
                "  \"status\": 1234\n" +
                "}\n";
        String jsonA = "{\n" +
                "  \"status\": \n" +
                "[" + jsonO + "]" +
                "}";
        String json = jsonO;
        ObjectMapper objectMapper = new ObjectMapper();
        //Transaction t = JSON.parseObject(json, Transaction.class);
        Transaction t = objectMapper.readValue(json, Transaction.class);
        System.out.println(t.getStatus());
//        System.out.println(objectMapper.readValue(t.getStatus(), new TypeReference<Map<String, Object>>() {
//        }));
//        System.out.println(objectMapper.readValue(t.getStatus(), new TypeReference<List<Object>>() {
//        }));
        //System.out.println(t.getStatusObj());
        //String json_2 = JSON.toJSONString(t);
        String json_2 = objectMapper.writeValueAsString(t);
        System.out.println(json_2);
        //System.out.println(json);
        //System.out.println(json.equals(json_2));
        Map m = objectMapper.readValue(json, new TypeReference<Map>() {
        });
        System.out.println(m);
        Transaction t2 = objectMapper.convertValue(m, Transaction.class);
        System.out.println(t2.getStatus());
        System.out.println(t.getStatus().equals(t2.getStatus()));
    }


}