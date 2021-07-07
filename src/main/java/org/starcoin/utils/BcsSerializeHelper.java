package org.starcoin.utils;

import java.math.BigInteger;
import java.util.List;
import lombok.SneakyThrows;

public class BcsSerializeHelper {


  @SneakyThrows
  public static byte[] serializeU64(Long v) {
    long value = v.longValue();
    com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
    serializer.increase_container_depth();
    serializer.serialize_u64(value);
    serializer.decrease_container_depth();
    return serializer.get_bytes();
  }

  @SneakyThrows
  public static byte[] serializeU128(BigInteger v) {
    com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
    serializer.increase_container_depth();
    serializer.serialize_u128(v);
    serializer.decrease_container_depth();
    return serializer.get_bytes();
  }

  @SneakyThrows
  public static byte[] serializeString(String v) {
    com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
    serializer.increase_container_depth();
    serializer.serialize_str(v);
    serializer.decrease_container_depth();
    return serializer.get_bytes();
  }

  @SneakyThrows
  public static byte[] serializeU8(Byte v) {
    com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
    serializer.increase_container_depth();
    serializer.serialize_u8(v);
    serializer.decrease_container_depth();
    return serializer.get_bytes();
  }

  @SneakyThrows
  public static byte[] serializeVectorU8(List<Byte> value) {
    com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();

    serializer.serialize_len(value.size());
    for (@com.novi.serde.Unsigned Byte item : value) {
      serializer.serialize_u8(item);
    }
    return serializer.get_bytes();
  }
}
