package org.starcoin.utils;

import static org.junit.Assert.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.io.BaseEncoding;
import com.novi.serde.Bytes;
import com.sun.scenario.effect.impl.state.AccessHelper;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.starcoin.bean.RawTransaction;
import org.starcoin.stdlib.Helpers;
import org.starcoin.types.AccountAddress;
import org.starcoin.types.AccountResource;
import org.starcoin.types.ChainId;
import org.starcoin.types.Ed25519PrivateKey;
import org.starcoin.types.Identifier;
import org.starcoin.types.RawUserTransaction;
import org.starcoin.types.SignedUserTransaction;
import org.starcoin.types.StructTag;
import org.starcoin.types.TransactionAuthenticator;
import org.starcoin.types.TransactionPayload;
import org.starcoin.types.TypeTag;

public class StarcoinClientTest {

  StarcoinClient starcoinClient = new StarcoinClient("https://barnard-seed.starcoin.org");


  @SneakyThrows
  public AccountResource getAccountSequence(AccountAddress sender) {
    String path = AccountAddressUtils.hex(sender)
        + "/1/0x00000000000000000000000000000001::Account::Account";
    String rst = starcoinClient.call("state.get", Lists.newArrayList(path));
    JSONObject jsonObject = JSON.parseObject(rst);
    List<Byte> result = jsonObject.getJSONArray("result").toJavaList(Byte.class);
    Byte[] bytes = result.toArray(new Byte[0]);
    AccountResource accountResource = AccountResource.bcsDeserialize(ArrayUtils.toPrimitive(bytes));
    return accountResource;
  }

  @SneakyThrows
  @Test
  public void test() {
    String privateKeyString = "0x587737ebefb4961d377a3ab2f9ceb37b1fa96eb862dfaf954a4a1a99535dfec0";
    Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
    String fromAddress = "0xd7f20befd34b9f1ab8aeae98b82a5a51";
    String toAddress = "0xf8af03dd08de49d81e4efd9e24c039cc";

    AccountAddress sender = AccountAddressUtils.create(fromAddress);
    TransactionPayload payload = buildTransferPayload(AccountAddressUtils.create(toAddress),
        new BigInteger("100000"));
    RawUserTransaction rawUserTransaction = buildRawUserTransaction(sender, payload);
    SignedUserTransaction signedUserTransaction = SignatureUtils
        .signTxn(privateKey, rawUserTransaction);
    List<String> params = Lists.newArrayList(Hex.encode(signedUserTransaction.bcsSerialize()));
    String rst = starcoinClient.call("txpool.submit_hex_transaction", params);
    System.out.println(rst);
  }

  public static StructTag STC() {
    return new StructTag(
        AccountAddressUtils.create("0x00000000000000000000000000000001"), new Identifier("STC"),
        new Identifier("STC"), Lists.newArrayList());
  }


  private TransactionPayload buildTransferPayload(AccountAddress toAddress, BigInteger amount) {
    TypeTag typeTag = new TypeTag.Struct(STC());
    TransactionPayload payload = Helpers
        .encode_peer_to_peer_v2_script_function(typeTag, toAddress, amount);
    return payload;
  }

  @SneakyThrows
  public RawUserTransaction buildRawUserTransaction(AccountAddress sender,
      TransactionPayload payload) {
    AccountResource accountResource = getAccountSequence(sender);

    long seqNumber = accountResource.sequence_number;
    ChainId chainId = new ChainId((byte) 251);
    RawUserTransaction rawUserTransaction = new RawUserTransaction(sender, seqNumber, payload,
        10000000L, 1L, "0x1::STC::STC",
        System.currentTimeMillis() / 1000 + TimeUnit.HOURS.toSeconds(1), chainId);
    return rawUserTransaction;

  }
}

