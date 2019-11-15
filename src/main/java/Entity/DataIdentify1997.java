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
        add(new byte[]{(byte)0x10,(byte)0x90});//正向有功总电能
        add(new byte[]{(byte)0x20,(byte)0x90});//反向有功总电能
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
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x90}),"total_power");//正向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x90}),"neg_positive_power");//反向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x11,(byte)0xB6}),"a_voltage");//A相电压
        put(Arrays.toString(new byte[]{(byte)0x12,(byte)0xB6}),"b_voltage");//B相电压
        put(Arrays.toString(new byte[]{(byte)0x13,(byte)0xB6}),"c_voltage");//C相电压
        put(Arrays.toString(new byte[]{(byte)0x21,(byte)0xB6}),"a_current");//A相电流
        put(Arrays.toString(new byte[]{(byte)0x22,(byte)0xB6}),"b_current");//B相电流
        put(Arrays.toString(new byte[]{(byte)0x23,(byte)0xB6}),"c_current");//C相电流
        put(Arrays.toString(new byte[]{(byte)0x30,(byte)0xB6}),"positive_power");//瞬时有功总功率
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
        put(Arrays.toString(new byte[]{(byte)0x10,(byte)0x90}),2);//正向有功总电能
        put(Arrays.toString(new byte[]{(byte)0x20,(byte)0x90}),2);//反向有功总电能
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
}
