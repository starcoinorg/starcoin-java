package org.starcoin.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import junit.framework.TestCase;
import org.starcoin.bean.Block;
import org.starcoin.bean.BlockGhostdagData;
import org.starcoin.bean.BlockHeader;
import org.starcoin.bean.BlockInfo;

import java.net.URL;
import java.util.List;

public class BlockRPCClientTest extends TestCase {
    private BlockRPCClient client;

    @Override
    protected void setUp() throws Exception {
        //client = new ContractRPCClient(new URL("http://localhost:9850"));
        client = new BlockRPCClient(new URL("https://halley-seed.starcoin.org"));
        // client = new BlockRPCClient(new URL("http://127.0.0.1:9850"));
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

    public void testGetBlockListFromHeight() {
        try {
            List<Block> blockList = client.getBlockListFromHeight(/*34401*/ 100, 10);
            System.out.println(blockList.size());
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }

    public void testGetBlockGhostdagData() {
        try {
            BlockGhostdagData ghostdagData = client.getBlockGhostdagData("0x5b63caad86f3240e73e4d6f5b2cb58025ff2e86eecc96da10ad551fd1a6f70e6");
            // assertNotNull(ghostdagData);
            if (ghostdagData != null) {
                System.out.println(ghostdagData);
            }
        } catch (JSONRPC2SessionException e) {
            e.printStackTrace();
        }
    }
}
