package org.starcoin.api;

import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import org.junit.Test;
import org.starcoin.bean.ListResource;

import java.net.MalformedURLException;
import java.net.URL;

public class TvlTest {

    @Test
    public void testTvl() throws MalformedURLException, JSONRPC2SessionException {
        StateRPCClient stateRPCClient = new StateRPCClient(new URL("http://barnard1.seed.starcoin.org:9850"));

        ListResource resources = stateRPCClient.getState("0xbd7e8be8fae9f60f2f5136433e36a091");

        for (String key :resources.getResources().keySet()){
            System.out.println("key is "+key+" value is "+resources.getResources().get(key));
        }
    }
}
