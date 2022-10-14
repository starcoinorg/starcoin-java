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

import com.alibaba.fastjson.JSON;
import io.reactivex.Flowable;
import org.starcoin.bean.EventFilter;
import org.starcoin.bean.EventNotification;
import org.starcoin.bean.Transaction;
import org.web3j.protocol.websocket.WebSocketService;

import java.io.IOException;

public class SubscribeSample {

    public static void main(String[] args) throws IOException {
        //WebSocketService service = new WebSocketService("ws://localhost:9870", true);
        WebSocketService service = new WebSocketService("ws://barnard4.seed.starcoin.org", true);
        service.connect();
        StarcoinSubscriber subscriber = new StarcoinSubscriber(service);
        EventFilter eventFilter = new EventFilter(0, "b75994d55eae88219dc57e7e62a11bc0");
        Flowable<EventNotification> flowableTxns = subscriber.newTxnSendRecvEventNotifications(eventFilter);

        for (EventNotification notification : flowableTxns.blockingIterable()) {
            System.out.println(notification.getParams().getResult().toString());
        }
    }


//    public static class C {
//        @JsonProperty("foo")
//        Object foo;
//    }

}
