package demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/5/26 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class SimpleChannelFactory {

    public static final String QUEUE_NAME = "hello";

    private static Connection connection;

    public static void closeConnection() throws IOException {
        if (null != connection)
            connection.close();
    }

    public static Channel getChannel(String queue) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();


        channel.queueDeclare(queue, false, false, false, null);

        return channel;
    }


}
