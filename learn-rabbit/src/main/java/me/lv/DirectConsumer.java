package me.lv;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectConsumer {
    private static final String exchangeName = "direct.exchange";

    public void msgConsumer(String queueName, String routingKey) throws IOException, TimeoutException {
        MsgConsumer.consumerMsg(exchangeName, queueName, routingKey);
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        DirectConsumer consumer = new DirectConsumer();
        String[] routingKey = new String[]{"aaa", "bbb", "ccc"};
        String[] queueNames = new String[]{"qa", "qb", "qc"};

        for (int i = 0; i < 3; i++) {
            consumer.msgConsumer(queueNames[i], routingKey[i]);
        }
    }
}
