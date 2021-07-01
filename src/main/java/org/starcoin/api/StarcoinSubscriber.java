package org.starcoin.api;

import io.reactivex.Flowable;
import org.starcoin.bean.Kind;
import org.starcoin.bean.PendingTransactionNotification;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthSubscribe;

import java.util.Arrays;

public class StarcoinSubscriber {

    private final Web3jService web3jService;

    public StarcoinSubscriber(Web3jService web3jService) {
        this.web3jService = web3jService;
    }

    public Flowable<PendingTransactionNotification> newPendingTransactionsNotifications() {
        return web3jService.subscribe(
                new Request<>(
                        "starcoin_subscribe",
                        Arrays.asList(Kind.PendingTxn),
                        web3jService,
                        EthSubscribe.class),
                "starcoin_unsubscribe",
                PendingTransactionNotification.class);
    }

}
