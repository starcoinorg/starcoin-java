package org.starcoin.utils;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public class HashUtils {

  public static byte[] hashPrefix(String name) {
    return hash("STARCOIN::".getBytes(), name.getBytes());
  }

  public static byte[] hash(byte[] prefix, byte[] bytes) {
    SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();

    digestSHA3.update(prefix);
    digestSHA3.update(bytes);

    return digestSHA3.digest();
  }


  public static byte[] sha3Hash(byte[] data) {
    SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
    return digestSHA3.digest(data);
  }

  public static String sha3HashStr(byte[] data) {
    SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
    return Hex.toHexString(digestSHA3.digest(data));
  }
}
