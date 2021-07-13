package org.starcoin.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChainInfo {


    //  MAIN("main", "", 1),
    BARNARD("barnard", "https://barnard-seed.starcoin.org", 251);
    private String name;
    private String url;
    private int chainId;

}
