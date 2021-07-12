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


  @Test
  public void testVerify() {
    StarcoinClient starcoinClient = new StarcoinClient("https://barnard-seed.starcoin.org", 254);

    String privateKeyString = "0xda82fa47266c40c84d76e20b0a278d1b27ae4a14c9c318e54457722d739371b0";
    String publicKeyString = "0xfe18b0900baa684231da3519ff5387c4b18f76ae5209b474b8dd06cb5f7ff464";
    String address = "0xfa0d5060eb2622e26b4dc307a481db0c";

    Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
    assertEquals(privateKeyString, Hex.encode(privateKey.value));
    Ed25519PublicKey publicKey = SignatureUtils.getPublicKey(privateKey);
    assertEquals(publicKeyString, Hex.encode(publicKey.value));

    String message = "Example `personal_sign` message 中文";
    String rst = starcoinClient
        .signPersonalMessage(AccountAddressUtils.create(address), privateKey, message);

    String rustRst = "0xfa0d5060eb2622e26b4dc307a481db0c264578616d706c652060706572736f6e616c5f7369676e60206d65737361676520e4b8ade696870020fe18b0900baa684231da3519ff5387c4b18f76ae5209b474b8dd06cb5f7ff46440ff4f9bb5df5386b12629650873267f3b26619829c8b76d6e4a66a8fb11275b133f4ebe48cfb6c1d7dda1b9a89912a3ed0807945cb26ed3206da297e08111c20bfe";
    assertEquals(rst, rustRst);

  }

  @SneakyThrows
  @Test
  public void testSignPersonalMessage() {
    StarcoinClient starcoinClient = new StarcoinClient("https://barnard-seed.starcoin.org", 254);

    String privateKeyString = "0xda82fa47266c40c84d76e20b0a278d1b27ae4a14c9c318e54457722d739371b0";
    String publicKeyString = "0xfe18b0900baa684231da3519ff5387c4b18f76ae5209b474b8dd06cb5f7ff464";
    String address = "0xfa0d5060eb2622e26b4dc307a481db0c";

    Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
    assertEquals(privateKeyString, Hex.encode(privateKey.value));
    Ed25519PublicKey publicKey = SignatureUtils.getPublicKey(privateKey);
    assertEquals(publicKeyString, Hex.encode(publicKey.value));

    String message = "Example `personal_sign` message 中文";
    String rst = starcoinClient
        .signPersonalMessage(AccountAddressUtils.create(address), privateKey, message);

    String rustRst = "0xfa0d5060eb2622e26b4dc307a481db0c264578616d706c652060706572736f6e616c5f7369676e60206d65737361676520e4b8ade696870020fe18b0900baa684231da3519ff5387c4b18f76ae5209b474b8dd06cb5f7ff46440ff4f9bb5df5386b12629650873267f3b26619829c8b76d6e4a66a8fb11275b133f4ebe48cfb6c1d7dda1b9a89912a3ed0807945cb26ed3206da297e08111c20bfe";
    assertEquals(rst, rustRst);

    SignedMessage signedMessage = SignedMessage
        .bcsDeserialize(Hex.decode(rst));
    List<Byte> messageByteList = signedMessage.message.message;

    byte[] arr = ArrayUtils.toPrimitive(messageByteList.toArray(new Byte[0]));
    assertEquals(new String(arr), message);

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