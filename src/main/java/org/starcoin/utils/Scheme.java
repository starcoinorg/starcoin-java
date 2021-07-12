package org.starcoin.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Scheme {
  Ed25519(0),
  MultiEd25519(1);


  private int index;
}
