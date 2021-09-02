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
import org.starcoin.bean.Transaction;

import java.net.MalformedURLException;
import java.net.URL;

public class TransactionRPCClientTest extends TestCase {

    private TransactionRPCClient client;

    @Override
    protected void setUp() throws Exception {
        client = new TransactionRPCClient(new URL("http://localhost:9850"));
    }

    public void testGetTransactionByHash() {

        try {
            Transaction transaction = client.getTransactionByHash("0x9497fc455c962ee27a2321e88af6c8eeae9842f3d3ea70dc349cdbe004250897");
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }
}