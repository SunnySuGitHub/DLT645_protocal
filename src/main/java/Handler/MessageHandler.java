package Handler;

import Entity.DataIdentify1997;
import Entity.ReadData1997;
import Utils.ConvertUtil;
import Utils.TimeUtil;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.Arrays;
import java.util.Random;

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
            case 0x81:
                /**
                 * 起始符  地址域  起始符  控制码  数据域长度              数据域                校验码   结束符
                 *  0       1~6      7      8         9                   9+n
                 * 1字节   6字节   1字节   1字节     1字节         n字节(2字节数据标识+数据)      1字节   1字节
                 */
                int[] data = readData.getEffectiveData();
                StringBuilder sb = new StringBuilder();
                for (int i = data.length - 1; i >= 0; i--) {
                    sb.append(data[i]);
                }
                sb.insert(sb.length() - readData.getDot(), ".");
                double dataresult = Double.valueOf(sb.toString());
                System.out.println(TimeUtil.getCurrentTime() + " Received  " + readData.getDataType() + " from " + readData.getDeviceAddress());
                System.out.println("*********************Value: " + dataresult + "*********************");
//                switch (readData.getDataType()) {
//                    case "a_positive_power":
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "a_influence" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "a_influence" + " : " + inf + "***********************\n");
////                                writeToDataBase.write("a_influence", inf, deviceAddress);
//
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "a_apparent_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "a_apparent_power" + " : " + dataresult / inf + "***********************\n");
////                                writeToDataBase.write("a_apparent_power", dataresult/inf, deviceAddress);
//
//                        double gou = Math.sqrt(1.0 - inf * inf);
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "a_reactive_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "a_reactive_power" + " : " + dataresult * gou + "***********************\n");
////                                writeToDataBase.write("a_reactive_power", dataresult*gou, deviceAddress);
//
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "a_voltage" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "a_voltage" + " : " + voltage + "***********************\n");
////                                writeToDataBase.write("a_voltage", voltage, deviceAddress);
//
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "a_current" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "a_current" + " : " + (1000 * dataresult / voltage) + "***********************\n");
////                                writeToDataBase.write("a_current", 1000 * dataresult / voltage, deviceAddress);
//
//                        break;
//                    case "b_positive_power":
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "b_influence" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "b_influence" + " : " + inf + "***********************\n");
////                                writeToDataBase.write("b_influence", inf, deviceAddress);
//
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "b_apparent_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "b_apparent_power" + " : " + dataresult / inf + "***********************\n");
////                                writeToDataBase.write("b_apparent_power", dataresult/inf, deviceAddress);
//
//                        gou = Math.sqrt(1.0 - inf * inf) / inf;
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "b_reactive_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "b_reactive_power" + " : " + dataresult / gou + "***********************\n");
////                                writeToDataBase.write("b_reactive_power", dataresult*gou, deviceAddress);
//
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "b_voltage" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "b_voltage" + " : " + voltage + "***********************\n");
////                                writeToDataBase.write("b_voltage", voltage, deviceAddress);
//
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "b_current" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "b_current" + " : " + (1000 * dataresult / voltage) + "***********************\n");
////                                writeToDataBase.write("b_current", 1000 * dataresult / voltage, deviceAddress);
//                        break;
//                    case "c_positive_power":
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "c_influence" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "c_influence" + " : " + inf + "***********************\n");
////                                writeToDataBase.write("c_influence", inf, deviceAddress);
//
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "c_apparent_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "c_apparent_power" + " : " + dataresult / inf + "***********************\n");
////                                writeToDataBase.write("c_apparent_power", dataresult/inf, deviceAddress);
//
//                        gou = Math.sqrt(1.0 - inf * inf) / inf;
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "c_reactive_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "c_reactive_power" + " : " + dataresult * gou + "***********************\n");
////                                writeToDataBase.write("c_reactive_power", dataresult*gou, deviceAddress);
//
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "c_voltage" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "c_voltage" + " : " + voltage + "***********************\n");
////                                writeToDataBase.write("c_voltage", voltage, deviceAddress);
//
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "c_current" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "c_current" + " : " + (1000 * dataresult / voltage) + "***********************\n");
////                                writeToDataBase.write("c_current", 1000 * dataresult / voltage, deviceAddress);
//                        break;
//                    case "positive_power":
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "influence" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "influence" + " : " + inf + "***********************\n");
////                                writeToDataBase.write("influence", inf, deviceAddress);
//
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "apparent_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "apparent_power" + " : " + dataresult / inf + "***********************\n");
////                                writeToDataBase.write("apparent_power", dataresult/inf, deviceAddress);
//
//                        gou = Math.sqrt(1.0 - inf * inf) / inf;
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "reactive_power" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "reactive_power" + " : " + dataresult * gou + "***********************\n");
////                                writeToDataBase.write("reactive_power", dataresult*gou, deviceAddress);
//
//                        System.out.println(TimeUtil.getCurrentTime() + " Received  " + "wave" + " from " + deviceAddress + "  Client : " + str_addr + "\n");
//                        System.out.println("*******************" + "wave" + " : " + dataresult / inf + "***********************\n");
////                                writeToDataBase.write("wave", inf*5000, deviceAddress);
//            case 0x82:
        }
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
