package Params;

/**
 * 命令类型类
 * 电能量和最大需量根据命令标识
 */
public enum CommandType {
    NONE(""),//无命令
    //读数据的命令,统一以0开头
    CUR_POSITIVE_ACTIVE_POWER("016144"),//当前正向有功总电能
    CUR_NEGATIVE_ACTIVE_POWER("032144"),//当前反向有功总电能
    CUR_POSITIVE_REACTIVE_POWER("016145"),//当前正向无功总电能
    CUR_NEGATIVE_REACTIVE_POWER("032145"),//前反向无功总电能
    PM_POSITIVE_ACTIVE_POWER("016148"),//上月正向有功总电能
    PM_NEGATIVE_ACTIVE_POWER("032148"),//上月反向有功总电能
    PM_POSITIVE_REACTIVE_POWER("016149"),//上月正向无功总电能
    PM_NEGATIVE_REACTIVE_POWER("032149"),//上月反向无功总电能
    PPM_POSITIVE_ACTIVE_POWER("016152"),//上上月正向有功总电能
    PPM_NEGATIVE_ACTIVE_POWER("032152"),//上上月反向有功总电能
    PPM_POSITIVE_REACTIVE_POWER("016153"),//上上月正向无功总电能
    PPM_NEGATIVE_REACTIVE_POWER("032153"),//上上月反向无功总电能
    A_VOLTAGE("017182"),//A相电压
    B_VOLTAGE("018182"),//B相电压
    C_VOLTAGE("019182"),//C相电压
    A_CURRENT("033182"),//A相电流
    B_CURRENT("034182"),//B相电流
    C_CURRENT("035182"),//C相电流
    POSITIVE_POWER("048182"),//瞬时有功功率
    A_POSITIVE_POWER("049182"),//瞬时A相有功功率
    B_POSITIVE_POWER("050182"),//瞬时B相有功功率
    C_POSITIVE_POWER("051182"),//瞬时C相有功功率
    REACTIVE_POWER("064182"),//瞬时总无功功率
    A_REACTIVE_POWER("065182"),//瞬时A相总无功功率
    B_REACTIVE_POWER("066182"),//瞬时B相总无功功率
    C_REACTIVE_POWER("067182"),//瞬时C相总无功功率
    INFLUENCE("080182"),//总功率因数
    A_INFLUENCE("081182"),//A相功率因数
    B_INFLUENCE("082182"),//B相功率因数
    C_INFLUENCE("083182"),//C相功率因数

    //写数据的命令，可能根据写入不同的命令而有不同的标识，暂时定为总体
    WRITE_DATA("8768"),
    MODIFY_COMM_SPEED("6783"),//更改通信速率
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
