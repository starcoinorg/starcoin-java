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
import lombok.SneakyThrows;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.starcoin.types.*;
import org.starcoin.types.TransactionAuthenticator.Ed25519;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import static org.starcoin.utils.HashUtils.hashStarcoinSignedUserTransaction;

public class SignatureUtils {

    public static final String GAS_TOKEN_CODE_STC = "0x1::STC::STC";

    @SneakyThrows
    public static String getTransactionHash(Ed25519PrivateKey ed25519PrivateKey, Integer chainId, AccountAddress accountAddress,
                                            BigInteger accountSeqNumber,
                                            TransactionPayload payload,
                                            BigInteger gasPrice, BigInteger gasLimit,
                                            Long expirationTimestampSecs) {
        SignedUserTransaction signedUserTransaction = createSignedUserTransaction(ed25519PrivateKey, chainId, accountAddress,
                accountSeqNumber, payload, gasPrice, gasLimit, expirationTimestampSecs);
        byte[] signedMessage = signedUserTransaction.bcsSerialize();
        return hashStarcoinSignedUserTransaction(signedMessage);
    }

    @SneakyThrows
    public static SignedUserTransaction createSignedUserTransaction(Ed25519PrivateKey ed25519PrivateKey, Integer chainId, AccountAddress accountAddress,
                                                                    BigInteger accountSeqNumber,
                                                                    TransactionPayload payload,
                                                                    BigInteger gasPrice, BigInteger gasLimit,
                                                                    Long expirationTimestampSecs) {
        RawUserTransaction rawUserTransaction = createRawUserTransaction(chainId, accountAddress,
                accountSeqNumber, payload, gasPrice, gasLimit, expirationTimestampSecs);

        return SignatureUtils.signTxn(ed25519PrivateKey, rawUserTransaction);
    }


    public static RawUserTransaction createRawUserTransaction(Integer chainId, AccountAddress accountAddress,
                                                              BigInteger accountSeqNumber,
                                                              TransactionPayload payload,
                                                              BigInteger gasPrice, BigInteger gasLimit,
                                                              Long expirationTimestampSecs) {
        ChainId chainIdObj = new ChainId(chainId.byteValue());
        return new RawUserTransaction(accountAddress, accountSeqNumber.longValue(), payload,
                gasLimit.longValue(), gasPrice.longValue(), GAS_TOKEN_CODE_STC,
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

    @SneakyThrows
    public static String signPersonalMessage(Ed25519PrivateKey privateKey, String message) {
        Ed25519PublicKey publicKey = getPublicKey(privateKey);
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        Ed25519Signature signature = new Ed25519Signature(new com.novi.serde.Bytes(messageBytes));

        Ed25519 ed25519 = new Ed25519(publicKey, signature);

//    @TODO
        byte[] bytes = com.google.common.primitives.Bytes
                .concat(HashUtils.hashWithStarcoinPrefix("TransactionAuthenticator"), ed25519.bcsSerialize());

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
