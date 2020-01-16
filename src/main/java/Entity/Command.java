package Entity;

import Params.CommandType;

import java.time.LocalDateTime;

public class Command {
    int id;//命令Id
    String deviceNo;//命令所属设备
    CommandType commandType = CommandType.NONE;//命令类型
    int state;//命令执行状态，对应于CommandState类
    //命令的相关参数，可以是标识符、密码、设备地址等信息
    String args1;//命令的相关参数1
    String args2;//命令的相关参数2
    LocalDateTime generateTime;//命令生成时间
    LocalDateTime startExecutingTime;//命令开始执行时间
    int secondsLimit=60;//时间限制，暂时随便定的时间60s
    boolean isSuspend;//命令在执行中是否中断,默认false
    int retryNum;//重试次数
    int allowedRetryTimes=1;//允许重试次数，暂时默认为1

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getArgs1() {
        return args1;
    }

    public void setArgs1(String args1) {
        this.args1 = args1;
    }

    public String getArgs2() {
        return args2;
    }

    public void setArgs2(String args2) {
        this.args2 = args2;
    }

    public LocalDateTime getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(LocalDateTime generateTime) {
        this.generateTime = generateTime;
    }

    public LocalDateTime getStartExecutingTime() {
        return startExecutingTime;
    }

    public void setStartExecutingTime(LocalDateTime startExecutingTime) {
        this.startExecutingTime = startExecutingTime;
    }

    public int getSecondsLimit() {
        return secondsLimit;
    }

    public void setSecondsLimit(int secondsLimit) {
        this.secondsLimit = secondsLimit;
    }

    public boolean isSuspend() {
        return isSuspend;
    }

    public void setSuspend(boolean suspend) {
        isSuspend = suspend;
    }

    public int getRetryNum() {
        return retryNum;
    }

    public void setRetryNum(int retryNum) {
        this.retryNum = retryNum;
    }

    public int getAllowedRetryTimes() {
        return allowedRetryTimes;
    }

    public void setAllowedRetryTimes(int allowedRetryTimes) {
        this.allowedRetryTimes = allowedRetryTimes;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", deviceNo='" + deviceNo + '\'' +
                ", commandType=" + commandType +
                ", state=" + state +
                ", args1='" + args1 + '\'' +
                ", args2='" + args2 + '\'' +
                ", generateTime=" + generateTime +
                ", startExecutingTime=" + startExecutingTime +
                ", secondsLimit=" + secondsLimit +
                ", isSuspend=" + isSuspend +
                ", retryNum=" + retryNum +
                ", allowedRetryTimes=" + allowedRetryTimes +
                '}';
    }
}
