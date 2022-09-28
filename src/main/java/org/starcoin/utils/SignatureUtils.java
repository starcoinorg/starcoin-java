/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.starcoin.utils;

import com.novi.serde.Bytes;
import com.novi.serde.DeserializationError;
import com.novi.serde.SerializationError;
import lombok.SneakyThrows;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.starcoin.types.*;
import org.starcoin.types.TransactionAuthenticator.Ed25519;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class SignatureUtils {

    public static final String GAS_TOKEN_CODE_STC = "0x1::STC::STC";

    /**
     * Compute transaction hash locally.
     */
    @SneakyThrows
    public static String getTransactionHash(Ed25519PrivateKey ed25519PrivateKey, Integer chainId, AccountAddress accountAddress,
                                            BigInteger accountSeqNumber,
                                            TransactionPayload payload,
                                            BigInteger gasPrice, BigInteger gasLimit,
                                            Long expirationTimestampSecs, String gasTokenCode) {
        SignedUserTransaction signedUserTransaction = newSignedUserTransaction(ed25519PrivateKey, chainId, accountAddress,
                accountSeqNumber, payload, gasPrice, gasLimit, expirationTimestampSecs, gasTokenCode);
        return getTransactionHash(signedUserTransaction);
    }


    /**
     * Compute transaction hash locally.
     */
    @SneakyThrows
    public static String getTransactionHash(SignedUserTransaction signedUserTransaction) throws SerializationError {
        byte[] message = signedUserTransaction.bcsSerialize();
        return hashStarcoinSignedUserTransactionHex(message);
    }

    private static String hashStarcoinSignedUserTransactionHex(byte[] m) {
        byte[] bytesToHash = com.google.common.primitives.Bytes
                .concat(HashUtils.hashWithStarcoinPrefix("SignedUserTransaction"), m);
        return Hex.encode(HashUtils.sha3Hash(bytesToHash));
    }

    @SneakyThrows
    private static SignedUserTransaction newSignedUserTransaction(Ed25519PrivateKey ed25519PrivateKey, Integer chainId, AccountAddress accountAddress,
                                                                  BigInteger accountSeqNumber,
                                                                  TransactionPayload payload,
                                                                  BigInteger gasPrice, BigInteger gasLimit,
                                                                  Long expirationTimestampSecs, String gasTokenCode) {
        RawUserTransaction rawUserTransaction = newRawUserTransaction(chainId, accountAddress,
                accountSeqNumber, payload, gasPrice, gasLimit, expirationTimestampSecs, gasTokenCode);

        return SignatureUtils.signTxn(ed25519PrivateKey, rawUserTransaction);
    }


    private static RawUserTransaction newRawUserTransaction(Integer chainId, AccountAddress accountAddress,
                                                            BigInteger accountSeqNumber,
                                                            TransactionPayload payload,
                                                            BigInteger gasPrice, BigInteger gasLimit,
                                                            Long expirationTimestampSecs, String gasTokenCode) {
        ChainId chainIdObj = new ChainId(chainId.byteValue());
        return new RawUserTransaction(accountAddress, accountSeqNumber.longValue(), payload,
                gasLimit.longValue(), gasPrice.longValue(),
                gasTokenCode == null ? GAS_TOKEN_CODE_STC : gasTokenCode,
                expirationTimestampSecs,
                chainIdObj);
    }

    @SneakyThrows
    public static SignedUserTransaction signTxn(Ed25519PrivateKey privateKey,
                                                RawUserTransaction rawUserTransaction) {
        byte[] bytes = com.google.common.primitives.Bytes
                .concat(HashUtils.hashWithStarcoinPrefix("RawUserTransaction"), rawUserTransaction.bcsSerialize());
        byte[] signRst = ed25519Sign(privateKey, bytes);
        Ed25519PublicKey publicKey = getPublicKey(privateKey);
        Ed25519Signature signature = new Ed25519Signature(new Bytes(signRst));
        TransactionAuthenticator transactionAuthenticator = new TransactionAuthenticator.Ed25519(
                publicKey, signature);
        SignedUserTransaction signedUserTransaction = new SignedUserTransaction(rawUserTransaction,
                transactionAuthenticator);
        return signedUserTransaction;
    }


    /**
     * Sign personal BCS serialized SigningMessage.
     *
     * @param privateKey           Private key
     * @param serializedMessageHex BCS serialized SigningMessage hex.
     * @return Signature
     */
    @SneakyThrows
    public static String signPersonalBcsSerializedMessage(Ed25519PrivateKey privateKey, String serializedMessageHex) {
        SigningMessage signingMessage = SigningMessage.bcsDeserialize(Hex.decode(serializedMessageHex));
        byte[] signRst = ed25519Sign(privateKey, SigningMessage.hashBytes(signingMessage));
        return Hex.encode(signRst);
    }

    /**
     * Sign personal string message.
     *
     * @param privateKey Private key
     * @param message    string message to be signed as UTF_8 bytes
     * @return Signature
     */
    @SneakyThrows
    public static String signPersonalMessage(Ed25519PrivateKey privateKey, String message) {
        Bytes signingBytes = Bytes.valueOf(message.getBytes(StandardCharsets.UTF_8));
        SigningMessage signingMessage = new SigningMessage(signingBytes.toList());
        byte[] signRst = ed25519Sign(privateKey, SigningMessage.hashBytes(signingMessage));
        return Hex.encode(signRst);
    }


    public static boolean signedMessageCheckAccount(SignedMessage signedMessage, ChainId chainId, AccountResource accountResource) throws DeserializationError, SerializationError {
        AuthenticationKey authenticationKeyInMessage = TransactionAuthenticator.authenticationKey(signedMessage.authenticator);
        AuthenticationKey authenticationKeyOnChain;
        if (accountResource == null) {
            return signedMessage.account.equals(authenticationKeyInMessage.derivedAddress());
        } else {
            if (signedMessage.chain_id.equals(chainId)) {
                Bytes serdeBytes = Bytes.fromList(accountResource.authentication_key);
                if (serdeBytes != null) {
                    authenticationKeyOnChain = new AuthenticationKey(serdeBytes);
                    return authenticationKeyOnChain.equals(authenticationKeyInMessage);
                }
            }
        }
        return false;
    }

    public static boolean signedMessageCheckSignature(SignedMessage signedMessage) throws SerializationError, DeserializationError {
        return signingMessageCheckSignature(signedMessage.message, signedMessage.authenticator);
    }

    static boolean signingMessageCheckSignature(SigningMessage message, TransactionAuthenticator authenticator) throws SerializationError, DeserializationError {
        if (authenticator instanceof TransactionAuthenticator.Ed25519) {
            Ed25519PublicKey publicKey = ((Ed25519) authenticator).public_key;
            Ed25519Signature ed25519Signature = ((Ed25519) authenticator).signature;
            return ed25519Verify(publicKey, SigningMessage.hashBytes(message), ed25519Signature.value.content());
        } else if (authenticator instanceof TransactionAuthenticator.MultiEd25519) {
            MultiEd25519PublicKey publicKey = ((TransactionAuthenticator.MultiEd25519) authenticator).public_key;
            MultiEd25519Signature multiEd25519Signature = ((TransactionAuthenticator.MultiEd25519) authenticator).signature;
            return multiEd25519Verify(publicKey, SigningMessage.hashBytes(message), multiEd25519Signature.value.content());
        }
        return false;
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


    public static boolean ed25519Verify(Ed25519PublicKey publicKey, byte[] signingMessage, byte[] signature) {
        Ed25519PublicKeyParameters key = new Ed25519PublicKeyParameters(publicKey.value.content(), 0);
        Ed25519Signer signer = new Ed25519Signer();
        signer.init(false, key);
        signer.update(signingMessage, 0, signingMessage.length);
        return signer.verifySignature(signature);
    }

    public static boolean multiEd25519Verify(MultiEd25519PublicKey publicKey, byte[] signingMessage, byte[] signature) {
        Ed25519PublicKeyParameters key = new Ed25519PublicKeyParameters(publicKey.value.content(), 0);
        Ed25519Signer signer = new Ed25519Signer();
        signer.init(false, key);
        signer.update(signingMessage, 0, signingMessage.length);
        return signer.verifySignature(signature);
    }

    public static Ed25519PrivateKey strToPrivateKey(String privateKeyString) {
        return new Ed25519PrivateKey(new Bytes(Hex.decode(privateKeyString)));
    }
}
