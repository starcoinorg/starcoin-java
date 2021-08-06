package org.starcoin.api;

import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starcoin.bean.ContractCall;
import org.starcoin.bean.GetTransactionOption;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ContractRPCClient {
    private static Logger log = LoggerFactory.getLogger(ContractRPCClient.class);
    JSONRPC2Session session;

    public ContractRPCClient(URL baseUrl) {
        session = new JSONRPC2Session(baseUrl);
    }

    public List<Object> call(ContractCall call) throws JSONRPC2SessionException {
        JsonRPCClient<Object> client = new JsonRPCClient<>();
        List<Object> parameter = new ArrayList<>();
        parameter.add(call.toMap());
        return client.getObjectArray(session, "contract.call_v2", parameter, 0,Object.class);
    }

}
