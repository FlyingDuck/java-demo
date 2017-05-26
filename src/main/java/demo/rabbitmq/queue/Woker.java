package demo.rabbitmq.queue;

import com.rabbitmq.client.*;
import demo.rabbitmq.SimpleChannelFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/5/26 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Woker {

    static final String TASK_QUEUE_NAME = "w-queue";


    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = SimpleChannelFactory.getChannel(TASK_QUEUE_NAME);

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } finally {
                    System.out.println(" [x] Done");
                }
            }
        };
        boolean autoAck = true; // acknowledgment is covered below
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);

    }

    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.')
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }


}
