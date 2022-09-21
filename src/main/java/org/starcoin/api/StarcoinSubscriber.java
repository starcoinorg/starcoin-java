/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.starcoin.api;

import io.reactivex.Flowable;
import org.starcoin.bean.EventFilter;
import org.starcoin.bean.EventNotification;
import org.starcoin.bean.Kind;
import org.starcoin.bean.PendingTransactionNotification;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthSubscribe;

import java.util.Arrays;

/**
 * 用于通过 websocket 订阅 Starcoin 的事件，暂时只实现了 PendingTransaction 相关的事件。
 * example 参考 SubscribeSample
 *
 * @author fanngyuan
 * @since 1.1.6
 */
public class StarcoinSubscriber {

    private final Web3jService web3jService;

    public StarcoinSubscriber(Web3jService web3jService) {
        this.web3jService = web3jService;
    }

    /**
     * 用于获取 PendingTransaction 相关的通知
     */
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

    /**
     * 用于获取 Event 相关的通知
     */
    public Flowable<EventNotification> newTxnSendRecvEventNotifications(EventFilter eventFilter) {
        return web3jService.subscribe(
                new Request<>(
                        "starcoin_subscribe",
                        //Arrays.asList(Kind.NewEvent, new EventParams(eventFilter,true)),
                        Arrays.asList(Kind.NewEvent, eventFilter),
                        web3jService,
                        EthSubscribe.class),
                "starcoin_unsubscribe",
                EventNotification.class);
    }

}
