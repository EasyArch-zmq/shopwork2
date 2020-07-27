package com.easyArch.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ChannelHandler.Sharable
public class SocketHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger logger= LoggerFactory.getLogger(SocketHandler.class);
    //@Autowired
    //这里就是连数据要的dao要注入

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String o) {
        //这里就是要接的字符串o 你要自己解析split()一下
        logger.info("收的数据"+o);
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info("客户端加入连接:"+ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channel.closeFuture();
        logger.info("客户端断开连接："+ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            channel.closeFuture();
        }
    }
}
