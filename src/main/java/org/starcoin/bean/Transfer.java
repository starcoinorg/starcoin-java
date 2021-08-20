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

public class Transfer {

    long timestamp;
    String identifier;
    @JSONField(name = "txn_hash")
    String txnHash;
    @JSONField(name = "sender")
    String sender;
    @JSONField(name = "receiver")
    String receiver;
    @JSONField(name = "amount")
    String amount;
    @JSONField(name = "type_tag")
    String typeTag;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTxnHash() {
        return txnHash;
    }

    public void setTxnHash(String txnHash) {
        this.txnHash = txnHash;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTypeTag() {
        return typeTag;
    }

    public void setTypeTag(String typeTag) {
        this.typeTag = typeTag;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "timestamp=" + timestamp +
                ", identifier='" + identifier + '\'' +
                ", txnHash='" + txnHash + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount='" + amount + '\'' +
                ", typeTag='" + typeTag + '\'' +
                '}';
    }
}
