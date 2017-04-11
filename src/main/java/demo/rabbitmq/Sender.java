package demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by dongsj on 2017/4/11.
 */
public class Sender {

    private static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.236.141.14");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello Rabbit MQ";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [P] Sent '"+message+"'");

        channel.close();
        connection.close();
    }

}
