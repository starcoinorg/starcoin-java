package org.starcoin.utils;

import com.novi.bcs.BcsDeserializer;
import com.novi.serde.Bytes;
import lombok.SneakyThrows;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.junit.Test;
import org.starcoin.types.*;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class SignatureUtilsTest {


    @SneakyThrows
    @Test
    public void testSignPersonalMessage() {

        String privateKeyString = "0x587737ebefb4961d377a3ab2f9ceb37b1fa96eb862dfaf954a4a1a99535dfec0";
        String publicKeyString = "0x32ed52d319694aebc5b52e00836e2f7c7d2c7c7791270ede450d21dbc90cbfa1";
        Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyString);
        assertEquals(privateKeyString, Hex.encode(privateKey.value));
        Ed25519PublicKey publicKey = SignatureUtils.getPublicKey(privateKey);
        assertEquals(publicKeyString, Hex.encode(publicKey.value));
        String message = "Example `personal_sign` message";
        String rst = SignatureUtils.signPersonalMessage(privateKey, message);

    }

    @SneakyThrows
    @Test
    public void testVerifyPersonalMessage() {
        String signedMessage = "0x290c7b35320a4dd26f651fd184373fe7264578616d706c652060706572736f6e616c5f7369676e60206d65737361676520e4b8ade696870020a4b14715924ad0627409fbabed863e360b066c039ea46c0094c1ced721d9a7d9404e7f2d656b6640631c71684fe46c93de759571f82d7cc5750a913c978e1b8170cd73238c82addd0fc915eab6a3ef27b3447506513d035bfd1fb21bb33b08750601";
        BcsDeserializer deserializer = new BcsDeserializer(Hex.decode(signedMessage));
        AccountAddress accountAddress = AccountAddress.deserialize(deserializer);
        System.out.println("address: " + accountAddress);
        Bytes message = deserializer.deserialize_bytes();
        System.out.println("message: " + new String(message.content()));
        TransactionAuthenticator.Ed25519 authenticator = (TransactionAuthenticator.Ed25519) TransactionAuthenticator.deserialize(deserializer);
        ChainId chainId = ChainId.deserialize(deserializer);
        System.out.println("chainId: " + chainId.id);

        Bytes signatureBytes = authenticator.signature.value;
        System.out.println("signature: " + Hex.encode(signatureBytes.content()));

        Bytes publicKeyBytes = authenticator.public_key.value;
        System.out.println("publicKey: " + Hex.encode(publicKeyBytes.content()));

        // verify
        Ed25519PublicKey ed25519PublicKey = authenticator.public_key;
        Ed25519Signature signature = new Ed25519Signature(message);
        TransactionAuthenticator.Ed25519 ed25519 = new TransactionAuthenticator.Ed25519(ed25519PublicKey, signature);
        assert ed25519Verify(ed25519PublicKey, ed25519.bcsSerialize(), authenticator.signature.value.content());
    }

    @SneakyThrows
    public static boolean ed25519Verify(Ed25519PublicKey publicKey, byte[] data, byte[] signature) {
        Ed25519PublicKeyParameters key = new Ed25519PublicKeyParameters(publicKey.value.content(),
                0);
        return verify(data, signature, key);
    }

    private static boolean verify(byte[] data, byte[] signature, Ed25519PublicKeyParameters key) {
        Ed25519Signer signer = new Ed25519Signer();
        signer.init(false, key);
        signer.update(data, 0, data.length);
        return signer.verifySignature(signature);
    }

    @Test
    public void testSign() {
        String privateKeyEncoded = "e424e16db235e3f3b9ef2475516c51d4c15aa5287ceb364213698bd551eab4f2";
        String message = "f7abb31497be2d952de2e1c64e2ce3edae7c4d9f5a522386a38af0c76457301e319ccfe5fc73a2cdae11c40f31ca1b619d0000000000000002000000000000000000000000000000010f5472616e73666572536372697074730f706565725f746f5f706565725f76320107000000000000000000000000000000010353544303535443000210d7f20befd34b9f1ab8aeae98b82a5a511010270000000000000000000000000000809698000000000001000000000000000d3078313a3a5354433a3a5354433d55e66000000000fb";
        String rst = "b287e9259649cfd38b075993fa825ddd7c0697a365d4981505a6046e25a2c4009018e6cdeb76dfe07f29197cf43ff34971fb55140a58eea07f7d21d0c261c70b";
        Ed25519PrivateKey privateKey = SignatureUtils.strToPrivateKey(privateKeyEncoded);
        byte[] m = SignatureUtils.ed25519Sign(privateKey, Hex.decode(message));

        Ed25519PrivateKeyParameters key = new Ed25519PrivateKeyParameters(privateKey.value.content(),0);
        Ed25519PublicKeyParameters publicKey = key.generatePublicKey();

        verify(message.getBytes(StandardCharsets.UTF_8),m,publicKey);
    }
}