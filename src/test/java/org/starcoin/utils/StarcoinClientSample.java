package org.starcoin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.novi.bcs.BcsSerializer;
import com.novi.serde.Bytes;
import lombok.SneakyThrows;
import org.junit.Test;
import org.starcoin.bean.ResourceObj;
import org.starcoin.bean.ScriptFunctionObj;
import org.starcoin.bean.TypeObj;
import org.starcoin.types.AccountAddress;
import org.starcoin.types.Ed25519PrivateKey;
import org.starcoin.types.RawUserTransaction;
import org.starcoin.types.TransactionPayload;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StarcoinClientSample {

    private final String address = "0xf8af03dd08de49d81e4efd9e24c039cc";
    private final String privateKeyString = "0x7899f7cac425b5ce7239eb313db06ac2a93c731ea4512b857f975c0447176b25";
    private final Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
    private final AccountAddress sender = AccountAddressUtils.create(address);
    private final StarcoinClient starcoinClient = new StarcoinClient(ChainInfo.DEFAULT_BARNARD);
    //private final StarcoinClient starcoinClient = new StarcoinClient(new ChainInfo("dev", "http://localhost:9850", 254));

    public void testSeqnubmer() {
        System.out.println(starcoinClient.getAccountSequence(sender).sequence_number);
    }


    public boolean checkTxt(String txn) {
        String rst = starcoinClient.getTransactionInfo(txn);
        JSONObject jsonObject = JSON.parseObject(rst);
        JSONObject result = jsonObject.getJSONObject("result");
        return result != null;
    }


    @SneakyThrows
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
                    .println("string:" + s + ",after:" + Hex
                            .encode(BcsSerializeHelper.serializeVectorU8ToBytes(s)));
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
    public void testDeployContract() {

        String contractPath = Objects.requireNonNull(this
                        .getClass()
                        .getClassLoader()
                        .getResource("contract/MyCounter.mv"))
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
    public void testTransfer() {
        String toAddress = "0xd7f20befd34b9f1ab8aeae98b82a5a51";
        TypeObj typeObj = TypeObj.STC();
        String rst = starcoinClient.transfer(sender, privateKey, AccountAddressUtils.create(toAddress),
                typeObj, new BigInteger("1000"));
        System.out.println(rst);
    }

    //@Test
    @SneakyThrows
    public void testTransfer_2() {
        String toAddress = "0xd7f20befd34b9f1ab8aeae98b82a5a51";
        TypeObj typeObj = TypeObj.STC();
        TransactionPayload.ScriptFunction transactionPayload = (TransactionPayload.ScriptFunction)
                starcoinClient.buildTransferPayload(AccountAddressUtils.create(toAddress), typeObj, new BigInteger("1000"));

        RawUserTransaction rawUserTransaction = starcoinClient.buildRawUserTransaction(sender, transactionPayload);
        String rst = starcoinClient.submitHexTransaction(privateKey, rawUserTransaction);
        System.out.println(rst);

        String txnHashLocally = SignatureUtils.getTransactionHash(privateKey, rawUserTransaction.chain_id.id.intValue(),
                rawUserTransaction.sender, BigInteger.valueOf(rawUserTransaction.sequence_number),
                rawUserTransaction.payload,
                BigInteger.valueOf(rawUserTransaction.gas_unit_price),
                BigInteger.valueOf(rawUserTransaction.max_gas_amount),
                rawUserTransaction.expiration_timestamp_secs,
                rawUserTransaction.gas_token_code //null // default gas token code
        );
        System.out.println(txnHashLocally);
    }

    public void testPriceOracleRead() {
        String stcUsdOracleType = "0x00000000000000000000000000000001::STCUSDOracle::STCUSD";
        int stcUsdDecimals = 6;
        String ethUsdOracleType = "0x07fa08a855753f0ff7292fdcbe871216::ETH_USD::ETH_USD";
        int ethUsdDecimals = 8;
        String oracleAddress = "0x07fa08a855753f0ff7292fdcbe871216";
        System.out.println("STC / USD: " + new BigDecimal(starcoinClient.priceOracleRead(stcUsdOracleType, oracleAddress))
                .divide(BigDecimal.TEN.pow(stcUsdDecimals), 8, RoundingMode.HALF_UP));
        System.out.println("ETH / USD: " + new BigDecimal(starcoinClient.priceOracleRead(ethUsdOracleType, oracleAddress))
                .divide(BigDecimal.TEN.pow(ethUsdDecimals), 8, RoundingMode.HALF_UP));
        // Output like these:
        //STC / USD: 0.15810000
        //ETH / USD: 3450.61348696
    }

    public void testPriceOracleGetScalingFactor() {
        String stcUsdOracleType = "0x00000000000000000000000000000001::STCUSDOracle::STCUSD";
        //int stcUsdDecimals = 6;
        String ethUsdOracleType = "0x07fa08a855753f0ff7292fdcbe871216::ETH_USD::ETH_USD";
        //int ethUsdDecimals = 8;
        System.out.println("STC / USD: " + starcoinClient.priceOracleGetScalingFactor(stcUsdOracleType));
        System.out.println("ETH / USD: " + starcoinClient.priceOracleGetScalingFactor(ethUsdOracleType));
    }
}

