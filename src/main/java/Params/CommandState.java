package Params;

/**
 * 命令执行状态类
 */
public class CommandState {
    public static final int UN_ENQUEUED = 0;//命令写入数据库未分配
    public static final int WAITING_IN_QUEUE = 1;//命令在等待队列中
    public static final int EXECUTING = 2;//命令正在执行
    public static final int SUCCEED =3;//命令执行成功
    public static final int FAILED =4;//命令执行失败

}
