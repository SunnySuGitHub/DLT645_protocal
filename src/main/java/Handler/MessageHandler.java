package Handler;

import Entity.*;
import Params.CommandState;
import Utils.ConvertUtil;
import Utils.DBUtil;

import Utils.LogUtil;
import Utils.TimeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:28
 * 消息实体handler
 */
public class MessageHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ReadData1997 readData = (ReadData1997) msg;
        String deviceAddress = readData.getDeviceAddress();
        byte [] dataType = readData.getDataType();
        ConcurrentHashMap<String,Device> map = GlobalMap.getMap();
        //处理首次上线的设备
        if (!map.contains(deviceAddress)){
            Device device = new Device(deviceAddress,ctx);
            map.put(deviceAddress,device);
            LogUtil.MessageLog(MessageHandler.class,"设备 "+deviceAddress+" 首次上线!");
            DBUtil.updateDeviceState(deviceAddress,1);
            //以下措施不知是否需要
            //首次上线，如果有命令正在执行，将其置为成功！
            List<Command> commands = DBUtil.getCommandsByDeviceAddressAndCommandState(deviceAddress, CommandState.EXECUTING);
            for (Command command:commands){
                DBUtil.updateCommandState(command.getId(),CommandState.SUCCEED);
            }
        }else{
            Device device = map.get(deviceAddress);
            device.setContext(ctx);
            Command curCommand = device.getCurCommand();
            if (curCommand!=null&&curCommand.isSuspend()){//重发掉线的命令
                if (device.getLatestMsg()!=null&&curCommand.getState()==CommandState.EXECUTING){
                    LogUtil.MessageLog(MessageHandler.class,"设备 " +deviceAddress+" 要重发命令！");
                    int retryNum = curCommand.getRetryNum();
                    if (retryNum<curCommand.getAllowedRetryTimes()){
                        SendHelper.writeAndFlush(device,device.getLatestMsg().send());
                        curCommand.setStartExecutingTime(LocalDateTime.now());
                        curCommand.setRetryNum(retryNum+1);

                    }
                }
                curCommand.setSuspend(false);
            }
        }

        Device curDevice = map.get(deviceAddress);

        //关于设备心跳；有必要将心跳更新到数据库嘛？
        if (curDevice.getHeartBeatTime()==null){
            curDevice.setHeartBeatTime(LocalDateTime.now());
        }else{
            Duration duration = Duration.between(curDevice.getHeartBeatTime(),LocalDateTime.now());
            if (duration.toMinutes()>2){
                curDevice.setHeartBeatTime(LocalDateTime.now());
                DBUtil.updateDeviceState(deviceAddress,1);
            }
        }

        //根据不同控制码和数据标识，对消息进行处理
        String dataTypeString =DataIdentify1997.getIdentify_Name().get(Arrays.toString(dataType)); //获取数据标识的String形式
        String controlCode = ConvertUtil.intToHex(readData.getControlCode());
        switch(controlCode){
            case "81": //主站请求读数据应答后 -- 无后续数据帧情况
                double dataResult = getDataResult(readData);
                //根据数据标识将获得的数据插入数据库
                //
                System.out.println("已经收到数据！");
                LogUtil.MessageLog(MessageHandler.class,readData.toString());
                //设置现在的命令成功
                System.out.println(TimeUtil.getCurrentTime() + " 主站请求读数据后" + "收到无后续数据帧情况 from "
                        + readData.getDeviceAddress()
                        + " " + dataTypeString
                        + " = " + dataResult);
                break;
            case "A1": //主站请求读数据应答后 --> 有后续数据帧情况，需要再次请求后续数据
                System.out.println(TimeUtil.getCurrentTime() + " 主站请求读数据后" + "收到有后续数据帧情况 from "
                        + readData.getDeviceAddress()
                        + " " + dataTypeString
                        + " = ");
                //根据数据标识将获得的数据插入数据库
                //

                break;
            case "82": //主站读后续数据请求应答后 --> 无后续数据帧情况
                System.out.println(TimeUtil.getCurrentTime() + " 主站请求读后续数据后" + "收到无后续数据帧情况 from "
                        + readData.getDeviceAddress()
                        + " " + dataTypeString
                        + " = ");
                //根据数据标识将获得的数据插入数据库
                //
                //设置请求后续数据命令成功
                break;
            case "A2": //主站读后续数据请求应答后 --> 有后续数据帧情况

                System.out.println(TimeUtil.getCurrentTime() + " 主站请求读后续数据后" + "收到有后续数据帧情况 from "
                        + deviceAddress + " " + dataTypeString  + " = ");
                //根据数据标识将获得的数据插入数据库
                //
                //请求后续数据
                LogUtil.MessageLog(MessageHandler.class,"收到数据:"+readData.toString());
                SendHelper.readNextData(curDevice,deviceAddress,dataType);

                break;
            case "83": //主站重读数据请求应答后 --> 无后续数据帧情况
                System.out.println(TimeUtil.getCurrentTime() + " 主站请求重读数据后" + "收到无后续数据帧情况 from "
                        + readData.getDeviceAddress()
                        + " " + dataTypeString
                        + " = ");
                //根据数据标识将获得的数据插入数据库
                //
                //设置重读命令成功
                break;
            case "A3": //主站重读数据请求应答后 --> 有后续数据帧情况
                System.out.println(TimeUtil.getCurrentTime() + " 主站请求重读数据后" + "收到有后续数据帧情况 from "
                        + readData.getDeviceAddress()
                        + " " + dataTypeString
                        + " = ");
                //根据数据标识将获得的数据插入数据库
                //
                //请求后续数据
                SendHelper.readNextData(curDevice,deviceAddress,dataType);

                break;
            case "84"://写数据的正常应答帧
                System.out.println("写入数据成功！");
                //设置写入命令成功
                break;
            case "8A"://写设备地址,正确执行该命令的应答
                System.out.println("设备地址已更新！");
                //设置写设备地址命令成功
                break;
            case "8C"://从站对更改速率请求的确认
                System.out.println("速率已更改！");
                //设置该命令成功
                break;
            case "8F"://正确执行密码修改命令
                System.out.println("已更新密码权限和密码！");
                //最大需求量清零
                //。。。
                break;
            default:
                //System.out.println("没有找到！");
                break;
        }
    }

    private double getDataResult(ReadData1997 readData) {
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

        System.out.println("出错了吧");
        cause.printStackTrace();
        ctx.close();

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
