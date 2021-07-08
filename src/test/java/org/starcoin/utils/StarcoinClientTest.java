package org.starcoin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import java.math.BigInteger;
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

