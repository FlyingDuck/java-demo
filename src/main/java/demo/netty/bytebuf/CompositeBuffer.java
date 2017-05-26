package demo.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/5/15 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class CompositeBuffer {

    public static void main(String[] args) {
        ByteBuf heapBuf = Unpooled.copiedBuffer("Heap", Charset.forName("UTF-8"));
        ByteBuf directBuf = ByteBufAllocator.DEFAULT.directBuffer().writeBytes("Direct".getBytes());

        CompositeByteBuf compositeBuf = ByteBufAllocator.DEFAULT.compositeBuffer();//new CompositeByteBuf(ByteBufAllocator.DEFAULT, true, 2);
        compositeBuf.addComponent(heapBuf);
        compositeBuf.addComponent(directBuf);

    }
}
