package Entity;

import Utils.CheckUtil;
import Utils.ConvertUtil;
import Utils.TimeUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:55
 * DLY645 1997版数据实体
 */
public class ReadData1997 {

    private int[] effectiveData;      //有效数据
    private int controlCode;         //控制码
    private Integer dot;              //确定小数点
    private String deviceAddress;     //表地址
    private byte[] dataType;     //数据标识

    public ReadData1997(byte[] bytes) {
        /**
         * 起始符  地址域  起始符  控制码  数据域长度              数据域                校验码   结束符
         *  0       1~6      7      8         9                   9+n
         * 1字节   6字节   1字节   1字节     1字节         n字节(2字节数据标识+数据)      1字节   1字节
         */
        this.deviceAddress = CheckUtil.GetBCDAddress(bytes);
        boolean check = CheckUtil.checkData(bytes);
        if (!check) {
            System.out.println("check error");
            System.out.print("error msg: ");
            for (byte b : bytes) {
                System.out.print(b + " ");
            }
            return;
        } else {
            int[] read_ints = ConvertUtil.bytesToInts(bytes);
            this.controlCode = read_ints[8];  //控制码
            int lengthOfData = read_ints[9];  //数据域长度

            if (read_ints.length>12&&lengthOfData==1){//对于更改速率的应答，数据域中无标识符，只有一个字节；从站异常应答也是如此
                this.effectiveData = new int[]{read_ints[10]};
            }

            if (read_ints.length>12&&lengthOfData>=2){//在有数据域的情况下
                byte[] type = new byte[2];   // 两字节的数据标识
                int[] data = new int[lengthOfData - 2]; //除去两字节的数据标识剩下的数据长度

                for (int t = 0; t < 2; t++) {
                    type[t] = (byte) (read_ints[10 + t] - 0x33);  //构建数据标识 DI0 DI1
                }
                if (lengthOfData>2){
                    for (int d = 0; d < lengthOfData - 2; d++) {
                        data[d] = read_ints[12 + d] - 0x33;                  //获取真正数据域
                        //data[d] = Integer.parseInt(ConvertUtil.intToHex(data[d]));//会报异常：NumberFormatException.forInputString
                    }
                }
                this.effectiveData = data;
                this.dataType = type;
                this.dot = DataIdentify1997.getDot().get(Arrays.toString(type));
            }


        }
    }

    public int[] getEffectiveData() {
        return effectiveData;
    }

    public void setEffectiveData(int[] effectiveData) {
        this.effectiveData = effectiveData;
    }

    public int getControlCode() {
        return controlCode;
    }

    public void setControlCode(int controlCode) {
        this.controlCode = controlCode;
    }

    public Integer getDot() {
        return dot;
    }

    public void setDot(Integer dot) {
        this.dot = dot;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public byte[] getDataType() {
        return dataType;
    }

    public void setDataType(byte[] dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "ReadData1997{" +
                "effectiveData=" + Arrays.toString(effectiveData) +
                ", controlCode=" + controlCode +
                ", dot=" + dot +
                ", deviceAddress='" + deviceAddress + '\'' +
                ", dataType=" + Arrays.toString(dataType) +
                '}';
    }
}
