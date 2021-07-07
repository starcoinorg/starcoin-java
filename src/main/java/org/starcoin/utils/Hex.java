package org.starcoin.utils;

import com.google.common.io.BaseEncoding;
import com.novi.serde.Bytes;
import com.novi.serde.Unsigned;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

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
