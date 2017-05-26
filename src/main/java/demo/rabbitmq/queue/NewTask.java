package demo.rabbitmq.queue;

import com.rabbitmq.client.Channel;
import demo.rabbitmq.SimpleChannelFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/5/26 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class NewTask {

    private static String getMessage(String[] strings) {
        if (strings.length < 1) {
            return "Hello Rabbit";
        }
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }


    public static void main(String[] args) throws IOException, TimeoutException {
        String message = getMessage(new String[]{"hello", ".", ".", "."});

        Channel channel = SimpleChannelFactory.getChannel(Woker.TASK_QUEUE_NAME);
        channel.basicPublish("", Woker.TASK_QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        SimpleChannelFactory.closeConnection();
    }


}
