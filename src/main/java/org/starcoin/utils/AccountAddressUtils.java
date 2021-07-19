package org.starcoin.utils;

import org.starcoin.types.AccountAddress;

import java.util.ArrayList;
import java.util.List;

public class AccountAddressUtils {

    public static int ACCOUNT_ADDRESS_LENGTH = 16;

    public static AccountAddress create(byte[] bytes) {
        if (bytes.length != ACCOUNT_ADDRESS_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("account address bytes length must be {}", ACCOUNT_ADDRESS_LENGTH));
        }
        List<Byte> address = new ArrayList<Byte>();
        for (int i = 0; i < ACCOUNT_ADDRESS_LENGTH; i++) {
            address.add(Byte.valueOf(bytes[i]));
        }
        return new AccountAddress(address);
    }

    public static AccountAddress create(String address) {
        if (address.startsWith("0x")) {
            return create(Hex.decode(address.substring("0x".length())));
        }
        return create(Hex.decode(address));
    }

    public static String hex(AccountAddress address) {
        return Hex.encode(address.value);
    }

    public static byte[] bytes(AccountAddress address) {
        byte[] ret = new byte[ACCOUNT_ADDRESS_LENGTH];
        for (int i = 0; i < ACCOUNT_ADDRESS_LENGTH; i++) {
            ret[i] = address.value.get(i);
        }
        return ret;
    }
}
