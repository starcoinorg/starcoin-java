package org.starcoin.utils;

import java.util.Arrays;
import org.starcoin.types.AccountAddress;

public class AuthenticationKeyUtils {

  private static byte[] ED25519_KEY_SCHEME = new byte[]{(byte) Scheme.Ed25519.getIndex()};
  private static byte[] MULTI_ED25519_KEY_SCHEME = new byte[]{
      (byte) Scheme.MultiEd25519.getIndex()};


  public static String authenticationKeyED25519(Scheme scheme, byte[] publicKey) {
    return Hex.encode(HashUtils.hash(publicKey, new byte[]{(byte) scheme.getIndex()}));
  }


  public static AccountAddress accountAddress(String authenticationKey) {
    byte[] bytes = Hex.decode(authenticationKey);
    byte[] address = Arrays
        .copyOfRange(bytes, bytes.length - AccountAddressUtils.ACCOUNT_ADDRESS_LENGTH,
            bytes.length);
    return AccountAddressUtils.create(address);
  }

  public static Scheme getScheme(String authenticationKey) {
//TODO
    return Scheme.Ed25519;
  }
}
