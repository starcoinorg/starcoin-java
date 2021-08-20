package org.starcoin.api;

import io.reactivex.Flowable;
import org.starcoin.bean.EventFilter;
import org.starcoin.bean.EventNotification;
import org.web3j.protocol.websocket.WebSocketService;

import java.net.ConnectException;

public class SubscribleSample {

    public static void main(String... args) throws ConnectException {
        WebSocketService service = new WebSocketService("ws://localhost:9870", true);
        service.connect();
        StarcoinSubscriber subscriber = new StarcoinSubscriber(service);
        EventFilter eventFilter = new EventFilter(0, "b75994d55eae88219dc57e7e62a11bc0");
        Flowable<EventNotification> flowableTxns = subscriber.newTxnSendRecvEventNotifications(eventFilter);

        for (EventNotification notification : flowableTxns.blockingIterable()) {
            System.out.println(notification.getParams().getResult().toString());
        }

    }
}
