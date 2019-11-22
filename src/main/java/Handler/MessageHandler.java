package Handler;

import Entity.ReadData1997;
import Utils.TimeUtil;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:28
 * 消息实体handler
 */
public class MessageHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ReadData1997 readData = (ReadData1997) msg;
        switch (readData.getControlCode()){
            case 0x81: //主站请求读数据应答后 -- 无后续数据帧情况
                double dataresult = getDataresult(readData);
                System.out.println(TimeUtil.getCurrentTime() + " 主站请求读数据后" + "收到无后续数据帧情况 from "
                        + readData.getDeviceAddress()
                        + " " + readData.getDataType()
                        + " = " + dataresult);
                break;
            case 0xA1: //主站请求读数据应答后 --> 有后续数据帧情况，需要再次请求后续数据
                System.out.println(TimeUtil.getCurrentTime() + " 主站请求读数据后" + "收到有后续数据帧情况 from "
                        + readData.getDeviceAddress()
                        + " " + readData.getDataType()
                        + " = ");
                break;
            case 0x82: //主站读后续数据请求应答后 --> 无后续数据帧情况
                System.out.println(TimeUtil.getCurrentTime() + " 主站请求读后续数据后" + "收到无后续数据帧情况 from "
                        + readData.getDeviceAddress()
                        + " " + readData.getDataType()
                        + " = ");
                break;
            case 0xA2: //主站读后续数据请求应答后 --> 有后续数据帧情况
                System.out.println(TimeUtil.getCurrentTime() + " 主站请求读后续数据后" + "收到有后续数据帧情况 from "
                        + readData.getDeviceAddress()
                        + " " + readData.getDataType()
                        + " = ");
                break;
            case 0x83: //主站重读数据请求应答后 --> 无后续数据帧情况
                System.out.println(TimeUtil.getCurrentTime() + " 主站请求重读数据后" + "收到无后续数据帧情况 from "
                        + readData.getDeviceAddress()
                        + " " + readData.getDataType()
                        + " = ");
                break;
            case 0xA3: //主站重读数据请求应答后 --> 有后续数据帧情况
                System.out.println(TimeUtil.getCurrentTime() + " 主站请求重读数据后" + "收到有后续数据帧情况 from "
                        + readData.getDeviceAddress()
                        + " " + readData.getDataType()
                        + " = ");
                break;
                //写数据、广播校时、写设备地址、更改通信速率、修改密码、最大需量清零未实现


        }
    }

    private double getDataresult(ReadData1997 readData) {
        int[] data = readData.getEffectiveData();
        StringBuilder sb = new StringBuilder();
        for (int i = data.length - 1; i >= 0; i--) {
            sb.append(data[i]);
        }
        sb.insert(sb.length() - readData.getDot(), ".");
        return Double.valueOf(sb.toString());
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }
}
