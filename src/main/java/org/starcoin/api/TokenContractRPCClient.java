package org.starcoin.api;

import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import org.starcoin.bean.ContractCall;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TokenContractRPCClient extends ContractRPCClient{

    public TokenContractRPCClient(URL baseUrl){
        super(baseUrl);
    }

    public BigInteger getTokenMarketCap(String tokenTypeTag) throws JSONRPC2SessionException {
        ContractCall call = new ContractCall();

        call.setFunctionId("0x00000000000000000000000000000001::Token::market_cap");

        List<String> typeTags = new ArrayList<>();
        typeTags.add(tokenTypeTag);

        call.setTypeArgs(typeTags);
        call.setArgs(new ArrayList<>());

        List result = this.call(call);
        if(result!=null && result.size()>0) {
            String capInString = String.valueOf(result.get(0));
            return new BigInteger(capInString);
        }else{
            throw new JSONRPC2SessionException("fetch token"+ tokenTypeTag+" market cap failed");
        }
    }

    public BigInteger getTreasurByalance(String tokenTypeTag) throws JSONRPC2SessionException {
        ContractCall call = new ContractCall();

        call.setFunctionId("0x00000000000000000000000000000001::Treasury::balance");

        List<String> typeTags = new ArrayList<>();
        typeTags.add(tokenTypeTag);

        call.setTypeArgs(typeTags);
        call.setArgs(new ArrayList<>());

        List result = this.call(call);
        if(result!=null && result.size()>0) {
            String capInString = String.valueOf(result.get(0));
            return new BigInteger(capInString);
        }else{
            throw new JSONRPC2SessionException("fetch token"+ tokenTypeTag+" market cap failed");
        }
    }

    public BigInteger getSTCMarketCap() throws JSONRPC2SessionException {
        return this.getTokenMarketCap("0x00000000000000000000000000000001::STC::STC");
    }

    public BigInteger getSTCTreasurByalance() throws JSONRPC2SessionException {
        return this.getTreasurByalance("0x00000000000000000000000000000001::STC::STC");
    }

    public BigInteger getSTCCurrentSupply() throws JSONRPC2SessionException {
        return this.getSTCMarketCap().subtract(this.getSTCTreasurByalance());
    }
}
