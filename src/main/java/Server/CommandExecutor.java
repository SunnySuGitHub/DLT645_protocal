package Server;

import Entity.Command;
import Entity.Device;
import Entity.GlobalMap;
import Handler.SendHelper;
import Params.CommandState;
import Params.CommandType;
import Utils.DBUtil;
import Utils.LogUtil;
import io.netty.channel.ChannelHandlerContext;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 命令执行线程：发送命令给设备（从站）
 * 先检查正在执行的命令是否超时，如果超时，能重发则重发。不行的话就设置该命令为失败
 * 没有正在执行的命令的话就从待执行队列中提取新的命令进行执行
 */
public class CommandExecutor implements Runnable {

    @Override
    public void run() {
        while (true) {
            ConcurrentHashMap<String, Device> map = GlobalMap.getMap();
            for (Map.Entry<String, Device> deviceEntry : map.entrySet()) {
                Device device = deviceEntry.getValue();
                Command curCommand = device.getCurCommand();
                //检查正在执行的命令是否超时，如果超时，能重发则重发。不行的话就设置该命令为失败
                if (curCommand != null && curCommand.getState() == CommandState.EXECUTING) {
                    Duration duration = Duration.between(curCommand.getStartExecutingTime(), LocalDateTime.now());
                    if (duration.toMillis() / 1000 > curCommand.getSecondsLimit()) {
                        int retryNum = curCommand.getRetryNum();
                        if (retryNum < curCommand.getAllowedRetryTimes()) {
                            //重发上一条命令
                            LogUtil.MessageLog(CommandExecutor.class,"重发指令！命令类型：" + curCommand.getCommandType()+"\r\n"+"指令为：" + device.getLatestMsg());
                            SendHelper.writeAndFlush(device, device.getLatestMsg().send());
                            curCommand.setStartExecutingTime(LocalDateTime.now());//重置命令开始时间
                            curCommand.setRetryNum(retryNum + 1);
                        } else {
                            LogUtil.MessageLog(CommandExecutor.class, "Failure for overTime! The device is "
                                    + device.getDeviceNo() + ". The command"+curCommand.getCommandType()+" is allowed to be done in "
                                    + curCommand.getSecondsLimit()+ " seconds,but it has run for" + duration.toMillis() / 1000
                                    + " seconds. And its retryTime is limited in" + retryNum);
                            DBUtil.updateCommandState(curCommand.getId(), CommandState.FAILED);
                        }
                    }
                }
                //没有正在执行的命令的话就从待执行队列中提取新的命令进行执行
                ChannelHandlerContext context = device.getContext();
                //通道活跃且待执行命令不为空
                if (context.channel().isActive() && !device.getCommandsQueue().isEmpty()) {
                    if (curCommand == null || curCommand.getState() == CommandState.SUCCEED || curCommand.getState() == CommandState.FAILED) {
                        Command newCommand = device.getCommandsQueue().poll();
                        device.setCurCommand(newCommand);
                        newCommand.setState(CommandState.EXECUTING);
                        DBUtil.updateCommandState(newCommand.getId(),CommandState.EXECUTING);
                        newCommand.setStartExecutingTime(LocalDateTime.now());
                        executeCommands(device);
                    }
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void executeCommands(Device device){
        Command command = device.getCurCommand();
        CommandType commandType = command.getCommandType();
        //根据不同指令发送消息
        switch(commandType){

        }

    }
}
