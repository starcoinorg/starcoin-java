package org.starcoin.api;

import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import junit.framework.TestCase;
import org.starcoin.bean.Transaction;

import java.net.URL;

public class TransactionRPCClientTest extends TestCase {

    private TransactionRPCClient client;

    @Override
    protected void setUp() throws Exception {
        client = new TransactionRPCClient(new URL("http://localhost:9850"));
    }

    public void testGetTransactionByHash() {
        try {
            Transaction transaction = client.getTransactionByHash("0x9497fc455c962ee27a2321e88af6c8eeae9842f3d3ea70dc349cdbe004250897");
//            TransactionPayload payload = transaction.getUserTransaction().getRawTransaction();
//            System.out.println("txn: " + payload.getClass());
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }
}