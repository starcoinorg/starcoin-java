package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.novi.bcs.BcsDeserializer;
import com.novi.serde.DeserializationError;
import org.starcoin.utils.Hex;

import java.math.BigInteger;

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
    @JSONField(name = "amount_value")
    BigInteger amountValue;
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

    public void setAmountValue(BigInteger amountValue) {
        this.amountValue = amountValue;
    }

    public BigInteger getAmountValue() {
        try {
            BigInteger amount = new BcsDeserializer(Hex.decode(this.amount)).deserialize_u128();
            return amount;
        } catch (DeserializationError deserializationError) {
            deserializationError.printStackTrace();
        }
        return BigInteger.ZERO;
    }

    private long transferAmount(String amountStr) {
        if (amountStr == null && (!amountStr.startsWith("0x"))) {
            return 0;
        }
        int len = amountStr.length();
        int index = 0;
        for (int i = len - 1; i > 1; i--) {
            if (!(amountStr.charAt(i) == '0')) {
                index = i;
                break;
            }
        }
        try {
            if (index + 1 > 2) {
                String tempStr = amountStr.substring(2, index + 1);
                return Long.parseLong(tempStr, 16);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
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
