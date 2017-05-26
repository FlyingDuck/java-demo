package demo.rabbitmq.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import demo.rabbitmq.SimpleChannelFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by dongsj on 2017/4/11.
 */
public class Sender {

    private static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = SimpleChannelFactory.getChannel("hello");
        String message = "Hello Rabbit MQ";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [P] Sent '"+message+"'");

        channel.close();
        SimpleChannelFactory.closeConnection();
    }

}
