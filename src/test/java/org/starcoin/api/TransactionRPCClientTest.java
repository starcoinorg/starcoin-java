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

import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import junit.framework.TestCase;
import org.starcoin.bean.Event;
import org.starcoin.bean.Transaction;

import java.net.URL;
import java.util.Collections;
import java.util.List;

public class TransactionRPCClientTest extends TestCase {

    private TransactionRPCClient client;

    @Override
    protected void setUp() throws Exception {
        // client = new TransactionRPCClient(new URL("http://localhost:9850"));
        client = new TransactionRPCClient(new URL("https://barnard-seed.starcoin.org"));
    }

    public void testGetTransactionByHash() {

        try {
            Transaction transaction = client.getTransactionByHash("0x9497fc455c962ee27a2321e88af6c8eeae9842f3d3ea70dc349cdbe004250897");
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }

    public void testGetTransactionInfos() throws JSONRPC2SessionException {
       List<Transaction> transactionList = client.getTransactionInfos(5222379, true, 10);
        for (Transaction transaction: transactionList) {
            System.out.println(transaction);
        }
    }

    public void testGetEvents() throws JSONRPC2SessionException {
        String tag = "0x8c109349c6bd91411d6bc962e080c4a3::TokenSwapFee::SwapFeeEvent";
//        String tag ="0x00000000000000000000000000000001::Block::NewBlockEvent";
       List<Event> result = client.getEvents(Long.valueOf(4887495), Long.valueOf(4887520) ,null, null, Collections.singletonList(tag), null);
        for (Event event: result) {
            System.out.println(event);
        }

    }
}