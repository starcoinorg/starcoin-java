package org.starcoin.utils;

import com.novi.serde.Bytes;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.SneakyThrows;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.starcoin.types.AccountAddress;
import org.starcoin.types.Ed25519PrivateKey;
import org.starcoin.types.Ed25519PublicKey;
import org.starcoin.types.Ed25519Signature;
import org.starcoin.types.RawUserTransaction;
import org.starcoin.types.SignedMessage;
import org.starcoin.types.SignedUserTransaction;
import org.starcoin.types.SigningMessage;
import org.starcoin.types.TransactionAuthenticator;


public class SignatureUtils {

  @SneakyThrows
  public static SignedUserTransaction signTxn(Ed25519PrivateKey privateKey,
      RawUserTransaction rawUserTransaction) {
    byte[] bytes = com.google.common.primitives.Bytes
        .concat(HashUtils.hashPrefix("RawUserTransaction"), rawUserTransaction.bcsSerialize());
    byte[] signRst = ed25519Sign(privateKey, bytes);
    Ed25519PublicKey publicKey = getPublicKey(privateKey);
    Ed25519Signature signature = new Ed25519Signature(new Bytes(signRst));
    TransactionAuthenticator transactionAuthenticator = new TransactionAuthenticator.Ed25519(
        publicKey, signature);
    SignedUserTransaction signedUserTransaction = new SignedUserTransaction(rawUserTransaction,
        transactionAuthenticator);
    return signedUserTransaction;
  }

  @SneakyThrows
  public static String signPersonalMessage(AccountAddress address, Ed25519PrivateKey privateKey,
      String message) {
    Ed25519PublicKey publicKey = getPublicKey(privateKey);

    List<Byte> arrays = com.google.common.primitives.Bytes
        .asList(message.getBytes(StandardCharsets.UTF_8));
    SigningMessage signingMessage = new SigningMessage(arrays);
    byte[] bytes = com.google.common.primitives.Bytes
        .concat(HashUtils.hashPrefix("SigningMessage"), signingMessage.bcsSerialize());
    byte[] signatureBytes = ed25519Sign(privateKey, bytes);
    Ed25519Signature signature = new Ed25519Signature(Bytes.valueOf(signatureBytes));
    TransactionAuthenticator authenticator = new TransactionAuthenticator.Ed25519(
        publicKey, signature);
    SignedMessage signedMessage = new SignedMessage(address, signingMessage, authenticator);
    return Hex.encode(signedMessage.bcsSerialize());

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


  @SneakyThrows
  public static boolean verifyPersonalMessage(Ed25519PublicKey ed25519PublicKey, byte[] message,
      byte[] signedBytes) {
    List<Byte> arrays = com.google.common.primitives.Bytes.asList(message);
    SigningMessage signingMessage = new SigningMessage(arrays);

    byte[] messageArray = com.google.common.primitives.Bytes
        .concat(HashUtils.hashPrefix("SigningMessage"), signingMessage.bcsSerialize());

    Ed25519PublicKeyParameters pk = new Ed25519PublicKeyParameters(
        ed25519PublicKey.value.content(),
        0);
    Ed25519Signer signer = new Ed25519Signer();
    signer.init(false, pk);
    signer.update(messageArray, 0, messageArray.length);
    return signer.verifySignature(signedBytes);

  }

  public static Ed25519PrivateKey strToPrivateKey(String privateKeyString) {
    return new Ed25519PrivateKey(new Bytes(Hex.decode(privateKeyString)));
  }

}
