package demo.rabbitmq.hello;

import com.rabbitmq.client.*;
import demo.rabbitmq.SimpleChannelFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by dongsj on 2017/4/11.
 */
public class Receiver {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = SimpleChannelFactory.getChannel("hello");

        System.out.println(" [C] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [C] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
