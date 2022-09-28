# Starcoin Java SDK

starcoin-java
[![GitHub Action](https://github.com/starcoinorg/starcoin-java/workflows/Build/badge.svg)](https://github.com/starcoinorg/starcoin-java/actions?query=workflow%3A%22Build%22)

Starcoin Java SDK implementation.

## 文档

Home page: [Home page](https://starcoinorg.github.io/starcoin-java/)

## 用户指南

### 引入依赖

**Maven**

```xml

<dependency>
    <groupId>org.starcoin</groupId>
    <artifactId>sdk</artifactId>
    <version>1.1.25</version>
</dependency>
```

**Gradle**

``` 
implementation group: 'org.starcoin', name: 'sdk', version: '1.1.18'
```

**Gradle short**

``` 
implementation 'org.starcoin:sdk:1.1.18'
```

**Gradle kotlin**

``` 
implementation("org.starcoin:sdk:1.1.18")
```

**SBT**

``` 
libraryDependencies += "org.starcoin" % "sdk" % "1.1.18"
```

### 节点配置

在 starcoin [github](https://github.com/starcoinorg/starcoin) 下载 starcoin 预编译好的包。通过如下命令启动 dev 测试网，当然你也可以启动其他网络如
barnard/main 等等。

```
starcoin -n dev --http-apis all console
```

注意需要把 starcoin 命令行替换为实际地址，如果不需要使用交互式命令行工具，则不需要加 console。在看到这些信息之后，则表示 starcoin 节点启动成功。

```
2021-08-20T16:39:17.159471+08:00 INFO - Service starcoin_rpc_server::module::pubsub::PubSubService start.
2021-08-20T16:39:17.159801+08:00 INFO - starcoin_rpc_server::module::pubsub::PubSubService service actor started
2021-08-20T16:39:17.160851+08:00 INFO - Service starcoin_stratum::service::StratumService start.
2021-08-20T16:39:17.161045+08:00 INFO - starcoin_stratum::service::StratumService service actor started
2021-08-20T16:39:17.164330+08:00 INFO - Ipc rpc server start at :"/var/folders/wd/skrxtj1n25qf74v3q2l9k39c0000gn/T/0fbe8e453ab9d82068e68053d5fa252d/dev/starcoin.ipc"
2021-08-20T16:39:17.169487+08:00 INFO - Rpc: http server start at :0.0.0.0:9850
2021-08-20T16:39:17.170683+08:00 INFO - Rpc: tcp server start at: 0.0.0.0:9860
2021-08-20T16:39:17.171802+08:00 INFO - Listening for new connections on 0.0.0.0:9870.
2021-08-20T16:39:17.172077+08:00 INFO - Rpc: websocket server start at: 0.0.0.0:9870
2021-08-20T16:39:17.172190+08:00 INFO - Service starcoin_rpc_server::service::RpcService start.
2021-08-20T16:39:17.172279+08:00 INFO - starcoin_rpc_server::service::RpcService service actor started
2021-08-20T16:39:17.176553+08:00 INFO - ChainWater actor started
2021-08-20T16:39:17.185617+08:00 INFO - Start console, disable stderr output.
```

默认的 http 端口是 9850 ，websocket 端口是 9870。

## Examples

### 签名并发送交易

#### 签名消息

```
    String privateKeyString = "0x587737ebefb4961d377a3ab2f9ceb37b1fa96eb862dfaf954a4a1a99535dfec0";
    String publicKeyString = "0x32ed52d319694aebc5b52e00836e2f7c7d2c7c7791270ede450d21dbc90cbfa1";
    Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
    Assert.assertEquals(privateKeyString, Hex.encode(privateKey.value));
    Ed25519PublicKey publicKey = SignatureUtils.getPublicKey(privateKey);
    Assert.assertEquals(publicKeyString, Hex.encode(publicKey.value));
    String message = "Example `personal_sign` message";
    String signature = SignatureUtils.signPersonalMessage(privateKey, message);
```

#### 转账

```
    StarcoinClient starcoinClient = new StarcoinClient(ChainInfo.DEFAULT_DEV);
    String address = "0xf8af03dd08de49d81e4efd9e24c039cc";
    String privateKeyString = "0x7899f7cac425b5ce7239eb313db06ac2a93c731ea4512b857f975c0447176b25";
    Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
    AccountAddress sender = AccountAddressUtils.create(address);

    String toAddress = "0xd7f20befd34b9f1ab8aeae98b82a5a51";
    TypeObj typeObj = TypeObj.STC();
    String rst = starcoinClient.transfer(sender, privateKey, AccountAddressUtils.create(toAddress),
                typeObj, new BigInteger("1000"));
    System.out.println(rst);

```

#### 签名交易
```
    StarcoinClient starcoinClient = new StarcoinClient(ChainInfo.DEFAULT_DEV);
    String address = "0xf8af03dd08de49d81e4efd9e24c039cc";
    String privateKeyString = "0x7899f7cac425b5ce7239eb313db06ac2a93c731ea4512b857f975c0447176b25";
    Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
    AccountAddress sender = AccountAddressUtils.create(address);

    String toAddress = "0xd7f20befd34b9f1ab8aeae98b82a5a51";
    TypeObj typeObj = TypeObj.STC();

    BigInteger amount = new BigInteger("1000");
    
    //TransactionPayload payload = starcoinClient.buildTransferPayload(privateKey, typeObj, amount);
    //buildTransferPayload() 是私有方法
    TransactionPayload payload = Helpers.encode_peer_to_peer_v2_script_function(typeObj.toTypeTag(), 
    AccountAddressUtils.create(toAddress), amount);
    String rst = starcoinClient.submitTransaction(sender, privateKey, payload);
    
    System.out.println(rst);
```

#### 部署合约

```
    StarcoinClient starcoinClient = new StarcoinClient(ChainInfo.DEFAULT_DEV);
    String address = "0xf8af03dd08de49d81e4efd9e24c039cc";
    String privateKeyString = "0x7899f7cac425b5ce7239eb313db06ac2a93c731ea4512b857f975c0447176b25";
    Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
    AccountAddress sender = AccountAddressUtils.create(address);

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
    System.out.println(txn);
```

### 根据地址查询链上最新状态或者资源

#### 查询资源

```
    StarcoinClient starcoinClient = new StarcoinClient(ChainInfo.DEFAULT_DEV);
    String address = "0xf8af03dd08de49d81e4efd9e24c039cc";
    String privateKeyString = "0x7899f7cac425b5ce7239eb313db06ac2a93c731ea4512b857f975c0447176b25";
    Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
    AccountAddress sender = AccountAddressUtils.create(address);

    ResourceObj resourceObj = ResourceObj
                .builder()
                .moduleAddress("0xf8af03dd08de49d81e4efd9e24c039cc")
                .moduleName("MyCounter")
                .resourceName("Counter")
                .build();
    String rst = starcoinClient.getResource(sender, resourceObj);
    System.out.println(rst);
```

#### 查询最新状态

```
    StateRPCClient stateRPCClient = new StateRPCClient(new URL("http://localhost:9850"));
    stateRPCClient.getState("0xb75994d55eae88219dc57e7e62a11bc0");
```

#### 查询txn

```
    try{
        TransactionRPCClient client = new TransactionRPCClient(new URL("http://localhost:9850"));
        Transaction transaction = client.getTransactionByHash("0x9497fc455c962ee27a2321e88af6c8eeae9842f3d3ea70dc349cdbe004250897");
        TransactionPayload payload = transaction.getUserTransaction().getRawTransaction().getTransactionPayload();
        System.out.println("txn: " + payload.getClass());
    }catch (Exception ex){
        ex.printStackTrace();
    }
```

### 监听链上Event

```
        WebSocketService service = new WebSocketService("ws://localhost:9870", true);
        service.connect();
        StarcoinSubscriber subscriber = new StarcoinSubscriber(service);
        EventFilter eventFilter = new EventFilter(0, "b75994d55eae88219dc57e7e62a11bc0");
        Flowable<EventNotification> flowableTxns = subscriber.newTxnSendRecvEventNotifications(eventFilter);

        for (EventNotification notification : flowableTxns.blockingIterable()) {
            System.out.println(notification.getParams().getResult().toString());
        }
```

## License

starcoin-java is licensed as [Apache 2.0](./LICENSE).
