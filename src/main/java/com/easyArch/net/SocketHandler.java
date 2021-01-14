package com.easyArch.net;

import com.easyArch.mapper.Time_InfoDao;
import com.easyArch.util.ControllerUtil;
import com.easyArch.util.HexUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import org.apache.tomcat.util.buf.HexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Configuration
@ChannelHandler.Sharable
public class SocketHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger LOGGER= LoggerFactory.getLogger(SocketHandler.class);
    @Autowired
    Time_InfoDao timeInfoDao;
    //这里就是连数据要的dao要注入

        @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest msg) throws IOException {
            //在这里进行读取内容
            //这里就是要接的字符串o 你要自己解析split()一下
            // String substring = o.substring(0, 12);
            //在这里进行读取内容
            byte[]bytes= ByteBufUtil.getBytes(msg.content());
            System.out.println(new String(bytes,StandardCharsets.UTF_8));

            // String str = o.substring(12);
            // byte[] bytes = HexUtil.hexStringToBytes(str);
            //  String s = new String(bytes, StandardCharsets.UTF_8);
            //LOGGER.info("收的数据"+substring+s);
//            LOGGER.info("收的数据电话号完整:"+o);
//            //电话号协议
            String []strings=new String(bytes,StandardCharsets.UTF_8).split("#");
            String s_value="";
            System.out.println(strings.length);
            for (int i = 0; i < strings.length; i++) {
                s_value+=strings[i];
            }
            LOGGER.info("收的数据电话号:"+s_value);
////        LOGGER.info("收的数据:"+o);
//            //    String []strings=ControllerUtil.getInfo(s);
////        String[] split = o.split("\\(");
////        byte[] bytess = HexUtils.fromHexString(split[0]);
////        LOGGER.info("解析的数据："+ Arrays.toString(bytess) +"\t"+split[1]);
//
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            //获取日期

            String[] time_split=ControllerUtil.slipDate4(df.format(new Date()));
            String mytime=time_split[0];
            String hao_miao=time_split[1];
//        String boxid=strings[0];
            String mac_address=s_value;
            Integer yangan=new Integer(strings[1]);
            LOGGER.info("mac: "+mac_address+"烟感值："+0);
            timeInfoDao.insertInfo(mac_address,mytime,hao_miao,0);
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        LOGGER.info("客户端加入连接:"+ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channel.closeFuture();
        LOGGER.info("客户端断开连接："+ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            channel.closeFuture();
        }
    }
}
