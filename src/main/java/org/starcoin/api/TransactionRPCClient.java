package org.starcoin.api;

import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starcoin.bean.Event;
import org.starcoin.bean.GetTransactionOption;
import org.starcoin.bean.PendingTransaction;
import org.starcoin.bean.Transaction;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRPCClient {

    private static Logger log = LoggerFactory.getLogger(TransactionRPCClient.class);
    JSONRPC2Session session;

    public TransactionRPCClient(URL baseUrl) {
        session = new JSONRPC2Session(baseUrl);
    }

    public PendingTransaction getPendingTransaction(String hash) throws JSONRPC2SessionException {
        JsonRPCClient<PendingTransaction> client = new JsonRPCClient<>();
        return client.getObject(session, "txpool.pending_txn", Collections.singletonList(hash), 0, PendingTransaction.class);
    }

    public Transaction getTransactionByHash(String hash) throws JSONRPC2SessionException {
        JsonRPCClient<Transaction> client = new JsonRPCClient<>();
        List<Object> parameter = new ArrayList<>();
        parameter.add(hash);
        GetTransactionOption option = new GetTransactionOption();
        option.setDecode(true);
        parameter.add(option);
        return client.getObject(session, "chain.get_transaction", parameter, 0, Transaction.class);
    }

    public Transaction getTransactionInfoByHash(String hash) throws JSONRPC2SessionException {
        JsonRPCClient<Transaction> client = new JsonRPCClient<>();
        return client.getObject(session, "chain.get_transaction_info", Collections.singletonList(hash), 0, Transaction.class);
    }


    public List<Transaction> getBlockTransactions(String blockHash) throws JSONRPC2SessionException {
        JsonRPCClient<Transaction> client = new JsonRPCClient<>();
        return client.getObjectArray(session, "chain.get_block_txn_infos", Collections.singletonList(blockHash), 0, Transaction.class);
    }

    public List<Event> getTransactionEvents(String transactionHash) throws JSONRPC2SessionException {
        JsonRPCClient<Event> client = new JsonRPCClient<>();
        return client.getObjectArray(session, "chain.get_events_by_txn_hash", Collections.singletonList(transactionHash), 0, Event.class);
    }

}
