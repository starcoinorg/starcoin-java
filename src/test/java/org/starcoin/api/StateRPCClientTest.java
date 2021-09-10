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
import org.junit.Before;
import org.junit.Ignore;
import org.starcoin.bean.TokenInfo;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StateRPCClientTest {
    private StateRPCClient stateRPCClient;

    @Before
    protected void setUp() throws Exception {
        stateRPCClient = new StateRPCClient(new URL("http://localhost:9850"));
    }

    @Ignore
    public void testGetState() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
            System.out.println(format.format(new Date()));
            stateRPCClient.getState("0x5c04bb88984f933151bf69504692074d");
            long amount = stateRPCClient.getAddressAmount("0x5c04bb88984f933151bf69504692074d", "0x9350502a3af6c617e9a42fa9e306a385::BX_USDT::BX_USDT");
            System.out.println("amount: " + amount);
            amount = stateRPCClient.getAddressAmount("0x5c04bb88984f933151bf69504692074d", "0x00000000000000000000000000000001::STC::STC");
            System.out.println("amount: " + amount);
            TokenInfo tokenInfo = stateRPCClient.getTokenInfo("0x44366bba9bc9ed51bc8f564ecb18b12a", "0x44366bba9bc9ed51bc8f564ecb18b12a::DummyToken::BBB");
            System.out.println(tokenInfo);
            tokenInfo = stateRPCClient.getTokenInfo("0x1", "0x1::STC::STC");
            System.out.println(tokenInfo);
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }
}
