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
package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.starcoin.types.TransactionPayload;

public class RawTransaction {
    @JSONField(name = "sequence_number")
    String sequenceNumber;
    @JSONField(name = "chain_id")
    int chainId;
    @JSONField(name = "sender")
    String sender;
    @JSONField(name = "payload")
    String payload;
    @JSONField(serialize = false)
    TransactionPayload transactionPayload;
    @JSONField(name = "gas_unit_price")
    String gasUnitPrice;
    @JSONField(name = "gas_token_code")
    String gasTokenCode;
    @JSONField(name = "max_gas_amount")
    String maxGasAmount;
    @JSONField(name = "expiration_timestamp_secs")
    String expirationTimestampSecs;
    @JSONField(name = "authenticator")
    Authenticator authenticator;
    @JSONField(name = "transaction_hash")
    String transactionHash;

    public String getDecodedPayload() {
        return decodedPayload;
    }

    public void setDecodedPayload(String decodedPayload) {
        this.decodedPayload = decodedPayload;
    }

    @JSONField(name = "decoded_payload")
    String decodedPayload;

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getChainId() {
        return chainId;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getGasUnitPrice() {
        return gasUnitPrice;
    }

    public void setGasUnitPrice(String gasUnitPrice) {
        this.gasUnitPrice = gasUnitPrice;
    }

    public String getGasTokenCode() {
        return gasTokenCode;
    }

    public void setGasTokenCode(String gasTokenCode) {
        this.gasTokenCode = gasTokenCode;
    }

    public String getMaxGasAmount() {
        return maxGasAmount;
    }

    public void setMaxGasAmount(String maxGasAmount) {
        this.maxGasAmount = maxGasAmount;
    }

    public String getExpirationTimestampSecs() {
        return expirationTimestampSecs;
    }

    public void setExpirationTimestampSecs(String expirationTimestampSecs) {
        this.expirationTimestampSecs = expirationTimestampSecs;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public TransactionPayload getTransactionPayload() {
        return transactionPayload;
    }

    public void setTransactionPayload(TransactionPayload transactionPayload) {
        this.transactionPayload = transactionPayload;
    }

    @Override
    public String toString() {
        return "RawTransaction{" +
                "sequenceNumber='" + sequenceNumber + '\'' +
                ", chainId=" + chainId +
                ", sender='" + sender + '\'' +
                ", payload='" + payload + '\'' +
                ", transactionPayload=" + transactionPayload +
                ", gasUnitPrice='" + gasUnitPrice + '\'' +
                ", gasTokenCode='" + gasTokenCode + '\'' +
                ", maxGasAmount='" + maxGasAmount + '\'' +
                ", expirationTimestampSecs='" + expirationTimestampSecs + '\'' +
                ", authenticator=" + authenticator +
                ", transactionHash='" + transactionHash + '\'' +
                ", decodedPayload='" + decodedPayload + '\'' +
                '}';
    }
}
