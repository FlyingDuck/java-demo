package demo.netty.websocket;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/6/24 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private final String wsUri;
    private final static File INDEX;

    static {
        URL location = HttpRequestHandler.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation();

        try {
            String path = location.toURI() + "index.html";
            path = !path.contains("file") ? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("无法定位 index.html", e);
        }
    }

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if (wsUri.equalsIgnoreCase(request.uri())) {
            // retain() 在 SimpleChannelInboundHandler 中，在调用 channelRead() 方法完成后，
            // 将调用 FullHttpRequest 对象上的 release() 方法来释放资源。

            // A reference-counted object that requires explicit deallocation.
            // When a new ReferenceCounted is instantiated, it starts with the reference count of 1.
            // retain() increases the reference count, and release() decreases the reference count.
            // If the reference count is decreased to 0, the object will be deallocated explicitly,
            // and accessing the deallocated object will usually result in an access violation.
            ctx.fireChannelRead(request.retain()); // 传递给下一个 Handler
        } else {
            if (HttpUtil.is100ContinueExpected(request)) {
                send100Continue(ctx);
            }
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if (keepAlive) {
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(response); // 将 HttpResponse 写回给客户端，这里并不是一个 FullHttpResponse，它只是响应的一部分

            if (ctx.pipeline().get(SslHandler.class) == null) {
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT); // LastHttpContent 标记响应结束
            if (!keepAlive) {
                future.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private static void send100Continue(ChannelHandlerContext context) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.CONTINUE);
        context.writeAndFlush(response);
    }
}
