package me.lv;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Slf4j
public class MsgConsumer {
    public static void consumerMsg(String exchange, String queue, String routingKey) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = RabbitUtil.getConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queue, true, false, false, null);
        channel.queueBind(queue, exchange, routingKey);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                log.info("receive {}", message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 取消自动ack
        channel.basicConsume(queue, false, consumer);
    }
}
