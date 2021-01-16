package com.easyArch.net;

import com.easyArch.mapper.Time_InfoDao;

import com.easyArch.util.ControllerUtil;
import io.netty.buffer.ByteBuf;

import io.netty.channel.*;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@Configuration
@ChannelHandler.Sharable
public class SocketHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER= LoggerFactory.getLogger(SocketHandler.class);
    @Autowired
    Time_InfoDao timeInfoDao;
    //这里就是连数据要的dao要注入

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
        result.readBytes(result1);
        String resultStr = new String(result1, StandardCharsets.UTF_8);
        System.out.println("Client said:" + resultStr);
        // 释放资源，这行很关键
        result.release();
        String[] strings = resultStr.split("#");
        String s_value = "";
        System.out.println(strings.length);
        for (int i = 0; i < strings.length; i++) {
            s_value += strings[i];
        }
        System.out.println("数据是：  "+s_value);
        DateTime now=DateTime.now();
        String date=now.toString("yyyy-MM-dd HH:mm:ss");

        System.out.println("时间是："+now.toString("yyyy-MM-dd HH:mm:ss :SSS"));
        //设置日期格式
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        //获取日期
//        String[] time_split=date.split(" :");
//        for (int t = 0; t < time_split.length; t++) {
//            System.out.println("::::::::   "+time_split[t]);
//        }
//        String mytime=time_split[0];
//        String hao_miao=time_split[1];
//        String boxid=strings[0];
        String mac_address=s_value;
//        Integer yangan=new Integer(strings[1]);
        LOGGER.info("mac: "+mac_address+"烟感值："+0);
//        System.out.println("mac: "+mac_address+"烟感值："+0);
//        System.out.println("时间是:"+mytime+"毫秒："+hao_miao);
        timeInfoDao.insertInfo(mac_address,date,0);
        System.out.println("存入成功");

    }


    //        @Override
//    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest msg) throws IOException {
//            //在这里进行读取内容
//            //这里就是要接的字符串o 你要自己解析split()一下
//            // String substring = o.substring(0, 12);
//            //在这里进行读取内容
//            byte[]bytes= ByteBufUtil.getBytes(msg.content());
//            System.out.println(new String(bytes,StandardCharsets.UTF_8));
//
//            // String str = o.substring(12);
//            // byte[] bytes = HexUtil.hexStringToBytes(str);
//            //  String s = new String(bytes, StandardCharsets.UTF_8);
//            //LOGGER.info("收的数据"+substring+s);
////            LOGGER.info("收的数据电话号完整:"+o);
////            //电话号协议

//            LOGGER.info("收的数据电话号:"+s_value);
//////        LOGGER.info("收的数据:"+o);
////            //    String []strings=ControllerUtil.getInfo(s);
//////        String[] split = o.split("\\(");
//////        byte[] bytess = HexUtils.fromHexString(split[0]);
//////        LOGGER.info("解析的数据："+ Arrays.toString(bytess) +"\t"+split[1]);
////

//    }
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
