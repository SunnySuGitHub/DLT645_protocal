package Params;

/**
 * 命令类型类
 * 电能量和最大需量根据命令标识
 */
public enum CommandType {
    NONE(""),//无命令
    //读数据的命令
    CUR_POSITIVE_ACTIVE_POWER("016144")//当前正向有功总电能
    //。。。
    //写数据的命令
    //。。。
    ;


    private String value;
    CommandType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
