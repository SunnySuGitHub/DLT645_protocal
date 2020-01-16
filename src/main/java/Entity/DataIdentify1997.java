package Entity;

import java.util.*;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:53
 * 数据标识
 */
public class DataIdentify1997 {
    /**
     * 数据标识数组
     */
    private static List<byte[]> DATAIDENTITY = new ArrayList<byte[]>(){{
        add(new byte[]{(byte)0x10,(byte)0x90});//当前正向有功总电能
        add(new byte[]{(byte)0x20,(byte)0x90});//当前反向有功总电能
        add(new byte[]{(byte)0x10,(byte)0x91});//当前正向无功总电能
        add(new byte[]{(byte)0x20,(byte)0x91});//当前反向无功总电能
        add(new byte[]{(byte)0x10,(byte)0x94});//上月正向有功总电能
        add(new byte[]{(byte)0x20,(byte)0x94});//上月反向有功总电能
        add(new byte[]{(byte)0x10,(byte)0x95});//上月正向无功总电能
        add(new byte[]{(byte)0x20,(byte)0x95});//上月反向无功总电能
        add(new byte[]{(byte)0x10,(byte)0x98});//上上月正向有功总电能
        add(new byte[]{(byte)0x20,(byte)0x98});//上上月反向有功总电能
        add(new byte[]{(byte)0x10,(byte)0x99});//上上月正向无功总电能
        add(new byte[]{(byte)0x20,(byte)0x99});//上上月反向无功总电能
        add(new byte[]{(byte)0x11,(byte)0xB6});//A相电压
        add(new byte[]{(byte)0x12,(byte)0xB6});//B相电压
        add(new byte[]{(byte)0x13,(byte)0xB6});//C相电压
        add(new byte[]{(byte)0x21,(byte)0xB6});//A相电流
        add(new byte[]{(byte)0x22,(byte)0xB6});//B相电流
        add(new byte[]{(byte)0x23,(byte)0xB6});//C相电流
        add(new byte[]{(byte)0x30,(byte)0xB6});//瞬时总功率
        add(new byte[]{(byte)0x31,(byte)0xB6});//瞬时A相有功功率
        add(new byte[]{(byte)0x32,(byte)0xB6});//瞬时B相有功功率
        add(new byte[]{(byte)0x33,(byte)0xB6});//瞬时C相有功功率
        add(new byte[]{(byte)0x40,(byte)0xB6});//瞬时总无功功率
        add(new byte[]{(byte)0x41,(byte)0xB6});//瞬时A相总无功功率
        add(new byte[]{(byte)0x42,(byte)0xB6});//瞬时B相总无功功率
        add(new byte[]{(byte)0x43,(byte)0xB6});//瞬时C相总无功功率
        add(new byte[]{(byte)0x50,(byte)0xB6});//总功率因数
        add(new byte[]{(byte)0x51,(byte)0xB6});//A相功率因数
        add(new byte[]{(byte)0x52,(byte)0xB6});//B相功率因数
        add(new byte[]{(byte)0x53,(byte)0xB6});//C相功率因数
        //增加重复项
        add(new byte[]{(byte)0x30,(byte)0xB6});//瞬时总功率
        add(new byte[]{(byte)0x31,(byte)0xB6});//瞬时A相有功功率
        add(new byte[]{(byte)0x32,(byte)0xB6});//瞬时B相有功功率
        add(new byte[]{(byte)0x33,(byte)0xB6});//瞬时C相有功功率
        add(new byte[]{(byte)0x40,(byte)0xB6});//瞬时总无功功率
    }};

    /**
     * 数据标识与其所代表含义映射map
     */
    private static Map<String ,String> IDENTIFY_NAME =new HashMap<String ,String>(){{
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x90}),"cur_positive_active_power");//当前正向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x90}),"cur_negative_active_power");//当前反向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x91}),"cur_positive_reactive_power");//当前正向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x91}),"cur_negative_reactive_power");//当前反向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x94}),"pm_positive_active_power");//上月正向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x94}),"pm_negative_active_power");//上月反向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x95}),"pm_positive_reactive_power");//上月正向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x95}),"pm_negative_reactive_power");//上月反向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x98}),"ppm_positive_active_power");//上上月正向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x98}),"ppm_negative_active_power");//上上月反向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x99}),"ppm_positive_reactive_power");//上上月正向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x99}),"ppm_negative_reactive_power");//上上月反向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x11,(byte)0xB6}),"a_voltage");//A相电压
        put(Arrays.toString(new byte[]{(byte)0x12,(byte)0xB6}),"b_voltage");//B相电压
        put(Arrays.toString(new byte[]{(byte)0x13,(byte)0xB6}),"c_voltage");//C相电压
        put(Arrays.toString(new byte[]{(byte)0x21,(byte)0xB6}),"a_current");//A相电流
        put(Arrays.toString(new byte[]{(byte)0x22,(byte)0xB6}),"b_current");//B相电流
        put(Arrays.toString(new byte[]{(byte)0x23,(byte)0xB6}),"c_current");//C相电流
        put(Arrays.toString(new byte[]{(byte)0x30,(byte)0xB6}),"positive_power");//瞬时有功功率
        put(Arrays.toString(new byte[]{(byte)0x31,(byte)0xB6}),"a_positive_power");//瞬时A相有功功率
        put(Arrays.toString(new byte[]{(byte)0x32,(byte)0xB6}),"b_positive_power");//瞬时B相有功功率
        put(Arrays.toString(new byte[]{(byte)0x33,(byte)0xB6}),"c_positive_power");//瞬时C相有功功率
        put(Arrays.toString(new byte[]{(byte)0x40,(byte)0xB6}),"reactive_power");//瞬时总无功功率
        put(Arrays.toString(new byte[]{(byte)0x41,(byte)0xB6}),"a_reactive_power");//瞬时A相总无功功率
        put(Arrays.toString(new byte[]{(byte)0x42,(byte)0xB6}),"b_reactive_power");//瞬时B相总无功功率
        put(Arrays.toString(new byte[]{(byte)0x43,(byte)0xB6}),"c_reactive_power");//瞬时C相总无功功率
        put(Arrays.toString(new byte[]{(byte)0x50,(byte)0xB6}),"influence");//总功率因数
        put(Arrays.toString(new byte[]{(byte)0x51,(byte)0xB6}),"a_influence");//A相功率因数
        put(Arrays.toString(new byte[]{(byte)0x52,(byte)0xB6}),"b_influence");//B相功率因数
        put(Arrays.toString(new byte[]{(byte)0x53,(byte)0xB6}),"c_influence");//C相功率因数
        //增加重复项
        put(Arrays.toString(new byte[]{(byte)0x30,(byte)0xB6}),"positive_power");//瞬时有功总功率
        put(Arrays.toString(new byte[]{(byte)0x31,(byte)0xB6}),"a_positive_power");//瞬时A相有功功率
        put(Arrays.toString(new byte[]{(byte)0x32,(byte)0xB6}),"b_positive_power");//瞬时B相有功功率
        put(Arrays.toString(new byte[]{(byte)0x33,(byte)0xB6}),"c_positive_power");//瞬时C相有功功率
        put(Arrays.toString(new byte[]{(byte)0x40,(byte)0xB6}),"reactive_power");//瞬时总无功功率
    }};


    /**
     * 小数点
     */
    private static Map<String ,Integer> DOT =new HashMap<String ,Integer>(){{
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x90}),2);//当前正向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x90}),2);//当前反向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x91}),2);//当前正向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x91}),2);//当前反向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x94}),2);//上月正向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x94}),2);//上月反向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x95}),2);//上月正向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x95}),2);//上月反向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x98}),2);//上上月正向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x98}),2);//上上月反向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x99}),2);//上上月正向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x99}),2);//上上月反向无功总电能
        put(Arrays.toString(new byte[]{(byte)0x11,(byte)0xB6}),0);//A相电压
        put(Arrays.toString(new byte[]{(byte)0x12,(byte)0xB6}),0);//B相电压
        put(Arrays.toString(new byte[]{(byte)0x13,(byte)0xB6}),0);//C相电压
        put(Arrays.toString(new byte[]{(byte)0x21,(byte)0xB6}),2);//A相电流
        put(Arrays.toString(new byte[]{(byte)0x22,(byte)0xB6}),2);//B相电流
        put(Arrays.toString(new byte[]{(byte)0x23,(byte)0xB6}),2);//C相电流
        put(Arrays.toString(new byte[]{(byte)0x30,(byte)0xB6}),4);//瞬时有功总功率
        put(Arrays.toString(new byte[]{(byte)0x31,(byte)0xB6}),4);//瞬时A相有功功率
        put(Arrays.toString(new byte[]{(byte)0x32,(byte)0xB6}),4);//瞬时B相有功功率
        put(Arrays.toString(new byte[]{(byte)0x33,(byte)0xB6}),4);//瞬时C相有功功率
        put(Arrays.toString(new byte[]{(byte)0x40,(byte)0xB6}),2);//瞬时总无功功率
        put(Arrays.toString(new byte[]{(byte)0x41,(byte)0xB6}),2);//瞬时A相总无功功率
        put(Arrays.toString(new byte[]{(byte)0x42,(byte)0xB6}),2);//瞬时B相总无功功率
        put(Arrays.toString(new byte[]{(byte)0x43,(byte)0xB6}),2);//瞬时C相总无功功率
        put(Arrays.toString(new byte[]{(byte)0x50,(byte)0xB6}),3);//总功率因数
        put(Arrays.toString(new byte[]{(byte)0x51,(byte)0xB6}),3);//A相功率因数
        put(Arrays.toString(new byte[]{(byte)0x52,(byte)0xB6}),3);//B相功率因数
        put(Arrays.toString(new byte[]{(byte)0x53,(byte)0xB6}),3);//C相功率因数
        //增加重复项
        put(Arrays.toString(new byte[]{(byte)0x30,(byte)0xB6}),4);//瞬时有功总功率
        put(Arrays.toString(new byte[]{(byte)0x31,(byte)0xB6}),4);//瞬时A相有功功率
        put(Arrays.toString(new byte[]{(byte)0x32,(byte)0xB6}),4);//瞬时B相有功功率
        put(Arrays.toString(new byte[]{(byte)0x33,(byte)0xB6}),4);//瞬时C相有功功率
        put(Arrays.toString(new byte[]{(byte)0x40,(byte)0xB6}),2);//瞬时总无功功率
    }};

    /**
     * 控制域与其所代表含义映射map
     */
    private static Map<Byte ,String> CONTROLCODE_NAME =new HashMap<Byte, String>(){{
        put((byte)0x41,"system_action");// 主站->集中器 系统动作
        put((byte)0x48,"read_realtime");// 主站->集中器 读实时数据
        put((byte)0x49,"read_history");//  主站->集中器 读历史数据
        put((byte)0x4A,"read_parameter");//主站->集中器 读参数
        put((byte)0x4B,"write_parameter");//主站->集中器 写参数
        put((byte)0x4C,"heartbeat");//     集中器->主站 心跳包
        put((byte)0x80,"reply_success");// 集中器->主站 确认回答
        put((byte)0x81,"reply_failed");//  集中器->主站 否认回答
        put((byte)0x82,"reply_pwd_error");//集中器->主站 密码错误
        put((byte)0x83,"reply_crc_error");//集中器->主站 校验错误
        put((byte)0x88,"reply_realtime_data");// 集中器->主站 返回实时数据
        put((byte)0x89,"reply_history_data");//  集中器->主站 返回历史数据
        put((byte)0x8A,"reply_read_parameter");//集中器->主站 返回读取参数
        put((byte)0x8B,"reply_write_parameter");//集中器->主站 返回写参数数据
    }};

    public DataIdentify1997() {
    }

    /**为了保证各个通道的协调，需要增加几条重复的命令帧，选择瞬时项**/

    public static int getLength() {
        return IDENTIFY_NAME.size();
    }

    public static List<byte[]> getDataIdentify() {
        return DATAIDENTITY;
    }

    public static Map<String, Integer> getDot() {
        return DOT;
    }

    public static Map<String, String> getIdentify_Name() {
        return IDENTIFY_NAME;
    }

    public static Map<Byte, String> getControlcode_Name() {
        return CONTROLCODE_NAME;
    }
}
