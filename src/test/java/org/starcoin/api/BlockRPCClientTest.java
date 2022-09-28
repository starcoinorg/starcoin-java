package org.starcoin.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import junit.framework.TestCase;
import org.starcoin.bean.Block;
import org.starcoin.bean.BlockHeader;
import org.starcoin.bean.BlockInfo;
import org.starcoin.jsonrpc.client.JSONRPC2SessionException;

import java.net.URL;

public class BlockRPCClientTest extends TestCase {
    private BlockRPCClient client;

    @Override
    protected void setUp() throws Exception {
        //client = new ContractRPCClient(new URL("http://localhost:9850"));
        client = new BlockRPCClient(new URL("https://barnard-seed.starcoin.org"));
    }

    public void testGetChainHeader() throws JsonProcessingException {
        try {
            BlockHeader chainHeader = client.getChainHeader();
            System.out.println(chainHeader);
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }

    public void testGetBlockByHeight() {
        try {
            Block block = client.getBlockByHeight(5495169L);
            System.out.println(block);
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }

    public void testGetBlockByHash() {
        try {
            Block block = client.getBlockByHash("0xda40a21da882d2c7c0e011d20ff375f31914fd40fad4dd70b12df0ca3733c897");
            System.out.println(block);
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }

    public void testGetBlockInfoByHeight() {
        try {
            BlockInfo blockInfo = client.getBlockInfoByHeight(8402745);
            System.out.println(blockInfo);
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }

}
