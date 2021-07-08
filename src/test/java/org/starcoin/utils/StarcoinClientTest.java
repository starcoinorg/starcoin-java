package org.starcoin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.novi.bcs.BcsSerializer;
import com.novi.serde.Bytes;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.junit.Test;
import org.starcoin.bean.ResourceObj;
import org.starcoin.bean.ScriptFunctionObj;
import org.starcoin.bean.TypeObj;
import org.starcoin.types.AccountAddress;
import org.starcoin.types.Ed25519PrivateKey;

public class StarcoinClientTest {

  private StarcoinClient starcoinClient = new StarcoinClient(ChainInfo.BARNARD);
  private String address = "0xf8af03dd08de49d81e4efd9e24c039cc";
  private String privateKeyString = "0x7899f7cac425b5ce7239eb313db06ac2a93c731ea4512b857f975c0447176b25";
  private Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
  private AccountAddress sender = AccountAddressUtils.create(address);


  @Test
  public void testSeqnubmer() {
    System.out.println(starcoinClient.getAccountSequence(sender).sequence_number);
  }


  public boolean checkTxt(String txn) {
    String rst = starcoinClient.getTransactionInfo(txn);
    JSONObject jsonObject = JSON.parseObject(rst);
    JSONObject result = jsonObject.getJSONObject("result");
    if (result != null) {
      return true;
    }
    return false;
  }


  @SneakyThrows
  @Test
  public void test() {

    Long l = 92386324538286L;

    System.out.println("u64:" + l + ", after:" + Hex
        .encode(BcsSerializeHelper.serializeU64ToBytes(l)));
    String str = "hello";
    System.out.println("string:" + str + ", after:" + Hex
        .encode(BcsSerializeHelper.serializeVectorU8ToBytes(str)));

    BigInteger bigInteger = new BigInteger("500000000000000");
    System.out.println("u128:" + bigInteger + ", after:" + Hex
        .encode(BcsSerializeHelper.serializeU128ToBytes(bigInteger)));

    List<String> list = Lists
        .newArrayList("a",
            "b", "c");

    List<Bytes> bytesList = list.stream().map(s -> {
      System.out
          .println("string:" + s + ",after:" + Hex.encode(BcsSerializeHelper.serializeVectorU8ToBytes(s)));
      return BcsSerializeHelper.serializeVectorU8ToBytes(s);
    }).collect(Collectors.toList());


    BcsSerializer s = new BcsSerializer();
    s.serialize_len(bytesList.size());
    System.out.println(Hex.encode(s.get_bytes()));
    for (Bytes item : bytesList) {
      s.serialize_bytes(item);
      System.out.println(Hex.encode(s.get_bytes()));
    }
    System.out.println(Hex.encode(s.get_bytes()));

    System.out.println("VectorU8(VectorU8):" + Joiner.on(",").join(list) + ",after:" + Hex
        .encode(BcsSerializeHelper.serializeListToBytes(list)));

    String address = "0xf8af03dd08de49d81e4efd9e24c039cc";

    System.out.println("address:" + address + ",after:" + Hex
        .encode(BcsSerializeHelper.serializeAddressToBytes(AccountAddressUtils.create(address))));
  }

  @SneakyThrows
  @Test
  public void testDeployContract() {

    String contractPath = this
        .getClass()
        .getClassLoader()
        .getResource("contract/MyCounter.mv")
        .getFile();

    ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
        .builder()
        .moduleAddress("0xf8af03dd08de49d81e4efd9e24c039cc")
        .moduleName("xxxx")
        .functionName("init_script")
        .tyArgs(Lists.newArrayList())
        .args(Lists.newArrayList())
        .build();

    String rst = starcoinClient.deployContractPackage(sender, privateKey, contractPath,
        scriptFunctionObj);
    JSONObject jsonObject = JSON.parseObject(rst);
    String txn = jsonObject.getString("result");
    checkTxt(txn);
  }

  @Test
  public void testGetResource() {
    ResourceObj resourceObj = ResourceObj
        .builder()
        .moduleAddress("0xf8af03dd08de49d81e4efd9e24c039cc")
        .moduleName("MyCounter")
        .resourceName("Counter")
        .build();
    String rst = starcoinClient.getResource(sender, resourceObj);
    System.out.println(rst);
  }

  @Test
  public void testCallContractFunctionV2() {
    BigInteger amount = new BigInteger("10000000");
    ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
        .builder()
        .moduleAddress("0xf8af03dd08de49d81e4efd9e24c039cc")
        .functionName("mint_script")
        .moduleName("ALM")
        .args(Lists.newArrayList(BcsSerializeHelper.serializeU128ToBytes(amount)))
        .build();
    String rst = starcoinClient.callScriptFunction(sender, privateKey, scriptFunctionObj);
    System.out.println(rst);
  }


  @SneakyThrows
  @Test
  public void testTransfer() {
    String toAddress = "0xd7f20befd34b9f1ab8aeae98b82a5a51";
    TypeObj typeObj = TypeObj.STC();
    String rst = starcoinClient.transfer(sender, privateKey, AccountAddressUtils.create(toAddress),
        typeObj, new BigInteger("1000"));
    System.out.println(rst);
  }


}

