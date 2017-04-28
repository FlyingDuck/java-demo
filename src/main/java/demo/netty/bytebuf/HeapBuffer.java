package demo.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Created by dongsj on 2017/4/27.
 */
public class HeapBuffer {

    public static void main(String[] args) {
//        ByteBuf directBuf = ByteBufAllocator.DEFAULT.directBuffer();
//        directBuf.writeBytes("Netty IO".getBytes());
        ByteBuf heapBuf = Unpooled.copiedBuffer("Netty IO", Charset.forName("UTF-8"));
        if (heapBuf.hasArray()) {
            System.out.println("Has Array");
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset();
            int length = heapBuf.readableBytes();
            System.out.println("capacity=" + heapBuf.capacity());
            System.out.println("isDirect=" + heapBuf.isDirect());
            System.out.println("isReadable=" + heapBuf.isReadable());
            System.out.println("isReadOnly=" + heapBuf.isReadOnly());
            System.out.println("offset="+ offset +" length="+length);
        } else {
            System.out.println("Hasn't Array");

        }
    }

}
