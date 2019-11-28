package Handler;

import Entity.Device;
import Entity.SendData1997;
import Params.Constants;
import Utils.CheckUtil;
import Utils.ConvertUtil;
import Utils.TimeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;

import java.time.LocalDateTime;
import java.util.Calendar;

public class SendHelper {
    /**
     * 请求读数据
     * @param device
     * @param deviceAddress 从数据库得来的地址域
     * @param dataType 从数据库得来的想获取的数据种类(数据标识)
     */
    public static void readData(Device device,String deviceAddress, String dataType){
        int[] address = ConvertUtil.addressToBCD(deviceAddress);
        int[] data = CheckUtil.getDataType(dataType);
        int control = 0x01;
        SendData1997 sendData1997 = new SendData1997(address,control,data);
        writeAndFlush(device,sendData1997.send());

    }

    /**
     * 请求后续数据
     * @param device
     * @param deviceAddress
     * @param dataType 从响应报文中提取的数据标识
     */
    public static void readNextData(Device device,String deviceAddress, byte[] dataType){
        int[] address = ConvertUtil.addressToBCD(deviceAddress);
        int control = 0x02;
        int[] data = new int[dataType.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = dataType[i];
        }
        SendData1997 sendData1997 = new SendData1997(address,control,data);

        writeAndFlush(device,sendData1997.send());
    }

    /**
     * 请求从站重发上帧数据
     * @param device
     * @param deviceAddress
     */
    public static void reReadData(Device device,String deviceAddress){
        int[] address = ConvertUtil.addressToBCD(deviceAddress);
        int control = 0x03;
        SendData1997 sendData1997 = new SendData1997(address,control,new int[0]);//没有数据域，传参为new int[0]
        writeAndFlush(device,sendData1997.send());
    }

    //写数据（暂时没有相应的数据标识）
    public static void writeData(){
        int[] address = Constants.BROADCAST;
        int control = 0x04;

    }

    /**
     * 广播校时
     * 仅当从站的日历和时钟与主站的时差在正负5min以内时执行校时命令，but how?
     */
    public static void Timing(){
        int[] address = Constants.BROADCAST;
        //数据域：YYMMDDhhmmss
        int control = 0x08;
        //没有标识符
        int[] data = TimeUtil.getTime();
        SendData1997 sendData1997 = new SendData1997(address,control,data);
        //如何发给每个设备？？

    }

    /**
     * 写设备地址
     * 注：没有标识符
     */
    public static void writeDeviceAddress(String deviceAddress){
        int[] address = Constants.BROADCAST;
        int control = 0x0A;
        int[] data = ConvertUtil.addressToBCD(deviceAddress);
        SendData1997 sendData1997 = new SendData1997(address,control,data);
        //广播
        //。。。。。。
    }

    /**
     *更改通信速率
     */
    public static void modifyCommunicationSpeed(Device device,String deviceAddress,String rate){
        int[] address = ConvertUtil.addressToBCD(deviceAddress);
        int control = 0x09;
        int rateCharacter = CheckUtil.getSpeedCharacter(rate);
        int[] data = new int[]{rateCharacter};
        SendData1997 sendData1997 = new SendData1997(address,control,data);
        writeAndFlush(device,sendData1997.send());
    }

    /**
     *最大需求量清零
     */
    public static void zeroMaxDemand(Device device,String deviceAddress,String rate){
        int[] address = ConvertUtil.addressToBCD(deviceAddress);
        int control = 0x10;
        //4字节，格式为PAn,P0n,P1n,P2n
        int[] data = null;        //................
        SendData1997 sendData1997 = new SendData1997(address,control,data);
        writeAndFlush(device,sendData1997.send());

    }

    public static void writeAndFlush(Device device,byte[] sendData1997){
        ByteBuf buf = Unpooled.copiedBuffer(sendData1997);
        ChannelFuture future = device.getContext().writeAndFlush(buf);
        //设置最近一条命令

    }
}
