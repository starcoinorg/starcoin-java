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

import org.starcoin.bean.ContractCall;
import org.starcoin.jsonrpc.client.JSONRPC2SessionException;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Starcoin Token 相关json-rpc接口的封装。
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class TokenContractRPCClient extends ContractRPCClient {

    public static final String STCTypeTag = "0x00000000000000000000000000000001::STC::STC";
    public static final String TreasuryBalanceTypeTag = "0x00000000000000000000000000000001::Treasury::balance";
    public static final String TokenMarketCapTypeTag = "0x00000000000000000000000000000001::Token::market_cap";

    public TokenContractRPCClient(URL baseUrl) {
        super(baseUrl);
    }

    /**
     * 获取某个 token 的市场容量
     */
    public BigInteger getTokenMarketCap(String tokenTypeTag) throws JSONRPC2SessionException {
        ContractCall call = new ContractCall();

        call.setFunctionId(TokenMarketCapTypeTag);

        List<String> typeTags = new ArrayList<>();
        typeTags.add(tokenTypeTag);

        call.setTypeArgs(typeTags);
        call.setArgs(new ArrayList<>());

        List result = this.call(call);
        if (result != null && result.size() > 0) {
            String capInString = String.valueOf(result.get(0));
            return new BigInteger(capInString);
        } else {
            throw new JSONRPC2SessionException("fetch token" + tokenTypeTag + " market cap failed");
        }
    }

    /**
     * 用于获取某个 token 在国库中的数量
     */
    public BigInteger getTreasuryBalance(String tokenTypeTag) throws JSONRPC2SessionException {
        ContractCall call = new ContractCall();

        call.setFunctionId(TreasuryBalanceTypeTag);

        List<String> typeTags = new ArrayList<>();
        typeTags.add(tokenTypeTag);

        call.setTypeArgs(typeTags);
        call.setArgs(new ArrayList<>());

        List result = this.call(call);
        if (result != null && result.size() > 0) {
            String capInString = String.valueOf(result.get(0));
            return new BigInteger(capInString);
        } else {
            throw new JSONRPC2SessionException("fetch token" + tokenTypeTag + " market cap failed");
        }
    }

    /**
     * 获取某个 token 的实际供应量
     */
    public BigInteger getTokenCurrentSupply(String tokenTypeTag) throws JSONRPC2SessionException {
        BigInteger tokenMarketCap = this.getTokenMarketCap(tokenTypeTag);
        BigInteger tokenTreasureBalance = this.getTreasuryBalance(tokenTypeTag);
        return tokenMarketCap.subtract(tokenTreasureBalance);
    }

    /**
     * 用于获取 STC 的市场容量
     */
    public BigInteger getSTCMarketCap() throws JSONRPC2SessionException {
        return this.getTokenMarketCap(STCTypeTag);
    }

    /**
     * 用于获取 STC 在国库中的数量
     */
    public BigInteger getSTCTreasurBalance() throws JSONRPC2SessionException {
        return this.getTreasuryBalance(STCTypeTag);
    }

    /**
     * 获取 STC 的实际供应量
     */
    public BigInteger getSTCCurrentSupply() throws JSONRPC2SessionException {
        return this.getSTCMarketCap().subtract(this.getSTCTreasurBalance());
    }
}
