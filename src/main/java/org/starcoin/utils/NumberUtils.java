package org.starcoin.utils;

import com.novi.bcs.BcsDeserializer;
import com.novi.serde.DeserializationError;

import java.math.BigInteger;

public class NumberUtils {

    public static BigInteger getValueFromString(String amount) {
        try {
            return new BcsDeserializer(Hex.decode(amount)).deserialize_u128();
        } catch (DeserializationError deserializationError) {
            deserializationError.printStackTrace();
        }
        return BigInteger.ZERO;
    }
}
