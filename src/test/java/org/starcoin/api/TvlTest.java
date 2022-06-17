package org.starcoin.api;


import org.junit.Test;
import org.starcoin.bean.ListResource;
import org.starcoin.bean.TokenSwapLiquidityToken;
import org.starcoin.bean.Tvl;
import org.starcoin.jsonrpc.client.JSONRPC2SessionException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TvlTest {

    @Test
    public void testTvl() throws MalformedURLException, JSONRPC2SessionException {
        //StateRPCClient stateRPCClient = new StateRPCClient(new URL("http://barnard1.seed.starcoin.org:9850"));
        StateRPCClient stateRPCClient = new StateRPCClient(new URL("https://barnard-seed.starcoin.org"));

        String address = "0xbd7e8be8fae9f60f2f5136433e36a091";
        ListResource resources = stateRPCClient.getState(address);

        for (String key : resources.getResources().keySet()) {

            if (key.contains("Balance")) {
                long value = resources.getResources().get(key).getJson().get("token").get("value").longValue();
                String token = key.replaceFirst("0x00000000000000000000000000000001::Account::Balance<", "");
                token = token.substring(0, token.length() - 1);

                Map<String, Tvl> tokenMap = new HashMap<>();
                if (token.contains("LiquidityToken")) {
                    String tokenPair = token.substring(token.indexOf("<") + 1, token.length() - 1);
                    String[] tokens = tokenPair.split(",");
                    if (tokens.length != 2) {
                        continue;
                    }
                    TokenSwapLiquidityToken tokenSwapLiquidityToken = new TokenSwapLiquidityToken(tokens[0], tokens[1]);
                    tokenMap.put(token, new Tvl(tokenSwapLiquidityToken, value));
                } else {
                    tokenMap.put(token, new Tvl(token, value));
                }
                System.out.println(tokenMap);
            }
        }
    }

}
