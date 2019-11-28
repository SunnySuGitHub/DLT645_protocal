package Entity;

import io.netty.channel.ChannelHandlerContext;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 设备实体，无法与数据库对应
 * 数据库中应有字段：id,deviceAddress,state...，设备密码等
 */
public class Device {
    private String deviceNo;//设备地址
    private ChannelHandlerContext context;//设备对应的数据传输通道
    private LocalDateTime heartBeatTime;//最近一次心跳时间
    private Command curCommand;//正在执行的命令
    private SendData1997 latestMsg;//最近一次发送的命令
    private ConcurrentLinkedQueue<Command> commandsQueue = new ConcurrentLinkedQueue<>();//待执行的命令队列

    public Device(String deviceNo, ChannelHandlerContext context) {
        this.deviceNo = deviceNo;
        this.context = context;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }

    public LocalDateTime getHeartBeatTime() {
        return heartBeatTime;
    }

    public void setHeartBeatTime(LocalDateTime heartBeatTime) {
        this.heartBeatTime = heartBeatTime;
    }

    public Command getCurCommand() {
        return curCommand;
    }

    public void setCurCommand(Command curCommand) {
        this.curCommand = curCommand;
    }

    public SendData1997 getLatestMsg() {
        return latestMsg;
    }

    public void setLatestMsg(SendData1997 latestMsg) {
        this.latestMsg = latestMsg;
    }

    public ConcurrentLinkedQueue<Command> getCommandsQueue() {
        return commandsQueue;
    }

    public void setCommandsQueue(ConcurrentLinkedQueue<Command> commandsQueue) {
        this.commandsQueue = commandsQueue;
    }
}
