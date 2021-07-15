package org.starcoin.api;

import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starcoin.bean.Block;
import org.starcoin.bean.BlockHeader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockRPCClient {
    private static Logger log = LoggerFactory.getLogger(BlockRPCClient.class);
    JSONRPC2Session session;

    public BlockRPCClient(URL baseUrl) {
        session = new JSONRPC2Session(baseUrl);
    }

    @SuppressWarnings("unchecked")
    public BlockHeader getChainHeader() throws JSONRPC2SessionException {
        JsonRPCClient<BlockHeader> client = new JsonRPCClient<>();
        return client.getSubObject(session, "chain.info", Collections.EMPTY_LIST, 0, "head", BlockHeader.class);
    }

    public Block getBlockByHash(String hash) throws JSONRPC2SessionException {
        JsonRPCClient<Block> client = new JsonRPCClient<>();
        return client.getObject(session, "chain.get_block_by_hash", Collections.singletonList(hash), 0, Block.class);
    }

    public Block getBlockByHeight(long height) throws JSONRPC2SessionException {
        JsonRPCClient<Block> client = new JsonRPCClient<>();
        return client.getObject(session, "chain.get_block_by_number", Collections.singletonList(height), 0, Block.class);
    }

    public List<Block> getBlockListFromHeight(long height, int count) throws JSONRPC2SessionException {
        JsonRPCClient<Block> client = new JsonRPCClient<>();
        List<Object> params = new ArrayList<>();
        params.add(height);
        params.add(count);
        return client.getObjectArray(session, "chain.get_blocks_by_number", params, 0, Block.class);
    }

}
