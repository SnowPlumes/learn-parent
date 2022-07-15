package me.lv;

import com.rabbitmq.client.BuiltinExchangeType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
public class DirectProducer {

    private static final String EXCHANGE_NAME = "direct.exchange";

    public void publishMsg(String routingKey, String message) throws IOException, TimeoutException {
        MsgProducer.publishMsg(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, routingKey, message);
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        DirectProducer directProducer = new DirectProducer();
        String[] routingKey = new String[]{"aaa", "bbb", "ccc"};
        String msg = "hello >>> ";
        for (int i = 0; i < 10; i++) {
            directProducer.publishMsg(routingKey[i % 3], msg + i);
        }
    }
}
