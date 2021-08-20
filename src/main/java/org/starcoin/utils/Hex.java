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

import com.google.common.io.BaseEncoding;
import com.novi.serde.Bytes;
import com.novi.serde.Unsigned;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Hex {

    public static byte[] decode(String hex) {
        if (StringUtils.startsWithIgnoreCase(hex, "0x")) {
            return BaseEncoding.base16().decode(hex.substring(2).toUpperCase());
        }
        return BaseEncoding.base16().decode(hex.toUpperCase());
    }

    public static String encode(byte[] bytes) {
        return "0x" + BaseEncoding.base16().encode(bytes).toLowerCase();
    }

    public static String encode(List<Byte> bytes) {
        return encode(bytes.toArray(new Byte[0]));
    }

    public static String encode(@Unsigned Byte[] bytes) {
        return encode(ArrayUtils.toPrimitive(bytes));
    }

    public static String encode(Bytes bytes) {
        return encode(bytes.content());
    }
}
