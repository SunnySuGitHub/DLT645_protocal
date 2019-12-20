package Server;

import Entity.Command;
import Entity.Device;
import Entity.GlobalMap;
import Params.CommandState;
import Params.CommandType;
import Utils.DBUtil;
import Utils.LogUtil;
import Utils.TimeUtil;
import io.netty.channel.ChannelHandlerContext;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 监控运行状态的线程，主要作用如下：
 * 1、将“在执行”但没有心跳的命令挂起，方便以后重发
 * 2、查看通道是否活跃，以检查设备是否掉线
 */
public class ConditionMonitor implements Runnable{

    @Override
    public void run() {
        System.out.println("监控程序启动！设备初始化！");
        DBUtil.initiateDevice();
        while(true){
            try {
                TimeUnit.MINUTES.sleep(30);//30分钟检查一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ConcurrentHashMap<String, Device> map = GlobalMap.getMap();
            System.out.println("现在共有"+map.size()+"台设备");
            Iterator<Map.Entry<String,Device>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,Device> deviceEntry=iterator.next();
                Device device = deviceEntry.getValue();
                Command command = device.getCurCommand();
                try {
                    ChannelHandlerContext context = device.getContext();
                    //1、将“在执行”但没有心跳的命令挂起，方便以后重发
                    if (device.getHeartBeatTime()!=null){
                        Duration duration = Duration.between(device.getHeartBeatTime(), LocalDateTime.now());
                        if (duration.toMinutes()>8){
                            if (command.getState()== CommandState.EXECUTING){
                                LogUtil.channelLog(device.getDeviceNo(),"监控时发现命令执行时断开，将"+command.getCommandType()+"命令挂起\r\n");
                                command.setSuspend(true);
                            }
                        }
                    }
                    //2、查看通道是否活跃，以检查设备是否掉线
                    if (context.channel().isActive()){
                        LogUtil.MessageLog(ConditionMonitor.class,device.getDeviceNo()+" 正在执行的命令为： "+command.toString());
                    }else {
                        //更新数据库，将设备设为不在线
                        DBUtil.updateDeviceState(device.getDeviceNo(),0);
                        LogUtil.MessageLog(ConditionMonitor.class,"设备"+device.getDeviceNo()+"掉线啦！");
                    }
                }catch (NullPointerException e){//防止万一得不到context或channel报异常
                    //更新数据库，将设备设置为不在线
                    DBUtil.updateDeviceState(device.getDeviceNo(),0);
                    LogUtil.MessageLog(ConditionMonitor.class,"空指针异常（没有合法的信道）,设备"+device.getDeviceNo()+"掉线！");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
