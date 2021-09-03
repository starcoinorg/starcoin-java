package org.starcoin.api;

import org.junit.Test;
import org.starcoin.bean.OracleTokenPair;

import java.io.IOException;
import java.util.List;

public class OracleRPCClientTest {

    @Test
    public void testGetOracleTokenPair() throws IOException {
        OracleRPCClient client = new OracleRPCClient("http://ac5e9dd967389445e809defadcba5242-1908387560.ap-northeast-1.elb.amazonaws.com");
        List<OracleTokenPair> result = client.getOracleTokenPair();
        System.out.println(result);
    }
}
