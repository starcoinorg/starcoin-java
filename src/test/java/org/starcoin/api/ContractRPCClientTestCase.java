package org.starcoin.api;


import junit.framework.TestCase;
import org.starcoin.bean.ContractCall;
import org.starcoin.jsonrpc.client.JSONRPC2SessionException;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ContractRPCClientTestCase extends TestCase {

    private ContractRPCClient client;

    @Override
    protected void setUp() throws Exception {
        //client = new ContractRPCClient(new URL("http://localhost:9850"));
        client = new ContractRPCClient(new URL("https://barnard-seed.starcoin.org"));
    }

    public void testCall() {
        try {
            ContractCall call = new ContractCall();

            call.setFunctionId("0x00000000000000000000000000000001::Token::market_cap");

            List<java.lang.String> typeTags = new ArrayList<>();
            typeTags.add("0x00000000000000000000000000000001::STC::STC");

            call.setTypeArgs(typeTags);
            call.setArgs(new ArrayList<>());

            List result = client.call(call);
            System.out.println(result);
            if (result.size() > 0) {
                long cap = (Long) result.get(0);
                System.out.println(cap);
            }
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }

    public void testSTCMarketCap() throws MalformedURLException, JSONRPC2SessionException {
        TokenContractRPCClient tokenContractRPCClient = new TokenContractRPCClient(new URL("https://barnard-seed.starcoin.org"));
        BigInteger cap = tokenContractRPCClient.getSTCMarketCap();
        System.out.println(cap);

        BigInteger balance = tokenContractRPCClient.getSTCTreasurBalance();
        System.out.println(balance);

        System.out.println(tokenContractRPCClient.getSTCCurrentSupply());
    }
}