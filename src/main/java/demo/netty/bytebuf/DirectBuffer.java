package demo.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/5/15 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class DirectBuffer {


    public static void main(String[] args) {

        ByteBuf directBuf = ByteBufAllocator.DEFAULT.directBuffer();
        directBuf.writeBytes("Netty IO".getBytes());
        if (!directBuf.hasArray()) {
            System.out.println("No Array");
            int length = directBuf.readableBytes();
            byte[] copyArray = new byte[length];
            directBuf.getBytes(0,copyArray);

            System.out.println((char)copyArray[0]);
        } else {
            System.out.println("Has Array");
        }


    }
}
