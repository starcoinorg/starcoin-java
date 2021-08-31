package org.starcoin.api;

import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import junit.framework.TestCase;
import org.starcoin.bean.Transaction;

import java.net.MalformedURLException;
import java.net.URL;

public class TransactionRPCClientTest extends TestCase {

    private TransactionRPCClient client;

    @Override
    protected void setUp() throws Exception {
        client = new TransactionRPCClient(new URL("http://localhost:9850"));
    }

    public void testGetTransactionByHash() {

        try {
            StateRPCClient stateRPCClient = new StateRPCClient(new URL("http://localhost:9850"));
            stateRPCClient.getState("0x5c04bb88984f933151bf69504692074d");
            long amount = stateRPCClient.getAddressAmount("0x5c04bb88984f933151bf69504692074d", "0x9350502a3af6c617e9a42fa9e306a385::BX_USDT::BX_USDT");
            System.out.println("amount: " + amount);
            amount = stateRPCClient.getAddressAmount("0x5c04bb88984f933151bf69504692074d", "0x00000000000000000000000000000001::STC::STC");
            System.out.println("amount: " + amount);
            Transaction transaction = client.getTransactionByHash("0x9497fc455c962ee27a2321e88af6c8eeae9842f3d3ea70dc349cdbe004250897");
        } catch (JSONRPC2SessionException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}