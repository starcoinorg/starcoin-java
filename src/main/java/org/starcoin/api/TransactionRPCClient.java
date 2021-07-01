package org.starcoin.api;

import com.alibaba.fastjson.JSON;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starcoin.bean.PendingTransaction;

import java.net.URL;
import java.util.Arrays;

public class TransactionRPCClient {

    private static Logger log = LoggerFactory.getLogger(TransactionRPCClient.class);
    JSONRPC2Session session;

    public TransactionRPCClient(URL baseUrl) {
        session = new JSONRPC2Session(baseUrl);
    }

    public PendingTransaction getTransaction(String hash) throws JSONRPC2SessionException {
        int requestID = 0;
        JSONRPC2Request request = new JSONRPC2Request("txpool.pending_txn", Arrays.asList(hash), requestID);
        JSONRPC2Response response = null;
        PendingTransaction transaction = null;

        response = session.send(request);

        if (response.indicatesSuccess()) {
            if (response != null && response.getResult() != null) {
                String result = response.getResult().toString();
                //log.info("pending txn result is "+result);
                transaction = JSON.parseObject(result, PendingTransaction.class);
            }
        } else
            log.info(response.getError().getMessage());

        return transaction;
    }

}
