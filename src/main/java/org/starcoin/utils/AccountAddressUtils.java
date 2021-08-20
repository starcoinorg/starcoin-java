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
