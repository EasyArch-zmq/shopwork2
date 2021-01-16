package com.easyArch.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import org.apache.tomcat.util.buf.Ascii;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    private SocketHandler socketHandler;
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //防止tcp拆、粘包使用换行符来防止
        // server端是接收  ，所以这里是httprequest解码
//        channel.pipeline().addLast(new HttpRequestDecoder());
//        // 消息合并+最大2M，防止半包问题
//        channel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(2 * 1024 * 1024));
//        //server端会返回respose 所以这里是进行编码
//        channel.pipeline().addLast(new HttpResponseEncoder());
//        //处理器
//        channel.pipeline().addLast("http-fileServerHandler", socketHandler);
        //channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//        channel.pipeline().addLast(new HttpServerCodec());
//        channel.pipeline().addLast(new HttpObjectAggregator(65535));
//        channel.pipeline().addLast(new ChunkedWriteHandler());
////        channel.pipeline().addLast(new HexDecode());
//        channel.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//        channel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4));
//        channel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//        channel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast(socketHandler);

    }
}
