package org.starcoin.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starcoin.bean.ListResource;
import org.starcoin.bean.ListResourceOption;
import org.starcoin.bean.Resource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StateRPCClient {
    private static Logger logger = LoggerFactory.getLogger(StateRPCClient.class);
    JSONRPC2Session session;

    public StateRPCClient(URL baseUrl) {
        session = new JSONRPC2Session(baseUrl);
    }

    public ListResource getState(String address) throws JSONRPC2SessionException {
        JsonRPCClient<ListResource> client = new JsonRPCClient<>();
        List<Object> param = new ArrayList<>();
        param.add(address);
        ListResourceOption option = new ListResourceOption();
        option.setDecode(true);
        param.add(option);
        try {
            return client.getObjectParseJackson(session, "state.list_resource", param, 0, ListResource.class);
        } catch (IOException e) {
            logger.error("get state error:", e);
        }
        return null;
    }

    public long getAddressAmount(String address) {
        try {
            ListResource listResource = getState(address);
            Map<String, Resource> resourceMap = listResource.getResources();
        for(String key: resourceMap.keySet()) {
            JsonNode node = resourceMap.get(key).getJson().get("token");
            System.out.println(node);
            if (node != null) {
                return node.get("value").asLong();
            }
        }
        } catch (JSONRPC2SessionException e) {
           logger.error("get amount error:", e);
        }
        return 0;
    }
}
