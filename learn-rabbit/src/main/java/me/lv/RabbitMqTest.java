package me.lv;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

@Slf4j
public class RabbitMqTest {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {

    }

    public static void send() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        // 连接
        Connection connection = factory.newConnection();
        // 消息通道
        Channel channel = connection.createChannel();
        // 消息队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        for (int i = 0; i < 10; i++) {
            String message = "rabbit count = " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            log.info("send = {}", message);
        }

        channel.close();
        connection.close();
    }

    public static void consumer() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        // 连接
        Connection connection = factory.newConnection();
        // 消息通道
        Channel channel = connection.createChannel();
        // 消息队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        DeliverCallback deliverCallback = ((consumerTag, message) -> {
            String content = new String(message.getBody(), StandardCharsets.UTF_8);
            log.info("received = {}", content);
        });
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });

        channel.close();
        connection.close();
    }

}
