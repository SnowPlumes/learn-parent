package me.lv;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
public class MsgProducer {
    public static void publishMsg(String exchange, BuiltinExchangeType exchangeType, String toutingKey, String message) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = RabbitUtil.getConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        // 说明exchange 中的消息可持久化，不自动删除
        channel.exchangeDeclare(exchange, exchangeType, true, false, null);

        channel.basicPublish(exchange, toutingKey, null, message.getBytes());

        log.info("send {}", message);
        channel.close();
        connection.close();
    }
}
