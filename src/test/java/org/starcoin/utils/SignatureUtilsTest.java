package org.starcoin.utils;

import com.novi.serde.Bytes;
import lombok.SneakyThrows;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.junit.Test;
import org.starcoin.types.*;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignatureUtilsTest {


    @SneakyThrows
    @Test
    public void testSignPersonalMessage() {
        String privateKeyString = "0x94be71d34e32184138cbcad8d24a8deb510aaa74af579f74877b647392421a3f";
        String publicKeyString = "0xa7d45ac5f1d1b5cb1b23303938b6da6b731acff6b05110e2aa0e3c1e677eeb47";
        Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
        assertEquals(privateKeyString, Hex.encode(privateKey.value));
        Ed25519PublicKey publicKey = SignatureUtils.getPublicKey(privateKey);
        assertEquals(publicKeyString, Hex.encode(publicKey.value));
        String message = "0568656c6c6f";
        String signature = SignatureUtils.signPersonalMessage(privateKey, message);

        SignatureUtils.ed25519Verify(publicKey, message.getBytes(StandardCharsets.UTF_8), Hex.decode(signature));
    }


    @SneakyThrows
    @Test
    public void testSignMessage() {
        Ed25519PrivateKeyParameters privateKeyParameters = new Ed25519PrivateKeyParameters(Hex.decode("94be71d34e32184138cbcad8d24a8deb510aaa74af579f74877b647392421a3f"), 0);
        Signer signer = new Ed25519Signer();
        signer.init(true, privateKeyParameters);
        byte[] message = Hex.decode("0x68656c6c6f");
        signer.update(message, 0, message.length);
        byte[] signature = signer.generateSignature();
        System.out.println("msg:" + Hex.encode(signature));
        Ed25519PublicKeyParameters publicKeyParameters = new Ed25519PublicKeyParameters(Hex.decode("0xa7d45ac5f1d1b5cb1b23303938b6da6b731acff6b05110e2aa0e3c1e677eeb47"), 0);
        Signer verifier = new Ed25519Signer();
        verifier.init(false, publicKeyParameters);
        verifier.update(message, 0, message.length);
        boolean shouldVerify = verifier.verifySignature(signature);
        assertTrue(shouldVerify);
    }

    @SneakyThrows
    @Test
    public void testVerifySignedMessage() {
        String messageBytes = "0xb9cf94b29d74cb9a029e3d9db55e28a90568656c6c6f0020a7d45ac5f1d1b5cb1b23303938b6da6b731acff6b05110e2aa0e3c1e677eeb4740a2c75841ff3bd7d0903a90bba00c5e19a10b0c4434bbdf5dd38f055581f73d1b537808a43045ac7200f6ba7568b0947258477b62e17f09b1a6bc1771c43e1f03ff";
        SignedMessage message = SignedMessage.bcsDeserialize(Hex.decode(messageBytes));
        boolean checked = SignatureUtils.signedMessageCheckSignature(message);
        assertTrue(checked);
        checked = SignatureUtils.signedMessageCheckAccount(message, new ChainId((byte) 255), null);
        assertTrue(checked);
    }

    @SneakyThrows
    @Test
    public void testSignedMessageWithResource() {
        String privateKeyString = "0x99d4590f7707c8277c9c591ffd56c2381e4e83c2218a00ce8d9745400843faab";
        Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
        String message = "abcded346ddtest";
//        String message = "0f616263646564333436646474657374";
        Bytes signingBytes = Bytes.valueOf(message.getBytes());
        SigningMessage signingMessage = new SigningMessage(signingBytes.toList());
        String signedMsg = "0xfab981cf1ee57d043be6f4f80b5575060f61626364656433343664647465737400204a4f7becc8b33af1ad34ed6195ab1109c4793e915759aa0eb26792fed4674f3d40097e0a748706c30de6457261bfc40ca0b83704072fb7614aac5b2643fe30860ed2e256b832e5160cd9da14d0fa183599d5e89b3169c8aa764ff86fc16f115600fd";
        String shouldSigned = SignatureUtils.signPersonalBcsSerializedMessage(privateKey, Hex.encode(signingMessage.bcsSerialize()));
        SignedMessage signedMessage = SignedMessage.bcsDeserialize(Hex.decode(signedMsg));
        byte[] sigature = ((TransactionAuthenticator.Ed25519) signedMessage.authenticator).signature.value.content();
        System.out.println(Hex.encode(signedMessage.authenticator.bcsSerialize()));
        assertEquals(shouldSigned, Hex.encode(sigature));
        // verify signature
        boolean checked = SignatureUtils.signedMessageCheckSignature(signedMessage);
        assertTrue(checked);
        //verify check account
        String resource = "0x205572bd99b2d9e6161d369a745b04bf9afab981cf1ee57d043be6f4f80b55750601fab981cf1ee57d043be6f4f80b55750601fab981cf1ee57d043be6f4f80b5575064c57000000000000180000000000000000fab981cf1ee57d043be6f4f80b55750630b6020000000000180100000000000000fab981cf1ee57d043be6f4f80b5575060100000000000000180200000000000000fab981cf1ee57d043be6f4f80b5575064c57000000000000";
        AccountResource accountResource = AccountResource.bcsDeserialize(Hex.decode(resource));
        checked = SignatureUtils.signedMessageCheckAccount(signedMessage, new ChainId((byte) 253), accountResource);
        assertTrue(checked);
    }

}