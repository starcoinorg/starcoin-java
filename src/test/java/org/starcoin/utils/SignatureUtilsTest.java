package org.starcoin.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.starcoin.types.Ed25519PrivateKey;
import org.starcoin.types.Ed25519PublicKey;
import org.starcoin.types.SignedMessage;

public class SignatureUtilsTest {


  @SneakyThrows
  @Test
  public void testSignPersonalMessage() {

    String privateKeyString = "0x587737ebefb4961d377a3ab2f9ceb37b1fa96eb862dfaf954a4a1a99535dfec0";
    String publicKeyString = "0x32ed52d319694aebc5b52e00836e2f7c7d2c7c7791270ede450d21dbc90cbfa1";
    String address = "0xd7f20befd34b9f1ab8aeae98b82a5a51";
    Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
    assertEquals(privateKeyString, Hex.encode(privateKey.value));
    Ed25519PublicKey publicKey = SignatureUtils.getPublicKey(privateKey);
    assertEquals(publicKeyString, Hex.encode(publicKey.value));
    String message = "helloworld";
    String rst = SignatureUtils
        .signPersonalMessage(AccountAddressUtils.create(address), privateKey, message);

    String rustRst = "0xd7f20befd34b9f1ab8aeae98b82a5a510a68656c6c6f776f726c64002032ed52d319694aebc5b52e00836e2f7c7d2c7c7791270ede450d21dbc90cbfa140f8ccf9ce7f6d45a5e16848301259722919567852113edd46542691fa06ed0eb283235190efc5b2925032b8af02c5bd89a34a923506e9a7a105c631dc0b0c580e";
    assertEquals(rst, rustRst);

    SignedMessage signedMessage = SignedMessage
        .bcsDeserialize(Hex.decode(rst));
    List<Byte> messageByteList = signedMessage.message.message;

    byte[] arr = ArrayUtils.toPrimitive(messageByteList.toArray(new Byte[0]));
    assertEquals(new String(arr), message);

    StarcoinClient starcoinClient = new StarcoinClient(ChainInfo.BARNARD);

    Optional<SignedMessage> verifyPersonalMessage = starcoinClient.verifyPersonalMessage(rustRst);

    assertTrue(verifyPersonalMessage.isPresent());
  }

  @Test
  public void testSign() {
    String privateKeyEncoded = "e424e16db235e3f3b9ef2475516c51d4c15aa5287ceb364213698bd551eab4f2";
    String message = "f7abb31497be2d952de2e1c64e2ce3edae7c4d9f5a522386a38af0c76457301e319ccfe5fc73a2cdae11c40f31ca1b619d0000000000000002000000000000000000000000000000010f5472616e73666572536372697074730f706565725f746f5f706565725f76320107000000000000000000000000000000010353544303535443000210d7f20befd34b9f1ab8aeae98b82a5a511010270000000000000000000000000000809698000000000001000000000000000d3078313a3a5354433a3a5354433d55e66000000000fb";

    String rst = "b287e9259649cfd38b075993fa825ddd7c0697a365d4981505a6046e25a2c4009018e6cdeb76dfe07f29197cf43ff34971fb55140a58eea07f7d21d0c261c70b";

    Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyEncoded);

    byte[] m = SignatureUtils.ed25519Sign(privateKey, Hex.decode(message));
    System.out.println(Hex.encode(m));
  }
}