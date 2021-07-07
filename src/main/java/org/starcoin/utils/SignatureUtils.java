package org.starcoin.utils;

import com.novi.serde.Bytes;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.starcoin.types.Ed25519PrivateKey;
import org.starcoin.types.Ed25519PublicKey;
import org.starcoin.types.Ed25519Signature;
import org.starcoin.types.RawUserTransaction;
import org.starcoin.types.SignedUserTransaction;
import org.starcoin.types.TransactionAuthenticator;
import org.starcoin.types.TransactionAuthenticator.Ed25519;

public class SignatureUtils {

  @SneakyThrows
  public static SignedUserTransaction signTxn(Ed25519PrivateKey privateKey,
      RawUserTransaction rawUserTransaction) {
    byte[] bytes = com.google.common.primitives.Bytes
        .concat(HashUtils.hashPrefix("RawUserTransaction"), rawUserTransaction.bcsSerialize());

    System.out.println(Hex.encode(bytes));
    byte[] signRst = ed25519Sign(privateKey, bytes);
    System.out.println(Hex.encode(signRst));

    Ed25519PublicKey publicKey = getPublicKey(privateKey);
    Ed25519Signature signature = new Ed25519Signature(new Bytes(signRst));
    TransactionAuthenticator transactionAuthenticator = new TransactionAuthenticator.Ed25519(
        publicKey, signature);
    SignedUserTransaction signedUserTransaction = new SignedUserTransaction(rawUserTransaction,
        transactionAuthenticator);
    return signedUserTransaction;
  }

  @SneakyThrows
  public static String signPersonalMessage(Ed25519PrivateKey privateKey, String message) {
    Ed25519PublicKey publicKey = getPublicKey(privateKey);
    byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

    Ed25519Signature signature = new Ed25519Signature(new com.novi.serde.Bytes(messageBytes));

    Ed25519 ed25519 = new Ed25519(publicKey, signature);

//    @TODO
    byte[] bytes = com.google.common.primitives.Bytes
        .concat(HashUtils.hashPrefix("TransactionAuthenticator"), ed25519.bcsSerialize());

    byte[] signRst = ed25519Sign(privateKey, bytes);
    return Hex.encode(signRst);
  }


  @SneakyThrows
  public static Ed25519PublicKey getPublicKey(Ed25519PrivateKey privateKey) {
    Ed25519PrivateKeyParameters key = new Ed25519PrivateKeyParameters(
        privateKey.value.content(),
        0);
    Ed25519PublicKeyParameters publicKeyParameters = key.generatePublicKey();
    Ed25519PublicKey publicKey = new Ed25519PublicKey(
        new Bytes(publicKeyParameters.getEncoded()));

    return publicKey;
  }

  @SneakyThrows
  public static byte[] ed25519Sign(Ed25519PrivateKey privateKey, byte[] data) {

    Ed25519PrivateKeyParameters key = new Ed25519PrivateKeyParameters(privateKey.value.content(),
        0);
    Ed25519Signer signer = new Ed25519Signer();
    signer.init(true, key);
    signer.update(data, 0, data.length);
    byte[] rst = signer.generateSignature();

    return rst;
  }

  public static Ed25519PrivateKey strToPrivateKey(String privateKeyString) {
    return new Ed25519PrivateKey(new Bytes(Hex.decode(privateKeyString)));
  }
}
